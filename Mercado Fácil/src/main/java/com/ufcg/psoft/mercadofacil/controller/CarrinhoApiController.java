package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.interfaces.Perfil;
import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.service.*;
import com.ufcg.psoft.mercadofacil.util.*;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.ufcg.psoft.mercadofacil.model.FormaPagamento.BOLETO;
import static com.ufcg.psoft.mercadofacil.model.FormaPagamento.VAZIA;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoApiController {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    CarrinhoService carrinhoService;
    @Autowired
    LoteService loteService;
    @Autowired
    CompraService compraService;
    @Autowired
    UsuarioService usuarioService;


    @RequestMapping(value="/carrinho/items/",method = RequestMethod.POST)
    public ResponseEntity<?> addProduto(@RequestBody CarrinhoItemDTO dto){
        long idProduto = dto.getId();
        int quantidadeProduto = dto.getQtd();
        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(dto.getCpf());

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(dto.getCpf());
        }

        Usuario usuario = optionalUsuario.get();

        Long idCarrinho;
        if(usuario.getCarrinho() == null){
            idCarrinho = carrinhoService.createCarrinho();
            usuario.setCarrinho(carrinhoService.getCarrinhoById(idCarrinho).get());
            carrinhoService.setStatusCarrinho(idCarrinho ,StatusCarrinho.ATIVO);
        }
        idCarrinho = usuario.getCarrinho().getId();

        if(carrinhoService.getStatusCarrinho(idCarrinho).toString().equals("ENCERRADO") || carrinhoService.getStatusCarrinho(idCarrinho).toString().equals("DELETADO")){
            carrinhoService.setStatusCarrinho(idCarrinho ,StatusCarrinho.ATIVO);
        }


        Optional<Produto> optionalProduto = produtoService.getProdutoById(idProduto);

        if (!optionalProduto.isPresent()) {
            return ErroProduto.erroProdutoNaoEnconrtrado(idProduto);
        }

        Produto produto = optionalProduto.get();
        if(!produto.isDisponivel()){
            return ErroProduto.erroProdutoNaoDisponivel(produto);
        }

        if(loteService.produtosTotais(idProduto) < quantidadeProduto){
            return ErroProduto.erroProdutoSemEstoque(produto);
        }

        CarrinhoItem produtoCarrinho = new CarrinhoItem(produto,quantidadeProduto,carrinhoService.getCarrinhoById(idCarrinho).get());
        carrinhoService.addProdutos(produtoCarrinho, idCarrinho);
        String response = "Item "+ produtoCarrinho.getIdProduto() + " adicionado";
        return new ResponseEntity<String>(response,HttpStatus.OK);

    }

    @RequestMapping(value="/carrinho/items/",method = RequestMethod.DELETE)
    public ResponseEntity<?> removeProduto(@RequestBody CarrinhoItemDTO dto) {
        long idProduto = dto.getId();
        int quantidadeProduto = dto.getQtd();

        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(dto.getCpf());

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(dto.getCpf());
        }

        Usuario usuario = optionalUsuario.get();

        if(usuario.getCarrinho() == null){
            return ErroCarrinho.erroCarrinhoInexistente();
        }

        Long idCarrinho = usuario.getCarrinho().getId();

        if(carrinhoService.isEmpty(idCarrinho) || usuario.getCarrinho().getStatusCarrinho().equals("ENCERRADO") || usuario.getCarrinho().getStatusCarrinho().equals("DELETADO")){
            return ErroCarrinho.erroCarrinhoVazio();
        }


        Optional<Produto> optionalProduto = produtoService.getProdutoById(idProduto);

        if (!optionalProduto.isPresent()) {
            return ErroProduto.erroProdutoNaoEnconrtrado(idProduto);
        }

        Produto produto = optionalProduto.get();

        CarrinhoItem produtoCarrinho = new CarrinhoItem(produto,quantidadeProduto,usuario.getCarrinho());
        if(!carrinhoService.temProdutoNoCarrinho(produtoCarrinho, idCarrinho)){
            return ErroCarrinho.erroProdutoNaoEstaNoCarrinho(produtoCarrinho.getProduto());
        }
        carrinhoService.removeProdutos(produtoCarrinho, idCarrinho);
        int quantidadeRestante = carrinhoService.restanteQtd(produtoCarrinho, idCarrinho);
        String response = quantidadeProduto + " produtos foram retirados do carrinho, restam " + quantidadeRestante;
        return new ResponseEntity<String>(response,HttpStatus.OK);

    }

    @RequestMapping(value = "/carrinho/{num_cpf}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarCarrinho(@PathVariable("num_cpf") Long cpf){

        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        Usuario usuario = optionalUsuario.get();

        if(usuario.getCarrinho() == null){
           return ErroCarrinho.erroCarrinhoAindaNaoCriado();
        }
        Long idCarrinho = usuario.getCarrinho().getId();
        if(carrinhoService.getStatusCarrinho(idCarrinho).toString().equals("DELETADO")){
            return ErroCarrinho.erroCarrinhoJaApagado();
        }

        carrinhoService.deletCarrinho(idCarrinho);


        return new ResponseEntity<String>("Carrinho deletado", HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho/{num_cpf}", method = RequestMethod.GET)
    public ResponseEntity<?> listProdutosCarrinho(@PathVariable("num_cpf") Long cpf){

        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        Usuario usuario = optionalUsuario.get();

        if(usuario.getCarrinho() == null){
            return ErroCarrinho.erroCarrinhoAindaNaoCriado();
        }

        Long idCarrrinho = usuario.getCarrinho().getId();

        if(carrinhoService.getStatusCarrinho(idCarrrinho).toString().equals("DELETADO") || carrinhoService.getStatusCarrinho(idCarrrinho).toString().equals("ENCERRADO")){
            return ErroCarrinho.erroCarrinhoInexistente();
        }

        if(carrinhoService.isEmpty(idCarrrinho)){
            return ErroCarrinho.erroCarrinhoVazio();
        }

        List<CarrinhoItem> listaItens = carrinhoService.listItensCarrinhos(idCarrrinho);

        return new ResponseEntity<List<CarrinhoItem>>(listaItens,HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho/compra/{cpf}", method = RequestMethod.POST)
    public ResponseEntity<?> compraProdutos(@PathVariable("cpf") Long cpf, @RequestBody String formaPagamentoString){
        String formaPagamento = Util.deAccent(formaPagamentoString).toLowerCase().replaceAll(" ","").trim();
        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        Usuario usuario = optionalUsuario.get();

        if(usuario.getCarrinho() == null){
            return ErroCarrinho.erroCarrinhoAindaNaoCriado();
        }

        Long idCarrinho = usuario.getCarrinho().getId();

        if(carrinhoService.getStatusCarrinho(idCarrinho).toString().equals("DELETADO") || carrinhoService.getStatusCarrinho(idCarrinho).toString().equals("ENCERRADO")){
            return ErroCarrinho.erroCarrinhoInexistente();
        }

        if(carrinhoService.isEmpty(idCarrinho)){
            return ErroCarrinho.erroCarrinhoVazio();
        }

        FormaPagamento formaPagamentoEnum = BOLETO;

        if(formaPagamento.equals("paypal")){
            formaPagamentoEnum = FormaPagamento.PAYPAL;
        } else if(formaPagamento.equals("boleto")){
            formaPagamentoEnum = FormaPagamento.BOLETO;
        } else if(formaPagamento.equals("cartaodecredito")){
            formaPagamentoEnum = FormaPagamento.CARTAO;
        } else{
            return ErroCarrinho.erroFormaDePagamentoInexistente(formaPagamento);
        }

        carrinhoService.setFormaDePagamento(formaPagamentoEnum, idCarrinho);


        Carrinho carrinho = carrinhoService.getCarrinhoById(idCarrinho).get();

        List<CarrinhoItem> itensCarrinho = carrinho.getProdutosCarrinho();

        Compra novaCompra = compraService.novaCompra();

        for(CarrinhoItem item : itensCarrinho){
            long idItem = item.getProduto().getId();
            int qtdItem = item.getQuantidade();

            CarrinhoItem itemComprado = new CarrinhoItem(item.getProduto(),item.getQuantidade(), item.getProduto().getPreco());

            if(loteService.produtosTotais(idItem) < qtdItem){
                return ErroProduto.erroProdutoSemEstoque(item.getProduto());
            }

            compraService.addItem(itemComprado,novaCompra.getId());
            loteService.retiraProdutos(idItem,qtdItem);
        }
        Perfil perfilUsuario = usuarioService.getPerfil(cpf);

        int qtdItens = carrinhoService.getQtdItens(idCarrinho);

        BigDecimal valorSemDesconto = carrinhoService.getValor(idCarrinho);
        BigDecimal valorDoAcrescimo = carrinhoService.getValorAcrescimo(idCarrinho);
        BigDecimal valorDesconto = perfilUsuario.calculaDesconto(valorSemDesconto,qtdItens);

        BigDecimal valorTotalCompra = valorSemDesconto.add(valorDoAcrescimo).subtract(valorDesconto);

        compraService.setValorCompra(valorTotalCompra,novaCompra.getId());
        compraService.setDataCompra(LocalDateTime.now(),novaCompra.getId());
        compraService.setIdUsuario(novaCompra.getId(),cpf);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        String formaDePagamento = "\n" + "Sua forma de pagamento escolhida foi: " + Util.formataFormaDePagamento(carrinhoService.getFormaDePagamento(idCarrinho));
        compraService.setFormaPagamento(carrinhoService.getFormaDePagamento(idCarrinho),novaCompra.getId());
        String descricaoCompra = "\n" + formaDePagamento + "\n" + carrinhoService.pagaCompra(idCarrinho);
        if(perfilUsuario.temDesconto(qtdItens)){
            descricaoCompra +=  "\n" + "Valor do desconto devido ao Perfil " + perfilUsuario.toString() + " :" + df.format(valorDesconto);
        }

        descricaoCompra += "\n" + "Valor total da Compra: " + df.format(valorTotalCompra);
        compraService.setDescricaoCompra(descricaoCompra,novaCompra.getId());
        String idCompra = "\n" + "ID da compra: " + novaCompra.getId().toString();

        descricaoCompra += idCompra;


        return new ResponseEntity<String>(descricaoCompra,HttpStatus.OK);

    }

    @RequestMapping(value="/carrinho/formas-de-pagamento-disponiveis", method = RequestMethod.GET)
    public ResponseEntity<?> listaFormaPagamentos(){
        String response = "1. PayPal\n" + "2. Boleto\n" + "3. Cartão de Crédito\n";

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @RequestMapping(value="/carrinho/forma-de-pagamento", method = RequestMethod.GET)
    public ResponseEntity<?> getFormaDePagamento(@RequestBody Long cpf){
        String formaDePagamento = "";

        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        Usuario usuario = optionalUsuario.get();

        if(usuario.getCarrinho() == null){
            return ErroCarrinho.erroCarrinhoAindaNaoCriado();
        }

        Long idCarrrinho = usuario.getCarrinho().getId();

        if(carrinhoService.getStatusCarrinho(idCarrrinho).toString().equals("DELETADO") || carrinhoService.getStatusCarrinho(idCarrrinho).toString().equals("ENCERRADO")){
            return ErroCarrinho.erroCarrinhoInexistente();
        }

        FormaPagamento formaPagamento = carrinhoService.getFormaDePagamento(idCarrrinho);

        formaDePagamento = Util.formataFormaDePagamento(formaPagamento);

        String response = "A forma de pagamento atualmente é " + formaDePagamento;

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }


    @RequestMapping(value="/carrinho/forma-de-pagamento", method = RequestMethod.POST)
    public ResponseEntity<?> selecionaFormaPagamentos(@RequestBody String formaPagamentoString, @RequestBody Long cpf){
        FormaPagamento formaPagamentoEnum = VAZIA;
        String formaPagamento = Util.deAccent(formaPagamentoString).toLowerCase().replaceAll(" ","").trim();

        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(cpf);

        if(optionalUsuario.isEmpty()){
            return ErroUsuario.erroCPFNaoCadastrado(cpf);
        }

        Usuario usuario = optionalUsuario.get();

        if(usuario.getCarrinho() == null){
            return ErroCarrinho.erroCarrinhoAindaNaoCriado();
        }

        Long idCarrrinho = usuario.getCarrinho().getId();

        if(carrinhoService.getStatusCarrinho(idCarrrinho).toString().equals("DELETADO") || carrinhoService.getStatusCarrinho(idCarrrinho).toString().equals("ENCERRADO")){
            return ErroCarrinho.erroCarrinhoInexistente();
        }

        if(formaPagamento.equals("paypal")){
            formaPagamentoEnum = FormaPagamento.PAYPAL;
        } else if(formaPagamento.equals("boleto")){
            formaPagamentoEnum = FormaPagamento.BOLETO;
        } else if(formaPagamento.equals("cartaodecredito")){
            formaPagamentoEnum = FormaPagamento.CARTAO;
        } else{
            return ErroCarrinho.erroFormaDePagamentoInexistente(formaPagamentoString);
        }

        carrinhoService.setFormaDePagamento(formaPagamentoEnum, idCarrrinho);
        return new ResponseEntity<String>(formaPagamentoEnum.toString() + " é a sua forma de pagamento atualmente",HttpStatus.OK);
    }


}


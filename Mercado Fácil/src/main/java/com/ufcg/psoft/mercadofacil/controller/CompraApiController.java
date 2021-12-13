package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.FormaPagamento;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroCompra;
import com.ufcg.psoft.mercadofacil.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CompraApiController {

    @Autowired
    CompraService compraService;

    @RequestMapping(value="/compra/list", method = RequestMethod.GET)
    public ResponseEntity<?> listaTodasCompras(){
        List<Compra> listaCompras = compraService.listarCompras();

        if(listaCompras.isEmpty()){
            return ErroCompra.erroNenhumaCompra();
        }

        return new ResponseEntity<List<Compra>>(listaCompras, HttpStatus.OK);
    }

    @RequestMapping(value = "/compra/{id}/data", method = RequestMethod.GET)
    public ResponseEntity<?> dataCompraById(@PathVariable ("id") Long id){
        Optional<Compra> optionalCompra = compraService.getCompraById(id);

        if(!optionalCompra.isPresent()){
            return ErroCompra.erroCompraInexistente();
        }

        String response = "A data da compra foi: " + compraService.getDataCompra(optionalCompra.get()).toString();

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/compra/{id}/itens", method = RequestMethod.GET)
    public ResponseEntity<?> itensCompraById(@PathVariable ("id") Long id){
        Optional<Compra> optionalCompra = compraService.getCompraById(id);

        if(!optionalCompra.isPresent()){
            return ErroCompra.erroCompraInexistente();
        }

        List<CarrinhoItem> listaItens = compraService.getItensCompra(optionalCompra.get());

        String response = "";

        for(CarrinhoItem item : listaItens){
            response += "- Produto - " + item.getProduto().getNome() + " - Quantidade - " + item.getQuantidade() + "\n";
        }

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/compra/{id}/valor", method = RequestMethod.GET)
    public ResponseEntity<?> valorCompraById(@PathVariable ("id") Long id) {
        Optional<Compra> optionalCompra = compraService.getCompraById(id);

        if(!optionalCompra.isPresent()){
            return ErroCompra.erroCompraInexistente();
        }

        BigDecimal valor = compraService.getValorCompra(optionalCompra.get());
        String response = "O valor da compra foi: " + valor.toString();

        return new ResponseEntity<String>(response,HttpStatus.OK);

    }

    @RequestMapping(value = "/compra/{id}/forma-de-pagamento", method = RequestMethod.GET)
    public ResponseEntity<?> formaDePagamentoCompraById(@PathVariable ("id") Long id) {
        Optional<Compra> optionalCompra = compraService.getCompraById(id);

        if(!optionalCompra.isPresent()){
            return ErroCompra.erroCompraInexistente();
        }


        FormaPagamento formaPagamento = compraService.getFormaPagamento(optionalCompra.get());
        String response = "A forma de pagamento da compra foi: " + Util.formataFormaDePagamento(formaPagamento);

        return new ResponseEntity<String>(response,HttpStatus.OK);

    }

}

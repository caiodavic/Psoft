package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.model.FormaPagamento;
import com.ufcg.psoft.mercadofacil.model.StatusCarrinho;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class CarrinhoServiceImpl implements CarrinhoService{

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Long createCarrinho(){
        Carrinho carrinho = new Carrinho();
        carrinhoRepository.save(carrinho);

        return carrinho.getId();
    }

    public Optional<Carrinho> getCarrinhoById(Long id){
       return carrinhoRepository.findById(id);

    }

    public void addProdutos(CarrinhoItem item, Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        carrinho.addProduto(item);
        carrinhoRepository.save(carrinho);
    }

    public List<CarrinhoItem> listItensCarrinhos(Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.carrinhoItemList();
    }

    public void removeProdutos(CarrinhoItem item, Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        carrinho.removeProduto(item);
        carrinhoRepository.save(carrinho);
    }

    public boolean isEmpty(Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.carrinhoItemList().isEmpty();
    }

    public int restanteQtd(CarrinhoItem item, Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.quantidadeRestanteProduto(item);
    }

    public void deletCarrinho(Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        carrinho.setStatusCarrinho(StatusCarrinho.DELETADO);
        carrinhoRepository.save(carrinho);
    }

    public boolean temProdutoNoCarrinho(CarrinhoItem item, Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.temProduto(item);
    }

    @Override
    public void setFormaDePagamento(FormaPagamento formaPagamento, Long id) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        carrinho.setFormaPagamento(formaPagamento);
        carrinhoRepository.save(carrinho);
    }

    @Override
    public FormaPagamento getFormaDePagamento(Long id) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.getFormaPagamento();
    }

    @Override
    public void salvaCarrinho(Long id) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        carrinhoRepository.save(carrinho);
    }

    @Override
    public StatusCarrinho getStatusCarrinho(Long id) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.getStatusCarrinho();
    }

    @Override
    public void setStatusCarrinho(Long id, StatusCarrinho statusCarrinho) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        carrinho.setStatusCarrinho(statusCarrinho);
        carrinhoRepository.save(carrinho);
    }

    @Override
    public BigDecimal getValor(Long id) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.valorTotal();
    }

    @Override
    public BigDecimal getValorAcrescimo(Long id) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.calculaAcrescimo(carrinho.valorTotal());
    }

    @Override
    public int getQtdItens(Long id) {
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        return carrinho.getNumeroDeItens();
    }

    public boolean existCarrinho(){
        return !carrinhoRepository.findAll().isEmpty();
    }

    public String pagaCompra(Long id){
        Optional<Carrinho> optionalCarrinho = getCarrinhoById(id);
        Carrinho carrinho = optionalCarrinho.get();
        String response = carrinho.compraToString();
        carrinho.esvaziaCarrinho();
        carrinho.setStatusCarrinho(StatusCarrinho.ENCERRADO);
        return response;
    }

}

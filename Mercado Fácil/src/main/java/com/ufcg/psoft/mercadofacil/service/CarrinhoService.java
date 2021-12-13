package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.*;

public interface CarrinhoService {

    public Long createCarrinho();
    public void addProdutos(CarrinhoItem item, Long id);
    public Optional<Carrinho> getCarrinhoById(Long id);
    public List<CarrinhoItem> listItensCarrinhos(Long id);
    public void removeProdutos(CarrinhoItem item,Long id);
    public boolean isEmpty(Long id);
    public int restanteQtd(CarrinhoItem item, Long id);
    public void deletCarrinho(Long id);
    public boolean existCarrinho();
    public String pagaCompra(Long idCarrinho);
    public boolean temProdutoNoCarrinho(CarrinhoItem item, Long id);
    public void setFormaDePagamento(FormaPagamento formaPagamento, Long id);
    public FormaPagamento getFormaDePagamento(Long id);
    public void salvaCarrinho(Long id);
    public StatusCarrinho getStatusCarrinho(Long id);
    public void setStatusCarrinho(Long id, StatusCarrinho statusCarrinho);
    public BigDecimal getValor(Long id);
    public BigDecimal getValorAcrescimo(Long id);
    public int getQtdItens(Long id);
}

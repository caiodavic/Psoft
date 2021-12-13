package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.FormaPagamento;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompraService {

    public Compra novaCompra();

    public Optional<Compra> getCompraById(Long id);

    public void salvarCompra(Compra compra);

    public List<Compra> listarCompras();

    public void addItem(CarrinhoItem carrinhoItem, Long id);

    public void setDataCompra(LocalDateTime data, Long id);

    public void setValorCompra(BigDecimal valorTotal, Long id);

    public void setDescricaoCompra(String descricao, Long id);

    public void setFormaPagamento(FormaPagamento formaPagamento, Long id);

    public LocalDateTime getDataCompra(Compra compra);

    public List<CarrinhoItem> getItensCompra(Compra compra);

    public BigDecimal getValorCompra(Compra compra);

    public FormaPagamento getFormaPagamento(Compra compra);

    public void setIdUsuario(Long id, Long idUsuario);

}

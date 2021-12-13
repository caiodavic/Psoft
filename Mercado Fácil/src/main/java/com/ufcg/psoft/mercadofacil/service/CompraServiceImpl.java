package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.FormaPagamento;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;
import com.ufcg.psoft.mercadofacil.util.ErroCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService{


    @Autowired
    private CompraRepository compraRepository;

    @Override
    public Compra novaCompra() {
        Compra compra = new Compra();
        this.salvarCompra(compra);
        return compra;
    }

    @Override
    public Optional<Compra> getCompraById(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public void salvarCompra(Compra compra) {
        compraRepository.save(compra);
    }

    @Override
    public List<Compra> listarCompras() {
        List<Compra> compras = compraRepository.findAll();
        return compras;
    }

    @Override
    public void addItem(CarrinhoItem carrinhoItem, Long id) {
        Optional<Compra> optionalCompra = getCompraById(id);
        optionalCompra.get().addItem(carrinhoItem);
        this.salvarCompra(optionalCompra.get());
    }

    @Override
    public void setDataCompra(LocalDateTime data, Long id) {
        Optional<Compra> optionalCompra = getCompraById(id);
        optionalCompra.get().setDataCompra(data);
        this.salvarCompra(optionalCompra.get());
    }

    @Override
    public void setValorCompra(BigDecimal valorTotal, Long id) {
        Optional<Compra> optionalCompra = getCompraById(id);
        optionalCompra.get().setValorCompra(valorTotal);
        this.salvarCompra(optionalCompra.get());
    }

    @Override
    public void setDescricaoCompra(String descricao, Long id) {
        Optional<Compra> optionalCompra = getCompraById(id);
        optionalCompra.get().setDescricaCompra(descricao);
        this.salvarCompra(optionalCompra.get());
    }

    @Override
    public void setFormaPagamento(FormaPagamento formaPagamento, Long id) {
        Optional<Compra> optionalCompra = getCompraById(id);
        optionalCompra.get().setFormaPagamento(formaPagamento);
        this.salvarCompra(optionalCompra.get());
    }

    @Override
    public LocalDateTime getDataCompra(Compra compra) {
        return compra.getDataCompra();
    }

    @Override
    public List<CarrinhoItem> getItensCompra(Compra compra) {
        return compra.getItensComprados();
    }

    @Override
    public BigDecimal getValorCompra(Compra compra) {
        return compra.getValorCompra();
    }

    @Override
    public FormaPagamento getFormaPagamento(Compra compra) {
        return compra.getFormaPagamento();
    }

    @Override
    public void setIdUsuario(Long id, Long idUsuario) {
        Optional<Compra> optionalCompra = getCompraById(id);
        optionalCompra.get().setIdUsuario(idUsuario);
    }

}

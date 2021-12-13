package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.util.Util;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dataCompra;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CarrinhoItem> itensComprados;
    private BigDecimal valorCompra;
    @Column(length = 100000)
    private String descricaCompra;
    private String descricaoCompleta;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    private Long idUsuario;


    public Compra(){
        itensComprados = new ArrayList<CarrinhoItem>();
    }


    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public void addItem(CarrinhoItem carrinhoItem){
        itensComprados.add(carrinhoItem);
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setDescricaCompra(String descricaCompra) {
        this.descricaCompra = descricaCompra;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public List<CarrinhoItem> getItensComprados() {
        return itensComprados;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricaoCompleta(){
        String descricaData = "Data: " + dataCompra.toString();
        descricaData += descricaCompra;

        return descricaData;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", dataCompra=" + dataCompra +
                ", itensComprados=" + itensComprados +
                ", valorCompra=" + valorCompra +
                '}';
    }

    public Long getId() {
        return id;
    }
}

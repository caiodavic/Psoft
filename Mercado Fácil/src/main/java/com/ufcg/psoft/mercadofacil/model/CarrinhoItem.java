package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class CarrinhoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Produto produto;
    private int quantidade;
    @ManyToOne(cascade = CascadeType.ALL)
    private Carrinho carrinho;
    private BigDecimal precoComprado;

    public CarrinhoItem(){}

    public CarrinhoItem(Produto produto, int quantidade, Carrinho carrinho){
        this.produto = produto;
        this.quantidade = quantidade;
        this.carrinho = carrinho;
    }

    public CarrinhoItem(Produto produto, int quantidade, BigDecimal precoComprado){
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoComprado = precoComprado;
    }
    public Produto getProduto(){
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public long getIdProduto() {
        return produto.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoItem that = (CarrinhoItem) o;
        return produto.equals(that.produto);
    }

    public void somaQuantidade(int soma) {
        this.quantidade = this.quantidade+soma;
    }

    public void subtraiQuantidade(int subtrai){
        this.quantidade = quantidade - subtrai;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public void setPrecoComprado(BigDecimal precoComprado) {
        this.precoComprado = precoComprado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoComprado() {
        return precoComprado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto);
    }

    @Override
    public String toString() {
        return "CarrinhoItem{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}

package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.service.ProdutoServiceImpl;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    @Enumerated(EnumType.STRING)
    private StatusCarrinho statusCarrinho;
    private int numeroDeItens;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CarrinhoItem> produtosCarrinho;


    public Carrinho(){
        produtosCarrinho = new ArrayList<CarrinhoItem>();
        formaPagamento = FormaPagamento.BOLETO;
        numeroDeItens = 0;
    }

    public void addProduto(CarrinhoItem item) {
        Produto produto = item.getProduto();
        if(produto.isDisponivel() && produtosCarrinho.contains(item)){
            produtosCarrinho.get(produtosCarrinho.indexOf(item)).somaQuantidade(item.getQuantidade());
        }else if(produto.isDisponivel()){
                produtosCarrinho.add(item);
        }
        numeroDeItens = numeroDeItens + item.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public List<CarrinhoItem> carrinhoItemList(){
        return produtosCarrinho;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void removeProduto(CarrinhoItem item) {
        if (produtosCarrinho.contains(item)) {
            if (item.getQuantidade() >= produtosCarrinho.get(produtosCarrinho.indexOf(item)).getQuantidade()) {
                produtosCarrinho.get(produtosCarrinho.indexOf(item)).setQuantidade(0);
                produtosCarrinho.remove(item);
            } else {
                produtosCarrinho.get(produtosCarrinho.indexOf(item)).subtraiQuantidade(item.getQuantidade());
            }
        }
    }

    public void esvaziaCarrinho(){
        produtosCarrinho.clear();
    }

    public int quantidadeRestanteProduto(CarrinhoItem item){
        int qtd = 0;
        if(produtosCarrinho.contains(item)){
            qtd = produtosCarrinho.get(produtosCarrinho.indexOf(item)).getQuantidade();
        }
        return qtd;
    }

    public List<CarrinhoItem> getProdutosCarrinho() {
        return produtosCarrinho;
    }

    public String compraToString() {
        String response = "";
        BigDecimal valorTotalProduto;
        BigDecimal temp;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        for(CarrinhoItem item : produtosCarrinho){
            temp = new BigDecimal(item.getQuantidade());
            valorTotalProduto = item.getProduto().getPreco().multiply(temp);
            response += item.getProduto().getNome() + " - " + " Quantidade: " + item.getQuantidade() + " - Total: " + valorTotalProduto.toString() + "\n";
                    }

        response += "Valor total dos Produtos: " + df.format(this.valorTotal()) + "\n";
        if(!formaPagamento.toString().equals("boleto")){
            response += "Valor do acréscimo devido a forma de pagamento " + formaPagamento.toString() + " :" +  df.format(this.calculaAcrescimo(this.valorTotal()));
         }

        return response;
    }

    public BigDecimal valorTotal(){
        BigDecimal valorTotal = new BigDecimal(0);
        BigDecimal valorTotalProduto;
        BigDecimal temp;
        for(CarrinhoItem item : produtosCarrinho){
            temp = new BigDecimal(item.getQuantidade());
            valorTotalProduto = item.getProduto().getPreco().multiply(temp);
            valorTotal = valorTotalProduto.add(valorTotal);
        }

        return valorTotal;
    }

    public BigDecimal calculaAcrescimo(BigDecimal valorTotal){
        String formaAux = formaPagamento.toString();
        switch (formaAux){
            case "BOLETO":
                return new BigDecimal(0);

            case "PAYPAL":
                return valorTotal.multiply(new BigDecimal(0.02));

            case "CARTAO":
                return valorTotal.multiply(new BigDecimal(0.05));

            default:
                return new BigDecimal(0);
        }

    }

    public boolean temProduto(CarrinhoItem item) {
        return produtosCarrinho.contains(item);
    }

    public void setFormaPagamento(FormaPagamento formaPagamento){
        this.formaPagamento = formaPagamento;
    }

    public FormaPagamento getFormaPagamento(){
        return this.formaPagamento;
    }

    public StatusCarrinho getStatusCarrinho() {
        return statusCarrinho;
    }

    public void setStatusCarrinho(StatusCarrinho statusCarrinho) {
        this.statusCarrinho = statusCarrinho;
    }
}

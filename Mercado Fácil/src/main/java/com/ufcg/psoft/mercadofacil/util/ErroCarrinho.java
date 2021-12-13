package com.ufcg.psoft.mercadofacil.util;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class ErroCarrinho {

    static final String CARRINHO_VAZIO = "Carrinho vazio, sem produtos";
    static final String CARRINHO_INEXISTENTE = "Carrinho não existe";
    static final String CARRINHO_SEM_ESSE_PRODUTO = "O Produto %s não esta no carrinho";
    static final String FORMA_DE_PAGAMENTO_INEXISTENTE = "A forma de pagamento %s não é aceita no Mercado Fácil";
    static final String FORMA_DE_PAGAMENTO_NAO_DECLARADA = "Não existe nenhuma forma de pagamento no Carrinho";
    static final String CARRINHO_AINDA_NÃO_CRIADO = "Carrinho ainda não criado";
    static final String CARRINHO_JA_APAGADO = "O carrinho já foi apagado";

    public static ResponseEntity<CustomErrorType>erroCarrinhoVazio() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.CARRINHO_VAZIO)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType>erroCarrinhoInexistente() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.CARRINHO_INEXISTENTE)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType>erroProdutoNaoEstaNoCarrinho(Produto produto){
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.CARRINHO_SEM_ESSE_PRODUTO, produto.getNome())), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType>erroFormaDePagamentoInexistente(String formaDePagamento){
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.FORMA_DE_PAGAMENTO_INEXISTENTE, formaDePagamento)), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType>erroFormaDePagamentoNaoDeclarada() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.FORMA_DE_PAGAMENTO_NAO_DECLARADA)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType>erroCarrinhoAindaNaoCriado() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.CARRINHO_AINDA_NÃO_CRIADO)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType>erroCarrinhoJaApagado() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.CARRINHO_JA_APAGADO)),
                HttpStatus.NOT_FOUND);
    }



}

package com.ufcg.psoft.mercadofacil.model;

public class CarrinhoItemDTO {
    private long id;
    private int qtd;
    private Long cpf;

    public Long getCpf() {
        return cpf;
    }

    public long getId() {
        return id;
    }

    public int getQtd() {
        return qtd;
    }
}

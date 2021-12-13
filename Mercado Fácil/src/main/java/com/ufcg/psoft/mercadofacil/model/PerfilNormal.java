package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.interfaces.Perfil;

import java.math.BigDecimal;

public class PerfilNormal implements Perfil{

    @Override
    public BigDecimal calculaDesconto(BigDecimal valor, int qtd) {
        return valor;
    }

    @Override
    public boolean temDesconto(int qtd) {
        return false;
    }
    @Override
    public String toString() {
        return "NORMAL";
    }


}

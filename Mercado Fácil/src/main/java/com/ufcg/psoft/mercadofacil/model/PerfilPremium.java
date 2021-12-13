package com.ufcg.psoft.mercadofacil.model;

import com.ufcg.psoft.mercadofacil.interfaces.Perfil;

import java.math.BigDecimal;

public class PerfilPremium implements Perfil {

    @Override
    public BigDecimal calculaDesconto(BigDecimal valor, int qtd) {
        BigDecimal valorDesconto = new BigDecimal(0);
        if(qtd > 5){
            valorDesconto = valor.multiply(new BigDecimal(0.1));
        }
        return valorDesconto;
    }

    @Override
    public boolean temDesconto(int qtd) {
        if(qtd > 5)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "PREMIUM";
    }
}

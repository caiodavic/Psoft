package com.ufcg.psoft.mercadofacil.interfaces;

import javax.persistence.Entity;
import java.math.BigDecimal;


public interface Perfil {

    public BigDecimal calculaDesconto(BigDecimal valor, int qtd);
    public boolean temDesconto(int qtd);
}

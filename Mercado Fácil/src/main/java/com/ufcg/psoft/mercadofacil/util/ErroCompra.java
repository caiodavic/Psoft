package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCompra {

    static final String COMPRA_NAO_EXISTE = "Não existe nenhuma compra com o ID passado";
    static final String NENHUMA_COMPRA = "Não existe nenhuma compra no sistema";

    public static ResponseEntity<CustomErrorType> erroCompraInexistente() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCompra.COMPRA_NAO_EXISTE)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType> erroNenhumaCompra() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCompra.NENHUMA_COMPRA)),
                HttpStatus.NOT_FOUND);
    }
}

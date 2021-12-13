package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroUsuario {

    static final String CPF_JA_CADASTRADO = "CPF %s já está cadastrado";
    static final String CPF_NAO_CADASTRADO = "CPF %s não está cadastrado no sistema";
    static final String NENHUM_CPF_CADASTRADO = "Nenhum CPF está cadastrado no sistema";
    static final String PERFIL_NAO_VALIDO = "O perfil %s inserido não existe";


    public static ResponseEntity<CustomErrorType> erroCPFJaCadastrado(long id) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroUsuario.CPF_JA_CADASTRADO, id)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType> erroCPFNaoCadastrado(long id) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroUsuario.CPF_NAO_CADASTRADO, id)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType> erroNenhumCPFCadastrado() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroUsuario.NENHUM_CPF_CADASTRADO)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomErrorType> erroPerfilInvalido(String perfilErrado) {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format((ErroUsuario.PERFIL_NAO_VALIDO),perfilErrado)),
                HttpStatus.NOT_FOUND);
    }
}

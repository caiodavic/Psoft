package com.ufcg.psoft.mercadofacil.util;

import com.ufcg.psoft.mercadofacil.model.FormaPagamento;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Util {
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static String formataFormaDePagamento(FormaPagamento formaPagamento){
        String response = "";
        if(formaPagamento.toString().equals("PAYPAL")){
            formaPagamento = FormaPagamento.PAYPAL;
            response += "PayPal";
        } else if(formaPagamento.toString().equals("BOLETO")){
            formaPagamento = FormaPagamento.BOLETO;
            response += "Boleto";
        } else if(formaPagamento.toString().equals("CARTAO")){
            formaPagamento = FormaPagamento.CARTAO;
            response += "Cartão de Crédito";
        }

        return response;
    }


}

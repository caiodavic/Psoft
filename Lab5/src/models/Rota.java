package models;

import models.interfaces.Transporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Rota {
    private Transporte escolheTransporte(String transporte){
        Transporte tipoTransporte;

        switch (transporte){
            case "carro":
                return tipoTransporte = new TransporteCarro();

            case "metro":
                return tipoTransporte = new TransporteMetro();

            case "onibus":
                return tipoTransporte = new TransporteOnibus();

            case "pedestre":
                return tipoTransporte = new TransportePedestre();

            default:
                return tipoTransporte = new TransporteOnibus();
        }
    }

    public String calculaRota(String tipoTransporteString){
        List<String> transportes = new ArrayList<String>(){{add("onibus");add("metro");add("carro");add("pedestre");}};

        tipoTransporteString = tipoTransporteString.trim().toLowerCase(Locale.ROOT);

        if(!transportes.contains(tipoTransporteString)){
            return "Transporte inexistente";
        }
        Transporte tipoTransporte = escolheTransporte(tipoTransporteString);

        return tipoTransporte.calculaRota();
    }
}

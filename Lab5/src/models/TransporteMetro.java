package models;

import models.interfaces.Transporte;

public class TransporteMetro implements Transporte {
    @Override
    public String calculaRota() {
        return "Calculando a rota de Metrô";
    }
}

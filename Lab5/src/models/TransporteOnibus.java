package models;

import models.interfaces.Transporte;

public class TransporteOnibus implements Transporte {
    @Override
    public String calculaRota() {
        return "Calculando a rota de Ônibus";
    }
}

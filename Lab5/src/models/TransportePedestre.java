package models;

import models.interfaces.Transporte;

public class TransportePedestre implements Transporte {
    @Override
    public String calculaRota() {
        return "Calculando a rota de Pedestre";
    }
}

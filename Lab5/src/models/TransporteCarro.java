package models;

import models.interfaces.Transporte;

public class TransporteCarro implements Transporte {
    @Override
    public String calculaRota() {
        return "Calculando a rota de Carro";
    }
}

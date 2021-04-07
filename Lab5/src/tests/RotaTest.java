package tests;

import models.Rota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotaTest {

    Rota rota = new Rota();

    @Test
    void calculaRotaMetroTest(){

        assertEquals("Calculando a rota de Metrô", rota.calculaRota("metro"));
    }

    @Test
    void calculaRotaCarroTest(){

        assertEquals("Calculando a rota de Carro", rota.calculaRota("carro"));
    }

    @Test
    void calculaRotaOnibusTest(){

        assertEquals("Calculando a rota de Ônibus", rota.calculaRota("onibus"));
    }

    @Test
    void calculaRotaPedestreTest(){

        assertEquals("Calculando a rota de Pedestre", rota.calculaRota("pedestre"));
    }

    @Test
    void transporteInexistenteTest(){

        assertEquals("Transporte inexistente", rota.calculaRota("encerrar"));
    }
}
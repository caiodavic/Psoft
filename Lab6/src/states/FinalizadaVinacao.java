package states;

import models.Pessoa;

public class FinalizadaVinacao implements Situacao {
    private static final String SITUACAO_ATUAL = "Finalizada a Vacinação";

    @Override
    public Situacao proximaSituacao() {
        return new FinalizadaVinacao();
    }

    @Override
    public void setProximaSituacao(Pessoa pessoa) {
    }

    @Override
    public String toString() {
        return this.SITUACAO_ATUAL;
    }
}

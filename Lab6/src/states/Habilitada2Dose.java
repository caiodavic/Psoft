package states;

import models.Pessoa;

public class Habilitada2Dose implements Situacao {
    private static final String SITUACAO_ATUAL = "Habilitado para tomar a 2ª dose";

    @Override
    public Situacao proximaSituacao() {
        return new FinalizadaVinacao();
    }

    @Override
    public void setProximaSituacao(Pessoa pessoa) {
        pessoa.setSituacao(new FinalizadaVinacao());
    }

    @Override
    public String toString() {
        return this.SITUACAO_ATUAL;
    }
}

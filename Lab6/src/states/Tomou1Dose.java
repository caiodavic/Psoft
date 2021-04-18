package states;

import models.Pessoa;

public class Tomou1Dose implements Situacao {
    private static final String SITUACAO_ATUAL = "Tomou a 1ª dose";

    @Override
    public Situacao proximaSituacao() {
        return new Habilitada2Dose();
    }

    @Override
    public void setProximaSituacao(Pessoa pessoa) {
        pessoa.setSituacao(new Habilitada2Dose());
    }

    @Override
    public String toString() {
        return this.SITUACAO_ATUAL;
    }
}

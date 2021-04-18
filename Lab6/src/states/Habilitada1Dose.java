package states;

import models.Pessoa;

public class Habilitada1Dose implements Situacao{
    private static final String SITUACAO_ATUAL = "Habilitado para tomar a 1ª dose";

    public Habilitada1Dose() {
    }

    @Override
    public Situacao proximaSituacao() {
        return new Tomou1Dose();
    }

    @Override
    public void setProximaSituacao(Pessoa pessoa) {
        pessoa.setSituacao(new Tomou1Dose());
    }

    @Override
    public String toString() {
        return this.SITUACAO_ATUAL;
    }
}

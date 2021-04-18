package states;

import models.Pessoa;

public class NaoHabilitado implements  Situacao{
    private static final String SITUACAO_ATUAL = "Não habilitado para tomar a vacina";

    public NaoHabilitado() {
    }

    @Override
    public Situacao proximaSituacao() {
        return new Habilitada1Dose();
    }

    @Override
    public void setProximaSituacao(Pessoa pessoa) {
        pessoa.setSituacao(new Habilitada1Dose());
    }

    @Override
    public String toString() {
        return this.SITUACAO_ATUAL;
    }
}

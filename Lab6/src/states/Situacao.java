package states;

import models.Pessoa;

public interface Situacao {

    public Situacao proximaSituacao();
    public void setProximaSituacao(Pessoa pessoa);
    public String toString();
}

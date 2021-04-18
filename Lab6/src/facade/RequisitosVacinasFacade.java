package facade;

import repository.RequisitosVacinasRepositorio;

public class RequisitosVacinasFacade {
    private RequisitosVacinasRepositorio repositorio;

    public RequisitosVacinasFacade() {
        this.repositorio = new RequisitosVacinasRepositorio();
    }

    public void setIdadeRequisitos(Integer idade){
        repositorio.setIdadeRequisitos(idade);
    }

    public void setComorbidadeRequisitos(String comorbidades){
        repositorio.setComorbidadeRequisitos(comorbidades);
    }

    public void setProfissaoRequisitos(String profissao){
        repositorio.setProfissaoRequisitos(profissao);
    }

    public Integer getIdade(){
        return repositorio.getIdade();
    }

    public String getComorbidadesString(){
        return repositorio.comorbidadesToString();
    }

    public String getProfissoesString(){
        return repositorio.profissoesToString();
    }

    public String requisitosToString(){
        return repositorio.requisitosToString();
    }

    public RequisitosVacinasRepositorio getRepositorio() {
        return repositorio;
    }
}

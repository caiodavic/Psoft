package repository;

import models.RequisitosVacinas;

import java.util.List;

public class RequisitosVacinasRepositorio {
        RequisitosVacinas requisitosVacinas = new RequisitosVacinas();

    public void setIdadeRequisitos(Integer idade){
        requisitosVacinas.setIdade(idade);
    }

    public void setComorbidadeRequisitos(String comorbidades){
        requisitosVacinas.setComorbidade(comorbidades);
    }

    public void setProfissaoRequisitos(String profissao){
        requisitosVacinas.setProfissao(profissao);
    }

    public Integer getIdade(){
        return requisitosVacinas.getIdade();
    }

    public List<String> getComorbidade(){
        return requisitosVacinas.getComorbidade();
    }

    public List<String> getProfissao(){
        return requisitosVacinas.getProfissao();
    }

    public String comorbidadesToString(){
        return requisitosVacinas.comorbidadesToString();
    }

    public String profissoesToString(){
        return requisitosVacinas.profissoesToString();
    }

    public String requisitosToString(){
        return requisitosVacinas.toString();
    }
}

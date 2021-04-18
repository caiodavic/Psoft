package models;

import java.util.ArrayList;
import java.util.List;

public class RequisitosVacinas {
    Integer idade;
    List<String> comorbidade;
    List<String> profissao;

    public RequisitosVacinas() {
        this.idade = 120;
        this.comorbidade = new ArrayList<>();
        this.profissao = new ArrayList<>();
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setComorbidade(String comorbidade){
        if(!this.comorbidade.contains(comorbidade)){
            this.comorbidade.add(comorbidade);
        }
    }

    public void setProfissao(String profissao) {
        if(!this.profissao.contains(profissao)){
            this.profissao.add(profissao);
        }
    }

    public Integer getIdade(){
        return this.idade;
    }

    public List<String> getComorbidade() {
        return comorbidade;
    }

    public List<String> getProfissao() {
        return profissao;
    }

    public String comorbidadesToString(){
        return comorbidade.toString();
    }

    public String profissoesToString(){
        return profissao.toString();
    }

    @Override
    public String toString() {
        return "Requisitos para vacina: \n" +
                "A Pessoa deve conter pelo menos um dos requisitos abaixo \n" +
                "Idade: " + idade +
                "\nComorbidades: " + comorbidadesToString() +
                "\nProfissoes: " + profissoesToString();
    }
}

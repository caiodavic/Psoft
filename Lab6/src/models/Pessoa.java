package models;

import states.NaoHabilitado;
import states.Situacao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Pessoa {
    private String nome;
    private String cpf;
    private String endereco;
    private String numeroCartaoSUS;
    private String email;
    private String telefone;
    private String profissao;
    private Date dataNascimento;
    private List<String> comorbidades;
    private Situacao situacao;
    private boolean primeiraDose;
    private boolean segundaDose;
    private Date dataPrimeiraDose;

    public Pessoa(String nome, Date dataNascimento, String cpf, String endereco, String numeroCartaoSUS, String email, String telefone, String profissao, String[] comorbidades) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numeroCartaoSUS = numeroCartaoSUS;
        this.email = email;
        this.telefone = telefone;
        this.profissao = profissao;
        this.dataNascimento = dataNascimento;
        this.comorbidades = new ArrayList<>();
        this.situacao = new NaoHabilitado();

        for(int i = 0; i < comorbidades.length; i++){
            this.comorbidades.add(comorbidades[i]);
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(String cpf) {
        this.cpf =cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumeroCartaoSUS(String numeroCartaoSUS) {
        this.numeroCartaoSUS = numeroCartaoSUS;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public void addComorbidade(String comorbidade){
        if(!this.comorbidades.contains(comorbidade))
            this.comorbidades.add(comorbidade);
    }

    public void removeComorbidade(String comorbidade){
        if(this.comorbidades.contains(comorbidade))
            this.comorbidades.remove(comorbidade);
    }

    public String getProfissao() {
        return profissao;
    }

    public List<String> getComorbidades() {
        return comorbidades;
    }

    public void setPrimeiraDose() {
        this.primeiraDose = true;
    }

    public void setSegundaDose() {
        this.segundaDose = true;
    }

    public void setDataPrimeiraDose() {
        this.dataPrimeiraDose = new Date();
    }

    public boolean isPrimeiraDose() {
        return primeiraDose;
    }

    public boolean isSegundaDose() {
        return segundaDose;
    }

    public Date getDataPrimeiraDose() {
        return dataPrimeiraDose;
    }

    public String getCpf() {
        return cpf;
    }

    public void setSituacao(Situacao situacao){
        this.situacao =  situacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public Integer idade(){
        Date dataHoje = new Date();
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfm = new SimpleDateFormat("MM");
        SimpleDateFormat sdfd = new SimpleDateFormat("dd");

        int anoPessoa = Integer.parseInt(sdfy.format(this.dataNascimento));
        int anoAtual = Integer.parseInt(sdfy.format(dataHoje));
        int idade = anoAtual - anoPessoa;

        int mesPessoa = Integer.parseInt(sdfm.format(this.dataNascimento));
        int mesAtual = Integer.parseInt(sdfm.format(dataHoje));

        int diaPessoa = Integer.parseInt(sdfd.format(this.dataNascimento));
        int diaAtual = Integer.parseInt(sdfd.format(dataHoje));

        if(mesAtual < mesPessoa)
            idade--;

        if(diaAtual < diaPessoa)
            idade--;

        return idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numeroCartaoSUS='" + numeroCartaoSUS + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", profissao='" + profissao + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", comorbidades=" + comorbidades +
                ", situacao=" + situacao.toString() +
                '}';
    }
}

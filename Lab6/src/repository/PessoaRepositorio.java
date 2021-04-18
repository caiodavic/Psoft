package repository;

import models.Pessoa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PessoaRepositorio {
    private Map<String, Pessoa> pessoaMap;

    public PessoaRepositorio() {
        this.pessoaMap = new HashMap<>();
    }

    public void criaPessoa(Pessoa novaPessoa) throws IllegalAccessException{
        Pessoa pessoaExistente = getPessoaCPF(novaPessoa.getCpf());

        if(pessoaExistente != null){
            throw new IllegalAccessException("CPF já cadastrado no sistema");
        }

        pessoaMap.put(novaPessoa.getCpf(),novaPessoa);
    }

    public Pessoa getPessoaCPF(String cpf){
        return this.pessoaMap.get(cpf);
    }

    public void alteraNome(String nome, Pessoa pessoa){
        pessoa.setNome(nome);
    }

    public void alteraEndereco(String endereco, Pessoa pessoa){
        pessoa.setEndereco(endereco);
    }

    public void alteraEmail(String email, Pessoa pessoa){
        pessoa.setEmail(email);
    }

    public void alteraTelefone(String telefone, Pessoa pessoa){
        pessoa.setTelefone(telefone);
    }

    public void alteraProfissao(String profissao, Pessoa pessoa){
        pessoa.setProfissao(profissao);
    }

    public void adicionaComorbidade(String comorbidade, Pessoa pessoa){
        pessoa.addComorbidade(comorbidade);
    }

    public void removeComorbidade(String comorbidade, Pessoa pessoa){
        pessoa.removeComorbidade(comorbidade);
    }



    public void atualizaPessoa(Pessoa pessoaNova) throws IllegalAccessException{
        Pessoa pessoaExistente = getPessoaCPF(pessoaNova.getCpf());

        if(pessoaExistente == null){
            throw new IllegalAccessException("CPF não cadastrado no sistema");
        }

        pessoaMap.replace(pessoaNova.getCpf(),pessoaNova);
    }

    public Collection<Pessoa> getPessoaMap(){
        return pessoaMap.values();
    }
}

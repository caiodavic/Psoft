package service;

import dto.PessoaDTO;
import models.Pessoa;
import repository.PessoaRepositorio;
import states.Situacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class PessoaService {
    private PessoaRepositorio repositorio;

    public PessoaService() {
        this.repositorio = new PessoaRepositorio();
    }

    public void cadastraPessoa(PessoaDTO pessoa) throws IllegalAccessException{


        Pessoa novaPessoa = new Pessoa(pessoa.getNome(),stringParaData(pessoa.getDataNascimento()),pessoa.getCpf(),pessoa.getEndereco(),pessoa.getNumeroCartaoSUS(),
                pessoa.getEmail(),pessoa.getTelefone(),pessoa.getProfissao(),pessoa.getComorbidades());

        repositorio.criaPessoa(novaPessoa);
    }

    private Date stringParaData(String data){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
        try{
            return dataNascimento = (Date) formato.parse(data);
        } catch (ParseException e){
            return new Date(0);
        }

    }

    public Pessoa getPessoa(String cpf) throws  IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        return pessoa;
    }

    public void verificaPessoa(Pessoa pessoa) throws IllegalAccessException{
        if(pessoa == null){
            throw new IllegalAccessException("CPF não cadastrado no sistema");
        }
    }

    public void alteraNome(String nome, String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        repositorio.alteraNome(nome,pessoa);
    }

    public void alteraEndereco(String endereco, String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        repositorio.alteraEndereco(endereco,pessoa);
    }

    public void alteraEmail(String email, String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        repositorio.alteraEmail(email,pessoa);
    }

    public void alteraTelefone(String telefone, String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        repositorio.alteraTelefone(telefone,pessoa);
    }

    public void alteraProfissao(String profissao, String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        repositorio.alteraProfissao(profissao,pessoa);
    }

    public void adicionaComorbidade(String nome, String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        repositorio.adicionaComorbidade(nome,pessoa);
    }

    public void removeComorbidade(String nome, String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        repositorio.removeComorbidade(nome,pessoa);
    }

    public Integer getIdade(String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        return pessoa.idade();
    }

    public Situacao getSituacao(String cpf) throws IllegalAccessException{
        Pessoa pessoa = repositorio.getPessoaCPF(cpf);
        verificaPessoa(pessoa);
        return pessoa.getSituacao();
    }

    public Collection<Pessoa> getPessoas(){
        return repositorio.getPessoaMap();
    }

}

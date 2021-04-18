package facade;

import dto.PessoaDTO;
import models.Pessoa;
import service.PessoaService;

import java.util.ArrayList;
import java.util.List;

public class PessoaFacade {
    private PessoaService servicePessoa;

    public PessoaFacade() {
        this.servicePessoa = new PessoaService();
    }

    public String cadastraPessoa(String nome, String dataNascimento, String cpf, String endereco, String numeroCartaoSUS, String email, String telefone, String profissao, String comorbidades){
        String[] comorbidadesArray = comorbidades.split(",");

        PessoaDTO novaPessoaDTO = new PessoaDTO(nome,dataNascimento,cpf,endereco,numeroCartaoSUS,email,telefone,profissao,comorbidadesArray);

        try{
            servicePessoa.cadastraPessoa(novaPessoaDTO);
            return String.format("CPF %s cadastrado corretamente", novaPessoaDTO.getCpf());
        } catch (IllegalAccessException e){
            return "CPF já cadastrado no sistema";
        }
    }

    public String getPessoa(String cpf){
        try{
            return servicePessoa.getPessoa(cpf).toString();
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String setNome(String nome, String cpf){
        try{
            servicePessoa.alteraNome(nome, cpf);
            return "Nome alterado com sucesso";
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String setEndereco(String endereco, String cpf){
        try{
            servicePessoa.alteraEndereco(endereco, cpf);
            return "Endereco alterado com sucesso";
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String setEmail(String email, String cpf){
        try{
            servicePessoa.alteraEmail(email, cpf);
            return "Email alterado com sucesso";
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String setTelefone(String telefone, String cpf){
        try{
            servicePessoa.alteraTelefone(telefone, cpf);
            return "Telefone alterado com sucesso";
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String setProfissao(String profissao, String cpf){
        try{
            servicePessoa.alteraProfissao(profissao, cpf);
            return "Profissao alterado com sucesso";
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String addComorbidade(String comorbidade, String cpf){
        try{
            servicePessoa.adicionaComorbidade(comorbidade, cpf);
            return "Comorbidade adicionada com sucesso";
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String removeComorbidade(String comorbidade, String cpf){
        try{
            servicePessoa.removeComorbidade(comorbidade, cpf);
            return "Comorbidade removida com sucesso";
        } catch(IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public String getSituacaoString(String cpf){
        try{
            return servicePessoa.getSituacao(cpf).toString();
        } catch (IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }
    }

    public PessoaService getServicePessoa() {
        return servicePessoa;
    }
}

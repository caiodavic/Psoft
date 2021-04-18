package controller;

import models.Pessoa;
import repository.RequisitosVacinasRepositorio;
import service.PessoaService;
import states.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerSistema {
    private PessoaService pessoaService;
    private RequisitosVacinasRepositorio requisitosRepositorio;

    public ControllerSistema(PessoaService pessoaService, RequisitosVacinasRepositorio requisitosRepositorio) {
        this.pessoaService = pessoaService;
        this.requisitosRepositorio = requisitosRepositorio;
    }

    public void atualizarSituacao(){
        for(Pessoa pessoa : pessoaService.getPessoas()){
            if(pessoa.getSituacao() instanceof NaoHabilitado){
                if(this.verificarPrimeiraSituacao(pessoa))
                    avancaSituacao(pessoa, pessoa.getSituacao());
            } else if(pessoa.getSituacao() instanceof Tomou1Dose){
                if(this.verificaSegundaSituacao(pessoa))
                    avancaSituacao(pessoa, pessoa.getSituacao());
            }

        }
    }

    private boolean verificarPrimeiraSituacao(Pessoa pessoa){
        if(pessoa.idade() > requisitosRepositorio.getIdade()){
           return true;
        } else if(requisitosRepositorio.getProfissao().contains(pessoa.getProfissao())){
            return true;
        } else {
            for(String comorbidade : pessoa.getComorbidades()){
                if(requisitosRepositorio.getComorbidade().contains(comorbidade))
                    return true;
            }
        }
        return false;
    }

    private boolean verificaSegundaSituacao(Pessoa pessoa){
        Date dataPrimeiraDose = pessoa.getDataPrimeiraDose();
        Date dataHoje = new Date();

        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfm = new SimpleDateFormat("MM");
        SimpleDateFormat sdfd = new SimpleDateFormat("dd");

        int mesPessoa = Integer.parseInt(sdfm.format(dataPrimeiraDose));
        int mesAtual = Integer.parseInt(sdfm.format(dataHoje));

        int diaPessoa = Integer.parseInt(sdfd.format(dataPrimeiraDose));
        int diaAtual = Integer.parseInt(sdfd.format(dataHoje));

        int diferencaDias;
        if(mesPessoa == mesAtual){
            diferencaDias = diaAtual - diaPessoa;
            if(diferencaDias > 20)
                return true;
        } else if(mesAtual > mesPessoa){
            diferencaDias = diaPessoa - diaAtual;
            if(diferencaDias <= 10)
                return true;
        }

        return false;
    }

    public String aplicaPrimeiraDose(String cpf){
        try{
            Pessoa futuraVacinada = pessoaService.getPessoa(cpf);
            if(!this.verifica1DoseHabilitada(futuraVacinada))
                return "Usuario nao habilitado para tomar a primeira dose";
            futuraVacinada.setPrimeiraDose();
            futuraVacinada.setDataPrimeiraDose();
            avancaSituacao(futuraVacinada,futuraVacinada.getSituacao());
            return "Usuario " + cpf + " vacinado com a primeira dose";
        } catch (IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }

    }

    public String aplicarSegundaDose(String cpf){
        try{
            Pessoa futuraVacinada = pessoaService.getPessoa(cpf);
            if(!this.verifica2DoseHabilitada(futuraVacinada))
                return "Usuario nao habilitado para tomar a segunda dose";
            futuraVacinada.setSegundaDose();
            avancaSituacao(futuraVacinada,futuraVacinada.getSituacao());
            return "Usuario " + cpf + " vacinado com a segunda dose";
        } catch (IllegalAccessException e){
            return "CPF não cadastrado no sistema";
        }

    }


    private void avancaSituacao(Pessoa pessoa, Situacao situacao){
        situacao.setProximaSituacao(pessoa);
    }

    private boolean verifica1DoseHabilitada(Pessoa pessoa){
        return pessoa.getSituacao() instanceof Habilitada1Dose;
    }

    private boolean verifica2DoseHabilitada(Pessoa pessoa){
        return pessoa.getSituacao() instanceof Habilitada2Dose;
    }

}

package facade;

import controller.ControllerSistema;
import repository.RequisitosVacinasRepositorio;
import service.PessoaService;

public class SistemaFacade {
    private ControllerSistema controller;

    public SistemaFacade(PessoaService pessoaService, RequisitosVacinasRepositorio requisitosRepositorio) {
        this.controller = new ControllerSistema(pessoaService, requisitosRepositorio);
    }

    public String aplicarPrimeiraDose(String cpf){
        return controller.aplicaPrimeiraDose(cpf);
    }

    public String aplicarSegundaDose(String cpf){
        return controller.aplicarSegundaDose(cpf);
    }

    public void atualizarSituacoes(){
        controller.atualizarSituacao();
    }
}

package main;

import controller.ControllerSistema;
import facade.PessoaFacade;
import facade.RequisitosVacinasFacade;
import facade.SistemaFacade;

import java.util.Scanner;

public class Main {

    private static PessoaFacade facade = new PessoaFacade();
    private static RequisitosVacinasFacade facadeRequisitos = new RequisitosVacinasFacade();
    private static SistemaFacade facadeController = new SistemaFacade(facade.getServicePessoa(),facadeRequisitos.getRepositorio());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            menu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0)
                break;

            if(opcao == 1){
                menuCadastraPessoa(scanner);
                facadeController.atualizarSituacoes();
            }

            if(opcao == 2){
                menuBuscaPessoa(scanner);
            }

            if(opcao == 3){
                menuAlteracao(scanner);
                facadeController.atualizarSituacoes();
            }

            if(opcao == 4){
                facadeController.atualizarSituacoes();
                menuSituacaoPessoa(scanner);
            }

            if(opcao == 5){
                facadeController.atualizarSituacoes();
                menuVacina(scanner);
            }

            if(opcao == 6){
                menuRequisitosInserir(scanner);
                facadeController.atualizarSituacoes();
            }

            if(opcao == 7){
                menuRequisitosConsultar(scanner);
            }
        }


    }

    private static void menu(){
        System.out.println("Menu: \n" + "1 - Cadastrar Pessoa \n" + "2 - Buscar Pessoa \n" +  "3 - Alterar dados da Pessoa \n" +
                "4 - Consulta situação da Pessoa \n" + "5 - Vacinar uma Pessoa \n" + "6 - Inserir novo requisito \n" + "7 - Consultar requisitos\n" + "0 - Encerrar Programa");
    }

    private static void menuCadastraPessoa(Scanner scanner){
        System.out.println("Insira o nome completo: ");
        String nome = scanner.nextLine();

        System.out.println("Insira a data de nascimento no formato dd/mm/aaaa: ");
        String dataNascimento = scanner.nextLine();

        System.out.println("Insira o cpf: ");
        String cpf = scanner.nextLine();

        System.out.println("Insira o endereço: ");
        String endereco = scanner.nextLine();

        System.out.println("Insira o número do cartão do SUS: ");
        String sus = scanner.nextLine();

        System.out.println("Insira o email: ");
        String email = scanner.nextLine();

        System.out.println("Insira o telefone: ");
        String telefone = scanner.nextLine();

        System.out.println("Insira a profissão: ");
        String profissao = scanner.nextLine();

        System.out.println("Insira as cormobidades: ");
        System.out.println("Obs: (Você deve inserir as comorbidades separadas por vírgulas) ");
        String cormobidades = scanner.nextLine();

        String retorno = facade.cadastraPessoa(nome,dataNascimento,cpf,endereco,sus,email,telefone,profissao,cormobidades);

        System.out.println(retorno);
    }

    private static void menuBuscaPessoa(Scanner scanner){
        System.out.println("Insira o cpf do cadastrado que você deseja buscar: ");
        String cpf = scanner.nextLine();

        String retorno = facade.getPessoa(cpf);
        System.out.println(retorno);
    }

    private static void menuAlteracao(Scanner scanner){
        System.out.println("Insira o CPF do usuário\n");
        String cpf = scanner.nextLine();

        System.out.println("1 - Alterar nome\n" + "2 - Alterar endereço\n" + "3 - Alterar e-mail\n" + "4 - Alterar telefone\n" + "5 - Alterar profissão\n" +
                "6 - Adicionar Comorbidade\n" + "7 - Remover Comorbidade\n");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insira o novo dado do usuário\n");
        String novo = scanner.nextLine();

        String retorno;
        switch(opcao){
            case 1:
                retorno = facade.setNome(novo, cpf);
                break;

            case 2:
                retorno = facade.setEndereco(novo,cpf);
                break;

            case 3:
                retorno = facade.setEmail(novo, cpf);
                break;

            case 4:
                retorno = facade.setTelefone(novo,cpf);
                break;

            case 5:
                retorno = facade.setProfissao(novo, cpf);
                break;

            case 6:
                retorno = facade.addComorbidade(novo,cpf);
                break;

            case 7:
                retorno = facade.removeComorbidade(novo,cpf);
                break;

            default: retorno = "Opção inválida";
        }

        System.out.println(retorno);
    }

    private static void menuSituacaoPessoa(Scanner scanner){
        System.out.println("Informe o cpf para busca de situação de uma Pessoa");
        String cpf = scanner.nextLine();

        String retorno =  facade.getSituacaoString(cpf);

        System.out.println(retorno);
    }

    private static void menuVacina(Scanner scanner){
        System.out.println("1 - Aplicar primeira dose \n" + "2 - Aplicar segunda dose \n");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Informe o CPF da Pessoa");
        String cpf = scanner.nextLine();
        String retorno;
        switch(opcao){
            case 1:
                retorno = facadeController.aplicarPrimeiraDose(cpf);
                break;

            case 2:
                retorno = facadeController.aplicarSegundaDose(cpf);
                break;
            default: retorno = "Opção inválida";
        }

        System.out.println(retorno);
    }

    private static void menuRequisitosInserir(Scanner scanner){
        System.out.println("1 - Inserir nova idade\n" +
                "2 - Inserir nova comorbidade\n" +
                "3 - Inserir nova profissão\n");
        int opcao = scanner.nextInt();
        scanner.nextLine();


        String retorno;
        switch(opcao){
            case 1:
                System.out.println("Inserir nova idade");
                int idade = scanner.nextInt();
                facadeRequisitos.setIdadeRequisitos(idade);
                retorno = "A partir de agora, pessoas com " + idade + " anos ou mais poderão ser vacinadas";
                break;

            case 2:
                System.out.println("Inserir nova comorbidade");
                String comorbidade = scanner.nextLine();
                facadeRequisitos.setComorbidadeRequisitos(comorbidade);
                retorno = "A partir de agora pessoas com " + comorbidade + " poderão ser vacinadas";
                break;

            case 3:
                System.out.println("Inserir nova profissão");
                String profissao = scanner.nextLine();
                facadeRequisitos.setProfissaoRequisitos(profissao);
                retorno = "A partir de agora pessoas que trabalham como " + profissao + " poderão ser vacinadas";
                break;

            default: retorno = "Opção inválida";
        }

        System.out.println(retorno);
    }

    private static void menuRequisitosConsultar(Scanner scanner) {
        System.out.println("1 - Consultar idade\n" +
                "2 - Consultar comorbidades\n" +
                "3 - Consultar profissões\n" +
                "4 - Consultar todos os requisitos\n");

        int opcao = scanner.nextInt();

        String retorno;
        switch(opcao){
            case 1:
                retorno = "A idade mínima para ser vacinado é: " + facadeRequisitos.getIdade();
                break;

            case 2:
                retorno = "As comorbidades que permitem as Pessoas serem vacinadas são: " + facadeRequisitos.getComorbidadesString();
                break;

            case 3:
                retorno = "As profissões que permitem as Pessoas serem vacinadas são: " + facadeRequisitos.getProfissoesString();
                break;

            case 4:
                retorno = facadeRequisitos.requisitosToString();
                break;

            default: retorno = "Opção inválida";
        }

        System.out.println(retorno);

    }
}

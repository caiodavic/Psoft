import models.Rota;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Rota rota = new Rota();

        System.out.println("Cálculo de Rota Strategy");
        System.out.println("1. Insira o transporte para cálculo da rota");
        System.out.println("    Carro" +
                "   Ônibus" +
                "   Metrô" +
                "   Pedestre");
        System.out.println("2. Insira 'encerrar' para encerrar o aplicativo");

        Scanner input = new Scanner(System.in);

        String opcao = input.next();

        while(!opcao.equals("encerrar")){

            System.out.println(rota.calculaRota(opcao));
            System.out.println("\n");
            System.out.println("Pressione enter para continuar");
            input.nextLine();
            input.nextLine();
            System.out.println("1. Insira o transporte para cálculo da rota");
            System.out.println("    Carro" +
                    "   Ônibus" +
                    "   Metrô" +
                    "   Pedestre");
            System.out.println("2. Insira 'encerrar' para encerrar o aplicativo");


            opcao = input.next();
        }

        System.out.println("Cálculo de Rota Strategy Encerrado, Obrigado.");

    }
}

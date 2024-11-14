package energia;

import java.util.Scanner;
public class SimuladorGastoEnergia {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a Tarifa por Kwh: ");
        double tarifaKwh = scanner.nextDouble();

        Casa casa = new Residencial(tarifaKwh);

        System.out.println("Quantos aparelhos você deseja adicionar? ");
        int quantidadeAparelhos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < quantidadeAparelhos; i++) {
            System.out.println("\nAparelho " + (i + 1) + ":");
            System.out.println("Nome do aparelho:");
            String nome = scanner.nextLine();

            System.out.println("Potência em watts");
            double potencia = scanner.nextDouble();

            System.out.println("Tempo médio do uso diário em horas: ");
            double horasPorDia = scanner.nextDouble();
            scanner.nextLine();

            Eletrodomestico eletrodomestico = new Eletrodomestico(nome, potencia, horasPorDia);
            casa.adicionarEletrodomestico(eletrodomestico);
        }
        System.out.printf("\nConsumo total mensal: %.2f Kwh%n", casa.calcularConsumoTotal());
        System.out.printf("Custo total estimado: R$ %.2f%n", casa.calcularCustoTotal());

        scanner.close();
    }
}

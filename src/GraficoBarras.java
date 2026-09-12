import java.util.Scanner;

public class GraficoBarras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[5];

        // Leitura dos cinco números
        for (int i = 0; i < 5; i++) {
            System.out.print("Digite o número " + (i + 1) + " (entre 1 e 30): ");
            numeros[i] = scanner.nextInt();
        }

        System.out.println("\n--- Gráfico de Barras ---");

        // Exibição das barras de asteriscos
        for (int i = 0; i < 5; i++) {
            StringBuilder barra = new StringBuilder();
            for (int j = 0; j < numeros[i]; j++) {
                barra.append("*");
            }
            System.out.println(barra.toString());
        }

        scanner.close();
    }
}
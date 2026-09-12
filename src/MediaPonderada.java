import java.util.Scanner;
import java.util.Locale;

public class MediaPonderada {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // garante que "." seja o separador decimal na leitura

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a primeira nota: ");
        double nota1 = scanner.nextDouble();

        System.out.print("Digite a segunda nota: ");
        double nota2 = scanner.nextDouble();

        System.out.print("Digite a terceira nota (peso 2): ");
        double nota3 = scanner.nextDouble();

        // Média ponderada: peso 2 para nota3
        double media = (nota1 * 1 + nota2 * 1 + nota3 * 2) / 4;

        System.out.printf("%n--- Resultado ---%n");
        System.out.println("Aluno: " + nome);
        System.out.printf("Média ponderada: %.2f%n", media);

        if (media >= 7) {
            System.out.println("Situação: Aprovado");
        } else {
            System.out.println("Situação: Reprovado");
        }

        scanner.close();
    }
}
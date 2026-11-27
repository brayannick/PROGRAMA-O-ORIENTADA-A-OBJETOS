import java.util.InputMismatchException;
import java.util.Scanner;

public class Questao4Lista7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] numeros = new double[5];

        for (int i = 0; i < numeros.length; i++) {
            while (true) {
                try {
                    System.out.print("Digite o número " + (i + 1) + ": ");
                    numeros[i] = sc.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Valor inválido! Digite um número.");
                    sc.next(); // descarta a entrada inválida
                }
            }
        }

        System.out.print("Qual índice deseja acessar (0 a 4)? ");
        try {
            int indice = sc.nextInt();
            System.out.println("Valor no índice " + indice + ": " + numeros[indice]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Índice inválido! Use um valor entre 0 e " + (numeros.length - 1) + ".");
        } catch (InputMismatchException e) {
            System.out.println("Você deve digitar um número inteiro.");
        } finally {
            sc.close();
        }
    }
}

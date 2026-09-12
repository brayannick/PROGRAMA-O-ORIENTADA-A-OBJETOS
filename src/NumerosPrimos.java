import java.util.Scanner;

public class NumerosPrimos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro positivo N: ");
        int n = scanner.nextInt();

        System.out.println("Números primos entre 2 e " + n + ":");

        for (int numero = 2; numero <= n; numero++) {
            if (ehPrimo(numero)) {
                System.out.print(numero + " ");
            }
        }
        System.out.println();

        scanner.close();
    }

    // Verifica se um número é primo
    public static boolean ehPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        // Só precisa verificar divisores até a raiz quadrada do número
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
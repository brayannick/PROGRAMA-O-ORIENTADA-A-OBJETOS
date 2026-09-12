import java.util.Scanner;

public class Multiplos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int numero = scanner.nextInt();

        boolean multiploDe3 = (numero % 3 == 0);
        boolean multiploDe5 = (numero % 5 == 0);

        if (multiploDe3 && multiploDe5) {
            System.out.println("Múltiplo de ambos");
        } else if (multiploDe3) {
            System.out.println("Múltiplo de 3");
        } else if (multiploDe5) {
            System.out.println("Múltiplo de 5");
        } else {
            System.out.println("Não é múltiplo de 3 nem de 5");
        }

        scanner.close();
    }
}
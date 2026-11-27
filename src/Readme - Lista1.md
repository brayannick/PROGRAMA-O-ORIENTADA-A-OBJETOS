Questão 5:

Scanner é uma classe do pacote java.util usada para ler dados digitados pelo usuário (entrada padrão, System.in). Ele permite ler diferentes tipos de dados através de métodos específicos, como:

nextInt() — lê um número inteiro
nextDouble() — lê um número de ponto flutuante (double)
nextLine() — lê uma linha inteira de texto (String)
next() — lê uma única palavra (sem espaços)

Para usá-lo, é preciso importar java.util.Scanner e criar um objeto associado à entrada padrão: Scanner sc = new Scanner(System.in);.

System.out.printf é usado para formatar a saída de dados de forma controlada, usando "especificadores de formato" como %d (inteiro), %s (String), %f (ponto flutuante), etc. É especialmente útil para controlar quantas casas decimais um número deve exibir, usando %.2f, por exemplo.

Exemplo prático (lendo um double e exibindo com 2 casas decimais):

java
import java.util.Scanner;

public class ExemploScannerPrintf {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número decimal: ");
        double numero = sc.nextDouble(); // Scanner lê o valor digitado

        // printf formata a saída com 2 casas decimais
        System.out.printf("O número digitado, formatado, é: %.2f%n", numero);

        sc.close();
    }
}

Se o usuário digitar 7.5, a saída será: O número digitado, formatado, é: 7.50

O %n no final é equivalente a uma quebra de linha (semelhante ao \n), mas mais recomendado porque se adapta ao sistema operacional.


Questão 6:

Erros identificados:
Erro de sintaxe (assinatura do main):
public static void main(String args) está errado. O parâmetro do método main deve ser um array de Strings: String[] args (ou String args[]). Sem os colchetes, o código não compila.
Erro de sintaxe (ponto e vírgula faltando):
A linha System.out.println("Contador: " + contador) não termina com ;. Toda instrução em Java precisa ser finalizada com ponto e vírgula.
Erro de lógica (loop infinito):
A variável contador nunca é incrementada dentro do while. Como a condição contador <= 5 nunca deixa de ser verdadeira (o valor de contador permanece sempre 0), o laço nunca termina — é um loop infinito.
Uso desnecessário do Scanner:
O objeto sc é criado, mas nunca é utilizado para ler nada. Não chega a ser um erro que impede a compilação, mas é um problema de lógica/limpeza de código (o programa declara uma entrada que não usa).
Código corrigido:

public class Contador {

    public static void main(String[] args) {
        int contador = 0;
        while (contador <= 5) {
        System.out.println("Contador: " + contador);
        contador++; // incremento necessário para evitar loop infinito
        }
    }
}

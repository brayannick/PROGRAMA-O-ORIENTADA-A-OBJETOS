import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Questão 5 - Simulação de navegador com pilha de títulos de páginas.
 * Deque<String> (ArrayDeque) usado como pilha: push() empilha, pop() desempilha.
 */
public class Questão5Lista6 {
    private final Deque<String> historico = new ArrayDeque<>();

    public void visitar(String titulo) {
        historico.push(titulo);
        System.out.println("Página atual: " + titulo);
    }

    public void voltar() {
        if (historico.size() <= 1) {
            System.out.println("Não há página anterior.");
            return;
        }
        historico.pop(); // descarta a página atual
        System.out.println("Voltou para: " + historico.peek());
    }

    public void mostrarAtual() {
        System.out.println(historico.isEmpty()
                ? "Nenhuma página aberta."
                : "Página atual: " + historico.peek());
    }

    public static void main(String[] args) {
        Questão5Lista6 nav = new Questão5Lista6();
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("\n1 - Visitar página | 2 - Voltar | 3 - Página atual | 0 - Sair");
            System.out.print("Opção: ");
            try {
                op = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                op = -1;
            }
            switch (op) {
                case 1 -> {
                    System.out.print("Título da página: ");
                    nav.visitar(sc.nextLine());
                }
                case 2 -> nav.voltar();
                case 3 -> nav.mostrarAtual();
                case 0 -> System.out.println("Navegador fechado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
        sc.close();
    }
}

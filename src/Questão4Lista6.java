import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * Questão 4 - Inserção, listagem e remoção de usuários sem e-mails repetidos.
 * Coleção escolhida: LinkedHashSet (unicidade via equals/hashCode do e-mail,
 * operações O(1) e listagem na ordem de cadastro).
 */
public class Questão4Lista6 {

    /**
     * A identidade do usuário é definida SOMENTE pelo e-mail (equals/hashCode),
     * o que permite a um Set impedir e-mails repetidos.
     */
    static class Usuario {
        private final String email;
        private final String nome;
        private final String senha;

        Usuario(String email, String nome, String senha) {
            this.email = email.trim().toLowerCase();
            this.nome = nome;
            this.senha = senha;
        }

        /** Usuário "chave": usado apenas para buscar/remover por e-mail. */
        static Usuario chave(String email) {
            return new Usuario(email, null, null);
        }

        String getEmail() { return email; }
        String getNome()  { return nome; }
        String getSenha() { return senha; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Usuario)) return false;
            return email.equals(((Usuario) o).email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(email);
        }

        @Override
        public String toString() {
            return "Usuario{nome='" + nome + "', email='" + email + "'}"; // senha nunca é exibida
        }
    }

    private final Set<Usuario> usuarios = new LinkedHashSet<>();

    public boolean inserir(String email, String nome, String senha) {
        return usuarios.add(new Usuario(email, nome, senha)); // false se o e-mail já existe
    }

    public boolean remover(String email) {
        return usuarios.remove(Usuario.chave(email));
    }

    public void listar() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        usuarios.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Questão4Lista6 g = new Questão4Lista6();
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("\n1 - Inserir | 2 - Listar | 3 - Remover | 0 - Sair");
            System.out.print("Opção: ");
            try {
                op = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                op = -1;
            }
            switch (op) {
                case 1 -> {
                    System.out.print("E-mail: ");
                    String email = sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.println(g.inserir(email, nome, senha)
                            ? "Usuário cadastrado!"
                            : "Erro: já existe um usuário com esse e-mail.");
                }
                case 2 -> g.listar();
                case 3 -> {
                    System.out.print("E-mail do usuário a remover: ");
                    System.out.println(g.remover(sc.nextLine())
                            ? "Usuário removido!"
                            : "Usuário não encontrado.");
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
        sc.close();
    }
}

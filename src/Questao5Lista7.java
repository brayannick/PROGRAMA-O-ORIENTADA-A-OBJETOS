public class Questao5Lista7 {
    public static void main(String[] args) {
        int[] idades = {25, -3, 200, 150};

        for (int idade : idades) {
            try {
                PessoaQuestão5lista7  p = new PessoaQuestão5lista7 ("Maria", idade);
                System.out.println("Pessoa criada: " + p.getNome() + ", " + p.getIdade() + " anos");
            } catch (IdadeInvalidaExceptionQuestão5lista7 e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}

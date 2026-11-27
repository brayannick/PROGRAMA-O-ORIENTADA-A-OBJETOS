public class PessoaQuestão5lista7 {
    private String nome;
    private int idade;

    public PessoaQuestão5lista7 (String nome, int idade) throws IdadeInvalidaExceptionQuestão5lista7 {
        this.nome = nome;
        setIdade(idade);
    }

    public void setIdade(int idade) throws IdadeInvalidaExceptionQuestão5lista7 {
        if (idade < 0 || idade > 150) {
            throw new IdadeInvalidaExceptionQuestão5lista7(
                "Idade inválida: " + idade + ". Deve estar entre 0 e 150.");
        }
        this.idade = idade;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
}

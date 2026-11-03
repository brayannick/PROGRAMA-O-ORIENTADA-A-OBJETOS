import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/** Questão 6 - Exercícios com Streams. */
public class Questão6Lista6 {

    /** a) Remove os elementos menores que 0 (mantém os >= 0). */
    public static List<Integer> removerNegativos(List<Integer> numeros) {
        return numeros.stream()
                .filter(n -> n >= 0)
                .collect(Collectors.toList());
    }

    /** b) Exibe as Strings que iniciam com a letra P (maiúscula ou minúscula). */
    public static void exibirIniciadasComP(List<String> palavras) {
        palavras.stream()
                .filter(s -> s.toUpperCase().startsWith("P"))
                .forEach(System.out::println);
    }

    /** c) Coloca as palavras de um parágrafo em um Set e devolve as palavras únicas. */
    public static Set<String> palavrasUnicas(String paragrafo) {
        return Arrays.stream(paragrafo.toLowerCase().split("[^\\p{L}]+"))
                .filter(p -> !p.isBlank())
                .collect(Collectors.toCollection(TreeSet::new)); // TreeSet: já sai em ordem alfabética
    }

    public static void main(String[] args) {
        // a)
        List<Integer> numeros = Arrays.asList(5, -3, 8, 0, -1, 12, -7, 4);
        System.out.println("a) Original: " + numeros);
        System.out.println("   Sem negativos: " + removerNegativos(numeros));

        // b)
        List<String> palavras = Arrays.asList("Pato", "casa", "Pedra", "bola", "paralelepípedo", "Java", "Programação");
        System.out.println("\nb) Palavras que iniciam com P:");
        exibirIniciadasComP(palavras);

        // c)
        String paragrafo = "Java é uma linguagem de programação. Programar em Java é divertido, "
                + "e a linguagem Java possui uma API de coleções muito poderosa.";
        System.out.println("\nc) Palavras únicas: " + palavrasUnicas(paragrafo));
    }
}

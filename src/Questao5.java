import java.util.List;

interface Imprimivel {
    void imprimir();
}

interface EnviavelPorEmail {
    void enviar(String email);
}

class RelatorioFinanceiro implements Imprimivel, EnviavelPorEmail {
    @Override
    public void imprimir() {
        System.out.println("Imprimindo Relatório Financeiro.");
    }

    @Override
    public void enviar(String email) {
        System.out.println("Enviando Relatório Financeiro para " + email);
    }
}

class Contrato implements Imprimivel {
    @Override
    public void imprimir() {
        System.out.println("Imprimindo Contrato.");
    }
}

public class Questao5 {
     static void main(String[] args) {
        // Todos os documentos são imprimíveis
        List<Imprimivel> documentos = List.of(
                new RelatorioFinanceiro(), new Contrato(), new RelatorioFinanceiro());

        // ---- Versão 1: usando instanceof ----
        System.out.println("=== Versão 1: instanceof ===");
        for (Imprimivel doc : documentos) {
            doc.imprimir();
            if (doc instanceof EnviavelPorEmail enviavel) {   // pattern matching (Java 16+)
                enviavel.enviar("cliente@exemplo.com");
            }
        }

        // ---- Versão 2: polimorfismo, sem verificações explícitas ----
        // Cada lista é tipada pela capacidade necessária.
        System.out.print("\n=== Versão 2: polimorfismo ===");
        List<Imprimivel> paraImprimir = List.of(
                new RelatorioFinanceiro(), new Contrato());
        List<EnviavelPorEmail> paraEnviar = List.of(
                new RelatorioFinanceiro());

        paraImprimir.forEach(Imprimivel::imprimir);
        paraEnviar.forEach(d -> d.enviar("cliente@exemplo.com"));
    }
}

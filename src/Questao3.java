interface AparelhoTelefonico {
    void ligar();
    void atender();
}

interface NavegadorInternet {
    void exibirPagina();
    void adicionarNovaAba();
}

interface ReprodutorMusical {
    void tocar();
    void pausar();
}

class Smartphone implements AparelhoTelefonico, NavegadorInternet, ReprodutorMusical {

    @Override
    public void ligar() {
        System.out.println("Ligando para o contato...");
    }

    @Override
    public void atender() {
        System.out.println("Chamada atendida.");
    }

    @Override
    public void exibirPagina() {
        System.out.println("Exibindo a página web.");
    }

    @Override
    public void adicionarNovaAba() {
        System.out.println("Nova aba adicionada ao navegador.");
    }

    @Override
    public void tocar() {
        System.out.println("Música tocando.");
    }

    @Override
    public void pausar() {
        System.out.println("Música pausada.");
    }
}

public class Questao3 {
    public static void main(String[] args) {
        Smartphone smartphone = new Smartphone();

        System.out.println("--- Telefone ---");
        smartphone.ligar();
        smartphone.atender();

        System.out.println("--- Navegador ---");
        smartphone.exibirPagina();
        smartphone.adicionarNovaAba();

        System.out.println("--- Reprodutor ---");
        smartphone.tocar();
        smartphone.pausar();
    }
}

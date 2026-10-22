# Lista de Exercícios 05 – POO em Java (Interfaces)

## Questão 1
**A partir de qual versão do Java foi possível implementar métodos com corpo (default ou static) nas interfaces? Qual é a utilidade de métodos default e static dentro de interfaces?**

A partir do **Java 8** (2014). (O Java 9 ainda acrescentou métodos `private` em interfaces.)

**Métodos `default`**
- Permitem **evoluir uma interface sem quebrar as classes que já a implementam**: basta adicionar um método novo com implementação padrão, e as classes antigas continuam compilando.
- Fornecem um comportamento padrão reaproveitável, que a classe pode sobrescrever se quiser.
- Foi assim que o Java 8 adicionou `forEach` em `Iterable`, `sort` em `List`, etc., sem quebrar todo o código existente.

```java
interface Animal {
    String nome();
    default String apresentar() { return "Olá, eu sou " + nome(); }
}
```

**Métodos `static`**
- Pertencem à interface (não ao objeto) e são chamados como `Interface.metodo()`.
- Servem para **métodos utilitários e fábricas** relacionados ao tipo, sem precisar de uma classe auxiliar separada (como `Collections` ou `Objects`).
- Não são herdados pelas classes que implementam a interface.

```java
interface Validador {
    boolean validar(String s);
    static Validador naoVazio() { return s -> s != null && !s.isBlank(); }
}
```

---

## Questão 2
**Explique como as interfaces ajudam na construção de sistemas desacoplados e de fácil manutenção. Dê um exemplo prático onde o uso de interfaces é preferível ao uso direto de classes concretas.**

Uma interface define um **contrato** (o *que* deve ser feito), sem se comprometer com a implementação (o *como*). Quem usa a interface depende apenas desse contrato, e não de uma classe específica. Isso traz:

- **Baixo acoplamento:** o código cliente não conhece a implementação concreta; trocar uma implementação por outra não exige alterar o cliente (princípio da *inversão de dependência* e "programe para uma interface, não para uma implementação").
- **Facilidade de manutenção e extensão:** novas implementações podem ser adicionadas sem modificar o código existente (princípio *aberto/fechado*).
- **Testabilidade:** é possível substituir dependências reais por versões falsas (*mocks*/*stubs*) nos testes.
- **Polimorfismo:** classes sem relação hierárquica podem ser tratadas de forma uniforme e uma classe pode implementar várias interfaces (o que a herança de classes não permite).

**Exemplo prático – meios de pagamento em um e-commerce**

```java
interface Pagamento { void pagar(double valor); }

class CartaoCredito implements Pagamento { public void pagar(double v) { /* ... */ } }
class Pix           implements Pagamento { public void pagar(double v) { /* ... */ } }
class Boleto        implements Pagamento { public void pagar(double v) { /* ... */ } }

class Checkout {
    private final Pagamento pagamento;           // depende da interface
    Checkout(Pagamento pagamento) { this.pagamento = pagamento; }
    void finalizar(double total) { pagamento.pagar(total); }
}
```

Se `Checkout` dependesse diretamente de `CartaoCredito`, adicionar PIX ou boleto exigiria alterar `Checkout` (vários `if`/`else`). Com a interface, basta criar uma nova classe que implemente `Pagamento`; o `Checkout` não muda. O mesmo vale para acesso a banco de dados (`Repositorio` com implementação MySQL, em memória, etc.), envio de notificações (e-mail, SMS) e outros casos.

---

## Questões práticas (código)

| Questão | Arquivo |
|---|---|
| 3 – Smartphone | [`Questao3.java`](Questao3.java) |
| 4 – Interface funcional / lambdas | [`Questao4.java`](Questao4.java) |
| 5 – Imprimivel / EnviavelPorEmail | [`Questao5.java`](Questao5.java) |

Para compilar e executar (exemplo):

```bash
javac Questao3.java && java Questao3
javac Questao4.java && java Questao4
javac Questao5.java && java Questao5
```

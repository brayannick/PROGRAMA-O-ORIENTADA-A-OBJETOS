# Exceções em Java – Respostas

## 1. Hierarquia das exceções em Java

Todas as classes de exceção em Java descendem de `java.lang.Throwable`:

```
Object
 └── Throwable
      ├── Error
      │    ├── OutOfMemoryError
      │    ├── StackOverflowError
      │    └── ...
      └── Exception
           ├── IOException            (checked)
           ├── SQLException           (checked)
           ├── ...
           └── RuntimeException       (unchecked)
                ├── NullPointerException
                ├── ArrayIndexOutOfBoundsException
                ├── IllegalArgumentException
                └── ...
```

| Classe | O que representa | Checked? | Deve ser tratada? |
|---|---|---|---|
| **Throwable** | Superclasse de tudo o que pode ser lançado com `throw` e capturado com `catch`. Guarda a mensagem, a causa e o *stack trace*. | – | – |
| **Error** | Problemas graves da JVM ou do ambiente (falta de memória, estouro de pilha, falha de *linking*). Geralmente irrecuperáveis. | Não (unchecked) | Não é esperado capturar. |
| **Exception** | Condições que uma aplicação razoável pode querer tratar (arquivo inexistente, falha de rede, dado inválido). | Sim, exceto `RuntimeException` e suas filhas | Sim: `try/catch` ou `throws`. |
| **RuntimeException** | Erros de programação ou uso indevido de API (índice inválido, `null`, argumento ilegal). | Não (unchecked) | Opcional; o ideal é evitar a causa com validação. |

**Checked vs. unchecked:**
- *Checked* (`Exception` e filhas, exceto `RuntimeException`): o compilador **obriga** a tratar (`try/catch`) ou declarar (`throws`).
- *Unchecked* (`RuntimeException`, `Error` e filhas): o compilador **não obriga** a tratar.

---

## 2. try-with-resources

O **try-with-resources** (Java 7) declara um ou mais recursos entre parênteses após o `try`. Ao final do bloco, com sucesso ou com exceção, cada recurso é **fechado automaticamente** pelo método `close()`, na ordem inversa da declaração.

```java
try (BufferedReader br = new BufferedReader(new FileReader("dados.txt"));
     BufferedWriter bw = new BufferedWriter(new FileWriter("saida.txt"))) {
    String linha;
    while ((linha = br.readLine()) != null) {
        bw.write(linha);
        bw.newLine();
    }
} catch (IOException e) {
    System.out.println("Erro de E/S: " + e.getMessage());
}
// br e bw já foram fechados aqui
```

**Quando usar:** sempre que trabalhar com recursos que precisam ser liberados: arquivos, *streams*, conexões JDBC (`Connection`, `Statement`, `ResultSet`), *sockets*, `Scanner` etc. Evita o `finally` verboso, previne vazamento de recursos e, se `close()` também lançar exceção, ela é registrada como **suppressed** (`getSuppressed()`), sem mascarar a exceção original.

**Interfaces compatíveis:**
- `java.lang.AutoCloseable` (método `void close() throws Exception`): a interface base exigida.
- `java.io.Closeable` (método `void close() throws IOException`): subinterface de `AutoCloseable`, implementada por `InputStream`, `OutputStream`, `Reader`, `Writer`, etc.

Qualquer classe que implemente uma delas pode ser usada. Desde o Java 9, também é possível usar variáveis já declaradas que sejam *final* ou *effectively final*: `try (recurso) { ... }`.

---

## 3. Multi-catch (Java 7)

O **multi-catch** permite capturar vários tipos de exceção em um único `catch`, separados por `|`, evitando código duplicado:

```java
try {
    // código que pode lançar várias exceções
} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
    System.out.println("Entrada inválida: " + e.getMessage());
}
```

**Principal cuidado:** os tipos listados **não podem ter relação de herança entre si** (um não pode ser subclasse do outro). Por exemplo, `catch (IOException | Exception e)` não compila, pois `IOException` já é subclasse de `Exception`.

Outros pontos:
- O parâmetro `e` é implicitamente `final`, então não pode ser reatribuído.
- O tipo estático de `e` é o ancestral comum, então só se acessam métodos comuns a todos os tipos listados.
- Use apenas quando o tratamento for realmente o mesmo para todas as exceções.

---

## 4, 5 e 6. Exercícios práticos

Os códigos estão nos arquivos `.java` desta pasta:

| Questão | Arquivo(s) | Resumo |
|---|---|---|
| 4 | `Questao4.java` | Lê 5 números em um array e permite consultar um índice; trata `ArrayIndexOutOfBoundsException` (e entrada não numérica). |
| 5 | `IdadeInvalidaException.java`, `Pessoa.java`, `Questao5.java` | Exceção personalizada (*checked*) lançada se a idade for `< 0` ou `> 150`. |
| 6 | `Questao6.java` | Captura uma exceção e relança outra mantendo a original como **causa**; mostra `getCause()`. |

**Como compilar e executar:**

```bash
javac *.java
java Questao4
java Questao5
java Questao6
```

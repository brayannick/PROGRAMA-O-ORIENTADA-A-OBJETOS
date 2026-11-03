# Lista de Exercícios 06 — Java Collections Framework

Respostas teóricas (questões 1 a 3) e instruções para as questões práticas (4 a 6).
Requer **Java 17+** (usa `switch` com `->`).

## Estrutura

```
src/
├── Questão4Lista6.java   # Questão 4 (inclui a classe interna Usuario)
├── Questão5Lista6.java   # Questão 5
└── Questão6Lista6.java   # Questão 6
```

Como executar:

```bash
cd src
javac -encoding UTF-8 -d out *.java
java -cp out Questão4Lista6
java -cp out Questão5Lista6
java -cp out Questão6Lista6
```

---

## Questão 1 — O que é o Java Collections Framework?

O **Java Collections Framework (JCF)** é uma arquitetura unificada, presente no pacote `java.util`, para representar e manipular grupos de objetos. Ele é formado por:

- **Interfaces** (`Collection`, `List`, `Set`, `Queue`, `Deque`, `Map`), que definem os contratos;
- **Implementações** (`ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `ArrayDeque`...), que são as estruturas de dados prontas;
- **Algoritmos** (classes utilitárias `Collections` e `Arrays`), como ordenação, busca, embaralhamento e inversão.

### Benefícios em relação a arrays tradicionais

| Aspecto | Array | Collections |
|---|---|---|
| Tamanho | Fixo, definido na criação | Dinâmico, cresce e diminui automaticamente |
| Operações | Apenas acesso por índice; inserir/remover exige deslocar elementos manualmente | Métodos prontos: `add`, `remove`, `contains`, `sort`, `size`... |
| Estruturas disponíveis | Somente sequência indexada | Listas, conjuntos sem duplicatas, filas, pilhas, mapas chave-valor, ordenados ou não |
| Tipagem | Aceita primitivos e objetos | Usa *generics* (verificação em tempo de compilação), sem *casts* |
| Polimorfismo | Nenhum | Programar para a interface (`List<String> l = new ArrayList<>()`) permite trocar a implementação sem alterar o resto do código |
| Integração | Limitada | Funciona com `for-each`, `Iterator` e Streams |
| Reuso | Cada programador reimplementa | Código testado, otimizado e padronizado |

Limitação: coleções só armazenam objetos (primitivos viram *wrappers*, como `Integer`, via *autoboxing*), o que tem um pequeno custo de memória. Para grandes volumes de primitivos, arrays ainda podem ser mais eficientes.

---

## Questão 2 — Diferenças entre `List`, `Set` e `Map`

| | `List` | `Set` | `Map` |
|---|---|---|---|
| Estende `Collection`? | Sim | Sim | **Não** (é uma hierarquia própria) |
| Armazena | Elementos | Elementos | Pares **chave → valor** |
| Duplicatas | **Permite** | **Não permite** | Chaves únicas; valores podem repetir |
| Ordem | Mantém a ordem de inserção | Depende da implementação | Depende da implementação |
| Acesso por índice | **Sim** (`get(i)`) | Não | Não (acesso por chave: `get(k)`) |
| Implementações | `ArrayList`, `LinkedList` | `HashSet`, `LinkedHashSet`, `TreeSet` | `HashMap`, `LinkedHashMap`, `TreeMap` |

### Quando usar cada uma (exemplos práticos)

- **List**: quando a ordem importa e/ou há repetição. Ex.: carrinho de compras (o mesmo produto pode aparecer duas vezes), playlist de músicas, histórico de transações, ranking em ordem de classificação.
- **Set**: quando a unicidade é a regra. Ex.: e-mails cadastrados, conjunto de tags de um post, CPFs já processados, palavras distintas de um texto.
- **Map**: quando é preciso buscar um valor a partir de um identificador. Ex.: `Map<String, Aluno>` com matrícula como chave, contagem de frequência de palavras (`Map<String, Integer>`), cache, dicionário de configurações.

---

## Questão 3 — `HashSet` × `LinkedHashSet` × `TreeSet`

### Ordem dos elementos

| Implementação | Ordem |
|---|---|
| `HashSet` | **Nenhuma garantida**; depende do hash dos elementos e pode mudar com o crescimento da tabela |
| `LinkedHashSet` | **Ordem de inserção** (mantém uma lista duplamente encadeada entre os elementos) |
| `TreeSet` | **Ordem natural** (`Comparable`) ou definida por um `Comparator`, sempre ordenado |

### Complexidade (busca / inserção / remoção)

| Implementação | Estrutura interna | `contains` | `add` | `remove` |
|---|---|---|---|---|
| `HashSet` | Tabela hash (`HashMap`) | O(1) médio | O(1) médio | O(1) médio |
| `LinkedHashSet` | Tabela hash + lista encadeada | O(1) médio | O(1) médio | O(1) médio |
| `TreeSet` | Árvore rubro-negra (`TreeMap`) | O(log n) | O(log n) | O(log n) |

No pior caso, com muitas colisões de hash, as versões baseadas em hash degradam (O(n) em listas, O(log n) quando a bucket vira árvore a partir do Java 8). O `TreeSet` mantém O(log n) sempre. Em contrapartida, ele oferece operações de navegação como `first()`, `last()`, `floor()`, `ceiling()`, `headSet()` e `subSet()`.

### Uso de memória

- **`HashSet`**: o menor consumo dos três (cada elemento ocupa uma entrada na tabela hash).
- **`LinkedHashSet`**: consome **mais** que o `HashSet`, pois cada entrada guarda também dois ponteiros extras (anterior e próximo) para manter a ordem de inserção.
- **`TreeSet`**: cada nó guarda referências para filho esquerdo, filho direito, pai e a cor do nó; por isso o custo por elemento também é maior que o do `HashSet` (e geralmente na faixa do `LinkedHashSet`). Não há tabela com espaços vazios, mas o overhead por nó é alto.

### Resumo de escolha

- Só importa a unicidade e a velocidade → `HashSet`.
- Unicidade + preservar a ordem de inserção → `LinkedHashSet`.
- Unicidade + elementos sempre ordenados / consultas por faixa → `TreeSet`.

---

## Questão 4 — Cadastro de usuários (`Questão4Lista6.java`)

**Coleção escolhida: `LinkedHashSet<Usuario>`.**

- A classe interna `Usuario` sobrescreve `equals()` e `hashCode()` usando **apenas o e-mail**. Assim, o `Set` rejeita automaticamente um segundo usuário com o mesmo e-mail (`add` retorna `false`).
- Inserção, busca e remoção são O(1) em média.
- Usa-se `LinkedHashSet` (e não `HashSet`) para que a listagem saia na ordem de cadastro.
- A remoção é feita por e-mail, criando um `Usuario.chave(email)`: como a igualdade depende só do e-mail, ele localiza o usuário correto.
- O e-mail é normalizado (`trim` + minúsculas), então `Ana@x.com` e `ana@x.com` contam como o mesmo.
- A senha nunca é exibida na listagem. Em um sistema real, ela seria armazenada como *hash* (por exemplo, BCrypt), não em texto puro.

Alternativa válida: um `Map<String, Usuario>` com o e-mail como chave, útil quando as buscas por e-mail são frequentes.

## Questão 5 — Navegador com pilha (`Questão5Lista6.java`)

Usa `Deque<String>` implementado por `ArrayDeque`, que é a forma recomendada de ter uma pilha em Java (a classe `Stack` é antiga e sincronizada sem necessidade).

- Visitar uma página: `push(titulo)` coloca o título no **topo**.
- Voltar: `pop()` desempilha a página atual, e `peek()` mostra a anterior, que passa a ser o topo.
- Se só houver uma página na pilha, não há para onde voltar.

## Questão 6 — Streams (`Questão6Lista6.java`)

- **a) Remover menores que 0:** `filter(n -> n >= 0)` mantém apenas os elementos válidos e devolve uma nova lista (streams não alteram a coleção original).
- **b) Strings que iniciam com P:** `filter(s -> s.toUpperCase().startsWith("P"))` seguido de `forEach(System.out::println)`. Aceita `P` e `p`; para aceitar só maiúscula, use `s.startsWith("P")`.
- **c) Palavras únicas de um parágrafo:** o texto é convertido para minúsculas e separado por `split("[^\\p{L}]+")` (qualquer sequência que não seja letra, o que cobre acentos e pontuação). As palavras são coletadas com `Collectors.toCollection(TreeSet::new)`, e o `Set` descarta as repetidas, saindo em ordem alfabética. Troque por `LinkedHashSet::new` se preferir a ordem de aparição.

# Respostas dos Subexercícios

#### 1.1: Por que new Conta("Fulano", "0000-0") para de compilar e new ContaCorrente(...) continua funcionando?

R: Porque conta se tornou uma classe abstrata, que não pode ser instanciada como regra.

#### 2.1: O método transferir() tem três linhas e não sabe que a corrente cobra taxa nem que a estrangeira converte moeda. Como as duas regras foram aplicadas então? Qual conceito de POO está agindo aí?

R: As regras foram aplicadas devido a **herança** que cada classe concreta tem sob a classe abstrata Conta. Cada método de depósito aplica sua própria regra de negócio. Além disso, as transações efetuadas são realizadas em reais, logo as regras de conversão de cambio simplificam.

#### 2.2: Por que getSaldoEmReais() devolveu 499.99999999999994 em vez de 500.00? (Dica: o valor voltou de uma divisão por 5,40.)

R: Porque ao realizar a conversão no método de depósito da ContaEstrangeira a divisão retorna uma dizima periódica `92,5925925925...`. Como formatamos para apenas 2 caracteres depois da virgula ao multiplicar ele se aproxima de R$ 500.  Para ser exato teriamos que multiplicar pela dizima, ou aumentar o número de casas depois da vírgula para tender a R$ 500.

#### 2.3: O cabeçalho do extrato diz VALOR (R$), mas os valores da ContaEstrangeira são dólares. Como você consertaria isso sem copiar o toString() inteiro para a subclasse?

R: Analisando o método `toString()` da classe abstrata, a formatação do extrato está hardcoded. 

Solução: Escrever um método público abstrato chamado `simboloMonetario` e substituir a lógica de formatação de extrato do `toString` para:

```java
// Cabeçalho das colunas, alinhado com o mesmo esquema usado em linha().
extrato += String.format(
    "%-28s %16s",
            "DESCRIÇÃO",
            "VALOR (" + this.simboloMonetario() + ")"
    ) + "\n";
```

Poderíamos utilizar um atributo protegido para representar o símbolo monetário, porém atributos não podem ser declarados como `abstract` em Java. Dessa forma, um método abstrato é mais adequado para estabelecer o contrato da classe:

- Toda Conta deve fornecer um símbolo monetário;
- A classe base não precisa assumir que toda Conta utiliza Real brasileiro;
- Cada classe concreta é obrigada a fornecer sua própria implementação de `simboloMonetario()`;

#### 2.4: A transferência de R$ 99.999,00 foi recusada e o saldo ficou intacto. Que atributo garantiu isso, e por que o Main não conseguiu forçar?

R: Foi recusado por saldo insuficiente, o atributo privado `saldo` garante encapsulamento.

#### 3.1: Qual a diferença entre classe abstrata e método abstrato? O exemplo tem os dois agora: aponte cada um. 

R: Uma classe abstrata não pode ser instanciada. Classe abstrata permite métodos abstratos. Métodos abstratos são declarações de operação sem implementação.
Uma classe abstrata pode ter implementações, desde métodos abstratos que são implementados por subclasses concretas até métodos que realmente efetuam operações.
Resumindo:

*Classe abstrata:* abstração sobre um tipo/objeto que não pode ser instanciado diretamente.
*Método abstrato:* abstração sobre um comportamento/operação cuja implementação não foi definida naquela classe.

A diferença então é: classe abstrata restringe a instanciação do tipo, já método abstrato indica que a operação possui apenas um contrato naquela classe e ainda não possui implementação.

#### 3.2: O que acontece se uma subclasse não implementar tipoDeConta()? Teste, leia o erro e copie a mensagem do compilador na resposta.

R: O erro que aparece é esse:

```bash
java: contas.ContaEstrangeira is not abstract and does not override abstract method tipoDeConta() in contas.Conta`
```
Como o método abstrato não foi implementado na subclasse concreta, o compilador não permite que ela seja considerada uma classe concreta válida, pois ela não segue o contrato herdado da classe abstrata.

#### 3.3: Por que tipoDeConta() é melhor que getClass().getSimpleName(), se os dois funcionam? (Pense em: quem controla o texto que aparece para o cliente?)

R: Por uma questão de legibilidade e formatação como resposta final no terminal ou recibo. Se existir um nome de classe longo e estranho como por exemplo `ContaCorrentePessoaFisicaTransacionalDeOperacaoDiaria` ou `ContaCorrentePF`. Seria muito melhor `Conta Corrente - Pessoa Física`.
Além disso, outra justificativa seria separar o nome técnico de implementação do nome de representação de domínio.

Resumo: Implementação técnica != Representação para o usuário

#### 3.4: Conta agora tem um método com corpo (sacar) e um sem corpo (tipoDeConta). Uma interface poderia ter os dois? Por que a Conta não é uma interface?

R: Uma interface poderia ter ambos os métodos, no entanto ela não pode ter atributos de instância e construtores. Observe que no caso de Conta, ela precisa ser abstrata porque usa construtor.

#### 4.1: Olhe o rodapé do extrato acima com atenção. O valor 22,50 está certo, mas o texto está errado. Por quê? Onde está escrito, e por que a ContaInvestimento não conseguiu mudá-lo? (É o mesmo problema do VALOR (R$) na ContaEstrangeira.)

R: Novamente pelo mesmo motivo da questão `2.3`, o método `toString()` imprime no extrato o tipo de imposto de forma hardcoded. A solução seria criar um método abstrato `tipoImposto()` e implementar em cada classe.

#### 4.2: Acao e ContaInvestimento cobram imposto sobre o lucro, e as duas implementam Tributavel. Por que uma herda de Conta e a outra não?

R: Uma ação ela é um dominios diferente de conta uma bancária. Uma ação representa um ativo de uma empresa. Na justificativa técnica, ela não utiliza os mesmos atributos e métodos de Conta, apenas da interface `Tributavel`, uma vez que ela de fato é suscetível a tributos. Portanto, Acao implementa Tributavel, mas não herda de Conta, pois não existe uma relação de especialização entre Acao e Conta.
Se você colocasse `Acao` como subclasse de `Conta` a pergunta que fica é: "Uma ação é uma conta?". Logicamente não né.
Não devemos herdar só porque um parece ser comum ao outro. 

#### 4.3: calcularImposto() veio da interface, foi implementado na Conta e agora foi sobrescrito de novo aqui. Quantas versões desse método existem no projeto? Qual roda quando o objeto está guardado numa variável do tipo Tributavel?

R: Existem 3 versões de métodos.
Temos uma versão na main que mostra um objeto concreto, ou melhor uma lista de objetos concretos do tipo Tributavel:

`ArrayList<Tributavel> listaTributaveis = new ArrayList<>();`

Isso é extremamente interessante, você consegue pegar classes que tem métodos e atributos internos diferentes, mas que seguem a implementação da interface `Tributavel` e executar esse método normalmente.
Esse é um dos usos mais elegantes da orientação objetos, sendo muito utilizado em arquitetura de software e dependencia entre módulos.

Analisando o código:

```
// Adiciona a conta corrente à lista.
listaTributaveis.add(ccNatan);

// Adiciona a poupança à lista.
listaTributaveis.add(cpWesley);

// Adiciona a ação à lista, mesmo sem nenhum parentesco com as contas.
listaTributaveis.add(petrobras);
```

Pode-se observar que os métodos que rodam em cada objeto são diferentes, mas cumprem o mesmo contrato da interface `Tributavel`.
São totalmente diferentes um é de conta corrente do Natan, outro conta poupança do Wesley e finalmente uma ação da petrobras.
Respondendo a pergunta final, o método que roda é `calcularImposto()`, o método que a interface define.


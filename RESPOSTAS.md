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


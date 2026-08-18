# Respostas dos Subexercícios

## 1.1: Por que new Conta("Fulano", "0000-0") para de compilar e new ContaCorrente(...) continua funcionando?

R: Porque conta se tornou uma classe abstrata, que não pode ser instanciada como regra.

## 2.1: O método transferir() tem três linhas e não sabe que a corrente cobra taxa nem que a estrangeira converte moeda. Como as duas regras foram aplicadas então? Qual conceito de POO está agindo aí?

R: As regras foram aplicadas porque o saldo é sempre salvo em dolár, além da **herança** que cada classe concreta tem sob a classe abstrata Conta.

## 2.2: Por que getSaldoEmReais() devolveu 499.99999999999994 em vez de 500.00? (Dica: o valor voltou de uma divisão por 5,40.)

R:

## 2.3: O cabeçalho do extrato diz VALOR (R$), mas os valores da ContaEstrangeira são dólares. Como você consertaria isso sem copiar o toString() inteiro para a subclasse?

R:

## 2.4: A transferência de R$ 99.999,00 foi recusada e o saldo ficou intacto. Que atributo garantiu isso, e por que o Main não conseguiu forçar?

R:

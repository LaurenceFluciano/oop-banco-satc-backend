// Mesmo pacote de Conta (contas), por isso NÃO precisa de "import contas.Conta;".
//
// Declara o pacote; a pasta contas/ precisa existir e ter exatamente este nome.
package contas;

// ============================================================================
//  TODO -- EXERCÍCIO 4 (enunciado completo em EXERCICIOS.md)
// ============================================================================
//
//  Esta classe está VAZIA de propósito: hoje ela só herda de Conta e não
//  acrescenta nada. Uma subclasse assim não justifica existir -- é o mesmo
//  problema que ContaCorrente e ContaPoupanca tinham antes de ganharem
//  regra própria. Seu trabalho é dar uma razão para ela.
//
//  A regra do investimento: o imposto NÃO incide sobre o saldo, e sim sobre o
//  LUCRO. Aplicou R$ 1.000 e o saldo está R$ 1.100? O IR de 22,5% incide sobre
//  os R$ 100 de rendimento. Sem lucro, imposto zero.
//
//  1. TODO: constante  private static final double ALIQUOTA_IR = 0.225; [ COMPLETE ]
//  2. TODO: atributo   private double totalAplicado;   (soma do que o cliente depositou) [ COMPLETE ]
//  3. TODO: sobrescrever depositar(double, String) para somar em totalAplicado [ COMPLETE ]
//  4. TODO: criar aplicarRendimento(double percentual) [ COMPLETE ]
//  5. TODO: sobrescrever calcularImposto() -> (saldo - totalAplicado) * ALIQUOTA_IR,
//           nunca negativo [ COMPLETE ]
//
//  CUIDADO: o rendimento não é dinheiro aplicado pelo cliente, é lucro. Se o
//  aplicarRendimento() somar em totalAplicado, o lucro fica sempre zero e o
//  imposto some. Dica: depositar(...) e super.depositar(...) não são a mesma
//  chamada quando existe sobrescrita.
//
//  Confira com estes números:
//      depositar(1000)          -> saldo 1000.00 | imposto  0.00
//      aplicarRendimento(0.10)  -> saldo 1100.00 | imposto 22.50
//
//  TODO do EXERCÍCIO 3: quando Conta ganhar o método abstrato tipoDeConta(),
//  esta classe para de compilar até implementá-lo. Devolva "Investimento".
//
// ============================================================================

// >>> HERANÇA: ContaInvestimento É uma Conta -- por enquanto, só isso.
public class ContaInvestimento extends Conta {

    // >>> ATRIBUTOS


    // Atributo Imposto IR
    private static final double ALIQUOTA_IR = 0.225;

    // Atributo que soma tudo o que o cliente depositou
    private double totalAplicado;


    // >>> OBJETO + HERANÇA: construtor não se herda; só repassa para a mãe.
    public ContaInvestimento(String titular, String numero) {

        // >>> HERANÇA: "mãe, monta a SUA parte do objeto com estes dois dados".
        super(titular, numero);

    // Fim do construtor.
    }

    // >>> POLIMORFISMO
    @Override
    public void depositar(double valor) {
        super.depositar(valor);
        this.totalAplicado += valor;
    }

    // >>> MÉTODO NOVO: aplica o rendimento SOBRE o saldo.
    // # double percentual -> varia de 0 a 1
    public boolean aplicarRendimento(double percentual) {
        if (percentual < 0 || percentual > 1) { return false; }
        super.depositar(this.getSaldo() * percentual, "Rendimento");
        return true;
    }

    @Override
    public double calcularImposto() {
        return (getSaldo() - totalAplicado) * ALIQUOTA_IR;
    }

    public String tipoDeConta() {
        return "Investimento";
    }

    @Override
    public String simboloMonetario() { return "R$"; }

    @Override
    public String tipoDeImposto() { return "IR"; };

// Fim da classe ContaInvestimento.
}

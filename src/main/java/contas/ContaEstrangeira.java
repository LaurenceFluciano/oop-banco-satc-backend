package contas;


public class ContaEstrangeira extends Conta {
    private static final double COTACAO_DOLAR = 5.40;


    public ContaEstrangeira(String titular, String numero) {
        super(titular, numero);
    }

    @Override
    public void depositar(double valor) {
        depositar(valor / COTACAO_DOLAR, "Depósito");
    }

    public double getSaldoEmReais() {
        return this.getSaldo() * COTACAO_DOLAR;
    }

    public String tipoDeConta() {
        return "Conta em Dólar";
    }

    @Override
    public String simboloMonetario() { return "$"; }

    @Override
    public String tipoDeImposto() { return "IOF"; };
}

package contas;


public class ContaEstrangeira extends Conta {
    private static final double COTACAO_DOLAR = 5.40;


    public ContaEstrangeira(String titular, String numero) {
        super(titular, numero);
    }

    public double getSaldoEmReais() {
        return COTACAO_DOLAR * this.getSaldo();
    }

    public String tipoDeConta() {
        return "Conta em Dólar";
    }
}

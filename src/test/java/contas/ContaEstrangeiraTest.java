package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaEstrangeiraTest {


    @Test
    @DisplayName("Verificar se a conversão do depósito funciona (R$ 540 viram US$ 100);")
    public void verificarConversaoDeDeposito() {
        ContaEstrangeira contaA = new ContaEstrangeira("Conta-A","0-0001");
        contaA.depositar(540);
        assertEquals(100, contaA.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Verificar se a transferência foi bem-sucedida, conferindo os dois saldos;")
    public void verificarTransferenciaBemSucedida() {
        ContaEstrangeira contaA = new ContaEstrangeira("Conta-A","0-0001");
        ContaCorrente contaB = new ContaCorrente("Conta-B", "0-0002");

        contaB.depositar(540);
        contaB.transferir(contaA, 540);

        assertEquals(0, contaB.getSaldo(), 0.001);
        assertEquals(100, contaA.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("A transferência deve ser recusada por falta de saldo, conferindo que nenhum dos dois saldos mudou.")
    public void verificarSeTransferenciaFoiRecusada() {
        ContaEstrangeira contaA = new ContaEstrangeira("Conta-A","0-0001");
        ContaCorrente contaB = new ContaCorrente("Conta-B", "0-0002");

        contaB.depositar(500);
        boolean resultadoTransferencia = contaB.transferir(contaA, 540);

        assertFalse(resultadoTransferencia);
    }

}

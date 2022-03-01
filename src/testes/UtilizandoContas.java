package testes;

import domain.entities.ContaCorrente;
import domain.entities.ContaPoupanca;
import domain.entities.TipoDeTransferencia;

public class UtilizandoContas {

	public static void main(String[] args) {

		ContaCorrente cc1 = new ContaCorrente("1111", "23123CC", "Rebecca Lopes");
		cc1.depositar(1500.00);
		cc1.sacar(150.00);
		cc1.sacar(100.00);
		cc1.depositar(1200.00);
		
		ContaPoupanca cp1 = new ContaPoupanca("1111", "00023P", "Eric Lopes");
		cc1.transferir(1000.0, cp1, TipoDeTransferencia.PIX);
		cc1.transferir(1000.0, cp1, TipoDeTransferencia.DOC);

		System.out.println(cc1.extrato());
		System.out.println("--------------------------");
		System.out.println(cp1.extrato());
	}

}

package domain.entities;

public interface ContaInterface {
	
	public String depositar(Double valor);
	
	public String sacar(Double valor);
	
	public String transferir(Double valor, Conta contaDestino, TipoDeTransferencia tipoDaTransferencia);
	
	public String extrato();
	
}

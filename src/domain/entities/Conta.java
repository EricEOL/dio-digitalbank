package domain.entities;

import java.util.ArrayList;
import java.util.List;

abstract class Conta implements ContaInterface {

	private String agencia;
	private String numero;
	private String titular;
	private List<String> operacoes = new ArrayList<String>();
	private Double saldo = 0d;
	
	public Conta(String agencia, String numero, String titular) {
		this.agencia = agencia;
		this.numero = numero;
		this.titular = titular;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	public List<String> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<String> operacoes) {
		this.operacoes = operacoes;
	}

	@Override
	public String depositar(Double valor) {
		if(valor <= 0) return "Opção de valor inválida";

		this.saldo += valor;

		String respostaPositiva = "Valor de " + valor + " foi depositado em sua conta";
		this.operacoes.add(respostaPositiva);
		
		return respostaPositiva;
	}

	@Override
	public String sacar(Double valor) {
		if(valor > saldo || valor <= 0) return "Opção de valor inválida";
		
		this.saldo -= valor;
		
		String respostaPositiva = "Valor de " + valor + " foi sacado de sua conta";
		this.operacoes.add(respostaPositiva);
		
		return respostaPositiva;
	}

	@Override
	public String transferir(Double valor, Conta contaDestino, TipoDeTransferencia tipoDaTransferencia) {
		if(valor > this.saldo) return "Sua conta não possui saldo suficiente";
		
		if(tipoDaTransferencia == TipoDeTransferencia.DOC) {
			Double taxa = valor * 0.03;
			
			if(valor + taxa > this.saldo) {
				return "Sua conta não possui saldo suficiente";
			} else {
				this.saldo -= valor + taxa;
				contaDestino.depositar(valor);
				
				String respostaPositiva = "Valor de " + valor + " foi transferido por meio de " + tipoDaTransferencia + " para a conta do titular: " + contaDestino.getTitular();
				this.operacoes.add(respostaPositiva);
				
				return respostaPositiva;
			}
		} else if(tipoDaTransferencia == TipoDeTransferencia.TED) {
			Double taxa = valor * 0.01;
			
			if(valor + taxa > this.saldo) {
				return "Sua conta não possui saldo suficiente";
			} else {
				this.saldo -= valor + taxa;
				contaDestino.depositar(valor);
				
				String respostaPositiva = "Valor de " + valor + " foi transferido por meio de " + tipoDaTransferencia + " para a conta do titular: " + contaDestino.getTitular();
				this.operacoes.add(respostaPositiva);
				
				return respostaPositiva;
			}
		}
		
		this.saldo -= valor;
		contaDestino.depositar(valor);
		
		String respostaPositiva = "Valor de " + valor + " foi transferido por meio de " + tipoDaTransferencia + " para a conta do titular: " + contaDestino.getTitular();
		this.operacoes.add(respostaPositiva);
		
		return respostaPositiva;
	}

	@Override
	public String extrato() {
		
		for(String op: this.operacoes) {
			System.out.println(op);
		}
		
		return "Saldo atual: " + this.saldo;
	}

}

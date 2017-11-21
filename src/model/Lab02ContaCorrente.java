package model;

public class Lab02ContaCorrente {
	public int numAgen;
	public int numConta;
	public String nome;
	public double saldo;
	
	public int saque(double pValor) {
		if (this.saldo >= pValor) {
			this.saldo -= pValor;
			return 1;
		} else {
			return 0;
		}
	}
	
	public void deposito(double pValor) {
		this.saldo += pValor;
	}
	
	public void imprimir() {
		System.out.println();
		System.out.println(new StringBuilder().append("Agência		: ").append(this.numAgen).toString());
		System.out.println(new StringBuilder().append("Conta		: ").append(this.numConta).toString());
		System.out.println(new StringBuilder().append("Nome cliente	: ").append(this.nome).toString());
		System.out.println(new StringBuilder().append("Saldo		: R$ ").append(this.saldo).toString());
		System.out.println();
	}
}

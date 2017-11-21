package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Lab03ContaCorrente {
	private int numAgen;
	private int numConta;
	private String nome;
	private double saldo;

	NumberFormat nf;

	public Lab03ContaCorrente() {
	};

	public Lab03ContaCorrente(int agencia, int conta) {
		setNumAgen(agencia);
		setNumConta(conta);
		recuperar();
	}

	public Lab03ContaCorrente(int agencia, int conta, String nome, double saldo) {
		setNumAgen(agencia);
		setNumConta(conta);
		setNome(nome);
		setSaldo(saldo);
	}

	public int saque(double pValor) {
		if (getSaldo() >= pValor) {
			setSaldo(getSaldo() - pValor);
			return 1;
		} else {
			return 0;
		}
	}

	public void deposito(double pValor) {
		setSaldo(getSaldo() + pValor);
	}

	public void imprimir() {
		nf = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
		nf.setMaximumFractionDigits(2);
		System.out.println();
		System.out.println(new StringBuilder().append("Agência		: ").append(getNumAgen()).toString());
		System.out.println(new StringBuilder().append("Conta		: ").append(getNumConta()).toString());
		System.out.println(new StringBuilder().append("Nome cliente	: ").append(getNome()).toString());
		System.out.println(new StringBuilder().append("Saldo		: ").append(nf.format(getSaldo())).toString());
		System.out.println();
	}

	private void recuperar() {
		FileReader tArq1;
		BufferedReader tArq2;
		int tQtde = 4;
		try {
			// Operação I - Abrir o arquivo
			tArq1 = new FileReader(getNumAgen() + "." + getNumConta() + ".dat");
			tArq2 = new BufferedReader(tArq1);
			// Operação III - Ler atributo/valor e colocar na matriz
			String[] tLinha = new String[tQtde];
			for (int i = 0; i < tQtde; i++) {
				tLinha[i] = tArq2.readLine();
			}
			// Operação IV - Fechar o arquivo
			tArq2.close();
			setNumAgen(Integer.parseInt(tLinha[0]));
			setNumConta(Integer.parseInt(tLinha[1]));
			setNome(tLinha[2]);
			setSaldo(Double.parseDouble(tLinha[3]));
		} catch (FileNotFoundException e) {
			System.out.println("\n Conta não existente \n\n");
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
		}

	}

	public boolean gravar() {
		FileWriter tArq1;
		PrintWriter tArq2;

		try {
			// Operação I - Abrir o aquivo
			tArq1 = new FileWriter(getNumAgen() + "." + getNumConta() + ".dat");
			tArq2 = new PrintWriter(tArq1);

			tArq2.println(getNumAgen());
			tArq2.println(getNumConta());
			tArq2.println(getNome());
			tArq2.println(getSaldo());
			// Operação III - Fechar o arquivo
			tArq2.close();

			return true;
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
			return false;
		}

	}

	public boolean removerArquivo() {
		File tArq1;
		tArq1 = new File(numAgen + "." + numConta + ".dat");
		tArq1.delete();
		tArq1 = new File(numAgen + "." + numConta + ".hist");
		tArq1.delete();
		return true;
	}

	public int getNumAgen() {
		return numAgen;
	}

	public void setNumAgen(int numAgen) {
		if ((numAgen > 1) && (numAgen < 9999)) {
			this.numAgen = numAgen;
		} else {
			System.out.println("Número de agência inválido!");
		}
	}

	public int getNumConta() {
		return numConta;
	}

	public void setNumConta(int numConta) {
		if ((numConta > 1) && (numConta < 9999999)) {
			this.numConta = numConta;
		} else {
			System.out.println("Número da conta é inválido!");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		if (saldo < 0) {
			System.out.println("Saldo negativo é inválido!");
		} else {
			this.saldo = saldo;
		}
	}

}

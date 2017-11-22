package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Locale;

public class Lab05ContaCorrenteEspecial extends Lab03ContaCorrente {
	private double limite;

	public Lab05ContaCorrenteEspecial() {
	}

	public Lab05ContaCorrenteEspecial(int agencia, int conta) {
		// super(agencia, conta);
		setNumAgen(agencia);
		setNumConta(conta);
		recuperarContaEspecial();
	}

	public Lab05ContaCorrenteEspecial(int agencia, int conta, String nome, double saldo, double limite) {
		super(agencia, conta, nome, saldo);
		setLimite(limite);
	}

	@Override
	public int saque(double pValor) {
		if (super.getSaldo() == 0) {
			if (getLimite() > 0) {
				if ((getLimite() - pValor < 0)) {
					return 0;
				}
				setLimite(getLimite() - pValor);
				return 1;
			} else {
				return 0;
			}
		} else {
			super.saque(pValor);
			return 1;
		}
	}

	@Override
	public void imprimir() {
		nf = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
		nf.setMaximumFractionDigits(2);
		System.out.println();
		System.out.println(new StringBuilder().append("Agência		: ").append(getNumAgen()).toString());
		System.out.println(new StringBuilder().append("Conta		: ").append(getNumConta()).toString());
		System.out.println(new StringBuilder().append("Nome cliente	: ").append(getNome()).toString());
		System.out.println(new StringBuilder().append("Saldo		: ").append(nf.format(getSaldo())).toString());
		System.out.println(new StringBuilder().append("Limite		: ").append(nf.format(getLimite())).toString());
		System.out.println();
	}

	private void recuperarContaEspecial() {
		FileReader tArq1;
		BufferedReader tArq2;
		int tQtde = 5;
		try {
			// Operação I - Abrir o arquivo
			tArq1 = new FileReader(getNumAgen() + "." + getNumConta() + ".esp");
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
			setLimite(Double.parseDouble(tLinha[4]));
		} catch (FileNotFoundException e) {
			System.out.println("\n Conta especial não existente \n\n");
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
		}

	}

	@Override
	public boolean gravar() {
		FileWriter tArq1;
		PrintWriter tArq2;

		try {
			// Operação I - Abrir o aquivo
			tArq1 = new FileWriter(getNumAgen() + "." + getNumConta() + ".esp");
			tArq2 = new PrintWriter(tArq1);

			tArq2.println(getNumAgen());
			tArq2.println(getNumConta());
			tArq2.println(getNome());
			tArq2.println(getSaldo());
			tArq2.println(getLimite());
			// Operação III - Fechar o arquivo
			tArq2.close();

			return true;
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removerArquivo() {
		super.removerArquivo();
		File tArq1;
		tArq1 = new File(getNumAgen() + "." + getNumConta() + ".esp");
		tArq1.delete();
		return true;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

}

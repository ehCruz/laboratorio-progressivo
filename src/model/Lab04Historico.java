package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

public class Lab04Historico {
	private int numAgen;
	private int numConta;
	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int min;
	private int seg;
	private int codHist;
	private double valor;
	Vector<String> vetOperacoes = new Vector<String>();

	public Lab04Historico() {
	}

	public Lab04Historico(int numAgen, int numConta) {
		this.numAgen = numAgen;
		this.numConta = numConta;
	}

	public boolean gravar(int p_hist, double p_valor) {
		FileWriter tArq1;
		PrintWriter tArq2;
		try {
			// Operação I - Abrir o aquivo
			tArq1 = new FileWriter(numAgen + "." + numConta + ".hist", true);
			tArq2 = new PrintWriter(tArq1);

			Date hoje = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(hoje);
			dia = cal.get(Calendar.DAY_OF_MONTH);
			// O mês em Java inicia com 0
			mes = cal.get(Calendar.MONTH) + 1;
			ano = cal.get(Calendar.YEAR);
			hora = cal.get(Calendar.HOUR_OF_DAY);
			min = cal.get(Calendar.MINUTE);
			seg = cal.get(Calendar.SECOND);

			tArq2.print(numAgen + " ");
			tArq2.print(numConta + " ");
			tArq2.print(dia + " ");
			tArq2.print(mes + " ");
			tArq2.print(ano + " ");
			tArq2.print(hora + " ");
			tArq2.print(min + " ");
			tArq2.print(seg + " ");
			tArq2.print(p_hist + " ");
			tArq2.println(p_valor);
			// Operação III - Fechar o arquivo
			tArq2.close();
			return true;
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
			return false;
		}
	}

	public void imprimir() {
		recuperarHistorico();
		String[][] tLinha = new String[vetOperacoes.size()][10];
		String array;
		String[] dados;
		int check,min,seg;
		double valor;

		NumberFormat formatter;
		StringBuffer sb;

		for (int i = 0; i < vetOperacoes.size(); i++) {
			array = vetOperacoes.elementAt(i);
			dados = array.split(" ");
			sb = new StringBuffer();
			for (int j = 0; j < 10; j++) {
				tLinha[i][j] = dados[j];
				if (j < 2) {
					sb.append(String.valueOf(tLinha[i][j]));
					sb.append(" ");
				} else if (j < 4) {
					sb.append(String.valueOf(tLinha[i][j]));
					sb.append("/");
				} else if (j == 4) {
					sb.append(String.valueOf(tLinha[i][j]));
					sb.append(" - ");
				} else if (j < 7) {
					min = Integer.parseInt(tLinha[i][j]);
					formatter = new DecimalFormat("00");
					sb.append(String.valueOf(formatter.format(min)));
					sb.append(":");
				} else if (j == 7) {
					seg = Integer.parseInt(tLinha[i][j]);
					formatter = new DecimalFormat("00");
					sb.append(String.valueOf(formatter.format(seg)));
					sb.append(" - ");
				} else if (j < 9) {
					check = Integer.parseInt(tLinha[i][j]);
					if (check == 1) {
						sb.append("Saque caixa      ");
					} else {
						sb.append("Deposito dinheiro");
					}
					sb.append(" ");
				} else {
					formatter = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));
					formatter.setMaximumFractionDigits(2);
					valor = Double.parseDouble(tLinha[i][j]);
					sb.append(String.valueOf(formatter.format(valor)));
				}
			}
			System.out.println(sb);
		}

	}

	public void recuperarHistorico() {
		FileReader tArq1;
		BufferedReader tArq2;
		String tLinha = null;
		try {
			// Operação I - Abrir o arquivo
			tArq1 = new FileReader(numAgen + "." + numConta + ".hist");
			tArq2 = new BufferedReader(tArq1);
			// Operação III - Ler atributo/valor e colocar na matriz
			while (true) {
				tLinha = tArq2.readLine();
				if (tLinha == null)
					break;
				// Criar vetOperacoes como um atributo do tipo Vector
				vetOperacoes.add(tLinha);
			}
			// Operação IV - Fechar o arquivo
			tArq2.close();
		} catch (FileNotFoundException e) {
			System.out.println("\n Conta sem movimento \n\n");
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
		}

	}
}

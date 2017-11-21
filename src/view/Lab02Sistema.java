package view;

import java.util.Scanner;

import model.Lab02ContaCorrente;

public class Lab02Sistema {
	static Scanner sc = new Scanner(System.in);
	static Lab02ContaCorrente cc = new Lab02ContaCorrente();

	/*
	 * Método que realiza o cadastro de novos clientes
	 */
	private static void execCadastramento() {
		/*
		 * Dados cliente
		 */
		String info;
		int agenciaChecked = 0;
		int contaChecked = 0;
		String nomeCliente = "";
		double saldo = 0;
		boolean checkData = true;
		/*
		 * Dentro de um loop, é solicitado ao usuário o número da agência, verifica se o
		 * que foi digitado possui o número de caracteres necessário, se sim, tenta
		 * converter a String digitada para um inteiro. Caso ocorra a conversão sem
		 * erros, o número da agência é salvo e o loop é finalizado, caso contrário, o
		 * loop continua e é solicitado ao usuário para que entre novamente com o número
		 * da agência.
		 */
		info = "";
		while (checkData) {
			do {
				System.out.println("Informe o Nº da agência:");
				info = sc.next();
				if (info.length() < 4) {
					System.out.println("Número inválido, Ex: 1111");
				} else if (info.length() > 4) {
					System.out.println("Número inválido, Ex: 1111");
				}
			} while (info.length() < 4 || info.length() > 4);
			try {
				agenciaChecked = Integer.parseInt(info);
			} catch (Exception e) {
				System.out.println("Erro: Número de agência " + e.getMessage() + " é inválido!");
			}
			if (agenciaChecked != 0) {
				cc.numAgen = agenciaChecked;
				checkData = false;
			}
		}

		/*
		 * Dentro de um loop, é solicitado ao usuário o número da conta, verifica se o
		 * que foi digitado possui o número de caracteres necessário, se sim, tenta
		 * converter a String digitada para um inteiro. Caso ocorra a conversão sem
		 * erros, o número da conta é salvo e o loop é finalizado, caso contrário, o
		 * loop continua e é solicitado ao usuário para que entre novamente com o número
		 * da conta.
		 */
		info = "";
		checkData = true;
		while (checkData) {
			do {
				System.out.println("Informe o Nº da conta:");
				info = sc.next();
				if (info.length() < 7) {
					System.out.println("Número inválido, Ex: 1111111");
				} else if (info.length() > 7) {
					System.out.println("Número inválido, Ex: 1111111");
				}
			} while (info.length() < 7 || info.length() > 7);
			try {
				contaChecked = Integer.parseInt(info);
			} catch (Exception e) {
				System.out.println("Erro: Número de agência " + e.getMessage() + " é inválido!");
			}
			if (contaChecked != 0) {
				cc.numConta = contaChecked;
				checkData = false;
			}
		}

		/*
		 * Solicita ao usuário para digitar o nome do cliente, caso o nome possua mais
		 * que 4 caracteres o nome é cadastrado corretamente.
		 */
		do {
			System.out.println("Informe o nome do cliente: ");
			nomeCliente = sc.next().concat(sc.nextLine());
			if (nomeCliente.length() < 4) {
				System.out.println("Nome do clite deve conter mais que 4 caracteres.");
			}
		} while (nomeCliente.length() < 4);
		cc.nome = nomeCliente;

		/*
		 * Da a opção ao usuário se deseja realizar um depósito, valida a entrada. Caso
		 * sim, chama o método que realiza o depósito, caso não segue com a execução.
		 */
		String confirma;
		do {
			System.out.println("Deseja realizar um depósito? <S/n>");
			confirma = sc.nextLine();
		} while (!(confirma.equalsIgnoreCase("s")) && !(confirma.equalsIgnoreCase("n")));
		if (confirma.equalsIgnoreCase("s")) {
			execDeposito();
		}
		cc.imprimir();
	}

	/*
	 * Método que realiza um saque
	 */
	private static void execSaque() {
		int agencia;
		int conta;
		double valorSaque;
		String confirma;
		int validaSaque;
		boolean loopSaque = true;
		/*
		 * Modificar depois para validar a entrada *somente números*
		 */
		while (loopSaque) {
			System.out.println("Informe o número da agência:");
			agencia = sc.nextInt();
			if (agencia != cc.numAgen) {
				System.out.println("Número agência inválido.");
			} else {
				System.out.println("Informe o número da conta:");
				conta = sc.nextInt();
				if (conta != cc.numConta) {
					System.out.println("Número conta inválido.");
				} else {
					loopSaque = false;
				}
			}
		}
		if (cc.saldo > 0) {
			do {
				System.out.println("Informe o valor para saque:");
				valorSaque = sc.nextDouble();
			} while (valorSaque <= 0);
			do {
				System.out.println("Confirmar saque <S/n>  :");
				confirma = sc.next().concat(sc.nextLine());
			} while (!(confirma.equalsIgnoreCase("s")) && !(confirma.equalsIgnoreCase("n")));
			if (confirma.equalsIgnoreCase("s")) {
				validaSaque = cc.saque(valorSaque);
				if (validaSaque == 1) {
					System.out.println("Saque realizado.");
				} else {
					System.out.println("Saldo Insuficiente.");
				}
			} else {
				System.out.println("Saque cancelado.");
			}
		} else {
			System.out.println("Saldo Insuficiente.");
		}
	}

	/*
	 * Método que realiza um depósito
	 */
	private static void execDeposito() {
		int agencia;
		int conta;
		double valorDeposito;
		String confirma;
		boolean loopDeposito = true;
		/*
		 * Modificar depois para validar a entrada *somente números*
		 */
		while (loopDeposito) {
			System.out.println("Informe o número da agência:");
			agencia = sc.nextInt();
			if (agencia != cc.numAgen) {
				System.out.println("Número agência inválido.");
			} else {
				System.out.println("Informe o número da conta:");
				conta = sc.nextInt();
				if (conta != cc.numConta) {
					System.out.println("Número conta inválido.");
				} else {
					loopDeposito = false;
				}
			}
		}
		do {
			System.out.println("Informe o valor a ser depositado:");
			valorDeposito = sc.nextDouble();
		} while (valorDeposito <= 0);
		do {
			System.out.println("Confirmar depósito <S/n>  :");
			confirma = sc.next().concat(sc.nextLine());
		} while (!(confirma.equalsIgnoreCase("s")) && !(confirma.equalsIgnoreCase("n")));
		if (confirma.equalsIgnoreCase("s")) {
			cc.deposito(valorDeposito);
			System.out.println("Depósito realizado.");
		} else {
			System.out.println("Depósito cancelado.");
		}
	}

	public static void execImprimir() {
		cc.imprimir();
	}

	public static void main(String[] args) {

		int opMenu;
		boolean loopMenu = true;
		while (loopMenu) {
			System.out.println(
					"1 - Cadastramento\n" + "2 - Saque\n" + "3 - Deposito\n" + "4 - Exibir dados\n" + "9 - Fim");
			opMenu = sc.nextInt();
			switch (opMenu) {
			case 1:
				// Cadastro de conta
				execCadastramento();
				break;
			case 2:
				// saque
				execSaque();
				break;
			case 3:
				// Deposito
				execDeposito();
				break;
			case 4:
				// imprimir
				execImprimir();
				break;
			case 9:
				// Fim
				System.out.println("Finalizando operações...");
				System.exit(0);
			default:
				break;
			}
		}
	}
}

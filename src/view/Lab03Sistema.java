package view;

import java.util.Scanner;

import model.Lab03ContaCorrente;

public class Lab03Sistema {
	static Scanner sc = new Scanner(System.in);

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
		double valorDeposito = 0;
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
			do {
				System.out.println("Informe o valor a ser depositado:");
				valorDeposito = sc.nextDouble();
			} while (valorDeposito <= 0);
			do {
				System.out.println("Confirmar depósito <S/n>  :");
				confirma = sc.next().concat(sc.nextLine());
			} while (!(confirma.equalsIgnoreCase("s")) && !(confirma.equalsIgnoreCase("n")));
			if (confirma.equalsIgnoreCase("s")) {
				System.out.println("Depósito realizado.");
			} else {
				System.out.println("Depósito cancelado.");
			}
		}
		Lab03ContaCorrente cc = new Lab03ContaCorrente(agenciaChecked, contaChecked, nomeCliente, valorDeposito);
		cc.gravar();
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

		System.out.println("Informe o número da agência:");
		agencia = sc.nextInt();
		System.out.println("Informe o número da conta:");
		conta = sc.nextInt();
		Lab03ContaCorrente cc = new Lab03ContaCorrente(agencia, conta);

		if (cc.getSaldo() > 0) {
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
					cc.gravar();
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

		System.out.println("Informe o número da agência:");
		agencia = sc.nextInt();
		System.out.println("Informe o número da conta:");
		conta = sc.nextInt();
		Lab03ContaCorrente cc = new Lab03ContaCorrente(agencia, conta);

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
			cc.gravar();
			System.out.println("Depósito realizado.");
		} else {
			System.out.println("Depósito cancelado.");
		}
	}

	public static void execConsulta() {
		int agencia;
		int conta;
		String confirma;
		
		System.out.println("Informe o número da agência:");
		agencia = sc.nextInt();
		System.out.println("Informe o número da conta:");
		conta = sc.nextInt();
		
		do {
			System.out.println("Confirmar consulta <S/n>  :");
			confirma = sc.next().concat(sc.nextLine());
		} while (!(confirma.equalsIgnoreCase("s")) && !(confirma.equalsIgnoreCase("n")));
		if (confirma.equalsIgnoreCase("s")) {
			Lab03ContaCorrente cc = new Lab03ContaCorrente(agencia, conta);
			System.out.println("------------------------------------------");
			System.out.println("          Situacação da conta");
			System.out.println("------------------------------------------");
			cc.imprimir();
			System.out.println("------------------------------------------");
		} else {
			System.out.println("Consulta cancelada.");
		}
	}

	public static void main(String[] args) {

		int opMenu;
		boolean loopMenu = true;
		while (loopMenu) {
			System.out.println(
					"1 - Cadastramento\n" + "2 - Saque\n" + "3 - Deposito\n" + "4 - Consulta\n" + "9 - Fim");
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
				execConsulta();
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

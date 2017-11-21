package view;

import java.util.Scanner;

public class Lab01Sistema {
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
		info = "";
		do {
			System.out.println("Deseja realizar um depósito? <S/n>");
			info = sc.nextLine();
		} while (!(info.equalsIgnoreCase("s")) && !(info.equalsIgnoreCase("n")));
		if (info.equalsIgnoreCase("s")) {
			execDeposito();
		}
		System.out.println(new StringBuilder().append("Agência		: ").append(agenciaChecked).toString());
		System.out.println(new StringBuilder().append("Conta		: ").append(contaChecked).toString());
		System.out.println(new StringBuilder().append("Nome cliente	: ").append(nomeCliente).toString());
		System.out.println(new StringBuilder().append("Saldo		: R$ ").append(saldo).toString());
	}

	/*
	 * Método que realiza um saque
	 */
	private static void execSaque() {
		int agencia;
		int conta;
		double valorSaque;
		String info = "";

		do {
			System.out.println("Confirmar saque <S/n>  :");
			info = sc.nextLine();
		} while (!(info.equalsIgnoreCase("s")) && !(info.equalsIgnoreCase("n")));
		if (info.equalsIgnoreCase("s")) {
			// Realiza a operação de saque
			System.out.println("Saque realizado.");
		} else {
			System.out.println("Saque cancelado.");
		}
	}

	/*
	 * Método que realiza um depósito
	 */
	private static void execDeposito() {
		int agencia;
		int conta;
		double valorDeposito;
		String info = "";

		do {
			System.out.println("Confirmar depósito <S/n>  :");
			info = sc.nextLine();
		} while (!(info.equalsIgnoreCase("s")) && !(info.equalsIgnoreCase("n")));
		if (info.equalsIgnoreCase("s")) {
			// Realiza a operação de saque
			System.out.println("Depósito realizado.");
		} else {
			System.out.println("Depósito cancelado.");
		}
	}

	public static void main(String[] args) {

		int opMenu;
		boolean loopMenu = true;
		while (loopMenu) {
			System.out.println("1 - Cadastramento\n" + "2 - Saque\n" + "3 - Deposito\n" + "9 - Fim");
			opMenu = sc.nextInt();
			switch (opMenu) {
			case 1:
				// Cadastro de conta
				execCadastramento();
				break;
			case 2:
				// saque
				break;
			case 3:
				// Deposito
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

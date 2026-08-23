package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Barbeiro;
import entities.Cliente;
import entities.Servico;
import services.GerenciadorAgendamento;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		GerenciadorAgendamento gerenciador = new GerenciadorAgendamento();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		// Banco de dados simulado da barbearia
		List<Cliente> clientesCadastrados = new ArrayList<>();
		List<Barbeiro> barbeiros = new ArrayList<>();
		List<Servico> servicos = new ArrayList<>();

		// Dados iniciais do estabelecimento
		barbeiros.add(new Barbeiro(1, "Lucas"));
		barbeiros.add(new Barbeiro(2, "Mateus"));

		servicos.add(new Servico(1, "Corte de Cabelo", 40.00));
		servicos.add(new Servico(2, "Barba", 30.00));
		servicos.add(new Servico(3, "Cabelo e Barba (Combo)", 60.00));

		int opcao = 0;

		do {
			System.out.println("\n===========================================");
			System.out.println("     SYSTEM BARBER -     ");
			System.out.println("===========================================");
			System.out.println("1. Novo Agendamento");
			System.out.println("2. Ver Agenda Completa");
			System.out.println("3. Sair");
			System.out.print("Recepcionista, escolha uma opção: ");

			if (!sc.hasNextInt()) {
				System.out.println("\n[AVISO] Digite apenas números inteiros!");
				sc.nextLine();
				continue;
			}

			opcao = sc.nextInt();
			sc.nextLine(); // Limpa o buffer

			switch (opcao) {
			case 1:
				System.out.println("\n-------------------------------------------");
				System.out.println("PASSO 1: IDENTIFICAÇÃO DO CLIENTE");
				System.out.println("-------------------------------------------");
				System.out.print("Informe o nome do cliente: ");
				String nomeCliente = sc.nextLine();

				// Recepcionista procura no cadastro
				Cliente clienteDoAgendamento = null;
				for (Cliente c : clientesCadastrados) {
					if (c.getNome().equalsIgnoreCase(nomeCliente)) {
						clienteDoAgendamento = c;
						break;
					}
				}

				// Se for um cliente novo, realiza o cadastro rápido
				if (clienteDoAgendamento == null) {
					System.out.println("-> Cliente novo! Cadastrando no sistema...");
					System.out.print("Informe o telefone de contato: ");
					String telefone = sc.nextLine();

					int proximoId = clientesCadastrados.size() + 1;
					clienteDoAgendamento = new Cliente(proximoId, nomeCliente, telefone);
					clientesCadastrados.add(clienteDoAgendamento);
					System.out.println("-> Cadastro do(a) " + nomeCliente + " realizado com sucesso!");
				} else {
					System.out.println("-> Cliente já cadastrado(a) no sistema.");
				}

				// PASSO 2: ESCOLHA DO SERVIÇO 
				Servico servicoEscolhido = null;
				while (servicoEscolhido == null) {
					System.out.println("\n-------------------------------------------");
					System.out.println("PASSO 2: ESCOLHA DO SERVIÇO");
					System.out.println("-------------------------------------------");
					for (int i = 0; i < servicos.size(); i++) {
						Servico s = servicos.get(i);
						System.out.printf("%d. %s - R$ %.2f\n", (i + 1), s.getTipoServico(), s.getPreco());
					}
					System.out.print("Selecione o serviço desejado: ");

					if (!sc.hasNextInt()) {
						System.out.println("\n[AVISO] Entrada inválida! Digite o número correspondente ao serviço.");
						sc.nextLine();
						continue;
					}

					int opServico = sc.nextInt();
					sc.nextLine();

					if (opServico >= 1 && opServico <= servicos.size()) {
						servicoEscolhido = servicos.get(opServico - 1);
					} else {
						System.out.printf("\n[AVISO] Opção %d é inválida! Escolha um serviço entre 1 e %d.\n", opServico, servicos.size());
					}
				}

				// PASSO 3: ESCOLHA DO BARBEIRO
				Barbeiro barbeiroEscolhido = null;
				while (barbeiroEscolhido == null) {
					System.out.println("\n-------------------------------------------");
					System.out.println("PASSO 3: ESCOLHA DO BARBEIRO");
					System.out.println("-------------------------------------------");
					for (int i = 0; i < barbeiros.size(); i++) {
						System.out.printf("%d. %s\n", (i + 1), barbeiros.get(i).getNome());
					}
					System.out.print("Selecione o barbeiro: ");

					if (!sc.hasNextInt()) {
						System.out.println("\n[AVISO] Entrada inválida! Digite o número correspondente ao barbeiro.");
						sc.nextLine();
						continue;
					}

					int opBarbeiro = sc.nextInt();
					sc.nextLine();

					if (opBarbeiro >= 1 && opBarbeiro <= barbeiros.size()) {
						barbeiroEscolhido = barbeiros.get(opBarbeiro - 1);
					} else {
						System.out.printf("\n[AVISO] Opção %d é inválida! Escolha um barbeiro entre 1 e %d.\n", opBarbeiro, barbeiros.size());
					}
				}

				// PASSO 4: DATA E HORÁRIO 
				LocalDateTime horario = null;
				while (horario == null) {
					System.out.println("\n-------------------------------------------");
					System.out.println("PASSO 4: DATA E HORÁRIO");
					System.out.println("-------------------------------------------");
					System.out.print("Digite a data e horário (ex: 25/08/2026 14:00): ");
					String dataInput = sc.nextLine();

					try {
						horario = LocalDateTime.parse(dataInput, fmt);
					} catch (Exception e) {
						System.out.println("\n[AVISO] Formato de data/hora incorreto! Tente novamente seguindo o padrão dd/MM/yyyy HH:mm");
					}
				}

				// Executa o agendamento no serviço
				gerenciador.agendar(clienteDoAgendamento, barbeiroEscolhido, servicoEscolhido, horario);
				break;

			case 2:
				gerenciador.listarAgendamentos();
				break;

			case 3:
				System.out.println("\nSaindo...");
				break;

			default:
				System.out.println("\n[AVISO] Opção do menu inválida! Escolha entre 1 e 3.");
			}

		} while (opcao != 3);

		sc.close();
	}
}
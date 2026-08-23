package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.Agendamento;
import entities.Barbeiro;
import entities.Cliente;
import entities.Servico;

public class GerenciadorAgendamento {
	
	private List<Agendamento> agendamentos = new ArrayList<>();
	
	public void agendar(Cliente cliente, Barbeiro barbeiro, Servico servico, LocalDateTime horario) {
		int duracao = 30;
		for(Agendamento a : agendamentos) {
			if(a.getBarbeiro().getId() == barbeiro.getId() ) {
				LocalDateTime horarioInicio = a.getHorario();
				LocalDateTime horarioFim = horarioInicio.plusMinutes(duracao);
				if(a.getHorario().equals(horario) || horario.isAfter(horarioInicio) && horario.isBefore(horarioFim)) {
					System.out.printf("\nO barbeiro %s está em serviço nesse horário, novos horários a partir de %s", barbeiro.getNome(), horarioFim.format(DateTimeFormatter.ofPattern("HH:mm")));
					return;
				}

				
				
				return;
			}
		}
		
		Agendamento novo = new Agendamento(barbeiro, cliente, servico, horario);
		agendamentos.add(novo);
		System.out.printf("\nHorário agendado às %s para o cliente %s!", horario.format(DateTimeFormatter.ofPattern("dd/MM HH:mm")), cliente.getNome());
		
	}
	
	public void listarAgendamentos() {
		
		System.out.println("\nAgendamentos: ");
		if(agendamentos.isEmpty()) {
			System.out.println("Não há agendamentos");
		}
		else {
			for(Agendamento a : agendamentos) {
				System.out.println(a);
			}
		}
		
	}
	
	
	
}

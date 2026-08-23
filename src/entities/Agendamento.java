package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Agendamento {

	private Barbeiro barbeiro;
	private Cliente cliente;
	private Servico servico;
	private LocalDateTime horario;

	public Agendamento(Barbeiro barbeiro, Cliente cliente, Servico servico, LocalDateTime horario) {

		this.barbeiro = barbeiro;
		this.cliente = cliente;
		this.servico = servico;
		this.horario = horario;
	}

	public Barbeiro getBarbeiro() {
		return barbeiro;
	}

	public void setBarbeiro(Barbeiro barbeiro) {
		this.barbeiro = barbeiro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return String.format("Data: %s | Cliente: %s | Barbeiro: %s | Serviço: %s | Preço: %s", 
				horario.format(fmt),
				cliente.getNome(), 
				barbeiro.getNome(), 
				servico.getTipoServico(), 
				servico.getPreco());
	}

}

package entities;

public class Servico {
	
	private int id;
	private String tipoServico;
	private double preco;
	
	public Servico(int id, String tipoServico, double preco) {
		super();
		this.id = id;
		this.tipoServico = tipoServico;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
	
	
}

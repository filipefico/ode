package ode.agenda.ciu;

public enum EnumTipoAppointment {
	
	DIARIO(1, "Diário"), SEMANAL(2, "Semanal"), MENSAL(3, "Mensal");
	
	private int valor;
	private String descricao;
	
	private EnumTipoAppointment(int valor, String descricao) {
		// TODO Auto-generated constructor stub
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
}

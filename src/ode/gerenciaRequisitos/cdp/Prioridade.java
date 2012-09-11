package ode.gerenciaRequisitos.cdp;

public enum Prioridade{
	ALTA ("Alta"), MEDIA("M�dia"), BAIXA("Baixa"); 
	
	String nome;
	
	Prioridade(String nome){
		this.nome = nome;		
	}

	public String getNome() {
		return nome;
	}
}

package ode.conhecimento.requisito.cdp;

public enum TipoRequisito{
	FUNCIONAL ("Requisito Funcional"), NAOFUNCIONAL ("Requisito N�o Funcional"), REGRADENEGOCIO("Regra de Neg�cio");
	
	private String nome;
	
	TipoRequisito(String tipo){
		nome = tipo;
	}

	public String getNome() {
		return nome;
	}

}

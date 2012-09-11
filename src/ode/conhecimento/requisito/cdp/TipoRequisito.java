package ode.conhecimento.requisito.cdp;

public enum TipoRequisito{
	FUNCIONAL ("Requisito Funcional"), NAOFUNCIONAL ("Requisito Não Funcional"), REGRADENEGOCIO("Regra de Negócio");
	
	private String nome;
	
	TipoRequisito(String tipo){
		nome = tipo;
	}

	public String getNome() {
		return nome;
	}

}

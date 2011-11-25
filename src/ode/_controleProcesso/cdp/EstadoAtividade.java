package ode._controleProcesso.cdp;

public enum EstadoAtividade {

	Inativa("Inativa"),
	AguardandoAutorizacao("Aguardando Autoriza��o"),
	EmExecucao("Em Execu��o"),
	Finalizada("Finalizada"),
	Encerrada("Encerrada"),
	Suspensa("Suspensa"),
	Cancelada("Cancelada"),
	CanceladaDefinitivo("Cancelada em Definitivo"),
	Excluida("Marcada como Exclu�da");
	
	private String nome;

	private EstadoAtividade(String label) {
		this.nome = label;
	}

	public String toString() {
		return this.nome;
	}
}

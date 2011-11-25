package ode.alocacaoRecurso.cdp;

public enum EstadoAlocacaoRH {

	AguardandoInicioAtividade("Aguardando In�cio da Atividade"),
	AguardandoInicioParticipacao("Aguardando In�cio da Participa��o"),
	EmAndamento("Em Andamento"),
	EmAndamentoAjustes("Em Andamento para Ajustes"),
	EmAvaliacaoEncerramento("Em Avalia��o para Encerramento"),
	Encerrada("Encerrada"),
	Cancelada("Cancelada");

	private String label;

	private EstadoAlocacaoRH(String label) {
		this.label = label;
	}

	public String toString() {
		return this.label;
	}
}

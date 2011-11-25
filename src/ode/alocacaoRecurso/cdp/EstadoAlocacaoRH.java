package ode.alocacaoRecurso.cdp;

public enum EstadoAlocacaoRH {

	AguardandoInicioAtividade("Aguardando Início da Atividade"),
	AguardandoInicioParticipacao("Aguardando Início da Participação"),
	EmAndamento("Em Andamento"),
	EmAndamentoAjustes("Em Andamento para Ajustes"),
	EmAvaliacaoEncerramento("Em Avaliação para Encerramento"),
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

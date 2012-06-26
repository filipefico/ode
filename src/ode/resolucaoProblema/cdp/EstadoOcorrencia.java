package ode.resolucaoProblema.cdp;


public enum EstadoOcorrencia {
	
	AguardandoAnalise ("Aguardando Análise"),
	EmResolucao ("Em Resolução"),
	Resolvida ("Resolvida"),
	ParcialmenteResolvida ("Parcialmente Resolvida"),
	NaoResolvida ("Não Resolvida");
	
	private String label;

    private EstadoOcorrencia(String label) {
            this.label = label;
    }

    public String toString() {
            return this.label;
    }
      

}

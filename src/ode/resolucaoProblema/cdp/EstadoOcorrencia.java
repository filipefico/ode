package ode.resolucaoProblema.cdp;


public enum EstadoOcorrencia {
	
	AguardandoAnalise ("Aguardando An�lise"),
	EmResolucao ("Em Resolu��o"),
	Resolvida ("Resolvida"),
	ParcialmenteResolvida ("Parcialmente Resolvida"),
	NaoResolvida ("N�o Resolvida");
	
	private String label;

    private EstadoOcorrencia(String label) {
            this.label = label;
    }

    public String toString() {
            return this.label;
    }
      

}

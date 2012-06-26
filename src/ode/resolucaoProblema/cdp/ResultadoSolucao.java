package ode.resolucaoProblema.cdp;


public enum ResultadoSolucao {
	
	Satisfatorio("Satisfatorio"),
	ParcialmenteSatisfatorio ("Parcialmente Satifat�rio"),
	NaoSatisfatorio("N�o Satisfat�rio");
	
	private String label;

    private ResultadoSolucao(String label) {
            this.label = label;
    }

    public String toString() {
            return this.label;
    }
      
	
	
}

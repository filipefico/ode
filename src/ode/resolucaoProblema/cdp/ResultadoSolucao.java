package ode.resolucaoProblema.cdp;


public enum ResultadoSolucao {
	
	Satisfatorio("Satisfatorio"),
	ParcialmenteSatisfatorio ("Parcialmente Satifatório"),
	NaoSatisfatorio("Não Satisfatório");
	
	private String label;

    private ResultadoSolucao(String label) {
            this.label = label;
    }

    public String toString() {
            return this.label;
    }
      
	
	
}

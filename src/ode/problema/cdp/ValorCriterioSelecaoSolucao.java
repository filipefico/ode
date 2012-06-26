package ode.problema.cdp;

public enum ValorCriterioSelecaoSolucao {

	Alto("Alto"),
	Medio("Medio"),
	Baixo ("Baixo");

	private String label;

    private ValorCriterioSelecaoSolucao(String label) {
            this.label = label;
    }

    public String toString() {
            return this.label;
    }
      
	
	
	
	
}

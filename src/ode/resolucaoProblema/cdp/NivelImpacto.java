package ode.resolucaoProblema.cdp;

public enum NivelImpacto {
	
	MuitoAlto("Muito Alto"),
	Alto ("Alto"),
	Medio("Medio"),
	Baixo ("Baixo"),
	MuitoBaixo ("Muito Baixo");


	
	private String label;

    private NivelImpacto(String label) {
            this.label = label;
    }

    public String toString() {
            return this.label;
    }
      
}
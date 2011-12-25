package ode.conhecimento.organizacao.cdp;

public enum NivelKCompetencia {
	//manter sempre do maior para o menor para não quebrar a verificação das competencias 
	Nenhuma("Nenhuma Competência", 0),
	Baixo("Nível Baixo", 25),
	Medio("Nível Médio", 50),
	Alto("Nível Alto", 75);

	private String nome;
	private int valor;

	private NivelKCompetencia(String nome, int id) {
		this.nome = nome;
		this.setValor(valor);
	}

	public String toString() {
		return this.getNome();
	}

	public String getNome() {
		return this.nome;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public static NivelKCompetencia obterPorValor(int valor) {
		for (NivelKCompetencia obj : NivelKCompetencia.values()) {
			if (obj.getValor() == valor)
				return obj;
		}
		return null;
	}
}

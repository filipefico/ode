package ode.conhecimento.organizacao.cdp;

public enum NivelKCompetencia {
	Nenhuma("Nenhuma Compet�ncia", 0), Baixo("N�vel Baixo", 10), Medio("N�vel M�dio", 20), Alto("N�vel Alto", 30);

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

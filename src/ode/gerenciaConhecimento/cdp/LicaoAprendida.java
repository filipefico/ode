package ode.gerenciaConhecimento.cdp;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Licao Aprendida")
public class LicaoAprendida extends ItemConhecimento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8616593927931529168L;

	public static final String TIPO_SUCESSO = "Sucesso";

	public static final String TIPO_FRACASSO = "Fracasso";

	public static final String TIPO_INFORMATIVA = "Informativa";

	private String tipo;

	private String descricaoProblema;

	private String solucaoAdotadaOuRecomendada;

	private String resultadoEsperado;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getSolucaoAdotadaOuRecomendada() {
		return solucaoAdotadaOuRecomendada;
	}

	public void setSolucaoAdotadaOuRecomendada(String solucaoAdotadaOuRecomendada) {
		this.solucaoAdotadaOuRecomendada = solucaoAdotadaOuRecomendada;
	}

	public String getResultadoEsperado() {
		return resultadoEsperado;
	}

	public void setResultadoEsperado(String resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}

}

package ode.resolucaoProblema.cdp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
@Entity
public class ResultadoImplementacaoSolucao extends ObjetoPersistente {

	
	private static final long serialVersionUID = 7171198619198077288L;
	
	private OcorrenciaProblema ocorrenciaproblema;
	
	private String observacao;
	
	private NivelImpacto impactotempo;
	
	private NivelImpacto impactocusto;
	
	private NivelImpacto impactoqualidade;
	
	private SolucaoOcorrenciaProblema solucaoocorrenciaproblema;
	
	private ResultadoSolucao resultadosolucao;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
@Enumerated(EnumType.STRING)
	public NivelImpacto getImpactotempo() {
		return impactotempo;
	}

	public void setImpactotempo(NivelImpacto impactotempo) {
		this.impactotempo = impactotempo;
	}
@Enumerated(EnumType.STRING)
	public NivelImpacto getImpactocusto() {
		return impactocusto;
	}

	public void setImpactocusto(NivelImpacto impactocusto) {
		this.impactocusto = impactocusto;
	}
@Enumerated(EnumType.STRING)
	public NivelImpacto getImpactoqualidade() {
		return impactoqualidade;
	}

	public void setImpactoqualidade(NivelImpacto impactoqualidade) {
		this.impactoqualidade = impactoqualidade;
	}
@ManyToOne
	public SolucaoOcorrenciaProblema getSolucaoocorrenciaproblema() {
		return solucaoocorrenciaproblema;
	}

	public void setSolucaoocorrenciaproblema(SolucaoOcorrenciaProblema solucaoocorrenciaproblema) {
		this.solucaoocorrenciaproblema = solucaoocorrenciaproblema;
	}
@Enumerated(EnumType.STRING)
	public ResultadoSolucao getResultadosolucao() {
		return resultadosolucao;
	}

	public void setResultadosolucao(ResultadoSolucao resultadosolucao) {
		this.resultadosolucao = resultadosolucao;
	}

	public OcorrenciaProblema getOcorrenciaproblema() {
		return ocorrenciaproblema;
	}

	public void setOcorrenciaproblema(OcorrenciaProblema ocorrenciaproblema) {
		this.ocorrenciaproblema = ocorrenciaproblema;
	}


	
	
	

}

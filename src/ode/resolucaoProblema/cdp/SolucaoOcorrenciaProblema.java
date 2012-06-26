package ode.resolucaoProblema.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.problema.cdp.KCriterioSelecaoSolucao;
import ode.problema.cdp.KSolucao;
@Entity
public class SolucaoOcorrenciaProblema extends ObjetoPersistente {

	
	private static final long serialVersionUID = 9092310108612124048L;
	
	private Boolean selecionada;
	
	private String justificativaSelecao;
	
	private java.util.Date dataSelecao;
	
	private Set<KSolucao> solucao;
	
	private Set<KCriterioSelecaoSolucao> kcriterioselecaosolucao;
	
	private Set<AvaliacaoSolucaoOcorrenciaProblema> avaliacaosolucaoocorrenciaproblema;
	
	private RecursoHumano recursohumano;

	public Boolean getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Boolean selecionada) {
		this.selecionada = selecionada;
	}

	public String getJustificativaSelecao() {
		return justificativaSelecao;
	}

	public void setJustificativaSelecao(String justificativaSelecao) {
		this.justificativaSelecao = justificativaSelecao;
	}
	
	public java.util.Date getDataSelecao() {
		return dataSelecao;
	}

	public void setDataSelecao(java.util.Date dataSelecao) {
		this.dataSelecao = dataSelecao;
	}
	

@ManyToMany
	public Set<KSolucao> getSolucao() {
		return solucao;
	}

	public void setSolucao(Set<KSolucao> solucao) {
		this.solucao = solucao;
	}
@ManyToMany
	public Set<KCriterioSelecaoSolucao> getKcriterioselecaosolucao() {
		return kcriterioselecaosolucao;
	}

	public void setKcriterioselecaosolucao(
			Set<KCriterioSelecaoSolucao> kcriterioselecaosolucao) {
		this.kcriterioselecaosolucao = kcriterioselecaosolucao;
	}
@OneToMany
	public Set<AvaliacaoSolucaoOcorrenciaProblema> getAvaliacaosolucaoocorrenciaproblema() {
		return avaliacaosolucaoocorrenciaproblema;
	}

	public void setAvaliacaosolucaoocorrenciaproblema(
			Set<AvaliacaoSolucaoOcorrenciaProblema> avaliacaosolucaoocorrenciaproblema) {
		this.avaliacaosolucaoocorrenciaproblema = avaliacaosolucaoocorrenciaproblema;
	}
@ManyToOne
	public RecursoHumano getrecursohumano() {
		return recursohumano;
	}

	public void setrecursohumano(RecursoHumano recursohumano) {
		this.recursohumano = recursohumano;
	}



}

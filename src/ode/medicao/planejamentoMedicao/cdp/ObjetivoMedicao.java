package ode.medicao.planejamentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class ObjetivoMedicao extends Objetivo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1860872188027432039L;
	
	private Set<ObjetivoEstrategico> objetivosEstrategicos;
	private Set<ObjetivoSoftware> objetivosSoftware;

	private Set<NecessidadeInformacao> necessidadeInformacao;
	
	private TipoObjetivoMedicao tipoObjetivoMedicao;
	
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="objetivosMedicao")
	public Set<NecessidadeInformacao> getNecessidadeInformacao() {
		return necessidadeInformacao;
	}

	public void setNecessidadeInformacao(Set<NecessidadeInformacao> necessidadeInformacao) {
		this.necessidadeInformacao = necessidadeInformacao;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KObjetivoMedicao_KObjetivoEstrategico")
	public Set<ObjetivoEstrategico> getObjetivosEstrategicos() {
		return objetivosEstrategicos;
	}

	public void setObjetivosEstrategicos(Set<ObjetivoEstrategico> objetivosEstrategicos) {
		this.objetivosEstrategicos = objetivosEstrategicos;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KObjetivoMedicao_KObjetivoSoftware")
	public Set<ObjetivoSoftware> getObjetivosSoftware() {
		return objetivosSoftware;
	}

	public void setObjetivosSoftware(Set<ObjetivoSoftware> set) {
		this.objetivosSoftware = set;
	}

	public TipoObjetivoMedicao getTipoObjetivoMedicao() {
		return tipoObjetivoMedicao;
	}

	public void setTipoObjetivoMedicao(TipoObjetivoMedicao tipoObjetivoMedicao) {
		this.tipoObjetivoMedicao = tipoObjetivoMedicao;
	}



}

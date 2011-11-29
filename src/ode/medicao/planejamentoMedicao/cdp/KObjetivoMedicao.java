package ode.medicao.planejamentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class KObjetivoMedicao extends KObjetivo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1860872188027432039L;
	
	private Set<KObjetivoEstrategico> objetivosEstrategicos;
	private Set<KObjetivoSoftware> objetivosSoftware;

	private Set<KNecessidadeInformacao> necessidadeInformacao;
	
	private TipoObjetivoMedicao tipoObjetivoMedicao;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="objetivosMedicao")
	public Set<KNecessidadeInformacao> getNecessidadeInformacao() {
		return necessidadeInformacao;
	}

	public void setNecessidadeInformacao(Set<KNecessidadeInformacao> necessidadeInformacao) {
		this.necessidadeInformacao = necessidadeInformacao;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KObjetivoMedicao_KObjetivoEstrategico")
	public Set<KObjetivoEstrategico> getObjetivosEstrategicos() {
		return objetivosEstrategicos;
	}

	public void setObjetivosEstrategicos(Set<KObjetivoEstrategico> objetivosEstrategicos) {
		this.objetivosEstrategicos = objetivosEstrategicos;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KObjetivoMedicao_KObjetivoSoftware")
	public Set<KObjetivoSoftware> getObjetivosSoftware() {
		return objetivosSoftware;
	}

	public void setObjetivosSoftware(Set<KObjetivoSoftware> set) {
		this.objetivosSoftware = set;
	}

	public TipoObjetivoMedicao getTipoObjetivoMedicao() {
		return tipoObjetivoMedicao;
	}

	public void setTipoObjetivoMedicao(TipoObjetivoMedicao tipoObjetivoMedicao) {
		this.tipoObjetivoMedicao = tipoObjetivoMedicao;
	}



}

package ode.medicao.planejamentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimentoMedicao.cdp.KMedida;

@Entity
public class KNecessidadeInformacao extends Conhecimento{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5842307272940751525L;
	
	private Set<KObjetivoMedicao> objetivosMedicao;
	private Set<KMedida> medidas;
	private Set<KProcesso> processos;


	@ManyToMany(fetch=FetchType.EAGER, mappedBy="necessidadesInformacao")
	public Set<KMedida> getMedidas() {
		return medidas;
	}

	public void setMedidas(Set<KMedida> medidas) {
		this.medidas = medidas;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KNecessidadeInformacao_KObjetivoMedicao")	
	public Set<KObjetivoMedicao> getObjetivosMedicao() {
		return objetivosMedicao;
	}

	public void setObjetivosMedicao(Set<KObjetivoMedicao> objetivosMedicao) {
		this.objetivosMedicao = objetivosMedicao;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KNecessidadeInformacao_KObjetivoSoftware")
	public Set<KProcesso> getProcessos() {
		return processos;
	}

	public void setProcessos(Set<KProcesso> processos) {
		this.processos = processos;
	}
}

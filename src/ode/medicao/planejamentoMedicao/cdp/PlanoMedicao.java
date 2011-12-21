package ode.medicao.planejamentoMedicao.cdp;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class PlanoMedicao extends ObjetoPersistente{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7735685755805004193L;
	private Date data;
	private String descricao;
	private float versao;
	private RecursoHumano responsavel;
	
	private Set<ObjetivoEstrategico> objsEstrategico;
	private Set<ObjetivoSoftware> objsSoftware;
	private Set<ObjetivoMedicao> objsMedicao;
	
	private Set<NecessidadeInformacao> necessidades;
	private Set<KProcesso> processos;
	
	private Set<MedidaPlanoMedicao> mpm;

	@ManyToMany(fetch=FetchType.EAGER)
	public Set<ObjetivoEstrategico> getObjsEstrategico() {
		return objsEstrategico;
	}

	public void setObjsEstrategico(Set<ObjetivoEstrategico> objsEstrategico) {
		this.objsEstrategico = objsEstrategico;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Set<ObjetivoSoftware> getObjsSoftware() {
		return objsSoftware;
	}

	public void setObjsSoftware(Set<ObjetivoSoftware> objsSoftware) {
		this.objsSoftware = objsSoftware;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Set<ObjetivoMedicao> getObjsMedicao() {
		return objsMedicao;
	}

	public void setObjsMedicao(Set<ObjetivoMedicao> objsMedicao) {
		this.objsMedicao = objsMedicao;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Set<NecessidadeInformacao> getNecessidades() {
		return necessidades;
	}

	public void setNecessidades(Set<NecessidadeInformacao> necessidades) {
		this.necessidades = necessidades;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Set<KProcesso> getProcessos() {
		return processos;
	}

	public void setProcessos(Set<KProcesso> processos) {
		this.processos = processos;
	}

	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Set<MedidaPlanoMedicao> getMpm() {
		return mpm;
	}

	public void setMpm(Set<MedidaPlanoMedicao> mpm) {
		this.mpm = mpm;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getVersao() {
		return versao;
	}

	public void setVersao(float versao) {
		this.versao = versao;
	}

	public RecursoHumano getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(RecursoHumano responsavel) {
		this.responsavel = responsavel;
	}
	
	public void adicionaMpm(MedidaPlanoMedicao medidaPlanoMedicao) {
		if(this.mpm==null){
			this.mpm = new HashSet<MedidaPlanoMedicao>();
		}
		this.mpm.add(medidaPlanoMedicao);
	}
	
}

package ode.medicao.planejamentoMedicao.cdp;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecursoHumano;


public abstract class PlanoMedicao extends ObjetoPersistente{


	private Date data;
	private String descricao;
	private float versao;
	private KRecursoHumano responsavel;
	
	private Collection<KObjetivoEstrategico> objsEstrategico;
	private Collection<KObjetivoSoftware> objsSoftware;
	private Collection<KObjetivoMedicao> objsMedicao;
	
	private Collection<KNecessidadeInformacao> necessidades;
	private Collection<KProcesso> processos;
	
	private Collection<MedidaPlanoMedicao> mpm;

	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<KObjetivoEstrategico> getObjsEstrategico() {
		return objsEstrategico;
	}

	public void setObjsEstrategico(Collection<KObjetivoEstrategico> objsEstrategico) {
		this.objsEstrategico = objsEstrategico;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<KObjetivoSoftware> getObjsSoftware() {
		return objsSoftware;
	}

	public void setObjsSoftware(Collection<KObjetivoSoftware> objsSoftware) {
		this.objsSoftware = objsSoftware;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<KObjetivoMedicao> getObjsMedicao() {
		return objsMedicao;
	}

	public void setObjsMedicao(Collection<KObjetivoMedicao> objsMedicao) {
		this.objsMedicao = objsMedicao;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<KNecessidadeInformacao> getNecessidades() {
		return necessidades;
	}

	public void setNecessidades(Collection<KNecessidadeInformacao> necessidades) {
		this.necessidades = necessidades;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<KProcesso> getProcessos() {
		return processos;
	}

	public void setProcessos(Collection<KProcesso> processos) {
		this.processos = processos;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<MedidaPlanoMedicao> getMpm() {
		return mpm;
	}

	public void setMpm(Collection<MedidaPlanoMedicao> mpm) {
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

	public KRecursoHumano getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(KRecursoHumano responsavel) {
		this.responsavel = responsavel;
	}
	
	
}

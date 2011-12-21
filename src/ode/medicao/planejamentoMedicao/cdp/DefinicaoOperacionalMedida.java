package ode.medicao.planejamentoMedicao.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimentoMedicao.cdp.KPeriodicidade;
import ode.conhecimentoMedicao.cdp.KProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cdp.KProcedimentoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;

@Entity
public class DefinicaoOperacionalMedida extends Conhecimento{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8691885298387647139L;
	
	private AplicacaoDefinicaoOperacional aplicacao;
	private Date data;
	private String intervalo;
	private String formulaCalculoMedida;
	private Set<ObjetivoMedicao> objetivosMedicao;
	
	private KProcedimentoMedicao procedimentoMedicao;
	private KRecursoHumano responsavelMedicao;
	private KAtividade momentoMedicao;
	private KPeriodicidade periodicidadeMedicao;
	
	private KProcedimentoAnaliseMedicao procedimentoAnaliseMedicao;
	private KRecursoHumano responsavelAnaliseMedicao;
	private KAtividade momentoAnaliseMedicao;
	private KPeriodicidade periodicidadeAnaliseMedicao;

	public AplicacaoDefinicaoOperacional getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(AplicacaoDefinicaoOperacional aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public KProcedimentoMedicao getProcedimentoMedicao() {
		return procedimentoMedicao;
	}

	public void setProcedimentoMedicao(KProcedimentoMedicao procedimentoMedicao) {
		this.procedimentoMedicao = procedimentoMedicao;
	}

	public String getFormulaCalculoMedida() {
		return formulaCalculoMedida;
	}

	public void setFormulaCalculoMedida(String formulaCalculoMedida) {
		this.formulaCalculoMedida = formulaCalculoMedida;
	}

	public KRecursoHumano getResponsavelMedicao() {
		return responsavelMedicao;
	}

	public void setResponsavelMedicao(KRecursoHumano responsavelMedicao) {
		this.responsavelMedicao = responsavelMedicao;
	}

	public KAtividade getMomentoMedicao() {
		return momentoMedicao;
	}

	public void setMomentoMedicao(KAtividade momentoMedicao) {
		this.momentoMedicao = momentoMedicao;
	}

	public KPeriodicidade getPeriodicidadeMedicao() {
		return periodicidadeMedicao;
	}

	public void setPeriodicidadeMedicao(KPeriodicidade periodicidadeMedicao) {
		this.periodicidadeMedicao = periodicidadeMedicao;
	}

	public KProcedimentoAnaliseMedicao getProcedimentoAnaliseMedicao() {
		return procedimentoAnaliseMedicao;
	}

	public void setProcedimentoAnaliseMedicao(
			KProcedimentoAnaliseMedicao procedimentoAnaliseMedicao) {
		this.procedimentoAnaliseMedicao = procedimentoAnaliseMedicao;
	}

	public KRecursoHumano getResponsavelAnaliseMedicao() {
		return responsavelAnaliseMedicao;
	}

	public void setResponsavelAnaliseMedicao(KRecursoHumano responsavelAnaliseMedicao) {
		this.responsavelAnaliseMedicao = responsavelAnaliseMedicao;
	}

	public KAtividade getMomentoAnaliseMedicao() {
		return momentoAnaliseMedicao;
	}

	public void setMomentoAnaliseMedicao(KAtividade momentoAnaliseMedicao) {
		this.momentoAnaliseMedicao = momentoAnaliseMedicao;
	}

	public KPeriodicidade getPeriodicidadeAnaliseMedicao() {
		return periodicidadeAnaliseMedicao;
	}

	public void setPeriodicidadeAnaliseMedicao(
			KPeriodicidade periodicidadeAnaliseMedicao) {
		this.periodicidadeAnaliseMedicao = periodicidadeAnaliseMedicao;
	}

	public String getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KDefinicao_KMedicao")
	public Set<ObjetivoMedicao> getObjetivosMedicao() {
		return objetivosMedicao;
	}

	public void setObjetivosMedicao(Set<ObjetivoMedicao> objetivosMedicao) {
		this.objetivosMedicao = objetivosMedicao;
	}


}

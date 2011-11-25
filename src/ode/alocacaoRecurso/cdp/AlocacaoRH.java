package ode.alocacaoRecurso.cdp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import ode._controleProcesso.cdp.Atividade;
import ode._controleProcesso.cdp.EstadoAtividade;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@Entity
public class AlocacaoRH extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private RecursoHumano recursoHumano;
	private Atividade atividade;
	private KRecursoHumano kRecursoHumano;
	private Date dtInicioPrevisto;
	private Date dtFimPrevisto;
	private Date dtInicioEfetivo;
	private Date dtFimEfetivo;
	private EstadoAlocacaoRH estado;
	private Integer dedicacao;
	
	public AlocacaoRH() {
	}
	
	public AlocacaoRH(Atividade atividade, RecursoHumano rh, KRecursoHumano papel) {
		setAtividade(atividade);
		setRecursoHumano(rh);
		setkRecursoHumano(papel);
		
		if(atividade.getEstado().equals(EstadoAtividade.Inativa)
				|| atividade.getEstado().equals(EstadoAtividade.AguardandoAutorizacao)) {
			setEstado(EstadoAlocacaoRH.AguardandoInicioAtividade);
		} else {
			setEstado(EstadoAlocacaoRH.AguardandoInicioParticipacao);
		}
	}

	@ManyToOne
	public RecursoHumano getRecursoHumano() {
		return recursoHumano;
	}
	public void setRecursoHumano(RecursoHumano recursoHumano) {
		this.recursoHumano = recursoHumano;
	}
	
	@ManyToOne
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	@ManyToOne
	public KRecursoHumano getkRecursoHumano() {
		return kRecursoHumano;
	}
	public void setkRecursoHumano(KRecursoHumano kRecursoHumano) {
		this.kRecursoHumano = kRecursoHumano;
	}
	
	public Date getDtInicioPrevisto() {
		return dtInicioPrevisto;
	}
	public void setDtInicioPrevisto(Date dtInicioPrevisto) {
		this.dtInicioPrevisto = dtInicioPrevisto;
	}
	
	public Date getDtFimPrevisto() {
		return dtFimPrevisto;
	}
	public void setDtFimPrevisto(Date dtFimPrevisto) {
		this.dtFimPrevisto = dtFimPrevisto;
	}
	
	public Date getDtInicioEfetivo() {
		return dtInicioEfetivo;
	}
	public void setDtInicioEfetivo(Date dtInicioEfetivo) {
		this.dtInicioEfetivo = dtInicioEfetivo;
	}
	
	public Date getDtFimEfetivo() {
		return dtFimEfetivo;
	}
	public void setDtFimEfetivo(Date dtFimEfetivo) {
		this.dtFimEfetivo = dtFimEfetivo;
	}
	
	public Integer getDedicacao() {
		return dedicacao;
	}
	public void setDedicacao(Integer dedicacao) {
		this.dedicacao = dedicacao;
	}

	@Enumerated(EnumType.STRING)
	public EstadoAlocacaoRH getEstado() {
		return estado;
	}
	public void setEstado(EstadoAlocacaoRH estado) {
		this.estado = estado;
	}
}
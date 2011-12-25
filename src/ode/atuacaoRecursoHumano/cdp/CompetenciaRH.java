package ode.atuacaoRecursoHumano.cdp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import ode.conhecimento.organizacao.cdp.KCompetencia;
import ode.conhecimento.organizacao.cdp.NivelKCompetencia;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class CompetenciaRH extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private KCompetencia kCompetencia;
	private NivelKCompetencia nivel;
	private AtuacaoRH atuacaoRH;	

	@ManyToOne
	public AtuacaoRH getAtuacaoRH() {
		return atuacaoRH;
	}
	public void setAtuacaoRH(AtuacaoRH atuacaoRH) {
		this.atuacaoRH = atuacaoRH;
	}

	@ManyToOne
	public KCompetencia getkCompetencia() {
		return kCompetencia;
	}
	public void setkCompetencia(KCompetencia competencia) {
		this.kCompetencia = competencia;
	}

	@Enumerated
	public NivelKCompetencia getNivel() {
		return nivel;
	}
	public void setNivel(NivelKCompetencia nivelCompetencia) {
		this.nivel = nivelCompetencia;
	}
}
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
	private NivelKCompetencia nivelCompetencia;
	private AtuacaoRH atuacaoRH;	

	@ManyToOne
	public AtuacaoRH getAtuacaoRH() {
		return atuacaoRH;
	}
	public void setAtuacaoRH(AtuacaoRH atuacaoRH) {
		this.atuacaoRH = atuacaoRH;
	}

	@ManyToOne
	public KCompetencia getKCompetencia() {
		return kCompetencia;
	}
	public void setKCompetencia(KCompetencia competencia) {
		this.kCompetencia = competencia;
	}

	@Enumerated
	public NivelKCompetencia getNivelCompetencia() {
		return nivelCompetencia;
	}
	public void setNivelCompetencia(NivelKCompetencia nivelCompetencia) {
		this.nivelCompetencia = nivelCompetencia;
	}
}
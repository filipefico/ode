package ode.planejamentoCompetencia.cdp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import ode._controleProcesso.cdp.DemandaRH;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.organizacao.cdp.KCompetencia;
import ode.conhecimento.organizacao.cdp.NivelKCompetencia;

@Entity
public class DemandaCompetencia extends ObjetoPersistente {
    
	private static final long serialVersionUID = 1L;
    
    private KCompetencia kCompetencia;
    private DemandaRH demandaRH;
    private NivelKCompetencia nivel;

    @ManyToOne
	public KCompetencia getkCompetencia() {
		return kCompetencia;
	}
	public void setkCompetencia(KCompetencia kCompetencia) {
		this.kCompetencia = kCompetencia;
	}

	@ManyToOne
	public DemandaRH getDemandaRH() {
		return demandaRH;
	}
	public void setDemandaRH(DemandaRH demandaRH) {
		this.demandaRH = demandaRH;
	}
	
	@Enumerated
	public NivelKCompetencia getNivel() {
		return nivel;
	}
	public void setNivel(NivelKCompetencia nivel) {
		this.nivel = nivel;
	}
    
}
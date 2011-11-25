package ode._controleProcesso.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@Entity
public class DemandaRH extends ObjetoPersistente {
    
	private static final long serialVersionUID = 1L;
   
	private DefinicaoAtividade definicaoAtividade;
    private KRecursoHumano kRecursoHumano;
    
    @ManyToOne
	public DefinicaoAtividade getDefinicaoAtividade() {
		return definicaoAtividade;
	}
	public void setDefinicaoAtividade(DefinicaoAtividade definicaoAtividade) {
		this.definicaoAtividade = definicaoAtividade;
	}
    
    @ManyToOne
    public KRecursoHumano getkRecursoHumano() {
    	return this.kRecursoHumano;
    }
	public void setkRecursoHumano(KRecursoHumano kRecursoHumano) {
		this.kRecursoHumano = kRecursoHumano;
	}
}
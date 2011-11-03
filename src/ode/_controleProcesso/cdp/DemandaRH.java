package ode._controleProcesso.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

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
    public KRecursoHumano getKRecursoHumano() {
    	return this.kRecursoHumano;
    }
	public void setKRecursoHumano(KRecursoHumano kRecursoHumano) {
		this.kRecursoHumano = kRecursoHumano;
	}
}
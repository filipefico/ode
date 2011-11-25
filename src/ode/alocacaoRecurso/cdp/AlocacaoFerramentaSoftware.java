package ode.alocacaoRecurso.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleProcesso.cdp.Atividade;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class AlocacaoFerramentaSoftware extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private FerramentaSoftware ferramentaSoftware;
	private Atividade atividade;
	
	public AlocacaoFerramentaSoftware() {
		
	}
	public AlocacaoFerramentaSoftware(Atividade atividade, FerramentaSoftware fs) {
		this.ferramentaSoftware = fs;
		this.atividade = atividade;
	}
	
	@ManyToOne
	public FerramentaSoftware getFerramentaSoftware() {
		return ferramentaSoftware;
	}
	public void setFerramentaSoftware(FerramentaSoftware ferramentaSoftware) {
		this.ferramentaSoftware = ferramentaSoftware;
	}
	
	@ManyToOne
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

}
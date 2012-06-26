package ode.entidadeProblema.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.cdp.KArtefato;
import ode.problema.cdp.TipoEntidadeProblema;
@Entity
public class ArtefatoProblema extends EntidadeProblema<KArtefato>{

	
	private static final long serialVersionUID = -5206121682095820440L;
	
	private final TipoEntidadeProblema tipo = TipoEntidadeProblema.ARTEFATO;
	
	private KArtefato kartefato;
	
	@Override
	public TipoEntidadeProblema recuperaTipo() {
		return tipo;
	}
	
	@ManyToOne
	public KArtefato getKartefato() {
		return kartefato;
	}

	public void setKartefato(KArtefato kartefato) {
		this.kartefato = kartefato;
	}

	@Override
	public String toString() {
		return this.kartefato.getNome();
	}

}

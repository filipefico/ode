package ode.problema.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import ode.conhecimento.principal.cdp.Conhecimento;



@Entity
public class KCausa extends Conhecimento {

	
	private static final long serialVersionUID = -6482913699780605858L;

	private Set <KProblema> kproblema;

	  @ManyToMany(fetch=FetchType.EAGER)
		@JoinTable(name="KCausa_KProblema")
	public Set <KProblema> getKproblema() {
		return kproblema;
	}

	public void setKproblema(Set <KProblema> kproblema) {
		this.kproblema = kproblema;
	}



 

}

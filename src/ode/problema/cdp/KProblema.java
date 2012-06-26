package ode.problema.cdp;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;


@Entity
public class KProblema extends Conhecimento {
	private static final long serialVersionUID = 7334178953999584565L;

	
	private Set <KSolucao> ksolucao;
	private KCategoriaProblema kcategoriaproblema;
	


@ManyToMany
	public Set<KSolucao> getKsolucao() {
		return ksolucao;
	}

	public void setKsolucao(Set<KSolucao> ksolucao) {
		this.ksolucao = ksolucao;
	}
@ManyToOne	
	public void setCategoria(KCategoriaProblema kcategoriaproblema) {
		this.kcategoriaproblema = kcategoriaproblema;
	}
	
	public KCategoriaProblema getCategoria() {
		return kcategoriaproblema;
	}	
	

}

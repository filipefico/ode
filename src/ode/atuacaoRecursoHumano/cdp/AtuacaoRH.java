package ode.atuacaoRecursoHumano.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@Entity
public class AtuacaoRH extends ObjetoPersistente{

	private static final long serialVersionUID = 1L;
	
	private RecursoHumano recursoHumano;
	private Set<KRecursoHumano> papeis;

	@OneToOne
	public RecursoHumano getRecursoHumano() {
		return recursoHumano;
	}
	public void setRecursoHumano(RecursoHumano recursoHumano) {
		this.recursoHumano = recursoHumano;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="atuacaoRH_papel")
	public Set<KRecursoHumano> getPapeis() {
		return papeis;
	}
	public void setPapeis(Set<KRecursoHumano> papeis) {
		this.papeis = papeis;
	}
	
}

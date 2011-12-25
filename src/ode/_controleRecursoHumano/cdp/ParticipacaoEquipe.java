package ode._controleRecursoHumano.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@Entity
public class ParticipacaoEquipe extends ObjetoPersistente{

	private static final long serialVersionUID = 1L;
	
	private Equipe equipe;
	private RecursoHumano recursoHumano;
	private Set<KRecursoHumano> papeis;
	private Date dtInicio;
	private Date dtFim;
	
	
	@ManyToOne
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	@OneToOne
	public RecursoHumano getRecursoHumano() {
		return recursoHumano;
	}
	public void setRecursoHumano(RecursoHumano recursoHumano) {
		this.recursoHumano = recursoHumano;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="participacaoequipe_papel")
	public Set<KRecursoHumano> getPapeis() {
		return papeis;
	}
	public void setPapeis(Set<KRecursoHumano> papeis) {
		this.papeis = papeis;
	}
	
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	
	public Date getDtFim() {
		return dtFim;
	}
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	
	
}

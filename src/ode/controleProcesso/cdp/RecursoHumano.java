package ode.controleProcesso.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.Cdp.KRecursoHumano;

@Entity
public class RecursoHumano extends Recurso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cargaHoraria;

	private KRecursoHumano cargo;

	private Set<KRecursoHumano> papeis;

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="recursohumano_papel")
	public Set<KRecursoHumano> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<KRecursoHumano> papeis) {
		this.papeis = papeis;
	}

	public void setCargo(KRecursoHumano cargo) {
		this.cargo = cargo;
	}

	@ManyToOne
	public KRecursoHumano getCargo() {
		return cargo;
	}	

}

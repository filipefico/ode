package ode.conhecimentoMedicao.cdp;

import java.util.Set;

import javax.jws.Oneway;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;

import ode.conhecimento.principal.cdp.Conhecimento;


@Entity
public class KElementoMensuravel extends Conhecimento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4526623633578588032L;
	
	private Set<TipoEntidadeMensuravel> tipoEntidadeMensuravel;
	
	@ElementCollection(targetClass=TipoEntidadeMensuravel.class)
	@Enumerated(EnumType.ORDINAL)
	public Set<TipoEntidadeMensuravel> getTipoEntidadeMensuravel() {
		return tipoEntidadeMensuravel;
	}

	public void setTipoEntidadeMensuravel(Set<TipoEntidadeMensuravel> tipoEntidadeMensuravel) {
		this.tipoEntidadeMensuravel = tipoEntidadeMensuravel;
	}
}

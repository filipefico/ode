package ode.conhecimentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ode.conhecimento.principal.cdp.Conhecimento;


@Entity
public class KElementoMensuravel extends Conhecimento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4526623633578588032L;
	
	private Set<KTipoEntidadeMensuravel> tipoEntidadeMensuravel;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KElementoMensuravel_KTipoEntidadeMensuravel")
	public Set<KTipoEntidadeMensuravel> getTipoEntidadeMensuravel() {
		return tipoEntidadeMensuravel;
	}

	public void setTipoEntidadeMensuravel(Set<KTipoEntidadeMensuravel> tipoEntidadeMensuravel) {
		this.tipoEntidadeMensuravel = tipoEntidadeMensuravel;
	}
}

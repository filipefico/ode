package ode.conhecimentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KProcedimentoAnaliseMedicao extends Conhecimento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -588019579298487197L;
	private Set<KMetodoAnalitico> metodosAnaliticos;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KProcedimentoAnaliseMedicao_KMetodoAnalitico")
	public Set<KMetodoAnalitico> getMetodosAnaliticos() {
		return metodosAnaliticos;
	}

	public void setMetodosAnaliticos(Set<KMetodoAnalitico> metodosAnaliticos) {
		this.metodosAnaliticos = metodosAnaliticos;
	}
	
}

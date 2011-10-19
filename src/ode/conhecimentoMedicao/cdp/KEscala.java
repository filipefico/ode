package ode.conhecimentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KEscala extends Conhecimento{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3784345351026743444L;
	private Set<KValorEscala> valores ;
	private TipoEscala tipo;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="KEscala_KTipoEscala")
	public Set<KValorEscala> getValores() {
		return valores;
	}

	public void setValores(Set<KValorEscala> valores) {
		this.valores = valores;
	}

	public TipoEscala getTipo() {
		return tipo;
	}

	public void setTipo(TipoEscala tipo) {
		this.tipo = tipo;
	}
	
}

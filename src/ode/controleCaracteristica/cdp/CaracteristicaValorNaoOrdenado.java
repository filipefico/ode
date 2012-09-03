package ode.controleCaracteristica.cdp;

import java.util.Set;

import javax.persistence.Entity;

@Entity
public class CaracteristicaValorNaoOrdenado extends Caracteristica{
	
	private static final long serialVersionUID = 1L;
	
	private boolean permiteCriarPossiveisValores;
	private boolean permiteMultivalor;
	
	public boolean isPermiteCriarPossiveisValores() {
		return permiteCriarPossiveisValores;
	}

	public void setPermiteCriarPossiveisValores(boolean permiteCriarPossiveisValores) {
		this.permiteCriarPossiveisValores = permiteCriarPossiveisValores;
	}

	public boolean ispermiteMultivalor() {
		return permiteMultivalor;
	}

	public void setPermiteMultivalor(boolean permiteMultivalor) {
		this.permiteMultivalor = permiteMultivalor;
	}
	
}
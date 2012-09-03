package ode.controleCaracteristica.cdp;

import javax.persistence.Entity;

@Entity
public class PossivelValorOrdenado extends PossivelValor {

	private static final long serialVersionUID = 1L;

	private Double valor;
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor2) {
		this.valor = valor2;
	}

}
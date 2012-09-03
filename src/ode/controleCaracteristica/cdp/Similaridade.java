package ode.controleCaracteristica.cdp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class Similaridade extends ObjetoPersistente{
	
	private static final long serialVersionUID = 1L;

	private Double valor;
	private PossivelValorNaoOrdenado pValor;
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor2) {
		this.valor = valor2;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public PossivelValorNaoOrdenado getPValor() {
		return pValor;
	}

	public void setPValor(PossivelValorNaoOrdenado ppValor) {
		this.pValor = ppValor;
	}

}
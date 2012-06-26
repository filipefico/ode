package ode.problema.cdp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import ode.conhecimento.principal.cdp.Conhecimento;



@Entity
public class KCriterioSelecaoSolucao extends Conhecimento {

	
	private static final long serialVersionUID = -7640108102784717539L;
	
	private int peso; 
	
	private ValorCriterioSelecaoSolucao valor;

@Enumerated(EnumType.STRING)
	public ValorCriterioSelecaoSolucao getvalor() {
		return valor;
	}

	public void setvalor (ValorCriterioSelecaoSolucao valor) {
		this.valor = valor;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}


}

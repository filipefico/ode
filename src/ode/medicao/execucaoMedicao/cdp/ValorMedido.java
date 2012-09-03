package ode.medicao.execucaoMedicao.cdp;

import javax.persistence.Entity;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class ValorMedido extends ObjetoPersistente{

	private float valor;

	public ValorMedido(String value) throws NumberFormatException {
		valor = Float.parseFloat(value);
	}
	
	public ValorMedido(){}

	public float getDescricao() {
		return valor;
	}

	public void setDescricao(float descricao) {
		this.valor = descricao;
	}
	
	public String toString(){
		return Float.toString(getDescricao());
	}
}

package ode.medicao.planejamentoMedicao.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimentoMedicao.cdp.KMedida;

@Entity
public class ValorReferencia extends ObjetoPersistente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572429986176025355L;

	private Date data;
	private float valor;
	private FaixaReferencia faixa;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public FaixaReferencia getFaixa() {
		return faixa;
	}
	public void setFaixa(FaixaReferencia faixa) {
		this.faixa = faixa;
	}
	
	public String toString(){
		return Float.toString(valor);
	}
	
}

package ode.alocacaoRecurso.cdp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class EsforcoDespendido extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;

	private AlocacaoRH alocacaoRH;
	private int qtdHoras;
	private Date data;
	private String observacao;
	
	@ManyToOne
	public AlocacaoRH getAlocacaoRH() {
		return alocacaoRH;
	}
	public void setAlocacaoRH(AlocacaoRH alocacaoRH) {
		this.alocacaoRH = alocacaoRH;
	}
	
	public int getQtdHoras() {
		return qtdHoras;
	}
	public void setQtdHoras(int qtdHoras) {
		this.qtdHoras = qtdHoras;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
}

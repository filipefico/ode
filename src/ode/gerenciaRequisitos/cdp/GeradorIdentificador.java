package ode.gerenciaRequisitos.cdp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.controleProjeto.cdp.Projeto;

@Entity
public class GeradorIdentificador extends ObjetoPersistente{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6258634179834844318L;
	
	Projeto projeto;
	long idRf;
	long idRnf;
	long idRn;
	
	public GeradorIdentificador(){
	}
	
	public GeradorIdentificador(Projeto p){
		idRf = 1;
		idRnf = 1;
		idRn = 1;
		projeto = p;
	}

	@OneToOne
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public long getIdRf() {
		return idRf;
	}

	public void setIdRf(long idRf) {
		this.idRf = idRf;
	}

	public long getIdRnf() {
		return idRnf;
	}

	public void setIdRnf(long idRnf) {
		this.idRnf = idRnf;
	}

	public long getIdRn() {
		return idRn;
	}

	public void setIdRn(long idRn) {
		this.idRn = idRn;
	}

	public void incIdRf() {
		idRf++;
	}

	public void incIdRnf() {
		idRnf++;
	}

	public void incIdRn() {
		idRn++;
	}
}

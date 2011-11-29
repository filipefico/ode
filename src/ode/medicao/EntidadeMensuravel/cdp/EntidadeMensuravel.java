package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

@Entity
public abstract class EntidadeMensuravel<T extends ObjetoPersistente> extends ObjetoPersistente{

	private T entidade;
	
	public abstract TipoEntidadeMensuravel recuperaTipo();
	
	public String toString(){
		return getEntidade().toString();
	}
	
	public boolean equals(Object o){
		return this.toString()==o.toString();
	}
	
	public T getEntidade(){
		return this.entidade;
	}
	
	public void setEntidade(T entidade){
		this.entidade = entidade;
	}
	
}

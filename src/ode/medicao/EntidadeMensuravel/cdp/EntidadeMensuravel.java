package ode.medicao.EntidadeMensuravel.cdp;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Target;
import org.springframework.context.annotation.DependsOn;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class EntidadeMensuravel<T extends ObjetoPersistente> extends ObjetoPersistente{
	 

	private static final long serialVersionUID = -4096346535615754341L;

	public abstract TipoEntidadeMensuravel recuperaTipo();
	protected T entidade;
	
	public String toString(){
		return getEntidade().toString();
	}
	
	public boolean equals(Object o){
		return this.toString()==o.toString();
	}
	
	@PostConstruct
	public abstract T getEntidade();
	
	public abstract void setEntidade(T entidade);
	
}

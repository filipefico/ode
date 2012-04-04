package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.AnyMetaDef;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

//@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class EntidadeMensuravel<T extends ObjetoPersistente> extends ObjetoPersistente{

        /**
         * 
         */
        private static final long serialVersionUID = -4096346535615754341L;

        public abstract TipoEntidadeMensuravel recuperaTipo();
        protected T entidade;
        
        public String toString(){
                return getEntidade().toString();
        }
        
        public boolean equals(Object o){
                return this.toString()==o.toString();
        }
        
        public abstract T getEntidade();
        
        public abstract void setEntidade(T entidade);
        
}
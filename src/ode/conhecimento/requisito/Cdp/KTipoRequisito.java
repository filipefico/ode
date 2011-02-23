package ode.conhecimento.requisito.Cdp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity
public class KTipoRequisito extends Conhecimento{
	private KTipoRequisito superKTipoRequisito;
	private boolean ehFuncional = true;

	public KTipoRequisito() {
    }

	@ManyToOne //(fetch = FetchType.EAGER)
	public KTipoRequisito getSuperKTipoRequisito(){
		return superKTipoRequisito;
	}

	public void setSuperKTipoRequisito(KTipoRequisito parSuperKTipoRequisito){
        superKTipoRequisito = parSuperKTipoRequisito;
	}
	
	public boolean getEhFuncional(){
		return ehFuncional;
	}
	
	public void setEhFuncional(boolean parEhFuncional){
		ehFuncional = parEhFuncional;
	}
	
	public String toString(){
		return super.getNome();
	}
}

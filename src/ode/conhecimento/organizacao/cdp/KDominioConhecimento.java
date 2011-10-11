package ode.conhecimento.organizacao.cdp;

import javax.persistence.Entity;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KDominioConhecimento  extends Conhecimento{
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getNome();
	}
}

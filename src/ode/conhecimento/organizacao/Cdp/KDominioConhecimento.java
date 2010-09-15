package ode.conhecimento.organizacao.Cdp;

import javax.persistence.Entity;

import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity
public class KDominioConhecimento  extends Conhecimento{
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getNome();
	}
}

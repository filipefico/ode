package ode.conhecimento.processo.cdp;

import javax.persistence.Entity;

import ode.conhecimento.principal.cdp.Conhecimento;



@Entity
public class KDominioAplicacao extends Conhecimento  {

	private static final long serialVersionUID = 7215443991537131684L;


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getNome();
	}


	}

package ode.conhecimento.processo.Cdp;

import ode.conhecimento.principal.Cdp.Conhecimento;

//import javax.persistence.Column;
import javax.persistence.Entity;

//import nucleo.comuns.persistencia.ObjetoPersistente;
//ObjetoPersistente


@Entity
public class KDominioAplicacao extends Conhecimento  {

	private static final long serialVersionUID = 7215443991537131684L;


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getNome();
	}


	}

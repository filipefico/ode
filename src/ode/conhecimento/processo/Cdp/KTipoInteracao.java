package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;

import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity
public class KTipoInteracao extends Conhecimento{
	
	public static final String PARALELO_INDEPENDENTE = "Paralelo Independente";
	public static final String PARALELO_DEPENDENTE = "Paralelo Dependente";
	public static final String SEQUENCIAL = "Sequencial";
	public static final String PONTUAL = "Pontual";
	public static final String SOB_DEMANDA = "Sob-Demanda";
	
	public KTipoInteracao() {}

}

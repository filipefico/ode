package ode.gerenciaRequisitos.cdp;

import javax.persistence.Entity;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class Prioridade extends Conhecimento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ALTA = "Alta";
	public static final String MEDIA = "Média";
	public static final String BAIXA = "Baixa";
}

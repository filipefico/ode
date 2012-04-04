package ode.conhecimento.requisito.cdp;

import javax.persistence.Entity;

import ode.conhecimento.principal.cdp.Conhecimento;

@SuppressWarnings("serial")
@Entity
public class TipoRequisito extends Conhecimento{
	public static final String FUNCIONAL = "Requisito Funcional";
	public static final String NAO_FUNCIONAL = "Requisito Não-Funcional";
	public static final String REGRA_NEGOCIO = "Regra de Negócio";
}

package ode.medicao.planejamentoMedicao.cdp;

import javax.persistence.Entity;

@Entity
public class PlanoMedicaoOrganizacao extends PlanoMedicao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -455780193313196683L;

	public String tostString(){
		return "Plano de Medição da Organização - Versão "+getVersao();
	}
	
}

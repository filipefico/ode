package ode.medicao.planejamentoMedicao.cdp;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;


@Entity
public class PlanoMedicaoOrganizacao extends PlanoMedicao{


	public String toString(){
		return "Plano de Medi��o da Organiza��o - Vers�o "+getVersao();
	}
	
}

package ode.medicao.planejamentoMedicao.cdp;

import javax.persistence.Entity;

import ode.controleProjeto.cdp.Projeto;

@Entity
public class PlanoMedicaoProjeto extends PlanoMedicao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3205947357517302230L;
	private Projeto projeto;
	private PlanoMedicaoOrganizacao planoBase;
	
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public PlanoMedicaoOrganizacao getPlanoBase() {
		return planoBase;
	}
	public void setPlanoBase(PlanoMedicaoOrganizacao planoBase) {
		this.planoBase = planoBase;
	}
	
	public String toString(){
		return "Plano de Medição do "+projeto+" - Versão "+getVersao();
	}
}

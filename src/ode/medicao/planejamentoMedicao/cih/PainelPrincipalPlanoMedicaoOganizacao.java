package ode.medicao.planejamentoMedicao.cih;

import java.util.Date;
import java.util.HashSet;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;

public class PainelPrincipalPlanoMedicaoOganizacao extends PainelPrincipalPlanoMedicao{

	@Override
	protected void novo() {
		dbData.setValue(null);
		tbDescricao.setText("");
		ibVersao.setText("");
		cbResponsavel.selecionarPrimeiroElemento();
		compObj.decelecionaTudo();
		medPlan.decelecionaTudo();
		((CtrlPlanoMedicaoOrganizacao)getControlador()).setPlanoMedicao(((CtrlPlanoMedicaoOrganizacao)getControlador()).novoPlanoMedicao());
	}

	@Override
	protected void abrir() {
		((CtrlPlanoMedicaoOrganizacao)getControlador()).abrir();
	}
	
	@Override
	protected void salvar() {
		((CtrlPlanoMedicaoOrganizacao)getControlador()).salvar();
	}
	
	@Override
	protected void deletar() {
		((CtrlPlanoMedicaoOrganizacao)getControlador()).deletar();
	}
	
	@Override
	public void preencherDados(PlanoMedicao objeto) {
		super.preencherDados(objeto);
	}

	@Override
	public void preencherObjetos(PlanoMedicao objeto) {
		super.preencherObjetos(objeto);
	}

	
}

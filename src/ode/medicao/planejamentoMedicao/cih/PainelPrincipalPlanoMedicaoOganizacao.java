package ode.medicao.planejamentoMedicao.cih;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;

public class PainelPrincipalPlanoMedicaoOganizacao extends PainelPrincipalPlanoMedicao{

	@Override
	protected void novo() {
	}

	@Override
	protected void salvar() {
		preencherDados(((CtrlPlanoMedicaoOrganizacao)getControlador()).getPlanoMedicao());
		
	}

	@Override
	protected void abrir() {
		
	}

	private void preencherDados(PlanoMedicaoOrganizacao objeto) {
		objeto.setData(dbData.getValue());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setVersao(Float.parseFloat(ibVersao.getText()));
		objeto.setResponsavel(cbResponsavel.getObjetoSelecionado());
		objeto.setObjsEstrategico(compObj.getEstrategicosSelecionados());
		objeto.setObjsSoftware(compObj.getSoftwareSelecionados());
		objeto.setObjsMedicao(compObj.getMedicaoSelecionados());
		objeto.setNecessidades(compObj.getNecessidadesSelecionadas());
		objeto.setProcessos(compObj.getProcessosSelecionados());
		
	}

	@Override
	protected void deletar() {
		// TODO Auto-generated method stub
		
	}
	
}

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
		dbData.setValue(new Date());
		tbDescricao.setText("");
		ibVersao.setText("");
		cbResponsavel.selecionarPrimeiroElemento();
		//compObj.decelecionaTudo();
		((CtrlPlanoMedicaoOrganizacao)getControlador()).setPlanoMedicao(((CtrlPlanoMedicaoOrganizacao)getControlador()).novoPlanoMedicao());
	}

	@Override
	protected void salvar() {
		((CtrlPlanoMedicaoOrganizacao)getControlador()).salvar();
	}

	@Override
	protected void abrir() {
		((CtrlPlanoMedicaoOrganizacao)getControlador()).abrir();
	}
	
	@Override
	protected void deletar() {
		
	}
	
	public void preencherDados(PlanoMedicao objeto) {
		dbData.setValue(objeto.getData());
		tbDescricao.setText(objeto.getDescricao());
		ibVersao.setText(Float.toString(objeto.getVersao()));
		cbResponsavel.setObjetoSelecionado(objeto.getResponsavel());
		compObj.populaArvore(objeto.getObjsEstrategico(), objeto.getObjsSoftware(), objeto.getObjsMedicao(), objeto.getNecessidades(), objeto.getProcessos());
	}

	public void preencherObjetos(PlanoMedicao objeto) {
		objeto.setData(dbData.getValue());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setVersao(Float.parseFloat(ibVersao.getText()));
		objeto.setResponsavel(cbResponsavel.getObjetoSelecionado());
		objeto.setObjsEstrategico(compObj.getEstrategicosSelecionados());
		objeto.setObjsSoftware(new HashSet(compObj.getSoftwareSelecionados()));
		objeto.setObjsMedicao(new HashSet(compObj.getMedicaoSelecionados()));
		objeto.setNecessidades(new HashSet(compObj.getNecessidadesSelecionadas()));
		objeto.setProcessos(new HashSet(compObj.getProcessosSelecionados()));
		objeto.adicionaMpm(new MedidaPlanoMedicao());
	}

	
}

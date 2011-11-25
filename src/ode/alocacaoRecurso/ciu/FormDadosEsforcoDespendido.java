package ode.alocacaoRecurso.ciu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.alocacaoRecurso.cdp.EsforcoDespendido;

import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

public class FormDadosEsforcoDespendido extends	FormularioDadosCRUD<EsforcoDespendido> {

	private static final long serialVersionUID = 1L;

	
	private Datebox txtData = new Datebox(new Date());
	private Intbox txtQtdHoras = new Intbox();
	private Textbox txtObservacao = new Textbox();
	
	@Override
	public CtrlEsforcoDespendidoCRUD getControlador() {
		return (CtrlEsforcoDespendidoCRUD)super.getControlador();
	}
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> tabs = new ArrayList<NucleoTab>();
		NucleoTab tabDados = new NucleoTab("Dados");
		
		GridDados gridDadosCadastro = new GridDados();
		tabDados.setConteudoTab(gridDadosCadastro);
		
		txtData.setWidth("100px");
		gridDadosCadastro.adicionarLinhaObrigatoria("Data", txtData);
		
		txtQtdHoras.setWidth("100px");
		gridDadosCadastro.adicionarLinhaObrigatoria("Esforço (em horas)", txtQtdHoras);

		txtObservacao.setWidth("98%");
		txtObservacao.setRows(3);
		gridDadosCadastro.adicionarLinhaDupla("Observação", txtObservacao);
		
		tabs.add(tabDados);
		return tabs;
	}

	@Override
	protected void preencherDadosTela(EsforcoDespendido objeto)
			throws NucleoRegraNegocioExcecao {
		txtData.setValue(objeto.getData());
		txtQtdHoras.setValue(objeto.getQtdHoras());
		txtObservacao.setValue(objeto.getObservacao());
	}

	@Override
	protected void preencherDadosObjeto(EsforcoDespendido objeto) {
		objeto.setData(txtData.getValue());
		objeto.setQtdHoras(txtQtdHoras.getValue());
		objeto.setObservacao(txtObservacao.getValue());
	}
	
	@Override
	protected void configurarConstraints() {
		txtData.setConstraint("no empty: Favor informar a Data!");
		txtQtdHoras.setConstraint("no empty: Favor informar a quantidade de horas despendidas!");
	}
}

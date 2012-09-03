package ode.medicao.analiseMedicao.ciu;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.analiseMedicao.cdp.MonitoramentoObjetivo;
import ode.medicao.analiseMedicao.cgt.AplAnaliseMedicao;
import ode.medicao.execucaoMedicao.cdp.Medicao;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

@Component
public class FormMonitoramento extends Vbox {

	private Tabs tabs = new Tabs();
	private Tabpanels tabpanels = new Tabpanels();
	private CtrlMonitoramentoObjetivo ctrl;

	private NucleoCombobox<MedidaPlanoMedicao> ncMedida;
	private Datebox from = new Datebox();
	private Datebox to = new Datebox();
	private NucleoCombobox<Projeto> ncProjeto;
	private Listitem apenasProjeto;
	private NucleoCombobox<RecursoHumano> executor;
	private Textbox resultadoMonitoramento;
	private NucleoCombobox<PlanoMedicao> ncPlano;
	private NucleoCombobox<ObjetivoEstrategico> cbEstrategico;
	private NucleoCombobox<ObjetivoSoftware> cbSoftware;
	private NucleoCombobox<ObjetivoMedicao> cbMedicao;
	private Datebox dataMonitoramento;
	private Tabbox tabbox;

	public FormMonitoramento(CtrlMonitoramentoObjetivo ctrl) {
		this.ctrl = ctrl;
	}

	public FormMonitoramento() {
	}
	
	private class OnSelectPlano implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			cbEstrategico.getItems().clear();
			cbSoftware.getItems().clear();
			cbMedicao.getItems().clear();
			ncMedida.getItems().clear();
			cbEstrategico.setObjetos(ncPlano.getObjetoSelecionado().getObjsEstrategico());
			cbEstrategico.selecionarPrimeiroElemento();
		}
	}
	
	private class OnSelectEstrategico implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			cbSoftware.getItems().clear();
			cbMedicao.getItems().clear();
			ncMedida.getItems().clear();
			cbSoftware.setObjetos(cbEstrategico.getObjetoSelecionado().getObjetivoSoftware());
			cbSoftware.selecionarPrimeiroElemento();
		}
	}
	
	private class OnSelectSoftware implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			cbMedicao.getItems().clear();
			ncMedida.getItems().clear();
			cbMedicao.setObjetos(cbSoftware.getObjetoSelecionado().getObjetivoMedicao());
			cbMedicao.selecionarPrimeiroElemento();
		}
	}
	
	private class OnSelectMedicao implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			ncMedida.getItems().clear();
			ncMedida.setObjetos(getMedidasByObjMedicao());
			ncMedida.selecionarPrimeiroElemento();
		}
	}
	
	private Iterable<MedidaPlanoMedicao> getMedidasByObjMedicao(){
		Set<MedidaPlanoMedicao> medidas = new HashSet<MedidaPlanoMedicao>();
		Set<NecessidadeInformacao> necessidades = cbMedicao.getObjetoSelecionado().getNecessidadeInformacao();
		for(NecessidadeInformacao necInf:necessidades){
			medidas.addAll(necInf.getMedidasNoPlano());
		}
		return medidas;
	}

	public void montar() {

		tabbox = new Tabbox();
		tabs = new Tabs();
		tabpanels = new Tabpanels();

		
		tabbox.setParent(this);
		tabs.setParent(tabbox);
		tabpanels.setParent(tabbox);

		// ///////////////////////////////////////////// DADOS DE MONITORAMENTO

		Tab t1 = new Tab("Dados para Monitoramento");
		Tabpanel tp1 = new Tabpanel();
		t1.setParent(tabs);

		tp1.setParent(tabpanels);
		GridDados grid1 = new GridDados();
		grid1.setParent(tp1);

		Vbox vc = new Vbox();
		Listbox lb = new Listbox();
		lb.setCheckmark(true);
		Listitem todosProjeto = new Listitem("Dados da Organização");
		apenasProjeto = new Listitem("Dados do projeto");
		todosProjeto.setParent(lb);
		apenasProjeto.setParent(lb);
		grid1.adicionarLinhaUnica(vc);
		vc.appendChild(lb);
		ncProjeto = new NucleoCombobox<Projeto>();
		ncProjeto.setObjetos(ctrl.getProjetos());
		vc.appendChild(ncProjeto);
		lb.selectItem(todosProjeto);
		ncProjeto.setVisible(false);
		lb.addEventListener("onSelect", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				ncProjeto.setVisible(apenasProjeto.isSelected());
			}
		});
		
		Groupbox gb1 = new Groupbox();
		gb1.setParent(tp1);
		gb1.appendChild(new Caption("Objetivos e Valores de Refrência"));

		GridDados gd1 = new GridDados();
		gd1.setParent(gb1);

		ncPlano = new NucleoCombobox<PlanoMedicao>();
		ncPlano.setObjetos(ctrl.getPlanos());
		ncPlano.selecionarPrimeiroElemento();
		ncPlano.addEventListener("onSelect", new OnSelectPlano());
		gd1.adicionarLinha("Plano de Medição", ncPlano);

		cbEstrategico = new NucleoCombobox<ObjetivoEstrategico>();
		cbEstrategico.setObjetos(ncPlano.getObjetoSelecionado().getObjsEstrategico());
		cbEstrategico.selecionarPrimeiroElemento();
		cbEstrategico.addEventListener("onSelect", new OnSelectEstrategico());
		gd1.adicionarLinha("Objetivo Estratégico", cbEstrategico);

		cbSoftware = new NucleoCombobox<ObjetivoSoftware>();
		cbSoftware.setObjetos(cbEstrategico.getObjetoSelecionado().getObjetivoSoftware());
		cbSoftware.selecionarPrimeiroElemento();
		cbSoftware.addEventListener("onSelect", new OnSelectSoftware());
		gd1.adicionarLinha("Objetivo de Software", cbSoftware);

		cbMedicao = new NucleoCombobox<ObjetivoMedicao>();
		cbMedicao.setObjetos(cbSoftware.getObjetoSelecionado().getObjetivoMedicao());
		cbMedicao.selecionarPrimeiroElemento();
		cbMedicao.addEventListener("onSelect", new OnSelectMedicao());
		gd1.adicionarLinha("Objetivo de Medição", cbMedicao);

		ncMedida = new NucleoCombobox<MedidaPlanoMedicao>();
		ncMedida.setObjetos(getMedidasByObjMedicao());
		ncMedida.selecionarPrimeiroElemento();
		gd1.adicionarLinha("Indicador", ncMedida);

		Groupbox gb2 = new Groupbox();
		gb2.setParent(tp1);
		gb2.appendChild(new Caption("Seleção dos Dados"));

		GridDados gdGam = new GridDados();
		gdGam.setParent(gb2);

		Hbox subhb = new Hbox();
		gdGam.adicionarLinha("Período", subhb);
		subhb.appendChild(from);
		subhb.appendChild(new Label(" a "));
		subhb.appendChild(to);

		Button gerarRelatorio = new Button("Gerar Gráfico");
		grid1.adicionarLinhaUnica(gerarRelatorio);
		gerarRelatorio.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.gerarGrafico(from.getValue(), to.getValue());
			}
		});

		// ////////////////////////////////////////////// MONITORAR DADOS

		Tab t2 = new Tab("Monitorar Objetivos");
		t2.setParent(tabs);
		Tabpanel tp2 = new Tabpanel();
		tp2.setParent(tabpanels);

		Hbox alto = new Hbox();
		tp2.appendChild(alto);
		GridDados altoEsq = new GridDados();
		Vbox altoDir = new Vbox();
		altoEsq.setParent(alto);
		altoEsq.setWidth("440px");
		altoDir.setParent(alto);
		Vbox baixo = new Vbox();
		tp2.appendChild(baixo);

		altoEsq.adicionarLinha("Data do Monitoramento",	dataMonitoramento = new Datebox());

		executor = new NucleoCombobox<RecursoHumano>();
		executor.setObjetos(ctrl.getRecursosHumanos());
		executor.setWidth("100%");
		altoEsq.adicionarLinha("Realizado por", executor);

		Image img = new Image("/imagens/grafico.gif");
		img.setParent(altoDir);
		img.setWidth("238px");
		img.setHeight("171px");

		baixo.appendChild(new Label("Resultados do Monitoramento"));
		baixo.setWidth("700px");
		baixo.appendChild(resultadoMonitoramento = new Textbox());
		resultadoMonitoramento.setWidth("700px");
		resultadoMonitoramento.setRows(3);

		// ///////////////////////////////////////////// Salvar

		Button salvar = new Button("Salvar");
		Toolbar tb = new Toolbar();
		salvar.setParent(tb);
		tb.setStyle("border:0px;background:white;");
		tb.setAlign("end");
		salvar.addEventListener("onClick",new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.acaoSalvar();
			}
		});
		tb.setParent(this);

	}

	public void preencheFormulario(MonitoramentoObjetivo objeto) {
		dataMonitoramento.setValue(objeto.getData());
		from.setValue(objeto.getInicio());
		to.setValue(objeto.getFim());
		resultadoMonitoramento.setValue(objeto.getResultadoMonitoramento());
		ncMedida.setObjetoSelecionado(objeto.getIndicador());
		cbMedicao.setObjetoSelecionado(objeto.getObjetivo());
		executor.setObjetoSelecionado(objeto.getExecutorMonitoramento());
	}

	public void preencheObjeto(MonitoramentoObjetivo objeto) {
		objeto.setData(dataMonitoramento.getValue());
		objeto.setInicio(from.getValue());
		objeto.setFim(to.getValue());
		objeto.setObjetivo(cbMedicao.getObjetoSelecionado());
		objeto.setResultadoMonitoramento(resultadoMonitoramento.getValue());
		objeto.setIndicador(ncMedida.getObjetoSelecionado());
		objeto.setExecutorMonitoramento(executor.getObjetoSelecionado());
	}

}

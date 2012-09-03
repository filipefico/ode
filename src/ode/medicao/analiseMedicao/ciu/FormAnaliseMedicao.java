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
public class FormAnaliseMedicao extends Vbox{

	private Tabs tabs = new Tabs();
	private Tabpanels tabpanels = new Tabpanels();
	private CtrlAnaliseMedicao ctrl;

	private NucleoCombobox<KMedida> ncMedida;
	private NucleoCombobox<DefinicaoOperacionalMedida> ncDefinicao;
	private Datebox from = new Datebox();
	private Datebox to = new Datebox();
	private NucleoCombobox<Projeto> ncProjeto;
	private Listitem apenasProjeto;
	private NucleoCombobox<RecursoHumano> executor;
	private NucleoCombobox<KAtividade> momento;
	private Textbox resultadoAnalise;
	private Datebox dataAnalise;
	private Tabbox tabbox;

	public FormAnaliseMedicao(CtrlAnaliseMedicao ctrl) {
		this.ctrl = ctrl;
	}

	public FormAnaliseMedicao() {
	}
	
	private class MostraDefinicoes implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			ncDefinicao.setObjetos(ncMedida.getObjetoSelecionado().getDefinicoesMedida());
		}
		
	}

	public void montar() {

		tabbox = new Tabbox();
		tabs = new Tabs();
		tabpanels = new Tabpanels();

		
		tabbox.setParent(this);
		tabs.setParent(tabbox);
		tabpanels.setParent(tabbox);

		// ///////////////////////////////////////////// DADOS DE ANALISE

		Tab t1 = new Tab("Dados para Analise");
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

		ncMedida = new NucleoCombobox<KMedida>();
		ncDefinicao = new NucleoCombobox<DefinicaoOperacionalMedida>();
		ncMedida.setObjetos(ctrl.getMedidas());
		ncMedida.selecionarPrimeiroElemento();
		gd1.adicionarLinha("Medida", ncMedida);
		gd1.addEventListener("onSelect", new MostraDefinicoes());

		ncDefinicao.setObjetos(ncMedida.getObjetoSelecionado().getDefinicoesMedida());
		ncDefinicao.selecionarPrimeiroElemento();
		gd1.adicionarLinha("Definição Operacional", ncDefinicao);
	
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
				ctrl.gerarGrafico(from.getValue(), to.getValue(), ncDefinicao.getObjetoSelecionado());
			}
		});

		// ////////////////////////////////////////////// ANALISAR DADOS

		Tab t2 = new Tab("Analisar Objetivos");
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

		altoEsq.adicionarLinha("Data da Analise",	dataAnalise = new Datebox());

		executor = new NucleoCombobox<RecursoHumano>();
		executor.setObjetos(ctrl.getRecursosHumanos());
		executor.setWidth("100%");
		altoEsq.adicionarLinha("Realizado por", executor);
		
		momento = new NucleoCombobox<KAtividade>();
		momento.setObjetos(ctrl.getAtividades());
		momento.setWidth("100%");
		altoEsq.adicionarLinha("Momento", momento );
		

		Image img = new Image("/imagens/grafico.gif");
		img.setParent(altoDir);
		img.setWidth("238px");
		img.setHeight("171px");
		

		baixo.appendChild(new Label("Resultados da Analise"));
		baixo.setWidth("695px");
		baixo.appendChild(resultadoAnalise = new Textbox());
		resultadoAnalise.setWidth("695px");
		resultadoAnalise.setRows(3);

		/////////////////////////////////////////////// Salvar

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

	public void preencheFormulario(AnaliseMedicao objeto) {
		dataAnalise.setValue(objeto.getData());
		from.setValue(objeto.getInicio());
		to.setValue(objeto.getFim());
		resultadoAnalise.setValue(objeto.getResultadoAnaliseMedicao());
		ncMedida.setObjetoSelecionado(objeto.getMedida());
		ncDefinicao.setObjetoSelecionado(objeto.getDefinicaoOperacional());
		momento.setObjetoSelecionado(objeto.getMomentoAnalise());
		executor.setObjetoSelecionado(objeto.getExecutorAnalise());
	}

	public void preencheObjeto(AnaliseMedicao objeto) {
		objeto.setData(dataAnalise.getValue());
		objeto.setInicio(from.getValue());
		objeto.setFim(to.getValue());
		objeto.setResultadoAnaliseMedicao(resultadoAnalise.getValue());
		objeto.setMedida(ncMedida.getObjetoSelecionado());
		objeto.setDefinicaoOperacional(ncDefinicao.getObjetoSelecionado());
		objeto.setMomentoAnalise(momento.getObjetoSelecionado());
		objeto.setExecutorAnalise(executor.getObjetoSelecionado());
	}
	
	
}

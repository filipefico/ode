package ode.medicao.analiseMedicao.ciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zhtml.H2;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
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

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;

@Controller
public class CtrlAnaliseMedicao extends CtrlBase{

	JanelaSimples jan;
	private Tabbox tabbox = new Tabbox();
	private Tabs tabs = new Tabs();
	private Tabpanels tabpanels = new Tabpanels();
	
	NucleoCombobox<KMedida> ncMedida;
	NucleoCombobox<DefinicaoOperacionalMedida> ncdefinicao;
	Datebox from = new Datebox();
	Datebox to = new Datebox();
	NucleoCombobox<Projeto> ncProjeto;
	Listitem apenasProjeto;
	Hbox hb2 ;
	Datebox dataAnalise;
	NucleoCombobox<RecursoHumano> executor;
	NucleoCombobox<KAtividade> momento;
	Textbox analiseMedicao;
	
	@Autowired
	AplCadastrarKMedida aplmedida;
	@Autowired
	AplCadastrarProjeto aplProjeto;
	@Autowired
	AplCadastrarRecursoHumano aplRH;
	@Autowired
	AplCadastrarKAtividade aplativ;
	
	
	private class OnSelectMedida implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			ncdefinicao.getItems().clear();
			ncdefinicao.setObjetos(ncMedida.getObjetoSelecionado().getDefinicoesMedida());
			ncdefinicao.selecionarPrimeiroElemento();
		}
		
	}
	
	
	@Override
	public void iniciar() {
		tabbox = new Tabbox();
		tabs = new Tabs();
		tabpanels = new Tabpanels();
		
		jan = factoryJanelaSimples();
		jan.setTitle("Analise de Medição");
		jan.setWidth("715px");
		tabbox.setParent(jan);
		tabs.setParent(tabbox);
		tabpanels.setParent(tabbox);
		
		///////////////////////////////////////////////     SELECIONAR DADOS PARA ANALISE
		
		Tab t1 = new Tab("Selecionar Dados para Análise");
		Tabpanel tp1 = new Tabpanel();
		t1.setParent(tabs);
		
		tp1.setParent(tabpanels);
		GridDados p1 = new GridDados();
		p1.setParent(tp1);
		
		ncMedida = new NucleoCombobox<KMedida>();
		p1.adicionarLinha("Medida", ncMedida);
		ncMedida.setObjetos(aplmedida.recuperarTodos());
		ncMedida.selecionarPrimeiroElemento();
		ncMedida.addEventListener("onSelect", new OnSelectMedida());
		
		ncdefinicao = new NucleoCombobox<DefinicaoOperacionalMedida>();
		p1.adicionarLinha("Definição Operacional", ncdefinicao);
		ncdefinicao.setObjetos(ncMedida.getObjetoSelecionado().getDefinicoesMedida());
		ncdefinicao.selecionarPrimeiroElemento();
		
		Hbox subhb = new Hbox();
		p1.adicionarLinha("Período", subhb);
		subhb.appendChild(from);
		subhb.appendChild(new Label(" a "));
		subhb.appendChild(to);
		
		Vbox vc = new Vbox();
		Listbox lb = new Listbox();
		lb.setCheckmark(true);
		Listitem todosProjeto = new Listitem("Dados de todos os projetos");
		apenasProjeto = new Listitem("Dados de apenas um projeto");
		todosProjeto.setParent(lb);
		apenasProjeto.setParent(lb);
		p1.adicionarLinhaUnica(vc);
		vc.appendChild(lb);
		hb2 = new Hbox();
		hb2.appendChild(new Label("Projeto"));
		ncProjeto = new NucleoCombobox<Projeto>();
		ncProjeto.setObjetos(aplProjeto.recuperarTodos());
		hb2.appendChild(ncProjeto);
		vc.appendChild(hb2);
		lb.selectItem(todosProjeto);
		hb2.setVisible(false);
		lb.addEventListener("onSelect", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				hb2.setVisible(apenasProjeto.isSelected());
			}
		});
		
		////////////////////////////////////////////////    ANALISAR DADOS
		
		Tab t2 = new Tab("Analisar Dados");
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
		
		altoEsq.adicionarLinha("Data da Análise de Medição", dataAnalise = new Datebox());
		
		executor = new NucleoCombobox<RecursoHumano>();
		executor.setObjetos(aplRH.recuperarTodos());
		executor.setWidth("100%");
		altoEsq.adicionarLinha("Executor da Análise de Medição", executor);
		
		momento = new NucleoCombobox<KAtividade>();
		momento.setObjetos(aplativ.recuperarTodos());
		altoEsq.adicionarLinha("Momento da Análise de Medição", momento);
		momento.setWidth("100%");
		
		Image img = new Image("/imagens/grafico.gif");
		img.setParent(altoDir);
		img.setWidth("238px");
		img.setHeight("171px");
		
		baixo.appendChild(new Label("Resultado da Análise"));
		baixo.setWidth("700px");
		baixo.appendChild(analiseMedicao = new Textbox());
		analiseMedicao.setWidth("700px");
		analiseMedicao.setRows(3);
		
		///////////////////////////////////////////////    Salvar
		
		Button salvar = new Button("Salvar");
		Toolbar tb = new Toolbar();
		salvar.setParent(tb);
		tb.setStyle("border:0px;background:white;");
		tb.setAlign("end");
		
		tb.setParent(jan);
		
		
		jan.mostrar();
	}
	
}

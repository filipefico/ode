package ode.medicao.analiseMedicao.ciu;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;


@Controller
public class CtrlMonitoramentoObjetivo extends CtrlBase{
	
	JanelaSimples jan;
	private Tabbox tabbox = new Tabbox();
	private Tabs tabs = new Tabs();
	private Tabpanels tabpanels = new Tabpanels();
	
	NucleoCombobox<KMedida> ncMedida;
	Datebox from = new Datebox();
	Datebox to = new Datebox();
	NucleoCombobox<Projeto> ncProjeto;
	NucleoCombobox<Projeto> ncProjeto2;
	Listitem apenasProjeto;
	Listitem apenasProjeto2;
	Hbox hb2 ;
	Hbox hb22 ;
	Datebox dataMonitoramento;
	NucleoCombobox<RecursoHumano> executor;
	NucleoCombobox<KAtividade> momento;
	Textbox analiseMedicao;
	NucleoCombobox<PlanoMedicao> ncPlano;
	NucleoCombobox<ObjetivoEstrategico> cbEstrategico;
	NucleoCombobox<ObjetivoSoftware> cbSoftware ;
	NucleoCombobox<ObjetivoMedicao> cbMedicao;
	
	private class OnSelectEstrategico implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			cbSoftware.getItems().clear();
			cbSoftware.setObjetos(cbEstrategico.getObjetoSelecionado().getObjetivoSoftware());
			cbSoftware.selecionarPrimeiroElemento();
			cbMedicao.selecionarPrimeiroElemento();
		}
		
	}
	private class OnSelectSoftware implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			cbMedicao.getItems().clear();
			cbMedicao.setObjetos(cbSoftware.getObjetoSelecionado().getObjetivoMedicao());
			cbMedicao.selecionarPrimeiroElemento();
		}
		
	}
	private class OnSelectMedicao implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			/*TANTO O OBJETIVO DE MEDICAO QNTO O PLANO DE MEDICAO
			 * AO SEREM SELECIONADOS DEVEM MONSTRAR MEDIDAS ESPECIFICAS*/
		}
		
	}
	
	
	@Autowired
	AplCadastrarKMedida aplmedida;
	@Autowired
	AplCadastrarProjeto aplProjeto;
	@Autowired
	AplCadastrarRecursoHumano aplRH;
	@Autowired
	AplCadastrarKAtividade aplativ;
	@Autowired
	AplCadastrarObjetivoEstrategico aplEstrategico;
	
	@Override
	public void iniciar() {
		
		/////////////////////////////PLANOS
		Set<PlanoMedicao> planos = new HashSet<PlanoMedicao>();
		PlanoMedicaoOrganizacao p = new PlanoMedicaoOrganizacao();
		p.setVersao((float)1.2);
		planos.add(p);
		p = new PlanoMedicaoOrganizacao();
		p.setVersao((float)1.3);
		planos.add(p);
		p = new PlanoMedicaoOrganizacao();
		p.setVersao((float)1.4);
		planos.add(p);
		////////////////////////////
		
		tabbox = new Tabbox();
		tabs = new Tabs();
		tabpanels = new Tabpanels();
		
		jan = factoryJanelaSimples();
		jan.setTitle("Monitoramento de Objetivos");
		jan.setWidth("715px");
		tabbox.setParent(jan);
		tabs.setParent(tabbox);
		tabpanels.setParent(tabbox);
		
		///////////////////////////////////////////////     SELECIONAR DADOS PARA MONITORAMENTO
		
		Tab t1 = new Tab("Selecionar Referências e Objetivos para Monitoramento");
		Tabpanel tp1 = new Tabpanel();
		t1.setParent(tabs);
		
		tp1.setParent(tabpanels);
		GridDados p1 = new GridDados();
		p1.setParent(tp1);
		
		Vbox vc = new Vbox();
		Listbox lb = new Listbox();
		lb.setCheckmark(true);
		Listitem todosProjeto = new Listitem("Dados da Organização");
		apenasProjeto = new Listitem("Dados do projeto");
		todosProjeto.setParent(lb);
		apenasProjeto.setParent(lb);
		p1.adicionarLinhaUnica(vc);
		vc.appendChild(lb);
		ncProjeto = new NucleoCombobox<Projeto>();
		ncProjeto.setObjetos(aplProjeto.recuperarTodos());
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
		ncPlano.setObjetos(planos);
		ncPlano.selecionarPrimeiroElemento();
		gd1.adicionarLinha("Plano de Medção", ncPlano);
		
		cbEstrategico = new NucleoCombobox<ObjetivoEstrategico>();
		cbEstrategico.setObjetos(aplEstrategico.recuperarTodos());
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
		
		ncMedida = new NucleoCombobox<KMedida>();
		ncMedida.setObjetos(aplmedida.recuperarTodos());
		ncMedida.selecionarPrimeiroElemento();
		gd1.adicionarLinha("Medida", ncMedida);
		
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
		
		Vbox vc2 = new Vbox();
		Listbox lb2 = new Listbox();
		lb2.setCheckmark(true);
		Listitem todosProjeto2 = new Listitem("Dados de todos os projetos");
		apenasProjeto2 = new Listitem("Dados de apenas um projeto");
		todosProjeto2.setParent(lb2);
		apenasProjeto2.setParent(lb2);
		gdGam.adicionarLinhaUnica(vc2);
		vc2.appendChild(lb2);
		hb22 = new Hbox();
		hb22.appendChild(new Label("Projeto"));
		ncProjeto2 = new NucleoCombobox<Projeto>();
		ncProjeto2.setObjetos(aplProjeto.recuperarTodos());
		hb22.appendChild(ncProjeto);
		vc2.appendChild(hb22);
		lb2.selectItem(todosProjeto2);
		hb22.setVisible(false);
		lb2.addEventListener("onSelect", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				hb22.setVisible(apenasProjeto2.isSelected());
			}
		});
		
		
		////////////////////////////////////////////////    MONITORAR DADOS
		
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
		
		altoEsq.adicionarLinha("Data do Monitoramento", dataMonitoramento = new Datebox());
		
		executor = new NucleoCombobox<RecursoHumano>();
		executor.setObjetos(aplRH.recuperarTodos());
		executor.setWidth("100%");
		altoEsq.adicionarLinha("Realizado por", executor);
		
		Image img = new Image("/imagens/grafico.gif");
		img.setParent(altoDir);
		img.setWidth("238px");
		img.setHeight("171px");
		
		baixo.appendChild(new Label("Resultados do Monitoramento"));
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

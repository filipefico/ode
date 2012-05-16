package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanAvaliarItemConhecimento extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Label labelAutorValor = new Label();
	Decimalbox textboxCorrecao = new Decimalbox();
	Decimalbox textboxCompletude = new Decimalbox();
	Decimalbox textboxConsistencia = new Decimalbox();
	Decimalbox textboxUtilidade = new Decimalbox();
	Decimalbox textboxAplicabilidade = new Decimalbox();
	Listcell listcellTextboxMediaNotas = new Listcell();
	Textbox textboxParecer = new Textbox("");
	Combobox comboboxResultadoFinal = new Combobox();
	
	JanAvaliarItemConhecimento(CtrlGerenciaConhecimento ctrl){
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanAvaliarItemConhecimento();
		
	}
	
	public void criarJanAvaliarItemConhecimento(){
		
		this.setTitle("Avaliar Item de Conhecimento");
		this.setBorder("normal");
		
		//////////////////////////////////////////
		// Criar a Tab Notas
		//////////////////////////////////////////
		
		Vbox vboxNotas = new Vbox();
		
		Tabbox tabboxNotas = new Tabbox();
		Tabs tabsNotas = new Tabs();
		Tab tabNotas = new Tab("Notas");
		
		tabNotas.setParent(tabsNotas);
		
		Tabpanels tabpanelsNotas = new Tabpanels();
		Tabpanel tabpanelNotas = new Tabpanel();
		
		Label labelAutor = new Label();
		labelAutorValor.setValue("PEGA O AUTOR DO ITEM DE CONHECIMENTO");
		Label labelDataAvaliacao = new Label();
		
		labelAutor.setValue("Autor: " + labelAutorValor.getValue());
		labelAutor.setParent(vboxNotas);
		
		//A data é a atual, assim não precisa ser declarada como global
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		labelDataAvaliacao.setValue("Data da Avaliação: " + formatador.format(data));
		labelDataAvaliacao.setParent(vboxNotas);
		
		// criar o listbox para as notas
		Listbox listboxNotas = new Listbox();
		listboxNotas.setSizedByContent(true);
		listboxNotas.setWidth("550px");
		
		Listhead listheadNotas = new Listhead();
		Listheader listheaderCriterio = new Listheader("Critério");
		Listheader listheaderNota = new Listheader("Nota");

		
		listheaderCriterio.setParent(listheadNotas);
		listheaderNota.setParent(listheadNotas);
		listheadNotas.setParent(listboxNotas);
		
		Listitem listitemCorrecao = new Listitem();
		Listcell listcellCorrecao = new Listcell("Correção:");
		Listcell listcellTextboxCorrecao = new Listcell();
		
		textboxCorrecao.setParent(listcellTextboxCorrecao);
		listcellCorrecao.setParent(listitemCorrecao);
		listcellTextboxCorrecao.setParent(listitemCorrecao);
		listitemCorrecao.setParent(listboxNotas);
		
		Listitem listitemCompletude = new Listitem();
		Listcell listcellCompletude = new Listcell("Completude:");
		Listcell listcellTextboxCompletude = new Listcell();
				
		textboxCompletude.setParent(listcellTextboxCompletude);
		listcellCompletude.setParent(listitemCompletude);
		listcellTextboxCompletude.setParent(listitemCompletude);
		listitemCompletude.setParent(listboxNotas);
		
		Listitem listitemConsistencia = new Listitem();
		Listcell listcellConsistencia = new Listcell("Consistência:");
		Listcell listcellTextboxConsistencia = new Listcell();
		
		textboxConsistencia.setParent(listcellTextboxConsistencia);
		listcellConsistencia.setParent(listitemConsistencia);
		listcellTextboxConsistencia.setParent(listitemConsistencia);
		listitemConsistencia.setParent(listboxNotas);
		
		Listitem listitemUtilidade = new Listitem();
		Listcell listcellUtilidade = new Listcell("Utilidade:");
		Listcell listcellTextboxUtilidade = new Listcell();
		
		textboxUtilidade.setParent(listcellTextboxUtilidade);
		listcellUtilidade.setParent(listitemUtilidade);
		listcellTextboxUtilidade.setParent(listitemUtilidade);
		listitemUtilidade.setParent(listboxNotas);
		
		Listitem listitemAplicabilidade = new Listitem();
		Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
		Listcell listcellTextboxAplicabilidade = new Listcell();
		
		textboxAplicabilidade.setParent(listcellTextboxAplicabilidade);
		listcellAplicabilidade.setParent(listitemAplicabilidade);
		listcellTextboxAplicabilidade.setParent(listitemAplicabilidade);
		listitemAplicabilidade.setParent(listboxNotas);
		
		Listitem listitemMediaNotas = new Listitem();
		Listcell listcellMediaNotas = new Listcell("Média das Notas:");
		listcellTextboxMediaNotas.setValue("MEDIA DAS NOTAS");
		
		listcellMediaNotas.setParent(listitemMediaNotas);
		listcellTextboxMediaNotas.setParent(listitemMediaNotas);
		listitemMediaNotas.setParent(listboxNotas);
		
		listboxNotas.setParent(vboxNotas);
		vboxNotas.setParent(tabpanelNotas);
		
		tabpanelNotas.setParent(tabpanelsNotas);
		
		tabsNotas.setParent(tabboxNotas);
		tabpanelsNotas.setParent(tabboxNotas);
		
		tabboxNotas.setParent(this);
		
		//////////////////////////////////////////
		// criar Tab Resultado
		//////////////////////////////////////////
		
		Hbox hboxResultado = new Hbox();
		
		Tabbox tabboxResultado = new Tabbox();
		
		Tabs tabsResultado = new Tabs();
		Tab tabResultado = new Tab("Resultado");
		
		tabResultado.setParent(tabsResultado);
		tabsResultado.setParent(tabboxResultado);
		
		Tabpanels tabpanelsResultado = new Tabpanels();
		Tabpanel tabpanelResultado = new Tabpanel();
		
		tabpanelResultado.setParent(tabpanelsResultado);
		tabpanelsResultado.setParent(tabboxResultado);
		
		Vbox vboxParecer = new Vbox();
		
		Label labelParecer = new Label("Parecer: ");
		textboxParecer.setRows(3);
		textboxParecer.setWidth("350px");
		
		labelParecer.setParent(vboxParecer);
		textboxParecer.setParent(vboxParecer);
		
		Vbox vboxResultadoFinal = new Vbox();
		
		Label labelResultadoFinal = new Label("Resultado Final:");
		comboboxResultadoFinal.setWidth("180px");
		Comboitem comboitemAprovado = new Comboitem("Aprovado");
		Comboitem comboitemAprovadoComModificacoes = new Comboitem("Aprovado com Modificações");
		Comboitem comboitemNaoAprovado = new Comboitem("Não Aprovado");
		Comboitem comboitemIndefinido = new Comboitem("Indefinido");
		
		comboitemAprovado.setParent(comboboxResultadoFinal);
		comboitemAprovadoComModificacoes.setParent(comboboxResultadoFinal);
		comboitemNaoAprovado.setParent(comboboxResultadoFinal);
		comboitemIndefinido.setParent(comboboxResultadoFinal);
		
		labelResultadoFinal.setParent(vboxResultadoFinal);
		comboboxResultadoFinal.setParent(vboxResultadoFinal);
		
		vboxParecer.setParent(hboxResultado);
		vboxResultadoFinal.setParent(hboxResultado);
		
		hboxResultado.setParent(tabpanelResultado);
		
		tabboxResultado.setParent(this);
		
		//////////////////////////////////////////
		// Criar buttons
		//////////////////////////////////////////
		
		Button botaoSalvar = new Button("Salvar");
		Button botaoCancelar = new Button("Cancelar");
		Toolbar toolbarInferior = new Toolbar();

		botaoSalvar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

			}
		});

		botaoCancelar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub


			}
		});

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoSalvar);
		toolbarInferior.appendChild(botaoCancelar);

		toolbarInferior.setParent(this);
	}
	
	
}

package ode.gerenciaConhecimento.ciu;

import java.util.Collection;

import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.Tema;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanPaginasAmarelasBuscarPessoas extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Listbox listbox = new Listbox();
	Textbox textboxNome;
	
	Listbox listboxTemasRelacionados = new Listbox();
	Listbox listboxProjetosRelacionados = new Listbox();
	Listbox listboxAtividadesRelacionadas = new Listbox();
	
	public JanPaginasAmarelasBuscarPessoas(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanPaginasAmarelasBuscarPessoas();
		
	}
	
	public void criarJanPaginasAmarelasBuscarPessoas(){
		
		this.setTitle("Páginas Amarelas");
		this.setBorder("normal");
		
		Tabbox tabbox = new Tabbox();
		Tabs tabs = new Tabs();
		
		Tab tabBuscarPessoas = new Tab("Buscar Pessoas");
		tabBuscarPessoas.setParent(tabs);
		tabs.setParent(tabbox);
		
		Tabpanels tabpanels = new Tabpanels();
		Tabpanel tabpanelBuscarPessoas = new Tabpanel();
		
		listbox.setHeight("300px");
		listbox.setSizedByContent(true);
		
		//////////////////////////////////////////
		// linha nome
		//////////////////////////////////////////
		Listitem listitemNome = new Listitem();
		Listcell listcellNome = new Listcell("Nome: ");
		Listcell listcellTextboxNome = new Listcell();
		textboxNome = new Textbox("");
		
		listcellNome.setParent(listitemNome);
		textboxNome.setParent(listcellTextboxNome);
		listcellTextboxNome.setParent(listitemNome);
		listitemNome.setParent(listbox);
		
		//////////////////////////////////////////
		// linha Competencias
		//////////////////////////////////////////
		Listitem listitemCompetencias = new Listitem();
		Listcell listcellCompetencias = new Listcell("Competências: ");
		Listcell listcellCompetenciasValor = new Listcell();
		
		listcellCompetencias.setParent(listitemCompetencias);
		listcellCompetenciasValor.setParent(listitemCompetencias);
		listitemCompetencias.setParent(listbox);
		
		//////////////////////////////////////////
		// linha Projetos
		//////////////////////////////////////////
		Listitem listitemProjetos = new Listitem();
		Listcell listcellProjetos = new Listcell("Projetos: ");
		Listcell listcellProjetosValor = new Listcell();
		
		// Preenche projetos no listbox
		listboxProjetosRelacionados.setMultiple(true);
		listboxProjetosRelacionados.setCheckmark(true);

		Listhead colunasProjetosRelacionados = new Listhead();
		Listheader colunaNome = new Listheader("Nome");
		
		colunaNome.setParent(colunasProjetosRelacionados);
		colunasProjetosRelacionados.setParent(listboxProjetosRelacionados);

		listboxProjetosRelacionados.setMultiple(true);
		listboxProjetosRelacionados.setCheckmark(true);

		Collection<Projeto> projetos = ctrlGerenciaConhecimento.aplCadastrarProjeto.recuperarTodos();
		for (Projeto projeto : projetos){
			Listitem listitem = new Listitem(projeto.getNome());
			listitem.setValue(projeto);
			listitem.setParent(listboxProjetosRelacionados);
		}
		
		listcellProjetos.setParent(listitemProjetos);
		listboxProjetosRelacionados.setParent(listcellProjetosValor);
		listcellProjetosValor.setParent(listitemProjetos);
		listitemProjetos.setParent(listbox);
		
		//////////////////////////////////////////
		// linha Atividades
		//////////////////////////////////////////
		Listitem listitemAtividades = new Listitem();
		Listcell listcellAtividades = new Listcell("Atividades: ");
		Listcell listcellAtividadesValor = new Listcell();
		
		// Preenche atividades no listbox
		listboxAtividadesRelacionadas.setMultiple(true);
		listboxAtividadesRelacionadas.setCheckmark(true);

		Listhead colunasAtividadesRelacionadas = new Listhead();
		Listheader colunaTitulo = new Listheader("Nome");
		
		colunaTitulo.setParent(colunasAtividadesRelacionadas);
		colunasAtividadesRelacionadas.setParent(listboxAtividadesRelacionadas);

		listboxAtividadesRelacionadas.setMultiple(true);
		listboxAtividadesRelacionadas.setCheckmark(true);
		
		Collection<KAtividade> atividades = ctrlGerenciaConhecimento.aplCadastrarKAtividade.recuperarTodos();
		for (KAtividade atividade : atividades){
			Listitem listitem = new Listitem(atividade.getNome());
			listitem.setValue(atividade);
			listitem.setParent(listboxAtividadesRelacionadas);
		}
		
		listcellAtividades.setParent(listitemAtividades);
		listboxAtividadesRelacionadas.setParent(listcellAtividadesValor);
		listcellAtividadesValor.setParent(listitemAtividades);
		listitemAtividades.setParent(listbox);
		
		//////////////////////////////////////////
		// linha Funções
		//////////////////////////////////////////
		Listitem listitemFuncoes = new Listitem();
		Listcell listcellFuncoes = new Listcell("Funções: ");
		Listcell listcellFuncoesValor = new Listcell();
		
		listcellFuncoes.setParent(listitemFuncoes);
		listcellFuncoesValor.setParent(listitemFuncoes);
		listitemFuncoes.setParent(listbox);
		
		//////////////////////////////////////////
		// linha Temas
		//////////////////////////////////////////
		Listitem listitemTemas = new Listitem();
		Listcell listcellTemas = new Listcell("Temas: ");
		Listcell listcellTemasValor = new Listcell();
		
		Listhead colunasTemasRelacionados = new Listhead();
		Listheader colunaTituloTema = new Listheader("Nome");

		colunaTituloTema.setParent(colunasTemasRelacionados);
		colunasTemasRelacionados.setParent(listboxTemasRelacionados);
		
		listboxTemasRelacionados.setMultiple(true);
		listboxTemasRelacionados.setCheckmark(true);

		// Preenche temas no listbox
		Collection<Tema> temas = ctrlGerenciaConhecimento.aplCadastrarTema.recuperarTodos();
		for (Tema tema : temas){
			Listitem listitem = new Listitem(tema.getNome());
			listitem.setValue(tema);
			listitem.setParent(listboxTemasRelacionados);
		}
		
		listcellTemas.setParent(listitemTemas);
		listboxTemasRelacionados.setParent(listcellTemasValor);
		listcellTemasValor.setParent(listitemTemas);
		listitemTemas.setParent(listbox);
		
		listbox.setParent(tabpanelBuscarPessoas);
		tabpanelBuscarPessoas.setParent(tabpanels);
		tabpanels.setParent(tabbox);
		
		tabbox.setParent(this);
		
		//botões
		Button botaoBuscar = new Button("Buscar");
		Button botaoCancelar = new Button("Cancelar");
		Toolbar toolbarInferior = new Toolbar();

		botaoBuscar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
			}
		});
		

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoBuscar);
		toolbarInferior.appendChild(botaoCancelar);

		toolbarInferior.setParent(this);
		
	}
	
}

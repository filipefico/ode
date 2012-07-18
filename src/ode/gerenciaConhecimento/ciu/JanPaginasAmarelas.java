package ode.gerenciaConhecimento.ciu;

import java.util.Collection;

import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.Tema;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
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

public class JanPaginasAmarelas extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Tabbox tabboxBuscarPessoas = new Tabbox();
	Tabbox tabboxResultado = new Tabbox();
	
	Listbox listboxProjetosRelacionados = new Listbox();
	Listbox listboxTemasRelacionados = new Listbox();
	Listbox listboxItensCriados = new Listbox();
	Listbox listboxItensAvaliados = new Listbox();
	Listbox listboxItensValorados = new Listbox();
	Listbox listboxPessoasEncontradas = new Listbox();
	
	Textbox textboxNome;
	
	public JanPaginasAmarelas(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanPaginasAmarelas();
	}
	
	public void criarTabboxBuscarPessoas(){
		
		Tabs tabsBuscarPessoas = new Tabs();
		Tab tabBuscarPessoas = new Tab("Buscar Pessoas");
		
		tabBuscarPessoas.setParent(tabsBuscarPessoas);
		tabsBuscarPessoas.setParent(tabboxBuscarPessoas);
		
		Tabpanels tabpanelsBuscarPessoas = new Tabpanels();
		Tabpanel tabpanelBuscarPessoas = new Tabpanel();
		
		tabpanelBuscarPessoas.setParent(tabpanelsBuscarPessoas);
		tabpanelsBuscarPessoas.setParent(tabboxBuscarPessoas);
		
		Vbox vboxBuscarPessoas = new Vbox();
		Hbox hboxBuscarPessoas_1 = new Hbox();
		Hbox hboxBuscarPessoas_2 = new Hbox();
		
		hboxBuscarPessoas_1.setParent(vboxBuscarPessoas);
		hboxBuscarPessoas_2.setParent(vboxBuscarPessoas);
		vboxBuscarPessoas.setParent(tabpanelBuscarPessoas);
		
		/////////////////////////////////
		//nome
		/////////////////////////////////
		Vbox vboxNome = new Vbox();
		Label labelNome = new Label("Nome:");
		
		textboxNome = new Textbox();
		textboxNome.setWidth("200px");
		
		labelNome.setParent(vboxNome);
		textboxNome.setParent(vboxNome);
		vboxNome.setParent(hboxBuscarPessoas_1);
		
		/////////////////////////////////
		//projetos
		/////////////////////////////////
		Vbox vboxProjetos = new Vbox();
		Label labelProjetos = new Label("Projetos:");
		
		listboxProjetosRelacionados.setMultiple(true);
		listboxProjetosRelacionados.setCheckmark(true);
		//listboxProjetosRelacionados.setHeight("140px");
		//listboxProjetosRelacionados.setSizedByContent(true);
		listboxProjetosRelacionados.setWidth("200px");
		listboxProjetosRelacionados.setRows(2);

		Listhead colunasProjetosRelacionados = new Listhead();
		Listheader colunaNome = new Listheader("Nome");

		colunaNome.setParent(colunasProjetosRelacionados);
		colunasProjetosRelacionados.setParent(listboxProjetosRelacionados);

		// Adicionar itens no listbox projeto
		Collection<Projeto> projetos = ctrlGerenciaConhecimento.aplCadastrarProjeto.recuperarTodos();
		for (Projeto projeto : projetos) {
			Listitem listitem = new Listitem(projeto.getNome());
			listitem.setValue(projeto);
			listitem.setParent(listboxProjetosRelacionados);
		}

		labelProjetos.setParent(vboxProjetos);
		listboxProjetosRelacionados.setParent(vboxProjetos);
		vboxProjetos.setParent(hboxBuscarPessoas_1);
		
		/////////////////////////////////
		//temas
		/////////////////////////////////
		Vbox vboxTemas = new Vbox();
		Label labelTemas = new Label("Temas de interesse:");
		
		listboxTemasRelacionados.setMultiple(true);
		listboxTemasRelacionados.setCheckmark(true);
	//	listboxTemasRelacionados.setHeight("140px");
		listboxTemasRelacionados.setWidth("200px");
		listboxTemasRelacionados.setRows(2);

		Listhead colunasTemasRelacionados = new Listhead();
		Listheader colunaTitulo1 = new Listheader("Título");

		colunaTitulo1.setParent(colunasTemasRelacionados);
		colunasTemasRelacionados.setParent(listboxTemasRelacionados);
	
		// Preenche temas no listbox
		Collection<Tema> temas = ctrlGerenciaConhecimento.aplCadastrarTema.recuperarTodos();
		for(Tema tema : temas){
			Listitem listitem = new Listitem(tema.getNome());
			listitem.setValue(tema);
			listitem.setParent(listboxTemasRelacionados);
		}

		labelTemas.setParent(vboxTemas);
		listboxTemasRelacionados.setParent(vboxTemas);
		vboxTemas.setParent(hboxBuscarPessoas_1);
		
		/////////////////////////////////
		//itens criados
		/////////////////////////////////
		Vbox vboxItensCriados = new Vbox();
		Label labelItensCriados = new Label("Itens Criados:");

		listboxItensCriados.setMultiple(true);
		listboxItensCriados.setCheckmark(true);
	//	listboxItensCriados.setHeight("140px");
		listboxItensCriados.setWidth("200px");
		listboxItensCriados.setRows(2);

		Listhead colunasItensCriados = new Listhead();
		Listheader colunaTitulo2 = new Listheader("Título");
		
		colunaTitulo2.setParent(colunasItensCriados);
		colunasItensCriados.setParent(listboxItensCriados);

		// Preenche itens no listbox
		Collection<ItemConhecimento> itensCriados = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itensCriados){
			Listitem listitem = new Listitem(item.getTitulo());
			listitem.setValue(item);
			listitem.setParent(listboxItensCriados);
		}
		
		labelItensCriados.setParent(vboxItensCriados);
		listboxItensCriados.setParent(vboxItensCriados);
		vboxItensCriados.setParent(hboxBuscarPessoas_2);
		
		/////////////////////////////////
		//itens Avaliados
		/////////////////////////////////
		Vbox vboxItensAvaliados = new Vbox();
		Label labelItensAvaliados = new Label("Itens Avaliados:");

		listboxItensAvaliados.setMultiple(true);
		listboxItensAvaliados.setCheckmark(true);
	//	listboxItensAvaliados.setHeight("140px");
		listboxItensAvaliados.setWidth("200px");
		listboxItensAvaliados.setRows(2);

		Listhead colunasItensAvaliados = new Listhead();
		Listheader colunaTitulo3 = new Listheader("Título");
		
		colunaTitulo3.setParent(colunasItensAvaliados);
		colunasItensAvaliados.setParent(listboxItensAvaliados);

		// Preenche itens no listbox
		Collection<ItemConhecimento> itensAvaliados = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itensAvaliados){
			Listitem listitem = new Listitem(item.getTitulo());
			listitem.setValue(item);
			listitem.setParent(listboxItensAvaliados);
		}
		
		labelItensAvaliados.setParent(vboxItensAvaliados);
		listboxItensAvaliados.setParent(vboxItensAvaliados);
		vboxItensAvaliados.setParent(hboxBuscarPessoas_2);
		
		/////////////////////////////////
		//itens Valorados
		/////////////////////////////////
		Vbox vboxItensValorados = new Vbox();
		Label labelItensValorados = new Label("Itens Valorados:");

		listboxItensValorados.setMultiple(true);
		listboxItensValorados.setCheckmark(true);
	//	listboxItensValorados.setHeight("140px");
		listboxItensValorados.setWidth("200px");
		listboxItensValorados.setRows(2);
		

		Listhead colunasItensValorados = new Listhead();
		Listheader colunaTitulo4 = new Listheader("Título");
		
		colunaTitulo4.setParent(colunasItensValorados);
		colunasItensValorados.setParent(listboxItensValorados);

		// Preenche itens no listbox
		Collection<ItemConhecimento> itensValorados = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itensValorados){
			Listitem listitem = new Listitem(item.getTitulo());
			listitem.setValue(item);
			listitem.setParent(listboxItensValorados);
		}
		
		labelItensValorados.setParent(vboxItensValorados);
		listboxItensValorados.setParent(vboxItensValorados);
		vboxItensValorados.setParent(hboxBuscarPessoas_2);
		
		/////////////////////////////////
		//Botao Buscar
		/////////////////////////////////
		Button botaoBuscar = new Button("Buscar");
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

		toolbarInferior.setParent(tabpanelBuscarPessoas);
		
	}
	
	public void criarTabboxResultado(){
		
		Tabs tabsResultado = new Tabs();
		Tab tabResultado = new Tab("Resultado");
		
		tabResultado.setParent(tabsResultado);
		tabsResultado.setParent(tabboxResultado);
		
		Tabpanels tabpanelsResultado = new Tabpanels();
		Tabpanel tabpanelResultado = new Tabpanel();
		
		tabpanelResultado.setParent(tabpanelsResultado);
		tabpanelsResultado.setParent(tabboxResultado);
		
		Vbox vboxResultado = new Vbox();
		vboxResultado.setParent(tabpanelResultado);
		
		Label labelQtdePessoasEncontradas = new Label("Quantidade de pessoas encontradas: xxx");
		labelQtdePessoasEncontradas.setParent(vboxResultado);
		
		listboxPessoasEncontradas.setMultiple(true);
		listboxPessoasEncontradas.setCheckmark(true);
		
		Listhead colunasPessoasEncontradas = new Listhead();
		Listheader colunaNome = new Listheader("Nome");
		Listheader colunaContato = new Listheader("Contato");
		
		colunaNome.setParent(colunasPessoasEncontradas);
		colunaContato.setParent(colunasPessoasEncontradas);
		colunasPessoasEncontradas.setParent(listboxPessoasEncontradas);
		
		listboxPessoasEncontradas.setParent(vboxResultado);
		
		Button botaoVisualizar = new Button("Visualizar");
		Button botaoSelecionar = new Button("Selecionar");
		Toolbar toolbarInferior = new Toolbar();

		botaoVisualizar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

			}
		});

		botaoSelecionar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

			}
		});

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVisualizar);
		toolbarInferior.appendChild(botaoSelecionar);

		toolbarInferior.setParent(vboxResultado);
		
	}
	
	public void criarJanPaginasAmarelas(){
		
		Vbox vboxPaginasAmarelas = new Vbox();
		
		vboxPaginasAmarelas.setWidth("100%");
		
		criarTabboxBuscarPessoas();
		tabboxBuscarPessoas.setParent(vboxPaginasAmarelas);
		
		criarTabboxResultado();
		tabboxResultado.setParent(vboxPaginasAmarelas);
		
		Button botaoVoltar = new Button("Voltar");
		Button botaoFechar = new Button("Fechar");
		Toolbar toolbarInferior = new Toolbar();

		botaoVoltar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

			}
		});

		botaoFechar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

			}
		});

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVoltar);
		toolbarInferior.appendChild(botaoFechar);

		toolbarInferior.setParent(vboxPaginasAmarelas);
		
		vboxPaginasAmarelas.setParent(this);
		
		
	}

}

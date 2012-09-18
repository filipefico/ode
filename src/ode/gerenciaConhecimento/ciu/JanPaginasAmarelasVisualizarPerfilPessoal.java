package ode.gerenciaConhecimento.ciu;

import java.util.Collection;

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
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanPaginasAmarelasVisualizarPerfilPessoal extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Tabpanel tabpanelInformacoes = new Tabpanel();
	
	Listbox listboxTemasRelacionados = new Listbox();
	Listbox listboxItensCriados = new Listbox();
	Listbox listboxItensAvaliados = new Listbox();
	Listbox listboxItensValorados = new Listbox();
	
	public JanPaginasAmarelasVisualizarPerfilPessoal(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanPaginasAmarelasVisualizarPerfilPessoal();
		
	}
	
	public void criarTabpanelInformacoes(){
		
		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		
		Label labelNome = new Label("Nome: " + "sss");
		Label labelEmail = new Label("E-mail: " + "ddd");
		Label labelTelefone = new Label("Telefone: " + "kkk");
		
		labelNome.setParent(vbox);
		labelEmail.setParent(vbox);
		labelTelefone.setParent(vbox);
		
		Hbox hbox1 = new Hbox();
		hbox1.setHeight("100%");
		Hbox hbox2 = new Hbox();
		hbox2.setHeight("100%");
		Vbox vboxInformacoes = new Vbox();
		vboxInformacoes.setWidth("100%");
		
		//////////////////////////////////////////
		// Temas relacionados
		//////////////////////////////////////////
		Vbox vboxTemas = new Vbox();

		Label labelTemasRelacionados = new Label("Temas Relacionados: ");
		labelTemasRelacionados.setStyle("color: blue;");

		listboxTemasRelacionados.setMultiple(false);
		listboxTemasRelacionados.setCheckmark(false);
//		listboxTemasRelacionados.setHeight("140px");

		// Preenche temas no listbox
		Collection<Tema> temas = ctrlGerenciaConhecimento.aplCadastrarTema.recuperarTodos();
		for(Tema tema : temas){
			Listitem listitem = new Listitem(tema.getNome());
			listitem.setValue(tema);
			listitem.setParent(listboxTemasRelacionados);
		}

		labelTemasRelacionados.setParent(vboxTemas);
		listboxTemasRelacionados.setParent(vboxTemas);

		vboxTemas.setParent(hbox1);
		
		//////////////////////////////////////////
		// Itens criados
		//////////////////////////////////////////
		Vbox vboxItensCriados = new Vbox();

		Label labelItensCriados = new Label("Itens Criados:");
		labelItensCriados.setStyle("color: blue;");

		listboxItensCriados.setMultiple(false);
		listboxItensCriados.setCheckmark(false);

		// Preenche itens no listbox
		Collection<ItemConhecimento> itensCriados = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itensCriados){
			Listitem listitem = new Listitem(item.getTitulo());
			listitem.setValue(item);
			listitem.setParent(listboxItensCriados);
		}
		
		labelItensCriados.setParent(vboxItensCriados);
		listboxItensCriados.setParent(vboxItensCriados);
		
		vboxItensCriados.setParent(hbox1);
		
		//////////////////////////////////////////
		// Itens avaliados
		//////////////////////////////////////////
		Vbox vboxItensAvaliados = new Vbox();

		Label labelItensAvaliados = new Label("Itens Avaliados:");
		labelItensAvaliados.setStyle("color: blue;");

		listboxItensAvaliados.setMultiple(false);
		listboxItensAvaliados.setCheckmark(false);

		// Preenche itens no listbox
		Collection<ItemConhecimento> itensAvaliados = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itensAvaliados){
			Listitem listitem = new Listitem(item.getTitulo());
			listitem.setValue(item);
			listitem.setParent(listboxItensAvaliados);
		}
		
		labelItensAvaliados.setParent(vboxItensAvaliados);
		listboxItensAvaliados.setParent(vboxItensAvaliados);
		
		vboxItensAvaliados.setParent(hbox2);
		
		//////////////////////////////////////////
		// Itens valorados
		//////////////////////////////////////////
		Vbox vboxItensValorados = new Vbox();

		Label labelItensValorados = new Label("Itens Avaliados:");
		labelItensValorados.setStyle("color: blue;");

		listboxItensValorados.setMultiple(false);
		listboxItensValorados.setCheckmark(false);

		// Preenche itens no listbox
		Collection<ItemConhecimento> itensValorados = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itensValorados){
			Listitem listitem = new Listitem(item.getTitulo());
			listitem.setValue(item);
			listitem.setParent(listboxItensValorados);
		}
		
		labelItensValorados.setParent(vboxItensValorados);
		listboxItensValorados.setParent(vboxItensValorados);
		
		vboxItensValorados.setParent(hbox2);
		
		hbox1.setParent(vboxInformacoes);
		hbox2.setParent(vboxInformacoes);
		vboxInformacoes.setParent(vbox);
		vbox.setParent(tabpanelInformacoes);
		
	}
	
	public void criarJanPaginasAmarelasVisualizarPerfilPessoal(){
		
		this.setTitle("Páginas Amarelas - Visualizar Perfil");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		
		Tabbox tabbox = new Tabbox();
		Tabs tabs = new Tabs();
		Tab tabInformacoes = new Tab("Informações");
		
		tabInformacoes.setParent(tabs);
		tabs.setParent(tabbox);
		tabbox.setParent(vbox);
		
		Tabpanels tabpanels = new Tabpanels();
		
		criarTabpanelInformacoes();
		
		tabpanelInformacoes.setParent(tabpanels);
		tabpanels.setParent(tabbox);
		
		///////////////////
		// Botoes
		///////////////////
		Button botaoVoltar = new Button("Voltar");
		
		Toolbar toolbarInferior = new Toolbar();
		
		botaoVoltar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

			}
		});
		
		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVoltar);
		
		toolbarInferior.setParent(vbox);
		
		vbox.setParent(this);
		
		
	}
}

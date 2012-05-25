package ode.gerenciaConhecimento.ciu;

import java.util.Collection;

import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.Tema;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Window;

public class JanBuscarItensConhecimento extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;

	Listbox listboxProjetosRelacionados = new Listbox();
	Listbox listboxKAtividadesRelacionadas = new Listbox();
	Listbox listboxTemasRelacionados = new Listbox();

	//o nome dos objetos não ficaram bons, se tiver outra idéia...
	Textbox textboxExpressao = new Textbox("");
	Datebox dateboxInicial_1 = new Datebox();
	Datebox dateboxFinal_1 = new Datebox();
	Datebox dateboxInicial_2 = new Datebox();
	Datebox dateboxFinal_2 = new Datebox();
	Datebox dateboxInicial_3 = new Datebox();
	Datebox dateboxFinal_3 = new Datebox();
	Intbox textboxMinimo_1 = new Intbox();
	Intbox textboxMaximo_1 = new Intbox();
	Intbox textboxMinimo_2 = new Intbox();
	Intbox textboxMaximo_2 = new Intbox();
	Decimalbox textboxMinimo_3 = new Decimalbox();
	Decimalbox textboxMaximo_3 = new Decimalbox();
	Decimalbox textboxMinimo_4 = new Decimalbox();
	Decimalbox textboxMaximo_4 = new Decimalbox();
	Combobox combobox = new Combobox();

	Button buttonBuscar = new Button("Buscar");

	JanBuscarItensConhecimento(CtrlGerenciaConhecimento ctrl){

		System.out.println("CRTL = " + ctrl);

		this.ctrlGerenciaConhecimento = ctrl;
		
		System.out.println("Ctrl = " + this.ctrlGerenciaConhecimento );

		criarJanBuscarItensConhecimento();

	}

	void criarJanBuscarItensConhecimento(){


		//	this.setHeight("500px");
		this.setTitle("Buscar Itens de Conhecimento");
		this.setBorder("normal");

		Tabbox tabbox = new Tabbox();

		Tabs tabs = new Tabs();
		Tab tabCriterios = new Tab("Critérios");

		tabCriterios.setParent(tabs);
		tabs.setParent(tabbox);

		Tabpanels tabpanels = new Tabpanels();
		Tabpanel tabpanelCriterios = new Tabpanel();
		tabpanelCriterios.setHeight("400px");
		tabpanelCriterios.setStyle("overflow:auto;");

		Grid grid = new Grid();
		grid.setSizedByContent(true);
		Rows rows = new Rows();

		// --- cria linha Expressão --- //

		Row linhaExpressao = new Row();

		Label labelExpressao = new Label("Expressão:");

		textboxExpressao.setWidth("300px");

		labelExpressao.setParent(linhaExpressao);
		textboxExpressao.setParent(linhaExpressao);

		linhaExpressao.setParent(rows);

		// --- cria linha Data Criação --- //

		Row linhaDataCriacao = new Row();

		Label labelDataCriacao = new Label("Data de Criação:");

		Div divDataCriacao = new Div();
		Label labelInicial_1 = new Label("Inicial: ");
		Label labelFinal_1 = new Label(" Final: ");


		labelInicial_1.setParent(divDataCriacao);
		dateboxInicial_1.setParent(divDataCriacao);
		labelFinal_1.setParent(divDataCriacao);
		dateboxFinal_1.setParent(divDataCriacao);

		labelDataCriacao.setParent(linhaDataCriacao);
		divDataCriacao.setParent(linhaDataCriacao);

		linhaDataCriacao.setParent(rows);

		// --- cria linha Data Ultima Utilização --- //

		Row linhaDataUltimaUtilizacao = new Row();

		Label labelDataUltimaUtilizacao = new Label("Data de Última Utilização:");

		Div divDataUltimaUtilizacao = new Div();
		Label labelInicial_2 = new Label("Inicial: ");
		Label labelFinal_2 = new Label(" Final: ");

		labelInicial_2.setParent(divDataUltimaUtilizacao);
		dateboxInicial_2.setParent(divDataUltimaUtilizacao);
		labelFinal_2.setParent(divDataUltimaUtilizacao);
		dateboxFinal_2.setParent(divDataUltimaUtilizacao);

		labelDataUltimaUtilizacao.setParent(linhaDataUltimaUtilizacao);
		divDataUltimaUtilizacao.setParent(linhaDataUltimaUtilizacao);

		linhaDataUltimaUtilizacao.setParent(rows);

		// --- cria linha Data Ultimo Acesso --- //

		Row linhaDataUltimoAcesso = new Row();

		Label labelDataUltimoAcesso = new Label("Data de Último Acesso:");

		Div divDataUltimoAcesso = new Div();
		Label labelInicial_3 = new Label("Inicial: ");
		Label labelFinal_3 = new Label(" Final: ");

		labelInicial_3.setParent(divDataUltimoAcesso);
		dateboxInicial_3.setParent(divDataUltimoAcesso);
		labelFinal_3.setParent(divDataUltimoAcesso);
		dateboxFinal_3.setParent(divDataUltimoAcesso);

		labelDataUltimoAcesso.setParent(linhaDataUltimoAcesso);
		divDataUltimoAcesso.setParent(linhaDataUltimoAcesso);

		linhaDataUltimoAcesso.setParent(rows);

		// --- cria linha Quantidade de Acessos --- //

		Row linhaQtdeAcessos = new Row();

		Label labelQtdeAcessos = new Label("Quantidade de Acessos:");

		Div divQtdeAcessos = new Div();
		Label labelMinimo_1 = new Label("Mínimo: ");
		textboxMinimo_1.setWidth("70px");
		Label labelMaximo_1 = new Label(" Máximo: ");
		textboxMaximo_1.setWidth("70px");
		labelMinimo_1.setParent(divQtdeAcessos);
		textboxMinimo_1.setParent(divQtdeAcessos);
		labelMaximo_1.setParent(divQtdeAcessos);
		textboxMaximo_1.setParent(divQtdeAcessos);

		labelQtdeAcessos.setParent(linhaQtdeAcessos);
		divQtdeAcessos.setParent(linhaQtdeAcessos);

		linhaQtdeAcessos.setParent(rows);

		// --- cria linha Quantidade de Valorações --- //

		Row linhaQtdeValoracoes = new Row();

		Label labelQtdeValoracoes = new Label("Quantidade de Valorações:");

		Div divQtdeValoracoes = new Div();
		Label labelMinimo_2 = new Label("Mínimo: ");
		textboxMinimo_2.setWidth("70px");
		Label labelMaximo_2 = new Label(" Máximo: ");
		textboxMaximo_2.setWidth("70px");

		labelMinimo_2.setParent(divQtdeValoracoes);
		textboxMinimo_2.setParent(divQtdeValoracoes);
		labelMaximo_2.setParent(divQtdeValoracoes);
		textboxMaximo_2.setParent(divQtdeValoracoes);

		labelQtdeValoracoes.setParent(linhaQtdeValoracoes);
		divQtdeValoracoes.setParent(linhaQtdeValoracoes);

		linhaQtdeValoracoes.setParent(rows);

		// --- cria linha Percentual de Valorações Positivas --- //

		Row linhaPercentualValoracoesPositivas = new Row();

		Label labelPercentualValoracoesPositivas = new Label("Percentual de Valorações Positivas:");

		Div divPercentualValoracoesPositivas = new Div();
		Label labelMinimo_3 = new Label("Mínimo: ");
		textboxMinimo_3.setWidth("70px");
		Label labelMaximo_3 = new Label(" %   Máximo: ");
		textboxMaximo_3.setWidth("70px");
		Label labelPercente_3 = new Label(" %");

		labelMinimo_3.setParent(divPercentualValoracoesPositivas);
		textboxMinimo_3.setParent(divPercentualValoracoesPositivas);
		labelMaximo_3.setParent(divPercentualValoracoesPositivas);
		textboxMaximo_3.setParent(divPercentualValoracoesPositivas);
		labelPercente_3.setParent(divPercentualValoracoesPositivas);

		labelPercentualValoracoesPositivas.setParent(linhaPercentualValoracoesPositivas);
		divPercentualValoracoesPositivas.setParent(linhaPercentualValoracoesPositivas);

		linhaPercentualValoracoesPositivas.setParent(rows);

		// --- cria linha Percentual de Valorações Negativas --- //

		Row linhaPercentualValoracoesNegativas = new Row();

		Label labelPercentualValoracoesNegativas = new Label("Percentual de Valorações Positivas:");

		Div divPercentualValoracoesNegativas = new Div();
		Label labelMinimo_4 = new Label("Mínimo: ");
		textboxMinimo_4.setWidth("70px");
		Label labelMaximo_4 = new Label(" %   Máximo: ");
		textboxMaximo_4.setWidth("70px");
		Label labelPercente_4 = new Label(" %");

		labelMinimo_4.setParent(divPercentualValoracoesNegativas);
		textboxMinimo_4.setParent(divPercentualValoracoesNegativas);
		labelMaximo_4.setParent(divPercentualValoracoesNegativas);
		textboxMaximo_4.setParent(divPercentualValoracoesNegativas);
		labelPercente_4.setParent(divPercentualValoracoesNegativas);

		labelPercentualValoracoesNegativas.setParent(linhaPercentualValoracoesNegativas);
		divPercentualValoracoesNegativas.setParent(linhaPercentualValoracoesNegativas);

		linhaPercentualValoracoesNegativas.setParent(rows);

		// --- cria linha Tipo de Item de Conhecimento --- //

		Row linhaTipoItemConhecimento = new Row();

		Label labelTipoItemConhecimento = new Label("Tipo de Item de Conhecimento:");

		combobox.setWidth("250px");
		Comboitem comboitemTodos = new Comboitem("Todos");
		comboitemTodos.setValue("Todos");
		Comboitem comboitemLicaoAprendida = new Comboitem("Lição Aprendida");
		comboitemLicaoAprendida.setValue("Lição Aprendida");
		Comboitem comboitemConhecimentoRelativoDiscussao = new Comboitem("Conhecimento Relativo a Discussão");
		comboitemConhecimentoRelativoDiscussao.setValue("Conhecimento Relativo a Discussão");

		comboitemTodos.setParent(combobox);
		comboitemLicaoAprendida.setParent(combobox);
		comboitemConhecimentoRelativoDiscussao.setParent(combobox);
		combobox.setSelectedIndex(0);

		labelTipoItemConhecimento.setParent(linhaTipoItemConhecimento);
		combobox.setParent(linhaTipoItemConhecimento);

		linhaTipoItemConhecimento.setParent(rows);

		//////////////////////////////////////////
		// Projetos relacionados
		//////////////////////////////////////////

		Row linhaProjetosRelacionados = new Row();

		Label labelProjetosRelacionados = new Label("Projetos Relacionados:");

		listboxProjetosRelacionados.setMultiple(true);
		listboxProjetosRelacionados.setCheckmark(true);

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

		labelProjetosRelacionados.setParent(linhaProjetosRelacionados);
		listboxProjetosRelacionados.setParent(linhaProjetosRelacionados);

		linhaProjetosRelacionados.setParent(rows);

		//////////////////////////////////////////
		// Atividades relacionadas
		//////////////////////////////////////////
		Row linhaAtividadesRelacionadas = new Row();

		Label labelAtividadesRelacionadas = new Label("Atividades Relacionadas: ");

		listboxKAtividadesRelacionadas.setMultiple(true);
		listboxKAtividadesRelacionadas.setCheckmark(true);

		Listhead colunasAtividadesRelacionadas = new Listhead();
		Listheader colunaTitulo2 = new Listheader("Título");

		colunaTitulo2.setParent(colunasAtividadesRelacionadas);
		colunasAtividadesRelacionadas.setParent(listboxKAtividadesRelacionadas);

		// Adicionar itens no listbox katividade
		Collection<KAtividade> atividades = ctrlGerenciaConhecimento.aplCadastrarKAtividade.recuperarTodos();
		for(KAtividade atividade : atividades){
			Listitem listitem = new Listitem(atividade.getNome());
			listitem.setValue(atividade);
			listitem.setParent(listboxKAtividadesRelacionadas);
		}

		labelAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);
		listboxKAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);

		linhaAtividadesRelacionadas.setParent(rows);

		//////////////////////////////////////////
		// Temas relacionados
		//////////////////////////////////////////
		Row linhaTemasRelacionados = new Row();

		Label labelTemasRelacionados = new Label("Temas Relacionados: ");

		listboxTemasRelacionados.setMultiple(true);
		listboxTemasRelacionados.setCheckmark(true);

		Listhead colunasTemasRelacionados = new Listhead();
		Listheader colunaTitulo1 = new Listheader("Título");

		colunaTitulo1.setParent(colunasTemasRelacionados);
		colunasTemasRelacionados.setParent(listboxTemasRelacionados);
		
		System.out.println("0 " + ctrlGerenciaConhecimento);
		
		System.out.println("0.1 " + ctrlGerenciaConhecimento.aplCadastrarTema);

		// Preenche temas no listbox
		Collection<Tema> temas = ctrlGerenciaConhecimento.aplCadastrarTema.recuperarTodos();
		for(Tema tema : temas){
			Listitem listitem = new Listitem(tema.getNome());
			listitem.setValue(tema);
			listitem.setParent(listboxTemasRelacionados);
		}

		labelTemasRelacionados.setParent(linhaTemasRelacionados);
		listboxTemasRelacionados.setParent(linhaTemasRelacionados);

		linhaTemasRelacionados.setParent(rows);

		rows.setParent(grid);
		grid.setParent(tabpanelCriterios);
		tabpanelCriterios.setParent(tabpanels);
		tabpanels.setParent(tabbox);

		tabbox.setParent(this);

		System.out.println("1 " + textboxExpressao.getValue()+ dateboxInicial_1.getValue()+ dateboxFinal_1.getValue()+ dateboxInicial_2.getValue()+ dateboxFinal_2.getValue()+ dateboxInicial_3.getValue()+ dateboxFinal_3.getValue()+ textboxMinimo_1.getValue()+ textboxMaximo_1.getValue()+ textboxMinimo_2.getValue()+ textboxMaximo_2.getValue()+ textboxMinimo_3.getValue()+ textboxMaximo_3.getValue()+ textboxMinimo_4.getValue()+ textboxMaximo_4.getValue());

		System.out.println("2 " + (String) combobox.getSelectedItem().getValue());

		System.out.println("3 " + listboxProjetosRelacionados.getSelectedItems()+ listboxKAtividadesRelacionadas.getSelectedItems() + listboxTemasRelacionados.getSelectedItems());

		System.out.println("4 " + ctrlGerenciaConhecimento);

		Button botaoBuscar = new Button("Buscar");
		Toolbar toolbarInferior = new Toolbar();

		botaoBuscar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.buscar(textboxExpressao.getValue(), dateboxInicial_1.getValue(), dateboxFinal_1.getValue(), dateboxInicial_2.getValue(), dateboxFinal_2.getValue(), dateboxInicial_3.getValue(), dateboxFinal_3.getValue(), textboxMinimo_1.getValue(), textboxMaximo_1.getValue(), textboxMinimo_2.getValue(), textboxMaximo_2.getValue(), textboxMinimo_3.getValue(), textboxMaximo_3.getValue(), textboxMinimo_4.getValue(), textboxMaximo_4.getValue(), (String)combobox.getSelectedItem().getValue(), listboxProjetosRelacionados.getSelectedItems(), listboxKAtividadesRelacionadas.getSelectedItems(), listboxTemasRelacionados.getSelectedItems());
				ctrlGerenciaConhecimento.exibirJanelaListaBuscarItensConhecimento();
			}
		});
		

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoBuscar);

		toolbarInferior.setParent(this);
		
	}

}

package ode.gerenciaConhecimento.ciu;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
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

public class JanAlterarItemConhecimento extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	ItemConhecimento itemConhecimento;
	
	LicaoAprendida licaoAprendida;
	ConhecimentoRelativoDiscussao conhecimentoRelativoDiscussao;
	
	Textbox textboxTitulo;
	Textbox labelAutor;
	Textbox textboxResumo;
	Textbox textboxAplicabilidade;
	Textbox textboxDescricaoProblema;
	Textbox textboxSolucaoAdotadaRecomendada;
	Textbox textboxResultadoEsperado;

	Radiogroup radiogroup;
	Radio sucesso;
	Radio fracasso;
	Radio informativa;

	Listbox listboxTemasRelacionados = new Listbox();
	Listbox listboxProjetosRelacionados = new Listbox();
	Listbox listboxAtividadesRelacionadas = new Listbox();
	Listbox listboxItensRelacionados = new Listbox();
	
	Label labelDataCriacaoValor;
	Textbox textboxConhecimentoAdquirido;
	Textbox textboxPontosFortes;
	Textbox textboxPontosFracos;
	Textbox textboxLinkDiscussao;

	Listbox listboxProjetosRelacionadosConhecimento = new Listbox();
	Listbox listboxTemasRelacionadosConhecimento = new Listbox();
	Listbox listboxKAtividadesRelacionadas = new Listbox();
	Listbox listboxItensConhecimentoRelacionados = new Listbox();
	
	public JanAlterarItemConhecimento(CtrlGerenciaConhecimento ctrl, ItemConhecimento item) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
	//	itemConhecimento = item;
		
		
		if(item instanceof LicaoAprendida){
			licaoAprendida = (LicaoAprendida) item;
			criarJanAlterarLicaoAprendida();
		}
		if(item instanceof ConhecimentoRelativoDiscussao){
			conhecimentoRelativoDiscussao = (ConhecimentoRelativoDiscussao) item;
			criarJanCriarConhecimentoRelativoDiscussao();
		}
		
		
		
	}
	
	public Tabpanel criarTabInfoGeraisConhecimento() {

		Tabpanel tabpainel = new Tabpanel();

		Grid grid = new Grid();
		Rows rows = new Rows();

		// --- cria linha autor --- //

		Row linhaAutor = new Row();

		Label labelAutor = new Label("Autor:");
		Label labelDescricaoAutor = new Label();
		labelDescricaoAutor.setValue(conhecimentoRelativoDiscussao.getAutor().getNome());

		labelAutor.setParent(linhaAutor);
		labelDescricaoAutor.setParent(linhaAutor);

		// --- cria linha dataCriação --- //

		Row linhaDataCriacao = new Row();
		Label labelDataCriacao = new Label();
		labelDataCriacaoValor = new Label();

	//	Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		labelDataCriacao.setValue("Data de Criação:");
		labelDataCriacaoValor.setValue(formatador.format(conhecimentoRelativoDiscussao.getDataCriacao()));

		labelDataCriacao.setParent(linhaDataCriacao);
		labelDataCriacaoValor.setParent(linhaDataCriacao);

		// --- cria linha titulo --- //

		Row linhaTitulo = new Row();

		Label labelTitulo = new Label();
		textboxTitulo = new Textbox();

		labelTitulo.setValue("Título:");
		textboxTitulo.setText(conhecimentoRelativoDiscussao.getTitulo());
		textboxTitulo.setWidth("430px");

		labelTitulo.setParent(linhaTitulo);
		textboxTitulo.setParent(linhaTitulo);

		// --- cria linha resumo --- //

		Row linhaResumo = new Row();

		Label labelResumo = new Label();
		textboxResumo = new Textbox();

		labelResumo.setValue("Resumo:");
		textboxResumo.setText(conhecimentoRelativoDiscussao.getResumo());
		textboxResumo.setHeight("100px");
		textboxResumo.setWidth("430px");
		textboxResumo.setRows(3);

		labelResumo.setParent(linhaResumo);
		textboxResumo.setParent(linhaResumo);

		// --- cria linha aplicabilidade --- //

		Row linhaAplicabilidade = new Row();

		Label labelAplicabilidade = new Label();
		textboxAplicabilidade = new Textbox();

		labelAplicabilidade.setValue("Aplicabilidade:");
		textboxAplicabilidade.setText(conhecimentoRelativoDiscussao.getAplicabilidade());
		textboxAplicabilidade.setHeight("100px");
		textboxAplicabilidade.setWidth("430px");
		textboxAplicabilidade.setRows(3);

		labelAplicabilidade.setParent(linhaAplicabilidade);
		textboxAplicabilidade.setParent(linhaAplicabilidade);

		linhaAutor.setParent(rows);
		linhaDataCriacao.setParent(rows);
		linhaTitulo.setParent(rows);
		linhaResumo.setParent(rows);
		linhaAplicabilidade.setParent(rows);

		rows.setParent(grid);
		grid.setParent(tabpainel);
		grid.setSizedByContent(true);

		return tabpainel;
	}

	public Tabpanel criarTabInfoEspecificaConhecimento() {

		Tabpanel tabpainel = new Tabpanel();
		tabpainel.setStyle("overflow:auto;");
		tabpainel.setHeight("350px");
	
		Grid grid = new Grid();
		Rows rows = new Rows();

		// --- cria linha conhecimento adquirido --- //

		Row linhaConhecimentoAdquirido = new Row();

		Label labelConhecimentoAdquirido = new Label();
		textboxConhecimentoAdquirido = new Textbox();

		labelConhecimentoAdquirido.setValue("Conhecimento Adquirido:");
		textboxConhecimentoAdquirido.setText(conhecimentoRelativoDiscussao.getConhecimentoAdquirido());
		textboxConhecimentoAdquirido.setHeight("100px");
		textboxConhecimentoAdquirido.setWidth("300px");
		textboxConhecimentoAdquirido.setRows(3);

		labelConhecimentoAdquirido.setParent(linhaConhecimentoAdquirido);
		textboxConhecimentoAdquirido.setParent(linhaConhecimentoAdquirido);

		// --- cria linha pontos fortes --- //

		Row linhaPontosFortes = new Row();

		Label labelPontosFortes = new Label();
		textboxPontosFortes = new Textbox();

		labelPontosFortes.setValue("Pontos Fortes:");
		textboxPontosFortes.setText(conhecimentoRelativoDiscussao.getPontosFortes());
		textboxPontosFortes.setHeight("100px");
		textboxPontosFortes.setWidth("300px");
		textboxPontosFortes.setRows(3);

		labelPontosFortes.setParent(linhaPontosFortes);
		textboxPontosFortes.setParent(linhaPontosFortes);

		// --- cria linha pontos fracos --- //

		Row linhaPontosFracos = new Row();

		Label labelPontosFracos = new Label();
		textboxPontosFracos = new Textbox();

		labelPontosFracos.setValue("Pontos Fracos:");
		textboxPontosFracos.setText(conhecimentoRelativoDiscussao.getPontosFracos());
		textboxPontosFracos.setHeight("100px");
		textboxPontosFracos.setWidth("300px");
		textboxPontosFracos.setRows(3);

		labelPontosFracos.setParent(linhaPontosFracos);
		textboxPontosFracos.setParent(linhaPontosFracos);

		// --- cria linha link discussão --- //

		Row linhaLinkDiscussao = new Row();

		Label labelLinkDiscussao = new Label();
		textboxLinkDiscussao = new Textbox();

		labelLinkDiscussao.setValue("Link para a discussão:");
		textboxLinkDiscussao.setText(conhecimentoRelativoDiscussao.getLinkDiscussao());
		textboxLinkDiscussao.setWidth("300px");

		labelLinkDiscussao.setParent(linhaLinkDiscussao);
		textboxLinkDiscussao.setParent(linhaLinkDiscussao);

		linhaConhecimentoAdquirido.setParent(rows);
		linhaPontosFortes.setParent(rows);
		linhaPontosFracos.setParent(rows);
		linhaLinkDiscussao.setParent(rows);
		rows.setParent(grid);
		grid.setParent(tabpainel);

		return tabpainel;

	}

	public Tabpanel criarTabAssociacoesConhecimento() {

		Tabpanel tabpainel = new Tabpanel();
		tabpainel.setStyle("overflow:auto;");
		tabpainel.setHeight("400px");
		
		Grid grid = new Grid();
		Rows rows = new Rows();

		//////////////////////////////////////////
		// Temas relacionados
		//////////////////////////////////////////
		Row linhaTemasRelacionados = new Row();

		Label labelTemasRelacionados = new Label("Temas Relacionados");

		listboxTemasRelacionadosConhecimento.setMultiple(true);
		listboxTemasRelacionadosConhecimento.setCheckmark(true);

		Listhead colunasTemasRelacionados = new Listhead();
		Listheader colunaTitulo1 = new Listheader("Título");

		colunaTitulo1.setParent(colunasTemasRelacionados);
		colunasTemasRelacionados.setParent(listboxTemasRelacionadosConhecimento);
		
		// Preenche temas no listbox
		Collection<Tema> temas = conhecimentoRelativoDiscussao.getTemas();
		Collection<Tema> temasTodos = ctrlGerenciaConhecimento.aplCadastrarTema.recuperarTodos();
		for(Tema tema : temasTodos){
			Listitem listitem = new Listitem(tema.getNome());
			if(temas.contains(tema)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(tema);
			listitem.setParent(listboxTemasRelacionadosConhecimento);
		}

		labelTemasRelacionados.setParent(linhaTemasRelacionados);
		listboxTemasRelacionadosConhecimento.setParent(linhaTemasRelacionados);

		//////////////////////////////////////////
		// Projetos relacionados
		//////////////////////////////////////////
		Row linhaProjetosRelacionados = new Row();

		Label labelProjetosRelacionados = new Label("Projetos Relacionados");

		listboxProjetosRelacionadosConhecimento.setMultiple(true);
		listboxProjetosRelacionadosConhecimento.setCheckmark(true);

		Listhead colunasProjetosRelacionados = new Listhead();
		Listheader colunaNome = new Listheader("Nome");

		colunaNome.setParent(colunasProjetosRelacionados);
		colunasProjetosRelacionados.setParent(listboxProjetosRelacionadosConhecimento);

		// Adicionar itens no listbox projeto
		Collection<Projeto> projetos = conhecimentoRelativoDiscussao.getProjetos();
		Collection<Projeto> projetosTodos = ctrlGerenciaConhecimento.aplCadastrarProjeto.recuperarTodos();
		for (Projeto projeto : projetosTodos) {
			Listitem listitem = new Listitem(projeto.getNome());
			if(projetos.contains(projeto)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(projeto);
			listitem.setParent(listboxProjetosRelacionadosConhecimento);
		}

		labelProjetosRelacionados.setParent(linhaProjetosRelacionados);
		listboxProjetosRelacionadosConhecimento.setParent(linhaProjetosRelacionados);

		//////////////////////////////////////////
		// Atividades relacionadas
		//////////////////////////////////////////
		Row linhaAtividadesRelacionadas = new Row();

		Label labelAtividadesRelacionadas = new Label("Atividades Relacionadas");

		listboxKAtividadesRelacionadas.setMultiple(true);
		listboxKAtividadesRelacionadas.setCheckmark(true);

		Listhead colunasAtividadesRelacionadas = new Listhead();
		Listheader colunaTitulo2 = new Listheader("Título");

		colunaTitulo2.setParent(colunasAtividadesRelacionadas);
		colunasAtividadesRelacionadas.setParent(listboxKAtividadesRelacionadas);

		// Adicionar itens no listbox katividade
		Collection<KAtividade> atividades = conhecimentoRelativoDiscussao.getkAtividades();
		Collection<KAtividade> atividadesTodos = ctrlGerenciaConhecimento.aplCadastrarKAtividade.recuperarTodos();
		for(KAtividade atividade : atividadesTodos){
			Listitem listitem = new Listitem(atividade.getNome());
			if(atividades.contains(atividade)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(atividade);
			listitem.setParent(listboxKAtividadesRelacionadas);
		}

		labelAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);
		listboxKAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);

		//////////////////////////////////////////
		// Conhecimentos relacionados
		//////////////////////////////////////////
		Row linhaItensConhecimentoRelacionados = new Row();

		Label labelItensConhecimentoRelacionadas = new Label("Itens de Conhecimento Relacionados");

		listboxItensConhecimentoRelacionados.setMultiple(true);
		listboxItensConhecimentoRelacionados.setCheckmark(true);

		Listhead colunasItensConhecimentoRelacionados = new Listhead();
		Listheader colunaTitulo3 = new Listheader("Título");

		colunaTitulo3.setParent(colunasItensConhecimentoRelacionados);
		colunasItensConhecimentoRelacionados.setParent(listboxItensConhecimentoRelacionados);

		// Adicionar itens no listbox
		Collection<ItemConhecimento> itemConhecimento = conhecimentoRelativoDiscussao.getItensRelacionados();
		Collection<ItemConhecimento> itemConhecimentoTodos = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for(ItemConhecimento itens : itemConhecimentoTodos){
			Listitem listitem = new Listitem(itens.getTitulo());
			if(itemConhecimento.contains(itens)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(itens);
			listitem.setParent(listboxItensConhecimentoRelacionados);
		}

		labelItensConhecimentoRelacionadas.setParent(linhaItensConhecimentoRelacionados);
		listboxItensConhecimentoRelacionados.setParent(linhaItensConhecimentoRelacionados);

		linhaTemasRelacionados.setParent(rows);
		linhaProjetosRelacionados.setParent(rows);
		linhaAtividadesRelacionadas.setParent(rows);
		linhaItensConhecimentoRelacionados.setParent(rows);
		
		rows.setParent(grid);
		grid.setParent(tabpainel);

		return tabpainel;

	}
	
	public Tabbox criarAbasCriarConhecimentoRelativoDiscussao() {

		Tabbox tabbox = new Tabbox();
		tabbox.setHeight("380px");
		Tabs tabs = new Tabs();
		Tabpanels tabpanels = new Tabpanels();

		Tab infoGerais = new Tab("Informações Gerais");
		Tab infoEspecificas = new Tab("Informações Específicas");
		Tab associacoes = new Tab("Associações");

		infoGerais.setParent(tabs);
		infoEspecificas.setParent(tabs);
		associacoes.setParent(tabs);

		Tabpanel tabInfoGerais = criarTabInfoGeraisConhecimento();
		Tabpanel tabInfoEspecifica = criarTabInfoEspecificaConhecimento();
		Tabpanel tabAssociacoes = criarTabAssociacoesConhecimento();

		tabInfoGerais.setParent(tabpanels);
		tabInfoEspecifica.setParent(tabpanels);
		tabAssociacoes.setParent(tabpanels);

		tabpanels.setParent(tabbox);
		tabs.setParent(tabbox);

		return tabbox;

	}
	
	public void salvarConhecimento() throws NucleoRegraNegocioExcecao {

		ctrlGerenciaConhecimento.salvarConhecimentoRelativoDiscussao(preencherConhecimentoRelativoDiscussao());

	}
	
	public ConhecimentoRelativoDiscussao preencherConhecimentoRelativoDiscussao() {

		//ConhecimentoRelativoDiscussao conhecimentoRelativoDiscussao = new ConhecimentoRelativoDiscussao();

		conhecimentoRelativoDiscussao.setAutor(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
		conhecimentoRelativoDiscussao.setDataCriacao(conhecimentoRelativoDiscussao.getDataCriacao());
		conhecimentoRelativoDiscussao.setTitulo(textboxTitulo.getValue());
		conhecimentoRelativoDiscussao.setResumo(textboxResumo.getValue());
		conhecimentoRelativoDiscussao.setAplicabilidade(textboxAplicabilidade.getValue());
		conhecimentoRelativoDiscussao.setConhecimentoAdquirido(textboxConhecimentoAdquirido.getValue());
		conhecimentoRelativoDiscussao.setPontosFortes(textboxPontosFortes.getValue());
		conhecimentoRelativoDiscussao.setPontosFracos(textboxPontosFracos.getValue());
		conhecimentoRelativoDiscussao.setLinkDiscussao(textboxLinkDiscussao.getValue());
		
		// preenche temas selecionados
		Set<Listitem> itens = listboxTemasRelacionadosConhecimento.getSelectedItems();
		Set<Tema> temas = new HashSet<Tema>();
		for (Listitem item : itens){
			temas.add((Tema)item.getValue());
		}
		conhecimentoRelativoDiscussao.getTemas().clear();
		conhecimentoRelativoDiscussao.setTemas(temas);
		
		// preenche projetos selecionados
		itens = listboxProjetosRelacionadosConhecimento.getSelectedItems();
		Set<Projeto> projetos = new HashSet<Projeto>();
		for(Listitem item : itens){
			projetos.add((Projeto)item.getValue());
		}
		conhecimentoRelativoDiscussao.getProjetos().clear();
		conhecimentoRelativoDiscussao.setProjetos(projetos);
		
		//preenche atividades selecionadas
		itens = listboxKAtividadesRelacionadas.getSelectedItems();
		Set<KAtividade> atividades = new HashSet<KAtividade>();
		for(Listitem item : itens){
			atividades.add((KAtividade)item.getValue());
		}
		conhecimentoRelativoDiscussao.getkAtividades().clear();
		conhecimentoRelativoDiscussao.setkAtividades(atividades);
		
		//preenche itens de conhecimento relacionados
		itens = listboxItensConhecimentoRelacionados.getSelectedItems();
		Set<ItemConhecimento> conhecimentos = new HashSet<ItemConhecimento>();
		for(Listitem item : itens){
			conhecimentos.add((ItemConhecimento)item.getValue());
		}
		conhecimentoRelativoDiscussao.getItensRelacionados().clear();
		conhecimentoRelativoDiscussao.setItensRelacionados(conhecimentos);

		return conhecimentoRelativoDiscussao;

	}

	
	public void criarJanCriarConhecimentoRelativoDiscussao() {

		this.setTitle("Criar Conhecimento Relativo a uma Discussão");
		this.setBorder("normal");
		this.setClosable(true);

		Tabbox tabbox = criarAbasCriarConhecimentoRelativoDiscussao();
		tabbox.setParent(this);

		Button botaoSalvar = new Button("Salvar");
		Button botaoCancelar = new Button("Cancelar");
		Toolbar toolbarInferior = new Toolbar();

		botaoSalvar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				salvarConhecimento();
				Messagebox messageboxSalvar = new Messagebox();
				messageboxSalvar.show("Conhecimento Relativo a uma Discussão salvo com sucesso!","Informação", Messagebox.OK,messageboxSalvar.INFORMATION);
				fecharJanela();
			}
		});

		botaoCancelar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				// Messagebox messageboxCancelar = new Messagebox();
				Messagebox
						.show("Deseja realmente cancelar o Conhecimento Relativo a uma Discussão?",
								"Aviso", Messagebox.YES | Messagebox.NO,
								Messagebox.QUESTION, new EventListener() {

									@Override
									public void onEvent(Event arg0)
											throws Exception {
										// TODO Auto-generated method stub
										if (arg0.getName().equals("onYes")) {
											Messagebox
													.show("Conhecimento Relativo a uma Discussão cancelado!",
															"Informação",
															Messagebox.OK,
															Messagebox.INFORMATION);
											fecharJanela();
										}
									}
								});
			}
		});

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoSalvar);
		toolbarInferior.appendChild(botaoCancelar);

		toolbarInferior.setParent(this);

	}
	
	public Tabpanel criarTabInfoGerais(){

		Tabpanel tabpainel = new Tabpanel();

		Grid grid = new Grid();
		Rows rows = new Rows();
		
		// --- cria linha autor --- //

		Row linhaAutor = new Row();

		Label labelAutor = new Label("Autor:");
		Label labelDescricaoAutor = new Label();
		labelDescricaoAutor.setValue(licaoAprendida.getAutor().getNome());

		labelAutor.setParent(linhaAutor);
		labelDescricaoAutor.setParent(linhaAutor);

		// --- cria linha dataCriação --- //

		Row linhaDataCriacao = new Row();
		Label labelDataCriacao = new Label();
		labelDataCriacaoValor = new Label();

		//Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 

		labelDataCriacao.setValue("Data de Criação:");
		labelDataCriacaoValor.setValue(formatador.format(licaoAprendida.getDataCriacao()));

		labelDataCriacao.setParent(linhaDataCriacao);
		labelDataCriacaoValor.setParent(linhaDataCriacao);

		// --- cria linha titulo --- //

		Row linhaTitulo = new Row();

		Label labelTitulo = new Label();
		textboxTitulo = new Textbox();

		labelTitulo.setValue("Título:");
		textboxTitulo.setText(licaoAprendida.getTitulo());
		textboxTitulo.setWidth("425px");

		labelTitulo.setParent(linhaTitulo);
		textboxTitulo.setParent(linhaTitulo);

		// --- cria linha resumo --- //

		Row linhaResumo = new Row();

		Label labelResumo = new Label();
		textboxResumo = new Textbox();

		labelResumo.setValue("Resumo:");
		textboxResumo.setText(licaoAprendida.getResumo());
		textboxResumo.setHeight("100px");
		textboxResumo.setWidth("425px");
		textboxResumo.setRows(3);

		labelResumo.setParent(linhaResumo);
		textboxResumo.setParent(linhaResumo);

		// --- cria linha aplicabilidade --- //

		Row linhaAplicabilidade = new Row();

		Label labelAplicabilidade = new Label();
		textboxAplicabilidade = new Textbox();

		labelAplicabilidade.setValue("Aplicabilidade:");
		textboxAplicabilidade.setText(licaoAprendida.getAplicabilidade());
		textboxAplicabilidade.setHeight("100px");
		textboxAplicabilidade.setWidth("425px");
		textboxAplicabilidade.setRows(3);

		labelAplicabilidade.setParent(linhaAplicabilidade);
		textboxAplicabilidade.setParent(linhaAplicabilidade);

		linhaAutor.setParent(rows);
		linhaDataCriacao.setParent(rows);
		linhaTitulo.setParent(rows);
		linhaResumo.setParent(rows);
		linhaAplicabilidade.setParent(rows);

		rows.setParent(grid);
		grid.setParent(tabpainel);
		grid.setSizedByContent(true);

		return tabpainel;
	}

	public Tabpanel criarTabInfoEspecifica(){

		Tabpanel tabpainel = new Tabpanel();
		tabpainel.setStyle("overflow:auto;");
		tabpainel.setHeight("200px");
	//	tabpainel.setHeight("500px");
		Grid grid = new Grid();
		Rows rows = new Rows();

		// --- cria linha tipo --- //

		Row linhaTipo = new Row();

		Label labelTipo = new Label();
		radiogroup = new Radiogroup();
		
		labelTipo.setValue("Tipo:");
		
		labelTipo.setParent(linhaTipo);
		radiogroup.setParent(linhaTipo);
		
		sucesso = new Radio();
		fracasso = new Radio();
		informativa = new Radio();
		
		sucesso.setLabel(LicaoAprendida.TIPO_SUCESSO);
		fracasso.setLabel(LicaoAprendida.TIPO_FRACASSO);
		informativa.setLabel(LicaoAprendida.TIPO_INFORMATIVA);
		
		sucesso.setParent(radiogroup);
		fracasso.setParent(radiogroup);
		informativa.setParent(radiogroup);

		System.out.println("\n\n\n kkk " + licaoAprendida.getTipo() + " kkk\n\n\n");

		if(licaoAprendida.getTipo() != null){
			if(licaoAprendida.getTipo().compareTo(LicaoAprendida.TIPO_SUCESSO) == 0){
				radiogroup.setSelectedItem(sucesso);
			}else{
				if(licaoAprendida.getTipo().compareTo(LicaoAprendida.TIPO_FRACASSO) == 0){
					radiogroup.setSelectedItem(fracasso);
				}else{
					if(licaoAprendida.getTipo().compareTo(LicaoAprendida.TIPO_INFORMATIVA) == 0){
						radiogroup.setSelectedItem(informativa);
					}
				}
			}
		}

		

		// --- cria linha descrição do problema --- //

		Row linhaDescricaoProblema = new Row();

		Label labelDescricaoProblema = new Label();
		textboxDescricaoProblema = new Textbox();

		labelDescricaoProblema.setValue("Descrição do Problema");
		textboxDescricaoProblema.setText(licaoAprendida.getDescricaoProblema());
		textboxDescricaoProblema.setHeight("100px");
		textboxDescricaoProblema.setWidth("315px");
		textboxDescricaoProblema.setRows(3);

		labelDescricaoProblema.setParent(linhaDescricaoProblema);
		textboxDescricaoProblema.setParent(linhaDescricaoProblema);

		// --- cria linha solução adotada ou recomendada --- //

		Row linhaSolucaoAdotadaRecomendada = new Row();

		Label labelSolucaoAdotadaRecomendada = new Label();
		textboxSolucaoAdotadaRecomendada = new Textbox();

		labelSolucaoAdotadaRecomendada.setValue("Solução Adotada ou Recomendada:");
		textboxSolucaoAdotadaRecomendada.setText(licaoAprendida.getSolucaoAdotadaOuRecomendada());
		textboxSolucaoAdotadaRecomendada.setHeight("100px");
		textboxSolucaoAdotadaRecomendada.setWidth("315px");
		textboxSolucaoAdotadaRecomendada.setRows(3);

		labelSolucaoAdotadaRecomendada.setParent(linhaSolucaoAdotadaRecomendada);
		textboxSolucaoAdotadaRecomendada.setParent(linhaSolucaoAdotadaRecomendada);

		// --- cria linha resultado esperado --- //

		Row linhaResultadoEsperado = new Row();

		Label labelResultadoEsperado = new Label();
		textboxResultadoEsperado = new Textbox();

		labelResultadoEsperado.setValue("Resultado Esperado:");
		textboxResultadoEsperado.setText(licaoAprendida.getResultadoEsperado());
		textboxResultadoEsperado.setHeight("100px");
		textboxResultadoEsperado.setWidth("315px");
		textboxResultadoEsperado.setRows(3);

		labelResultadoEsperado.setParent(linhaResultadoEsperado);
		textboxResultadoEsperado.setParent(linhaResultadoEsperado);


		linhaTipo.setParent(rows);
		linhaDescricaoProblema.setParent(rows);
		linhaSolucaoAdotadaRecomendada.setParent(rows);
		linhaResultadoEsperado.setParent(rows);
		rows.setParent(grid);
		grid.setParent(tabpainel);
		grid.setSizedByContent(true);

		return tabpainel;

	}

	public Tabpanel criarTabAssociacoes(){

		Tabpanel tabpainel = new Tabpanel();
		tabpainel.setStyle("overflow:auto;");
		tabpainel.setHeight("400px");

		Grid grid = new Grid();
		Rows rows = new Rows();

		//////////////////////////////////////////
		// Temas relacionados
		//////////////////////////////////////////
		Row linhaTemasRelacionados = new Row();

		Label labelTemasRelacionados = new Label("Temas Relacionados");

		Listhead colunasTemasRelacionados = new Listhead();
		Listheader colunaTitulo1 = new Listheader("Título");

		listboxTemasRelacionados.setMultiple(true);
		listboxTemasRelacionados.setCheckmark(true);

		linhaTemasRelacionados.setParent(rows);
		labelTemasRelacionados.setParent(linhaTemasRelacionados);
		listboxTemasRelacionados.setParent(linhaTemasRelacionados);
		colunasTemasRelacionados.setParent(listboxTemasRelacionados);
		colunaTitulo1.setParent(colunasTemasRelacionados);

		// Preenche temas no listbox
		Collection<Tema> temas = licaoAprendida.getTemas();
		Collection<Tema> temasTodos = ctrlGerenciaConhecimento.aplCadastrarTema.recuperarTodos();
		for (Tema tema : temasTodos){
			Listitem listitem = new Listitem(tema.getNome());
			if(temas.contains(tema)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(tema);
			listitem.setParent(listboxTemasRelacionados);
		}

		//////////////////////////////////////////
		// Projetos relacionados
		//////////////////////////////////////////
		Row linhaProjetosRelacionados = new Row();

		Label labelProjetosRelacionados = new Label("Projetos Relacionados");

		listboxProjetosRelacionados.setMultiple(true);
		listboxProjetosRelacionados.setCheckmark(true);

		Listhead colunasProjetosRelacionados = new Listhead();
		Listheader colunaTitulo2 = new Listheader("Título");

		listboxProjetosRelacionados.setMultiple(true);
		listboxProjetosRelacionados.setCheckmark(true);

		linhaProjetosRelacionados.setParent(rows);
		labelProjetosRelacionados.setParent(linhaProjetosRelacionados);
		listboxProjetosRelacionados.setParent(linhaProjetosRelacionados);
		colunasProjetosRelacionados.setParent(listboxProjetosRelacionados);
		colunaTitulo2.setParent(colunasProjetosRelacionados);

		// Preenche projetos no listbox
		Collection<Projeto> projetos = licaoAprendida.getProjetos();
		Collection<Projeto> projetosTodos = ctrlGerenciaConhecimento.aplCadastrarProjeto.recuperarTodos();
		for (Projeto projeto : projetosTodos){
			Listitem listitem = new Listitem(projeto.getNome());
			if(projetos.contains(projeto)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(projeto);
			listitem.setParent(listboxProjetosRelacionados);
		}

		//////////////////////////////////////////
		// Atividades relacionadas
		//////////////////////////////////////////
		Row linhaAtividadesRelacionadas = new Row();

		Label labelAtividadesRelacionadas = new Label("Atividades Relacionados");

		listboxAtividadesRelacionadas.setMultiple(true);
		listboxAtividadesRelacionadas.setCheckmark(true);

		Listhead colunasAtividadesRelacionadas = new Listhead();
		Listheader colunaTitulo3 = new Listheader("Título");

		listboxAtividadesRelacionadas.setMultiple(true);
		listboxAtividadesRelacionadas.setCheckmark(true);

		linhaAtividadesRelacionadas.setParent(rows);
		labelAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);
		listboxAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);
		colunasAtividadesRelacionadas.setParent(listboxAtividadesRelacionadas);
		colunaTitulo3.setParent(colunasAtividadesRelacionadas);

		// Preenche atividades no listbox
		Collection<KAtividade> atividades = licaoAprendida.getkAtividades();
		Collection<KAtividade> atividadesTodas = ctrlGerenciaConhecimento.aplCadastrarKAtividade.recuperarTodos();
		for (KAtividade atividade : atividadesTodas){
			Listitem listitem = new Listitem(atividade.getNome());
			if(atividades.contains(atividade)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(atividade);
			listitem.setParent(listboxAtividadesRelacionadas);
		}

		//////////////////////////////////////////
		// Itens relacionados
		//////////////////////////////////////////
		Row linhaItensRelacionados = new Row();

		Label labelItensRelacionados = new Label("Itens Relacionados");

		listboxItensRelacionados.setMultiple(true);
		listboxItensRelacionados.setCheckmark(true);

		Listhead colunasItensRelacionados = new Listhead();
		Listheader colunaTitulo4 = new Listheader("Título");

		listboxItensRelacionados.setMultiple(true);
		listboxItensRelacionados.setCheckmark(true);

		linhaItensRelacionados.setParent(rows);
		labelItensRelacionados.setParent(linhaItensRelacionados);
		listboxItensRelacionados.setParent(linhaItensRelacionados);
		colunasItensRelacionados.setParent(listboxItensRelacionados);
		colunaTitulo4.setParent(colunasItensRelacionados);

		// Preenche itens no listbox
		Collection<ItemConhecimento> itens = licaoAprendida.getItensRelacionados();
		Collection<ItemConhecimento> itensTodos = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itensTodos){
			Listitem listitem = new Listitem(item.getTitulo());
			if(itens.contains(item)){ //
				listitem.setSelected(true);
			}
			listitem.setValue(item);
			listitem.setParent(listboxItensRelacionados);
		}
		
		rows.setParent(grid);
		grid.setParent(tabpainel);
		
		return tabpainel;

	}
	
	public Tabbox criarAbasCriarLicaoAprendida(){

		Tabbox tabbox = new Tabbox();
		tabbox.setHeight("380px");
		Tabs tabs = new Tabs();
		Tabpanels tabpanels = new Tabpanels();

		Tab infoGerais = new Tab("Informações Gerais");
		Tab infoEspecificas = new Tab("Informações Específicas");
		Tab associacoes = new Tab("Associações");

		infoGerais.setParent(tabs);
		infoEspecificas.setParent(tabs);
		associacoes.setParent(tabs);

		Tabpanel tabInfoGerais = criarTabInfoGerais();
		Tabpanel tabInfoEspecifica = criarTabInfoEspecifica();
		Tabpanel tabAssociacoes = criarTabAssociacoes();

		tabInfoGerais.setParent(tabpanels);
		tabInfoEspecifica.setParent(tabpanels);
		tabAssociacoes.setParent(tabpanels);

		tabpanels.setParent(tabbox);
		tabs.setParent(tabbox);

		return tabbox;

	}
	
	public void salvarLicaoAprendida() throws NucleoRegraNegocioExcecao{

		ctrlGerenciaConhecimento.salvarLicaoAprendida(preencherLicaoAprendida());

	}

	public LicaoAprendida preencherLicaoAprendida(){

		//LicaoAprendida la = new LicaoAprendida();
		
		//SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		licaoAprendida.setAutor(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
		licaoAprendida.setDataCriacao(licaoAprendida.getDataCriacao());
		licaoAprendida.setTitulo(textboxTitulo.getValue());
		licaoAprendida.setResumo(textboxResumo.getValue());
		licaoAprendida.setAplicabilidade(textboxAplicabilidade.getValue());
		licaoAprendida.setDescricaoProblema(textboxDescricaoProblema.getValue());
		licaoAprendida.setSolucaoAdotadaOuRecomendada(textboxSolucaoAdotadaRecomendada.getValue());
		licaoAprendida.setResultadoEsperado(textboxResultadoEsperado.getValue());

		if (sucesso.isChecked()){
			licaoAprendida.setTipo(LicaoAprendida.TIPO_SUCESSO);
		}else if (fracasso.isChecked()){
			licaoAprendida.setTipo(LicaoAprendida.TIPO_FRACASSO);
		}else if (informativa.isChecked()){
			licaoAprendida.setTipo(LicaoAprendida.TIPO_INFORMATIVA);
		}

		// preenche temas selecionados
		Set<Listitem> itens = listboxTemasRelacionados.getSelectedItems();
		Set<Tema> temas = new HashSet<Tema>();
		for (Listitem item : itens){
			temas.add((Tema)item.getValue());
		}
		licaoAprendida.getTemas().clear();
		licaoAprendida.setTemas(temas);

		// preenche projetos selecionados
		itens = listboxProjetosRelacionados.getSelectedItems();
		Set<Projeto> projetos = new HashSet<Projeto>();
		for (Listitem item : itens){
			projetos.add((Projeto)item.getValue());
		}
		licaoAprendida.getProjetos().clear();
		licaoAprendida.setProjetos(projetos);

		// preenche atividades selecionados
		itens = listboxAtividadesRelacionadas.getSelectedItems();
		Set<KAtividade> atividades = new HashSet<KAtividade>();
		for (Listitem item : itens){
			atividades.add((KAtividade)item.getValue());
		}
		licaoAprendida.getkAtividades().clear();
		licaoAprendida.setkAtividades(atividades);
		
		// preenche itens selecionados
		itens = listboxItensRelacionados.getSelectedItems();
		Set<ItemConhecimento> itensConhecimento = new HashSet<ItemConhecimento>();
		for (Listitem item : itens){
			itensConhecimento.add((ItemConhecimento)item.getValue());
		}
		licaoAprendida.getItensRelacionados().clear();
		licaoAprendida.setItensRelacionados(itensConhecimento);
		return licaoAprendida;

	}
	
	public void fecharJanela(){
		this.detach();
	}
	
	public void criarJanAlterarLicaoAprendida(){
		
		this.setTitle("Alterar Item de Conhecimento");
		this.setBorder("normal");
		
		Tabbox tabbox = criarAbasCriarLicaoAprendida();
		tabbox.setParent(this);

		Button botaoSalvar = new Button("Salvar");
		Button botaoCancelar = new Button("Cancelar");
		Toolbar toolbarInferior = new Toolbar();

		botaoSalvar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				salvarLicaoAprendida();
				Messagebox messageboxSalvar = new Messagebox();
				messageboxSalvar.show("Lição Aprendida salva com sucesso!", "Informação", Messagebox.OK, messageboxSalvar.INFORMATION);
				fecharJanela();
			}
		});

		botaoCancelar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub


				//Messagebox messageboxCancelar = new Messagebox();
				Messagebox.show("Deseja realmente cancelar a Lição Aprendida?", "Aviso", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						if (arg0.getName().equals("onYes")){
							Messagebox.show("Lição Aprendida cancelada!", "Informação", Messagebox.OK, Messagebox.INFORMATION);
							fecharJanela();
						}
					}
				});
			}
		});

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoSalvar);
		toolbarInferior.appendChild(botaoCancelar);

		toolbarInferior.setParent(this);
		
	}
	
}

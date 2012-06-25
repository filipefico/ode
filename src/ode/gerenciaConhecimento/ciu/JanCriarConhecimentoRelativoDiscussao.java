package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
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

public class JanCriarConhecimentoRelativoDiscussao extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;

	Textbox textboxTitulo;
	Label labelDataCriacaoValor;
	Textbox labelAutor;
	Textbox textboxResumo;
	Textbox textboxAplicabilidade;
	Textbox textboxConhecimentoAdquirido;
	Textbox textboxPontosFortes;
	Textbox textboxPontosFracos;
	Textbox textboxLinkDiscussao;

	Listbox listboxProjetosRelacionados = new Listbox();
	Listbox listboxTemasRelacionados = new Listbox();
	Listbox listboxKAtividadesRelacionadas = new Listbox();
	Listbox listboxItensConhecimentoRelacionados = new Listbox();


	public JanCriarConhecimentoRelativoDiscussao(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub

		ctrlGerenciaConhecimento = ctrl;

		criarJanCriarConhecimentoRelativoDiscussao();

	}

	public void salvar() throws NucleoRegraNegocioExcecao {

		ctrlGerenciaConhecimento.salvarConhecimentoRelativoDiscussao(preencherConhecimentoRelativoDiscussao());

	}

	public ConhecimentoRelativoDiscussao preencherConhecimentoRelativoDiscussao() {

		ConhecimentoRelativoDiscussao conhecimentoRelativoDiscussao = new ConhecimentoRelativoDiscussao();

		conhecimentoRelativoDiscussao.setAutor(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
		conhecimentoRelativoDiscussao.setDataCriacao(new Date());
		conhecimentoRelativoDiscussao.setTitulo(textboxTitulo.getValue());
		conhecimentoRelativoDiscussao.setResumo(textboxResumo.getValue());
		conhecimentoRelativoDiscussao.setAplicabilidade(textboxAplicabilidade.getValue());
		conhecimentoRelativoDiscussao.setConhecimentoAdquirido(textboxConhecimentoAdquirido.getValue());
		conhecimentoRelativoDiscussao.setPontosFortes(textboxPontosFortes.getValue());
		conhecimentoRelativoDiscussao.setPontosFracos(textboxPontosFracos.getValue());
		conhecimentoRelativoDiscussao.setLinkDiscussao(textboxLinkDiscussao.getValue());
		
		// preenche temas selecionados
		Set<Listitem> itens = listboxTemasRelacionados.getSelectedItems();
		Set<Tema> temas = new HashSet<Tema>();
		for (Listitem item : itens){
			temas.add((Tema)item.getValue());
		}
		conhecimentoRelativoDiscussao.getTemas().clear();
		conhecimentoRelativoDiscussao.setTemas(temas);
		
		// preenche projetos selecionados
		itens = listboxProjetosRelacionados.getSelectedItems();
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

		Tabbox tabbox = criarAbasCriarLicaoAprendida();
		tabbox.setParent(this);

		Button botaoSalvar = new Button("Salvar");
		Button botaoCancelar = new Button("Cancelar");
		Toolbar toolbarInferior = new Toolbar();

		botaoSalvar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				salvar();
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

	public void fecharJanela() {
		this.detach();
	}

	public Tabbox criarAbasCriarLicaoAprendida() {

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

	public Tabpanel criarTabInfoGerais() {

		Tabpanel tabpainel = new Tabpanel();

		Grid grid = new Grid();
		Rows rows = new Rows();

		// --- cria linha autor --- //

		Row linhaAutor = new Row();

		Label labelAutor = new Label("Autor:");
		Label labelDescricaoAutor = new Label();
		labelDescricaoAutor.setValue(NucleoContexto.recuperarUsuarioLogado()
				.getRecursoHumano().getNome());

		labelAutor.setParent(linhaAutor);
		labelDescricaoAutor.setParent(linhaAutor);

		// --- cria linha dataCriação --- //

		Row linhaDataCriacao = new Row();
		Label labelDataCriacao = new Label();
		labelDataCriacaoValor = new Label();

		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		labelDataCriacao.setValue("Data de Criação:");
		labelDataCriacaoValor.setValue(formatador.format(data));

		labelDataCriacao.setParent(linhaDataCriacao);
		labelDataCriacaoValor.setParent(linhaDataCriacao);

		// --- cria linha titulo --- //

		Row linhaTitulo = new Row();

		Label labelTitulo = new Label();
		textboxTitulo = new Textbox();

		labelTitulo.setValue("Título:");
		textboxTitulo.setText("");
		textboxTitulo.setWidth("430px");

		labelTitulo.setParent(linhaTitulo);
		textboxTitulo.setParent(linhaTitulo);

		// --- cria linha resumo --- //

		Row linhaResumo = new Row();

		Label labelResumo = new Label();
		textboxResumo = new Textbox();

		labelResumo.setValue("Resumo:");
		textboxResumo.setText("");
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
		textboxAplicabilidade.setText("");
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

	public Tabpanel criarTabInfoEspecifica() {

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
		textboxConhecimentoAdquirido.setText("");
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
		textboxPontosFortes.setText("");
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
		textboxPontosFracos.setText("");
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
		textboxLinkDiscussao.setText("");
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

	public Tabpanel criarTabAssociacoes() {

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

		listboxTemasRelacionados.setMultiple(true);
		listboxTemasRelacionados.setCheckmark(true);

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

		labelTemasRelacionados.setParent(linhaTemasRelacionados);
		listboxTemasRelacionados.setParent(linhaTemasRelacionados);

		//////////////////////////////////////////
		// Projetos relacionados
		//////////////////////////////////////////
		Row linhaProjetosRelacionados = new Row();

		Label labelProjetosRelacionados = new Label("Projetos Relacionados");

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
		Collection<KAtividade> atividades = ctrlGerenciaConhecimento.aplCadastrarKAtividade.recuperarTodos();
		for(KAtividade atividade : atividades){
			Listitem listitem = new Listitem(atividade.getNome());
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

		Collection<ItemConhecimento> itemConhecimento = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		
		for(ItemConhecimento itens : itemConhecimento){
			Listitem listitem = new Listitem(itens.getTitulo());
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

}

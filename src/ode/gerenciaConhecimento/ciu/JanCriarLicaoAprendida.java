package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
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

public class JanCriarLicaoAprendida extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;

	Textbox textboxTitulo;
	Textbox textboxDataCriacao;
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

	public JanCriarLicaoAprendida(CtrlGerenciaConhecimento ctrl) {

		ctrlGerenciaConhecimento = ctrl;
		cria();
	}

	public void salvar() throws NucleoRegraNegocioExcecao{

		ctrlGerenciaConhecimento.salvarLicaoAprendida(preencherLicaoAprendida());

	}

	public LicaoAprendida preencherLicaoAprendida(){

		LicaoAprendida la = new LicaoAprendida();

		la.setAutor(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
		la.setDataCriacao(new Date());
		la.setTitulo(textboxTitulo.getValue());
		la.setResumo(textboxResumo.getValue());
		la.setAplicabilidade(textboxAplicabilidade.getValue());
		la.setDescricaoProblema(textboxDescricaoProblema.getValue());
		la.setSolucaoAdotadaOuRecomendada(textboxSolucaoAdotadaRecomendada.getValue());
		la.setResultadoEsperado(textboxResultadoEsperado.getValue());

		if (sucesso.isChecked()){
			la.setTipo(LicaoAprendida.TIPO_SUCESSO);
		}else if (fracasso.isChecked()){
			la.setTipo(LicaoAprendida.TIPO_FRACASSO);
		}else if (informativa.isChecked()){
			la.setTipo(LicaoAprendida.TIPO_INFORMATIVA);
		}

		// preenche temas selecionados
		Set<Listitem> itens = listboxTemasRelacionados.getSelectedItems();
		List<Tema> temas = new ArrayList<Tema>();
		for (Listitem item : itens){
			temas.add((Tema)item.getValue());
		}
		la.getTemas().clear();
		la.setTemas(temas);

		// preenche projetos selecionados
		itens = listboxProjetosRelacionados.getSelectedItems();
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (Listitem item : itens){
			projetos.add((Projeto)item.getValue());
		}
		la.getProjetos().clear();
		la.setProjetos(projetos);

		// preenche atividades selecionados
		itens = listboxAtividadesRelacionadas.getSelectedItems();
		List<KAtividade> atividades = new ArrayList<KAtividade>();
		for (Listitem item : itens){
			atividades.add((KAtividade)item.getValue());
		}
		la.getkAtividades().clear();
		la.setkAtividades(atividades);
		
		// preenche itens selecionados
		itens = listboxItensRelacionados.getSelectedItems();
		List<ItemConhecimento> itensConhecimento = new ArrayList<ItemConhecimento>();
		for (Listitem item : itens){
			itensConhecimento.add((ItemConhecimento)item.getValue());
		}
		la.getItensRelacionados().clear();
		la.setItensRelacionados(itensConhecimento);
		return la;

	}

	public void cria(){

		this.setTitle("Criar Lição Aprendida");
		this.setBorder("normal");
		this.setClosable(true);

		Tabbox tabbox = criaAbasCriarLicaoAprendida();
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

	public void fecharJanela(){
		this.detach();
	}

	////////////////////////////////////////////
	/////// ??????????????????????????????????
	/////// PRESTE ATENCAO NO NOME DOS METODOS: deve ser no infinitivo. Altere para o padrao correto.
	public Tabbox criaAbasCriarLicaoAprendida(){

		Tabbox tabbox = new Tabbox();
		Tabs tabs = new Tabs();
		Tabpanels tabpanels = new Tabpanels();

		Tab infoGerais = new Tab("Informações Gerais");
		Tab infoEspecificas = new Tab("Informações Específicas");
		Tab associacoes = new Tab("Associações");

		infoGerais.setParent(tabs);
		infoEspecificas.setParent(tabs);
		associacoes.setParent(tabs);

		Tabpanel tabInfoGerais = criaTabInfoGerais();
		Tabpanel tabInfoEspecifica = criaTabInfoEspecifica();
		Tabpanel tabAssociacoes = criaTabAssociacoes();

		tabInfoGerais.setParent(tabpanels);
		tabInfoEspecifica.setParent(tabpanels);
		tabAssociacoes.setParent(tabpanels);

		tabpanels.setParent(tabbox);
		tabs.setParent(tabbox);

		return tabbox;

	}

	////////////////////////////////////////////
	/////// ??????????????????????????????????
	/////// PRESTE ATENCAO NO NOME DOS METODOS: deve ser no infinitivo. Altere para o padrao correto.
	public Tabpanel criaTabInfoGerais(){

		Tabpanel tabpainel = new Tabpanel();

		Grid grid = new Grid();
		Rows rows = new Rows();

		// --- cria linha autor --- //

		Row linhaAutor = new Row();

		Label labelAutor = new Label("Autor:");
		Label labelDescricaoAutor = new Label();
		labelDescricaoAutor.setValue(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano().getNome());

		labelAutor.setParent(linhaAutor);
		labelDescricaoAutor.setParent(linhaAutor);

		// --- cria linha dataCriação --- //

		Row linhaDataCriacao = new Row();
		Label labelDataCriacao = new Label();
		textboxDataCriacao = new Textbox();

		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 

		labelDataCriacao.setValue("Data de Criação:");
		textboxDataCriacao.setValue(formatador.format(data));
		textboxDataCriacao.setWidth("300px");

		labelDataCriacao.setParent(linhaDataCriacao);
		textboxDataCriacao.setParent(linhaDataCriacao);

		// --- cria linha titulo --- //

		Row linhaTitulo = new Row();

		Label labelTitulo = new Label();
		textboxTitulo = new Textbox();

		labelTitulo.setValue("Título:");
		textboxTitulo.setText("");
		textboxTitulo.setWidth("300px");

		labelTitulo.setParent(linhaTitulo);
		textboxTitulo.setParent(linhaTitulo);

		// --- cria linha resumo --- //

		Row linhaResumo = new Row();

		Label labelResumo = new Label();
		textboxResumo = new Textbox();

		labelResumo.setValue("Resumo:");
		textboxResumo.setText("");
		textboxResumo.setHeight("100px");
		textboxResumo.setWidth("300px");
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
		textboxAplicabilidade.setWidth("300px");
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

	//////////////////////////////////////////
	/////// ??????????????????????????????????
	/////// PRESTE ATENCAO NO NOME DOS METODOS: deve ser no infinitivo. Altere para o padrao correto.
	public Tabpanel criaTabInfoEspecifica(){

		Tabpanel tabpainel = new Tabpanel();

		Grid grid = new Grid();
		Rows rows = new Rows();

		// --- cria linha tipo --- //

		Row linhaTipo = new Row();

		Label labelTipo = new Label();
		radiogroup = new Radiogroup();
		sucesso = new Radio();
		fracasso = new Radio();
		informativa = new Radio();

		labelTipo.setValue("Tipo:");
		sucesso.setLabel(LicaoAprendida.TIPO_SUCESSO);
		fracasso.setLabel(LicaoAprendida.TIPO_FRACASSO);
		informativa.setLabel(LicaoAprendida.TIPO_INFORMATIVA);

		labelTipo.setParent(linhaTipo);
		sucesso.setParent(radiogroup);
		fracasso.setParent(radiogroup);
		informativa.setParent(radiogroup);
		radiogroup.setParent(linhaTipo);

		// --- cria linha descrição do problema --- //

		Row linhaDescricaoProblema = new Row();

		Label labelDescricaoProblema = new Label();
		textboxDescricaoProblema = new Textbox();

		labelDescricaoProblema.setValue("Descrição do Problema");
		textboxDescricaoProblema.setText("");
		textboxDescricaoProblema.setHeight("100px");
		textboxDescricaoProblema.setWidth("300px");
		textboxDescricaoProblema.setRows(3);

		labelDescricaoProblema.setParent(linhaDescricaoProblema);
		textboxDescricaoProblema.setParent(linhaDescricaoProblema);

		// --- cria linha solução adotada ou recomendada --- //

		Row linhaSolucaoAdotadaRecomendada = new Row();

		Label labelSolucaoAdotadaRecomendada = new Label();
		textboxSolucaoAdotadaRecomendada = new Textbox();

		labelSolucaoAdotadaRecomendada.setValue("Solução Adotada ou Recomendada:");
		textboxSolucaoAdotadaRecomendada.setText("");
		textboxSolucaoAdotadaRecomendada.setHeight("100px");
		textboxSolucaoAdotadaRecomendada.setWidth("300px");
		textboxSolucaoAdotadaRecomendada.setRows(3);

		labelSolucaoAdotadaRecomendada.setParent(linhaSolucaoAdotadaRecomendada);
		textboxSolucaoAdotadaRecomendada.setParent(linhaSolucaoAdotadaRecomendada);

		// --- cria linha resultado esperado --- //

		Row linhaResultadoEsperado = new Row();

		Label labelResultadoEsperado = new Label();
		textboxResultadoEsperado = new Textbox();

		labelResultadoEsperado.setValue("Resultado Esperado:");
		textboxResultadoEsperado.setText("");
		textboxResultadoEsperado.setHeight("100px");
		textboxResultadoEsperado.setWidth("300px");
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

	////////////////////////////////////////////
	/////// ??????????????????????????????????
	/////// PRESTE ATENCAO NO NOME DOS METODOS: deve ser no infinitivo. Altere para o padrao correto.
	public Tabpanel criaTabAssociacoes(){

		Tabpanel tabpainel = new Tabpanel();
		tabpainel.setStyle("overflow:auto;");
		tabpainel.setHeight("400px");

		Grid grid = new Grid();
		Rows rows = new Rows();

		//////////////////////////////////////////
		// Domínios relacionados
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
		Collection<Tema> temas = ctrlGerenciaConhecimento.aplCadastrarTema.recuperarTodos();
		for (Tema tema : temas){
			Listitem listitem = new Listitem(tema.getNome());
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
		Collection<Projeto> projetos = ctrlGerenciaConhecimento.aplCadastrarProjeto.recuperarTodos();
		for (Projeto projeto : projetos){
			Listitem listitem = new Listitem(projeto.getNome());
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
		Collection<KAtividade> atividades = ctrlGerenciaConhecimento.aplCadastrarKAtividade.recuperarTodos();
		for (KAtividade atividade : atividades){
			Listitem listitem = new Listitem(atividade.getNome());
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
		Collection<ItemConhecimento> itens = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itens){
			Listitem listitem = new Listitem(item.getTitulo());
			listitem.setValue(item);
			listitem.setParent(listboxItensRelacionados);
		}
		
		rows.setParent(grid);
		grid.setParent(tabpainel);
		
		return tabpainel;

	}
}

package ode.gerenciaConhecimento.ciu;

import groovy.ui.SystemOutputInterceptor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.controleProjeto.cdp.Projeto;
import ode.controleUsuario.cdp.Usuario;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
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
	
	Listbox listboxProjetosRelacionados = new Listbox();
	//?????? Declarar os outros listbox aqui 

	public JanCriarLicaoAprendida(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub

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
		
		
		
		// preencher projetos selecionados
		
		Set<Listitem> itens = listboxProjetosRelacionados.getSelectedItems();
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (Listitem item : itens){
			projetos.add((Projeto)item.getValue());
		}
		
		la.setProjetos(projetos);

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

		return tabpainel;
	}

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


		return tabpainel;

	}

	public Tabpanel criaTabAssociacoes(){

		Tabpanel tabpainel = new Tabpanel();

		Grid grid = new Grid();
		Rows rows = new Rows();

		// --- cria linha Dominios Relacionados --- //

		Row linhaDominiosRelacionados = new Row();

		Label labelDominiosRelacionados = new Label("Domínios Relacionados");

		Listbox listboxDominiosRelacionados = new Listbox();
		listboxDominiosRelacionados.setMultiple(true);
		listboxDominiosRelacionados.setCheckmark(true);

		Listhead colunasDominiosRelacionados = new Listhead();
		Listheader colunaTitulo1 = new Listheader("Título");

		colunaTitulo1.setParent(colunasDominiosRelacionados);
		colunasDominiosRelacionados.setParent(listboxDominiosRelacionados);

		Listitem linhaIndustriaNaval = new Listitem();
		Listcell celulaIndustriaNaval = new Listcell("Indústria Naval");

		celulaIndustriaNaval.setParent(linhaIndustriaNaval);
		linhaIndustriaNaval.setParent(listboxDominiosRelacionados);

		Listitem linhaMineração = new Listitem();
		Listcell celulaMineração = new Listcell("Mineração");

		celulaMineração.setParent(linhaMineração);
		linhaMineração.setParent(listboxDominiosRelacionados);

		labelDominiosRelacionados.setParent(linhaDominiosRelacionados);
		listboxDominiosRelacionados.setParent(linhaDominiosRelacionados);

		// --- cria linha Projetos Relacionados --- //

		Row linhaProjetosRelacionados = new Row();

		Label labelProjetosRelacionados = new Label("Projetos Relacionados");

		listboxProjetosRelacionados.setMultiple(true);
		listboxProjetosRelacionados.setCheckmark(true);
		
		Listhead colunasProjetosRelacionados = new Listhead();
		Listheader colunaNome = new Listheader("Nome");

		colunaNome.setParent(colunasProjetosRelacionados);
		colunasProjetosRelacionados.setParent(listboxProjetosRelacionados);
		
		// Adicionar itens no listbox projeto
		Collection<Projeto> projetos = ctrlGerenciaConhecimento.recuperarProjetos();
		for (Projeto projeto : projetos){

			Listitem linhaSistemaApoio = new Listitem();
			Listcell celulaSistemaApoio = new Listcell(projeto.getNome());
			linhaSistemaApoio.setValue(projeto);

			celulaSistemaApoio.setParent(linhaSistemaApoio);
			linhaSistemaApoio.setParent(listboxProjetosRelacionados);
			
		}

		labelProjetosRelacionados.setParent(linhaProjetosRelacionados);
		listboxProjetosRelacionados.setParent(linhaProjetosRelacionados);

		// --- cria linha Atividades Relacionados --- //

		Row linhaAtividadesRelacionadas = new Row();

		Label labelAtividadesRelacionadas = new Label("Atividades Relacionadas");

		Listbox listboxAtividadesRelacionadas = new Listbox();
		listboxAtividadesRelacionadas.setMultiple(true);
		listboxAtividadesRelacionadas.setCheckmark(true);

		Listhead colunasAtividadesRelacionadas = new Listhead();
		Listheader colunaTitulo2 = new Listheader("Título");

		colunaTitulo2.setParent(colunasAtividadesRelacionadas);
		colunasAtividadesRelacionadas.setParent(listboxAtividadesRelacionadas);

		Listitem linhaAnaliseRequisitos = new Listitem();
		Listcell celulaAnaliseRequisitos = new Listcell("Análise de Requisitos");

		celulaAnaliseRequisitos.setParent(linhaAnaliseRequisitos);
		linhaAnaliseRequisitos.setParent(listboxAtividadesRelacionadas);

		labelAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);
		listboxAtividadesRelacionadas.setParent(linhaAtividadesRelacionadas);

		// --- cria linha Itens de Conhecimento Relacionados --- //

		Row linhaItensConhecimentoRelacionados = new Row();

		Label labelItensConhecimentoRelacionadas = new Label("Itens de Conhecimento Relacionados");

		Listbox listboxItensConhecimentoRelacionados = new Listbox();
		listboxItensConhecimentoRelacionados.setMultiple(true);
		listboxItensConhecimentoRelacionados.setCheckmark(true);

		Listhead colunasItensConhecimentoRelacionados = new Listhead();
		Listheader colunaTitulo3 = new Listheader("Título");

		colunaTitulo3.setParent(colunasItensConhecimentoRelacionados);
		colunasItensConhecimentoRelacionados.setParent(listboxItensConhecimentoRelacionados);

		Listitem linhaBancoDadosRelacionais = new Listitem();
		Listcell celulaBancoDadosRelacionais = new Listcell("Bancos de Dados Relacionais");

		celulaBancoDadosRelacionais.setParent(linhaBancoDadosRelacionais);
		linhaBancoDadosRelacionais.setParent(listboxItensConhecimentoRelacionados);

		labelItensConhecimentoRelacionadas.setParent(linhaItensConhecimentoRelacionados);
		listboxItensConhecimentoRelacionados.setParent(linhaItensConhecimentoRelacionados);


		linhaDominiosRelacionados.setParent(rows);
		linhaProjetosRelacionados.setParent(rows);
		linhaAtividadesRelacionadas.setParent(rows);
		linhaItensConhecimentoRelacionados.setParent(rows);
		rows.setParent(grid);
		grid.setParent(tabpainel);
		grid.setSizedByContent(true);
		
		return tabpainel;

	}
}

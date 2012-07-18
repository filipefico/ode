package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.Date;

import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
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
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanVisualizarItemConhecimentoGerente extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Tabpanel tabpanelInformacoes;
	Tabpanel tabpanelAvaliacoes;
	Tabpanel tabpanelValoracoes;
	Listbox listboxInformacoes = new Listbox();
	Listbox listboxAvaliacoes = new Listbox();
	Listbox listboxValoracoes = new Listbox();
	Listitem listitemTitulo = new Listitem();
	Listcell listcellTituloValor;
	Listcell listcellAutorValor;
	Listcell listcellCriadoEmValor;
	Listcell listcellTipoItemConhecimentoValor;
	Listcell listcellResumoValor;
	Listcell listcellAplicabilidadeValor;
	Listcell listcellTipoLicaoAprendidaValor;
	Listcell listcellDescricaoProblemaValor;
	Listcell listcellSolucaoAdotadaRecomendadaValor;
	Listcell listcellResultadoEsperadoValor;
	Listcell listcellConhecimentoAdquiridoValor;
	Listcell listcellPontosFortesValor;
	Listcell listcellPontosFracosValor;
	Listcell listcellLinkDiscussaoValor;
	
	public JanVisualizarItemConhecimentoGerente(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
	
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanVisualizarItemConhecimento();
	}
	
	
	public void preencherListboxInformacoes(ItemConhecimento i){
		
		/*
		 * se o item de conhecimento for Lição Aprendida
		 * então a visualização é feita com a aba informações especificas
		 * de Lição Aprendida junto as outras abas
		 */
		
		if(i instanceof LicaoAprendida){
			
			LicaoAprendida item = (LicaoAprendida) i;
			
			//--- linha Título ---//
			Listcell listcellTitulo = new Listcell("Título:");
			listcellTituloValor = new Listcell(item.getTitulo());
			
			listcellTitulo.setParent(listitemTitulo);
			listcellTituloValor.setParent(listitemTitulo);
			listitemTitulo.setParent(listboxInformacoes);
			
			//--- linha Autor ---//
			Listitem listitemAutor = new Listitem();
			Listcell listcellAutor = new Listcell("Autor:");
			listcellAutorValor = new Listcell("AUTORRRR");
			
			listcellAutor.setParent(listitemAutor);
			listcellAutorValor.setParent(listitemAutor);
			listitemAutor.setParent(listboxInformacoes);
			
			//--- linha Criado Em ---//
			Listitem listitemCriadoEm = new Listitem();
			Listcell listcellCriadoEm = new Listcell("Criado em:");
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			listcellCriadoEmValor = new Listcell(formatador.format(item.getDataCriacao()));
			
			listcellCriadoEm.setParent(listitemCriadoEm);
			listcellCriadoEmValor.setParent(listitemCriadoEm);
			listitemCriadoEm.setParent(listboxInformacoes);
			
			//--- linha Tipo de Item de Conhecimento ---//				
			Listitem listitemTipoItemConhecimento = new Listitem();
			Listcell listcellTipoItemConhecimento = new Listcell("Tipo de Item de Conhecimento:");
			listcellTipoItemConhecimentoValor = new Listcell("Lição Aprendida");
			
			listcellTipoItemConhecimento.setParent(listitemTipoItemConhecimento);
			listcellTipoItemConhecimentoValor.setParent(listitemTipoItemConhecimento);
			listitemTipoItemConhecimento.setParent(listboxInformacoes);
		
			//--- linha Resumo ---//
			Listitem listitemResumo = new Listitem();
			Listcell listcellResumo = new Listcell("Resumo:");
			listcellResumoValor = new Listcell(item.getResumo());
			
			listcellResumo.setParent(listitemResumo);
			listcellResumoValor.setParent(listitemResumo);
			listitemResumo.setParent(listboxInformacoes);
			
			//--- linha Aplicabilidade ---//
			Listitem listitemAplicabilidade = new Listitem();
			Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
			listcellAplicabilidadeValor = new Listcell(item.getAplicabilidade());
			
			listcellAplicabilidade.setParent(listitemAplicabilidade);
			listcellAplicabilidadeValor.setParent(listitemAplicabilidade);
			listitemAplicabilidade.setParent(listboxInformacoes);
			
			//--- linha Tipo de Lição Aprendida ---//
			Listitem listitemTipoLicaoAprendida = new Listitem();
			Listcell listcellTipoLicaoAprendida = new Listcell("Tipo de Lição Aprendida:");
			listcellTipoLicaoAprendidaValor = new Listcell(item.getTipo());
			
			listcellTipoLicaoAprendida.setParent(listitemTipoLicaoAprendida);
			listcellTipoLicaoAprendidaValor.setParent(listitemTipoLicaoAprendida);
			listitemTipoLicaoAprendida.setParent(listboxInformacoes);
			
			//--- linha Descrição do Problema ---//
			Listitem listitemDescricaoProblema = new Listitem();
			Listcell listcellDescricaoProblema = new Listcell("Descrição do Problema:");
			listcellDescricaoProblemaValor = new Listcell(item.getDescricaoProblema());
			
			listcellDescricaoProblema.setParent(listitemDescricaoProblema);
			listcellDescricaoProblemaValor.setParent(listitemDescricaoProblema);
			listitemDescricaoProblema.setParent(listboxInformacoes);
			
			//--- linha Solução Adotada ou Recomendada ---//
			Listitem listitemSolucaoAdotadaRecomendada = new Listitem();
			Listcell listcellSolucaoAdotadaRecomendada = new Listcell("Solução Adotada ou Recomendada:");
			listcellSolucaoAdotadaRecomendadaValor = new Listcell(item.getSolucaoAdotadaOuRecomendada());
			
			listcellSolucaoAdotadaRecomendada.setParent(listitemSolucaoAdotadaRecomendada);
			listcellSolucaoAdotadaRecomendadaValor.setParent(listitemSolucaoAdotadaRecomendada);
			listitemSolucaoAdotadaRecomendada.setParent(listboxInformacoes);
			
			//--- linha Resultado Esperado ---//
			Listitem listitemResultadoEsperado = new Listitem();
			Listcell listcellResultadoEsperado = new Listcell("Resultado Esperado:");
			listcellResultadoEsperadoValor = new Listcell(item.getResultadoEsperado());
			
			listcellResultadoEsperado.setParent(listitemResultadoEsperado);
			listcellResultadoEsperadoValor.setParent(listitemResultadoEsperado);
			listitemResultadoEsperado.setParent(listboxInformacoes);
		}
		
		/*
		 * se o item de conhecimento for Conhecimento Relativo a uma dicussão
		 * então a visualização é feita com a aba informações especificas
		 * de Conhecimento Relativo a uma Discussão junto as outras abas
		 */
		
		if(i instanceof ConhecimentoRelativoDiscussao){
			
			ConhecimentoRelativoDiscussao item = (ConhecimentoRelativoDiscussao) i;
			
			//--- linha Título ---//
			Listcell listcellTitulo = new Listcell("Título:");
			listcellTituloValor = new Listcell(item.getTitulo());
			
			listcellTitulo.setParent(listitemTitulo);
			listcellTituloValor.setParent(listitemTitulo);
			listitemTitulo.setParent(listboxInformacoes);
			
			//--- linha Autor ---//
			Listitem listitemAutor = new Listitem();
			Listcell listcellAutor = new Listcell("Autor:");
			listcellAutorValor = new Listcell("AUTORRRR");
			
			listcellAutor.setParent(listitemAutor);
			listcellAutorValor.setParent(listitemAutor);
			listitemAutor.setParent(listboxInformacoes);
			
			//--- linha Criado Em ---//
			Listitem listitemCriadoEm = new Listitem();
			Listcell listcellCriadoEm = new Listcell("Criado em:");
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			listcellCriadoEmValor = new Listcell(formatador.format(item.getDataCriacao()));
			
			listcellCriadoEm.setParent(listitemCriadoEm);
			listcellCriadoEmValor.setParent(listitemCriadoEm);
			listitemCriadoEm.setParent(listboxInformacoes);
			
			//--- linha Tipo de Item de Conhecimento ---//
			Listitem listitemTipoItemConhecimento = new Listitem();
			Listcell listcellTipoItemConhecimento = new Listcell("Tipo de Item de Conhecimento:");
			listcellTipoItemConhecimentoValor = new Listcell("Conhecimento Relativo a uma Discussão");
			
			listcellTipoItemConhecimento.setParent(listitemTipoItemConhecimento);
			listcellTipoItemConhecimentoValor.setParent(listitemTipoItemConhecimento);
			listitemTipoItemConhecimento.setParent(listboxInformacoes);
			
			//--- linha Resumo ---//
			Listitem listitemResumo = new Listitem();
			Listcell listcellResumo = new Listcell("Resumo:");
			listcellResumoValor = new Listcell(item.getResumo());
			
			listcellResumo.setParent(listitemResumo);
			listcellResumoValor.setParent(listitemResumo);
			listitemResumo.setParent(listboxInformacoes);
			
			//--- linha Aplicabilidade ---//
			Listitem listitemAplicabilidade = new Listitem();
			Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
			listcellAplicabilidadeValor = new Listcell(item.getAplicabilidade());
			
			listcellAplicabilidade.setParent(listitemAplicabilidade);
			listcellAplicabilidadeValor.setParent(listitemAplicabilidade);
			listitemAplicabilidade.setParent(listboxInformacoes);
			
			//--- linha Conhecimento Adquirido ---//
			Listitem listitemConhecimentoAdquirido = new Listitem();
			Listcell listcellConhecimentoAdquirido = new Listcell("Conhecimento Adquirido:");
			listcellConhecimentoAdquiridoValor = new Listcell(item.getConhecimentoAdquirido());
			
			listcellConhecimentoAdquirido.setParent(listitemConhecimentoAdquirido);
			listcellConhecimentoAdquiridoValor.setParent(listitemConhecimentoAdquirido);
			listitemConhecimentoAdquirido.setParent(listboxInformacoes);
			
			//--- linha Pontos Fortes ---//
			Listitem listitemPontosFortes = new Listitem();
			Listcell listcellPontosFortes = new Listcell("Pontos Fortes:");
			listcellPontosFortesValor = new Listcell(item.getPontosFortes());
			
			listcellPontosFortes.setParent(listitemPontosFortes);
			listcellPontosFortesValor.setParent(listitemPontosFortes);
			listitemPontosFortes.setParent(listboxInformacoes);
			
			//--- linha Pontos Fracos ---//
			Listitem listitemPontosFracos = new Listitem();
			Listcell listcellPontosFracos = new Listcell("Pontos Fracos:");
			listcellPontosFracosValor = new Listcell(item.getPontosFracos());
			
			listcellPontosFracos.setParent(listitemPontosFracos);
			listcellPontosFracosValor.setParent(listitemPontosFracos);
			listitemPontosFracos.setParent(listboxInformacoes);
			
			//--- linha Resultado Esperado ---//
			Listitem listitemLinkDiscussao = new Listitem();
			Listcell listcellLinkDiscussao = new Listcell("Link para a discussão:");
			listcellLinkDiscussaoValor = new Listcell(item.getLinkDiscussao());
			
			listcellLinkDiscussao.setParent(listitemLinkDiscussao);
			listcellLinkDiscussaoValor.setParent(listitemLinkDiscussao);
			listitemLinkDiscussao.setParent(listboxInformacoes);
		}
		
	}
	
	public void criarAbaInformacoes(){
		
		tabpanelInformacoes = new Tabpanel();
		
		Hbox hboxInformacoes = new Hbox();
		
		listboxInformacoes.setSizedByContent(true);
		listboxInformacoes.setWidth("300px");
		listboxInformacoes.setHeight("400px");
		
		//cria licao aprendida teste
		LicaoAprendida licaoTESTE = new LicaoAprendida();
		licaoTESTE.setTitulo("tituloTESTE");
		//licaoTESTE.setAutor(NucleoContexto.recuperarUsuarioLogado().)
		licaoTESTE.setDataCriacao(new Date());
		licaoTESTE.setResumo("resumoTESTE");
		licaoTESTE.setResultadoEsperado("resumoTESTE");
		licaoTESTE.setAplicabilidade("aplicabilidadeTESTE");
		licaoTESTE.setTipo("sucesso");
		licaoTESTE.setDescricaoProblema("descricaoProblemaTESTE");
		licaoTESTE.setSolucaoAdotadaOuRecomendada("solucaoAdotadaOuRecomendadaTESTE");
		licaoTESTE.setResultadoEsperado("resultadoEsperadoTESTE");
		
		//cria conhecimento relativo a uma discussao teste
		ConhecimentoRelativoDiscussao conheTESTE = new ConhecimentoRelativoDiscussao();
		conheTESTE.setTitulo("tituloTESTE");
		//conheTESTE(NucleoContexto.recuperarUsuarioLogado().)
		conheTESTE.setDataCriacao(new Date());
		conheTESTE.setResumo("resumoTESTE");
		conheTESTE.setAplicabilidade("aplicabilidadeTESTE");
		conheTESTE.setConhecimentoAdquirido("conhecimentoAdquiridoTESTE");
		conheTESTE.setPontosFortes("pontosFortesTESTE");
		conheTESTE.setPontosFracos("pontosFracosTESTE");
		conheTESTE.setLinkDiscussao("linkDiscussaoTESTE");
		
		preencherListboxInformacoes(licaoTESTE);
		
		listboxInformacoes.setParent(hboxInformacoes);
			
		Vbox vboxInformacoes = new Vbox();
		
		Label labelResumoValoracoes = new Label("Resumo das Valorações:");
		labelResumoValoracoes.setStyle("font-weight: bold; color: blue;");
		Label labelMediaNotas = new Label("Média das Notas: " + "INTEIRO");
		Label labelQuantidade = new Label("Quantidade: " + "INTEIRO");
		Label labelPositivas = new Label("Positivas: " + "INTEIRO" + "%");
		Label labelNegativas = new Label("Negativas: " + "INTEIRO" + "%");
		Label labelNeutras = new Label("Neutras: " + "INTEIRO" + "%");
		Label labelAcessos = new Label("Acessos:");
		labelAcessos.setStyle("font-weight: bold; color: blue;");
		Label labelQtdeAcessos = new Label("Quantidade: " + "INTEIRO");
		Label labelProjetosRelacionados = new Label("Projetos Relacionados:");
		labelProjetosRelacionados.setStyle("font-weight: bold; color: blue;");
		Label labelAtividadesRelacionados = new Label("Atividades Relacionadas:");
		labelAtividadesRelacionados.setStyle("font-weight: bold; color: blue;");
		Label labelDominiosRelacionados = new Label("Domínios Relacionados:");
		labelDominiosRelacionados.setStyle("font-weight: bold; color: blue;");
		Label labelItensRelacionados = new Label("Itens Relacionados:");
		labelItensRelacionados.setStyle("font-weight: bold; color: blue;");
		
		labelResumoValoracoes.setParent(vboxInformacoes);
		labelMediaNotas.setParent(vboxInformacoes);
		labelQuantidade.setParent(vboxInformacoes);
		labelPositivas.setParent(vboxInformacoes);
		labelNegativas.setParent(vboxInformacoes);
		labelNeutras.setParent(vboxInformacoes);
		labelAcessos.setParent(vboxInformacoes);
		labelQtdeAcessos.setParent(vboxInformacoes);
		labelProjetosRelacionados.setParent(vboxInformacoes);
		labelAtividadesRelacionados.setParent(vboxInformacoes);
		labelDominiosRelacionados.setParent(vboxInformacoes);
		labelItensRelacionados.setParent(vboxInformacoes);
		
		vboxInformacoes.setParent(hboxInformacoes);
		
		hboxInformacoes.setParent(tabpanelInformacoes);
	}
	
	public void criarAbaAvaliacoes(){
		
		tabpanelAvaliacoes = new Tabpanel();
		
		listboxAvaliacoes.setHeight("400px");
		
		Listhead listheadAvaliacoes = new Listhead();
		
		Listheader listheaderInformacoes = new Listheader("Informações");
		Listheader listheaderNotas = new Listheader("Notas");
		Listheader listheaderResultado = new Listheader("Resultado");
		
		listheaderInformacoes.setParent(listheadAvaliacoes);
		listheaderNotas.setParent(listheadAvaliacoes);
		listheaderResultado.setParent(listheadAvaliacoes);
		
		listheadAvaliacoes.setParent(listboxAvaliacoes);
		
		Listitem listitem = new Listitem();
		
		//coluna informações
		Listcell listcellInformacoes = new Listcell();
		Vbox vboxInformacoes = new Vbox();
		
		Label labelAutor = new Label("Autor: " + "");
		Label labelDataAvaliacao = new Label("Data de Avaliação: " + "");
		
		labelAutor.setParent(vboxInformacoes);
		labelDataAvaliacao.setParent(vboxInformacoes);
		
		vboxInformacoes.setParent(listcellInformacoes);
		listcellInformacoes.setParent(listitem);
		listitem.setParent(listboxAvaliacoes);
		
		//coluna Notas
		Listcell listcellNotas = new Listcell();
		Vbox vboxNotas = new Vbox();
		
		Label labelCorrecao = new Label("Correção: " + "");
		Label labelCompletude = new Label("Completude: " + "");
		Label labelConsistencia = new Label("Consistencia: " + "");
		Label labelUtilidade = new Label("Utilidade: " + "");
		Label labelAplicabilidade = new Label("Aplicabilidade: " + "");
		Label labelMediaNotas = new Label("Media das Notas: " + "");
		
		labelCorrecao.setParent(vboxNotas);
		labelCompletude.setParent(vboxNotas);
		labelConsistencia.setParent(vboxNotas);
		labelUtilidade.setParent(vboxNotas);
		labelAplicabilidade.setParent(vboxNotas);
		labelMediaNotas.setParent(vboxNotas);
		
		vboxNotas.setParent(listcellNotas);
		listcellNotas.setParent(listitem);
		listitem.setParent(listboxAvaliacoes);
		
		//coluna Resultado
		Listcell listcellResultado = new Listcell();
		Vbox vboxResultado = new Vbox();
		
		Label labelParecer = new Label("Parecer: " + "");
		Label labelResultadoFinal = new Label("Resultado Final: " + "");
		
		labelParecer.setParent(vboxResultado);
		labelResultadoFinal.setParent(vboxResultado);
		
		vboxResultado.setParent(listcellResultado);
		listcellResultado.setParent(listitem);
		listitem.setParent(listboxAvaliacoes);
		
		listboxAvaliacoes.setParent(tabpanelAvaliacoes);
		
	}
	
	public void criarAbaValoracoes(){
		
		tabpanelValoracoes = new Tabpanel();
		
		listboxValoracoes.setHeight("400px");
		
		Listhead listheadValoracoes = new Listhead();
		Listheader listheaderValoracoes = new Listheader("Valorações");
		
		listheaderValoracoes.setParent(listheadValoracoes);
		listheadValoracoes.setParent(listboxValoracoes);
		
		Listitem listitem = new Listitem();
		Listcell listcellValoracoes = new Listcell();
		Vbox vboxValoracoes = new Vbox();
		
		Label labelAutor = new Label("Autor: " + "");
		Label labelValoradoEm = new Label("Valorado em: " + "");
		Label labelUtilidade = new Label("Utilidade: " + "");
		Label labelClassificacao = new Label("Classificação: " + "");
		Label labelComentario = new Label("Comentário: " + "");
		
		labelAutor.setParent(vboxValoracoes);
		labelValoradoEm.setParent(vboxValoracoes);
		labelUtilidade.setParent(vboxValoracoes);
		labelClassificacao.setParent(vboxValoracoes);
		labelComentario.setParent(vboxValoracoes);
		
		vboxValoracoes.setParent(listcellValoracoes);
		listcellValoracoes.setParent(listitem);
		listitem.setParent(listboxValoracoes);
		
		listboxValoracoes.setParent(tabpanelValoracoes);
		
	}
	
	public void criarJanVisualizarItemConhecimento(){
		
		this.setTitle("Visualizar Item de Conhecimento");
		this.setBorder("normal");
		
		Tabbox tabboxVisualizarItemConhecimento = new Tabbox();
		tabboxVisualizarItemConhecimento.setHeight("380px");
		Tabs tabsVisualizarItemConhecimento = new Tabs();
		
		Tab tabInformacoes = new Tab("Informações");
		Tab tabAvaliacoes = new Tab("Avaliações");
		Tab tabValoracoes = new Tab("Valorações");
		
		tabInformacoes.setParent(tabsVisualizarItemConhecimento);
		tabAvaliacoes.setParent(tabsVisualizarItemConhecimento);
		tabValoracoes.setParent(tabsVisualizarItemConhecimento);
		
		tabsVisualizarItemConhecimento.setParent(tabboxVisualizarItemConhecimento);
		
		Tabpanels tabpanelsVisualizarItemConhecimento = new Tabpanels();
		
		//criar Aba Informações
		criarAbaInformacoes();
		tabpanelInformacoes.setParent(tabpanelsVisualizarItemConhecimento);
		
		//criar Aba Avaliações
		criarAbaAvaliacoes();
		tabpanelAvaliacoes.setParent(tabpanelsVisualizarItemConhecimento);
		
		//criar Aba Valorações
		criarAbaValoracoes();
		tabpanelValoracoes.setParent(tabpanelsVisualizarItemConhecimento);
		
		tabpanelsVisualizarItemConhecimento.setParent(tabboxVisualizarItemConhecimento);
		tabboxVisualizarItemConhecimento.setParent(this);
		
		Button botaoVoltar = new Button("Voltar");
		Button botaoAlterar = new Button("Alterar");		
		Button botaoExcluir = new Button("Excluir");
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
		toolbarInferior.appendChild(botaoAlterar);
		toolbarInferior.appendChild(botaoExcluir);

		toolbarInferior.setParent(this);
	}
	
}

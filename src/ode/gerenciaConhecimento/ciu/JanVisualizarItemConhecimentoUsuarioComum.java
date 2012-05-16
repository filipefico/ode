package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.Date;

import ode._infraestruturaBase.util.NucleoContexto;
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
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanVisualizarItemConhecimentoUsuarioComum extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Listbox listbox = new Listbox();
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
	
	public JanVisualizarItemConhecimentoUsuarioComum(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
	
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanVisualizarItemConhecimento();
	}
	
	
	public void preencherListbox(ItemConhecimento i){
		
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
			listitemTitulo.setParent(listbox);
			
			//--- linha Autor ---//
			Listitem listitemAutor = new Listitem();
			Listcell listcellAutor = new Listcell("Autor:");
			listcellAutorValor = new Listcell("AUTORRRR");
			
			listcellAutor.setParent(listitemAutor);
			listcellAutorValor.setParent(listitemAutor);
			listitemAutor.setParent(listbox);
			
			//--- linha Criado Em ---//
			Listitem listitemCriadoEm = new Listitem();
			Listcell listcellCriadoEm = new Listcell("Criado em:");
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			listcellCriadoEmValor = new Listcell(formatador.format(item.getDataCriacao()));
			
			listcellCriadoEm.setParent(listitemCriadoEm);
			listcellCriadoEmValor.setParent(listitemCriadoEm);
			listitemCriadoEm.setParent(listbox);
			
			//--- linha Tipo de Item de Conhecimento ---//				
			Listitem listitemTipoItemConhecimento = new Listitem();
			Listcell listcellTipoItemConhecimento = new Listcell("Tipo de Item de Conhecimento:");
			listcellTipoItemConhecimentoValor = new Listcell("Lição Aprendida");
			
			listcellTipoItemConhecimento.setParent(listitemTipoItemConhecimento);
			listcellTipoItemConhecimentoValor.setParent(listitemTipoItemConhecimento);
			listitemTipoItemConhecimento.setParent(listbox);
		
			//--- linha Resumo ---//
			Listitem listitemResumo = new Listitem();
			Listcell listcellResumo = new Listcell("Resumo:");
			listcellResumoValor = new Listcell(item.getResumo());
			
			listcellResumo.setParent(listitemResumo);
			listcellResumoValor.setParent(listitemResumo);
			listitemResumo.setParent(listbox);
			
			//--- linha Aplicabilidade ---//
			Listitem listitemAplicabilidade = new Listitem();
			Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
			listcellAplicabilidadeValor = new Listcell(item.getAplicabilidade());
			
			listcellAplicabilidade.setParent(listitemAplicabilidade);
			listcellAplicabilidadeValor.setParent(listitemAplicabilidade);
			listitemAplicabilidade.setParent(listbox);
			
			//--- linha Tipo de Lição Aprendida ---//
			Listitem listitemTipoLicaoAprendida = new Listitem();
			Listcell listcellTipoLicaoAprendida = new Listcell("Tipo de Lição Aprendida:");
			listcellTipoLicaoAprendidaValor = new Listcell(item.getTipo());
			
			listcellTipoLicaoAprendida.setParent(listitemTipoLicaoAprendida);
			listcellTipoLicaoAprendidaValor.setParent(listitemTipoLicaoAprendida);
			listitemTipoLicaoAprendida.setParent(listbox);
			
			//--- linha Descrição do Problema ---//
			Listitem listitemDescricaoProblema = new Listitem();
			Listcell listcellDescricaoProblema = new Listcell("Descrição do Problema:");
			listcellDescricaoProblemaValor = new Listcell(item.getDescricaoProblema());
			
			listcellDescricaoProblema.setParent(listitemDescricaoProblema);
			listcellDescricaoProblemaValor.setParent(listitemDescricaoProblema);
			listitemDescricaoProblema.setParent(listbox);
			
			//--- linha Solução Adotada ou Recomendada ---//
			Listitem listitemSolucaoAdotadaRecomendada = new Listitem();
			Listcell listcellSolucaoAdotadaRecomendada = new Listcell("Solução Adotada ou Recomendada:");
			listcellSolucaoAdotadaRecomendadaValor = new Listcell(item.getSolucaoAdotadaOuRecomendada());
			
			listcellSolucaoAdotadaRecomendada.setParent(listitemSolucaoAdotadaRecomendada);
			listcellSolucaoAdotadaRecomendadaValor.setParent(listitemSolucaoAdotadaRecomendada);
			listitemSolucaoAdotadaRecomendada.setParent(listbox);
			
			//--- linha Resultado Esperado ---//
			Listitem listitemResultadoEsperado = new Listitem();
			Listcell listcellResultadoEsperado = new Listcell("Resultado Esperado:");
			listcellResultadoEsperadoValor = new Listcell(item.getResultadoEsperado());
			
			listcellResultadoEsperado.setParent(listitemResultadoEsperado);
			listcellResultadoEsperadoValor.setParent(listitemResultadoEsperado);
			listitemResultadoEsperado.setParent(listbox);
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
			listitemTitulo.setParent(listbox);
			
			//--- linha Autor ---//
			Listitem listitemAutor = new Listitem();
			Listcell listcellAutor = new Listcell("Autor:");
			listcellAutorValor = new Listcell("AUTORRRR");
			
			listcellAutor.setParent(listitemAutor);
			listcellAutorValor.setParent(listitemAutor);
			listitemAutor.setParent(listbox);
			
			//--- linha Criado Em ---//
			Listitem listitemCriadoEm = new Listitem();
			Listcell listcellCriadoEm = new Listcell("Criado em:");
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			listcellCriadoEmValor = new Listcell(formatador.format(item.getDataCriacao()));
			
			listcellCriadoEm.setParent(listitemCriadoEm);
			listcellCriadoEmValor.setParent(listitemCriadoEm);
			listitemCriadoEm.setParent(listbox);
			
			//--- linha Tipo de Item de Conhecimento ---//
			Listitem listitemTipoItemConhecimento = new Listitem();
			Listcell listcellTipoItemConhecimento = new Listcell("Tipo de Item de Conhecimento:");
			listcellTipoItemConhecimentoValor = new Listcell("Conhecimento Relativo a uma Discussão");
			
			listcellTipoItemConhecimento.setParent(listitemTipoItemConhecimento);
			listcellTipoItemConhecimentoValor.setParent(listitemTipoItemConhecimento);
			listitemTipoItemConhecimento.setParent(listbox);
			
			//--- linha Resumo ---//
			Listitem listitemResumo = new Listitem();
			Listcell listcellResumo = new Listcell("Resumo:");
			listcellResumoValor = new Listcell(item.getResumo());
			
			listcellResumo.setParent(listitemResumo);
			listcellResumoValor.setParent(listitemResumo);
			listitemResumo.setParent(listbox);
			
			//--- linha Aplicabilidade ---//
			Listitem listitemAplicabilidade = new Listitem();
			Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
			listcellAplicabilidadeValor = new Listcell(item.getAplicabilidade());
			
			listcellAplicabilidade.setParent(listitemAplicabilidade);
			listcellAplicabilidadeValor.setParent(listitemAplicabilidade);
			listitemAplicabilidade.setParent(listbox);
			
			//--- linha Conhecimento Adquirido ---//
			Listitem listitemConhecimentoAdquirido = new Listitem();
			Listcell listcellConhecimentoAdquirido = new Listcell("Conhecimento Adquirido:");
			listcellConhecimentoAdquiridoValor = new Listcell(item.getConhecimentoAdquirido());
			
			listcellConhecimentoAdquirido.setParent(listitemConhecimentoAdquirido);
			listcellConhecimentoAdquiridoValor.setParent(listitemConhecimentoAdquirido);
			listitemConhecimentoAdquirido.setParent(listbox);
			
			//--- linha Pontos Fortes ---//
			Listitem listitemPontosFortes = new Listitem();
			Listcell listcellPontosFortes = new Listcell("Pontos Fortes:");
			listcellPontosFortesValor = new Listcell(item.getPontosFortes());
			
			listcellPontosFortes.setParent(listitemPontosFortes);
			listcellPontosFortesValor.setParent(listitemPontosFortes);
			listitemPontosFortes.setParent(listbox);
			
			//--- linha Pontos Fracos ---//
			Listitem listitemPontosFracos = new Listitem();
			Listcell listcellPontosFracos = new Listcell("Pontos Fracos:");
			listcellPontosFracosValor = new Listcell(item.getPontosFracos());
			
			listcellPontosFracos.setParent(listitemPontosFracos);
			listcellPontosFracosValor.setParent(listitemPontosFracos);
			listitemPontosFracos.setParent(listbox);
			
			//--- linha Resultado Esperado ---//
			Listitem listitemLinkDiscussao = new Listitem();
			Listcell listcellLinkDiscussao = new Listcell("Link para a discussão:");
			listcellLinkDiscussaoValor = new Listcell(item.getLinkDiscussao());
			
			listcellLinkDiscussao.setParent(listitemLinkDiscussao);
			listcellLinkDiscussaoValor.setParent(listitemLinkDiscussao);
			listitemLinkDiscussao.setParent(listbox);
		}
		
	}
	
	public void criarJanVisualizarItemConhecimento(){
		
		this.setTitle("Visualizar Item de Conhecimento");
		this.setBorder("normal");
		
		Hbox hbox = new Hbox();
		
		Tabbox tabboxVisualizarItemConhecimento = new Tabbox();
		Tabs tabsVisualizarItemConhecimento = new Tabs();
		
		Tab tabInformacoes = new Tab("Informações");
		Tab tabAvaliacoes = new Tab("Avaliações");
		Tab tabValoracoes = new Tab("Valorações");
		
		tabInformacoes.setParent(tabsVisualizarItemConhecimento);
		tabAvaliacoes.setParent(tabsVisualizarItemConhecimento);
		tabValoracoes.setParent(tabsVisualizarItemConhecimento);
		
		tabsVisualizarItemConhecimento.setParent(tabboxVisualizarItemConhecimento);
		
		Tabpanels tabpanelsVisualizarItemConhecimento = new Tabpanels();
		
		Tabpanel tabpanelInformacoes = new Tabpanel();
		
		listbox.setSizedByContent(true);
		listbox.setWidth("300px");
		listbox.setHeight("400px");
		
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
		
		preencherListbox(licaoTESTE);
		
		listbox.setParent(hbox);
			
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
		
		vboxInformacoes.setParent(hbox);
		
		hbox.setParent(tabpanelInformacoes);
		tabpanelInformacoes.setParent(tabpanelsVisualizarItemConhecimento);
		

		
		Tabpanel tabpanelAvaliacoes = new Tabpanel();
		Tabpanel tabpanelValoracoes = new Tabpanel();
		
		tabpanelsVisualizarItemConhecimento.setParent(tabboxVisualizarItemConhecimento);
		tabboxVisualizarItemConhecimento.setParent(this);
		
		Button botaoVoltar = new Button("Voltar");
		Button botaoValorar = new Button("Valorar");
		Toolbar toolbarInferior = new Toolbar();

		botaoVoltar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
			
			}
		});
		
		botaoValorar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaValorarItemConhecimento();
			}
		});

		

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVoltar);
		toolbarInferior.appendChild(botaoValorar);

		toolbarInferior.setParent(this);
	}
	
}

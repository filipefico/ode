package ode.gerenciaConhecimento.ciu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cdp.Valoracao;

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
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanVisualizarItemConhecimentoUsuarioComum extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Tab tabInformacoes = new Tab("Informações");
	Tab tabAvaliacoes = new Tab("Avaliações");
	Tab tabValoracoes = new Tab("Valorações");
	
	Tabpanel tabpanelInformacoes = new Tabpanel();
	Tabpanel tabpanelAvaliacoes = new Tabpanel();
	Tabpanel tabpanelValoracoes = new Tabpanel();
	
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
	
	ItemConhecimento itemConhecimento;
	
	Listitem listitemValoracao;
	Listitem listitemAvaliacao;
	Listbox listboxValoracoes = new Listbox();
	Listbox listboxAvaliacoes = new Listbox();
	
	float positiva = 0;
	float negativa = 0;
	float neutra = 0;
	int tamanho = 0;
	double somaValoracao = 0;
	
	Label qtdeValoracoes;
	Label percentualValoracoesPositivas;
	Label percentualValoracoesNegativas;
	Label percentualValoracoesNeutras;
	Label valoracaoMedia;
	
	public JanVisualizarItemConhecimentoUsuarioComum(CtrlGerenciaConhecimento ctrl, Object item) {
		// TODO Auto-generated constructor stub
	
		ctrlGerenciaConhecimento = ctrl;
		
		itemConhecimento = (ItemConhecimento) item;
		
		criarJanVisualizarItemConhecimento(item);
	}
	
	
	public void preencherVisualizacaoEsquerda(ItemConhecimento i){
		
		/*
		 * se o item de conhecimento for Lição Aprendida
		 * então a visualização é feita com a aba informações especificas
		 * de Lição Aprendida junto as outras abas
		 */
		
		if(i instanceof LicaoAprendida){
			
			LicaoAprendida item = (LicaoAprendida) i;
			
			Listhead listhead = new Listhead();
			Listheader listheader = new Listheader();
			listheader.setWidth("150px");
			Listheader listheader2 = new Listheader();
			
			listheader.setParent(listhead);
			listheader2.setParent(listhead);
			listhead.setParent(listbox);
			
			//--- linha Título ---//
			Listcell listcellTitulo = new Listcell("Título:");
			listcellTitulo.setStyle("font-weight: bold; color: black;");
			listcellTituloValor = new Listcell(item.getTitulo());
			
			listcellTitulo.setParent(listitemTitulo);
			listcellTituloValor.setParent(listitemTitulo);
			listitemTitulo.setParent(listbox);
			
			//--- linha Autor ---//
			Listitem listitemAutor = new Listitem();
			Listcell listcellAutor = new Listcell("Autor:");
			listcellAutor.setStyle("font-weight: bold; color: black;");
			listcellAutorValor = new Listcell(item.getAutor().getNome());
			
			listcellAutor.setParent(listitemAutor);
			listcellAutorValor.setParent(listitemAutor);
			listitemAutor.setParent(listbox);
			
			//--- linha Criado Em ---//
			Listitem listitemCriadoEm = new Listitem();
			Listcell listcellCriadoEm = new Listcell("Criado em:");
			listcellCriadoEm.setStyle("font-weight: bold; color: black;");
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			listcellCriadoEmValor = new Listcell(formatador.format(item.getDataCriacao()));
			
			listcellCriadoEm.setParent(listitemCriadoEm);
			listcellCriadoEmValor.setParent(listitemCriadoEm);
			listitemCriadoEm.setParent(listbox);
			
			//--- linha Tipo de Item de Conhecimento ---//				
			Listitem listitemTipoItemConhecimento = new Listitem();
			Listcell listcellTipoItemConhecimento = new Listcell("Tipo de Item de Conhecimento:");
			listcellTipoItemConhecimento.setStyle("font-weight: bold; color: black;");
			listcellTipoItemConhecimentoValor = new Listcell("Lição Aprendida");
			
			listcellTipoItemConhecimento.setParent(listitemTipoItemConhecimento);
			listcellTipoItemConhecimentoValor.setParent(listitemTipoItemConhecimento);
			listitemTipoItemConhecimento.setParent(listbox);
		
			//--- linha Resumo ---//
			Listitem listitemResumo = new Listitem();
			Listcell listcellResumo = new Listcell("Resumo:");
			listcellResumo.setStyle("font-weight: bold; color: black;");
			listcellResumoValor = new Listcell(item.getResumo());
			
			listcellResumo.setParent(listitemResumo);
			listcellResumoValor.setParent(listitemResumo);
			listitemResumo.setParent(listbox);
			
			//--- linha Aplicabilidade ---//
			Listitem listitemAplicabilidade = new Listitem();
			Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
			listcellAplicabilidade.setStyle("font-weight: bold; color: black;");
			listcellAplicabilidadeValor = new Listcell(item.getAplicabilidade());
			
			listcellAplicabilidade.setParent(listitemAplicabilidade);
			listcellAplicabilidadeValor.setParent(listitemAplicabilidade);
			listitemAplicabilidade.setParent(listbox);
			
			//--- linha Tipo de Lição Aprendida ---//
			Listitem listitemTipoLicaoAprendida = new Listitem();
			Listcell listcellTipoLicaoAprendida = new Listcell("Tipo de Lição Aprendida:");
			listcellTipoLicaoAprendida.setStyle("font-weight: bold; color: black;");
			listcellTipoLicaoAprendidaValor = new Listcell(item.getTipo());
			
			listcellTipoLicaoAprendida.setParent(listitemTipoLicaoAprendida);
			listcellTipoLicaoAprendidaValor.setParent(listitemTipoLicaoAprendida);
			listitemTipoLicaoAprendida.setParent(listbox);
			
			//--- linha Descrição do Problema ---//
			Listitem listitemDescricaoProblema = new Listitem();
			Listcell listcellDescricaoProblema = new Listcell("Descrição do Problema:");
			listcellDescricaoProblema.setStyle("font-weight: bold; color: black;");
			listcellDescricaoProblemaValor = new Listcell(item.getDescricaoProblema());
			
			listcellDescricaoProblema.setParent(listitemDescricaoProblema);
			listcellDescricaoProblemaValor.setParent(listitemDescricaoProblema);
			listitemDescricaoProblema.setParent(listbox);
			
			//--- linha Solução Adotada ou Recomendada ---//
			Listitem listitemSolucaoAdotadaRecomendada = new Listitem();
			Listcell listcellSolucaoAdotadaRecomendada = new Listcell("Solução Adotada ou Recomendada:");
			listcellSolucaoAdotadaRecomendada.setStyle("font-weight: bold; color: black;");
			listcellSolucaoAdotadaRecomendadaValor = new Listcell(item.getSolucaoAdotadaOuRecomendada());
			
			listcellSolucaoAdotadaRecomendada.setParent(listitemSolucaoAdotadaRecomendada);
			listcellSolucaoAdotadaRecomendadaValor.setParent(listitemSolucaoAdotadaRecomendada);
			listitemSolucaoAdotadaRecomendada.setParent(listbox);
			
			//--- linha Resultado Esperado ---//
			Listitem listitemResultadoEsperado = new Listitem();
			Listcell listcellResultadoEsperado = new Listcell("Resultado Esperado:");
			listcellResultadoEsperado.setStyle("font-weight: bold; color: black;");
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
			
			Listhead listhead = new Listhead();
			Listheader listheader = new Listheader();
			listheader.setWidth("150px");
			Listheader listheader2 = new Listheader();
			
			listheader.setParent(listhead);
			listheader2.setParent(listhead);
			listhead.setParent(listbox);
			
			//--- linha Título ---//
			Listcell listcellTitulo = new Listcell("Título:");
			listcellTitulo.setStyle("font-weight: bold; color: black;");
			listcellTituloValor = new Listcell(item.getTitulo());
			
			listcellTitulo.setParent(listitemTitulo);
			listcellTituloValor.setParent(listitemTitulo);
			listitemTitulo.setParent(listbox);
			
			//--- linha Autor ---//
			Listitem listitemAutor = new Listitem();
			Listcell listcellAutor = new Listcell("Autor:");
			listcellAutor.setStyle("font-weight: bold; color: black;");
			listcellAutorValor = new Listcell(item.getAutor().getNome());
			
			listcellAutor.setParent(listitemAutor);
			listcellAutorValor.setParent(listitemAutor);
			listitemAutor.setParent(listbox);
			
			//--- linha Criado Em ---//
			Listitem listitemCriadoEm = new Listitem();
			Listcell listcellCriadoEm = new Listcell("Criado em:");
			listcellCriadoEm.setStyle("font-weight: bold; color: black;");
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			listcellCriadoEmValor = new Listcell(formatador.format(item.getDataCriacao()));
			
			listcellCriadoEm.setParent(listitemCriadoEm);
			listcellCriadoEmValor.setParent(listitemCriadoEm);
			listitemCriadoEm.setParent(listbox);
			
			//--- linha Tipo de Item de Conhecimento ---//
			Listitem listitemTipoItemConhecimento = new Listitem();
			Listcell listcellTipoItemConhecimento = new Listcell("Tipo de Item de Conhecimento:");
			listcellTipoItemConhecimento.setStyle("font-weight: bold; color: black;");
			listcellTipoItemConhecimentoValor = new Listcell("Conhecimento Relativo a uma Discussão");
			
			listcellTipoItemConhecimento.setParent(listitemTipoItemConhecimento);
			listcellTipoItemConhecimentoValor.setParent(listitemTipoItemConhecimento);
			listitemTipoItemConhecimento.setParent(listbox);
			
			//--- linha Resumo ---//
			Listitem listitemResumo = new Listitem();
			Listcell listcellResumo = new Listcell("Resumo:");
			listcellResumo.setStyle("font-weight: bold; color: black;");
			listcellResumoValor = new Listcell(item.getResumo());
			
			listcellResumo.setParent(listitemResumo);
			listcellResumoValor.setParent(listitemResumo);
			listitemResumo.setParent(listbox);
			
			//--- linha Aplicabilidade ---//
			Listitem listitemAplicabilidade = new Listitem();
			Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
			listcellAplicabilidade.setStyle("font-weight: bold; color: black;");
			listcellAplicabilidadeValor = new Listcell(item.getAplicabilidade());
			
			listcellAplicabilidade.setParent(listitemAplicabilidade);
			listcellAplicabilidadeValor.setParent(listitemAplicabilidade);
			listitemAplicabilidade.setParent(listbox);
			
			//--- linha Conhecimento Adquirido ---//
			Listitem listitemConhecimentoAdquirido = new Listitem();
			Listcell listcellConhecimentoAdquirido = new Listcell("Conhecimento Adquirido:");
			listcellConhecimentoAdquirido.setStyle("font-weight: bold; color: black;");
			listcellConhecimentoAdquiridoValor = new Listcell(item.getConhecimentoAdquirido());
			
			listcellConhecimentoAdquirido.setParent(listitemConhecimentoAdquirido);
			listcellConhecimentoAdquiridoValor.setParent(listitemConhecimentoAdquirido);
			listitemConhecimentoAdquirido.setParent(listbox);
			
			//--- linha Pontos Fortes ---//
			Listitem listitemPontosFortes = new Listitem();
			Listcell listcellPontosFortes = new Listcell("Pontos Fortes:");
			listcellPontosFortes.setStyle("font-weight: bold; color: black;");
			listcellPontosFortesValor = new Listcell(item.getPontosFortes());
			
			listcellPontosFortes.setParent(listitemPontosFortes);
			listcellPontosFortesValor.setParent(listitemPontosFortes);
			listitemPontosFortes.setParent(listbox);
			
			//--- linha Pontos Fracos ---//
			Listitem listitemPontosFracos = new Listitem();
			Listcell listcellPontosFracos = new Listcell("Pontos Fracos:");
			listcellPontosFracos.setStyle("font-weight: bold; color: black;");
			listcellPontosFracosValor = new Listcell(item.getPontosFracos());
			
			listcellPontosFracos.setParent(listitemPontosFracos);
			listcellPontosFracosValor.setParent(listitemPontosFracos);
			listitemPontosFracos.setParent(listbox);
			
			//--- linha Resultado Esperado ---//
			Listitem listitemLinkDiscussao = new Listitem();
			Listcell listcellLinkDiscussao = new Listcell("Link para a discussão:");
			listcellLinkDiscussao.setStyle("font-weight: bold; color: black;");
			listcellLinkDiscussaoValor = new Listcell(item.getLinkDiscussao());
			
			listcellLinkDiscussao.setParent(listitemLinkDiscussao);
			listcellLinkDiscussaoValor.setParent(listitemLinkDiscussao);
			listitemLinkDiscussao.setParent(listbox);
		}
		
	}
	
	public void preencherVisualizacaoDireita(Hbox hbox, ItemConhecimento item){
		
		Vbox vboxInformacoes = new Vbox();
		
		Hbox hboxMediaNotas = new Hbox();
		Hbox hboxQuantidadeValoracoes = new Hbox();
		Hbox hboxPositivas = new Hbox();
		Hbox hboxNegativas = new Hbox();
		Hbox hboxNeutras = new Hbox();
		Hbox hboxQuantidadeAcessos = new Hbox();
		
		Label labelResumoValoracoes = new Label("Resumo das Valorações:");
		labelResumoValoracoes.setStyle("font-weight: bold; color: blue;");
		labelResumoValoracoes.setParent(vboxInformacoes);
		
		Collection<Valoracao> valoracoes = item.getValoracoes();
		
		tamanho = valoracoes.size();
		
		qtdeValoracoes = new Label(Integer.toString(tamanho));
		qtdeValoracoes.setStyle("font-weight: bold; font-size: 12px;");
		Label labelQuantidadeValoracoes = new Label("- Quantidade: ");
		labelQuantidadeValoracoes.setParent(hboxQuantidadeValoracoes);
		qtdeValoracoes.setParent(hboxQuantidadeValoracoes);
		
		for (Valoracao valoracao : valoracoes){
			
			BigDecimal bigDecimalPositiva1 = new BigDecimal("10.0");
			BigDecimal bigDecimalPositiva2 = new BigDecimal("0.01");
			
			BigDecimal bigDecimalNegativa1 = new BigDecimal("-10.0");
			BigDecimal bigDecimalNegativa2 = new BigDecimal("-0.01");
			
			BigDecimal bigDecimalNeutra = new BigDecimal("0.00");
			
			BigDecimal utilidade;
			utilidade = valoracao.getGrauUtilidade();

			somaValoracao = somaValoracao + utilidade.doubleValue();
			
			if(utilidade.doubleValue() >= bigDecimalNegativa1.doubleValue() && utilidade.doubleValue() <= bigDecimalNegativa2.doubleValue()){
				negativa++;
			}
			
			if(utilidade.doubleValue() == bigDecimalNeutra.doubleValue()){
				neutra++;
			}
			
			if(utilidade.doubleValue() >= bigDecimalPositiva2.doubleValue() && utilidade.doubleValue() <= bigDecimalPositiva1.doubleValue()){
				positiva++;
			}
		}
		
		
		Label labelPositivas = new Label();
		Label labelNegativas = new Label();
		Label labelNeutras = new Label();
		Label labelValoracaoMedia = new Label();
		
		if(tamanho != 0){
			
			DecimalFormat formatador = new DecimalFormat("0.0");
			
			//percentual de valorações positivas
			percentualValoracoesPositivas = new Label();
			percentualValoracoesPositivas.setValue(formatador.format((positiva/tamanho)*100) + "%");
			percentualValoracoesPositivas.setStyle("font-weight: bold; font-size: 12px;");
			labelPositivas.setValue("- Positivas: ");
			labelPositivas.setParent(hboxPositivas);
			percentualValoracoesPositivas.setParent(hboxPositivas);
			
			//percentual de valorações negativas
			percentualValoracoesNegativas = new Label();
			percentualValoracoesNegativas.setValue(formatador.format((negativa/tamanho)*100) + "%");
			percentualValoracoesNegativas.setStyle("font-weight: bold; font-size: 12px;");
			labelNegativas.setValue("- Negativas: ");
			labelNegativas.setParent(hboxNegativas);
			percentualValoracoesNegativas.setParent(hboxNegativas);
			
			//percentual de valorações neutras
			percentualValoracoesNeutras = new Label();
			percentualValoracoesNeutras.setValue(formatador.format((neutra/tamanho)*100) + "%");
			percentualValoracoesNeutras.setStyle("font-weight: bold; font-size: 12px;");
			labelNeutras.setValue("- Neutras: ");
			labelNeutras.setParent(hboxNeutras);
			percentualValoracoesNeutras.setParent(hboxNeutras);
			
			//medias das notas dada na valoração
			valoracaoMedia = new Label();
			valoracaoMedia.setValue(formatador.format(somaValoracao/tamanho));
			valoracaoMedia.setStyle("font-weight: bold; font-size: 12px;");
			labelValoracaoMedia.setValue("- Média das notas: ");
			labelValoracaoMedia.setParent(hboxMediaNotas);
			valoracaoMedia.setParent(hboxMediaNotas);
			
		}else{
			percentualValoracoesPositivas = new Label("0%");
			percentualValoracoesPositivas.setStyle("font-weight: bold; font-size: 12px;");
			labelPositivas.setValue("- Positivas: ");
			labelPositivas.setParent(hboxPositivas);
			percentualValoracoesPositivas.setParent(hboxPositivas);
			
			percentualValoracoesNegativas = new Label("0%");
			percentualValoracoesNegativas.setStyle("font-weight: bold; font-size: 12px;");
			labelNegativas.setValue("- Negativas: ");
			labelNegativas.setParent(hboxNegativas);
			percentualValoracoesNegativas.setParent(hboxNegativas);
			
			percentualValoracoesNeutras = new Label("0%");
			percentualValoracoesNeutras.setStyle("font-weight: bold; font-size: 12px;");
			labelNeutras.setValue("- Neutras: ");
			labelNeutras.setParent(hboxNeutras);
			percentualValoracoesNeutras.setParent(hboxNeutras);
			
			valoracaoMedia = new Label("0,0");
			valoracaoMedia.setStyle("font-weight: bold; font-size: 12px;");
			labelValoracaoMedia.setValue("- Média das notas: ");
			labelValoracaoMedia.setParent(hboxMediaNotas);
			valoracaoMedia.setParent(hboxMediaNotas);
		}
		
		hboxMediaNotas.setParent(vboxInformacoes);
		hboxQuantidadeValoracoes.setParent(vboxInformacoes);
		hboxPositivas.setParent(vboxInformacoes);
		hboxNegativas.setParent(vboxInformacoes);
		hboxNeutras.setParent(vboxInformacoes);
		
		(new Separator()).setParent(vboxInformacoes);
		
		Label labelAcessos = new Label("Acessos:");
		labelAcessos.setParent(vboxInformacoes);
		labelAcessos.setStyle("font-weight: bold; color: blue;");
	
		positiva = 0;
		negativa = 0;
		neutra = 0;
		somaValoracao = 0;
	
		Label labelQtdeAcessosValor = new Label(Integer.toString(item.getQuantidadeAcessos().intValue()));
		labelQtdeAcessosValor.setStyle("font-weight: bold; font-size: 12px;");
		Label labelQtdeAcessos = new Label("- Quantidade: ");
		labelQtdeAcessos.setParent(hboxQuantidadeAcessos);
		labelQtdeAcessosValor.setParent(hboxQuantidadeAcessos);
		hboxQuantidadeAcessos.setParent(vboxInformacoes);
		
		(new Separator()).setParent(vboxInformacoes);
		
		//////////////////////////////////////////
		// Projetos relacionados
		//////////////////////////////////////////
		Vbox vboxProjetos = new Vbox();
		
		Label labelProjetosRelacionados = new Label("Projetos Relacionados:");
		labelProjetosRelacionados.setParent(vboxProjetos);
		labelProjetosRelacionados.setStyle("font-weight: bold; color: blue;");
		
		Label nomeProjeto;
		
		Collection<Projeto> projetos = item.getProjetos();
		
		if(projetos.size() == 0){
			nomeProjeto = new Label("- Nenhum");
			nomeProjeto.setParent(vboxProjetos);
		}else{
			for (Projeto projeto : projetos){
				nomeProjeto = new Label("- " + projeto.getNome());
				nomeProjeto.setParent(vboxProjetos);
				
			}
		}
		
		vboxProjetos.setParent(vboxInformacoes);
		
		(new Separator()).setParent(vboxInformacoes);
		
		//////////////////////////////////////////
		// Atividades relacionados
		//////////////////////////////////////////
		
		Vbox vboxAtividades = new Vbox();
		
		
		
		Label labelAtividadesRelacionados = new Label("Atividades Relacionadas:");
		labelAtividadesRelacionados.setParent(vboxAtividades);
		labelAtividadesRelacionados.setStyle("font-weight: bold; color: blue;");
		
		Label nomeKAtividade;
		
		Collection<KAtividade> atividades = item.getkAtividades();
		
		if (atividades.size() == 0){
			nomeKAtividade = new Label("- Nenhum");
			nomeKAtividade.setParent(vboxAtividades);
		}else{
			for (KAtividade atividade : atividades){
				nomeKAtividade = new Label("- " + atividade.getNome());
				nomeKAtividade.setParent(vboxAtividades);
			}
		}
		
		vboxAtividades.setParent(vboxInformacoes);
		
		(new Separator()).setParent(vboxInformacoes);
		
		//////////////////////////////////////////
		// Temas relacionados
		//////////////////////////////////////////
		
		Vbox vboxTemasRelacionados = new Vbox();
		
		Label labelTemasRelacionados = new Label("Temas Relacionados:");
		labelTemasRelacionados.setParent(vboxTemasRelacionados);
		labelTemasRelacionados.setStyle("font-weight: bold; color: blue;");
		
		Label nomeTema;
		
		Collection<Tema> temas = item.getTemas();
		
		if(temas.size() == 0){
			nomeTema = new Label("- Nenhum");
			nomeTema.setParent(vboxTemasRelacionados);
		}else{
			for (Tema tema : temas){
				nomeTema = new Label("- " + tema.getNome());
				nomeTema.setParent(vboxTemasRelacionados);
			}
		}
		
		vboxTemasRelacionados.setParent(vboxInformacoes);
		
		(new Separator()).setParent(vboxInformacoes);
		
		//////////////////////////////////////////
		// Itens relacionados
		//////////////////////////////////////////
		
		Vbox vboxItensRelacionados = new Vbox();
		
		Label labelItensRelacionados = new Label("Itens Relacionados:");
		labelItensRelacionados.setParent(vboxItensRelacionados);
		labelItensRelacionados.setStyle("font-weight: bold; color: blue;");
		
		Label itensRelacionados;
		
		Collection<ItemConhecimento> itens = item.getItensRelacionados();
		
		if(itens.size() == 0){
			itensRelacionados = new Label("- Nenhum");
			itensRelacionados.setParent(vboxItensRelacionados);
		}else{
			for (ItemConhecimento itemConhecimento : itens){
				itensRelacionados = new Label("- " + itemConhecimento.getTitulo());
				itensRelacionados.setParent(vboxItensRelacionados);
			}
		}
		
		vboxItensRelacionados.setParent(vboxInformacoes);
		
		vboxInformacoes.setParent(hbox);
		
	}
	
	public void preencherAbaInformacoes(Object item){
		
		Hbox hbox = new Hbox();
		
		listbox.setWidth("380px");
		listbox.setHeight("345px");
		
		ItemConhecimento itemConhecimento = (ItemConhecimento) item; 
		
		preencherVisualizacaoEsquerda(itemConhecimento);
		listbox.setParent(hbox);
		
		preencherVisualizacaoDireita(hbox, itemConhecimento);
	
		hbox.setParent(tabpanelInformacoes);
		
	}
	
	public void preencherAbaAvaliacoes(Object item){
		
		Listhead listhead = new Listhead();
		Listheader listheaderInformacoes = new Listheader("informações");
		Listheader listheaderNotas = new Listheader("Notas");
		Listheader listheaderResultado = new Listheader("Resultado");
		
		listheaderInformacoes.setParent(listhead);
		listheaderNotas.setParent(listhead);
		listheaderResultado.setParent(listhead);
		listhead.setParent(listboxAvaliacoes);
		
	//	listboxAvaliacoes.setHeight("350px");
		preencherListboxAvaliacoes();
		
		listboxAvaliacoes.setParent(tabpanelAvaliacoes);
		
	}
	
	public void preencherListboxAvaliacoes(){
		Collection<Avaliacao> itens = itemConhecimento.getAvaliacoes();
		for (Avaliacao item : itens){
			listitemAvaliacao = new Listitem();
			listitemAvaliacao.setValue(item);
			preencherLinhaListboxAvaliacoes(item);
			listitemAvaliacao.setParent(listboxAvaliacoes);
		}
	}
	
	public double calculaMedia(double correcao, double completude, double consistencia, double utilidade, double aplicabilidade){
		return (correcao + completude + consistencia + utilidade + aplicabilidade)/5;
	}
	
	public void preencherLinhaListboxAvaliacoes(Avaliacao item){
		
		Listcell listcellInformacoes = new Listcell();
		Listcell listcellNotas = new Listcell();
		Listcell listcellResultado = new Listcell();
		
		listcellInformacoes.setParent(listitemAvaliacao);
		listcellNotas.setParent(listitemAvaliacao);
		listcellResultado.setParent(listitemAvaliacao);
		
		Vbox vboxInformacoes = new Vbox();
		Vbox vboxNotas = new Vbox();
		Vbox vboxResultado = new Vbox();
				
		vboxInformacoes.setParent(listcellInformacoes);
		vboxNotas.setParent(listcellNotas);
		vboxResultado.setParent(listcellResultado);
		
		/////////////////////////
		// criar aba informacoes
		/////////////////////////
			
		Hbox hboxAutor = new Hbox();
		Label labelAutor = new Label("Autor: ");
		Label labelAutorValor = new Label(item.getAutor().getNome());
		labelAutorValor.setStyle("font-weight: bold; color: black;");
		labelAutor.setParent(hboxAutor);
		labelAutorValor.setParent(hboxAutor);
		hboxAutor.setParent(vboxInformacoes);
		
		
		Hbox hboxDataAvaliacao = new Hbox();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		Label labelDataAvaliacao = new Label("Data da Avaliação: ");
		Label labelDataAvaliacaoValor = new Label();
		labelDataAvaliacaoValor.setValue(formatador.format(item.getDataAvaliacao()));
		labelDataAvaliacaoValor.setStyle("font-weight: bold; color: black;");
		labelDataAvaliacao.setParent(hboxDataAvaliacao);
		labelDataAvaliacaoValor.setParent(hboxDataAvaliacao);
		hboxDataAvaliacao.setParent(vboxInformacoes);
		
		/////////////////////////
		// criar aba Notas
		/////////////////////////
		
		//correcao
		Hbox hboxCorrecao = new Hbox();
		double correcao = item.getNotaCorrecao().doubleValue();
		Label labelCorrecao = new Label("Correção: ");
		Label labelCorrecaoValor = new Label(Double.toString(correcao));
		labelCorrecaoValor.setStyle("font-weight: bold; color: black;");
		labelCorrecao.setParent(hboxCorrecao);
		labelCorrecaoValor.setParent(hboxCorrecao);
		hboxCorrecao.setParent(vboxNotas);

		//completude
		Hbox hboxCompletude = new Hbox();
		double completude = item.getNotaCompletude().doubleValue();
		Label labelCompletude = new Label("Completude: ");
		Label labelCompletudeValor = new Label(Double.toString(completude));
		labelCompletudeValor.setStyle("font-weight: bold; color: black;");
		labelCompletude.setParent(hboxCompletude);
		labelCompletudeValor.setParent(hboxCompletude);
		hboxCompletude.setParent(vboxNotas);

		//consistencia
		Hbox hboxConsistencia = new Hbox();
		double consistencia = item.getNotaConsistencia().doubleValue();
		Label labelConsistencia = new Label("Consistência: ");
		Label labelConsistenciaValor = new Label(Double.toString(consistencia));
		labelConsistenciaValor.setStyle("font-weight: bold; color: black;");
		labelConsistencia.setParent(hboxConsistencia);
		labelConsistenciaValor.setParent(hboxConsistencia);
		hboxConsistencia.setParent(vboxNotas);

		//utilidade
		Hbox hboxUtilidade = new Hbox();
		double utilidade = item.getNotaUtilidade().doubleValue();
		Label labelUtilidade = new Label("Utilidade: ");
		Label labelUtilidadeValor = new Label(Double.toString(utilidade));
		labelUtilidadeValor.setStyle("font-weight: bold; color: black;");
		labelUtilidade.setParent(hboxUtilidade);
		labelUtilidadeValor.setParent(hboxUtilidade);
		hboxUtilidade.setParent(vboxNotas);

		//aplicabilidade
		Hbox hboxAplicabilidade = new Hbox();
		double aplicabilidade = item.getNotaAplicabilidade().doubleValue();
		Label labelAplicabilidade = new Label("Aplicabilidade: ");
		Label labelAplicabilidadeValor = new Label(Double.toString(aplicabilidade));
		labelAplicabilidadeValor.setStyle("font-weight: bold; color: black;");
		labelAplicabilidade.setParent(hboxAplicabilidade);
		labelAplicabilidadeValor.setParent(hboxAplicabilidade);
		hboxAplicabilidade.setParent(vboxNotas);

		//media
		Hbox hboxMedia = new Hbox();
		double media = calculaMedia(correcao, completude, consistencia, utilidade, aplicabilidade);
		Label labelMedia = new Label("Média das Notas: ");
		Label labelMediaValor = new Label(Double.toString(media));
		labelMediaValor.setStyle("font-weight: bold; color: black;");
		labelMedia.setParent(hboxMedia);
		labelMediaValor.setParent(hboxMedia);
		hboxMedia.setParent(vboxNotas);
		
		/////////////////////////
		// criar aba Rsultado
		/////////////////////////
		
		//parecer
		Hbox hboxParecer = new Hbox();
		Label labelParecer = new Label("Parecer: ");
		Label labelParecerValor = new Label(item.getParecer());
		labelParecerValor.setStyle("font-weight: bold; color: black;");
		labelParecer.setParent(hboxParecer);
		labelParecerValor.setParent(hboxParecer);
		hboxParecer.setParent(vboxResultado);
		
		//resultado final
		Hbox hboxResultadoFinal = new Hbox();
		Label labelResultadoFinal = new Label("Resultado Final: ");
		Label labelResultadoFinalValor = new Label(item.getResultadoFinal());
		labelResultadoFinalValor.setStyle("font-weight: bold; color: black;");
		labelResultadoFinal.setParent(hboxResultadoFinal);
		labelResultadoFinalValor.setParent(hboxResultadoFinal);
		hboxResultadoFinal.setParent(vboxResultado);
		
	}
	
	public void preencherLinhaListboxValoracoes(Valoracao item){
		
		Listcell listcellValoracoes = new Listcell();
		Vbox vboxLinhaValoracoes = new Vbox();
		
		//autor
		Hbox hboxAutor = new Hbox();
		Label labelAutor = new Label("Autor: ");
		Label labelAutorValor = new Label(item.getAutor().getNome());
		labelAutorValor.setStyle("font-weight: bold; color: black;");
		labelAutor.setParent(hboxAutor);
		labelAutorValor.setParent(hboxAutor);
		hboxAutor.setParent(vboxLinhaValoracoes);
		
		//data da valoracao
		Hbox hboxDataValoracao = new Hbox();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		Label labelDataValoracao = new Label("Valorado em: "); //rever
		Label labelDataValoracaoValor = new Label(formatador.format(item.getDataValoracao()));
		labelDataValoracaoValor.setStyle("font-weight: bold; color: black;");
		labelDataValoracao.setParent(hboxDataValoracao);
		labelDataValoracaoValor.setParent(hboxDataValoracao);
		hboxDataValoracao.setParent(vboxLinhaValoracoes);
		
		//grau de utilidade
		Hbox hboxGrauUtilidade = new Hbox();
		Label labelGrauUtilidade = new Label("Utilidade: "); 
		Label labelGrauUtilidadeValor = new Label(Double.toString(item.getGrauUtilidade().doubleValue()));
		labelGrauUtilidadeValor.setStyle("font-weight: bold; color: black;");
		labelGrauUtilidade.setParent(hboxGrauUtilidade);
		labelGrauUtilidadeValor.setParent(hboxGrauUtilidade);
		hboxGrauUtilidade.setParent(vboxLinhaValoracoes);
		
		//classificacao
		Hbox hboxClassificacao = new Hbox();
		Label labelClassificacao = new Label();
		
		BigDecimal bigDecimalPositiva1 = new BigDecimal("10.0");
		BigDecimal bigDecimalPositiva2 = new BigDecimal("0.01");
		
		BigDecimal bigDecimalNegativa1 = new BigDecimal("-10.0");
		BigDecimal bigDecimalNegativa2 = new BigDecimal("-0.01");
		
		BigDecimal bigDecimalNeutra = new BigDecimal("0.00");
		
		BigDecimal valoracao;
		valoracao = item.getGrauUtilidade();

		
		if(valoracao.doubleValue() >= bigDecimalNegativa1.doubleValue() && valoracao.doubleValue() <= bigDecimalNegativa2.doubleValue()){
			Label labelNegativa = new Label();
			labelNegativa.setValue("Negativa");
			labelNegativa.setStyle("font-weight: bold; color: black;");
			labelClassificacao.setValue("Classificação: ");
			labelClassificacao.setParent(hboxClassificacao);
			labelNegativa.setParent(hboxClassificacao);
			hboxClassificacao.setParent(vboxLinhaValoracoes);
		}
		
		if(valoracao.doubleValue() == bigDecimalNeutra.doubleValue()){
			Label labelNeutra = new Label();
			labelNeutra.setValue("Neutra");
			labelNeutra.setStyle("font-weight: bold; color: black;");
			labelClassificacao.setValue("Classificação: ");
			labelClassificacao.setParent(hboxClassificacao);
			labelNeutra.setParent(hboxClassificacao);
			hboxClassificacao.setParent(vboxLinhaValoracoes);
		}
		
		if(valoracao.doubleValue() >= bigDecimalPositiva2.doubleValue() && valoracao.doubleValue() <= bigDecimalPositiva1.doubleValue()){
			Label labelPositiva = new Label();
			labelPositiva.setValue("Positiva");
			labelPositiva.setStyle("font-weight: bold; color: black;");
			labelClassificacao.setValue("Classificação: ");
			labelClassificacao.setParent(hboxClassificacao);
			labelPositiva.setParent(hboxClassificacao);
			hboxClassificacao.setParent(vboxLinhaValoracoes);
		}
		
		//comentario
		Hbox hboxComentario = new Hbox();
		Label labelComentario = new Label("Comentário: ");
		Label labelComentarioValor = new Label(item.getComentario());
		labelComentarioValor.setStyle("font-weight: bold; color: black;");
		labelComentario.setParent(hboxComentario);
		labelComentarioValor.setParent(hboxComentario);
		hboxComentario.setParent(vboxLinhaValoracoes);
		
		vboxLinhaValoracoes.setParent(listcellValoracoes);
		
		listcellValoracoes.setParent(listitemValoracao);
		
		
		
	}
	
	public void preencherListboxValoracoes(){
		Collection<Valoracao> itens = itemConhecimento.getValoracoes();
		for (Valoracao item : itens){
			listitemValoracao = new Listitem();
			listitemValoracao.setValue(item);
			preencherLinhaListboxValoracoes(item);
			listitemValoracao.setParent(listboxValoracoes);
		}
	}
	
	public void preencherAbaValoracoes(Object item){
		
		Listhead listhead = new Listhead();
		Listheader listheaderValoracoes = new Listheader("Valorações");
		
		listheaderValoracoes.setParent(listhead);
		listhead.setParent(listboxValoracoes);
		
		//listboxValoracoes.setHeight("350px");
		preencherListboxValoracoes();
		
		listboxValoracoes.setParent(tabpanelValoracoes);
		
		
	}
	
	public void criarJanVisualizarItemConhecimento(Object item){
		
		this.setTitle("Visualizar Item de Conhecimento");
		this.setBorder("normal");
		
		Tabbox tabboxVisualizarItemConhecimento = new Tabbox();
		tabboxVisualizarItemConhecimento.setHeight("380px");
		Tabs tabsVisualizarItemConhecimento = new Tabs();
		
		tabInformacoes.setParent(tabsVisualizarItemConhecimento);
		tabAvaliacoes.setParent(tabsVisualizarItemConhecimento);
		tabValoracoes.setParent(tabsVisualizarItemConhecimento);
		tabsVisualizarItemConhecimento.setParent(tabboxVisualizarItemConhecimento);
		
		Tabpanels tabpanelsVisualizarItemConhecimento = new Tabpanels();
	
		tabpanelsVisualizarItemConhecimento.setParent(tabboxVisualizarItemConhecimento);
		tabboxVisualizarItemConhecimento.setParent(this);
		
		preencherAbaInformacoes(item);
		tabpanelInformacoes.setStyle("overflow:auto;");
		tabpanelInformacoes.setParent(tabpanelsVisualizarItemConhecimento);
		
		preencherAbaAvaliacoes(item);
		tabpanelAvaliacoes.setStyle("overflow:auto;");
		tabpanelAvaliacoes.setParent(tabpanelsVisualizarItemConhecimento);
		
		preencherAbaValoracoes(item);
		tabpanelValoracoes.setStyle("overflow:auto;");
		tabpanelValoracoes.setParent(tabpanelsVisualizarItemConhecimento);

		
		Button botaoVoltar = new Button("Voltar");
		Button botaoValorar = new Button("Valorar");
		Toolbar toolbarInferior = new Toolbar();

		botaoVoltar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.voltar();
			
			}
		});
		
		botaoValorar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				
				ctrlGerenciaConhecimento.exibirJanelaValorarItemConhecimento(itemConhecimento);
			}
		});

		

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVoltar);
		toolbarInferior.appendChild(botaoValorar);

		toolbarInferior.setParent(this);
	}
	
}

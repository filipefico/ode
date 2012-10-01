package ode.gerenciaConhecimento.ciu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanListaBuscarItensConhecimento extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	int qtdeItens = 0; //acho q nem vai precisar desses atributos, pois é só fazer um get
	Label labelTitulo;
	Label labelResumo;
	Label labelCriadoEmValor;  
	Label labelAutorValor;
	Label labelTipoValor;
	Label labelQtdeAcessosValor;
	Label labelQtdeItensEncontradosValor = new Label("0");
	Listitem listitem = new Listitem();
	Listbox listboxBuscarItensConhecimento = new Listbox();
	Label qtdeValoracoes;
	Label percentualValoracoesPositivas;
	Label percentualValoracoesNegativas;
	Label percentualValoracoesNeutras;
	Label valoracaoMedia;
	List<ItemConhecimento> itens;
	
	float positiva = 0;
	float negativa = 0;
	float neutra = 0;
	int tamanho = 0;
	double somaValoracao = 0;
	
	public JanListaBuscarItensConhecimento(CtrlGerenciaConhecimento ctrl, List<ItemConhecimento> itens) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		this.itens = itens;
		
		criarJanListaBuscarItensConhecimento();
	}
	
	public void alterarLabelQtdeItensEncontrados(int qtde){
		
		labelQtdeItensEncontradosValor.setValue(Integer.toString(qtde));
		
	}
	
	public void preencherListboxItensEncontrados(){
		
		
		for (ItemConhecimento item : this.itens){
			listitem = new Listitem();
			listitem.setValue(item);
			preencherLinhaListbox(item);
			listitem.setParent(listboxBuscarItensConhecimento);
		}
	}
	
	public void preencherLinhaListbox(ItemConhecimento item){
		
		Listcell listcellRadio = new Listcell("");
		
		Hbox hboxMediaNotas = new Hbox();
		Hbox hboxPositivas = new Hbox();
		Hbox hboxNegativas = new Hbox();
		Hbox hboxNeutras = new Hbox();
		
		//coluna conteudo
		Listcell listcellConteudo = new Listcell();
		Vbox vboxConteudo = new Vbox();
		labelTitulo = new Label(item.getTitulo());
		labelTitulo.setStyle("font-weight: bold; font-style: italic;color: black;");
		labelTitulo.setParent(vboxConteudo);
		labelResumo = new Label(item.getResumo());
		labelResumo.setMultiline(true);
		labelResumo.setParent(vboxConteudo);
		vboxConteudo.setParent(listcellConteudo);
		
		//coluna valorações
		Listcell listcellValoracoes = new Listcell();
		Vbox vboxValoracoes = new Vbox();
		
		Collection<Valoracao> itens = item.getValoracoes();
	
		
		//quantidade de valorações
		Hbox hboxQtdeValoracoes = new Hbox();
		tamanho = itens.size();
		qtdeValoracoes = new Label(Integer.toString(tamanho));
		qtdeValoracoes.setStyle("font-weight: bold; color: black;");
		Label labelQuantidades = new Label("Quantidade: ");
		
		labelQuantidades.setParent(hboxQtdeValoracoes);
		qtdeValoracoes.setParent(hboxQtdeValoracoes);
		
		for (Valoracao valoracao : itens){
				
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
			percentualValoracoesPositivas.setStyle("font-weight: bold; color: black;");
			labelPositivas.setValue("Positivas: ");
			labelPositivas.setParent(hboxPositivas);
			percentualValoracoesPositivas.setParent(hboxPositivas);
			
			//percentual de valorações negativas
			percentualValoracoesNegativas = new Label();
			percentualValoracoesNegativas.setValue(formatador.format((negativa/tamanho)*100) + "%");
			percentualValoracoesNegativas.setStyle("font-weight: bold; color: black;");
			labelNegativas.setValue("Negativas: ");
			labelNegativas.setParent(hboxNegativas);
			percentualValoracoesNegativas.setParent(hboxNegativas);
			
			//percentual de valorações neutras
			percentualValoracoesNeutras = new Label();
			percentualValoracoesNeutras.setValue(formatador.format((neutra/tamanho)*100) + "%");
			percentualValoracoesNeutras.setStyle("font-weight: bold; color: black;");
			labelNeutras.setValue("Neutras: ");
			labelNeutras.setParent(hboxNeutras);
			percentualValoracoesNeutras.setParent(hboxNeutras);
			
			//medias das notas dada na valoração
			valoracaoMedia = new Label();
			valoracaoMedia.setValue(formatador.format(somaValoracao/tamanho));
			valoracaoMedia.setStyle("font-weight: bold; color: black;");
			labelValoracaoMedia.setValue("Média das notas: ");
			labelValoracaoMedia.setParent(hboxMediaNotas);
			valoracaoMedia.setParent(hboxMediaNotas);
			
		}else{
			percentualValoracoesPositivas = new Label("0%");
			percentualValoracoesPositivas.setStyle("font-weight: bold; color: black;");
			labelPositivas.setValue("Positivas: ");
			labelPositivas.setParent(hboxPositivas);
			percentualValoracoesPositivas.setParent(hboxPositivas);
			
			percentualValoracoesNegativas = new Label("0%");
			percentualValoracoesNegativas.setStyle("font-weight: bold; color: black;");
			labelNegativas.setValue("Negativas: ");
			labelNegativas.setParent(hboxNegativas);
			percentualValoracoesNegativas.setParent(hboxNegativas);
			
			percentualValoracoesNeutras = new Label("0%");
			percentualValoracoesNeutras.setStyle("font-weight: bold; color: black;");
			labelNeutras.setValue("Neutras: ");
			labelNeutras.setParent(hboxNeutras);
			percentualValoracoesNeutras.setParent(hboxNeutras);
			
			valoracaoMedia = new Label("0,0");
			valoracaoMedia.setStyle("font-weight: bold; color: black;");
			labelValoracaoMedia.setValue("Média das notas: ");
			labelValoracaoMedia.setParent(hboxMediaNotas);
			valoracaoMedia.setParent(hboxMediaNotas);
		}
		hboxQtdeValoracoes.setParent(vboxValoracoes);
		hboxPositivas.setParent(vboxValoracoes);
		hboxNegativas.setParent(vboxValoracoes);
		hboxNeutras.setParent(vboxValoracoes);
		hboxMediaNotas.setParent(vboxValoracoes);
		vboxValoracoes.setParent(listcellValoracoes);
		
		positiva = 0;
		negativa = 0;
		neutra = 0;
		somaValoracao = 0;
		
		//coluna informações
		Listcell listcellInformacoes = new Listcell();
		Vbox vboxInformacoes = new Vbox();
		
		//data de criacao
		Hbox hboxDataCriacao = new Hbox();
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		labelCriadoEmValor = new Label(out.format(item.getDataCriacao()));  // só pegar a data de Criacao
		labelCriadoEmValor.setStyle("font-weight: bold; color: black;");
		Label labelCriadoEm = new Label("Criado em: ");
		labelCriadoEm.setParent(hboxDataCriacao);
		labelCriadoEmValor.setParent(hboxDataCriacao);
		hboxDataCriacao.setParent(vboxInformacoes);
		
		//autor	
		Hbox hboxAutor = new Hbox();
		labelAutorValor = new Label(item.getAutor().getNome());
		labelAutorValor.setStyle("font-weight: bold; color: black;");
		Label labelAutor = new Label("Autor: ");
		labelAutor.setParent(hboxAutor);
		labelAutorValor.setParent(hboxAutor);
		hboxAutor.setParent(vboxInformacoes);
		
		//tipo de item
		Hbox hboxTipoItem = new Hbox();
		if (item instanceof LicaoAprendida) {
			labelTipoValor = new Label("Lição Aprendida");
			labelTipoValor.setStyle("font-weight: bold; color: black;");
			Label labelTipo = new Label("Tipo: ");
			labelTipo.setParent(hboxTipoItem);
			labelTipoValor.setParent(hboxTipoItem);
			hboxTipoItem.setParent(vboxInformacoes);

		} 
		if (item instanceof ConhecimentoRelativoDiscussao) {
			labelTipoValor = new Label("Conhecimento Relativo a uma Discussão");
			labelTipoValor.setStyle("font-weight: bold; color: black;");
			Label labelTipo = new Label("Tipo: ");
			labelTipo.setParent(hboxTipoItem);
			labelTipoValor.setParent(hboxTipoItem);
			hboxTipoItem.setParent(vboxInformacoes);
		} 
	
		//quantidade de acessos
		Hbox hboxQtdeAcessos = new Hbox();
		labelQtdeAcessosValor = new Label(item.getQuantidadeAcessos().toString());
		labelQtdeAcessosValor.setStyle("font-weight: bold; color: black;");
		Label labelQtdeAcessos = new Label("Quantidade de Acessos: ");
		labelQtdeAcessos.setParent(hboxQtdeAcessos);
		labelQtdeAcessosValor.setParent(hboxQtdeAcessos);
		hboxQtdeAcessos.setParent(vboxInformacoes);
		
		vboxInformacoes.setParent(listcellInformacoes);
		
		listcellRadio.setParent(listitem);
		listcellConteudo.setParent(listitem);
		listcellValoracoes.setParent(listitem);
		listcellInformacoes.setParent(listitem);
		
		
	}
	
	public void criarJanListaBuscarItensConhecimento(){
		
		this.setTitle("Buscar Itens de Conhecimento");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		
		//quantidade de itens encontrados
		Hbox hboxQtdeItensEncontrados = new Hbox();
		labelQtdeItensEncontradosValor.setValue(String.valueOf(itens.size()));
		labelQtdeItensEncontradosValor.setStyle("font-weight: bold; color: blue");
		Label labelQtdeItensEncontrados = new Label("Quantidade de Itens Encontrados: ");
		labelQtdeItensEncontrados.setStyle("font-weight: bold;font-style: italic;");
		
		labelQtdeItensEncontrados.setParent(hboxQtdeItensEncontrados);
		labelQtdeItensEncontradosValor.setParent(hboxQtdeItensEncontrados);
		hboxQtdeItensEncontrados.setParent(vbox);
		
		//Criar as colunas do Listbox
		listboxBuscarItensConhecimento.setMultiple(false);
		listboxBuscarItensConhecimento.setCheckmark(true);
		listboxBuscarItensConhecimento.setSizedByContent(true);
		listboxBuscarItensConhecimento.setHeight("350px");
	//	listboxBuscarItensConhecimento.setWidth("580px");
		
		
		Listhead listheadBuscarItensConhecimento = new Listhead();
		Listheader listheaderRadio = new Listheader(" ");
		listheaderRadio.setWidth("25px");
		Listheader listheaderConteudo = new Listheader("Conteúdo");
		listheaderConteudo.setWidth("185px");
		Listheader listheaderValoracoes = new Listheader("Valorações");
		listheaderValoracoes.setWidth("133px");
		Listheader listheaderInformacoes = new Listheader("Informações");
		listheaderInformacoes.setWidth("100%");
		
		listheaderRadio.setParent(listheadBuscarItensConhecimento);
		listheaderConteudo.setParent(listheadBuscarItensConhecimento);
		listheaderValoracoes.setParent(listheadBuscarItensConhecimento);
		listheaderInformacoes.setParent(listheadBuscarItensConhecimento);
		listheadBuscarItensConhecimento.setParent(listboxBuscarItensConhecimento);
		
		preencherListboxItensEncontrados();

		listboxBuscarItensConhecimento.setParent(vbox);
		
		Button botaoVisualizar = new Button("Visualizar");
		Button botaoNovaBusca = new Button("Nova Busca");
		Toolbar toolbarInferior = new Toolbar();

		botaoVisualizar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				if(listboxBuscarItensConhecimento.getSelectedIndex() == -1){
					Messagebox messageboxInformar = new Messagebox();
					messageboxInformar.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, messageboxInformar.INFORMATION);
				}else{
					Object objeto = new Object();
					if (listboxBuscarItensConhecimento.getSelectedItem() != null) {
						objeto =  listboxBuscarItensConhecimento.getSelectedItem().getValue();
					}
					
					if(objeto != null){
						((ItemConhecimento)objeto).setQuantidadeAcessos(((ItemConhecimento)objeto).getQuantidadeAcessos() + 1);
						ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.salvar((ItemConhecimento)objeto);
						ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimento((ItemConhecimento)objeto);
					}
				}
				
			}
		});
		
		botaoNovaBusca.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaBuscarItensConhecimento();
			}
		});

		

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVisualizar);
		toolbarInferior.appendChild(botaoNovaBusca);

		toolbarInferior.setParent(vbox);
		
		vbox.setParent(this);
		
	}
	
}

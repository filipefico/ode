package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
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
	Label labelCriadoEm;  
	Label labelAutor;
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
		
		//quantidade de valorações
		qtdeValoracoes = new Label("0");
		qtdeValoracoes.setStyle("font-weight: bold;");
		Label labelQuantidades = new Label("Quantidade: " + qtdeValoracoes.getValue());
		
		//percentual de valorações positivas
		percentualValoracoesPositivas = new Label("0");
		percentualValoracoesPositivas.setStyle("font-weight: bold;");
		Label labelPositivas = new Label("Positivas: " + percentualValoracoesPositivas.getValue() + "%");
		
		//percentual de valorações negativas
		percentualValoracoesNegativas = new Label("hgjfj");
		percentualValoracoesNegativas.setStyle("font-weight: bold;");
		Label labelNegativas = new Label("Negativas: " + percentualValoracoesNegativas.getValue() + "%");
		
		//percentual de valorações neutras
		percentualValoracoesNeutras = new Label("0");
		percentualValoracoesNeutras.setStyle("font-weight: bold;");
		Label labelNeutras = new Label("Neutras: " + percentualValoracoesNeutras.getValue() + "%");
		
		//medias das notas dada na valoração
		valoracaoMedia = new Label("");
		valoracaoMedia.setStyle("font-weight: bold;");
		Label labelValoracaoMedia = new Label("Valoração Média: " + valoracaoMedia.getValue());
		
		labelQuantidades.setParent(vboxValoracoes);
		labelPositivas.setParent(vboxValoracoes);
		labelNegativas.setParent(vboxValoracoes);
		labelNeutras.setParent(vboxValoracoes);
		labelValoracaoMedia.setParent(vboxValoracoes);
		vboxValoracoes.setParent(listcellValoracoes);
		
		//coluna informações
		Listcell listcellInformacoes = new Listcell();
		Vbox vboxInformacoes = new Vbox();
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		labelCriadoEm = new Label("Criado em: " + out.format(item.getDataCriacao()));  // só pegar a data de Criacao
		labelCriadoEm.setStyle("font-weight: bold;");
		labelAutor = new Label("Autor: " + item.getAutor().getNome());
		labelAutor.setStyle("font-weight: bold;");
		
		labelCriadoEm.setParent(vboxInformacoes);
		labelAutor.setParent(vboxInformacoes);
		
		if (item instanceof LicaoAprendida) {
			labelTipoValor = new Label("Lição Aprendida");
			labelTipoValor.setStyle("font-weight: bold;");
			Label labelTipo = new Label("Tipo: " + labelTipoValor.getValue());
			
			labelTipo.setParent(vboxInformacoes);
		} 
		if (item instanceof ConhecimentoRelativoDiscussao) {
			labelTipoValor = new Label("Conhecimento Relativo a uma Discussão");
			labelTipoValor.setStyle("font-weight: bold;");
			Label labelTipo = new Label("Tipo: " + labelTipoValor.getValue());
			
			labelTipo.setParent(vboxInformacoes);
		} 
	
		labelQtdeAcessosValor = new Label("0");
		labelQtdeAcessosValor.setStyle("font-weight: bold;");
		Label labelQtdeAcessos = new Label("Quantidade de Acessos: " + labelQtdeAcessosValor.getValue());
		
		labelQtdeAcessos.setParent(vboxInformacoes);
		
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
		labelQtdeItensEncontradosValor.setValue(String.valueOf(itens.size()));
		Label labelQtdeItensEncontrados = new Label("Quantidade de Itens Encontrados: " + labelQtdeItensEncontradosValor.getValue());
		labelQtdeItensEncontrados.setStyle("font-weight: bold;font-style: italic;");
		
		labelQtdeItensEncontrados.setParent(vbox);
		
		//Criar as colunas do Listbox
		listboxBuscarItensConhecimento.setMultiple(false);
		listboxBuscarItensConhecimento.setCheckmark(true);
		listboxBuscarItensConhecimento.setSizedByContent(true);
		listboxBuscarItensConhecimento.setHeight("350px");
		listboxBuscarItensConhecimento.setWidth("580px");
		
		
		Listhead listheadBuscarItensConhecimento = new Listhead();
		Listheader listheaderRadio = new Listheader(" ");
		listheaderRadio.setWidth("25px");
		Listheader listheaderConteudo = new Listheader("Conteúdo");
		listheaderConteudo.setWidth("185px");
		Listheader listheaderValoracoes = new Listheader("Valorações");
		listheaderValoracoes.setWidth("133px");
		Listheader listheaderInformacoes = new Listheader("Informações");
		listheaderInformacoes.setWidth("220px");
		
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

				Object objeto = new Object();
				if (listboxBuscarItensConhecimento.getSelectedItem() != null) {
					objeto =  listboxBuscarItensConhecimento.getSelectedItem().getValue();
				} 
				ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimentoUsuarioComum(objeto);
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

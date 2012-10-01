package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanItensPendentesAvaliacaoGerente extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Label labelQtdeItens = new Label();
	Label labelQtdeItensValor = new Label("0");
	Listbox listboxQtdeItens = new Listbox();
	Listitem listitem;
	Listcell listcellRadio;
	Listcell listcellTitulo;
	Listcell listcellInformacoes;
	Listcell listcellEstado;
	Label labelAutorValor;
	Label labelDataCriacaoValor;
	Label labelTipoValor;
	Listcell listcellAvaliadoresSelecionados;
	Label labelQtdeAvaliacoesRealizadas = new Label("0");
	
	public JanItensPendentesAvaliacaoGerente(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanItensPendentesAvaliacaoGerente();
	}
	
	public void criarJanItensPendentesAvaliacaoGerente(){
		
		this.setTitle("Itens Pendentes de Avaliação - Gerente");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
			
		labelQtdeItens.setParent(vbox);
		
		//listbox	
		Listhead colunas = new Listhead();;
		Listheader colunaRadioButton = new Listheader(" ");
		colunaRadioButton.setWidth("30px");
		Listheader colunaTitulo = new Listheader("Título");
		colunaTitulo.setWidth("120px");
		Listheader colunaInformacoes = new Listheader("Informações");
		colunaInformacoes.setWidth("200px");
		Listheader colunaAvaliadoresSelecionados = new Listheader("Avaliadores Selecionados");
		colunaAvaliadoresSelecionados.setWidth("200px");
		Listheader colunaEstado = new Listheader("Estado");
		colunaEstado.setWidth("100%");
		
		listboxQtdeItens.setMultiple(false);
		listboxQtdeItens.setCheckmark(true);
		listboxQtdeItens.setSizedByContent(true);
		listboxQtdeItens.setHeight("150px");
		
		colunaRadioButton.setParent(colunas);
		colunaTitulo.setParent(colunas);
		colunaInformacoes.setParent(colunas);
		colunaAvaliadoresSelecionados.setParent(colunas);
		colunaEstado.setParent(colunas);
		colunas.setParent(listboxQtdeItens);
		
		preencherListboxItensPendentesAvaliacao();
		listboxQtdeItens.setParent(vbox);
		
		//Botões
		Button botaoVisualizar = new Button("Visualizar");
		Button botaoAvaliar = new Button("Avaliar");
		Button botaoSelecionarEspecialistas = new Button("Selecionar Especialistas");
		Button botaoAprovar = new Button("Aprovar");
		Button botaoRejeitar = new Button("Rejeitar");
		Toolbar toolbarInferior = new Toolbar();
		
		botaoVisualizar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				Object object = new Object();
						
				if(listboxQtdeItens.getSelectedItem() == null){
					Messagebox messageboxSalvar = new Messagebox();
					messageboxSalvar.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, messageboxSalvar.INFORMATION);
				}else{
					object = listboxQtdeItens.getSelectedItem().getValue();
					ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimento((ItemConhecimento)object);
				}
			}
		});
		
		botaoSelecionarEspecialistas.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				if (listboxQtdeItens.getSelectedItem() != null){
					
					// Coloca o item de conhecimento na sessao para selecionar seus avaliadores
					ctrlGerenciaConhecimento.colocarItemConhecimentoSessao((ItemConhecimento)listboxQtdeItens.getSelectedItem().getValue());
					
					ctrlGerenciaConhecimento.exibirJanelaPaginasAmarelasBuscarPessoas();
					
				}else{
					Messagebox messageboxSalvar = new Messagebox();
					messageboxSalvar.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, messageboxSalvar.INFORMATION);
				}
				
			}
		});
		
		botaoAvaliar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				if (listboxQtdeItens.getSelectedItem() != null){
					ctrlGerenciaConhecimento.exibirJanelaAvaliarItemConhecimento((ItemConhecimento)listboxQtdeItens.getSelectedItem().getValue());
				}else{
					Messagebox.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, Messagebox.INFORMATION);
				}
			}
		});
		
		botaoAprovar.addEventListener("onClick", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (listboxQtdeItens.getSelectedItem() != null){
					ctrlGerenciaConhecimento.salvarEstadoItemConhecimento((ItemConhecimento)listboxQtdeItens.getSelectedItem().getValue(), ItemConhecimento.ESTADO_DISPONIVEL);
					Messagebox.show("Item de conhecimento aprovado com sucesso.", "Informação", Messagebox.OK, Messagebox.INFORMATION);
					preencherListboxItensPendentesAvaliacao();
				}else{
					Messagebox.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, Messagebox.INFORMATION);
				}
			}
		});

		botaoRejeitar.addEventListener("onClick", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (listboxQtdeItens.getSelectedItem() != null){
					ctrlGerenciaConhecimento.salvarEstadoItemConhecimento((ItemConhecimento)listboxQtdeItens.getSelectedItem().getValue(), ItemConhecimento.ESTADO_REJEITADO);
					Messagebox.show("Item de conhecimento rejeitado com sucesso.", "Informação", Messagebox.OK, Messagebox.INFORMATION);
					preencherListboxItensPendentesAvaliacao();
				}else{
					Messagebox.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, Messagebox.INFORMATION);
				}
			}
		});
		
		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");
			
		toolbarInferior.appendChild(botaoVisualizar);
		toolbarInferior.appendChild(botaoAvaliar);
		toolbarInferior.appendChild(botaoSelecionarEspecialistas);
		toolbarInferior.appendChild(botaoAprovar);
		toolbarInferior.appendChild(botaoRejeitar);
		vbox.appendChild(toolbarInferior);

		vbox.setParent(this);
		
	}
	
	public void preencherListboxItensPendentesAvaliacao(){
		
		// Limpa o listbox
		List<Listitem> itensList = listboxQtdeItens.getItems();
		while (itensList.size()>0)
			itensList.remove(0);
		
		// fiz recuperar todos somente para teste
		Collection<ItemConhecimento> itens = ctrlGerenciaConhecimento.recuperarItensConhecimentoPendentesPorUsuarioAtual();
		for (ItemConhecimento item : itens){
			listitem = new Listitem();
			listitem.setValue(item);
			preencherLinhaListbox(item);
			listitem.setParent(listboxQtdeItens);
		}
		
		labelQtdeItens.setValue("Quantidade de Itens: " + itens.size());
		
	}
	
	public void preencherLinhaListbox(ItemConhecimento item){
		
		//coluna radio
		listcellRadio = new Listcell("");
		listcellRadio.setParent(listitem);
		
		//coluna titulo
		listcellTitulo = new Listcell(item.getTitulo());
		listcellTitulo.setParent(listitem);
		
		//coluna informações
		Vbox vboxInformacoes = new Vbox();
		
		listcellInformacoes = new Listcell();
		
		//autor
		Hbox hboxAutor = new Hbox();
		labelAutorValor = new Label(item.getAutor().getNome());
		labelAutorValor.setStyle("font-weight: bold; color: black;");
		Label labelAutor = new Label("Autor: ");
		labelAutor.setParent(hboxAutor);
		labelAutorValor.setParent(hboxAutor);
		hboxAutor.setParent(vboxInformacoes);
		
		//data de criacao
		Hbox hboxDataCriacao = new Hbox();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		labelDataCriacaoValor = new Label(formatador.format(item.getDataCriacao()));
		labelDataCriacaoValor.setStyle("font-weight: bold; color: black;");
		Label labelDataCriacao = new Label("Data de criação: ");
		labelDataCriacao.setParent(hboxDataCriacao);
		labelDataCriacaoValor.setParent(hboxDataCriacao);
		hboxDataCriacao.setParent(vboxInformacoes);
		
		//tipo
		Hbox hboxTipo = new Hbox();
		if(item instanceof LicaoAprendida){
			labelTipoValor = new Label("Lição Aprendida");
			labelTipoValor.setStyle("font-weight: bold; color: black;");
			Label labelTipo = new Label("Tipo: ");
			labelTipo.setParent(hboxTipo);
			labelTipoValor.setParent(hboxTipo);
			hboxTipo.setParent(vboxInformacoes);
		}
		if(item instanceof ConhecimentoRelativoDiscussao){
			labelTipoValor = new Label("Conhecimento Relativo a uma Discussão");
			labelTipoValor.setStyle("font-weight: bold; color: black;");
			Label labelTipo = new Label("Tipo: ");
			labelTipo.setParent(hboxTipo);
			labelTipoValor.setParent(hboxTipo);
			hboxTipo.setParent(vboxInformacoes);
		}
		
		vboxInformacoes.setParent(listcellInformacoes);
		listcellInformacoes.setParent(listitem);
		
		//coluna Avaliadores Selecionados
		Vbox vboxAvaliadoresSelecionados = new Vbox();
		
		listcellAvaliadoresSelecionados = new Listcell();
		
		Label labelAvaliadores = new Label("Avaliadores:");
		labelAvaliadores.setParent(vboxAvaliadoresSelecionados);
		
		Collection<RecursoHumano> itens = item.getAvaliadores();
		for (RecursoHumano recurso : itens){
			Label labelNomeAutor = new Label();
			labelNomeAutor.setValue("- " + recurso.getNome());
			labelNomeAutor.setStyle("font-weight: bold; color: black;");
			labelNomeAutor.setParent(vboxAvaliadoresSelecionados);
		}
		
		//quantidade de avaliacoes realizadas
		Hbox hboxAvaliacoesRealizadas = new Hbox();
		Label labelAvaliacoesRealizadas = new Label("Avaliações Realizadas: ");
		Label labelAvaliacoesRealizadasValor = new Label(Integer.toString(item.getAvaliacoes().size()));
		labelAvaliacoesRealizadasValor.setStyle("font-weight: bold; color: black;");
		labelAvaliacoesRealizadas.setParent(hboxAvaliacoesRealizadas);
		labelAvaliacoesRealizadasValor.setParent(hboxAvaliacoesRealizadas);
		hboxAvaliacoesRealizadas.setParent(vboxAvaliadoresSelecionados);
		
		vboxAvaliadoresSelecionados.setParent(listcellAvaliadoresSelecionados);
		listcellAvaliadoresSelecionados.setParent(listitem);
		
		//coluna estado
		listcellEstado = new Listcell(item.getEstado());
		listcellEstado.setParent(listitem);
		
	}
}

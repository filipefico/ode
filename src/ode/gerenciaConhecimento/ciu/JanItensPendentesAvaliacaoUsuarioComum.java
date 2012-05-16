package ode.gerenciaConhecimento.ciu;

import java.text.SimpleDateFormat;
import java.util.Collection;

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
import org.zkoss.zul.Panel;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

public class JanItensPendentesAvaliacaoUsuarioComum extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Label labelQtdeItensValor = new Label("0");
	Listbox listboxQtdeItens = new Listbox();
	Listitem listitem;
	Listcell listcellRadio;
	Listcell listcellTitulo;
	Listcell listcellAutor;
	Listcell listcellDataCriacao;
	Listcell listcellTipo;
	
	
	public JanItensPendentesAvaliacaoUsuarioComum(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanItensPendentesAvliacao();
		
	}
	
	public void alterarQtdeItensPendentes(int qtde){
		
		labelQtdeItensValor.setValue(Integer.toString(qtde));
		
	}
	
	public void criarJanItensPendentesAvliacao(){
			
		this.setTitle("Itens Pendentes de Avaliação");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		Label labelQtdeItens = new Label();
			
		labelQtdeItens.setValue("Quantidade de Itens: " + labelQtdeItensValor.getValue());
		labelQtdeItens.setParent(vbox);
			
		//listbox	
		Listhead colunas = new Listhead();;
		Listheader colunaRadioButton = new Listheader(" ");
		Listheader colunaTitulo = new Listheader("Título");
		Listheader colunaAutor = new Listheader("Autor");
		Listheader colunaDataCriacao = new Listheader("Data de Criação");
		Listheader colunaTipo = new Listheader("Tipo");
		
		colunaRadioButton.setWidth("30px");
		listboxQtdeItens.setMultiple(false);
		listboxQtdeItens.setCheckmark(true);
		listboxQtdeItens.setSizedByContent(true);
		
		colunaRadioButton.setParent(colunas);
		colunaTitulo.setParent(colunas);
		colunaAutor.setParent(colunas);
		colunaDataCriacao.setParent(colunas);
		colunaTipo.setParent(colunas);
		colunas.setParent(listboxQtdeItens);
		
		preencherListboxItensPendentesAvaliacao();
		listboxQtdeItens.setParent(vbox);
			
		//Botões
		Button botaoVisualizar = new Button("Visualizar");
		Button botaoAvaliar = new Button("Avaliar");
		Toolbar toolbarInferior = new Toolbar();
		
		botaoVisualizar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimentoUsuarioComum();
				
			}
		});
		
		botaoAvaliar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.exibirJanelaAvaliarItemConhecimento();
				
			}
		});
		
		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");
			
		toolbarInferior.appendChild(botaoVisualizar);
		toolbarInferior.appendChild(botaoAvaliar);
		vbox.appendChild(toolbarInferior);

		vbox.setParent(this);
			
	}
	
	public void preencherListboxItensPendentesAvaliacao(){
		
		// fiz recuperar todos somente para teste
		Collection<ItemConhecimento> itens = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itens){
			listitem = new Listitem();
			listitem.setValue(item);
			preencherLinhaListbox(item);
			listitem.setParent(listboxQtdeItens);
		}
		
	}
	
	public void preencherLinhaListbox(ItemConhecimento item){
		
		listcellRadio = new Listcell("");
		listcellRadio.setParent(listitem);
		
		listcellTitulo = new Listcell(item.getTitulo());
		listcellTitulo.setParent(listitem);
		
		listcellAutor = new Listcell(item.getAutor().getNome());
		listcellAutor.setParent(listitem);
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		listcellDataCriacao = new Listcell(formatador.format(item.getDataCriacao()));
		listcellDataCriacao.setParent(listitem);
		
		if(item instanceof LicaoAprendida){
			listcellTipo = new Listcell("Lição Aprendida");
			listcellTipo.setParent(listitem);
		}
		if(item instanceof ConhecimentoRelativoDiscussao){
			listcellTipo = new Listcell("Conhecimento Relativo a uma Discussão");
			listcellTipo.setParent(listitem);
		}
		
		
	}
	
}

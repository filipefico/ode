package ode.gerenciaConhecimento.ciu;

import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;

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

public class JanPaginasAmarelasListaBuscarPessoas extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Label labelQtdePessoasEncontradasValor = new Label("0");
	Listbox listbox = new Listbox();
	
	public JanPaginasAmarelasListaBuscarPessoas(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanPaginasAmarelasListaBuscarPessoas();
		
	}
	
	public void alterarLabelQuantidade(int qtde){
		
		labelQtdePessoasEncontradasValor.setValue(Integer.toString(qtde));
		
	}
	
	public void criarJanPaginasAmarelasListaBuscarPessoas(){
		
		this.setTitle("Páginas Amarelas");
		this.setBorder("normal");
		
		listbox.setMultiple(true);
		listbox.setCheckmark(true);
		
		Label labelQtdePessoasEncontrados = new Label("Quantidade de Pessoas Encontradas: " + labelQtdePessoasEncontradasValor.getValue());
		labelQtdePessoasEncontrados.setParent(this);
		
		Listhead listhead = new Listhead();
		Listheader listheaderNome = new Listheader("Nome");
		Listheader listheaderContato = new Listheader("Contato");
		
		listheaderNome.setParent(listhead);
		listheaderContato.setParent(listhead);
		listhead.setParent(listbox);
		
		listbox.setParent(this);
		
		Button botaoVisualizar = new Button("Visualizar");
		Button botaoSelecionar = new Button("Selecionar");
		Button botaoNovaBusca = new Button("Nova Busca");
		
		Toolbar toolbarInferior = new Toolbar();

		botaoVisualizar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
			//	ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimentoUsuarioComum();
				ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimentoGerente();
			}
		});

		

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVisualizar);
		toolbarInferior.appendChild(botaoSelecionar);
		toolbarInferior.appendChild(botaoNovaBusca);

		toolbarInferior.setParent(this);
		
		this.preencherLista();
		
	}
	
	public void preencherLista(){
		Collection<RecursoHumano> recursosHumanos = ctrlGerenciaConhecimento.recuperarRecursosHumanosAtivos();
		for (RecursoHumano recursoHumano : recursosHumanos) {
			Listitem listitem = new Listitem();
			listitem.setParent(listbox);
			
			new Listcell(recursoHumano.getNome()).setParent(listitem);
			new Listcell(recursoHumano.getTelefone() + " - " + recursoHumano.getEmail()).setParent(listitem);
			
		}
	}
	
}

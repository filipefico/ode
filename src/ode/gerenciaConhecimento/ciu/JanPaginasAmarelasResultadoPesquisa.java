package ode.gerenciaConhecimento.ciu;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;

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

public class JanPaginasAmarelasResultadoPesquisa extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Label labelQuantidadePessoasValor = new Label();
	Listbox listboxPessoasEncontradas = new Listbox();
	Listitem listitem;
	
	public JanPaginasAmarelasResultadoPesquisa(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanPaginasAmarelasResultadoPesquisa();
	}
	
	public void alterarLabelQuantidade(int qtde){
		
		labelQuantidadePessoasValor.setValue(Integer.toString(qtde));
		
	}
	
	public void preencherLinhaListbox(RecursoHumano recurso){
		
		Listcell listcellNome = new Listcell(recurso.getNome());
		Listcell listcellContato = new Listcell();
		
		listcellNome.setParent(listitem);
		listcellContato.setParent(listitem);
		
		Vbox vboxContato = new Vbox();
		vboxContato.setParent(listcellContato);
		
		Label labelEmail = new Label("E-mail: " + recurso.getEmail());
		Label labelTelefone = new Label("Telefone: " + recurso.getTelefone());
		
		labelEmail.setParent(vboxContato);
		labelTelefone.setParent(vboxContato);
		
	}
	
	public void criarListboxPessoasEncontradas(){
		
		Collection<RecursoHumano> recursoHumano = ctrlGerenciaConhecimento.aplCadastrarRecursoHumano.recuperarTodos();
		for (RecursoHumano recurso : recursoHumano){
			listitem = new Listitem();
			listitem.setValue(recurso);
			preencherLinhaListbox(recurso);
			listitem.setParent(listboxPessoasEncontradas);
		}
		
	}
	
	public void criarJanPaginasAmarelasResultadoPesquisa(){
		
		this.setTitle("Páginas Amarelas - Resultado Pesquisa");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		
		Label labelQuantidadePessoas = new Label("Quantidade de Pessoas Encontradas: " + labelQuantidadePessoasValor.getValue());
		
		labelQuantidadePessoas.setParent(vbox);
		
		listboxPessoasEncontradas.setMultiple(true);
		listboxPessoasEncontradas.setCheckmark(true);
		
		Listhead listhead = new Listhead();
		Listheader listheaderNome = new Listheader("Nome");
		Listheader listheaderContato = new Listheader("Contato");
		
		listheaderNome.setParent(listhead);
		listheaderContato.setParent(listhead);
		listhead.setParent(listboxPessoasEncontradas);
		
		criarListboxPessoasEncontradas();
		
		listboxPessoasEncontradas.setParent(vbox);
		
		///////////////////
		// Botoes
		///////////////////
		Button botaoVisualizar = new Button("Visualizar");
		Button botaoSelecionar = new Button("Selecionar");
		Button botaoNovaBusca = new Button("Nova Busca");
		
		Toolbar toolbarInferior = new Toolbar();

		botaoNovaBusca.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaPaginasAmarelasBuscarPessoas();
			}
		});
		
		botaoVisualizar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

			}
		});
		
		botaoSelecionar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				
			}
		});


		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVisualizar);
		toolbarInferior.appendChild(botaoSelecionar);
		toolbarInferior.appendChild(botaoNovaBusca);
		
		toolbarInferior.setParent(vbox);
		
		vbox.setParent(this);
		
		
		
	}
	
}

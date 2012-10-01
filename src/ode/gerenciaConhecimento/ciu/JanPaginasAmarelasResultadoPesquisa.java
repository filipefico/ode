package ode.gerenciaConhecimento.ciu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;

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

public class JanPaginasAmarelasResultadoPesquisa extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Label labelQuantidadePessoasValor = new Label();
	Listbox listboxPessoasEncontradas = new Listbox();
	Listitem listitem;
	
	Collection<RecursoHumano> recursos;
	
	public JanPaginasAmarelasResultadoPesquisa(CtrlGerenciaConhecimento ctrl, Collection<RecursoHumano> recursos) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		this.recursos = recursos;
		
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
		
		Hbox hboxEmail = new Hbox();
		Label labelEmailValor = new Label(recurso.getEmail());
		labelEmailValor.setStyle("font-weight: bold; color: black;");
		Hbox hboxTelefone = new Hbox();
		Label labelTelefoneValor = new Label(recurso.getTelefone());
		labelTelefoneValor.setStyle("font-weight: bold; color: black;");
		
		Label labelEmail = new Label("E-mail: ");
		Label labelTelefone = new Label("Telefone: ");
		
		labelEmail.setParent(hboxEmail);
		labelEmailValor.setParent(hboxEmail);
		
		labelTelefone.setParent(hboxTelefone);
		labelTelefoneValor.setParent(hboxTelefone);
		
		hboxEmail.setParent(vboxContato);
		hboxTelefone.setParent(vboxContato);
		
	}
	
	public void criarListboxPessoasEncontradas(){
		
		for (RecursoHumano recurso : recursos){
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
				/*para visualizar um perfil, no listbox deve estar selecionado somente
				um perfil (verificar isso)*/
				int itensSelecionados = listboxPessoasEncontradas.getSelectedCount();
				
				Object object = new Object();
				if(itensSelecionados == 1){
					object = listboxPessoasEncontradas.getSelectedItem().getValue();
					
					if(object != null){
						ctrlGerenciaConhecimento.exibirJanelaPaginasAmarelasVisualizarPerfil((RecursoHumano) object);
					}
				}else{
					Messagebox
					.show("Para visualizar um perfil completo, selecione somente uma pessoa",
							"Informação",
							Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}
		});
		
		botaoSelecionar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (ctrlGerenciaConhecimento.possuiItemNaSessao()){
					
					Set<Listitem> itens = listboxPessoasEncontradas.getSelectedItems();
					Set<RecursoHumano> avaliadores = new HashSet<RecursoHumano>();
					for (Listitem item : itens){
						avaliadores.add((RecursoHumano)item.getValue());
					}
					ctrlGerenciaConhecimento.definirAvaliadores(avaliadores);
					ctrlGerenciaConhecimento.exibirJanelaItensPendentesAvaliacao();
				}
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

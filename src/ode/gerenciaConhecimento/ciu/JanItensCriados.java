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
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanItensCriados extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Listitem listitem;
	Label labelQuantidadeItensValor = new Label("0");
	Listbox listboxItensCriados = new Listbox();
	Listcell listcellTitulo;
	Listcell listcellAutor;
	Listcell listcellDataCriacao;
	Listcell listcellTipo;
	Listcell listcellStatus;
	//List<ItemConhecimento> l = new List<ItemConhecimento>();

	
	public JanItensCriados(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanItensCriados();
		
	}
	
	public void alterarLabelQuantidade(int qtde){
		
		labelQuantidadeItensValor.setValue(Integer.toString(qtde));
		
	}
	
	public void preencherListbox(){
		// fiz recuperar todos somente para teste
		Collection<ItemConhecimento> itens = ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.recuperarTodos();
		for (ItemConhecimento item : itens){
			listitem = new Listitem();
			listitem.setValue(item);
			preencherLinhaListbox(item);
			listitem.setParent(listboxItensCriados);
		}
	}
	
	public void preencherLinhaListbox(ItemConhecimento item){
		
		Listcell listcellRadio = new Listcell("");
		listcellRadio.setParent(listitem);
		listcellTitulo = new Listcell(item.getTitulo());
		listcellTitulo.setParent(listitem);
		listcellAutor = new Listcell(item.getAutor().getNome());
		listcellAutor.setParent(listitem);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		listcellDataCriacao = new Listcell(formatador.format(item.getDataCriacao()));
		listcellDataCriacao.setParent(listitem);

		if (item instanceof LicaoAprendida){
			listcellTipo = new Listcell("Li��o Aprendida");
			listcellTipo.setParent(listitem);
		}
		if (item instanceof ConhecimentoRelativoDiscussao){
			listcellTipo = new Listcell("Conhecimento Relativo a uma Discuss�o");
			listcellTipo.setParent(listitem);
		}
		
		listcellStatus = new Listcell("Statustatus");
		listcellStatus.setParent(listitem);
		
	}
	
	public void criarJanItensCriados(){
		
		this.setTitle("Itens Criados");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		
		Label labelQuantidadeItens = new Label("Quantidade de Itens: " + labelQuantidadeItensValor.getValue());
		
		labelQuantidadeItens.setParent(vbox);
		
		listboxItensCriados.setMultiple(false);
		listboxItensCriados.setCheckmark(true);
		listboxItensCriados.setSizedByContent(true);
		listboxItensCriados.setWidth("560px");
		Listhead listheadItensCriados = new Listhead();
		
		Listheader listheaderRadio = new Listheader(" ");
		Listheader listheaderTitulo = new Listheader("T�tulo");
		Listheader listheaderAutor = new Listheader("Autor");
		Listheader listheaderDataCriacao = new Listheader("Data da Cria��o");
		Listheader listheaderTipo = new Listheader("Tipo");
		Listheader listheaderStatus = new Listheader("Status");
		
		listheaderRadio.setParent(listheadItensCriados);
		listheaderTitulo.setParent(listheadItensCriados);
		listheaderAutor.setParent(listheadItensCriados);
		listheaderDataCriacao.setParent(listheadItensCriados);
		listheaderTipo.setParent(listheadItensCriados);
		listheaderStatus.setParent(listheadItensCriados);
		
		listheadItensCriados.setParent(listboxItensCriados);
		
		preencherListbox();
		
		listboxItensCriados.setParent(vbox);
		
		Button botaoVisualizar = new Button("Visualizar");
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

		toolbarInferior.setParent(vbox);
		
		vbox.setParent(this);
		
		
	}
}
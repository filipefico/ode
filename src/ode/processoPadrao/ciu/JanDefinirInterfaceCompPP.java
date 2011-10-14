package ode.processoPadrao.ciu;

import java.util.Collection;
import java.util.Iterator;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;

import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;

public class JanDefinirInterfaceCompPP {
	private CtrlDefinirProcessoPadrao ctrl;
	private JanelaSimples janela;
	private Listbox listbox;

	public JanDefinirInterfaceCompPP(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			JanelaSimples JanelaSimples) {

		ctrl = ctrlDefinirProcessoPadrao;
		janela = JanelaSimples;

		configuracaoBasica();
		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuracaoBasica() {
		janela.setTitle("Definir CompPP");
		janela.setWidth("600px");
		janela.setBorder("normal");
		janela.setClosable(true);
		janela.setPosition("&quot;center;&quot;;");
		janela.setSizable(true);
		janela.setMaximizable(true);
	}

	private void configuraElementosJanela() {
		listbox = new Listbox();
		Button buttonOK = new Button();

		listbox.setMultiple(true);
		listbox.setCheckmark(true);
		listbox.setParent(janela);

		preencherListBox(listbox);

		buttonOK.setParent(janela);
		buttonOK.setLabel("Salvar");
		buttonOK.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				//ctrl.salvarCompPP(compPP);
				janela.onClose();// fecha a janela
			}
		});

	}

	private void preencherListBox(Listbox listbox) {
		Listhead listhead = new Listhead();
		Listheader listheader1 = new Listheader();
		Listheader listheader2 = new Listheader();

		listhead.setParent(listbox);
		listheader1.setParent(listhead);
		listheader2.setParent(listhead);

		listheader1.setLabel("Obrigatorio");
		listheader2.setLabel("Nome");

		
		insereElementosLista(listbox);
		configuraElementosLista(listbox);
		
				
		//marca itens que fazem parte do objeto CompPP selecionado.
		//ctrl.getcompPPSelecionado().getInterfaceCompPP().get
		
	}

	private void configuraElementosLista(Listbox listbox) {
		//carrega os atributos dos elementos que estão no compPP selecionado e seta na lista.
		/*Set<ElementoCompPP> elementos = ctrl.getcompPPSelecionado().getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP();
		
		Object obj = listbox.getItems();
		for (ElementoCompPP elementoCompPP : elementos) {
			
		}*/
	}

	private void insereElementosLista(Listbox listbox) {
		@SuppressWarnings("rawtypes")
		Collection listaK;
		//obtem valores
		if (ctrl.getcompPPSelecionado().getClass()
				.equals(CompPPProcessoComplexo.class)) {
			listaK = ctrl.getAllKProcesso();
		} else {
			listaK = ctrl.getAllKAtividade();
		}

		//insere valores na lista
		for (Iterator iterator = listaK.iterator(); iterator.hasNext();) {
			//kProcesso ou Katividade
			Conhecimento conhecimento = (Conhecimento) iterator.next();  

			Listitem listitem = new Listitem();
			Listcell listcell1 = new Listcell();
			Listcell listcell2 = new Listcell();

			Checkbox checkbox = new Checkbox();

			listitem.setParent(listbox);

			listcell1.setParent(listitem);
			listcell1.appendChild(checkbox);

			listcell2.setParent(listitem);
			listcell2.setLabel(conhecimento.getNome());
			listcell2.setValue(conhecimento);
		}
	}
}

class eventoDrop implements EventListener {

	public void onEvent(Event arg0) throws Exception {
		DropEvent dropEvent = (DropEvent) arg0;
		Listitem listItemDragged = (Listitem) dropEvent.getDragged();
		Listitem listItemTarget = (Listitem) dropEvent.getTarget();

		listItemDragged.getParent().removeChild(listItemDragged);
		listItemTarget.getParent()
				.insertBefore(listItemDragged, listItemTarget);
	}
}
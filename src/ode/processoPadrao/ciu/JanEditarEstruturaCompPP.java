package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.ElementoCompPP;

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

public class JanEditarEstruturaCompPP extends JanCore {
	private static final long serialVersionUID = 3035850190447854404L;
	private JanelaSimples janela;
	private Listbox listbox;

	public JanEditarEstruturaCompPP(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		janela = this;

		configuracaoBasica();
		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuraElementosJanela() {

		janela.setTitle("Editar estrutura");

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
				salvarCompPPAlterado(); // salva alterações
				janela.onClose();// fecha a janela
			}
		});

	}

	private void salvarCompPPAlterado() {
		Set<Listitem> itens = listbox.getSelectedItems();

		Set<Conhecimento> selecionados = new HashSet<Conhecimento>();
		Set<Conhecimento> selecionadosObrigatorios = new HashSet<Conhecimento>();

		for (Listitem item : itens) {
			Conhecimento conhecimento = (Conhecimento) ((Listcell) item
					.getChildren().get(1)).getValue();
			Checkbox checkBox = (Checkbox) ((Listcell) item.getChildren()
					.get(0)).getChildren().get(0);

			boolean obrigatorio = checkBox.isChecked();

			if (obrigatorio) {
				selecionadosObrigatorios.add(conhecimento);
			} else {
				selecionados.add(conhecimento);
			}
		}

		ctrl.atualizarEstruturaCompPP(selecionados, selecionadosObrigatorios);
	}

	private void preencherListBox(Listbox listbox) {
		Listhead listhead = new Listhead();
		Listheader listheader1 = new Listheader();
		Listheader listheader2 = new Listheader();

		listhead.setParent(listbox);
		listheader1.setParent(listhead);
		listheader2.setParent(listhead);

		listheader1.setLabel("Selecionados");
		listheader2.setLabel("Nome");

		insereElementosLista(listbox);
		configuraElementosLista(listbox);
	}

	private void configuraElementosLista(Listbox listbox) {

		Set<ElementoCompPP> elementosObj = ctrl.getcompPPSelecionado()
				.getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP();

		List<Conhecimento> elementosAssociadosObj = new ArrayList<Conhecimento>();
		List<Conhecimento> elementosAssociadosObrigatoriosObj = new ArrayList<Conhecimento>();

		// preenche duas listas para facil manipulacao.
		for (ElementoCompPP el : elementosObj) {
			if (el.isObrigatorio()) {
				Object o = el.getElementoConhecimento();
				elementosAssociadosObrigatoriosObj.add(el
						.getElementoConhecimento());
			} else {
				Object o = el.getElementoConhecimento();
				elementosAssociadosObj.add(el.getElementoConhecimento());
			}
		}

		// seta na janela os valores corretos nos checkboxes.
		marcaSelecionadosEObrigatorios(listbox, elementosAssociadosObj,
				elementosAssociadosObrigatoriosObj);

	}

	private void marcaSelecionadosEObrigatorios(Listbox listbox,
			List<Conhecimento> elementosAssociadosObj,
			List<Conhecimento> elementosAssociadosObrigatoriosObj) {
		Set<Listitem> listItemsSelecionados = new HashSet<Listitem>();
		for (Iterator iterator = listbox.getItems().iterator(); iterator
				.hasNext();) {
			Listitem listItem = (Listitem) iterator.next();

			Conhecimento conhecimentosJan = (Conhecimento) ((Listcell) listItem
					.getChildren().get(1)).getValue();

			if (elementosAssociadosObj.contains(conhecimentosJan)) {
				listItemsSelecionados.add(listItem);
			}

			if (elementosAssociadosObrigatoriosObj.contains(conhecimentosJan)) {
				Checkbox checkBoxObrigatorio = (Checkbox) ((Listcell) listItem
						.getChildren().get(0)).getChildren().get(0);

				checkBoxObrigatorio.setChecked(true);
				listItemsSelecionados.add(listItem);
			}
		}
		// seta os elementos xselecionados.
		listbox.setSelectedItems(listItemsSelecionados);
	}

	private void insereElementosLista(Listbox listbox) {
		@SuppressWarnings("rawtypes")
		Collection listaK;
		// obtem valores
		if (ctrl.getcompPPSelecionado().getClass()
				.equals(CompPPProcessoComplexo.class)) {
			listaK = ctrl.getAllKProcesso();
		} else {
			listaK = ctrl.getAllKAtividade();
		}

		// insere valores na lista
		for (Iterator iterator = listaK.iterator(); iterator.hasNext();) {
			// kProcesso ou Katividade
			Conhecimento conhecimento = (Conhecimento) iterator.next();

			Listitem listitem = new Listitem();
			Listcell listcell1 = new Listcell();
			Listcell listcell2 = new Listcell();

			Checkbox checkbox = new Checkbox();
			checkbox.setLabel("(obrigatório)");

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

package ode._controleRecursoHumano.ciu;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;

public class PainelDefinirEquipe extends Panel {

	private static final long serialVersionUID = 1L;

	private CtrlDefinirEquipe ctrlDefinirEquipe;
	
	private NucleoListbox<RecursoHumano> listBox = new NucleoListbox<RecursoHumano>();

	public PainelDefinirEquipe(CtrlDefinirEquipe ctrlSelecionarProjeto){
		super();
		this.ctrlDefinirEquipe = ctrlSelecionarProjeto;

		// Cria lista de recursos humanos
		Panelchildren panelchildren = new Panelchildren();
		panelchildren.setParent(this);
		listBox.setHeight("300px");
		listBox.setCheckmark(true);
		listBox.setMultiple(true);
		listBox.setParent(panelchildren);
		
		for (RecursoHumano rh : ctrlDefinirEquipe.listarRecursosHumanos()) {
			Listitem li = new Listitem();
			li.setValue(rh);

			Listcell listCell = new Listcell(rh.getNome());
			li.appendChild(listCell);
			Listcell listCell2 = new Listcell(rh.getCargo().getNome());
			li.appendChild(listCell2);

			listBox.appendChild(li);
		}

		listBox.setObjetosSelecionados(ctrlDefinirEquipe.listarRecursosHumanosEquipe());
		Listhead listHead = new Listhead();
		listHead.setParent(listBox);
		Listheader listHeaderRH = new Listheader("Recurso Humano");
		listHeaderRH.setParent(listHead);
		Listheader listHeaderCargo = new Listheader("Cargo");
		listHeaderCargo.setParent(listHead);

		// Bot�es OK e Cancelar
		Div div = new Div();
		div.setParent(panelchildren);
		div.setStyle("float: right;");
		Separator separator = new Separator();
		separator.setParent(div);
		Button botaoOK = new Button("OK");
		botaoOK.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				ctrlDefinirEquipe.definirEquipe(listBox.getObjetosSelecionados());
				ctrlDefinirEquipe.finalizar();
				Messagebox.show("Equipe definida com sucesso.");
			}
			
		});
		botaoOK.setWidth("100px");
		botaoOK.setStyle("margin-right: 5px");
		botaoOK.setParent(div);
		Button botaoCancelar = new Button("Cancelar");
		botaoCancelar.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				ctrlDefinirEquipe.finalizar();
			}
		});
		botaoCancelar.setWidth("100px");
		botaoCancelar.setParent(div);
	}
}
package ode.alocacaoRecursoHumano.ciu;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;

public class PainelDefinirEquipe extends Hbox {
	
	private static final long serialVersionUID = 1L;
		
	public PainelDefinirEquipe(final CtrlAlocacaoRecursoHumano ctrl) {
		
		Collection<KRecursoHumano> listaKRH = ctrl.listarKRecursosHumanosPorProjeto();
		
		if(listaKRH.size()>0) {
			NucleoTabbox tabbox = new NucleoTabbox();
			//tabbox.setHeight("180px");
			
			tabbox.setParent(this);
			tabbox.setOrient("vertical");
			tabbox.getTabs().setWidth("150px");
			
			for(final KRecursoHumano krh : listaKRH) {
				NucleoTab tab = new NucleoTab(krh.getNome());
				
				Collection<RecursoHumano> rhs = ctrl.listarRecursosHumanosPorPapel(krh);
				if(rhs.size()>0) {
					final NucleoListbox<RecursoHumano> listbox = new NucleoListbox<RecursoHumano>();
					
					listbox.setObjetos(ctrl.listarRecursosHumanosPorPapel(krh));
					listbox.setObjetosSelecionados(ctrl.listarParticipacoesRecursosHumanosPorPapel(krh));
					listbox.setWidth("250px");
					listbox.setVflex("min");
					listbox.setRows(5);
					listbox.setCheckmark(true);
					listbox.setMultiple(true);
		
					tab.addElemento(listbox);
					Button buttonOK = new Button("Definir");
					buttonOK.addEventListener("onClick", new EventListener() {
						public void onEvent(Event arg0) throws Exception {
							ctrl.definirEquipe(listbox.getObjetosSelecionados(),krh);				
						}
					});
					
					tab.addElemento(buttonOK);
				}
				else {
					Label label = new Label("Nenhum Recurso Humano apto a este papel.");
					tab.addElemento(label);
				}
				
				tabbox.addTab(tab);
			}
		} else {
			Label label = new Label("Este projeto não requer Recursos Humanos");
			label.setParent(this);
		}
	}
}

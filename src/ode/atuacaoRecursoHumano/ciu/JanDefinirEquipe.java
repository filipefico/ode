package ode.atuacaoRecursoHumano.ciu;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.ciu.ListagemHistoricoEquipe;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;

public class JanDefinirEquipe extends JanelaSimples {

	private static final long serialVersionUID = 1L;

	private Collection<KRecursoHumano> listaKRH;

	private CtrlDefinirEquipe ctrl;

	public JanDefinirEquipe(final CtrlDefinirEquipe ctrl) {
		super();

		this.ctrl = ctrl;

		this.setTitle("Definir Equipe - " + ctrl.getProjeto().getNome());
		this.setHflex("min");

		listaKRH = ctrl.listarKRecursosHumanosPorProjeto();

		Hbox hbox = new Hbox();
		hbox.setParent(this);

		if (listaKRH.size() > 0) {

			NucleoTabbox tabbox = new NucleoTabbox();
			tabbox.setParent(this);
			NucleoTab tabEquipe = new NucleoTab("Equipe");
			tabbox.addTab(tabEquipe);
			NucleoTab tabHistorico = new NucleoTab("Histórico");
			tabbox.addTab(tabHistorico);

			preencherTabEquipe(tabEquipe);
			preencherTabHistorico(tabHistorico);
		} else {
			Label label = new Label("Este projeto não requer Recursos Humanos");
			label.setParent(hbox);
		}
	}

	private void preencherTabEquipe(NucleoTab tabEquipe) {
		NucleoTabbox tabboxInterno = new NucleoTabbox();
		tabEquipe.addElemento(tabboxInterno);
		tabboxInterno.setOrient("vertical");
		tabboxInterno.getTabs().setWidth("150px");

		for (final KRecursoHumano krh : listaKRH) {
			NucleoTab tab = new NucleoTab(krh.getNome());

			Collection<RecursoHumano> rhs = ctrl.atuacaoRHDAO
					.recuperarAptosPorPapel(krh.getId());
			if (rhs.size() > 0) {
				final NucleoListbox<RecursoHumano> listbox = new NucleoListbox<RecursoHumano>();

				listbox.setObjetos(rhs);
				listbox.setWidth("250px");
				listbox.setVflex("min");
				listbox.setRows(5);
				listbox.setCheckmark(true);
				listbox.setMultiple(true);
				listbox.setObjetosSelecionados(ctrl
						.listarParticipacoesRecursosHumanosPorPapel(krh));

				tab.addElemento(listbox);
				Button buttonOK = new Button("Definir");
				buttonOK.addEventListener("onClick", new EventListener() {
					public void onEvent(Event arg0) throws Exception {
						ctrl.definirEquipe(listbox.getObjetosSelecionados(),
								krh);
					}
				});

				tab.addElemento(buttonOK);
			} else {
				Label label = new Label(
						"Nenhum Recurso Humano apto a este papel.");
				tab.addElemento(label);
			}

			tabboxInterno.addTab(tab);
		}
	}
	
	private void preencherTabHistorico(NucleoTab tabHistorico) {
		ListagemHistoricoEquipe listagem = new ListagemHistoricoEquipe();
		
		listagem.atualizar(ctrl.listarHistoricoEquipe());
		listagem.configurarComponentes();
		tabHistorico.addElemento(listagem);
	}
}

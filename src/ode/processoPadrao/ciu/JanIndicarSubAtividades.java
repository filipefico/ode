package ode.processoPadrao.ciu;

import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPPMacroatividade;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

public class JanIndicarSubAtividades extends JanCore {

	private static final long serialVersionUID = -469457758735551751L;
	Listbox listaSubAtv;
	private JanelaSimples janela;

	public JanIndicarSubAtividades(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		janela = this;

		listaSubAtv = new Listbox();
		conteudo();

		this.mostrar();
	}

	private void conteudo() {
		this.setTitle("Definir SubAtividades");

		listaSubAtv.setCheckmark(true);
		listaSubAtv.setMultiple(true);
		listaSubAtv.setParent(this);

		preencheLista();

		botaoDefinirSubAtividade();
	}

	private void botaoDefinirSubAtividade() {
		Button buttonSalvar = new Button();
		buttonSalvar.setParent(this);
		buttonSalvar.setLabel("Definir");
		buttonSalvar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				salvarDados();
				janela.onClose();
			}

			private void salvarDados() {
				Set<KAtividade> listaKAtividades = obterListaSubArtefatos();

				adicionarSubAtividade(listaKAtividades);

				ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
			}

		});

	}

	private void adicionarSubAtividade(Set<KAtividade> listaKAtividades) {

		AtividadeProcessoPadrao atividadePP = ((CompPPMacroatividade) ctrl
				.getcompPPSelecionado()).getAtividadeProcessoPadrao();

		Set<AtividadeProcessoPadrao> listaAtvPP = new HashSet<AtividadeProcessoPadrao>();
		for (KAtividade kAtividade : listaKAtividades) {
			AtividadeProcessoPadrao atvPP = new AtividadeProcessoPadrao();
			atvPP.setTipo(kAtividade);
			listaAtvPP.add(atvPP);
		}
		atividadePP.addSubAtividades(listaAtvPP);

	}

	private Set<KAtividade> obterListaSubArtefatos() {
		Set<KAtividade> listaKatividades = new HashSet<KAtividade>();

		Set<Listitem> listItens = listaSubAtv.getSelectedItems();
		for (Listitem listitem : listItens) {
			Listcell listCell = (Listcell) listitem.getChildren().get(0);
			KAtividade ka = (KAtividade) listCell.getValue();
			listaKatividades.add(ka);
		}

		return listaKatividades;

	}

	private void preencheLista() {
		Listitem itemListaSubAtv = null;
		Set<Listitem> listItensAMarcar = new HashSet<Listitem>();

		for (KAtividade kAtividade : ctrl.getAllKAtividade()) {
			itemListaSubAtv = new Listitem();
			Listcell listcell = new Listcell();
			itemListaSubAtv.setParent(listaSubAtv);
			itemListaSubAtv.appendChild(listcell);

			listcell.setLabel(kAtividade.getNome());
			listcell.setValue(kAtividade);
		}

	}
}

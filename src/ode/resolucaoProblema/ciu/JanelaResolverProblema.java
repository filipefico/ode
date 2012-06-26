package ode.resolucaoProblema.ciu;

import java.util.Collection;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

public class JanelaResolverProblema extends JanelaSimples {

	private static final long serialVersionUID = 1708774118441345358L;
	private Collection<OcorrenciaProblema> listaocorrenciaproblema;
	private CtrlResolverProblema ctrl;
	private Tree arvore;
	private Button buttonPriorizarOcorrencia = new Button();

	public JanelaResolverProblema(CtrlResolverProblema ctrl) {
		super();
		this.ctrl = ctrl;
	}

	public void mostrar() {
		this.setTitle("Resolver Problema - Ocorrência de Problemas Listados");
		this.setHflex("min");

		Hlayout painel = new Hlayout();
		painel.setParent(this);

		painel.setSclass("z-valign-top");

		listaocorrenciaproblema = ctrl.aplocorrenciaproblema.recuperarTodos();

		if (listaocorrenciaproblema.size() > 0) {
			arvore = new Tree();
			arvore.setParent(painel);
			arvore.setWidth("400px");
			arvore.setHeight("400px");
			arvore.setCheckmark(true);
			arvore.setMultiple(true);
			arvore.setZclass("z-dottree");

			arvore.addEventListener("onSelect", new ArvoreSelectEventListener());

			ctrl.preencherPainelOcorrenciaProblema();
		} else {
			Label label = new Label(
					"Não existe Ocorrencia Problema Cadastrado.");
			label.setParent(painel);
		}
		buttonPriorizarOcorrencia.setLabel("Priorizar Ocorrência de Problemas");
		buttonPriorizarOcorrencia.setParent(this);
		buttonPriorizarOcorrencia.addEventListener("onClick",
				new EventListener() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						listaocorrenciaproblema = ctrl.aplocorrenciaproblema.recuperarTodosOrdenadoImpacto();
						preencherArvore();
					}
				});
		super.mostrar();
	}

	public void preencherArvore() {
		inserirNodesOcorrenciaProblema(listaocorrenciaproblema);

	}

	private void inserirNodesOcorrenciaProblema(
			Collection<OcorrenciaProblema> ocorrenciaproblema) {

		if (arvore.getTreechildren() != null)
			arvore.getTreechildren().setParent(null);
		for (OcorrenciaProblema ocorrenciaProblema : listaocorrenciaproblema) {

			if (arvore.getTreechildren() == null) {
				Treechildren tc = new Treechildren();
				tc.setParent(arvore);
			}

			Treeitem ti = new Treeitem(ocorrenciaProblema.getNome(),
					ocorrenciaProblema);
			ti.setParent(arvore.getTreechildren());
		}

	}

	public class ArvoreSelectEventListener implements EventListener {
		public void onEvent(Event e) throws Exception {

			SelectEvent event = (SelectEvent) e;
			Treeitem ti = (Treeitem) event.getReference();
			OcorrenciaProblema op = (OcorrenciaProblema) ti.getValue();
			ctrl.ocorrenciaproblema = op;
			ctrl.abrirControladorSelecionarCausa(op);

		}

	}

}

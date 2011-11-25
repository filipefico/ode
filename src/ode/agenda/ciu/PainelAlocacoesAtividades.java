package ode.agenda.ciu;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoUtil;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.ciu.CtrlEsforcoDespendidoCRUD;
import ode.alocacaoRecurso.ciu.PainelCRUDEsforcoDespendido;
import ode.controleProjeto.cdp.Projeto;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Vlayout;

public class PainelAlocacoesAtividades extends Hlayout {

	private static final long serialVersionUID = 1L;
	
	private CtrlAgenda ctrl;

	private Vlayout boxDireita;

	public PainelAlocacoesAtividades(CtrlAgenda ctrl) {
		this.ctrl = ctrl;
		
		this.setSclass("z-valign-top");
			
		Collection<Projeto> listaProjetos = ctrl.alocacaoRHDAO.recuperarProjetosPorRH(ctrl.getRecursoHumano().getId());
		
		if(listaProjetos.size()>0) {
			
			Tree arvore = new Tree();
			arvore.setParent(this);
			
			arvore.setWidth("302px");
			arvore.setHflex("min");
			arvore.setHeight("298px");
			arvore.setZclass("z-dottree");
			arvore.addEventListener("onSelect", new ArvoreEventListener());
			Treecols treecols = new Treecols();
			treecols.setParent(arvore);
			treecols.setSizable(true);
			Treecol tc1 = new Treecol("Atividade");
			tc1.setParent(treecols);
			tc1.setHflex("min");
			Treecol tc2 = new Treecol("Cargo");
			tc2.setParent(treecols);
			tc2.setHflex("min");
			
			inserirNodesRaiz(arvore, listaProjetos);
			
			boxDireita = new Vlayout();
			boxDireita.setWidth("400px");
			boxDireita.setHeight("300px");
			boxDireita.setParent(this);
			
		} else {
			Label label = new Label("Este recurso não está alocado a nenhuma atividade.");
			label.setParent(this);
		}
		
	}
	
	private void inserirNodesRaiz(Tree tree, Collection<Projeto> listaProjetos) {
		if(tree.getTreechildren()!=null)
			tree.getTreechildren().setParent(null);
		for (Projeto projeto : listaProjetos) {    
			if(tree.getTreechildren()==null) {
				Treechildren tc = new Treechildren();
				tc.setParent(tree);
			}
			Treeitem ti = new Treeitem();
			ti.setParent(tree.getTreechildren());
			
			Treerow tr = new Treerow();
			tr.setParent(ti);
			
			Treecell tc = new Treecell(projeto.getNome());
			tc.setParent(tr);
			tc.setSpan(2);
						
			inserirNodesProjeto(ti, projeto);	
        }
		
	}

	private void inserirNodesProjeto(Treeitem tiPai, Projeto projeto) {
		if(tiPai.getTreechildren()!=null)
			tiPai.getTreechildren().setParent(null);

		for (ProcessoProjetoEspecifico processo : ctrl.alocacaoRHDAO.recuperarProcessosComAlocacaoPorRecursoHumanoProjeto(ctrl.getRecursoHumano().getId(), projeto.getId())) {
			if(tiPai.getTreechildren()==null) {
				Treechildren tc = new Treechildren();
				tc.setParent(tiPai);
			}
			Treeitem tiProcesso = new Treeitem();
			tiProcesso.setParent(tiPai.getTreechildren());
			
			Treerow tr = new Treerow();
			tr.setParent(tiProcesso);
			
			Treecell tc = new Treecell(processo.getNome());
			tc.setParent(tr);
			tc.setSpan(2);
			
			inserirNodesProcesso(tiProcesso, processo);
		}
		
	}

	private void inserirNodesProcesso(Treeitem tiProcesso, ProcessoProjetoEspecifico processo) {
		if(tiProcesso.getTreechildren()!=null)
			tiProcesso.getTreechildren().setParent(null);
		
		for (AlocacaoRH alocacaoRH : ctrl.alocacaoRHDAO.recuperarPorRecursoHumanoProcesso(ctrl.getRecursoHumano().getId(), processo.getId())) {
			if(tiProcesso.getTreechildren()==null) {
				Treechildren tc = new Treechildren();
				tc.setParent(tiProcesso);
			}
			
			Treeitem tiAlocacaoRH = new Treeitem();
			tiAlocacaoRH.setValue(alocacaoRH);
			tiAlocacaoRH.setParent(tiProcesso.getTreechildren());

			Treerow tr = new Treerow();
			tr.setParent(tiAlocacaoRH);
			Treecell tc1 = new Treecell(alocacaoRH.getAtividade().getNome());
			tc1.setParent(tr);
			Treecell tc2 = new Treecell(alocacaoRH.getkRecursoHumano().getNome());
			tc2.setParent(tr);
		}
	}
	
	private class ArvoreEventListener implements EventListener {
		public void onEvent(Event e) throws Exception {
			SelectEvent event = (SelectEvent) e;
			Treeitem ti = (Treeitem) event.getReference();
			
			boxDireita.getChildren().clear();
			
			if(ti.getValue() instanceof AlocacaoRH) {
				preencherAlocacaoRH((AlocacaoRH) ti.getValue());
			}
		}
	}
	
	private void preencherAlocacaoRH(AlocacaoRH alocacaoRH) {
		NucleoTabbox tabbox  = new NucleoTabbox();
		tabbox.setParent(boxDireita);
		NucleoTab tabEsforco = new NucleoTab("Esforço Despendido");
		tabbox.addTab(tabEsforco);
		NucleoTab tabAlocacao = new NucleoTab("Alocação");
		tabAlocacao.getTabpanel().setHeight("265px");
		tabbox.addTab(tabAlocacao);
		NucleoTab tabFerramentas = new NucleoTab("Ferramentas Disponíveis");
		tabFerramentas.getTabpanel().setHeight("265px");
		tabbox.addTab(tabFerramentas);
		
		
		Collection<FerramentaSoftware> listaFerramentas = ctrl.alocacaoFerramentaSoftwareDAO.recuperarFerramentasAlocadasPorRH(ctrl.getRecursoHumano().getId());
		
		for (final FerramentaSoftware fs : listaFerramentas) {
			Button botaoFerramenta = new Button(fs.getNome(),"/imagens/ferramenta.png");
			botaoFerramenta.addEventListener("onClick", new EventListener() {
				public void onEvent(Event event) throws Exception {
					NucleoContexto.recuperarJanelaPrincipal().abrirJanela(fs.getCaminho());
				}
			});
			tabFerramentas.addElemento(botaoFerramenta);
		}

		GridDados grid = new GridDados();
		tabAlocacao.setConteudoTab(grid);
		
		grid.setLarguras("156px", "230px");
		grid.adicionarLinha("Papel", alocacaoRH.getkRecursoHumano().getNome());
		grid.adicionarLinha("Estado", alocacaoRH.getEstado().toString());
		grid.adicionarLinha("Data de Início Previsto", NucleoUtil.formataData(alocacaoRH.getDtInicioPrevisto()));
		grid.adicionarLinha("Data de Fim Previsto", NucleoUtil.formataData(alocacaoRH.getDtFimPrevisto()));
		grid.adicionarLinha("Data de Início Efetivo", NucleoUtil.formataData(alocacaoRH.getDtInicioEfetivo()));
		grid.adicionarLinha("Data de Fim Efetivo", NucleoUtil.formataData(alocacaoRH.getDtFimEfetivo()));
		grid.adicionarLinha("Dedicação", (alocacaoRH.getDedicacao() != null) ? alocacaoRH.getDedicacao().toString() : "");
		
		PainelCRUDEsforcoDespendido painelEsforco = SpringUtil.getApplicationContext().getBean(CtrlEsforcoDespendidoCRUD.class).iniciar(alocacaoRH);
		tabEsforco.setConteudoTab(painelEsforco);
	}	

}

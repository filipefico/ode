package ode.entidadeProblema.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.NucleoSwitcher;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.controleProjeto.cdp.Projeto;
import ode.entidadeProblema.cci.CtrlEntidadeProblema;
import ode.entidadeProblema.cdp.ArtefatoProblema;
import ode.entidadeProblema.cdp.ProjetoProblema;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vlayout;

public class PainelEntidadeProblema extends Vlayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6100974198557828646L;
	protected List<NucleoTab> listaTabs;
	protected Tabbox tabbox = new Tabbox();
	protected Tabs tabs = new Tabs();
	protected Tabpanels tabpanels = new Tabpanels();
	private NucleoSwitcher<KArtefato> nsar;
	private NucleoSwitcher<Projeto> nspt;
	private CtrlEntidadeProblema ctrl;

	
	
	private NucleoTab criaTabProjeto(){
		NucleoTab tab = new NucleoTab();
		tab.setNomeTab("Projeto");
		
		nspt = new NucleoSwitcher<Projeto>();
		tab.setConteudoTab(nspt);
		
		Collection<ProjetoProblema> projetosProblema = ctrl.recuperarProjetoProblema();
		Collection<Projeto> projetosProblema2 = new ArrayList<Projeto>();
		for (ProjetoProblema projetoProblema : projetosProblema) {
			projetosProblema2.add(projetoProblema.getProjeto());
		}
		
		Collection<Projeto> projetos = ctrl.recuperarProjeto();
		projetos.removeAll(projetosProblema2);
		
		nspt.montar("Projetos", "Projetos com Problema");
		
		nspt.setLeftItens(projetos);
		nspt.setRightItens(projetosProblema2);
		
		return tab;
		
	}
	
	private NucleoTab criaTabArtefato(){
		NucleoTab tab = new NucleoTab();
		tab.setNomeTab("Artefato");
		
		nsar = new NucleoSwitcher<KArtefato>();
		tab.setConteudoTab(nsar);
		
		Collection<ArtefatoProblema> artefatosProblema = ctrl.recuperarArtefatoProblema();
		Collection<KArtefato> artefatosProblema2 = new ArrayList<KArtefato>();
		for (ArtefatoProblema projetoProblema : artefatosProblema) {
			artefatosProblema2.add(projetoProblema.getKartefato());
		}
		
		Collection<KArtefato> artefatos = ctrl.recuperarArtefato();
		artefatos.removeAll(artefatosProblema2);
		
		nsar.montar("Artefatos", "Artefatos com Problema");
		
		nsar.setLeftItens(artefatos);
		nsar.setRightItens(artefatosProblema2);
		
		return tab;
		
	}

	
	public List<NucleoTab> getAbas(){
		List<NucleoTab> lista = new ArrayList<NucleoTab>();
		
		try{
			

			lista.add(criaTabArtefato());
	
			lista.add(criaTabProjeto());
		}catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<NucleoTab>();
		}
		return lista;
		
	}

	public void montar() {
		
		listaTabs = getAbas();
		
		for (NucleoTab nucleoTab : listaTabs) {
			// Cria tab
			Tab tab = new Tab(nucleoTab.getNomeTab());
			tab.setParent(tabs);

			// Cria painel com conteúdo
			Tabpanel tabpanel = new Tabpanel();
			tabpanel.setParent(tabpanels);
			Component conteudo = nucleoTab.getConteudoTab();
			conteudo.setParent(tabpanel);
		}
		tabpanels.setParent(tabbox);
		tabs.setParent(tabbox);
		this.appendChild(tabbox);
		
		Button salvar = new Button("Salvar");
		Toolbar toolbarInferior = new Toolbar();
		
		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");
		
		toolbarInferior.appendChild(salvar);
		salvar.addEventListener("onClick", new SalvarEntidades());
		this.appendChild(toolbarInferior);
	}
	
	private class SalvarEntidades implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			
			ctrl.atualizarArtefato(nsar.getLeftItens(),nsar.getRightItens());
			ctrl.atualizarProjeto(nspt.getLeftItens(),nspt.getRightItens());
			Messagebox.show("Configuração salva com sucesso.");
		}
	}
	
	public void setCtrl(CtrlEntidadeProblema ctrlEntidadeProblema) {
		this.ctrl = ctrlEntidadeProblema;
	}

}


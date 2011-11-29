package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;

import org.python.tests.ExceptionTest.Thrower;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

public class ComponenteObjetivosTree extends Tree{
	
	protected Treechildren arvore;
	protected EventListener ev;
	
	private ArrayList<Treeitem> estList;
	private ArrayList<Treeitem> sftList;
	private ArrayList<Treeitem> medList;
	private ArrayList<Treeitem> necInfList;
	private ArrayList<Treeitem> procList;
	
	public ComponenteObjetivosTree(){
		this.setCheckmark(true);
		this.setMultiple(true);
		montar();
	}
	
	public void montar(){
		Treecols abas = new Treecols();
		Treecol nome = new Treecol();
		nome.setLabel("Nome");
		
		abas.appendChild(nome);
		
		abas.setParent(this);
		
		arvore = new Treechildren();
		this.appendChild(arvore);
	}
	
	public void setEventoMedicao(EventListener event) {
		ev = event;
	}
	
	protected Treechildren adicionarLinha(Treechildren pai, ObjetoPersistente obj){
		Treeitem item = new Treeitem();
		Treerow row = new Treerow();
		pai.appendChild(item);
		item.appendChild(row);
		item.setValue(obj);
		
		Treecell nome = new Treecell();
		nome.setParent(row);
		nome.setLabel(obj.toString());
		
		item.addEventListener("onClick", new OpenCloseEvent());
		item.addEventListener("onOpen", new MarkUnmarkEvent());
		item.addEventListener("onClick", new SpreadEvent());
		
		if(obj instanceof KObjetivoEstrategico){
			estList.add(item);
			item.setOpen(false);
		}else if(obj instanceof KObjetivoSoftware){
			sftList.add(item);
			item.setOpen(false);
		}else if(obj instanceof KObjetivoMedicao){
			medList.add(item);
			item.setOpen(false);
		}else if(obj instanceof KNecessidadeInformacao){
			necInfList.add(item);
			item.setOpen(false);
		}else{
			procList.add(item);
			item.setOpen(false);
		}
		
		
		Treechildren children = new Treechildren();
		children.setParent(item);
		
		return children;
	}
	
	private class MarkUnmarkEvent implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			((Treeitem)arg0.getTarget()).setSelected(((Treeitem)arg0.getTarget()).isOpen());
		}
	}
	
	private class OpenCloseEvent implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			((Treeitem)arg0.getTarget()).setOpen(((Treeitem)arg0.getTarget()).isSelected());
		}
	}
	
	private class SpreadEvent implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			ObjetoPersistente obj= (ObjetoPersistente) ((Treeitem)arg0.getTarget()).getValue();
			
			if(obj instanceof KObjetivoEstrategico){
				spread(estList, obj,((Treeitem)arg0.getTarget()).isSelected() );
			}else if(obj instanceof KObjetivoSoftware){
				spread(sftList, obj,((Treeitem)arg0.getTarget()).isSelected() );
			}else if(obj instanceof KObjetivoMedicao){
				spread(medList, obj,((Treeitem)arg0.getTarget()).isSelected() );
			}else if(obj instanceof KNecessidadeInformacao){
				spread(necInfList, obj,((Treeitem)arg0.getTarget()).isSelected() );
				HashSet<KNecessidadeInformacao> itensRetorno = new HashSet<KNecessidadeInformacao>();
				for(Treeitem tree:necInfList){
					if(tree.isSelected()) itensRetorno.add((KNecessidadeInformacao)tree.getValue());
				}
				ev.onEvent(new Event("trocouNecessidade", arg0.getTarget(), itensRetorno));
			}else{
				spread(procList, obj,((Treeitem)arg0.getTarget()).isSelected() );
			}
		}
	}
	
	private void spread(Collection<Treeitem> lista, ObjetoPersistente espelho, Boolean b){
		for(Treeitem items:lista){
			if(espelho.equals(items.getValue())){
				items.setSelected(b);
				OpenCloseEvent ev = new OpenCloseEvent();
				try {
					ev.onEvent(new Event("onClick",items));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void atualizar(Collection<ObjetoPersistente> objetos){
		
	}
	
	public void iniciar(Collection<KObjetivoEstrategico> objetivos){
		this.clear();
		estList = new ArrayList<Treeitem>();
		sftList = new ArrayList<Treeitem>();
		medList = new ArrayList<Treeitem>();
		necInfList = new ArrayList<Treeitem>();
		procList = new ArrayList<Treeitem>();
		for(KObjetivoEstrategico est:objetivos){
			Treechildren estChildren = adicionarLinha(arvore, est);
			for(KObjetivoSoftware sft:est.getObjetivoSoftware()){
				Treechildren sftChildren = adicionarLinha(estChildren, sft);
				for(KObjetivoMedicao med:sft.getObjetivoMedicao()){
					Treechildren medChildren = adicionarLinha(sftChildren, med);
					for(KNecessidadeInformacao necInf:med.getNecessidadeInformacao()){
						Treechildren necInfChildren = adicionarLinha(medChildren, necInf);
						for(KProcesso proc:necInf.getProcessos()){
							adicionarLinha(necInfChildren, proc);
						}
					}
				}
			}
		}
	}

	public Set<KObjetivoEstrategico> getEstrategicosSelecionados() {
		Set<KObjetivoEstrategico> objsSelect = new HashSet<KObjetivoEstrategico>();
		for(Treeitem item:estList){
			if(item.isSelected())objsSelect.add((KObjetivoEstrategico)item.getValue());
		}
		return objsSelect;
	}

	public Collection<KObjetivoSoftware> getSoftwareSelecionados() {
		Set<KObjetivoSoftware> objsSelect = new HashSet<KObjetivoSoftware>();
		for(Treeitem item:sftList){
			if(item.isSelected())objsSelect.add((KObjetivoSoftware)item.getValue());
		}
		return objsSelect;
	}

	public Collection<KObjetivoMedicao> getMedicaoSelecionados() {
		Set<KObjetivoMedicao> objsSelect = new HashSet<KObjetivoMedicao>();
		for(Treeitem item:medList){
			if(item.isSelected())objsSelect.add((KObjetivoMedicao)item.getValue());
		}
		return objsSelect;
	}

	public Collection<KNecessidadeInformacao> getNecessidadesSelecionadas() {
		Set<KNecessidadeInformacao> objsSelect = new HashSet<KNecessidadeInformacao>();
		for(Treeitem item:necInfList){
			if(item.isSelected())objsSelect.add((KNecessidadeInformacao)item.getValue());
		}
		return objsSelect;
	}

	public Collection<KProcesso> getProcessosSelecionados() {
		Set<KProcesso> objsSelect = new HashSet<KProcesso>();
		for(Treeitem item:procList){
			if(item.isSelected())objsSelect.add((KProcesso)item.getValue());
		}
		return objsSelect;
	}
	
}

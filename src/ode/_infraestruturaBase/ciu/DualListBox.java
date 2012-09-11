package ode._infraestruturaBase.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;

public abstract class DualListBox<T extends Object> extends Hbox{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4327294545568033482L;
	
	private Listbox lbDisponiveis = new Listbox();
	private Listbox lbSelecionados = new Listbox();
	Vbox botoes = new Vbox();
	private Button btSelecionaTodos = new Button();
	private Button btSelecionaUm = new Button();
	private Button btRemoveTodos = new Button();
	private Button btRemoveUm = new Button();
	private List<T> disponiveis;
	private List<T> selecionados;
	int rows;
	
	public void setRows (int row){
		rows = row;
	}
	
	public DualListBox (){
		carregaInterface();
	}
	
	@Override
	public void setWidth(String string){
		string = string.replaceAll("px", "");
		int tamanho = Integer.valueOf(string);
		lbDisponiveis.setWidth(((int)(tamanho * 0.45)) + "px");
		lbSelecionados.setWidth(((int)(tamanho * 0.45)) + "px");
		botoes.setWidth(((int)(tamanho * 0.1)) + "px");
	}
	
	@Override
	public void setHeight(String string){
		lbDisponiveis.setHeight(string);
		lbSelecionados.setHeight(string);
		botoes.setHeight(string);
	}
	
	public void setCheckmark(boolean pode){
		lbSelecionados.setCheckmark(pode);
		lbDisponiveis.setCheckmark(pode);
	}
	
	public abstract Listhead defineCabecalho();
	
	public abstract Listitem criaItem(T objeto);
	
	public abstract List<T> listaDisponiveis();
	
	public abstract List<T> listaSelecionados();
	
	@SuppressWarnings("unchecked")
	public void carregaInterface(){
		Vbox candidatos = new Vbox();
		candidatos.setParent(this);
		new Label("Disponíveis:").setParent(candidatos);
		lbDisponiveis.setParent(candidatos);
		lbDisponiveis.setHeight("300px");
		lbDisponiveis.setRows(10);
		lbDisponiveis.setMultiple(true);
		lbDisponiveis.appendChild(defineCabecalho());
		
		botoes.setSpacing("15px");
		botoes.setAlign("center");
		botoes.setPack("center");
		botoes.setParent(this);
		
		btSelecionaTodos.setParent(botoes);
		btSelecionaTodos.setLabel(">>");
		btSelecionaTodos.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				List<Listitem> itens = new ArrayList<Listitem>();
				itens.addAll(lbDisponiveis.getItems());
				for (Listitem listitem : itens) {
					listitem.setParent(lbSelecionados);
				}				
			}
		});
		
		btSelecionaUm.setParent(botoes);
		btSelecionaUm.setLabel(">");
		btSelecionaUm.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				List<Listitem> itens = new ArrayList<Listitem>();
				itens.addAll(lbDisponiveis.getSelectedItems());
				for (Listitem listitem : itens) {
					listitem.setParent(lbSelecionados);
				}				
			}
		});
		
		btRemoveUm.setParent(botoes);
		btRemoveUm.setLabel("<");
		btRemoveUm.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				List<Listitem> itens = new ArrayList<Listitem>();
				itens.addAll(lbSelecionados.getSelectedItems());
				for (Listitem listitem : itens) {
					listitem.setParent(lbDisponiveis);
				}				
			}
		});
		
		btRemoveTodos.setParent(botoes);
		btRemoveTodos.setLabel("<<");
		btRemoveTodos.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				List<Listitem> itens = new ArrayList<Listitem>();
				itens.addAll(lbSelecionados.getItems());
				for (Listitem listitem : itens) {
					listitem.setParent(lbDisponiveis);
				}				
			}
		});
		
		Vbox selecionados = new Vbox();
		selecionados.setParent(this);
		new Label("Selecionados:").setParent(selecionados);
		lbSelecionados.setParent(selecionados);
		lbSelecionados.setHeight("300px");
		lbSelecionados.setMultiple(true);
		lbSelecionados.appendChild(defineCabecalho());
		
		preencheListbox();
	}

	public void preencheListbox(){
		disponiveis = listaDisponiveis();
		selecionados = listaSelecionados();
		
		lbDisponiveis.getChildren().clear();
		lbDisponiveis.appendChild(defineCabecalho());
		
		lbSelecionados.getChildren().clear();
		lbSelecionados.appendChild(defineCabecalho());
		
		for (T objeto : disponiveis) {
			Listitem item = criaItem(objeto);
			if (selecionados.contains(objeto)){
				item.setParent(lbSelecionados);
			}else{
				item.setParent(lbDisponiveis);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void removeObjeto (T objeto){
		List<Listitem> itens = lbDisponiveis.getItems();
		for (Listitem listitem : itens) {
			if (objeto.equals(listitem.getValue())){
				lbDisponiveis.removeChild(listitem);
				return;
			}
		}
		itens = lbSelecionados.getItems();
		for (Listitem listitem : itens) {
			if (objeto.equals(listitem.getValue())){
				lbSelecionados.removeChild(listitem);
				return;
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<T> getSelecionados(){
		Collection<Listitem> selecionados = lbSelecionados.getItems();
		Set<T> selec = new HashSet();
		for (Listitem item : selecionados){
			selec.add((T) item.getValue());
		}
		return selec;
	}
}

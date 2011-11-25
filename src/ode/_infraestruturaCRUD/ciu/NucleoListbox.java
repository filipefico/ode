package ode._infraestruturaCRUD.ciu;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode._infraestruturaBase.ciu.NucleoColecao;

import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;

@SuppressWarnings("unchecked")
public class NucleoListbox<T> extends Listbox implements NucleoColecao<T> {

	private static final long serialVersionUID = 1L;
	
	private Listhead listhead;
	private Listheader listheader;

	public void setHeader(String str) {
		if (listhead == null) {
			listhead = new Listhead();
			listhead.setParent(this);
		}
		if (listheader == null) {
			listheader = new Listheader();
			listheader.setParent(listhead);
		}
		listheader.setLabel(str);
	}

	@Override
	public void setObjetoSelecionado(T objeto) {
		List<Listitem> listItems = this.getItems();
		for (Listitem item : listItems) {
			if (objeto == null && item.getValue() == null || item.getValue() != null && item.getValue().equals(objeto)) {
				this.selectItem(item);
				return;
			}
		}
	}

	@Override
	public T getObjetoSelecionado() {
		return this.getSelectedItem() != null ? (T) this.getSelectedItem().getValue() : null;
	}

	@Override
	public Listitem getItem(T objeto) {
		List<Listitem> listItems = this.getItems();
		for (Listitem item : listItems)
			if (item.getValue().equals(objeto))
				return item;
		return null;
	}

	public void setObjetosSelecionados(Collection<T> conjunto) {
		List<Listitem> listItems = this.getItems();
		for (Listitem item : listItems) {
			boolean selecionado = conjunto.contains((T) item.getValue()); 
			item.setSelected(selecionado);
		}
	}

	public Set<T> getObjetosSelecionados() {
		Set<T> conjunto = new HashSet<T>();

		List<Listitem> listItems = this.getItems();
		for (Listitem item : listItems)
			if (item.isSelected())
				conjunto.add((T) item.getValue());
		return conjunto;
	}
	
	public Set<T> getObjetosNaoSelecionados() {
		Set<T> conjunto = new HashSet<T>();

		List<Listitem> listItems = this.getItems();
		for (Listitem item : listItems)
			if (!item.isSelected())
				conjunto.add((T) item.getValue());
		return conjunto;
	}

	@Override
	public void addObjeto(T objeto) {
		Listitem listitem = new Listitem(objeto.toString(), objeto);
		this.appendChild(listitem);
	}
	
	@Override
	public void addObjetos(Iterable<T> conjunto) {
		for (T objeto : conjunto) {
			addObjeto(objeto);
		}
	}
	
	@Override
	public void addObjetos(T[] conjunto) {
		addObjetos(Arrays.asList(conjunto));
	}

	@Override
	public void setObjetos(Iterable<T> conjunto) {
		this.getItems().clear();
		addObjetos(conjunto);
	}
	
	@Override
	public void setObjetos(T[] conjunto) {
		setObjetos(Arrays.asList(conjunto));
	}

	@Override
	public void selecionarPrimeiroElemento() {
		this.setSelectedIndex(0);
	}

	public void setPrimeiroItem(String str) {
		Listitem listitem = new Listitem(str, null);
		this.appendChild(listitem);	
	}

}

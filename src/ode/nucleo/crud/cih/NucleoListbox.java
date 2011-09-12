package ode.nucleo.crud.cih;

import java.util.*;

import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

@SuppressWarnings("unchecked")
public class NucleoListbox<T> extends Listbox {

	private static final long serialVersionUID = 1L;

	public void setObjetoSelecionado(T objeto) {

		List<Listitem> listItems = this.getItems();
		for (Listitem item : listItems) {
			if (item.getValue().equals(objeto)) {
				this.selectItem(item);
				return;
			}
		}
	}

	public T getObjetoSelecionado() {
		return (T) this.getSelectedItem().getValue();
	}

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
			item.setSelected(false);
			if (conjunto.contains((T)item.getValue()))
				item.setSelected(true);
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
	
	public void addObjeto(T objeto) {
		Listitem listitem = new Listitem(objeto.toString(), objeto);
		this.appendChild(listitem);
	}
	
	public void setObjetos(Iterable<T> conjunto) {
		for(T objeto : conjunto) {
			addObjeto(objeto);
		}
	}
	
	public void selecionarPrimeiroElemento() {
		this.setSelectedIndex(0);
	}

}

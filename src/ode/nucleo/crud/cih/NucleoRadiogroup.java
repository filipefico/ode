package ode.nucleo.crud.cih;

import java.util.*;

import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Radio;

@SuppressWarnings("unchecked")
public class NucleoRadiogroup<T> extends Radiogroup implements NucleoColecao<T> {

	private static final long serialVersionUID = 1L;
	
	private static final String ATTR = "obj";
	
	public static final String ORIENT_VERTICAL = "vertical";
	public static final String ORIENT_HORIZONTAL = "horizontal";
	
	@Override
	public void setObjetoSelecionado(T objeto) {
		List<Radio> listItems = this.getItems();
		for (Radio item : listItems) {
			if (item.getAttribute(ATTR).equals(objeto)) {
				this.setSelectedItem(item);
				return;
			}
		}
	}

	@Override
	public T getObjetoSelecionado() {
		return this.getSelectedItem() != null ? (T)this.getSelectedItem().getAttribute(ATTR) : null;
	}

	@Override
	public Radio getItem(T objeto) {
		List<Radio> listItems = this.getItems();
		for (Radio item : listItems)
			if (item.getAttribute(ATTR).equals(objeto))
				return item;
		return null;
	}

	@Override
	public void addObjeto(T objeto) {
		Radio item = new Radio(objeto.toString());
		item.setAttribute(ATTR, objeto);
		this.appendChild(item);
	}

	@Override
	public void setObjetos(Iterable<T> conjunto) {
		for (T objeto : conjunto) {
			addObjeto(objeto);
		}
	}
	
	@Override
	public void setObjetos(T[] conjunto) {
		setObjetos(Arrays.asList(conjunto));
	}

	@Override
	public void selecionarPrimeiroElemento() {
		this.setSelectedIndex(0);
	}

}

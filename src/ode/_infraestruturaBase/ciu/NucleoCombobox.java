package ode._infraestruturaBase.ciu;

import java.util.Arrays;
import java.util.List;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoColecao;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

public class NucleoCombobox<T extends ObjetoPersistente> extends Combobox implements NucleoColecao<T>{

	private static final long serialVersionUID = 1L;

	@Override
	public void setObjetoSelecionado(T objeto) {
		this.setSelectedItem(((Comboitem)this.getItem(objeto)));
	}

	@Override
	public T getObjetoSelecionado() {
		return (T) this.getSelectedItem().getValue();
	}

	@Override
	public Object getItem(T objeto) {
		List<Comboitem> list = this.getItems();
		for (Comboitem item : list) {
			if (item.getValue().equals(objeto)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public void addObjeto(T objeto) {
		Comboitem novo = new Comboitem(objeto.toString());
		novo.setValue(objeto);
		this.appendChild(novo);
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
		if(this.getItemCount()!=0){
			this.setSelectedIndex(0);
		}
	}

	public void adicionaCampoNulo() {
		Comboitem novo = new Comboitem(" -- ");
		novo.setValue(null);
		this.appendChild(novo);
	}

	@Override
	public void addObjetos(Iterable<T> conjunto) {
		java.util.Iterator<T> i = conjunto.iterator();
		while (i.hasNext()){
			addObjeto(i.next());
		}
	}
	
	@Override
	public void addObjetos(T[] conjunto) {
		addObjetos(Arrays.asList(conjunto));
	}

}

package ode._infraestruturaBase.ciu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javassist.bytecode.Descriptor.Iterator;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaCRUD.ciu.NucleoColecao;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listitem;

import com.lowagie.text.ListItem;

public class NucleoCombobox<T extends ObjetoPersistente> extends Combobox implements NucleoColecao<T>{

	
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
		java.util.Iterator<T> i = conjunto.iterator();
		while (i.hasNext()){
			addObjeto(i.next());
		}
	}

	@Override
	public void setObjetos(T[] conjunto) {
		for(int i=0;i<conjunto.length;i++){
			addObjeto(conjunto[i]);
		}
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

}

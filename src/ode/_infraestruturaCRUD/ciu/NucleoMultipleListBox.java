package ode._infraestruturaCRUD.ciu;

import java.util.List;
import java.util.Set;

import org.zkoss.zul.Listitem;


public class NucleoMultipleListBox<T> extends NucleoListbox<T>{

	public NucleoMultipleListBox(){
		super();
		this.setCheckmark(true);
		this.setMultiple(true);
	}

	public void setCheckable(boolean b) {
		for(Listitem item:(List<Listitem>)this.getItems()){
			item.setDisabled(!b);
		}
		
	}
	
}

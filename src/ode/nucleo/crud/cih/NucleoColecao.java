package ode.nucleo.crud.cih;

import org.zkoss.zul.impl.api.XulElement;

public interface NucleoColecao<T> extends XulElement {
	
	public void setObjetoSelecionado(T objeto);

	public T getObjetoSelecionado();

	public Object getItem(T objeto);

	public void addObjeto(T objeto);

	public void setObjetos(Iterable<T> conjunto);
	
	public void setObjetos(T[] conjunto);
	
	public void selecionarPrimeiroElemento();

}

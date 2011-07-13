package ode.nucleo.cgd;

import java.util.Collection;

public class ResultadoPaginado<T extends Object> implements IObjetoTransferencia{
	
	Collection<T> listaObjetos;
	
	int tamanhoTotal;

	public Collection<T> getListaObjetos() {
		return listaObjetos;
	}

	public void setListaObjetos(Collection<T> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}

	public int getTamanhoTotal() {
		return tamanhoTotal;
	}

	public void setTamanhoTotal(int tamanhoTotal) {
		this.tamanhoTotal = tamanhoTotal;
	}

}

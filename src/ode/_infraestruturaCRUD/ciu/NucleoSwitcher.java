package ode._infraestruturaCRUD.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.ciu.NucleoListbox;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Vbox;

public class NucleoSwitcher<T> extends Hbox{

	private NucleoListbox<T> listEsquerda;
	private NucleoListbox<T> listDireita;
	
	private Vbox boxEsquerda;
	private Vbox boxDireita;
	
	private Button esq = new Button("<");
	private Button dir = new Button(">");
	
	protected void montarEsquerda(){
		
		listEsquerda = new NucleoListbox<T>();

		boxEsquerda.appendChild(listEsquerda);
		
		this.appendChild(boxEsquerda);
	}
	
	protected void montarDireita(){
		
		listDireita = new NucleoListbox<T>();
		
		boxDireita.appendChild(listDireita);
		
		this.appendChild(boxDireita);
	}
	
	protected void montarCentro(){
		Vbox botoesCentro = new Vbox();
		botoesCentro.appendChild(esq);
		esq.addEventListener("onClick", new esqButtonPress());
		botoesCentro.appendChild(dir);
		dir.addEventListener("onClick", new dirButtonPress());
		botoesCentro.setParent(this);
	}
	
	public void montar(String n1, String n2){
		for(Component child:(Collection<Component>)this.getChildren()){
			this.removeChild(child);
		}
		boxEsquerda = new Vbox();
		boxEsquerda.appendChild(new Label(n1));
		montarEsquerda();
		montarCentro();
		boxDireita = new Vbox();
		boxDireita.appendChild(new Label(n2));
		montarDireita();
	}
	
	public void montar(){
		for(Component child:(Collection<Component>)this.getChildren()){
			this.removeChild(child);
		}
		boxEsquerda = new Vbox();
		montarEsquerda();
		montarCentro();
		boxDireita = new Vbox();
		montarDireita();
	}
	
	private class esqButtonPress implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
				addLeftIten(removeRightIten(listDireita.getObjetoSelecionado()));
		}
	}

	private class dirButtonPress implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
				addRightIten(removeLeftIten(listEsquerda.getObjetoSelecionado()));
		}
	}
	
	public void setLeftItens(Collection<T> objetos) {
		listEsquerda.setObjetos(objetos);
	}

	public void setRightItens(Collection<T> objetos) {
		listDireita.setObjetos(objetos);
	}
	
	public void addLeftIten(T objeto){
		listEsquerda.addObjeto(objeto);
	}
	
	public void addRightIten(T objeto){
		listDireita.addObjeto(objeto);
	}
	
	public T removeLeftIten(T objeto){
		return listEsquerda.removerObjeto(objeto);
	}
	
	public T removeRightIten(T objeto){
		return listDireita.removerObjeto(objeto);
	}
	
	public Collection<T> getLeftItens(){
		return listEsquerda.getObjetos();
	}
	
	public Collection<T> getRightItens(){
		return listDireita.getObjetos();
	}
	
}

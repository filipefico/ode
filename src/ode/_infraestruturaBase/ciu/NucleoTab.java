package ode._infraestruturaBase.ciu;

import java.io.Serializable;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.impl.XulElement;

public class NucleoTab implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tab tab = new Tab();
	
	private Tabpanel tabpanel = new Tabpanel();
	
	public NucleoTab() {
	}
	
	public NucleoTab(String nome) {
		setNomeTab(nome);
	}
	
	@SuppressWarnings("unchecked")
	public List<Component> getConteudo() {
		return (List<Component>)this.tabpanel.getChildren();
	}
	
	public void addElemento(Component elemento) {
		this.tabpanel.appendChild(elemento);
	}
	
	public XulElement getConteudoTab(){
		return (XulElement) this.tabpanel.getChildren().get(0);
	}

	public void setConteudoTab(Component elemento) {
		this.tabpanel.getChildren().clear();
		addElemento(elemento);
	}

	public String getNomeTab() {
		return tab.getLabel();
	}

	public void setNomeTab(String nomeTab) {
		this.tab.setLabel(nomeTab);
	}	

	public Tab getTab() {
		return tab;
	}
	public void setTab(Tab tab) {
		this.tab = tab;
	}

	public Tabpanel getTabpanel() {
		return tabpanel;
	}
	public void setTabpanel(Tabpanel tabpanel) {
		this.tabpanel = tabpanel;
	}

}

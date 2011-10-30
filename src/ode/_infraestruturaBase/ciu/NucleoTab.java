package ode._infraestruturaBase.ciu;

import java.io.Serializable;
import java.util.List;

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
	public List<XulElement> getConteudo() {
		return (List<XulElement>)this.tabpanel.getChildren();
	}
	
	public void addElemento(XulElement elemento) {
		elemento.setParent(this.tabpanel);
	}

	public void setConteudoTab(XulElement elemento) {
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

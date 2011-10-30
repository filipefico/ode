package ode._infraestruturaBase.ciu;

import java.util.List;

import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

public class NucleoTabbox extends Tabbox {

	protected Tabs tabs;
	protected Tabpanels tabpanels;

	private static final long serialVersionUID = 1L;

	public NucleoTabbox() {
		super();
		tabs = new Tabs();
		tabpanels = new Tabpanels();
		tabs.setParent(this);
		tabpanels.setParent(this);
	}

	public NucleoTabbox(List<NucleoTab> nucleoTabs) {
		this();
		this.setTabs(nucleoTabs);
	}

	public void addTab(NucleoTab nucleoTab) {
		nucleoTab.getTab().setParent(tabs);
		nucleoTab.getTabpanel().setParent(tabpanels);
	}

	public void setTabs(List<NucleoTab> nucleoTabs) {
		for (NucleoTab nucleoTab : nucleoTabs) {
			addTab(nucleoTab);
		}
	}

}

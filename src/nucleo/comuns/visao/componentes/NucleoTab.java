package nucleo.comuns.visao.componentes;

import org.zkoss.zul.impl.XulElement;

public class NucleoTab {

	// Nome a ser exibido na tab
	String nomeTab;

	// Conteúdo da tab
	XulElement conteudoTab;

	public XulElement getConteudoTab() {
		return conteudoTab;
	}

	public void setConteudoTab(XulElement conteudoTab) {
		this.conteudoTab = conteudoTab;
	}

	public String getNomeTab() {
		return nomeTab;
	}

	public void setNomeTab(String nomeTab) {
		this.nomeTab = nomeTab;
	}

}

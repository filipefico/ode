package ode._infraestruturaCRUD.ciu;

import ode._infraestruturaBase.util.NucleoContexto;

import org.zkoss.zul.Window;

public class JanelaSimples extends Window {
	private static final long serialVersionUID = -5859724805082908994L;
	
	/** Tamanho da window. */
	protected String WIDTH_WINDOW = "550px";

	public JanelaSimples(){
		super();
		configurarPropriedadesPadrao();
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
	}

	
	protected void configurarPropriedadesPadrao() {
		// configuracoes default da classe window
		this.setSclass("embedded");
		this.setContentStyle("background:white");
		this.setTitle(getTituloWindow());
		this.setBorder("normal");
		this.setClosable(true);
		this.setWidth(WIDTH_WINDOW);
		this.setPosition("&quot;center;&quot;;");
		this.setZIndex(10);
		this.setSizable(true);
		this.setMaximizable(true);
	}

	
	protected String getTituloWindow() {		
		return getTitle();
	}

	
	public void mostrar() {		
		doOverlapped();						
	}

}

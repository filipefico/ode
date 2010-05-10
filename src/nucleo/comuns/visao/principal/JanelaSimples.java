package nucleo.comuns.visao.principal;

import org.zkoss.zul.Window;

import nucleo.comuns.visao.NucleoWindow;

public class JanelaSimples extends Window{
	
	/** Tamanho da window. */
	protected String WIDTH_WINDOW = "450px";

	
	
	public JanelaSimples(){
		 configurarPropriedadesPadrao();
		
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
		setSizable(true);
		setMaximizable(true);
		
	}

	
	protected String getTituloWindow() {		
		return getTitle();
	}

	
	public void mostrar() {		
		doOverlapped();						
	}

}

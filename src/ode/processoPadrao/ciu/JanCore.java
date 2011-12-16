package ode.processoPadrao.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;

public class JanCore extends JanelaSimples {
	protected CtrlDefinirProcessoPadrao ctrl;

	public JanCore(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {
		super();
		ctrl = ctrlDefinirProcessoPadrao;
		configuracaoBasica();
	}

	protected void configuracaoBasica() {
		this.setTitle("Título provisório");
		this.setWidth("450px");
		this.setHeight("600px");
		this.setBorder("normal");
		this.setClosable(true);
		this.setPosition("&quot;center;&quot;;");
		this.setSizable(true);
		this.setMaximizable(true);
	}

}

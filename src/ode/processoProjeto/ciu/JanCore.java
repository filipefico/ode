package ode.processoProjeto.ciu;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Set;

import org.zkoss.zul.Hbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

import ode._infraestruturaCRUD.ciu.JanelaSimples;

public class JanCore extends JanelaSimples implements Serializable {
	private static final long serialVersionUID = 3374943399161265500L;
	protected CtrlDefinirProcessoProjeto ctrl;

	public JanCore(CtrlDefinirProcessoProjeto ctrl) {
		super();
		this.ctrl = ctrl;
		configuracaoBasica();
	}

	protected void configuracaoBasica() {
		this.setTitle("Título Provisório");
		this.setWidth("450px");
		this.setBorder("normal");
		this.setClosable(true);
		this.setPosition("&quot;center;&quot;;");
		this.setSizable(true);
		this.setMaximizable(true);
	}

}
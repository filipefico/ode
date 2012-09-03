package ode.controleCaracteristica.ciu;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zkplus.spring.SpringUtil;

import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.JanelaSimples;

public class JanelaCore extends JanelaSimples implements Serializable {
	private static final long serialVersionUID = 3374943399161265500L;
	
	protected CtrlPossivelValorNaoOrdenado ctrlP = SpringUtil.getApplicationContext().getBean(CtrlPossivelValorNaoOrdenado.class);
	

	public JanelaCore(CtrlPossivelValorNaoOrdenado ctrlP) {
		super();
		this.ctrlP = ctrlP;
		configuracaoBasica();
	}
	
	protected void configuracaoBasica() {
		this.setTitle(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME));
		this.setWidth("480px");
		this.setBorder("normal");
		this.setClosable(true);
		this.setPosition("&quot;center;&quot;;");
		this.setSizable(true);
		this.setMaximizable(true);
	}

}
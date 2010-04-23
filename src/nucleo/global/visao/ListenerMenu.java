package nucleo.global.visao;

import nucleo.comuns.visao.paginacao.JanelaSimples;

import ode.exemplo.visao.PainelCrudPessoa;

import org.hibernate.id.IdentityGenerator.GetGeneratedKeysDelegate;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Deferrable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

/**
 * @author Bruno Nandolpho
 * */
public class ListenerMenu implements EventListener {
	
	public String CAMINHO
	="/visao/principal/janelaSimples.zul";

	private Component pai;
	
	private Component filhoJanela;
	

	public ListenerMenu(Component pai, Component filhoJanela) {
		this.pai = pai;
		this.filhoJanela = filhoJanela;
	}

	public void onEvent(Event event) {
		JanelaSimples win = (JanelaSimples) Executions.createComponents(CAMINHO, pai, null);		
		filhoJanela.setParent(win);
		win.mostrar();
	}

	public boolean isAsap() {
		return true;
	}
/*
	/**
	 * Método que dispara apenas o evento para o cliente e não par ao servidor
	
	public boolean isDeferrable() {
		return true;
	}
	 * */
	}

package nucleo.global.visao;

import nucleo.comuns.visao.paginacao.JanelaSimples;

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
	
	private String caminho="/visao/principal/janelaSimples.zul";

	private Component pai;
	
	private Component filhoJanela;
	

	public ListenerMenu(Component pai, Component filhoJanela) {
		this.pai = pai;
		this.filhoJanela = filhoJanela;
	}

	public void onEvent(Event event) {
		JanelaSimples win = (JanelaSimples) Executions.createComponents(caminho, pai, null);
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
	/***/
	public static EventListener factory(Component paiJanela, Component filhoJanela) {		
		return new ListenerMenu(paiJanela, filhoJanela);

	}
}

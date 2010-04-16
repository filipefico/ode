package nucleo.global.visao;

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

	private Component pai;
	private String caminho;

	public ListenerMenu(Component pai, String caminho) {
		this.pai = pai;
		this.caminho = caminho;
	}

	public void onEvent(Event event) {
		Window win = (Window) Executions.createComponents(caminho, pai, null);
		win.doOverlapped();
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
	public static EventListener factory(Component pai, String caminho) {		
		return new ListenerMenu(pai, caminho);

	}
}

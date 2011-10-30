package ode._infraestruturaBase.ciu;


import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.JanelaSimples;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public abstract class CtrlBase extends Window {

	private static final long serialVersionUID = 1L;

	public JanelaSimples factoryJanelaSimples() {
		return new JanelaSimples();
	}

	public void confirmaSimNao(String mensagemConfirmacao,EventListener eventListenerConfirma)
	throws InterruptedException {
		Messagebox.show(mensagemConfirmacao, NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CONFIRMACAO), Messagebox.YES
				| Messagebox.NO, Messagebox.QUESTION,eventListenerConfirma) ;

	}


	public void mostrarJanelaInformacao(String mensagem)
	throws InterruptedException {

		Messagebox.show(mensagem, NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_INFORMACAO), Messagebox.OK,
				Messagebox.INFORMATION);

	}

	public void mostrarJanelaAviso(String mensagem) throws InterruptedException {

		Messagebox.show(mensagem, NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
				Messagebox.INFORMATION);

	}
	
	public abstract void iniciar();

}

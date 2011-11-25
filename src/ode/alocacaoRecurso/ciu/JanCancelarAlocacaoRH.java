package ode.alocacaoRecurso.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.alocacaoRecurso.cdp.AlocacaoRH;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanCancelarAlocacaoRH extends JanelaSimples {

	private static final long serialVersionUID = 1L;
	
	public JanCancelarAlocacaoRH(final CtrlAlocacaoRecurso ctrl) {
		super();
		
		final AlocacaoRH alocacaoRH = ctrl.getAlocacaoSelecionada();
		
		this.setTitle("Cancelar Alocação?");
		this.setHflex("min");
		
		Vbox vbox = new Vbox();
		vbox.setParent(this);
		
		Label labelMotivo = new Label("Motivo:");
		labelMotivo.setParent(vbox);
		
		final Textbox txtMotivo = new Textbox();
		txtMotivo.setParent(vbox);
		txtMotivo.setWidth("300px");
		
		Label labelConfirma = new Label("Deseja mesmo cancelar a alocação?"); 
		labelConfirma.setParent(vbox);
		
		Hbox hbox = new Hbox();
		hbox.setParent(vbox);
		
		Button botaoOK = new Button("Sim");
		botaoOK.addEventListener("onClick", new EventListener() {
			public void onEvent(Event e) throws Exception {
				ctrl.cancelarAlocacaoRH(alocacaoRH, txtMotivo.getValue());
				onClose();
			}
		});
		botaoOK.setParent(hbox);
		
		Button botaoCancelar = new Button("Não");
		botaoCancelar.addEventListener("onClick", new EventListener() {
			public void onEvent(Event e) throws Exception {
				onClose();
			}
		});
		botaoCancelar.setParent(hbox);
	}

	public void mostrar() {
		try {
			doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

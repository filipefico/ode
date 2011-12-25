package ode.alocacaoRecurso.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

public class JanCancelarAlocacaoRH extends JanelaSimples {

	private static final long serialVersionUID = 1L;
	
	public JanCancelarAlocacaoRH(final CtrlAlocacaoRecurso ctrl) {
		super();
		
		this.setTitle("Cancelar Alocação");
		this.setHflex("min");
		
		Vlayout vbox = new Vlayout();
		vbox.setParent(this);
		vbox.setStyle("padding: 3px 15px");
		
		Label labelMotivo = new Label("Motivo:");
		labelMotivo.setParent(vbox);
		
		final Textbox txtMotivo = new Textbox();
		txtMotivo.setParent(vbox);
		txtMotivo.setRows(2);
		txtMotivo.setWidth("200px");
		
		Label labelConfirma = new Label("Deseja mesmo cancelar a alocação?"); 
		labelConfirma.setParent(vbox);
		
		Hbox hbox = new Hbox();
		hbox.setHflex("1");
		hbox.setParent(vbox);
		hbox.setPack("center");
		
		Button botaoOK = new Button("Sim");
		botaoOK.setZclass("z-messagebox-btn");
		botaoOK.addEventListener("onClick", new EventListener() {
			public void onEvent(Event e) throws Exception {
				ctrl.cancelarAlocacaoRH(txtMotivo.getValue());
				onClose();
			}
		});
		botaoOK.setParent(hbox);
		
		Button botaoCancelar = new Button("Não");
		botaoCancelar.setZclass("z-messagebox-btn");
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

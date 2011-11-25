package ode.alocacaoRecurso.ciu;

import java.text.DateFormat;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.alocacaoRecurso.cdp.CancelamentoAlocacaoRH;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vbox;

public class JanAnularCancelamentoAlocacaoRH extends JanelaSimples {

	private static final long serialVersionUID = 1L;
	
	public JanAnularCancelamentoAlocacaoRH(final CtrlAlocacaoRecurso ctrl, final CancelamentoAlocacaoRH cancelamentoAlocacaoRH) {
		super();
		
		this.setTitle("Anular Cancelamento de Alocação");
		this.setHflex("min");
		
		Vbox vbox = new Vbox();
		vbox.setParent(this);
		
		String motivo = cancelamentoAlocacaoRH.getMotivo();
		Label label;
		String data = DateFormat.getDateInstance(DateFormat.SHORT).format(cancelamentoAlocacaoRH.getData());
		if(motivo.isEmpty())
			label = new Label("Alocação cancelada em " + data + " sem motivo informado.");
		else
			label = new Label("Alocação cancelada em " + data + " pelo seguinte motivo:\r\n" + motivo.replace("\n", "\r\n"));
		label.setParent(vbox);
		label.setWidth("300px");
		
		Label label2 = new Label("Deseja mesmo anular o cancelamento da alocação?");
		label2.setParent(vbox);
		
		Hbox hbox = new Hbox();
		hbox.setParent(vbox);
		hbox.setAlign("center");
		
		Button botaoOK = new Button("Sim");
		botaoOK.addEventListener("onClick", new EventListener() {
			public void onEvent(Event e) throws Exception {
				ctrl.anularCancelamentoAlocacaoRH(cancelamentoAlocacaoRH.getAlocacaoRH());
				onClose();
			}
		});
		botaoOK.setZclass("z-messagebox-btn");
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
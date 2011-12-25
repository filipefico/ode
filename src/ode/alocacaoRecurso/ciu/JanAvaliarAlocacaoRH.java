package ode.alocacaoRecurso.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;

import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vlayout;

public class JanAvaliarAlocacaoRH extends JanelaSimples {

	private static final long serialVersionUID = 1L;

	public JanAvaliarAlocacaoRH(final CtrlAlocacaoRecurso ctrl) {
		super();
		
		final AlocacaoRH alocacaoRH = ctrl.getAlocacaoSelecionada(); 
		
		this.setTitle("Avaliar Participacao de Recurso Humano");
		this.setHflex("min");
		
		Vlayout vbox = new Vlayout();
		vbox.setParent(this);
		vbox.setStyle("padding: 3px 15px");
		
		Label labelMotivo = new Label("Selecione o novo estado da alocação:");
		labelMotivo.setParent(vbox);
		
		final NucleoListbox<EstadoAlocacaoRH> listEstados = new NucleoListbox<EstadoAlocacaoRH>();
		listEstados.setObjetos(new EstadoAlocacaoRH[] { EstadoAlocacaoRH.Encerrada, EstadoAlocacaoRH.EmAndamentoAjustes } );
		
		listEstados.setParent(vbox);
		listEstados.setWidth("300px");
		
		Hbox hbox = new Hbox();
		hbox.setHflex("1");
		hbox.setPack("center");
		hbox.setParent(vbox);
		
		Button botaoOK = new Button("OK");
		botaoOK.setZclass("z-messagebox-btn");
		botaoOK.addEventListener("onClick", new EventListener() {
			public void onEvent(Event e) throws Exception {
				if(listEstados.getObjetoSelecionado()==null) {
					throw new WrongValueException(listEstados, "Favor informar o Estado desejado para a alocação!");
				} else {
					ctrl.avaliarAlocacaoRH(alocacaoRH, listEstados.getObjetoSelecionado());
					onClose();
				}
			}
		});
		botaoOK.setParent(hbox);
		
		Button botaoCancelar = new Button("Cancelar");
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

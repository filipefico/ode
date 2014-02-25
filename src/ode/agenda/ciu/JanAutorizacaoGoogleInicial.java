package ode.agenda.ciu;

import ode._infraestruturaBase.util.NucleoContexto;

import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;


public class JanAutorizacaoGoogleInicial extends Window {

	private static final long serialVersionUID = 1L;
	
	private CtrlAgenda ctrlAgenda;
	
	public JanAutorizacaoGoogleInicial(CtrlAgenda ctrl) {
		super();
		this.ctrlAgenda = ctrl;
		
		this.setTitle("Autorização Google");
		this.setWidth("400px");
		this.setPosition("&quot;center;&quot;;");
		this.setClosable(true);
		this.setBorder("normal");
		
		try {
			this.setMode("overlapped");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
		
		Vlayout vlayout = new Vlayout();
		vlayout.setStyle("margin: 10px");
		
			Label informacao = new Label("Você ainda não autorizou a Agenda do ODE sincronizar com a Agenda do Google. Você deseja fazer isso agora?");
			informacao.setParent(vlayout);
			
			Hlayout hlayout = new Hlayout();
			hlayout.setStyle("text-align: center; margin-top: 10px;");
			
				Button btAutorizar = new Button("Autorizar");
				btAutorizar.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						ctrlAgenda.autenticacaoInicial();
					}
				});
				
				btAutorizar.setParent(hlayout);
				
				Button btAgoraNao = new Button("Agora Não");
				btAgoraNao.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						onClose();
						ctrlAgenda.iniciar();
					}
				});
				
				btAgoraNao.setParent(hlayout);
				
				
				this.addEventListener("onClose", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						ctrlAgenda.iniciar();
					}
				});
				
			
			hlayout.setParent(vlayout);		
		vlayout.setParent(this);		
		
	}

	public void mostrar() {
		try {
			this.doModal();
			
		} catch (SuspendNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

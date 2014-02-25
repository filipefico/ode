package ode.agenda.ciu;

import ode._infraestruturaBase.util.NucleoContexto;
import ode.agenda.cdp.Owner;
import ode.agenda.cgt.AplControlarOwner;
import ode.middlewareGoogle.calendar.AplCalendarServiceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

public class JanAutorizacaoGoogleFinal extends Window {

	private static final long serialVersionUID = 1L;
	private CtrlAgenda ctrlAgenda;

	
	public JanAutorizacaoGoogleFinal(CtrlAgenda ctrl) {
		super();
		
		this.ctrlAgenda = ctrl;
		this.setTitle("Autorização Google Final");
		this.setWidth("400px");
		this.setPosition("&quot;center;&quot;;");
		this.setClosable(true);
		this.setBorder("normal");
		
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
		
		Vlayout vlayout = new Vlayout();
		vlayout.setStyle("margin: 10px; text-align: center;");
		
			Label mensagem = new Label("Aguarde enquanto a autorização está terminando.");
			mensagem.setParent(vlayout);
			
			Hlayout hlayout = new Hlayout();
			hlayout.setStyle("text-align: center; margin-top: 10px;");
			
				boolean autorizacao = ctrlAgenda.autenticacaoFinal();
				
				if(autorizacao) {
										
					mensagem.setValue("Autorização concluida com sucesso.");
					Button btConcluir = new Button("Concluir");
					btConcluir.addEventListener("onClick", new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							ctrlAgenda.redirecionarCallBack();
						}
					});
					btConcluir.setParent(hlayout);
					
				}else{
					mensagem.setValue("Não foi possivel terminar a autenticação agora, por favor tente novamente.");
					
					Button btNovamente = new Button("Tentar Novamente");
					btNovamente.addEventListener("onClick", new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							onClose();
							ctrlAgenda.redirecionarCallBack();
						}
					});
					btNovamente.setParent(hlayout);
					
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
					
				}
			
			hlayout.setParent(vlayout);
		
		vlayout.setParent(this);
	}
	
	public void mostrar(){
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

package ode.conhecimentoMedicao.cci;

import java.util.EventListener;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Messagebox;

public class SimNaoAlterarHelper<T extends CtrlCRUD>{
	public SimNaoAlterarHelper(T este, String termo, String relacionado, final org.zkoss.zk.ui.event.EventListener myEvent){
		try {
			Messagebox
					.show("Existem "+relacionado+"(s) relacionadas, tem certeza que deseja alterar?",NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_ALTERAR)
							+ termo, Messagebox.YES | Messagebox.NO,
							Messagebox.QUESTION,
							new EventListenerParaControlador(este){

								@Override
								public void onEvent(Event e)
										throws Exception {
									switch ((Integer) e.getData()) {
									case Messagebox.YES:
										if(/*usuario==adminstrador*/false){
											Messagebox.show(NucleoMensagens.getMensagem(NucleoMensagens.MSG_SOMENTE_ADMINISTRADOR));
											return;
										}
										myEvent.onEvent(null);
										break;
									case Messagebox.NO:

										break;
									default:

										break;
									}
								}
					});
			}catch (Exception e) {
		}
	}
}

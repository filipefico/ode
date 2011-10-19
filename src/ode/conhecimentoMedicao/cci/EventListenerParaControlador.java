package ode.conhecimentoMedicao.cci;


import ode._infraestruturaBase.ciu.CtrlBase;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public abstract class EventListenerParaControlador implements EventListener{

	private CtrlBase ctrl;
	
	public EventListenerParaControlador(CtrlBase control){
		super();
		this.ctrl = control;
	}

	public CtrlBase getCtrl() {
		return ctrl;
	}

}

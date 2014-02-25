package ode.agenda.ciu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.agenda.cdp.Contact;
import ode.agenda.cgt.AplControlarContatos;
import ode.agenda.cgt.AplControlarOwner;

@Controller
public class CtrlContatos extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	private JanContatos jc;
	
	@Autowired
	AplControlarContatos aplControlarContatos;
	
	@Autowired
	AplControlarOwner aplControlarOwner;

	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		
		//recuper a lista de contatos
		List<Contact> lista = this.recuperarContatos();
		
		jc = new JanContatos(this, lista);
		jc.mostrar();
	}
	
	List<Contact> recuperarContatos(){
		return aplControlarContatos.recuperarContatos(aplControlarOwner.recuperarOwnerAtual());
	}

}

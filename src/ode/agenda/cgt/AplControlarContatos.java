package ode.agenda.cgt;

import java.util.ArrayList;
import java.util.List;

import ode.agenda.cdp.Contact;
import ode.agenda.cdp.Owner;
import ode.middlewareGoogle.contacts.AplContatctsServiceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplControlarContatos {

	@Autowired
	AplContatctsServiceManager aplContatctsServiceManager;
	
	public List<Contact> recuperarContatos(Owner owner){
				/*List<Contact> lista = new ArrayList<Contact>();
				Contact c = new Contact();
				c.setNome("Filipe Cardoso");
				//c.setEmail("filipefico@gmail.com");
				lista.add(c);
		
				c = new Contact();
				c.setNome("Teste");
				lista.add(c);*/
		return aplContatctsServiceManager.recuperarContatos(owner);
	}
}

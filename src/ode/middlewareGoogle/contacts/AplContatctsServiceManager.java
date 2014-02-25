package ode.middlewareGoogle.contacts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ode.agenda.cdp.Contact;
import ode.agenda.cdp.Owner;
import ode.middlewareGoogle.autenticacao.AplCredenciais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.util.ServiceException;



@Service
public class AplContatctsServiceManager {
	
	@Autowired
	private AplCredenciais credenciais;
	
	
	
	
	private ContactsService criaServio(Owner owner){
		
		GoogleCredential googleCredential = credenciais.recuperaGoogleCredential(owner);
		try {
			googleCredential.refreshToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ContactsService servico = new ContactsService(credenciais.getAPPLICATION_NAME());
		
		servico.setHeader("GData-Version", "3.0");
		servico.setOAuth2Credentials(googleCredential);
		return servico;		
	}
	
	private URL getFeedUrl(){
		URL url;
		try {
			url = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			return url;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Contact construirContato(ContactEntry entry){
		
		Contact contato = new Contact();
		contato.setId(entry.getId());
		if(entry.hasName()){
			Name name = entry.getName();
			if(name.hasFullName()){
				contato.setNome(name.getFullName().getValue());
				if(name.getFullName().hasYomi()) {
					contato.setNome(contato.getNome() + " (" + name.getFullName().getYomi() + ") ");
				}
			}else{
				contato.setNome("Sem nome completo");
			}
		}
		
		if(entry.hasEmailAddresses()) {
			List<String> listaEmail = new ArrayList<String>();
			for(Email email : entry.getEmailAddresses()){
				listaEmail.add(email.getAddress());
			}
			contato.setEmail(listaEmail);
		}
		
		if(entry.hasPhoneNumbers()){
			List<String> listaTel = new ArrayList<String>();
			for(PhoneNumber numero : entry.getPhoneNumbers()){
				listaTel.add(numero.getPhoneNumber());
			}
			contato.setTelefone(listaTel);
		}
		
		/*
		    for (Email email : entry.getEmailAddresses()) {
		      System.out.print(" " + email.getAddress());
		      if (email.getRel() != null) {
		        System.out.print(" rel:" + email.getRel());
		      }
		      if (email.getLabel() != null) {
		        System.out.print(" label:" + email.getLabel());
		      }
		      if (email.getPrimary()) {
		        System.out.print(" (primary) ");
		      }
		      System.out.print("\n");
		    }
		 */
		
		return contato;
	}
	
	
	public List<Contact> recuperarContatos(Owner owner){
		
		GoogleService servico = criaServio(owner);
		List<Contact> lista = new ArrayList<Contact>();
		
		try {
			Query query = new Query(getFeedUrl());
			query.setStringCustomParameter("sortorder", "ascending");
			
			//ContactFeed listaContatos = servico.getFeed(getFeedUrl(), ContactFeed.class);
			ContactFeed listaContatos = servico.query(query, ContactFeed.class);
			
			for(ContactEntry entry : listaContatos.getEntries()){
				
				lista.add(construirContato(entry));
				//System.out.println("Nome: " + name.getFullName().getValue());
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;		
	}

}


/**
private Calendar criaServico(Owner owner){
		
		GoogleCredential googleCredenciais = credenciais.recuperaGoogleCredential(owner);
				
		Calendar servico = new Calendar
						.Builder(credenciais.getTRANSPORT(), credenciais.getJSON_FACTORY(), googleCredenciais)
						.setApplicationName(credenciais.getAPPLICATION_NAME())
						.build();
				
		return servico;
	}
*/

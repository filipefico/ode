package ode.middlewareGoogle.autenticacao;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ode.agenda.cdp.Owner;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Executions;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

@Service
@Scope(value =  "singleton")
public class AplCredenciais {
	
	//private static final AplCredenciais INSTANCE = new AplCredenciais();
	
	private	final String CLIENT_ID = "516592061408-mtvc5n91ck7nvr5jdbcr2643d5u6rnrk.apps.googleusercontent.com";
	private final String CLIENT_SECRET = "LAj2QyphVI3CG_42JdTQqT0Y";
	private final List<String>  SCOPE = Arrays.asList("https://www.googleapis.com/auth/calendar", "https://www.google.com/m8/feeds");
	private final String CALLBACK_URL = "http://localhost:8080/ode";
	
	private final HttpTransport TRANSPORT = new NetHttpTransport();
	//private final JsonFactory JSON_FACTORY = new JacksonFactory();
	//private final JsonFactory JSON_FACTORY = new JacksonFactory();
	private final JsonFactory JSON_FACTORY = new JacksonFactory();
	public final String APPLICATION_NAME = "ODE";
	
	/*public AplCredenciais getInstance() {
		return INSTANCE;
	}*/
	
	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	/*public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}*/

	public String getCLIENT_SECRET() {
		return CLIENT_SECRET;
	}

	public List<String> getSCOPE() {
		return SCOPE;
	}

	public HttpTransport getTRANSPORT() {
		return TRANSPORT;
	}

	public JsonFactory getJSON_FACTORY() {
		return JSON_FACTORY;
	}

	public String getAPPLICATION_NAME() {
		return APPLICATION_NAME;
	}

	public String getCALLBACK_URL() {
		return CALLBACK_URL;
	}

	public String recuperarUrlAutorizacaoGoogle(){
		String urlAutorizacao = new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, CALLBACK_URL, SCOPE).setAccessType("offline").setApprovalPrompt("force").build();
		return urlAutorizacao;
	}
	
	private GoogleTokenResponse recuperarTokenAutorizacao(String code) {

		GoogleTokenResponse resposta = null;
		try {
			resposta = new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, code, CALLBACK_URL).execute();
			return resposta;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resposta;
	}
	
	public void autenticacaoInicial(){
		
		String urlAutorizacao = recuperarUrlAutorizacaoGoogle();
		Executions.sendRedirect(urlAutorizacao);		
	}
	
	public GoogleTokenResponse autenticacaoFinal(){
		
		String code = Executions.getCurrent().getParameter("code");
		
		GoogleTokenResponse resposta = recuperarTokenAutorizacao(code);
		
		return resposta;
		
	}
	
	public GoogleCredential recuperaGoogleCredential (Owner owner){
		/*GoogleCredential credential = new GoogleCredential();
			credential.setAccessToken(owner.getAccessToken());
			credential.setRefreshToken(owner.getRefreshToken());*/
		GoogleCredential credential = new GoogleCredential.Builder()
							.setTransport(TRANSPORT)
							.setJsonFactory(JSON_FACTORY)
							.setClientSecrets(CLIENT_ID, CLIENT_SECRET)
							.build();
			credential.setAccessToken(owner.getAccessToken());
			credential.setRefreshToken(owner.getRefreshToken());
		return credential;
	}

}



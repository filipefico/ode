package ode.agenda.cgt;

import javax.swing.Spring;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.agenda.cdp.Owner;
import ode.agenda.cgd.OwnerDAO;
import ode.middlewareGoogle.autenticacao.AplCredenciais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

@Service
public class AplControlarOwner extends AplCRUD<Owner> {

	@Autowired
	OwnerDAO ownerDAO;
	
	@Autowired
	AplCredenciais credenciais;	
		
	@Override
	public DAOBase<Owner> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return ownerDAO;
	}
	
	//Verifica se ja autorizou a aplicacao do google
	public boolean verificaAutorizacaoGoogleRHAtual(){
		
		Owner owner = ownerDAO.recuperarOwnerDoRHAtual();
		if(owner != null) {
			return true;
		}else{
			return false;
		}
	}
	
	public Owner recuperarOwnerAtual(){
		Owner owner = ownerDAO.recuperarOwnerDoRHAtual();
		return owner;
	}
	
	public Owner recuperarOwnerDoRH(RecursoHumano rh){
		
		Owner owner = ownerDAO.recuperarOwnerDoRH(rh);
		return owner;
	}
	
	public void autenticacaoInicial(){
		credenciais.autenticacaoInicial();
	}
	
	public boolean autenticacaoFinal(){
		
		GoogleTokenResponse resposta = credenciais.autenticacaoFinal();
		
		if(resposta != null){
			
			Owner owner = new Owner();
			owner.setrecursoHumano(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
			owner.setAccessToken(resposta.getAccessToken());
			owner.setRefreshToken(resposta.getRefreshToken());
			
			try {
				this.salvar(owner);				
				
				return true;				
			} catch (NucleoRegraNegocioExcecao e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}else{
			return false;
		}
	}
	
	public void redirecionaCallBack(){
		String callBack = credenciais.getCALLBACK_URL();
		Executions.sendRedirect(callBack);
	}

}

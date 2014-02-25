package ode.middlewareIssueTracker.mantis;

import java.util.Observable;
import java.util.Observer;

import ode.controleProjeto.cdp.Projeto;
import ode.observador.cdp.ProdutorProjeto;
import ode.observador.cdp.ProdutorProjeto.TipoProjeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value =  "singleton")
public class AplConsumidorMiddlewareIssueTracker implements Observer {

	@Autowired
	AplIssueTrackerManager aplIssueTrackerManager;
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(o instanceof ProdutorProjeto){
			
			ProdutorProjeto produtorProjeto = (ProdutorProjeto) o;
			
			if(produtorProjeto.getTipo() ==  TipoProjeto.CRIAR){
				Projeto projeto = produtorProjeto.getProjeto();
								
				aplIssueTrackerManager.insereProjetoMantis(projeto);
				return;
			}
			
			if(produtorProjeto.getTipo() == TipoProjeto.EXCLUIR) {
				Projeto projeto = produtorProjeto.getProjeto();
								
				aplIssueTrackerManager.excluirProjetoMantis(projeto);
				return;
			}
			
		}
		
	}

}

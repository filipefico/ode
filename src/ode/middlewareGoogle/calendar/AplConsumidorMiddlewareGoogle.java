package ode.middlewareGoogle.calendar;

import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ode.agenda.cdp.Owner;
import ode.agenda.cgt.AplControlarOwner;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.observador.cdp.ProdutorAlocaODE;
import ode.observador.cdp.ProdutorAlocaODE.TipoAlocacao;

@Service
@Scope(value =  "singleton")
public class AplConsumidorMiddlewareGoogle implements Observer {

	/*public static AplConsumidorMiddlewareGoogle instance = null;	
	

	public static AplConsumidorMiddlewareGoogle getInstace(){
		if(instance == null) {
			instance = new AplConsumidorMiddlewareGoogle();
		}
		return instance;
	}*/
	
	@Autowired
	AplCalendarServiceManager aplCalendarServiceManager;
	
	//AplControlarOwner aplControlarOwner = new AplControlarOwner();
	
	@Autowired
	AplControlarOwner aplControlarOwner;
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(o instanceof ProdutorAlocaODE) {
			
			ProdutorAlocaODE alocaODE = (ProdutorAlocaODE) o;
			
			if(alocaODE.getTipo() == TipoAlocacao.CRIAR){
				AlocacaoRH alocacaoRH = alocaODE.getAlocacaoRH();
				
				if(alocacaoRH.getDtInicioPrevisto() != null && alocacaoRH.getDtFimPrevisto() != null){
					Owner owner = aplControlarOwner.recuperarOwnerDoRH(alocacaoRH.getRecursoHumano());
					
					if(owner != null) {
						aplCalendarServiceManager.inserirEvent(owner, alocacaoRH, alocaODE.getNomeProjeto());
					}
				}
				return;
			}
			
			//usar SWITCH
			if(alocaODE.getTipo() == TipoAlocacao.ATUALIZAR) {
				AlocacaoRH alocacaoRH = alocaODE.getAlocacaoRH();

				Owner owner = aplControlarOwner.recuperarOwnerDoRH(alocacaoRH.getRecursoHumano());
				
				if(owner != null) {
					aplCalendarServiceManager.inserirEvent(owner, alocacaoRH, alocaODE.getNomeProjeto());
				}
				return;
			}
			
			//se owner == null, entao o recurso humano ainda nao altorizou o uso da agenda
			//Owner owner = aplControlarOwner.recuperarOwnerDoRH(alocacaoRH.getRecursoHumano());
			
			//System.out.println("\n\nAlocacao: " + alocaODE.getAlocacaoRH().getId() + " | " + alocaODE.getAlocacaoRH().getAtividade().getNome());
			//System.out.println("\nTipo Alocacao: " + alocaODE.getTipo().getDescricao());
			//aplCalendarServiceManager.construirEvent(alocacaoRH);
		}
	}

}

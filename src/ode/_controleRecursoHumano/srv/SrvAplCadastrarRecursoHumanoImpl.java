package ode._controleRecursoHumano.srv;

import java.util.Collection;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@WebService(endpointInterface = "ode._controleRecursoHumano.srv.SrvAplCadastrarRecursoHumano")  
public class SrvAplCadastrarRecursoHumanoImpl implements SrvAplCadastrarRecursoHumano {

	@Autowired
	private AplCadastrarRecursoHumano apl;
	
	@Override
	public Collection<RecursoHumano> recuperarTodos() {
		return apl.recuperarRecursosHumanosAtivos();
	}

	@Override
	public RecursoHumano recuperarPorId(long id) {
		return apl.recuperarPorId(id);
	}
	
	@Override
	public void excluir(RecursoHumano rh){
		try {
			apl.excluir(rh);
		} catch (NucleoRegraNegocioExcecao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Collection<RecursoHumano> recuperarPorCargo(KRecursoHumano kRH){
		return apl.recuperarPorCargo(kRH);
	}
	
	@Override
	public int recuperarQuantidadeTotal(){
		return apl.recuperarQuantidadeTotal();
	}
	
	@Override
	public Collection<RecursoHumano> recuperarRecursosHumanosAtivos(){
		return apl.recuperarRecursosHumanosAtivos();
	}
	
	@Override
	public void salvar(RecursoHumano rh){
		try {
			apl.salvar(rh);
		} catch (NucleoRegraNegocioExcecao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

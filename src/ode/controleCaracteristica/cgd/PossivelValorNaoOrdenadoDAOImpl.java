package ode.controleCaracteristica.cgd;

import org.springframework.stereotype.Repository;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;

@Repository
public class PossivelValorNaoOrdenadoDAOImpl extends
DAOBaseImpl<PossivelValorNaoOrdenado> implements PossivelValorNaoOrdenadoDAO{


	/*public Collection<PossivelValorNaoOrdenado> recuperarTodosPorIDCaracteristica(long id){
		
		return entityManager.createQuery(
				"from possivelvalor p JOIN caracteristica_possivelvalor c WHERE c.caracteristica_id = "+ id, getClasseDominio()).getResultList();
	
	}*/
}
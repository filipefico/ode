package ode.agenda.cgd;

import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.agenda.cdp.Owner;

import org.springframework.stereotype.Repository;

@Repository
public class OwnerDAOImpl extends DAOBaseImpl<Owner> implements OwnerDAO {

	@Override
	public Owner recuperarOwnerDoRHAtual() {
		// TODO Auto-generated method stub
				
		RecursoHumano recursoHumano = NucleoContexto.recuperarUsuarioLogado().getRecursoHumano();
		
		List<Owner> owners = getEntityManager().createQuery("from Owner where recursoHumano = :recursoHumano").setParameter("recursoHumano", recursoHumano).getResultList();
		
		if(owners.size() > 0) {
			return owners.get(0);
		}
		
		return null;
	}

	@Override
	public Owner recuperarOwnerDoRH(RecursoHumano rh) {
		// TODO Auto-generated method stub
		System.out.println("\nBanco: " + rh.getNome() + " " + rh.getId());
		List<Owner> owners = getEntityManager().createQuery("from Owner where recursoHumano = :recursoHumano").setParameter("recursoHumano", rh).getResultList();
		
		if(owners.size() > 0) {
			return owners.get(0);
		}
		
		return null;
	}
	

}
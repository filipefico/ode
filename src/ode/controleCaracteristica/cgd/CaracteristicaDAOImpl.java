package ode.controleCaracteristica.cgd;

import java.util.Collection;
import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.EnumTipoItemSoftware;
import ode.controleCaracteristica.cdp.Importancia;
import ode.controleCaracteristica.cgd.CaracteristicaDAO;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

import org.springframework.stereotype.Repository;

@Repository
public class CaracteristicaDAOImpl extends
			DAOBaseImpl<Caracteristica> implements CaracteristicaDAO {

	@SuppressWarnings("unchecked")
	public Collection<Caracteristica> recuperarCaracteristicaPorTipoSoftware(EnumTipoItemSoftware tipo) {
		return entityManager.createQuery(
				"from Caracteristica c WHERE " + tipo + " in elements (c.aplicabilidades)").getResultList();
	}

	public boolean estaNumaPespectivaAnalise(long id){
		
		Collection<Importancia> importancia = entityManager.createQuery(
				"from Importancia imp WHERE imp.caracteristica.id = :id", Importancia.class).setParameter("id", id).getResultList();
		
		if(importancia.isEmpty()){
			return false;
		}else{
			return true;	
		}
		
		
	}
	
}
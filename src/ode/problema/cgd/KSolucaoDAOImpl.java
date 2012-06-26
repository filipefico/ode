package ode.problema.cgd;

import java.util.Collection;
import java.util.List;

import ode._infraestruturaCRUD.ciu.NucleoListbox;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import ode.problema.cdp.KSolucao;

import org.springframework.stereotype.Repository;


@Repository
public class KSolucaoDAOImpl extends DAOBaseImpl<KSolucao> implements KSolucaoDAO {

	
	@Override
	public Collection<KSolucao> recuperarSolucaoPorCausa(KCausa kcausa) {
		Long idKCausa = kcausa.getId();
		@SuppressWarnings("unchecked")
		Collection<KSolucao> lista1 = entityManager.createQuery("from KSolucao solucao left outer join fetch solucao.KCausa as kp where kp.id=" +idKCausa+" order by solucao.nome").getResultList();
		
		return lista1;
		
	
		
			
	
	}
	


}

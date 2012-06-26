
    
package ode.problema.cgd;
import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import org.springframework.stereotype.Repository;




@Repository
public class KCausaDAOImpl extends DAOBaseImpl<KCausa> implements KCausaDAO{
	

	
	@Override
	public Collection<KCausa> recuperarCausaPorProblema(KProblema kproblema) {
		Long idKProblema = kproblema.getId();
		System.out.println(idKProblema);
        @SuppressWarnings("unchecked")
		Collection<KCausa> lista = entityManager.createQuery("from KCausa causa left outer join fetch causa.kproblema as kp where kp.id=" +idKProblema+" order by causa.nome").getResultList();
		
		return lista;
		
		
		
			
	
	}
	

	
	
}

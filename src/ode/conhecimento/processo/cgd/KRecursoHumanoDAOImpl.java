package ode.conhecimento.processo.cgd;

import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.springframework.stereotype.Repository;

@Repository
public class KRecursoHumanoDAOImpl extends DAOBaseImpl<KRecursoHumano> implements KRecursoHumanoDAO {

	public KRecursoHumano recuperarPorParteNome(String nome){
	
		// Adiciona % antes e depois de nome
		nome = ("%").concat(nome.concat(("%")));
		
		List<KRecursoHumano> kRecursos = entityManager.createQuery("from KRecursoHumano where upper(nome) like upper(:nome)")
		.setParameter("nome", nome)
		.getResultList();
		
		if (kRecursos.size() > 0)
			return kRecursos.get(0);
		
		return null;
	}
		
	
	
}

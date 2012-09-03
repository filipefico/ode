package ode.controleCaracteristica.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleCaracteristica.cdp.Importancia;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImportanciaDAOImpl extends
DAOBaseImpl<Importancia> implements ImportanciaDAO {

	@Override
	public void salvar(Importancia objeto) {
		super.salvar(objeto);
	}

	@Override
	public void excluir(Importancia objeto) {
		// TODO Auto-generated method stub
		super.excluir(objeto);
	}

	@Override
	public Importancia atualizar(Importancia objeto) {
		// TODO Auto-generated method stub
		return super.atualizar(objeto);
	}
	
	public Collection<Importancia> estaNumaImportancia(long idCaracteristica){
		return entityManager.createQuery(
			"from Importancia imp WHERE imp.caracteristica.id = :idCaracteristica", Importancia.class).setParameter("idCaracteristica", idCaracteristica).getResultList();
	}
	

}
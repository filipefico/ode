package ode.gerenciaRequisitos.cgd;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.gerenciaRequisitos.cdp.Prioridade;

@Repository(value="prioridadeDao")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PrioridadeDAOImpl extends DAOBaseImpl<Prioridade> implements PrioridadeDAO{
	@Override
	public void salvar(Prioridade objeto) {
		super.salvar(objeto);
		getEntityManager().flush();
	}

}

package ode.processoProjeto.cgd;

import java.util.Collection;
import java.util.List;

import javax.management.RuntimeErrorException;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.processoProjeto.cdp.CompPPr;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompPPrDAOImpl extends DAOBaseImpl<CompPPr> implements CompPPrDAO {

	public CompPPr obterPorNome(String parNome) {

		List locProc = getEntityManager().createQuery(
				"from " + CompPPr.class.getName() + " as proc "
				+ " where proc.nome = '" + parNome + "'")
				.getResultList();

		if (locProc.isEmpty())
			return null;
		return (CompPPr) locProc.get(0);
	}
	
	@Override
	public Collection recuperarTodosFinalizados(Class compPPr) {

			return getEntityManager().createQuery(
					"from " + compPPr.getName() + " as comp "
							+ " where comp.definicaoConcluida = true")
					.getResultList();
	}
	
}
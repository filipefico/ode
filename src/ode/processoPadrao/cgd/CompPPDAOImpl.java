package ode.processoPadrao.cgd;

import java.util.Collection;
import java.util.List;

import javax.management.RuntimeErrorException;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompPPDAOImpl extends DAOBaseImpl<CompPP> implements CompPPDAO {

	public CompPP obterPorNome(String parNome) {

		List locProc = getEntityManager().createQuery(
				"from " + CompPPProcessoComplexo.class.getName() + " as proc "
				+ " where proc.nome = '" + parNome + "'")
				.getResultList();

		if (locProc.isEmpty())
			return null;
		return (CompPP) locProc.get(0);
	}

	
	@Override
	public Collection recuperarTodosFinalizados(Class compPP) {

		if (compPP.equals(CompPPProcessoComplexo.class)
				|| compPP.equals(CompPPProcessoSimples.class)
				|| compPP.equals(CompPPMacroatividade.class)) {

			return getEntityManager().createQuery(
					"from " + compPP.getName() + " as comp "
							+ " where comp.definicaoConcluida = true")
					.getResultList();

		} else {
			throw new RuntimeErrorException(
					null,
					"Tipo incompativel. Por favor decida entre CompPP Complexo, simples ou macroatividade.");
		}
	}
}

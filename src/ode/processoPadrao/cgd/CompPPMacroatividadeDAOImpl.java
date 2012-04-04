package ode.processoPadrao.cgd;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

import org.springframework.stereotype.Repository;

@Repository
public class CompPPMacroatividadeDAOImpl extends
		DAOBaseImpl<CompPPMacroatividade> implements CompPPMacroatividadeDAO {

	public boolean podeExcluir(CompPPMacroatividade macro) {
		String query = "from " + CompPPProcessoSimples.class.getName();
		query += " comp left join comp.macroAtividades macro ";
		query += "where macro.id = " + macro.getId();
		System.out.println(query);
		if (getEntityManager().createQuery(query).getResultList().size() > 0) {
			return false;
		}

		String query2 = "from " + CompPPMacroatividade.class.getName();
		query2 += " macro left join macro.compPPBase base ";
		query2 += "where base.id = " + macro.getId();
		if (getEntityManager().createQuery(query2).getResultList().size() > 0) {
			return false;
		}

		return true;
	}
}

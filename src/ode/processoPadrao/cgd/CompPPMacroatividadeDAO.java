package ode.processoPadrao.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.processoPadrao.cdp.CompPPMacroatividade;

public interface CompPPMacroatividadeDAO extends DAOBase<CompPPMacroatividade> {

	// public CompPPMacroatividade obterPorNome(String parNome);

	// public List obterMacroAtividades(CompPPProcessoSimples parPPE);

	// public CompPPMacroatividade obterPorId(CompPP parCompPP);
	public boolean podeExcluir(CompPPMacroatividade macro);
}

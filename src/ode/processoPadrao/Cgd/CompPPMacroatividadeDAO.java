package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.CompPPMacroatividade;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface CompPPMacroatividadeDAO extends NucleoDAOBase<CompPPMacroatividade>{

	   public void salvar(CompPPMacroatividade compPPMacroatividade);

	    public void excluir(CompPPMacroatividade compPPMacroatividade);

	    public Collection<CompPPMacroatividade> recuperarTodos();

	    //public CompPPMacroatividade obterPorNome(String parNome);

	    //public List obterMacroAtividades(CompPPProcessoSimples parPPE);

	    //public CompPPMacroatividade obterPorId(CompPP parCompPP);
}

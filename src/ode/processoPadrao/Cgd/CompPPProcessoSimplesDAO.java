package ode.processoPadrao.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.processoPadrao.Cdp.CompPPProcessoSimples; 
import ode.processoPadrao.Cdp.CompPP;

public interface CompPPProcessoSimplesDAO extends NucleoDAOBase<CompPPProcessoSimples>{
		
		public void salvar(CompPPProcessoSimples parProcessoPadraoEspecifico);

	    public void excluir(CompPPProcessoSimples parProcessoPadraoEspecifico);

	    public Collection<CompPPProcessoSimples> recuperarTodos();
	 
	    //   public List obterProcessosEngenharia(CompPPProcessoComplexo parProc);

	 //   public List obterProcessosNaoEngenhaira(CompPPProcessoComplexo parProc);

	 //   public List obterKProcessosDefinidos(CompPPProcessoComplexo parPPG);

	    //public CompPPProcessoSimplesDAO obterPorNome(String parNome);

	    //public CompPPProcessoSimplesDAO obterPorId(CompPP parCompPP);
}

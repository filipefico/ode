package ode.processoPadrao.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.processoPadrao.Cdp.CompPPProcessoSimples; 
import ode.processoPadrao.Cdp.CompPP;

public interface CompPPProcessoSimplesDAO extends NucleoDAOBase<CompPPProcessoSimples>{
		
		public void salvar(CompPPProcessoSimplesDAO parProcessoPadraoEspecifico);

	    public void excluir(CompPPProcessoSimplesDAO parProcessoPadraoEspecifico);

	 //   public List obterTodos();

	    //   public List obterProcessosEngenharia(CompPPProcessoComplexo parProc);

	 //   public List obterProcessosNaoEngenhaira(CompPPProcessoComplexo parProc);

	 //   public List obterKProcessosDefinidos(CompPPProcessoComplexo parPPG);

	    //public CompPPProcessoSimplesDAO obterPorNome(String parNome);

	    //public CompPPProcessoSimplesDAO obterPorId(CompPP parCompPP);
}

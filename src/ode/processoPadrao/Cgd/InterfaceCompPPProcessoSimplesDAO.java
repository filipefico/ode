package ode.processoPadrao.Cgd;
import ode.processoPadrao.Cdp.InterfaceCompPP;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoSimples;

public interface InterfaceCompPPProcessoSimplesDAO {
	
		public void salvar(InterfaceCompPPProcessoSimples parInterfaceCompPPProcessoSimples);

	    public void excluir(InterfaceCompPPProcessoSimples parInterfaceCompPPProcessoSimples);

	  //  public List obterTodos();

	   // public InterfaceCompPPProcessoSimples obterPorId(InterfaceCompPP parInterfaceCompPP);
}

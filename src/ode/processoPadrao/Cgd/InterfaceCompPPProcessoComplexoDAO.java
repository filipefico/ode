package ode.processoPadrao.Cgd;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoSimples;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoComplexo;
import ode.processoPadrao.Cdp.InterfaceCompPP;

public interface InterfaceCompPPProcessoComplexoDAO {
	 	public void salvar(InterfaceCompPPProcessoComplexo parProcessoPadrao);

	    public void excluir(InterfaceCompPPProcessoComplexo parProcessoPadrao);

	  /*  public List obterTodos();

	    public InterfaceCompPPProcessoComplexo obterPorId(InterfaceCompPP parInterfaceCompPP);
	    */
}

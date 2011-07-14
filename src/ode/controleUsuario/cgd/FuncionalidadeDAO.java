package ode.controleUsuario.cgd;

import java.util.Collection;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.cgd.NucleoDAOBase;

public interface FuncionalidadeDAO extends
		NucleoDAOBase<Funcionalidade> {
	
	/**
	 * Recupera subfuncionalidades de uma funcionalidade.
	 * @param funcionalidadePai Funcionalidade a qual se deseja recuperar suas subfuncionalidades.
	 * @return Subfuncionalidades recuperadas.
	 */
	public Collection<Funcionalidade> recuperarSubFuncionalidadesPorFuncionalidade(Funcionalidade funcionalidadePai);

	/**
	 * Recupera funcionalidades raíz do sistema.
	 * @return Funcionalidades recuperadas.
	 */
	public Collection<Funcionalidade> recuperarFuncionalidadesRaiz();
}

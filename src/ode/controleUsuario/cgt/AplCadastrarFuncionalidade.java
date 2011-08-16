package ode.controleUsuario.cgt;

import java.util.Collection;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.crud.cgt.AplBase;

public interface AplCadastrarFuncionalidade extends
		AplBase<Funcionalidade> {
	
	/**
	 * Recupera subfuncionalidades de uma funcionalidade.
	 * @param funcionalidadePai Funcionalidade a qual se deseja recuperar suas subfuncionalidades.
	 * @return Subfuncionalidades recuperadas.
	 */
	public Collection<Funcionalidade> recuperarSubFuncionalidadesPorFuncionalidade(Funcionalidade funcionalidadePai);

	public Collection<Funcionalidade> recuperarFuncionalidadesRaiz();

}

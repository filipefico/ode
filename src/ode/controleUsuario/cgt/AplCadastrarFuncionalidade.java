package ode.controleUsuario.cgt;

import java.util.Collection;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.dao.DataAccessException;

public interface AplCadastrarFuncionalidade extends
		AplBase<Funcionalidade> {
	
	/**
	 * Recupera subfuncionalidades de uma funcionalidade.
	 * @param funcionalidadePai Funcionalidade a qual se deseja recuperar suas subfuncionalidades.
	 * @return Subfuncionalidades recuperadas.
	 */
	public Collection<Funcionalidade> recuperarSubFuncionalidadesPorFuncionalidade(Funcionalidade funcionalidadePai);

	public Funcionalidade salvar(Funcionalidade funcionalidadePai, Funcionalidade objeto) throws NucleoRegraNegocioExcecao, DataAccessException;

	public void excluir(Funcionalidade objeto) throws NucleoRegraNegocioExcecao, DataAccessException;

	public Collection<Funcionalidade> recuperarFuncionalidadesRaiz();

	public void salvarObjetoMovido(Object objetoPai,
			Long idObjetoPosicaoInicio, Long idObjetoPosicaoFim);

}

package ode.controleUsuario.cgt;

import java.util.Collection;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cgd.FuncionalidadeDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarFuncionalidade")
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarFuncionalidadeImpl extends
AplBaseImpl<Funcionalidade> implements AplCadastrarFuncionalidade {
	
	@Autowired
	private FuncionalidadeDAO funcionalidadeDAO;
	
	/**
	 * Recupera subfuncionalidades de uma funcionalidade.
	 * @param funcionalidadePai Funcionalidade a qual se deseja recuperar suas subfuncionalidades.
	 * @return Subfuncionalidades recuperadas.
	 */
	public Collection<Funcionalidade> recuperarSubFuncionalidadesPorFuncionalidade(Funcionalidade funcionalidadePai) {
		
		return funcionalidadeDAO.recuperarSubFuncionalidadesPorFuncionalidade(funcionalidadePai);
		
	}

	public Collection<Funcionalidade> recuperarFuncionalidadesRaiz() {
		return funcionalidadeDAO.recuperarFuncionalidadesRaiz();
	}

	@Override
	public DAOBase<Funcionalidade> getNucleoDaoBase() {
		return this.funcionalidadeDAO;
	}

}


package ode.controleUsuario.cgt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cgd.FuncionalidadeDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.principal.cdp.ConfiguracoesSistema;
import ode.principal.cgd.ConfiguracoesSistemaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarFuncionalidade")
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarFuncionalidadeImpl extends
NucleoAplCadastroBaseImpl<Funcionalidade> implements AplCadastrarFuncionalidade {
	
	@Autowired
	private FuncionalidadeDAO funcionalidadeDAO;
	
	@Autowired
	private ConfiguracoesSistemaDAO configuracoesSistemaDAO;
	
	/**
	 * Recupera subfuncionalidades de uma funcionalidade.
	 * @param funcionalidadePai Funcionalidade a qual se deseja recuperar suas subfuncionalidades.
	 * @return Subfuncionalidades recuperadas.
	 */
	public Collection<Funcionalidade> recuperarSubFuncionalidadesPorFuncionalidade(Funcionalidade funcionalidadePai) {
		
		return funcionalidadeDAO.recuperarSubFuncionalidadesPorFuncionalidade(funcionalidadePai);
		
	}

	public Funcionalidade salvar(Funcionalidade funcionalidadePai, Funcionalidade objeto) throws NucleoRegraNegocioExcecao, DataAccessException{

		objeto = salvar(objeto);
		
		// Verifica se existe objeto pai
		if (funcionalidadePai != null) {
			// Adiciona o objeto no objeto pai
			Funcionalidade objetoPersistente = recuperarPorId(funcionalidadePai.getId());
			
			if (!objetoPersistente.getSubfuncionalidades().contains(objeto))
				objetoPersistente.adicionarSubfuncionalidade(objeto);
		}else {
			// Adiciona o objeto nas configurações do sistema
			ConfiguracoesSistema configuracoesSistema = (ConfiguracoesSistema)configuracoesSistemaDAO.recuperarTodos().toArray()[0];
			
			if (!configuracoesSistema.getFuncionalidadesSistema().contains(objeto))
				configuracoesSistema.adicionarSubfuncionalidade(objeto);
		}

		return objeto;
	}

	public void excluir(Funcionalidade objeto) throws NucleoRegraNegocioExcecao, DataAccessException { 

		objeto = recuperarPorId(objeto.getId());

		// Verifica se o objeto ainda existe no banco. As vezes o objeto pai já foi deletado juntamente com seus objetos filhos.
		// Nesse caso, ao excluir o objeto filho, ele não existirá mais no banco.
		if (objeto == null )
			return;

		// Exclui o objeto
		if (objeto.getFuncionalidadePai() == null) {

			ConfiguracoesSistema configuracoesSistema = (ConfiguracoesSistema) configuracoesSistemaDAO.recuperarTodos().toArray()[0];

			configuracoesSistema.removerSubfuncionalidade(objeto);

		} else { 

			Funcionalidade funcionalidadePai = recuperarPorId(objeto.getFuncionalidadePai().getId());
			funcionalidadePai.removerSubfuncionalidade(objeto);

		}

		super.excluir(objeto);	

	}

	public Collection<Funcionalidade> recuperarFuncionalidadesRaiz() {
		return funcionalidadeDAO.recuperarFuncionalidadesRaiz();
	}

	public void salvarObjetoMovido(Object objetoPai,
			Long idObjetoPosicaoInicio, Long idObjetoPosicaoFim) {

		List<Funcionalidade> funcionalidades = null;
		Funcionalidade funcionalidadePai = null;
		ConfiguracoesSistema configuracoesSistema = null;

		if (objetoPai != null) {

			funcionalidadePai = (Funcionalidade) objetoPai;

			// Recupera a grupo pai das subgrupos movidas
			funcionalidadePai = super.recuperarPorId(funcionalidadePai.getId());

			// Recupera as posições dos objetos movidos
			funcionalidades = funcionalidadePai.getSubfuncionalidades();

		} else {

			// Recupera as configurações do sistema
			configuracoesSistema = (ConfiguracoesSistema) configuracoesSistemaDAO.recuperarTodos().toArray()[0];

			// Recupera as funcionalidades do sistema
			funcionalidades = configuracoesSistema.getFuncionalidadesSistema();

		}

		Funcionalidade funcionalidadePosicaoInicio = null;
		Funcionalidade funcionalidadePosicaoFim = null;

		// Pega posições das funcionalidades
		for (Funcionalidade funcionalidade : funcionalidades) {
			if (funcionalidade.getId().equals(idObjetoPosicaoInicio)) {
				funcionalidadePosicaoInicio = funcionalidade;
			}
			if (funcionalidade.getId().equals(idObjetoPosicaoFim)) {
				funcionalidadePosicaoFim = funcionalidade;
			}
		}

		// Cria uma nova lista, colocando os objetos na ordem correta
		List<Funcionalidade> funcionalidadesNova = new ArrayList<Funcionalidade>();
		for (Funcionalidade funcionalidade : funcionalidades) {
			if (funcionalidade.getId().equals(idObjetoPosicaoInicio)) {
				funcionalidadesNova.add(funcionalidadePosicaoFim);
			} else if (funcionalidade.getId().equals(idObjetoPosicaoFim)) {
				funcionalidadesNova.add(funcionalidadePosicaoInicio);
			} else {
				funcionalidadesNova.add(funcionalidade);
			}
		}

		if (objetoPai != null) {
			// Atribui as sub-áreas da área
			funcionalidadePai.setSubfuncionalidades(funcionalidadesNova);
		} else {
			// Atribui as funcionalidades do sistema
			configuracoesSistema.setFuncionalidadesSistema(funcionalidadesNova);
		}
	}

	@Override
	public NucleoDAOBase<Funcionalidade> getNucleoDaoBase() {
		return this.funcionalidadeDAO;
	}

}


package ode.controleProjeto.cgt;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarProjetoImpl extends
		NucleoAplCadastroBaseImpl<Projeto> implements AplCadastrarProjeto {

	@Autowired
	private ProjetoDAO nucleoOrganizacaoDAO;

	@Override
	protected void antesIncluirNovo(Projeto objeto)
			throws NucleoRegraNegocioExcecao {

		// Verifica se existe outro projeto com o mesmo nome
		if (((ProjetoDAO) this.getNucleoDaoBase()).recuperarPorNome(objeto
				.getNome()) != null) {
			throw new NucleoRegraNegocioExcecao("Já existe projeto com o nome informado.",
					null);
		}
	}

	@Override
	protected void antesAlterarDados(Projeto objeto)
			throws NucleoRegraNegocioExcecao {

		// Verifica se existe Organizacao com o mesmo e-mail
		Projeto nucleoOrganizacaoPersistido = ((ProjetoDAO) this
				.getNucleoDaoBase()).recuperarPorNome(objeto.getNome());
		if (nucleoOrganizacaoPersistido != null) {
			if (!nucleoOrganizacaoPersistido.getId().equals(objeto.getId())) {
				throw new NucleoRegraNegocioExcecao("Já existe projeto com o nome informado.",
						null);
			}
		}
	}

	public Collection<Projeto> recuperarTodos() {
		List<Projeto> Organizacao = (List<Projeto>) super.recuperarTodos();
		Collections.sort(Organizacao, new ComparaNucleoOrganizacao());
		return Organizacao;
	}

	private class ComparaNucleoOrganizacao implements Comparator<Projeto> {

		public int compare(Projeto objeto1, Projeto objeto2) {
			return objeto1.getNome().compareToIgnoreCase(objeto2.getNome());
		}
	}

	@Override
	public NucleoDAOBase<Projeto> getNucleoDaoBase() {
		return nucleoOrganizacaoDAO ;
	}
}

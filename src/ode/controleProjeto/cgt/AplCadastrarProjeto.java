package ode.controleProjeto.cgt;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._controleRecursoHumano.cgd.EquipeDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.observador.cdp.ProdutorProjeto;
import ode.observador.cdp.ProdutorProjeto.TipoProjeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarProjeto extends AplCRUD<Projeto> {

	@Autowired
	private ProjetoDAO nucleoOrganizacaoDAO;
	
	@Autowired
	private EquipeDAO equipeDAO;
	
	//Produtor que avisa a agenda que um evento envolvendo um projeto aconteceu
	private ProdutorProjeto produtorProjeto = ProdutorProjeto.getInstance();

	@Override
	protected void antesIncluirNovo(Projeto objeto) throws NucleoRegraNegocioExcecao {

		// Verifica se existe outro projeto com o mesmo nome
		if (nucleoOrganizacaoDAO.recuperarPorNome(objeto.getNome()) != null) {
			throw new NucleoRegraNegocioExcecao("Já existe projeto com o nome informado.", null);
		}
	}

	@Override
	protected void antesAlterarDados(Projeto objeto) throws NucleoRegraNegocioExcecao {

		// Verifica se existe Organizacao com o mesmo e-mail
		Projeto nucleoOrganizacaoPersistido = ((ProjetoDAO) this.getNucleoDaoBase()).recuperarPorNome(objeto.getNome());
		
		if (nucleoOrganizacaoPersistido != null) {
			if (!nucleoOrganizacaoPersistido.getId().equals(objeto.getId())) {
				throw new NucleoRegraNegocioExcecao("Já existe projeto com o nome informado.",
						null);
			}
		}
	}
	
	@Override
	protected void depoisIncluirNovo(Projeto objeto) {
		
		//Notifica
		produtorProjeto.setModify(objeto, TipoProjeto.CRIAR);
		
		Equipe equipe = new Equipe();
		equipe.setProjeto(objeto);
		equipeDAO.salvar(equipe);
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
	protected void antesExcluir(Projeto objeto) throws NucleoRegraNegocioExcecao {
		
		produtorProjeto.setModify(objeto, TipoProjeto.EXCLUIR);		
	} 
	

	@Override
	public DAOBase<Projeto> getNucleoDaoBase() {
		return nucleoOrganizacaoDAO ;
	}
}

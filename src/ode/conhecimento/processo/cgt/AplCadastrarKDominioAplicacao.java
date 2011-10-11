package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KDominioAplicacao;
import ode.conhecimento.processo.cgd.KDominioAplicacaoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKDominioAplicacao extends
		AplCRUD<KDominioAplicacao> {
	
	@Autowired
	private KDominioAplicacaoDAO KDominioAplicacaoDAO;

	public KDominioAplicacaoDAO getKDominioAplicacaoDAO() {
		return KDominioAplicacaoDAO;
	}

	public void setKDominioAplicacaoDAO(KDominioAplicacaoDAO KDominioAplicacaoDao) {
		this.KDominioAplicacaoDAO = KDominioAplicacaoDao;
	}
	
	protected KDominioAplicacao alterarDados(KDominioAplicacao objeto) throws NucleoRegraNegocioExcecao {
		// Executa a ação necessária antes de alterar os dados
//		antesAlterarDados(objeto);

		// Obtém o Principal Serviço persistido e altera seus dados
	//	KDominioAplicacao objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());
		
	//	copiarValor(objeto, objetoPersistido);

		// Executa a ação necessária depois de alterar os dados
//		depoisAlterarDados(objeto);
		
		// Inclui o Principal Serviço
	getNucleoDaoBase().atualizar(objeto);


		//return objetoPersistido;
		return objeto;
	}

	@Override
	public DAOBase<KDominioAplicacao> getNucleoDaoBase() {
		return KDominioAplicacaoDAO;
	}

}

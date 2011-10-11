package ode.conhecimento.organizacao.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.organizacao.cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.cgd.KDominioConhecimentoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKDominioConhecimento extends
AplCRUD<KDominioConhecimento> {


	@Autowired
	private KDominioConhecimentoDAO kDominioConhecimentoDAO;

	public KDominioConhecimentoDAO getKDominioConhecimentoDAO() {
		return kDominioConhecimentoDAO;
	}

	public void setKDominioConhecimentoDAO(KDominioConhecimentoDAO kDominioConhecimentoDAO) {
		this.kDominioConhecimentoDAO = kDominioConhecimentoDAO;
	}

	protected KDominioConhecimento alterarDados(KDominioConhecimento objeto) throws NucleoRegraNegocioExcecao {
		// Executa a ação necessária antes de alterar os dados
		//			antesAlterarDados(objeto);

		// Obtém o Principal Serviço persistido e altera seus dados
		//	PessoaExemplo objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());

		//	copiarValor(objeto, objetoPersistido);

		// Executa a ação necessária depois de alterar os dados
		//			depoisAlterarDados(objeto);

		// Inclui o Principal Serviço
		getNucleoDaoBase().atualizar(objeto);


		//return objetoPersistido;
		return objeto;
	}

	@Override
	public DAOBase<KDominioConhecimento> getNucleoDaoBase() {
		return kDominioConhecimentoDAO;
	}

}

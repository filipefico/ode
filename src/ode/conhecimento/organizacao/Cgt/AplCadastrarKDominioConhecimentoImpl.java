package ode.conhecimento.organizacao.Cgt;

import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.Cgd.KDominioConhecimentoDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKDominioConhecimentoImpl extends
AplBaseImpl<KDominioConhecimento> implements
AplCadastrarKDominioConhecimento {


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

package ode.conhecimento.organizacao.Cgt;

import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.Cgd.KDominioConhecimentoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKDominioConhecimentoImpl extends
NucleoAplCadastroBaseImpl<KDominioConhecimento> implements
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
		// Executa a a��o necess�ria antes de alterar os dados
		//			antesAlterarDados(objeto);

		// Obt�m o Principal Servi�o persistido e altera seus dados
		//	PessoaExemplo objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());

		//	copiarValor(objeto, objetoPersistido);

		// Executa a a��o necess�ria depois de alterar os dados
		//			depoisAlterarDados(objeto);

		// Inclui o Principal Servi�o
		getNucleoDaoBase().merge(objeto);


		//return objetoPersistido;
		return objeto;
	}

	@Override
	public NucleoDAOBase<KDominioConhecimento> getNucleoDaoBase() {
		return kDominioConhecimentoDAO;
	}

}

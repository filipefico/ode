package ode.conhecimento.processo.Cgt;

import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cgd.KDominioAplicacaoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKDominioAplicacaoImpl extends
		NucleoAplCadastroBaseImpl<KDominioAplicacao> implements
		AplCadastrarKDominioAplicacao {
	
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
	getNucleoDaoBase().merge(objeto);


		//return objetoPersistido;
		return objeto;
	}

	@Override
	public NucleoDAOBase<KDominioAplicacao> getNucleoDaoBase() {
		return KDominioAplicacaoDAO;
	}

}

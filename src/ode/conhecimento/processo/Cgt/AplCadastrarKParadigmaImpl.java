package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.Cdp.KParadigma;
import ode.conhecimento.processo.Cgd.KParadigmaDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKParadigmaImpl extends
		NucleoAplCadastroBaseImpl<KParadigma> implements AplCadastrarKParadigma {

	@Autowired
	private KParadigmaDAO KParadigmaDAO;

	public KParadigmaDAO getKParadigmaDAO() {
		return KParadigmaDAO;
	}

	public void setKDominioAplicacaoDAO(KParadigmaDAO KParadigmaDao) {
		this.KParadigmaDAO = KParadigmaDao;
	}
	
	protected KParadigma alterarDados(KParadigma objeto) throws NucleoRegraNegocioExcecao {
		// Executa a a��o necess�ria antes de alterar os dados
//		antesAlterarDados(objeto);

		// Obt�m o Principal Servi�o persistido e altera seus dados
	//	KDominioAplicacao objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());
		
	//	copiarValor(objeto, objetoPersistido);

		// Executa a a��o necess�ria depois de alterar os dados
//		depoisAlterarDados(objeto);
		
		// Inclui o Principal Servi�o
	getNucleoDaoBase().merge(objeto);


		//return objetoPersistido;
		return objeto;
	}

	@Override
	public NucleoDAOBase<KParadigma> getNucleoDaoBase() {
		return KParadigmaDAO;
	}

}


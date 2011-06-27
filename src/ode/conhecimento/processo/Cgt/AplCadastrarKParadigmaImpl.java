package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KParadigma;
import ode.conhecimento.processo.Cgd.KParadigmaDAO;

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

	@Override
	protected void copiarValor(KParadigma objetoFonte,
			KParadigma objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
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


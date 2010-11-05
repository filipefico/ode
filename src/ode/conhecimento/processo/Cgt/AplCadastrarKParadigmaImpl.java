package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.Cdp.KParadigma;
import ode.conhecimento.processo.Cgd.KParadigmaDAO;

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



}


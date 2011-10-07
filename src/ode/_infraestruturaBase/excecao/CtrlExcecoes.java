package ode._infraestruturaBase.excecao;


import ode._infraestruturaBase.util.NucleoMensagens;

import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Messagebox;

public class CtrlExcecoes {
	/**
	 * M�todo F�brica que cria uma execacao de defini��o de objetos
	 * */
	@SuppressWarnings("rawtypes")
	public static RuntimeException factoryExcecaoDefinicao(String nomeObjeto,
			Class classe) {

		String nomeClasse = classe.toString();
		return new RuntimeException(
				"N�o foi configurado adequadamente o objeto " + nomeObjeto
						+ " em " + nomeClasse + ". Seu valor n�o pode ser null");

	}

	/**
	 * Exibe janela de erro apresentando a mensagem de erro passada.
	 * 
	 * @param mensagemErro
	 *            Mensagem de erro a ser exibida na janela de erro
	 */
	public static void exibirJanelaErro(String mensagemErro) {
		try {
			Messagebox.show(mensagemErro,
					NucleoMensagens.getMensagem(NucleoMensagens.TERMO_ERRO),
					Messagebox.OK,
					Messagebox.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exibe janela de erro apresentando a mensagem de erro de acordo com a
	 * exce��o passada
	 * 
	 * @param excecao
	 *            Exce��o para a qual o erro deve ser exibido
	 */
	public static void tratarExcecao(Exception excecao) {

		// Inicializa a mensagem de erro com "Erro Desconhecido!"
		String mensagemErro = NucleoMensagens.getMensagem(NucleoMensagens.MSG_ERRO_DESCONHECIDO);
		
		if (excecao instanceof DataAccessException) {
			mensagemErro = NucleoMensagens.getMensagem(NucleoMensagens.MSG_ERRO_ACESSO_BD);
		} else if (excecao instanceof NucleoRegraNegocioExcecao) {
			mensagemErro = ((NucleoRegraNegocioExcecao) excecao).getMensagem();
		} else if (excecao instanceof WrongValueException) {
			mensagemErro = excecao.getMessage();
		}
		
		// TODO LOG
		excecao.printStackTrace();
		exibirJanelaErro(mensagemErro);

	}
}

package nucleo.comuns.visao.paginacao;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;

import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Messagebox;

public class ErrorHandling {
	
	/**
	 * Exibe janela de erro apresentando a mensagem de erro passada.
	 * 
	 * @param mensagemErro
	 *            Mensagem de erro a ser exibida na janela de erro
	 */
	public static void exibirJanelaErro(String mensagemErro) {
		try {
			Messagebox.show(mensagemErro, NucleoMensagens
					.getMensagem(NucleoMensagens.TERMO_ERRO), Messagebox.OK,
					Messagebox.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exibe janela de erro apresentando a mensagem de erro de acordo com a
	 * exceção passada
	 * 
	 * @param excecao
	 *            Exceção para a qual o erro deve ser exibido
	 */
	public static void exibirJanelaErro(Exception excecao) {

		// Inicializa a mensagem de erro com "Erro Desconhecido!"
		String mensagemErro = NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_ERRO_DESCONHECIDO);

		// Altera a mensagem de erro se for uma exceção conhecida
		if (excecao instanceof DataAccessException) {
			mensagemErro = NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_ERRO_ACESSO_BD);
		} else if (excecao instanceof NucleoRegraNegocioExcecao) {
			mensagemErro = ((NucleoRegraNegocioExcecao) excecao).getMensagem();
		} else if (excecao instanceof WrongValueException) {
			mensagemErro = excecao.getMessage();
		}

		excecao.printStackTrace();

		// Exibe a janela de erro
		exibirJanelaErro(mensagemErro);

	}

}

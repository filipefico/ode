package ode.nucleo.excecao;


import ode.nucleo.util.NucleoMensagens;

import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Messagebox;

public class ControladorExcecoes {
	/**
	 * Método Fábrica que cria uma execacao de definição de objetos
	 * */
	public static RuntimeException factoryExcecaoDefinicao(String nomeObjeto,
			Class classe) {

		String nomeClasse = classe.toString();
		return new RuntimeException(
				"Não foi configurado adequadamente o objeto " + nomeObjeto
						+ " em " + nomeClasse + ". Seu valor não pode ser null");

	}

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

		try {

			throw excecao;

		} catch (DataAccessException e) {
			mensagemErro = NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_ERRO_ACESSO_BD);

		} catch (NucleoRegraNegocioExcecao e) {
			mensagemErro = ((NucleoRegraNegocioExcecao) excecao).getMensagem();

		} catch (WrongValueException e) {
			mensagemErro = excecao.getMessage();
		}

		catch (Exception e) {
			// faz nada pra mensagem
		}

		// TODO LOG
		excecao.printStackTrace();

		// Exibe a janela de erro
		exibirJanelaErro(mensagemErro);

	}
}

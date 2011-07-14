package ode.nucleo.excecao;

import ode.nucleo.util.NucleoMensagens;

public abstract class NucleoExcecao extends Exception {

	/**
	 * Mensagem internacionaliz�vel a ser demonstrada na exce��o
	 */
	private String mensagem;

	/**
	 * Causa da exce��o disparada
	 */
	private Throwable causa;

	public NucleoExcecao(String msg, Throwable tr) {
		if (msg == null) {
			mensagem = NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_ERRO_DESCONHECIDO);
		} else {
			mensagem = msg;
		}

		causa = tr;
	}

	public Throwable getCausa() {
		return causa;
	}

	public void setCausa(Throwable causa) {
		this.causa = causa;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}

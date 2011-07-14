package ode.nucleo.excecao;

import ode.nucleo.util.NucleoMensagens;

public abstract class NucleoExcecao extends Exception {

	/**
	 * Mensagem internacionalizável a ser demonstrada na exceção
	 */
	private String mensagem;

	/**
	 * Causa da exceção disparada
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

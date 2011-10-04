package ode.nucleo.excecao;

public class NucleoRegraNegocioExcecao extends NucleoExcecao {

	private static final long serialVersionUID = -774219454567511749L;

	public NucleoRegraNegocioExcecao(String msg, Throwable tr) {
		super(msg, tr);
	}
	
	public NucleoRegraNegocioExcecao(String msg) {
		super(msg, null);
	}
}
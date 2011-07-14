package ode.controleProcesso.cih;

import ode.controleProcesso.cdp.RecursoHumano;
import ode.nucleo.cih.NucleoBandbox;

public class RecursoHuamanoBandbox extends NucleoBandbox<RecursoHumano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String[] definirTitulosCabecalho() {
		return new String[]{"Nome"};
	}

	@Override
	protected String[] definirTamanhosCabecalho() {
		return new String[]{"100%"};
	}

	@Override
	protected String[] recuperarDadosObjeto(RecursoHumano objeto) {
		return new String[]{objeto.getNome()};
	}

	@Override
	public String recuperarLabelObjetoSelecionado(RecursoHumano objeto) {
		return objeto.getNome();
	}
	
}

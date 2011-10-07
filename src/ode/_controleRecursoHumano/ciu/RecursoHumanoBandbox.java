package ode._controleRecursoHumano.ciu;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.NucleoBandbox;

public class RecursoHumanoBandbox extends NucleoBandbox<RecursoHumano> {

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

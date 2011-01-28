package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cdp.KRecursoHumano;

public class PainelCrudKRecursoHardware extends PainelCRUD<KRecursoHardware> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemPaginada<KRecursoHardware> definirListagem() {
		ListagemKRecursoHardware listagem = new ListagemKRecursoHardware();
		return listagem;
	}

	

}


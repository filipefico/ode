package ode.exemplo2.organizacao.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import ode.exemplo2.organizacao.Cdp.OrganizacaoExemplo;

public class PainelCrudOrganizacao extends PainelCRUD<OrganizacaoExemplo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemPaginada<OrganizacaoExemplo> definirListagem() {
		ListagemOrganizacaoExemplo listagem = new ListagemOrganizacaoExemplo();
		return listagem;
	}

	

}


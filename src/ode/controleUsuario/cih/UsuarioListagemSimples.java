package ode.controleUsuario.cih;

import java.util.ArrayList;
import java.util.List;

import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;


public class UsuarioListagemSimples extends ListagemSimples<NucleoUserDetails> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","50%"));
		colunas.add(new NucleoListHeader("Perfil de Acesso","perfilacesso","50%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(NucleoUserDetails objeto) {
		return new String[]{objeto.getRecursoHumano().getNome(),objeto.getPerfilAcesso().getNome()};
	}

}

package ode.controleUsuario.cih;

import java.util.ArrayList;
import java.util.List;

import ode.controleUsuario.cdp.Usuario;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;


public class ListagemUsuario extends ListagemSimples<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Nome de Usuário","nomeUsuario","30%"));
		colunas.add(new NucleoListHeader("Perfil de Acesso","perfilacesso","30%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(Usuario objeto) {
		return new String[]{objeto.getRecursoHumano().getNome(),objeto.getNomeUsuario(),objeto.getPerfilAcesso().getNome()};
	}

}

package ode.controleUsuario.cgt;

import java.util.List;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;


public interface AplAutenticarUsuario {

	public void efetuarLogin(String nomeUsuario, String senha, boolean rememberme) throws NucleoRegraNegocioExcecao;
	
	public void efetuarLogout();
	
	public boolean recuperarLoginCookie(String nomeUsuario, String token);
	
	public List<Funcionalidade> obterFuncionalidades();
	
}

package ode.controleUsuario.cgt;

import java.util.List;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;


public interface AplAutenticarUsuario {

	/*
	 * Efetua o login de um usu�rio no sistema, possivelmente salvando um cookie para preservar a identifica��o para visitas posteriores.
	 * 
	 * @param nomeUsuario
	 * Nome do usu�rio, conforme informado na interface
	 * 
	 * @param senha
	 * Senha do usu�rio, conforme informada na interface
	 * 
	 * @param rememberMe
	 * Boolean informando se o usu�rio desejar permanecer conectado
	 */
	public void efetuarLogin(String nomeUsuario, String senha, boolean rememberme) throws NucleoRegraNegocioExcecao;
	
	/*
	 * Remove o usu�rio da sess�o e exclui os devidos cookies do navegador
	 */
	public void efetuarLogout();
	
	/*
	 * Utiliza os cookies do navegador para identificar o usu�rio no sistema
	 * @return Se o usu�rio foi ou n�o logado no sistema
	 */
	public boolean recuperarLoginCookie(String nomeUsuario, String token);
	
	/*
	 * Obt�m as funcionalidades do sistema e suas permiss�es
	 * @return Lista de funcionalidades 
	 */
	public List<Funcionalidade> obterFuncionalidades();
	
}

package ode.controleUsuario.cgt;

import java.util.List;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;


public interface AplAutenticarUsuario {

	/*
	 * Efetua o login de um usuário no sistema, possivelmente salvando um cookie para preservar a identificação para visitas posteriores.
	 * 
	 * @param nomeUsuario
	 * Nome do usuário, conforme informado na interface
	 * 
	 * @param senha
	 * Senha do usuário, conforme informada na interface
	 * 
	 * @param rememberMe
	 * Boolean informando se o usuário desejar permanecer conectado
	 */
	public void efetuarLogin(String nomeUsuario, String senha, boolean rememberme) throws NucleoRegraNegocioExcecao;
	
	/*
	 * Remove o usuário da sessão e exclui os devidos cookies do navegador
	 */
	public void efetuarLogout();
	
	/*
	 * Utiliza os cookies do navegador para identificar o usuário no sistema
	 * @return Se o usuário foi ou não logado no sistema
	 */
	public boolean recuperarLoginCookie(String nomeUsuario, String token);
	
	/*
	 * Obtém as funcionalidades do sistema e suas permissões
	 * @return Lista de funcionalidades 
	 */
	public List<Funcionalidade> obterFuncionalidades();
	
}

package ode.nucleo.util;

import java.util.Locale;

import ode.controleProjeto.cdp.Projeto;
import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.principal.cih.WindowMenu;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Window;


/**
 * Classe que disponibiliza informa��es de contexto para a sess�o do usu�rio.
 * 
 * @author Alexandre G. N. Coelho
 */
public class NucleoContexto {

	/**
	 * Nome do atributo de sess�o que cont�m o Locale da aplica��o
	 */
	public static final String LOCALE = "locale";
	
	/**
	 * Nome do atributo da sess�o que cont�m o projeto atualmente selecionado.
	 */
	public static final String PROJETO = "Projeto";
	
	/**
	 * Nome do atributo da sess�o que cont�m janela principal.
	 */
	public static final String JANELA_PRINCIPAL = "JanelaPrincipal";

	/**
	 * Obt�m o Locale da Sess�o do Usu�rio.
	 * 
	 * @return O Locale da Sess�o do Usu�rio.
	 */
	public static Locale recuperarLocale() {
		Locale locale = null;
		if (Sessions.getCurrent() != null) {
			locale = (Locale) Sessions.getCurrent().getAttribute(LOCALE);
			if (locale == null) {
				locale = new Locale("pt", "BR");
				atribuirLocale(locale);
			}
		} else {
			locale = new Locale("pt", "BR");
		}

		return locale;
	}

	/**
	 * Atribui um novo Locale para a Sess�o do Usu�rio.
	 * 
	 * @param locale
	 *            Novo Locale para a Sess�o do Usu�rio.
	 */
	public static void atribuirLocale(Locale locale) {
		Sessions.getCurrent().setAttribute(LOCALE, locale);
		Sessions.getCurrent().setAttribute("px_preferred_locale", locale);
	}

	/**
	 * Obt�m o usu�rio atualmente logado no sistema.
	 * 
	 * @return Usu�rio logado.
	 */
	public static NucleoUserDetails recuperarUsuarioLogado() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		NucleoUserDetails usuario = (NucleoUserDetails) authentication
				.getPrincipal();

		return usuario;
	}

	public static Projeto recuperarProjeto() {
		Projeto projeto = null;
		if (Sessions.getCurrent() != null) {
			projeto = (Projeto) Sessions.getCurrent().getAttribute(PROJETO);
		}

		return projeto;
	}
	
	public static void atribuirProjeto(Projeto projeto) {
		Sessions.getCurrent().setAttribute(PROJETO, projeto);
	}
	
	public static WindowMenu recuperarJanelaPrincipal() {
		WindowMenu window = null;
		if (Sessions.getCurrent() != null) {
			window = (WindowMenu) Sessions.getCurrent().getAttribute(JANELA_PRINCIPAL);
		}
		return window;
	}
	
	public static void atribuirJanelaPrincipal(WindowMenu window) {
		Sessions.getCurrent().setAttribute(JANELA_PRINCIPAL, window);
	}
}
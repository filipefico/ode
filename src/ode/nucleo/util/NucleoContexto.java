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
 * Classe que disponibiliza informações de contexto para a sessão do usuário.
 * 
 * @author Alexandre G. N. Coelho
 */
public class NucleoContexto {

	/**
	 * Nome do atributo de sessão que contém o Locale da aplicação
	 */
	public static final String LOCALE = "locale";
	
	/**
	 * Nome do atributo da sessão que contém o projeto atualmente selecionado.
	 */
	public static final String PROJETO = "Projeto";
	
	/**
	 * Nome do atributo da sessão que contém janela principal.
	 */
	public static final String JANELA_PRINCIPAL = "JanelaPrincipal";

	/**
	 * Obtém o Locale da Sessão do Usuário.
	 * 
	 * @return O Locale da Sessão do Usuário.
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
	 * Atribui um novo Locale para a Sessão do Usuário.
	 * 
	 * @param locale
	 *            Novo Locale para a Sessão do Usuário.
	 */
	public static void atribuirLocale(Locale locale) {
		Sessions.getCurrent().setAttribute(LOCALE, locale);
		Sessions.getCurrent().setAttribute("px_preferred_locale", locale);
	}

	/**
	 * Obtém o usuário atualmente logado no sistema.
	 * 
	 * @return Usuário logado.
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
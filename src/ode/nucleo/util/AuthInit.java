package ode.nucleo.util;

import java.util.Map;

import ode.controleUsuario.cgt.AplAutenticarUsuario;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.SpringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInit implements Initiator {
	@SuppressWarnings("rawtypes")
	@Override
	public void doInit(Page page, Map args) throws Exception {
		
		boolean autenticado = false;
		if (NucleoContexto.recuperarUsuarioLogado() != null)
			autenticado = true;

		Execution exec = Executions.getCurrent();
		Cookie[] cookies = ((HttpServletRequest) exec.getNativeRequest()).getCookies();

		String nomeUsuario = null;
		String token = null;
		for (Cookie cookie : cookies) {
			if ("nomeUsuario".equals(cookie.getName()))
				nomeUsuario = cookie.getValue();
			else if ("token".equals(cookie.getName()))
				token = cookie.getValue();
		}
		if (nomeUsuario != null) {
			AplAutenticarUsuario apl = (AplAutenticarUsuario)SpringUtil.getBean(AplAutenticarUsuario.class.getSimpleName());
			autenticado = apl.recuperarLoginCookie(nomeUsuario, token);
		}

		if (!autenticado) {
			((HttpServletResponse) exec.getNativeResponse()).sendRedirect("login.zul");
			exec.setVoided(true);
		}
	}

	@Override
	public void doAfterCompose(Page arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean doCatch(Throwable arg0) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doFinally() throws Exception {
		// TODO Auto-generated method stub

	}
}
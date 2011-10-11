package ode._infraestruturaBase.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ode.controleUsuario.cgt.AplAutenticarUsuario;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.SpringUtil;

public class AuthInit implements Initiator {
	@SuppressWarnings("rawtypes")
	@Override
	public void doInit(Page page, Map args) throws Exception {
		//se o usuario nao está na sessao, deve-se verificar os cookies  
		if (NucleoContexto.recuperarUsuarioLogado() == null) {
			boolean autenticado = false;
			Execution exec = Executions.getCurrent();
			Cookie[] cookies = ((HttpServletRequest) exec.getNativeRequest()).getCookies();
			//caso o navegador suporte cookies
			if(cookies!=null) {
				String nomeUsuario = null;
				String token = null;
				for (Cookie cookie : cookies) {
					if ("nomeUsuario".equals(cookie.getName()))
						nomeUsuario = cookie.getValue();
					else if ("token".equals(cookie.getName()))
						token = cookie.getValue();
				}
				if (nomeUsuario != null) {
					//caso os cookies existam, executa o método da cgt
					AplAutenticarUsuario apl = (AplAutenticarUsuario)SpringUtil.getBean(AplAutenticarUsuario.class.getSimpleName());
					autenticado = apl.recuperarLoginCookie(nomeUsuario, token);
				}
			}
			//cookies inexistentes ou invalidos -> redirecionar para login
			if (!autenticado) {
				
				ServletContext sc = (ServletContext)Sessions.getCurrent().getWebApp().getNativeContext();
				String fullPath = sc.getRealPath("login.zul");
				
				((HttpServletResponse) exec.getNativeResponse()).sendRedirect(fullPath);
				exec.setVoided(true);
			}
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
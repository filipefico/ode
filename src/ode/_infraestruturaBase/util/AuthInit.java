package ode._infraestruturaBase.util;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ode.controleUsuario.cgt.AplAutenticarUsuario;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.SpringUtil;

public class AuthInit implements Initiator {
	@SuppressWarnings("rawtypes")
	@Override
	public void doInit(Page page, Map args) throws Exception {
		//se o usuario nao est� na sessao, deve-se verificar os cookies  
		if (NucleoContexto.recuperarUsuarioLogado() == null) {
			NucleoUtil.carregarDadosIniciaisODE();
			boolean autenticado = false;
			Execution exec = Executions.getCurrent();
			Cookie[] cookies = ((HttpServletRequest) exec.getNativeRequest()).getCookies();
			//caso o navegador suporte cookies
			if(cookies!=null) {
				AplAutenticarUsuario apl = (AplAutenticarUsuario)SpringUtil.getBean(AplAutenticarUsuario.class.getSimpleName());
				autenticado = apl.recuperarLoginCookies(cookies);
			}
			//cookies inexistentes ou invalidos -> redirecionar para login
			if (!autenticado) {			
				((HttpServletResponse) exec.getNativeResponse()).sendRedirect("/ode/login.zul");
				exec.setVoided(true);
			}
		}
	}

	@Override
	public void doAfterCompose(Page arg0) throws Exception {
	}

	@Override
	public boolean doCatch(Throwable arg0) throws Exception {
		return false;
	}

	@Override
	public void doFinally() throws Exception {
	}
}
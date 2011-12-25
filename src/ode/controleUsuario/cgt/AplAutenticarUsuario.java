package ode.controleUsuario.cgt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoUtil;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgd.UsuarioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.Executions;

@Service("AplAutenticarUsuario")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplAutenticarUsuario {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ProjetoDAO projetoDAO;

	 // Método obterFuncionalides foi migrado para a infraestruturaBase::util::FuncionalidadesMenu
	
	/*
	 * (non-Javadoc)
	 * @see ode.controleUsuario.cgt.AplAutenticarUsuario#efetuarLogin(java.lang.String, java.lang.String, boolean)
	 */

	public void efetuarLogin(String nomeUsuario, String senha, boolean rememberme) throws NucleoRegraNegocioExcecao {
		Usuario usuario = usuarioDAO.recuperarPorNomeUsuario(nomeUsuario);
		if (usuario == null) {
			throw new NucleoRegraNegocioExcecao("Usuário não encontrado!", null);
		}
		//verifica a senha do usuário
		if (!usuario.getSenha().equals(NucleoUtil.encrypt(senha))) {
			throw new NucleoRegraNegocioExcecao("Senha incorreta!", null);
		} else {
			NucleoContexto.atribuirUsuarioLogado(usuario);
			if (rememberme) {
				HttpServletResponse response = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
				Cookie cookieNomeUsuario = new Cookie("nomeUsuario", nomeUsuario);
				cookieNomeUsuario.setMaxAge(9999999);
				response.addCookie(cookieNomeUsuario);
				
				Cookie cookieToken = new Cookie("token", NucleoUtil.encrypt(usuario.getSenha()));
				cookieToken.setMaxAge(9999999);
				response.addCookie(cookieToken);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ode.controleUsuario.cgt.AplAutenticarUsuario#recuperarLoginCookie(java.lang.String, java.lang.String)
	 */
	
	public boolean recuperarLoginCookies(Cookie[] cookies) {
		String nomeUsuario = null;
		String token = null;
		String projetoId = null;
		for (Cookie cookie : cookies) {
			if ("nomeUsuario".equals(cookie.getName()))
				nomeUsuario = cookie.getValue();
			else if ("token".equals(cookie.getName()))
				token = cookie.getValue();
			else if("projeto".equals(cookie.getName()))
				projetoId = cookie.getValue();
		}
		
		Usuario usuario = usuarioDAO.recuperarPorNomeUsuario(nomeUsuario);
		//caso o cookie seja válido, registra a sessão do usuário
		if(usuario!=null && token.equals(NucleoUtil.encrypt(usuario.getSenha()))) {
			NucleoContexto.atribuirUsuarioLogado(usuario);
			if(projetoId != null) {
				Projeto projeto = projetoDAO.recuperarPorId(Long.parseLong(projetoId));
				if (projeto != null)
					NucleoContexto.atribuirProjeto(projeto);	
			}
			return true;
		}
		//caso contrário, exclui o cookie do navegador
		efetuarLogout();
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ode.controleUsuario.cgt.AplAutenticarUsuario#efetuarLogout()
	 */
	
	public void efetuarLogout() {
		HttpServletResponse response = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
		Cookie c1 = new Cookie("nomeUsuario", "");
		c1.setMaxAge(0);
		response.addCookie(c1);
		Cookie c2 = new Cookie("token", "");
		c2.setMaxAge(0);
		response.addCookie(c2);
		Cookie c3 = new Cookie("projeto", "");
		c3.setMaxAge(0);
		response.addCookie(c3);
		NucleoContexto.atribuirUsuarioLogado(null);
		NucleoContexto.atribuirProjeto(null);
	}
}
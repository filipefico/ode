package ode.controleUsuario.cgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import ode.conhecimento.processo.Cci.*;
import ode.controleProcesso.cci.*;
import ode.controleProjeto.cci.*;
import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgd.UsuarioDAO;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoContexto;
import ode.nucleo.util.NucleoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.Executions;

@Service("AplAutenticarUsuario")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplAutenticarUsuarioImpl implements AplAutenticarUsuario {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	

	public void efetuarLogin(String nomeUsuario, String senha, boolean rememberme) throws NucleoRegraNegocioExcecao {
		Usuario usuario = usuarioDAO.recuperarPorNomeUsuario(nomeUsuario);
		if (usuario == null) {
			throw new NucleoRegraNegocioExcecao("Usu�rio n�o encontrado!", null);
		}
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
	
	public boolean recuperarLoginCookie(String nomeUsuario, String tokenCookie) {
		Usuario usuario = usuarioDAO.recuperarPorNomeUsuario(nomeUsuario);
		if(usuario!=null && tokenCookie.equals(NucleoUtil.encrypt(usuario.getSenha()))) {
			NucleoContexto.atribuirUsuarioLogado(usuario);
			return true;
		}
		efetuarLogout();
		return false;
	}
	
	public void efetuarLogout() {
		HttpServletResponse response = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
		Cookie c1 = new Cookie("nomeUsuario", "");
		c1.setMaxAge(0);
		response.addCookie(c1);
		Cookie c2 = new Cookie("token", "");
		c1.setMaxAge(0);
		response.addCookie(c2);
		NucleoContexto.atribuirUsuarioLogado(null);
	}

	public List<Funcionalidade> obterFuncionalidades() {
		List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
			
		funcionalidades.add(criar("Projeto").permitir(PerfilAcesso.Desenvolvedor)
			.addSubfuncionalidade(criar("Selecionar Projeto").setCtrl(CtrlSelecionarProjeto.class).permitir(PerfilAcesso.Desenvolvedor))
			.addSubfuncionalidade(criar("Cadastrar Projeto").setCtrl(CtrlProjetoCRUD.class))
		);
		
		funcionalidades.add(criar("Administra��o")
			.addSubfuncionalidade(criar("Usu�rios").setCtrl(ode.controleUsuario.cci.CtrlUsuarioCRUD.class))
		);
		
		funcionalidades.add(criar("Recursos")
			.addSubfuncionalidade(criar("Recursos Humanos").setCtrl(CtrlRecursoHumanoCRUD.class))
			//.addSubfuncionalidade(criar("Recursos de Hardware").setCtrl(CtrlRecursoHardwareCRUD.class))
			//.addSubfuncionalidade(criar("Ferramentas de Software").setCtrl(CtrlFerramentaSoftwareCRUD.class))
		);
			
		funcionalidades.add(criar("Conhecimento")
			.addSubfuncionalidade(criar("Recursos")
				.addSubfuncionalidade(criar("Recursos Humanos").setCtrl(CtrlKRecursoHumanoCRUD.class))
				.addSubfuncionalidade(criar("Recursos de Hardware").setCtrl(CtrlKRecursoHardwareCRUD.class))
				.addSubfuncionalidade(criar("Ferramentas de Software").setCtrl(CtrlKFerramentaSoftwareCRUD.class))
			)
			.addSubfuncionalidade(criar("Processos")
				.addSubfuncionalidade(criar("Paradigmas").setCtrl(CtrlKParadigmaCRUD.class))
				.addSubfuncionalidade(criar("Tipos de Software").setCtrl(CtrlKTipoSoftwareCRUD.class))
				.addSubfuncionalidade(criar("Tipos de Artefato").setCtrl(CrtlTipoKArtefatoCRUD.class))
				.addSubfuncionalidade(criar("Artefatos").setCtrl(CtrlKArtefatoCRUD.class))
				.addSubfuncionalidade(criar("Dom�nios de Aplica��o").setCtrl(CtrlKDominioAplicacaoCRUD.class))
				.addSubfuncionalidade(criar("Categorias de Processo").setCtrl(CtrlKCategoriaProcessoCRUD.class))
				.addSubfuncionalidade(criar("Processos").setCtrl(CtrlKProcessoCRUD.class))
				.addSubfuncionalidade(criar("Atividades").setCtrl(CtrlKAtividadeCRUD.class))
				.addSubfuncionalidade(criar("Procedimentos").setCtrl(CtrlKProcedimentoCRUD.class))
			)
			.addSubfuncionalidade(criar("Organiza��o")
				.addSubfuncionalidade(criar("Procedimentos").setCtrl(ode.conhecimento.organizacao.Cci.CtrlKDominioConhecimentoCRUD.class))
			)
		);
		
		funcionalidades.add(criar("Processo Padr�o")
			.addSubfuncionalidade(criar("Componentes de Processo Padr�o").setCtrl(ode.processoPadrao.Cci.CtrlDefinirProcessoPadraoCRUD.class))
		);
		return funcionalidades;

	}

	private Funcionalidade criar(String s) {
		Funcionalidade f = new Funcionalidade(s);
		f.permitir(PerfilAcesso.Administrador);
		return f;
	}
	
}

package ode.controleUsuario.cgt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import ode._controleFerramenta.ciu.CtrlFerramentaSoftwareCRUD;
import ode._controleProcesso.ciu.CtrlDefinicaoProcesso;
import ode._controleRecursoHumano.ciu.CtrlDefinirEquipe;
import ode._controleRecursoHumano.ciu.CtrlRecursoHumanoCRUD;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoUtil;
import ode.alocacaoRecurso.ciu.CtrlAlocacaoRecurso;
import ode.atuacaoRecursoHumano.ciu.CtrlAtuacaoRHCRUD;
import ode.conhecimento.organizacao.ciu.CtrlKCompetenciaCRUD;
import ode.conhecimento.processo.ciu.CrtlTipoKArtefatoCRUD;
import ode.conhecimento.processo.ciu.CtrlKArtefatoCRUD;
import ode.conhecimento.processo.ciu.CtrlKAtividadeCRUD;
import ode.conhecimento.processo.ciu.CtrlKCategoriaProcessoCRUD;
import ode.conhecimento.processo.ciu.CtrlKDominioAplicacaoCRUD;
import ode.conhecimento.processo.ciu.CtrlKFerramentaSoftwareCRUD;
import ode.conhecimento.processo.ciu.CtrlKParadigmaCRUD;
import ode.conhecimento.processo.ciu.CtrlKProcedimentoCRUD;
import ode.conhecimento.processo.ciu.CtrlKProcessoCRUD;
import ode.conhecimento.processo.ciu.CtrlKRecursoHardwareCRUD;
import ode.conhecimento.processo.ciu.CtrlKRecursoHumanoCRUD;
import ode.conhecimento.processo.ciu.CtrlKTipoSoftwareCRUD;
import ode.conhecimentoMedicao.cci.CtrlKElementoMensuravelCRUD;
import ode.conhecimentoMedicao.cci.CtrlKEscalaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKMedidaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKMetodoAnaliticoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKNecessidadeInformacaoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKObjetivoEstrategicoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKObjetivoMedicaoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKObjetivoSoftwareCRUD;
import ode.conhecimentoMedicao.cci.CtrlKPeriodicidadeCRUD;
import ode.conhecimentoMedicao.cci.CtrlKProcedimentoAnaliseMedicaoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKProcedimentoMedicaoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKTipoEntidadeMensuravelCRUD;
import ode.conhecimentoMedicao.cci.CtrlKUnidadeMedidaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKValorEscalaCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.controleProjeto.ciu.CtrlProjetoCRUD;
import ode.controleProjeto.ciu.CtrlSelecionarProjeto;
import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgd.UsuarioDAO;
import ode.controleUsuario.ciu.CtrlUsuarioCRUD;
import ode.processoPadrao.ciu.CtrlDefinirProcessoPadrao;

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

	/*
	 * (non-Javadoc)
	 * @see ode.controleUsuario.cgt.AplAutenticarUsuario#obterFuncionalidades()
	 */

	public List<Funcionalidade> obterFuncionalidades() {
		
		/*
		 * Seguir o padrão para cadastrar as funcionalidades:
		 *
		 * Itens para a raiz do menu entram em funcionalidades.add()
		 * Itens em nível hierárquico inferior entram em .addSubfuncionalidade() do item pai
		 * 
		 * Criar cada funcionalidade com o método criar(String nomeFuncionalidade)
		 * 
		 *  Atribuir a classe controladora que contém o método iniciar() através de setCtrl()
		 *  Atribuir as eventuais permissões além do Perfil de Acesso Administrador através de permitir()
		 */

		List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
		
		funcionalidades.add(criar("Projeto").permitir(PerfilAcesso.Desenvolvedor)
			.addSubfuncionalidade(criar("Selecionar Projeto").setCtrl(CtrlSelecionarProjeto.class).permitir(PerfilAcesso.Desenvolvedor))
			.addSubfuncionalidade(criar("Cadastrar Projeto").setCtrl(CtrlProjetoCRUD.class))
			.addSubfuncionalidade(criar("Definir Equipe").setCtrl(CtrlDefinirEquipe.class).setDisponivelApenasParaProjetosAbertos(true))
		);
		
		funcionalidades.add(criar("Administração")
			.addSubfuncionalidade(criar("Usuários").setCtrl(CtrlUsuarioCRUD.class))
		);
		
		funcionalidades.add(criar("Recursos")
			//.addSubfuncionalidade(criar("Recursos Humanos").setCtrl(CtrlRecursoHumanoCRUD.class))
			//.addSubfuncionalidade(criar("Recursos Humanos").setCtrl(CtrlAtuacaoRHCRUD.class))
			.addSubfuncionalidade(criar("Recursos Humanos (Básico)").setCtrl(CtrlRecursoHumanoCRUD.class))
			.addSubfuncionalidade(criar("Recursos Humanos (Completo)").setCtrl(CtrlAtuacaoRHCRUD.class))
			.addSubfuncionalidade(criar("Ferramentas de Software").setCtrl(CtrlFerramentaSoftwareCRUD.class))
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
				.addSubfuncionalidade(criar("Domínios de Aplicação").setCtrl(CtrlKDominioAplicacaoCRUD.class))
				.addSubfuncionalidade(criar("Categorias de Processo").setCtrl(CtrlKCategoriaProcessoCRUD.class))
				.addSubfuncionalidade(criar("Processos").setCtrl(CtrlKProcessoCRUD.class))
				.addSubfuncionalidade(criar("Atividades").setCtrl(CtrlKAtividadeCRUD.class))
				.addSubfuncionalidade(criar("Procedimentos").setCtrl(CtrlKProcedimentoCRUD.class))
			)
			.addSubfuncionalidade(criar("Organização")
				.addSubfuncionalidade(criar("Competências").setCtrl(CtrlKCompetenciaCRUD.class))
			)
			.addSubfuncionalidade(criar("Medição")
					.addSubfuncionalidade(criar("Objetivos")
							.addSubfuncionalidade(criar("Objetivo Estrategico").setCtrl(CtrlKObjetivoEstrategicoCRUD.class))
							.addSubfuncionalidade(criar("Objetivo de Software").setCtrl(CtrlKObjetivoSoftwareCRUD.class))
							.addSubfuncionalidade(criar("Objetivo de Medicao").setCtrl(CtrlKObjetivoMedicaoCRUD.class))
							)
					.addSubfuncionalidade(criar("Necessidade de Informacao").setCtrl(CtrlKNecessidadeInformacaoCRUD.class))
					.addSubfuncionalidade(criar("Medida")
							.addSubfuncionalidade(criar("Elemento Mensurável").setCtrl(CtrlKElementoMensuravelCRUD.class))
							.addSubfuncionalidade(criar("Tipo de Entidade Mensurável").setCtrl(CtrlKTipoEntidadeMensuravelCRUD.class))
							.addSubfuncionalidade(criar("Unidade de Medida").setCtrl(CtrlKUnidadeMedidaCRUD.class))
							.addSubfuncionalidade(criar("Medida").setCtrl(CtrlKMedidaCRUD.class))
							)
					.addSubfuncionalidade(criar("Escala")
							.addSubfuncionalidade(criar("Escala").setCtrl(CtrlKEscalaCRUD.class))
							.addSubfuncionalidade(criar("Valor de Escala").setCtrl(CtrlKValorEscalaCRUD.class))
							)
					.addSubfuncionalidade(criar("Procedimentos")
							.addSubfuncionalidade(criar("Procedimento de Medicao").setCtrl(CtrlKProcedimentoMedicaoCRUD.class))
							.addSubfuncionalidade(criar("Procedimento de Analise de Medicao").setCtrl(CtrlKProcedimentoAnaliseMedicaoCRUD.class))
							.addSubfuncionalidade(criar("Metodo Analitico").setCtrl(CtrlKMetodoAnaliticoCRUD.class))
							)
					.addSubfuncionalidade(criar("Periodicidade").setCtrl(CtrlKPeriodicidadeCRUD.class))
				)
		);
		funcionalidades.add(criar("Processo Padrão")
			.addSubfuncionalidade(criar("Componentes de Processo Padrão").setCtrl(CtrlDefinirProcessoPadrao.class))
		);
		
		funcionalidades.add(criar("Agenda").setCtrl(ode.agenda.ciu.CtrlAgenda.class));
		
		funcionalidades.add(criar("Ferramentas")
			.addSubfuncionalidade(criar("Definição de Processos").setCtrl(CtrlDefinicaoProcesso.class).setDisponivelApenasParaProjetosAbertos(true))
			.addSubfuncionalidade(criar("Alocação de Recursos").setCtrl(CtrlAlocacaoRecurso.class).setDisponivelApenasParaProjetosAbertos(true))
		);
		
		return funcionalidades;

	}
	
	/*
	 * Função auxiliar para o método obterFuncionalidades
	 */

	private Funcionalidade criar(String s) {
		Funcionalidade f = new Funcionalidade(s);
		f.permitir(PerfilAcesso.Administrador);
		return f;
	}
	
	
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
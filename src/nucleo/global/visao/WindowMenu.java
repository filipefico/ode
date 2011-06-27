package nucleo.global.visao;

import java.util.Locale;
import java.util.Set;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoGrantedAuthority;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.util.NucleoContexto;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoMenu;
import ode.conhecimento.organizacao.Cci.CtrlDominioConhecimentoCRUD;
import ode.conhecimento.processo.Cci.CrtlTipoKArtefatoCRUD;
import ode.conhecimento.processo.Cci.CtrlCategoriaProcessoCRUD;
import ode.conhecimento.processo.Cci.CtrlFerramentaSoftwareCRUD;
import ode.conhecimento.processo.Cci.CtrlKArtefatoCRUD;
import ode.conhecimento.processo.Cci.CtrlKAtividadeCRUD;
import ode.conhecimento.processo.Cci.CtrlKDominioAplicacaoCRUD;
import ode.conhecimento.processo.Cci.CtrlKParadigmaCRUD;
import ode.conhecimento.processo.Cci.CtrlKProcedimentoCRUD;
import ode.conhecimento.processo.Cci.CtrlKProcessoCRUD;
import ode.conhecimento.processo.Cci.CtrlKRecursoHardwareCRUD;
import ode.conhecimento.processo.Cci.CtrlKRecursoHumanoCRUD;
import ode.conhecimento.processo.Cci.CtrlTipoSoftwareCRUD;
import ode.conhecimento.requisito.Cci.CtrlKTipoRequisitoCRUD;
import ode.exemplo2.pessoa.Cci.ControladorPrincipal;
import ode.processoPadrao.Cci.CtrlDefinirProcessoPadraoCRUD;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 * Classe responsï¿½vel pela exibiï¿½ï¿½o de um menu de uma janela.
 * 
 * @author Alexandre G. N. Coelho
 */
public class WindowMenu extends Window {

	private static final long serialVersionUID = -1857633860305556039L;

	public WindowMenu() {
	}

	public void onCreate() {

		// Configura e monta a interface grï¿½fica
		this.setWidth("100%");
		this.setHeight("500px");
		this.setZIndex(1);
		this.setBorder("normal");
		this.setTitle("ODE - Caso de Teste");

		// Adiciona o event listener para as bandeiras
		Image imgIdiomaIngles = (Image) getReference().getPage().getFellow(
				"idiomaIngles");
		imgIdiomaIngles.addEventListener("onClick",
				new EventListenerIdiomaIngles());

		Image imgIdiomaPortugues = (Image) getReference().getPage().getFellow(
				"idiomaPortugues");
		imgIdiomaPortugues.addEventListener("onClick",
				new EventListenerIdiomaPortugues());

		// Monta a interface
		this.montarInterfaceGrafica();
		this.atualizaBarraInformacoes();
		this.atualizarPermissoesAcesso();

	}

	public void atualizaBarraInformacoes() {
		// Atualiza o label de informaï¿½ï¿½es de usuï¿½rio e projetoWindowDefinirCompPP.zul
		Label lblInformacoes = (Label) getReference().getPage().getFellow(
				"lblInformacoes");
		NucleoUserDetails usuario = NucleoContexto.recuperarUsuarioLogado();
		String nomeUsuario;
		@SuppressWarnings("unused")
		String nomeProjeto;

		nomeUsuario = (usuario == null) ? NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_USUARIO_NAO_LOGADO)
				: NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO)
						+ ": " + usuario.getUsername();

		lblInformacoes.setValue(nomeUsuario);

		// Atualiza o label e o combo de idioma
		Label lblIdioma = (Label) getReference().getPage().getFellow(
				"lblIdioma");
		lblIdioma.setValue(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_IDIOMA)
				+ ":");
	}

	private void atualizaBarraMenu() {

		// /////////////////////////////
		// Menu Arquivo
		// ////////////////////////////
		menuArquivo.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ARQUIVO));

		menuitemSair.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SAIR));

		// /////////////////////////////
		// Menu Pessoas
		// ////////////////////////////
		menuPessoa.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOA));

		menuitemPessoa.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOAS));
		
		
		// /////////////////////////////
		// Menu Organizacao
		// ////////////////////////////
		menuOrganizacao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO));
		
		//sub-Menu CadastroRecurso

		menuCadastroConhecimento.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_CONHECIMENTO));
		
		//sub-Menu CadastroConhecimento
		
		menuCadastroRecurso.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_RECURSO));

		menuitemRecursoHumano.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_kRECURSO_HUMANO));

		menuitemRecursoHardware.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_kRECURSO_HARDWARE));
		
		menuitemFerramentaSoftware.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_FERRAMENTA_SOFTWARE));

		
		menuitemOrganizacao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO));
		
		// Sub-Menu Cadastro de Processo

		menuCadastroProcesso.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_PROCESSO));
		
		//Sub-Sub-Menu Caracteristicas Gerais
		
		menuCaracteristicasGerais.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CARACTERISTICAS_GERAIS));
		
		menuitemTipoSoftware.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_SOFTWARE));
		
		menuitemDominioAplicacao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DOMINIO_DA_APLICACAO));

		menuitemParadigma.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PARADIGMA));

		//Sub-Sub-Menu Processo
		
		menuProcesso.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO));
		
		menuitemCategoriaProcesso.setLabel (NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CATEGORIA));
		
		menuitemKProcesso.setLabel (NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO));
		
		menuitemMCV.setLabel (NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_MCV));
		
		//Sub-Sub-Menu Ativos de Processo
		
		menuAtivosProcesso.setLabel (NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ATIVO_PROCESSOS));
		
		menuitemTipoKArtefato.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_K_ARTEFATO));
		
		menuitemKArtefato.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_K_ARTEFATO));
		
		menuitemProcedimento.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCEDIMENTOS));
		
		menuitemKAtividade.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_KATIVIDADE));
		
	
				//sub-menu Processo Padrao
		menuProcessoPadrao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_PADRAO));
		
		menuitemCompPP.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_COMPPP));
		
		menuitemEstabelecerRequisitos.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ESTABELECER_REQUISITOS));
		
		menuitemSelecionarCompPPBase.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SELECIONAR_COMPPP_BASE));
		
		menuNovo.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOVO));
		
		menuitemProcessoPadrao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_PADRAO));
		
		menuitemProcessoEspecializado.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_ESPECIALIZADO));
		
		menuAbrir.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ABRIR));
		
		menuitemFinalizarDefinicao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_FINALIZAR_DEFINICAO));
		
		menuitemDefinirMCV.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DEFINIR_MCV));
		
						
		// /////////////////////////////
		// Menu Projeto
		// ////////////////////////////
		menuProjeto.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROJETO));

		menuitemNovo.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOVO));
		
		menuitemAbrir.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ABRIR));
		
		// /////////////////////////////
		// Menu Ferramenta
		// ////////////////////////////
		menuFerramenta.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_FERRAMENTAS));

		menuitemFerramenta.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DEFINICAO_PROCESSOS));
		
	}
	public void atualizarPermissoesAcesso() {

		// Atualiza a visibilidade dos menus e interfaces
		@SuppressWarnings("unused")
		boolean visivel = true;

		// Atualiza menus administrativos baseado na autoridade do usuï¿½rio
		// logado
		boolean admin = false;
		Set<NucleoGrantedAuthority> autoridadesUsuario = NucleoContexto
				.recuperarUsuarioLogado().getGrantedAuthorities();
		for (NucleoGrantedAuthority autoridade : autoridadesUsuario) {
			if (autoridade.getAuthority().equals(
					NucleoGrantedAuthority.AUTHORITY_ADMINISTRADOR)) {
				admin = true;
			}
		}

		// /////////////////////////////
		// Menu Arquivo
		// ////////////////////////////
		menuArquivo.setVisible(true);

		menuitemSair.setVisible(true);

		// /////////////////////////////
		// Menu Pessoas
		// ////////////////////////////

		menuPessoa.setVisible(admin);

		menuitemPessoa.setVisible(admin);

		// /////////////////////////////
		// Menu Organizacao
		// ////////////////////////////

		menuOrganizacao.setVisible(admin);

		menuCadastroConhecimento.setVisible(admin);
		
		menuCadastroRecurso.setVisible(admin);
		
		menuitemFerramentaSoftware.setVisible(admin);
		
		menuitemRecursoHumano.setVisible(admin);
		
		menuitemRecursoHardware.setVisible(admin);
		
		menuitemOrganizacao.setVisible(admin);
		
		// Sub-Menu Cadastro de Processo
		
		menuCadastroProcesso.setVisible(admin);
		
		//Sub-Sub-Menu Caracteristicas Gerais
		
		menuCaracteristicasGerais.setVisible(admin);
		
		menuitemTipoSoftware.setVisible(admin);

		menuitemDominioAplicacao.setVisible(admin);
				
		menuitemParadigma.setVisible(admin);
		
		//Sub-Sub-Menu Processo
		
		menuProcesso.setVisible(admin);
		
		menuitemCategoriaProcesso.setVisible(admin);
		
		menuitemKProcesso.setVisible(admin);

		menuitemMCV.setVisible(admin);
		
		//Sub-Sub-Menu Ativos de Processo
		
		menuAtivosProcesso.setVisible(admin);

		menuitemTipoKArtefato.setVisible(admin);

		menuitemKArtefato.setVisible(admin);
		
		menuitemKAtividade.setVisible(admin);
		
		menuitemProcedimento.setVisible(admin);

		//SubMenu Processo Padrao
		
		menuProcessoPadrao.setVisible(admin);

		menuitemCompPP.setVisible(admin);
		
		menuitemEstabelecerRequisitos.setVisible(admin);
		
		menuitemSelecionarCompPPBase.setVisible(admin);
		
		menuNovo.setVisible(admin);
		
		menuitemProcessoPadrao.setVisible(admin);
		
		menuitemProcessoEspecializado.setVisible(admin);
		
		menuAbrir.setVisible(admin);
		
		menuitemFinalizarDefinicao.setVisible(admin);
		
		menuitemDefinirMCV.setVisible(admin);
		
		
		// /////////////////////////////
		// Menu Projeto
		// ////////////////////////////
		
		menuProjeto.setVisible(admin);

		menuitemNovo.setVisible(admin);
		
		menuitemAbrir.setVisible(admin);

		// /////////////////////////////
		// Menu Ferramenta
		// ////////////////////////////
		menuFerramenta.setVisible(admin);

		menuitemFerramenta.setVisible(admin);
		
	}

	/** Evento executado ao fechar a window */
	public void onClose() {
		this.detach();
	}

	private WindowMenu getReference() {
		return this;
	}

	/** Agrupa e configura componentes de interface grï¿½fica. */
	private void montarInterfaceGrafica() {

		// Barra de Menu
		menuBar = new Menubar();
		menuBar.setParent(this);

		// /////////////////////////////
		// Menu Arquivo
		// ////////////////////////////
		menuArquivo = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ARQUIVO));
		menuArquivo.setParent(menuBar);
		menupopupArquivo = new Menupopup();
		menupopupArquivo.setParent(menuArquivo);
		menuitemSair = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SAIR));
		menuitemSair.setParent(menupopupArquivo);
		menuitemSair.setHref("/logout.zul");

		// /////////////////////////////
		// Menu Projeto
		// ////////////////////////////
		menuProjeto = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROJETO));
		menuProjeto.setParent(menuBar);
		menupopupProjeto = new Menupopup();
		menupopupProjeto.setParent(menuProjeto);
		menuitemNovo = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOVO));
		menuitemNovo.addEventListener("onClick",
				new EventListenerMenuItemNovoProjeto());
		menuitemNovo.setParent(menupopupProjeto);
		//menuitemNovo.setHref("/logout.zul");
		menuitemAbrir = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ABRIR));
		menuitemAbrir.setParent(menupopupProjeto);
		//menuitemAbrir.setHref("/logout.zul");
		
		// /////////////////////////////
		// Menu Pessoa
		// ////////////////////////////
		menuPessoa = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOA));
		menuPessoa.setParent(menuBar);
		menupopupPessoa = new Menupopup();
		menupopupPessoa.setParent(menuPessoa);
		menuitemPessoa = new Menuitem(NucleoMensagens //
				.getMensagem(NucleoMensagens.TERMO_PESSOAS));
		// menuitemPessoa.addEventListener("onClick",new
		// EventListenerMenuItemPessoaExemplo());
		menuitemPessoa.addEventListener("onClick",
				new EListenerPessoaComControlador());
		menuitemPessoa.setParent(menupopupPessoa);

		// /////////////////////////////
		// Menu organizacao
		// ////////////////////////////
		menuOrganizacao = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO));
		menuOrganizacao.setParent(menuBar);
		menupopupOrganizacao = new Menupopup();
		menupopupOrganizacao.setParent(menuOrganizacao);

		// Sub-menu Cadastro de Recurso
		menuCadastroRecurso = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_RECURSO));
		menuCadastroRecurso.setParent(menupopupOrganizacao);
		menupopupCadastroRecurso = new Menupopup();
		menupopupCadastroRecurso.setParent(menuCadastroRecurso);		
		
		menuitemFerramentaSoftware = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_FERRAMENTA_SOFTWARE));
		menuitemFerramentaSoftware.addEventListener("onClick",
				new EListenerFerramentaSoftware());
		menuitemFerramentaSoftware.setParent(menupopupCadastroRecurso);
		
		menuitemRecursoHumano = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_kRECURSO_HUMANO));
		menuitemRecursoHumano.addEventListener("onClick",
				new EListenerRecursoHumano());
		menuitemRecursoHumano.setParent(menupopupCadastroRecurso);
		
		menuitemRecursoHardware = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_kRECURSO_HARDWARE));
		menuitemRecursoHardware.addEventListener("onClick",
				new EListenerRecursoHardware());
		menuitemRecursoHardware.setParent(menupopupCadastroRecurso);
		
		// Sub-menu Cadastro de Conhecimento
		menuCadastroConhecimento = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_CONHECIMENTO));
		menuCadastroConhecimento.setParent(menupopupOrganizacao);
		menupopupCadastroConhecimento = new Menupopup();
		menupopupCadastroConhecimento.setParent(menuCadastroConhecimento);

		menuitemOrganizacao = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO));
		menuitemOrganizacao.addEventListener("onClick",
				new EListenerDominioConhecimento());
		menuitemOrganizacao.setParent(menupopupCadastroConhecimento);

		// Sub-menu Requisitos
		
		menuRequisitos = new NucleoMenu ("Requisitos");
		menuRequisitos.setParent(menupopupCadastroConhecimento);
		menupopupRequisitos = new Menupopup();
		menupopupRequisitos.setParent(menuRequisitos);
		menuitemTipoRequisito = new Menuitem ("Tipos de Requisito");
		menuitemTipoRequisito.setParent(menupopupRequisitos);
		menuitemTipoRequisito.addEventListener("onClick", new EListenerKTipoRequisito()); 
		
		// Sub-menu Processo
		menuProcesso = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO));
		menuProcesso.setParent(menupopupCadastroConhecimento);
		menupopupProcesso = new Menupopup();
		menupopupProcesso.setParent(menuProcesso);
		// Sub-menu Processo Padrão

		menuProcessoPadrao = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_PADRAO));
		menuProcessoPadrao.setParent(menupopupOrganizacao);
		menupopupProcessoPadrao = new Menupopup();
		menupopupProcessoPadrao.setParent(menuProcessoPadrao);
		
		menuitemCompPP = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_COMPPP));
		menuitemCompPP.addEventListener("onClick",
				new EventListenerMenuItemProcessoPadrao());
		menuitemCompPP.setParent(menupopupProcessoPadrao);
		
		menuitemEstabelecerRequisitos = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ESTABELECER_REQUISITOS));
		menuitemEstabelecerRequisitos.setParent(menupopupProcessoPadrao);
		
		menuitemSelecionarCompPPBase = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SELECIONAR_COMPPP_BASE));
		menuitemSelecionarCompPPBase.addEventListener("onClick",
				new EventListenerMenuItemProcessoPadrao());
		menuitemSelecionarCompPPBase.setParent(menupopupProcessoPadrao);
		
		menuNovo = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOVO));
		menuNovo.setParent(menupopupProcessoPadrao);
		menupopupNovo = new Menupopup();
		menupopupNovo.setParent(menuNovo);		
		
		menuitemProcessoPadrao = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_PADRAO));
		//menuitemProcessoPadrao.addEventListener("onClick",
		//		new EListenerFerramentaSoftware());
		menuitemProcessoPadrao.setParent(menupopupNovo);
		
		menuitemProcessoEspecializado = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_ESPECIALIZADO));
		//menuitemProcessoEspecializado.addEventListener("onClick",
		//		new EListenerFerramentaSoftware());
		menuitemProcessoEspecializado.setParent(menupopupNovo);
		
		menuAbrir = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ABRIR));
		menuAbrir.setParent(menupopupProcessoPadrao);
		menupopupAbrir = new Menupopup();
		menupopupAbrir.setParent(menuAbrir);
		
		menuitemProcessoPadrao = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_PADRAO));
		//menuitemProcessoPadrao.addEventListener("onClick",
		//		new EListenerFerramentaSoftware());
		menuitemProcessoPadrao.setParent(menupopupAbrir);
		
		menuitemProcessoEspecializado = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_ESPECIALIZADO));
		//menuitemProcessoEspecializado.addEventListener("onClick",
		//		new EListenerFerramentaSoftware());
		menuitemProcessoEspecializado.setParent(menupopupAbrir);
		
		menuitemFinalizarDefinicao = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_FINALIZAR_DEFINICAO));
		menuitemFinalizarDefinicao.setParent(menupopupProcessoPadrao);
		
		menuitemDefinirMCV = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DEFINIR_MCV));
		menuitemDefinirMCV.setParent(menupopupProcessoPadrao);		
		
		
		// Sub-menu Cadastro de Processo
		menuCadastroProcesso = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_PROCESSO));
		menuCadastroProcesso.setParent(menupopupCadastroConhecimento);
		menupopupCadastroProcesso = new Menupopup();
		menupopupCadastroProcesso.setParent(menuCadastroProcesso);
		
		//Sub-Sub-Menu Caracteristicas Gerais
		
		menuCaracteristicasGerais = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CARACTERISTICAS_GERAIS));
		menuCaracteristicasGerais.setParent(menupopupCadastroProcesso);
		menupopupCaracteristicasGerais = new Menupopup();
		menupopupCaracteristicasGerais.setParent(menuCaracteristicasGerais);
		
		menuitemTipoSoftware = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_SOFTWARE));
		menuitemTipoSoftware.addEventListener("onClick",
				new EListenerTipoSoftware());
		menuitemTipoSoftware.setParent(menupopupCaracteristicasGerais);
		
		menuitemDominioAplicacao = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DOMINIO_DA_APLICACAO));
		 menuitemDominioAplicacao.addEventListener("onClick",
				 new EListenerKDominioAplicacaoComControlador());
		menuitemDominioAplicacao.setParent(menupopupCaracteristicasGerais);
		
		menuitemParadigma = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PARADIGMA));
		menuitemParadigma.addEventListener("onClick",
				new EListenerKParadigmaComControlador());
		menuitemParadigma.setParent(menupopupCaracteristicasGerais);
		
		//Sub-Sub-Menu Processo
		
		menuProcesso = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO));
		menuProcesso.setParent(menupopupCadastroProcesso);
		menupopupProcesso = new Menupopup();
		menupopupProcesso.setParent(menuProcesso);
		
		menuitemCategoriaProcesso = new Menuitem (NucleoMensagens.
				getMensagem((NucleoMensagens.TERMO_CATEGORIA)));
		menuitemCategoriaProcesso.setParent(menupopupProcesso);
		menuitemCategoriaProcesso.addEventListener ("onClick", 
				new EListenerKCategoriaProcesso());
		
		menuitemKProcesso = new Menuitem (NucleoMensagens.
				getMensagem(NucleoMensagens.TERMO_PROCESSO));
		menuitemKProcesso.setParent(menupopupProcesso);
		menuitemKProcesso.addEventListener("onClick", 
				new EListenerKProcesso());		
		
		menuitemMCV = new Menuitem (NucleoMensagens.
				getMensagem((NucleoMensagens.TERMO_MCV)));
		menuitemMCV.setParent(menupopupProcesso);
				
		//Sub-Sub-Menu Ativos de Processo
		
		menuAtivosProcesso = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ATIVO_PROCESSOS));
		menuAtivosProcesso.setParent(menupopupCadastroProcesso);
		menupopupAtivosProcesso = new Menupopup();
		menupopupAtivosProcesso.setParent(menuAtivosProcesso);
		
		menuitemTipoKArtefato = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_K_ARTEFATO));
		menuitemTipoKArtefato.setParent(menupopupAtivosProcesso);
		menuitemTipoKArtefato.addEventListener("onClick",
				new EventListenerTipoKArtefato());
		
		menuitemKArtefato = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_K_ARTEFATO));
		menuitemKArtefato.setParent(menupopupAtivosProcesso);
		menuitemKArtefato.addEventListener("onClick",
				new EventListenerKArtefato());
		
		menuitemProcedimento = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCEDIMENTOS));
		menuitemProcedimento.setParent(menupopupAtivosProcesso);
		menuitemProcedimento.addEventListener("onClick",
				new EListenerProcedimento());
		
		menuitemKAtividade = new Menuitem (NucleoMensagens.
				getMensagem(NucleoMensagens.TERMO_KATIVIDADE));
		menuitemKAtividade.setParent(menupopupAtivosProcesso);
		menuitemKAtividade.addEventListener("onClick", 
				new EListenerKAtividade());


		// /////////////////////////////
		// Menu Ferramentas
		// ////////////////////////////
		
		menuFerramenta = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_FERRAMENTAS));
		menuFerramenta.setParent(menuBar);
		menupopupFerramenta = new Menupopup();
		menupopupFerramenta.setParent(menuFerramenta);
		menuitemFerramenta = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DEFINICAO_PROCESSOS));
		menuitemFerramenta.addEventListener("onClick",
				new EventListenerMenuItemDefinirProcesso());
		menuitemFerramenta.setParent(menupopupFerramenta);
	}

	private class EventListenerTipoKArtefato implements EventListener {
		public void onEvent(Event event) {
			CrtlTipoKArtefatoCRUD ctrlTipoKA = new CrtlTipoKArtefatoCRUD();
			ctrlTipoKA.iniciar();
		}
		
		@SuppressWarnings("unused")
		public boolean isAsap(){
			return true;
		}

	}
	
	private class EListenerProcessoPadrao implements EventListener{
		public void onEvent(Event event){
			CtrlDefinirProcessoPadraoCRUD ctrlDC = new CtrlDefinirProcessoPadraoCRUD();
			ctrlDC.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	/** Classe do evento do Menuitem KDominioAplicacao. */
	@SuppressWarnings("unused")
	private class EventListenerMenuItemProcessoPadrao implements
			EventListener {

		public void onEvent(Event event) {
			// sem controlador
			Window win = (Window) Executions.createComponents(
					"/visao/cadastros_gerais/windowDefinirCompPP.zul",
					getReference(), null);
			win.doOverlapped();
		}
		
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EventListenerMenuItemNovoProjeto implements
	EventListener {

	public void onEvent(Event event) {
		// sem controlador
		Window win = (Window) Executions.createComponents(
				"/visao/cadastros_gerais/windowNovoProjeto.zul",
				getReference(), null);
		win.doOverlapped();
	}
	
	public boolean isAsap() {
		return true;
	}
}
	
	private class EventListenerMenuItemDefinirProcesso implements
	EventListener {

		public void onEvent(Event event) {
			// sem controlador
			Window win = (Window) Executions.createComponents(
					"/visao/cadastros_gerais/windowDefinirProcesso.zul",
					getReference(), null);
			win.doOverlapped();
		}
		
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerKParadigmaComControlador implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			CtrlKParadigmaCRUD ctrlP = new CtrlKParadigmaCRUD();
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;// retorna se o cliente deve enviar a informacao o
			// quanto antes
		}
	}

	
	private class EListenerDominioConhecimento implements EventListener {
		public void onEvent(Event event) {

			CtrlDominioConhecimentoCRUD ctrlDC = new CtrlDominioConhecimentoCRUD();
			ctrlDC.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerKCategoriaProcesso implements EventListener {
		public void onEvent(Event event) {

			CtrlCategoriaProcessoCRUD ctrl = new CtrlCategoriaProcessoCRUD();
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerKProcesso implements EventListener {
		public void onEvent(Event event) {

			CtrlKProcessoCRUD ctrl = new CtrlKProcessoCRUD();
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EventListenerKArtefato implements EventListener {
		public void onEvent(Event event) {
			CtrlKArtefatoCRUD ctrlKA = new CtrlKArtefatoCRUD();
			ctrlKA.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do Menu Organizacao. */
	@SuppressWarnings("unused")
	private class EListenerKDominioAplicacaoComControlador implements
			EventListener {

		public void onEvent(Event event) {
			// Com controlador
			CtrlKDominioAplicacaoCRUD ctrlO = new CtrlKDominioAplicacaoCRUD();
			ctrlO.iniciar();

		}

		public boolean isAsap() {
			return true;
		}
	}


	private class EListenerPessoaComControlador implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			ControladorPrincipal ctrlP = (ControladorPrincipal) SpringUtil.getBean("ControladorPrincipal");
			ctrlP.exibirControlador();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;// retorna se o cliente deve enviar a informacao o
			// quanto antes
		}
	}

	private class EListenerTipoSoftware implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			CtrlTipoSoftwareCRUD ctrlP = new CtrlTipoSoftwareCRUD();
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerFerramentaSoftware implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			CtrlFerramentaSoftwareCRUD ctrlP = new CtrlFerramentaSoftwareCRUD();
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerRecursoHumano implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			CtrlKRecursoHumanoCRUD ctrlP = new CtrlKRecursoHumanoCRUD();
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerRecursoHardware implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			CtrlKRecursoHardwareCRUD ctrlP = new CtrlKRecursoHardwareCRUD();
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerKAtividade implements EventListener {
		public void onEvent(Event event) {

			CtrlKAtividadeCRUD ctrl = new CtrlKAtividadeCRUD();
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	private class EListenerKTipoRequisito implements EventListener {
		public void onEvent(Event event) {
			CtrlKTipoRequisitoCRUD ctrl = (CtrlKTipoRequisitoCRUD) SpringUtil.getBean(CtrlKTipoRequisitoCRUD.NOME);
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}
	
	
	private class EListenerProcedimento implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			CtrlKProcedimentoCRUD ctrlP = new CtrlKProcedimentoCRUD();
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

/** Classe do evento do idioma inglï¿½s. */
	private class EventListenerIdiomaIngles implements EventListener {

		public void onEvent(Event event) {

			NucleoContexto.atribuirLocale(Locale.US);

			atualizaBarraInformacoes();
			atualizaBarraMenu();
			try {
				Messagebox.show(NucleoMensagens
						.getMensagem(NucleoMensagens.MSG_IDIOMA_ALTERADO));
			} catch (Exception e) {
				// TODO: Pensar como tratar essas exceï¿½ï¿½es
			}

		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do idioma portuguï¿½s. */
	private class EventListenerIdiomaPortugues implements EventListener {

		public void onEvent(Event event) {

			Locale localePtBr = new Locale("pt", "BR");
			NucleoContexto.atribuirLocale(localePtBr);

			atualizaBarraInformacoes();
			atualizaBarraMenu();
			try {
				Messagebox.show(NucleoMensagens
						.getMensagem(NucleoMensagens.MSG_IDIOMA_ALTERADO));
			} catch (Exception e) {
				// TODO: Pensar como tratar essas exceï¿½ï¿½es
			}

		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	
	}
	
	// /////////////////////////////
	// Menu Pessoas
	// ////////////////////////////
	/** Classe do evento do Menuitem Pessoa. */

	/*
	 * private class EventListenerMenuItemPessoa implements EventListener {
	 * 
	 * public void onEvent(Event event) { Window win = (Window)
	 * Executions.createComponents(
	 * "/visao/exemplo/windowCadastroListaPessoaExemplo.zul", getReference(),
	 * null); win.doOverlapped(); }
	 * 
	 * public boolean isAsap() { return true; } }
	 */

	Menubar menuBar;

	// /////////////////////////////
	// Menu Arquivo
	// ////////////////////////////
	NucleoMenu menuArquivo;

	Menupopup menupopupArquivo;

	Menuitem menuitemSair;

	// /////////////////////////////
	// Menu Pessoa
	// ////////////////////////////
	NucleoMenu menuPessoa;

	Menupopup menupopupPessoa;

	Menuitem menuitemPessoa;

	// /////////////////////////////
	// Menu Organizacao
	// ////////////////////////////
	NucleoMenu menuOrganizacao;

	Menupopup menupopupOrganizacao;
	

	// Sub-menu Cadastro de Recursos:
	NucleoMenu menuCadastroRecurso;

	Menupopup menupopupCadastroRecurso;
	
	Menuitem menuitemFerramentaSoftware;
	
	Menuitem menuitemRecursoHumano;
	
	Menuitem menuitemRecursoHardware;
	
	// Sub-menu Cadastro de Conhecimento:
	NucleoMenu menuCadastroConhecimento;

	Menupopup menupopupCadastroConhecimento;
	
	Menupopup menupopupCadastroProcesso;
	
	//Menupopup menupopupKAtividade;
	
	// Sub-Menu Cadastro de Processo

	NucleoMenu menuCadastroProcesso;
	
	Menuitem menuitemCadastroProcesso;
	
	//Sub-Sub-Menu Caracteristicas Gerais
	
	NucleoMenu menuCaracteristicasGerais;
	
	Menupopup menupopupCaracteristicasGerais;
	
	Menuitem menuitemTipoSoftware;
	
	Menuitem menuitemDominioAplicacao;
	
	Menuitem menuitemParadigma;
	
	//Sub-Sub-Menu Processo
	
	NucleoMenu menuProcesso;
	
	Menupopup menupopupProcesso;
	
	Menuitem menuitemCategoriaProcesso;
	
	Menuitem menuitemKProcesso;
	
	// Requisitos
	NucleoMenu menuRequisitos;
	Menupopup menupopupRequisitos;
	Menuitem menuitemTipoRequisito;
	
	Menuitem menuitemMCV;
	
	//Sub-Sub-Menu Ativos de Processo
	
	NucleoMenu menuAtivosProcesso;
	
	Menupopup menupopupAtivosProcesso;

	Menuitem menuitemTipoKArtefato;
	
	Menuitem menuitemKArtefato;
	
	Menuitem menuitemRecurso;
	
	Menuitem menuitemProcedimento;
	
	Menuitem menuitemKAtividade;
	
	Menuitem menuitemOrganizacao;
	

	// /////////////////////////////
	// Menu Processo Padrao
	// ////////////////////////////
	NucleoMenu menuProcessoPadrao;

	Menupopup menupopupProcessoPadrao;

	Menuitem menuitemCompPP;
	
	Menuitem menuitemEstabelecerRequisitos;
	
	Menuitem menuitemSelecionarCompPPBase;
	
	// Sub-menu Novo
	NucleoMenu menuNovo;
	
	Menupopup menupopupNovo;
	
	Menuitem menuitemProcessoPadrao;
	
	Menuitem menuitemProcessoEspecializado;
	
	// Sub-menu Abrir
	NucleoMenu menuAbrir;
	
	Menupopup menupopupAbrir;
	
	Menuitem menuitemFinalizarDefinicao;
	
	Menuitem menuitemDefinirMCV;
	
	
	// /////////////////////////////
	// Menu Projeto
	// ////////////////////////////
	NucleoMenu menuProjeto;

	Menupopup menupopupProjeto;

	Menuitem menuitemNovo;
	
	Menuitem menuitemAbrir;
	
	// /////////////////////////////
	// Menu Ferramenta
	// ////////////////////////////
	NucleoMenu menuFerramenta;

	Menupopup menupopupFerramenta;

	Menuitem menuitemFerramenta;
	
	
}

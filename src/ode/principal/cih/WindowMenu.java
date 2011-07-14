package ode.principal.cih;

import java.util.Locale;
import java.util.Set;

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
import ode.controleProcesso.cci.CtrlCRUDRecursoHumano;
import ode.controleProjeto.cci.CtrlProjetoCRUD;
import ode.controleProjeto.cci.CtrlSelecionarProjeto;
import ode.controleProjeto.cdp.Projeto;
import ode.controleUsuario.cdp.GrantedAuthorityImpl;
import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.cih.NucleoMenu;
import ode.nucleo.util.NucleoContexto;
import ode.nucleo.util.NucleoMensagens;
import ode.processoPadrao.Cci.CtrlDefinirProcessoPadraoCRUD;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbarbutton;
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

		// Não utilizado
		// Adiciona o event listener para as bandeiras
		Toolbarbutton toolbarbuttonIdiomaIngles = (Toolbarbutton) getReference().getPage().getFellow(
		"idiomaIngles");
		toolbarbuttonIdiomaIngles.addEventListener("onClick",
				new EventListenerIdiomaIngles());

		Toolbarbutton toolbarbuttonIdiomaPortugues = (Toolbarbutton) getReference().getPage().getFellow(
		"idiomaPortugues");
		toolbarbuttonIdiomaPortugues.addEventListener("onClick",
				new EventListenerIdiomaPortugues());

		// Monta a interface
		this.montarInterfaceGrafica();
		this.atualizaBarraInformacoes();
		this.atualizarPermissoesAcesso();

	}

	public void atualizaBarraInformacoes() {
		
		// Atualiza o label nomeProjeto da pagina principal.zul
		Label labelNomeProjeto = (Label) getReference().getPage().getFellow(
		"nomeProjeto");
		
		Projeto projetoContexto = NucleoContexto.recuperarProjeto();
		
		labelNomeProjeto.setValue(projetoContexto == null ? "Projeto: Nenhum projeto selecionado!" : "Projeto: " + projetoContexto.getNome());

		// Atualiza o label nomeUsuario da pagina principal.zul
		Label labelNomeUsuario = (Label) getReference().getPage().getFellow(
		"nomeUsuario");

		NucleoUserDetails usuario = NucleoContexto.recuperarUsuarioLogado();
		String nomeUsuario;

		nomeUsuario = (usuario == null) ? NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_USUARIO_NAO_LOGADO)
				: NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO)
				+ ": " + usuario.getUsername();

				labelNomeUsuario.setValue(nomeUsuario);

				//Atualiza o label e o combo de idioma
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
		menuRecursoHumano.setLabel("Recursos");

		menuitemPessoa.setLabel("Recursos Humanos");


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
		menuControleProjetos.setLabel("Projetos");

		menuitemProjetos.setLabel("Projetos");

		menuitemSelecionarProjeto.setLabel("Selecionar Projeto");

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
		Set<GrantedAuthorityImpl> autoridadesUsuario = NucleoContexto
		.recuperarUsuarioLogado().getGrantedAuthorities();
		for (GrantedAuthorityImpl autoridade : autoridadesUsuario) {
			if (autoridade.getAuthority().equals(
					GrantedAuthorityImpl.AUTHORITY_ADMINISTRADOR)) {
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

		menuRecursoHumano.setVisible(admin);

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

		menuControleProjetos.setVisible(admin);

		menuitemProjetos.setVisible(admin);

		menuitemSelecionarProjeto.setVisible(admin);

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
		// Menu Controle de Projetos
		// ////////////////////////////
		menuControleProjetos = new NucleoMenu("Projetos");
		menuControleProjetos.setParent(menuBar);
		menupopupProjeto = new Menupopup();
		menupopupProjeto.setParent(menuControleProjetos);
		menuitemProjetos = new Menuitem("Projetos");
		menuitemProjetos.addEventListener("onClick",
				new EventListenerMenuItemProjetos());
		menuitemProjetos.setParent(menupopupProjeto);
		menuitemSelecionarProjeto = new Menuitem("Selecionar Projeto");
		menuitemSelecionarProjeto.setParent(menupopupProjeto);
		menuitemSelecionarProjeto.addEventListener("onClick",
				new EventListenerMenuItemSelecionarProjetos());

		// /////////////////////////////
		// Menu Pessoa
		// ////////////////////////////
		menuRecursoHumano = new NucleoMenu("Recursos");
		menuRecursoHumano.setParent(menuBar);
		menupopupPessoa = new Menupopup();
		menupopupPessoa.setParent(menuRecursoHumano);
		menuitemPessoa = new Menuitem("Recursos Humanos");
		menuitemPessoa.addEventListener("onClick",new
				EventListenerMenuItemPessoa());
		//menuitemPessoa.addEventListener("onClick",
		//new EListenerPessoaComControlador());
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
			
			CrtlTipoKArtefatoCRUD ctrlTipoKA = (CrtlTipoKArtefatoCRUD) SpringUtil.getApplicationContext().getBean(CrtlTipoKArtefatoCRUD.class);
			ctrlTipoKA.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap(){
			return true;
		}

	}

	private class EListenerProcessoPadrao implements EventListener{
		public void onEvent(Event event){
			
			CtrlDefinirProcessoPadraoCRUD ctrl = (CtrlDefinirProcessoPadraoCRUD) SpringUtil.getApplicationContext().getBean(CtrlDefinirProcessoPadraoCRUD.class);
			ctrl.iniciar();
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
			CtrlDefinirProcessoPadraoCRUD ctrlDC = (CtrlDefinirProcessoPadraoCRUD) SpringUtil.getApplicationContext().getBean(CtrlDefinirProcessoPadraoCRUD.class);
			ctrlDC.iniciar();
		}

		public boolean isAsap() {
			return true;
		}
	}

	private class EventListenerMenuItemProjetos implements
	EventListener {

		public void onEvent(Event event) {
			CtrlProjetoCRUD ctrlProjetoCRUD = (CtrlProjetoCRUD) SpringUtil.getApplicationContext().getBean(CtrlProjetoCRUD.class);
			ctrlProjetoCRUD.iniciar();
		}

		public boolean isAsap() {
			return true;
		}
	}
	
	private class EventListenerMenuItemSelecionarProjetos implements
	EventListener {

		public void onEvent(Event event) {
			CtrlSelecionarProjeto ctrlSelecionarProjeto = (CtrlSelecionarProjeto) SpringUtil.getApplicationContext().getBean(CtrlSelecionarProjeto.class);
			ctrlSelecionarProjeto.iniciar(WindowMenu.this);
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

			CtrlKParadigmaCRUD ctrlP = (CtrlKParadigmaCRUD) SpringUtil.getApplicationContext().getBean(CtrlKParadigmaCRUD.class);
			
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

			//CtrlDominioConhecimentoCRUD ctrlDC = new CtrlDominioConhecimentoCRUD();
			CtrlDominioConhecimentoCRUD ctrlDC = (CtrlDominioConhecimentoCRUD) SpringUtil.getApplicationContext().getBean(CtrlDominioConhecimentoCRUD.class);
			
			ctrlDC.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EListenerKCategoriaProcesso implements EventListener {
		public void onEvent(Event event) {

		//	CtrlCategoriaProcessoCRUD ctrl = new CtrlCategoriaProcessoCRUD();
			CtrlCategoriaProcessoCRUD ctrl = (CtrlCategoriaProcessoCRUD) SpringUtil.getApplicationContext().getBean(CtrlCategoriaProcessoCRUD.class);
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EListenerKProcesso implements EventListener {
		public void onEvent(Event event) {

			CtrlKProcessoCRUD ctrl = (CtrlKProcessoCRUD) SpringUtil.getApplicationContext().getBean(CtrlKProcessoCRUD.class);
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EventListenerKArtefato implements EventListener {
		public void onEvent(Event event) {
			
			CtrlKArtefatoCRUD ctrlKA = (CtrlKArtefatoCRUD) SpringUtil.getApplicationContext().getBean(CtrlKArtefatoCRUD.class);
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
			CtrlKDominioAplicacaoCRUD ctrlO = (CtrlKDominioAplicacaoCRUD) SpringUtil.getApplicationContext().getBean(CtrlKDominioAplicacaoCRUD.class);
			ctrlO.iniciar();

		}

		public boolean isAsap() {
			return true;
		}
	}

	private class EListenerTipoSoftware implements EventListener {

		public void onEvent(Event event) {
			CtrlTipoSoftwareCRUD ctrlP = (CtrlTipoSoftwareCRUD) SpringUtil.getApplicationContext().getBean(CtrlTipoSoftwareCRUD.class);
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EListenerFerramentaSoftware implements EventListener {

		public void onEvent(Event event) {

			CtrlFerramentaSoftwareCRUD ctrl = (CtrlFerramentaSoftwareCRUD) SpringUtil.getApplicationContext().getBean(CtrlFerramentaSoftwareCRUD.class);			
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EListenerRecursoHumano implements EventListener {

		public void onEvent(Event event) {
			// COm controlador
			CtrlKRecursoHumanoCRUD ctrlP = (CtrlKRecursoHumanoCRUD) SpringUtil.getApplicationContext().getBean(CtrlKRecursoHumanoCRUD.class);
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
//			CtrlKRecursoHardwareCRUD ctrlP = new CtrlKRecursoHardwareCRUD();
			CtrlKRecursoHardwareCRUD ctrl = (CtrlKRecursoHardwareCRUD) SpringUtil.getApplicationContext().getBean(CtrlKRecursoHardwareCRUD.class);
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EListenerKAtividade implements EventListener {
		public void onEvent(Event event) {

		//	CtrlKAtividadeCRUD ctrl = new CtrlKAtividadeCRUD();
			CtrlKAtividadeCRUD ctrl = (CtrlKAtividadeCRUD) SpringUtil.getApplicationContext().getBean(CtrlKAtividadeCRUD.class);
			ctrl.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	private class EListenerKTipoRequisito implements EventListener {
		public void onEvent(Event event) {
			CtrlKTipoRequisitoCRUD ctrl = (CtrlKTipoRequisitoCRUD) SpringUtil.getApplicationContext().getBean(CtrlKTipoRequisitoCRUD.class);
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
			
			CtrlKProcedimentoCRUD ctrlP = (CtrlKProcedimentoCRUD) SpringUtil.getApplicationContext().getBean(CtrlKProcedimentoCRUD.class);
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do idioma inglês. */
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


	private class EventListenerMenuItemPessoa implements EventListener {

		public void onEvent(Event event) { 

			CtrlCRUDRecursoHumano ctrlCRUDRecursoHumano = (CtrlCRUDRecursoHumano) SpringUtil.getBean(CtrlCRUDRecursoHumano.NOME);
			ctrlCRUDRecursoHumano.iniciar();

		}

		public boolean isAsap() { return true; } }

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
	NucleoMenu menuRecursoHumano;

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
	// Menu Controle de Projetos
	// ////////////////////////////
	NucleoMenu menuControleProjetos;

	Menupopup menupopupProjeto;

	Menuitem menuitemProjetos;

	Menuitem menuitemSelecionarProjeto;

	// /////////////////////////////
	// Menu Ferramenta
	// ////////////////////////////
	NucleoMenu menuFerramenta;

	Menupopup menupopupFerramenta;

	Menuitem menuitemFerramenta;


}

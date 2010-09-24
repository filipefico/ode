package nucleo.global.visao;

import java.util.Locale;
import java.util.Set;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoGrantedAuthority;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;

import nucleo.comuns.util.NucleoContexto;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoMenu;

import ode.conhecimento.processo.Cci.CrtlTipoKArtefatoCRUD;
import ode.conhecimento.processo.Cci.CtrlKArtefatoCRUD;
import ode.conhecimento.processo.Cci.CtrlKDominioAplicacaoCRUD;
import ode.conhecimento.organizacao.Cci.CtrlDominioConhecimentoCRUD;
import ode.processoPadrao.Cci.CtrlDefinirProcessoPadraoCRUD;

import ode.exemplo2.pessoa.Cci.CtrlPessoaCRUD;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 * Classe respons�vel pela exibi��o de um menu de uma janela.
 * 
 * @author Alexandre G. N. Coelho
 */
public class WindowMenu extends Window {

	private static final long serialVersionUID = -1857633860305556039L;

	public WindowMenu() {
	}

	public void onCreate() {

		// Configura e monta a interface gr�fica
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
		// Atualiza o label de informa��es de usu�rio e projeto
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

		menuCadastroConhecimento.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_CONHECIMENTO));

		menuitemOrganizacao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO));

		menuProcesso.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO));

		menuitemParadigma.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PARADIGMA));

		menuitemTipoSoftware.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_SOFTWARE));

		menuitemDominioAplicacao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DOMINIO_DA_APLICACAO));

		menuitemKArtefato.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_K_ARTEFATO));

		menuitemTipoKArtefato.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_K_ARTEFATO));

		menuProcessoPadrao.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_PADRAO));
		
		menuitemCompPP.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_COMPPP));

	}

	public void atualizarPermissoesAcesso() {

		// Atualiza a visibilidade dos menus e interfaces
		@SuppressWarnings("unused")
		boolean visivel = true;

		// Atualiza menus administrativos baseado na autoridade do usu�rio
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

		menuitemDominioAplicacao.setVisible(admin);

		menuCadastroConhecimento.setVisible(admin);

		menuitemOrganizacao.setVisible(admin);

		menuProcesso.setVisible(admin);

		menuitemParadigma.setVisible(admin);

		menuitemTipoSoftware.setVisible(admin);

		menuitemDominioAplicacao.setVisible(admin);

		menuitemTipoKArtefato.setVisible(admin);

		menuitemKArtefato.setVisible(admin);

		menuProcessoPadrao.setVisible(admin);
		
		menuitemCompPP.setVisible(admin);
		
	}

	/** Evento executado ao fechar a window */
	public void onClose() {
		this.detach();
	}

	private WindowMenu getReference() {
		return this;
	}

	/** Agrupa e configura componentes de interface gr�fica. */
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
		// Sub-menu Processo
		menuProcesso = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO));
		menuProcesso.setParent(menupopupCadastroConhecimento);
		menupopupProcesso = new Menupopup();
		menupopupProcesso.setParent(menuProcesso);

		menuitemParadigma = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PARADIGMA));
		menuitemParadigma.setParent(menupopupProcesso);

		menuitemTipoSoftware = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_SOFTWARE));
		menuitemTipoSoftware.addEventListener("onClick",
				new EListenerTipoSoftware());
		menuitemTipoSoftware.setParent(menupopupProcesso);

		menuitemTipoKArtefato = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_K_ARTEFATO));
		menuitemTipoKArtefato.setParent(menupopupProcesso);
		menuitemTipoKArtefato.addEventListener("onClick",
				new EventListenerTipoKArtefato());
		// menuitemPessoa.addEventListener("onClick",new
		// EventListenerTipoArtefato());
		menuitemKArtefato = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_K_ARTEFATO));
		menuitemKArtefato.setParent(menupopupProcesso);

		menuitemKArtefato.addEventListener("onClick",new EventListenerKArtefato());
		menuitemKArtefato.addEventListener("onClick",new EventListenerKArtefato());


		menuitemDominioAplicacao = new Menuitem(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DOMINIO_DA_APLICACAO));
		menuitemDominioAplicacao.addEventListener("onClick",new EListenerKDominioAplicacaoComControlador());

		// Sub-menu Dominio da Aplica��o

		menuitemDominioAplicacao = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DOMINIO_DA_APLICACAO));
		 menuitemDominioAplicacao.addEventListener("onClick",new EListenerKDominioAplicacaoComControlador());
		menuitemDominioAplicacao.setParent(menupopupProcesso);

		// Sub-menu Processo Padrao
		menuProcessoPadrao = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PROCESSO_PADRAO));
		menuProcessoPadrao.setParent(menupopupCadastroConhecimento);
		menupopupProcessoPadrao = new Menupopup();
		menupopupProcessoPadrao.setParent(menuProcessoPadrao);
		
		menuitemCompPP = new Menuitem(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_COMPPP));
		menuitemCompPP.addEventListener("onClick", new EListenerProcessoPadrao());
		//menuitemCompPP.addEventListener("onClick", new EventListenerMenuItemProcessoPadrao());
		menuitemCompPP.setParent(menupopupProcessoPadrao);

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
	private class EventListenerMenuItemProcessoPadrao implements
			EventListener {

		public void onEvent(Event event) {
			// sem controlador
			Window win = (Window) Executions.createComponents(
					"/visao/cadastros_gerais/windowCadastroListaProcessoPadrao.zul",
					getReference(), null);
			win.doOverlapped();
		}
		
		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
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
			CtrlPessoaCRUD ctrlP = new CtrlPessoaCRUD();
			ctrlP.iniciar();
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
			CtrlPessoaCRUD ctrlP = new CtrlPessoaCRUD();
			ctrlP.iniciar();
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do idioma ingl�s. */
	private class EventListenerIdiomaIngles implements EventListener {

		public void onEvent(Event event) {

			NucleoContexto.atribuirLocale(Locale.US);

			atualizaBarraInformacoes();
			atualizaBarraMenu();
			try {
				Messagebox.show(NucleoMensagens
						.getMensagem(NucleoMensagens.MSG_IDIOMA_ALTERADO));
			} catch (Exception e) {
				// TODO: Pensar como tratar essas exce��es
			}

		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do idioma portugu�s. */
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
				// TODO: Pensar como tratar essas exce��es
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

	Menuitem menuitemCadastroConhecimento;

	// Sub-menu Cadastro de Conhecimento:
	NucleoMenu menuCadastroConhecimento;

	Menupopup menupopupCadastroConhecimento;

	Menuitem menuitemProcesso;

	Menuitem menuitemOrganizacao;

	// Sub-menu Processo:
	NucleoMenu menuProcesso;

	Menupopup menupopupProcesso;

	Menuitem menuitemParadigma;

	Menuitem menuitemTipoSoftware;

	Menuitem menuitemTipoKArtefato;

	Menuitem menuitemKArtefato;

	Menuitem menuitemDominioAplicacao;

	// Sub-menu Processo Padrao:
	NucleoMenu menuProcessoPadrao;
	
	Menupopup menupopupProcessoPadrao;

	Menuitem menuitemCompPP;	
}

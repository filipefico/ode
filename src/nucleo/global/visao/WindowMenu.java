package nucleo.global.visao;

import java.util.Locale;
import java.util.Set;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoGrantedAuthority;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.autenticacao.visao.WindowCadastroDadosNucleoUsuario;
import nucleo.comuns.autenticacao.visao.WindowCadastroListaNucleoUsuario;
import nucleo.comuns.util.NucleoContexto;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoMenu;

import ode.exemplo.visao.WindowCadastroListaPessoaExemplo;

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
 * Classe responsável pela exibição de um menu de uma janela.
 * 
 * @author Alexandre G. N. Coelho
 */
public class WindowMenu extends Window {

	private static final long serialVersionUID = -1857633860305556039L;

	public WindowMenu() {
	}

	public void onCreate() {

		// Configura e monta a interface gráfica
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
		// Atualiza o label de informações de usuário e projeto
		Label lblInformacoes = (Label) getReference().getPage().getFellow(
				"lblInformacoes");
		NucleoUserDetails usuario = NucleoContexto.recuperarUsuarioLogado();
		String nomeUsuario;
		String nomeProjeto;

		nomeUsuario = (usuario == null) ? NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_USUARIO_NAO_LOGADO)
				: NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO) + ": "
						+ usuario.getUsername();

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
		// Menu Administração
		// ////////////////////////////
		menuAdmin.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ADMINISTRACAO));

		menuitemPessoas.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOAS));

		menuitemUsuarios.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_USUARIOS));
		
		// /////////////////////////////
		// Menu Pessoas
		// ////////////////////////////
		menuPessoa.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOA));

		menuitemPessoa.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOAS));
		
	}

	public void atualizarPermissoesAcesso() {

		// Atualiza a visibilidade dos menu's e interfaces
		boolean visivel = true;

		// Atualiza menus administrativos baseado na autoridade do usuário
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
		// Menu Administração
		// ////////////////////////////

		menuAdmin.setVisible(admin);

		menuitemPessoas.setVisible(admin);

		menuitemUsuarios.setVisible(admin);
		
		
		// /////////////////////////////
		// Menu Pessoas
		// ////////////////////////////

		menuPessoa.setVisible(admin);

		menuitemPessoa.setVisible(admin);

	}

	/** Evento executado ao fechar a window */
	public void onClose() {
		this.detach();
	}

	private WindowMenu getReference() {
		return this;
	}

	/** Agrupa e configura componentes de interface gráfica. */
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
		// Menu Administração
		// ////////////////////////////
		menuAdmin = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ADMINISTRACAO));
		menuAdmin.setParent(menuBar);
		menupopupAdmin = new Menupopup();
		menupopupAdmin.setParent(menuAdmin);
		menuitemPessoas = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOAS));
		menuitemPessoas.addEventListener("onClick",
				new EventListenerMenuItemPessoas());
		// menuitemPessoas.setParent(menupopupAdmin);
		menuitemUsuarios = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CADASTRO_USUARIOS));
		menuitemUsuarios.addEventListener("onClick",
				new EventListenerMenuItemUsuarios());
		menuitemUsuarios.setParent(menupopupAdmin);
		
		// /////////////////////////////
		// Menu Pessoa
		// ////////////////////////////
		menuPessoa = new NucleoMenu(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOA));
		menuPessoa.setParent(menuBar);
		menupopupPessoa = new Menupopup();
		menupopupPessoa.setParent(menuPessoa);
		menuitemPessoa = new Menuitem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_PESSOAS));
		menuitemPessoa.addEventListener("onClick",ListenerMenu.factory(getReference(),WindowCadastroListaPessoaExemplo.CAMINHO));
		 menuitemPessoa.setParent(menupopupPessoa);
	}

	// /////////////////////////////
	// Menu Administração
	// ////////////////////////////
	/** Classe do evento do Menuitem Pessoas. */
	private class EventListenerMenuItemPessoas implements EventListener {

		public void onEvent(Event event) {
			Window win = (Window) Executions.createComponents(
					"/visao/admin/windowCadastroListaPessoa.zul",
					getReference(), null);
			win.doOverlapped();
		}

		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do Menuitem Usuários. */
	private class EventListenerMenuItemUsuarios implements EventListener {

		public void onEvent(Event event) {
			Window win = (Window) Executions.createComponents(
					"/visao/admin/windowCadastroListaNucleoUsuario.zul",
					getReference(), null);
			
			
			win.doOverlapped();
		
			
		}

		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do Menuitem Usuários. */
	private class EventListenerMenuItemConfiguracaoEmail implements
			EventListener {

		public void onEvent(Event event) {
			Window win = (Window) Executions.createComponents(
					"/visao/admin/windowCadastroDadosConfiguracaoEmail.zul",
					getReference(), null);
			win.doOverlapped();
		}

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
				// TODO: Pensar como tratar essas exceções
			}

		}

		public boolean isAsap() {
			return true;
		}
	}

	/** Classe do evento do idioma português. */
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
				// TODO: Pensar como tratar essas exceções
			}

		}

		public boolean isAsap() {
			return true;
		}
	}
	
	// /////////////////////////////
	// Menu Pessoas
	// ////////////////////////////
	/** Classe do evento do Menuitem Pessoa. */
	
	/*
	private class EventListenerMenuItemPessoa implements EventListener {

		public void onEvent(Event event) {
			Window win = (Window) Executions.createComponents(
					"/visao/exemplo/windowCadastroListaPessoaExemplo.zul",
					getReference(), null);
			win.doOverlapped();
		}

		public boolean isAsap() {
			return true;
		}
	}
	*/

	Menubar menuBar;

	// /////////////////////////////
	// Menu Arquivo
	// ////////////////////////////
	NucleoMenu menuArquivo;

	Menupopup menupopupArquivo;

	Menuitem menuitemSair;
	
	// /////////////////////////////
	// Menu Administração
	// ////////////////////////////
	NucleoMenu menuAdmin;

	Menupopup menupopupAdmin;

	Menuitem menuitemPessoas;

	Menuitem menuitemUsuarios;

	
	// /////////////////////////////
	// Menu Pessoa
	// ////////////////////////////
	NucleoMenu menuPessoa;

	Menupopup menupopupPessoa;

	Menuitem menuitemPessoa;

}

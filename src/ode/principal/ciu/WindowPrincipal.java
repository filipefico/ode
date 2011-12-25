package ode.principal.ciu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.FuncionalidadesMenu;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode.agenda.ciu.CtrlAgenda;
import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cdp.PerfilAcesso;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.api.XulElement;

/**
 * Classe responsável pela exibição da página principal da aplicação.
 * 
 * @author Alexandre G. N. Coelho.
 */
public class WindowPrincipal extends Window {

	private static final long serialVersionUID = -1857633860305556039L;
	
	/**
	 * Label idiomas.
	 */
	private Label labelIdiomas;

	/**
	 * Label com o nome do projeto atual selecionado.
	 */
	private Label labelProjeto;

	/**
	 * Label com as informações sobre o usuário atual logado.
	 */
	private Label labelUsuario;
	
	/**
	 * Menubar.
	 */
	private Menubar menubar;
	
	// Obtém o perfil do usuário autenticado
	private PerfilAcesso perfilUsuario = NucleoContexto.recuperarUsuarioLogado().getPerfilAcesso();

	public void onCreate() throws InterruptedException {

		// Atribui a janela principal ao context
		NucleoContexto.atribuirJanelaPrincipal(this);

		// Monta a página principal
		this.MontarPagina();

	}

	/**
	 * Monta a página principal da aplicação contendo o menu com as funcionalidades.
	 */
	private void MontarPagina(){
		this.adicionarCabecalhoPrincipal();
		this.adicionarFuncionalidadesDisponiveisUsuario();
		
		abrirJanela(CtrlAgenda.class.getCanonicalName(),"iniciarAutomaticamente");
	}


	/**
	 * Adiciona o cabecalho da página principal
	 */
	private void adicionarCabecalhoPrincipal(){

		Div divCabecalho = new Div();
		divCabecalho.setParent(this);
		divCabecalho.setHeight("25px");
		divCabecalho.setStyle("background-image: url('imagens/wnd-hm.png');background-repeat: repeat-x;");

		Div divLadoEsquerdo = new Div();
		divLadoEsquerdo.setParent(divCabecalho);
		divLadoEsquerdo.setStyle("float:left;");

		Hbox hboxLadoEsquerdo = new Hbox();
		hboxLadoEsquerdo.setParent(divLadoEsquerdo);
		hboxLadoEsquerdo.setAlign("middle");
		hboxLadoEsquerdo.setHeight("25px");

		// Adiciona o nome do sistema
		Label labelNomeSistema = new Label("ODE - Ontology Development Environment");
		labelNomeSistema.setParent(hboxLadoEsquerdo);
		labelNomeSistema.setStyle("font-weight:bold; color: black; margin-left: 5px");

		// Adiciona o nome da versão
		Label labelNomeVersao = new Label(" - Versão 1.0");
		labelNomeVersao.setParent(hboxLadoEsquerdo);
		labelNomeVersao.setStyle("color: black;");

		Div divLadoDireito = new Div();
		divLadoDireito.setParent(divCabecalho);
		divLadoDireito.setStyle("valign: middle; float:right; margin-right: 5px;");//

		Hbox hboxLadoDireito = new Hbox();
		hboxLadoDireito.setParent(divLadoDireito);
		hboxLadoDireito.setAlign("middle");
		hboxLadoDireito.setHeight("25px");

		// Adiciona label e toolbarbuttons dos idiomas
		labelIdiomas = new Label();
		labelIdiomas.setParent(hboxLadoDireito);

		Toolbarbutton toolbarbuttonPortuguesBR = new Toolbarbutton();
		toolbarbuttonPortuguesBR.setTooltiptext("Português - Brasil");
		toolbarbuttonPortuguesBR.setParent(hboxLadoDireito);
		toolbarbuttonPortuguesBR.setImage("/imagens/idiomaPort.gif");
		toolbarbuttonPortuguesBR.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				NucleoContexto.atribuirLocale(new Locale("pt", "BR"));
				atualizarWindowPrincipal();
				Messagebox.show(NucleoMensagens.getMensagem(NucleoMensagens.MSG_IDIOMA_ALTERADO));
			}
		});

		Toolbarbutton toolbarbuttonEnglish = new Toolbarbutton();
		toolbarbuttonEnglish.setTooltiptext("English");
		toolbarbuttonEnglish.setParent(hboxLadoDireito);
		toolbarbuttonEnglish.setImage("/imagens/idiomaIng.gif");
		toolbarbuttonEnglish.setStyle("margin-right: 50px;");
		toolbarbuttonEnglish.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				NucleoContexto.atribuirLocale(new Locale("en", "US"));
				atualizarWindowPrincipal();
				Messagebox.show(NucleoMensagens.getMensagem(NucleoMensagens.MSG_IDIOMA_ALTERADO));
			}
		});

		// Adiciona nome do projeto
		labelProjeto = new Label();
		labelProjeto.setParent(hboxLadoDireito);
		labelProjeto.setStyle("margin-right: 50px; color: black;");
		labelProjeto.setMaxlength(70);

		// Adiciona nome do usuário		
		labelUsuario = new Label();
		labelUsuario.setParent(hboxLadoDireito);
		labelUsuario.setMaxlength(70);

		atualizarBarraInformacoes();
	}

	/**
	 * Adiciona funcionalidades disponíveis para o usuário logado na sessão.
	 */
	private void adicionarFuncionalidadesDisponiveisUsuario(){

		// Cria o menu
		menubar = new Menubar();
		menubar.setParent(this);

		//////////////////////////////////////////////////////////////////////////
		// Adiciona as funcionalidades comuns a todos os usuários
		//////////////////////////////////////////////////////////////////////////

		// Adiciona o menu Opções
		Menu menuOpcoes = new Menu("Opções");
		menuOpcoes.setParent(menubar);
		Menupopup menupopupOpcoes = new Menupopup();
		menupopupOpcoes.setParent(menuOpcoes);

		// Adiciona menuitem Alterar Senha
		Menuitem menuitemAlterarSenha = new Menuitem("Alterar Senha");
		menuitemAlterarSenha.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				abrirJanela(ode.controleUsuario.ciu.CtrlAlterarSenha.class.getCanonicalName());
			}
		});
		menuitemAlterarSenha.setParent(menupopupOpcoes);

		// Adiciona separator
		Menuseparator menuseparator = new Menuseparator();
		menuseparator.setParent(menupopupOpcoes);

		// Adiciona menuitem Sair
		Menuitem menuitemSair = new Menuitem("Sair");
		menuitemSair.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				Executions.sendRedirect("/logout.zul");
			}
		});
		menuitemSair.setParent(menupopupOpcoes);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Adiciona as funcionalidades permitidas de acordo com o perfil do usuário logado e com o projeto aberto
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		this.adicionarSubFuncionalidades(null, menubar);
	}

	/**
	 * Adiciona as subfuncionalidades de um menu.
	 * @param funcionalidadePai Funcionalidade pai.
	 * @param pai Menu pai.
	 */
	
	private void adicionarSubFuncionalidades(Funcionalidade funcionalidadePai, XulElement pai){

		Collection<Funcionalidade> funcionalidades;
		
		if(funcionalidadePai == null) {
			funcionalidades = FuncionalidadesMenu.obterFuncionalidades();
		} else {
			Menupopup menupopup = new Menupopup();
			menupopup.setParent(pai);
			pai = menupopup;
			funcionalidades = funcionalidadePai.getSubfuncionalidades();
		}

		// Recupera o projeto
		

		for (final Funcionalidade funcionalidade : funcionalidades) {

			boolean enabledProjeto = NucleoContexto.recuperarProjeto() != null || !funcionalidade.isDisponivelApenasParaProjetosAbertos();
			// Verifica se funcionalidade está disponível para o usuário
			if (funcionalidade.permite(perfilUsuario)) {

				if (funcionalidade.getSubfuncionalidades().size() > 0 && enabledProjeto) {
					Menu menu = new Menu(funcionalidade.getNome());
					menu.setParent(pai);
					this.adicionarSubFuncionalidades(funcionalidade, menu);
				} else {
					Menuitem menuitem = new Menuitem(funcionalidade.getNome());
					menuitem.addEventListener("onClick", new EventListener() {
						public void onEvent(Event arg0) throws Exception {
							abrirJanela(funcionalidade.getCtrl());
						}
					});
					menuitem.setDisabled(!enabledProjeto);
					menuitem.setParent(pai);
				}

			}
		}

	}

	/**
	 * Inicia o controlador de uma aplicação.
	 * @param srcCtrl Src do controlador da aplicação.
	 */
	public void abrirJanela(String srcCtrl){
		abrirJanela(srcCtrl,null);
	}
	
	public void abrirJanela(String srcCtrl, String metodo) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("use", srcCtrl);
		CtrlBase ctrl = (CtrlBase) Executions.createComponents(
				"/paginas/principal/ctrl.zul",
				this, map);
		ctrl = (CtrlBase) SpringUtil.getApplicationContext().getBean(ctrl.getClass());
		if (metodo == null) {
			ctrl.iniciar();
		} else {
			try {
				ctrl.getClass().getMethod(metodo).invoke(ctrl, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** 
	 * Evento executado ao fechar a window. 
	 */
	public void atualizarBarraInformacoes() {
		this.labelIdiomas.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_IDIOMAS) + ":");
		this.labelProjeto.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROJETO) + ": " + (NucleoContexto.recuperarProjeto() == null ? NucleoMensagens.getMensagem(NucleoMensagens.MSG_NENHUM_PROJETO_SELECIONADO) : NucleoContexto.recuperarProjeto().getNome()));
		this.labelUsuario.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO) + ": " + NucleoContexto.recuperarUsuarioLogado().getRecursoHumano().getNome() + " - " + NucleoContexto.recuperarUsuarioLogado().getPerfilAcesso());
	}
	
	/**
	 * Atualiza Menubar.
	 */
	public void atualizarMenubar(){
		this.menubar.detach();
		this.adicionarFuncionalidadesDisponiveisUsuario();
	}
	
	public void atualizarWindowPrincipal(){
		this.atualizarBarraInformacoes();
		this.atualizarMenubar();
	}	
}


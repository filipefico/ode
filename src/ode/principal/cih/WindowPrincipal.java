package ode.principal.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cgt.AplCadastrarFuncionalidade;
import ode.controleUsuario.cgt.AplCadastrarPerfilAcesso;
import ode.nucleo.cci.CtrlBase;
import ode.nucleo.util.NucleoContexto;
import ode.nucleo.util.NucleoMensagens;

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

/**
 * Classe respons�vel pela exibi��o da p�gina principal da aplica��o.
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
	 * Label com as informa��es sobre o usu�rio atual logado.
	 */
	private Label labelUsuario;
	
	/**
	 * Menubar.
	 */
	private Menubar menubar;

	/**
	 *	AplCadastrarFuncionalidade. 
	 */
	private AplCadastrarFuncionalidade aplCadastrarFuncionalidade;

	/**
	 *	AplCadastrarPerfilAcesso. 
	 */
	private AplCadastrarPerfilAcesso aplCadastrarPerfilAcesso;

	/**
	 * Funcionalidades dispon�veis para o usu�rio autenticado.
	 */
	private List<Funcionalidade> funcionalidadesDisponiveisUsuario = new ArrayList<Funcionalidade>();

	public void onCreate() throws InterruptedException {

		// Atribui a janela principal ao context
		NucleoContexto.atribuirJanelaPrincipal(this);

		// Faz a inje��o das apl's da janela
		aplCadastrarFuncionalidade = (AplCadastrarFuncionalidade) SpringUtil.getBean("AplCadastrarFuncionalidade");

		aplCadastrarPerfilAcesso = (AplCadastrarPerfilAcesso) SpringUtil.getBean("AplCadastrarPerfilAcesso");

		// Monta a p�gina principal
		this.MontarPagina();

	}

	/**
	 * Monta a p�gina principal da aplica��o contendo o menu com as funcionalidades.
	 */
	private void MontarPagina(){

		this.adicionarCabecalhoPrincipal();
		this.adicionarFuncionalidadesDisponiveisUsuario();
	}

	/**
	 * Adiciona o cabecalho da p�gina principal
	 */
	private void adicionarCabecalhoPrincipal(){

		Div divCabecalho = new Div();
		divCabecalho.setParent(this);
		divCabecalho.setHeight("25px");
		divCabecalho.setStyle("background-image: url('../../imagens/wnd-hm.png');background-repeat: repeat-x;");

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

		// Adiciona o nome da vers�o
		Label labelNomeVersao = new Label(" - Vers�o 1.0");
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
		labelIdiomas = new Label(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_IDIOMAS) + ":");
		labelIdiomas.setParent(hboxLadoDireito);

		Toolbarbutton toolbarbuttonPortuguesBR = new Toolbarbutton();
		toolbarbuttonPortuguesBR.setTooltiptext("Portugu�s - Brasil");
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
		labelProjeto = new Label(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROJETO) + ": " + NucleoMensagens.getMensagem(NucleoMensagens.MSG_NENHUM_PROJETO_SELECIONADO));
		labelProjeto.setParent(hboxLadoDireito);
		labelProjeto.setStyle("margin-right: 50px; color: black;");
		labelProjeto.setMaxlength(70);

		// Recupera usu�rio logado na sess�o
		NucleoUserDetails usuario = NucleoContexto.recuperarUsuarioLogado();

		String nomeUsuario = usuario.getRecursoHumano().getNome();

		// Adiciona nome do usu�rio		
		labelUsuario = new Label(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO) + ": " + nomeUsuario + " - " + usuario.getPerfilAcesso().getNome());
		labelUsuario.setParent(hboxLadoDireito);
		labelUsuario.setMaxlength(70);

	}

	/**
	 * Adiciona funcionalidades dispon�veis para o usu�rio logado na sess�o.
	 */
	private void adicionarFuncionalidadesDisponiveisUsuario(){

		// Cria o menu
		menubar = new Menubar();
		menubar.setParent(this);

		//////////////////////////////////////////////////////////////////////////
		// Adiciona as funcionalidades comuns a todos os usu�rios
		//////////////////////////////////////////////////////////////////////////

		// Adiciona o menu Op��es
		Menu menuOpcoes = new Menu("Op��es");
		menuOpcoes.setParent(menubar);
		Menupopup menupopupOpcoes = new Menupopup();
		menupopupOpcoes.setParent(menuOpcoes);

		// Adiciona menuitem Alterar Senha
		Menuitem menuitemAlterarSenha = new Menuitem("Alterar Senha");
		menuitemAlterarSenha.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				abrirJanela("ode.controleUsuario.cci.AlterarSenhaCtrl");
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
		// Adiciona as funcionalidades permitidas de acordo com o perfil do usu�rio logado e com o projeto aberto
		//////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Obt�m o perfil do usu�rio autenticado
		PerfilAcesso grupoUsuario = aplCadastrarPerfilAcesso.recuperarPorId(NucleoContexto.recuperarUsuarioLogado().getPerfilAcesso().getId());

		// Obt�m as funcionalidades do perfil do usu�rio
		funcionalidadesDisponiveisUsuario.addAll(grupoUsuario.getFuncionalidadesPermitidas());

		// Recupera o projeto
		boolean existeProjetoAberto = NucleoContexto.recuperarProjeto() != null ? true : false;

		// Recupera do banco todas as funcionalidades ra�z
		Collection<Funcionalidade> funcionalidades = aplCadastrarFuncionalidade.recuperarFuncionalidadesRaiz();

		Iterator<Funcionalidade> i = funcionalidades.iterator();	

		while (i.hasNext()){

			final Funcionalidade funcionalidade = i.next();

			// Verifica se a funcionalidade est� dispon�vel para o usu�rio
			if (funcionalidadesDisponiveisUsuario.contains(funcionalidade)) {

				// Verifica se a funcionalidade est� dispon�vel de acordo com o projeto aberto
				if ((funcionalidade.isDisponivelApenasParaProjetosAbertos() && existeProjetoAberto) || !funcionalidade.isDisponivelApenasParaProjetosAbertos()) {

					if (funcionalidade.getSubfuncionalidades().size() > 0) {
						Menu menu = new Menu(funcionalidade.getNome());
						menu.setParent(menubar);
						this.adicionarSubFuncionalidades(funcionalidade, menu);
					} else {
						Menuitem menuitem = new Menuitem(funcionalidade.getNome());
						menuitem.setAttribute("srcJanela", funcionalidade.getSrcCtrl());
						menuitem.addEventListener("onClick", new EventListener() {
							public void onEvent(Event arg0) throws Exception {
								abrirJanela(funcionalidade.getSrcCtrl());
							}
						});
						menuitem.setParent(menubar);
					}

				}
			}

		}

	}

	/**
	 * Adiciona as subfuncionalidades de um menu.
	 * @param funcionalidadePai Funcionalidade pai.
	 * @param menuPai Menu pai.
	 */
	private void adicionarSubFuncionalidades(Funcionalidade funcionalidadePai, final Menu menuPai){

		// Recupera o projeto
		boolean existeProjetoAberto = NucleoContexto.recuperarProjeto() != null ? true : false;

		// Recupera funcionalidades
		Collection<Funcionalidade> funcionalidades = aplCadastrarFuncionalidade.recuperarSubFuncionalidadesPorFuncionalidade(funcionalidadePai);

		Iterator<Funcionalidade> i = funcionalidades.iterator();

		Menupopup menupopup = new Menupopup();
		menupopup.setParent(menuPai);

		while (i.hasNext()){

			final Funcionalidade funcionalidade = i.next();

			// Verifica se funcionalidade est� dispon�vel para o usu�rio
			if (funcionalidadesDisponiveisUsuario.contains(funcionalidade)) {

				// Verifica se a funcionalidade est� dispon�vel de acordo com o projeto aberto
				if ((funcionalidade.isDisponivelApenasParaProjetosAbertos() && existeProjetoAberto) || !funcionalidade.isDisponivelApenasParaProjetosAbertos()) {


					if (funcionalidade.getSubfuncionalidades().size() > 0) {
						Menu menu = new Menu(funcionalidade.getNome());
						menu.setParent(menupopup);
						this.adicionarSubFuncionalidades(funcionalidade, menu);
					} else {
						Menuitem menuitem = new Menuitem(funcionalidade.getNome());
						menuitem.setAttribute("srcJanela", funcionalidade.getSrcCtrl());
						menuitem.addEventListener("onClick", new EventListener() {
							public void onEvent(Event arg0) throws Exception {
								abrirJanela(funcionalidade.getSrcCtrl());
							}
						});
						menuitem.setParent(menupopup);
					}

				}
			}
		}

	}

	/**
	 * Inicia o controlador de uma aplica��o.
	 * @param srcCtrl Src do controlador da aplica��o.
	 */
	public void abrirJanela(String srcCtrl){
		Map<String,String> map = new HashMap<String,String>();
		map.put("use", srcCtrl);
		CtrlBase ctrl = (CtrlBase) Executions.createComponents(
				"/paginas/principal/ctrl.zul",
				this, map);
		ctrl = (CtrlBase) SpringUtil.getApplicationContext().getBean(ctrl.getClass());
		ctrl.iniciar();
	}

	/** 
	 * Evento executado ao fechar a window. 
	 */
	public void atualizarBarraInformacoes() {
		this.labelIdiomas.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_IDIOMAS) + ":");
		this.labelProjeto.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROJETO) + ": " + (NucleoContexto.recuperarProjeto() == null ? NucleoMensagens.getMensagem(NucleoMensagens.MSG_NENHUM_PROJETO_SELECIONADO) : NucleoContexto.recuperarProjeto().getNome()));
		this.labelUsuario.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO) + ": " + NucleoContexto.recuperarUsuarioLogado().getRecursoHumano().getNome() + " - " + NucleoContexto.recuperarUsuarioLogado().getPerfilAcesso().getNome());
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


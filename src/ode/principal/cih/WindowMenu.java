package ode.principal.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cgt.AplCadastrarFuncionalidade;
import ode.controleUsuario.cgt.AplCadastrarFuncionalidadeImpl;
import ode.controleUsuario.cgt.AplCadastrarPerfilAcesso;
import ode.controleUsuario.cgt.AplCadastrarPerfilAcessoImpl;
import ode.nucleo.cci.CtrlBase;
import ode.nucleo.cih.NucleoMenu;
import ode.nucleo.util.NucleoContexto;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 * Classe responsável pela exibição da página principal da aplicação.
 * 
 * @author Alexandre G. N. Coelho.
 */
public class WindowMenu extends Window {

	private static final long serialVersionUID = -1857633860305556039L;

	/**
	 *	AplCadastrarFuncionalidade. 
	 */
	private AplCadastrarFuncionalidade aplCadastrarFuncionalidade;

	/**
	 *	AplCadastrarPerfilAcesso. 
	 */
	private AplCadastrarPerfilAcesso aplCadastrarPerfilAcesso;

	/**
	 * Funcionalidades disponíveis para o usuário autenticado.
	 */
	private List<Funcionalidade> funcionalidadesDisponiveisUsuario = new ArrayList<Funcionalidade>();

	public void onCreate() throws InterruptedException {
		
		// Atribui a janela principal ao context
		NucleoContexto.atribuirJanelaPrincipal(this);

		// Faz a injeção das apl's da janela
		aplCadastrarFuncionalidade = (AplCadastrarFuncionalidade) SpringUtil.getBean("AplCadastrarFuncionalidade");

		aplCadastrarPerfilAcesso = (AplCadastrarPerfilAcesso) SpringUtil.getBean("AplCadastrarPerfilAcesso");

		// Monta a página principal
		this.MontarPagina();

	}

	/**
	 * Monta a página principal da aplicação contendo o menu com as funcionalidades.
	 */
	private void MontarPagina(){

		this.adicionarCabecalhoPrincipal();
		this.adicionarFuncionalidadesDisponiveisUsuario();
	}

	/**
	 * Adiciona o cabecalho da página principal
	 */
	private void adicionarCabecalhoPrincipal(){

		Div divCabecalho = new Div();
		divCabecalho.setParent(this);
		divCabecalho.setHeight("25px");
		divCabecalho.setClass("cabecalho");

		Div divLadoEsquerdo = new Div();
		divLadoEsquerdo.setParent(divCabecalho);
		divLadoEsquerdo.setStyle("float:left;");

		Hbox hboxLadoEsquerdo = new Hbox();
		hboxLadoEsquerdo.setParent(divLadoEsquerdo);
		hboxLadoEsquerdo.setAlign("middle");
		hboxLadoEsquerdo.setHeight("25px");

		// Adiciona o nome do sistema
		Label labelNomeSistema = new Label("Nome do Sistema");
		labelNomeSistema.setParent(hboxLadoEsquerdo);
		labelNomeSistema.setStyle("font-weight:bold; color: white; margin-left: 5px");

		// Adiciona o nome do cliente
		Label labelNomeCliente = new Label(" - Nome do Cliente");
		labelNomeCliente.setParent(hboxLadoEsquerdo);
		labelNomeCliente.setStyle("color: white;");

		Div divLadoDireito = new Div();
		divLadoDireito.setParent(divCabecalho);
		divLadoDireito.setStyle("valign: middle; float:right; margin-right: 5px;");//

		Hbox hboxLadoDireito = new Hbox();
		hboxLadoDireito.setParent(divLadoDireito);
		hboxLadoDireito.setAlign("middle");
		hboxLadoDireito.setHeight("25px");

		// Adiciona a imagem pessoa
		Image imageUsuario = new Image("/imagens/pessoa.png");
		imageUsuario.setParent(hboxLadoDireito);

		// Recupera usuário logado na sessão
		NucleoUserDetails usuario = NucleoContexto.recuperarUsuarioLogado();

		String nomeUsuario = (usuario == null) ? "Usuário Não Logado" : usuario.getRecursoHumano().getNome();

		// Adiciona nome do usuário		
		Label labelUsuario = new Label(nomeUsuario + " : " + usuario.getPerfilAcesso().getNome());
		labelUsuario.setParent(hboxLadoDireito);
		labelUsuario.setStyle("margin-right: 50px; color: white;");
		labelUsuario.setMaxlength(70);

		// Adiciona butão Sair
		Toolbarbutton toolbarbuttonSair = new Toolbarbutton("Sair", "/imagens/exit.png");
		toolbarbuttonSair.setParent(hboxLadoDireito);
		toolbarbuttonSair.setStyle("color: white;");
		toolbarbuttonSair.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				Executions.sendRedirect("/logout.zul");
			}
		});

	}

	/**
	 * Adiciona funcionalidades disponíveis para o usuário logado na sessão.
	 */
	private void adicionarFuncionalidadesDisponiveisUsuario(){

		// Cria o menu
		Menubar menuBar = new Menubar();
		menuBar.setParent(this);

		//////////////////////////////////////////////////////////////////////////
		// Adiciona as funcionalidades permitidas para o perfil do usuário logado
		//////////////////////////////////////////////////////////////////////////

		// Obtém o perfil do usuário autenticado
		PerfilAcesso grupoUsuario = aplCadastrarPerfilAcesso.recuperarPorId(NucleoContexto.recuperarUsuarioLogado().getPerfilAcesso().getId());

		// Obtém as funcionalidades do perfil do usuário
		funcionalidadesDisponiveisUsuario.addAll(grupoUsuario.getFuncionalidadesPermitidas());

		// Recupera do banco todas as funcionalidades raíz
		Collection<Funcionalidade> funcionalidades = aplCadastrarFuncionalidade.recuperarFuncionalidadesRaiz();

		Iterator<Funcionalidade> i = funcionalidades.iterator();	

		while (i.hasNext()){

			final Funcionalidade funcionalidade = i.next();

			// Adiciona a funcionalidade na interface, caso esteja disponível para o usuário
			if (funcionalidadesDisponiveisUsuario.contains(funcionalidade)) {

				if (funcionalidade.getSubfuncionalidades().size() > 0) {
					Menu menu = new Menu(funcionalidade.getNome());
					menu.setParent(menuBar);
					this.adicionarSubFuncionalidades(funcionalidade, menu);
				} else {
					Menuitem menuitem = new Menuitem(funcionalidade.getNome());
					menuitem.setAttribute("srcJanela", funcionalidade.getSrcJanela());
					menuitem.addEventListener("onClick", new EventListener() {
						public void onEvent(Event arg0) throws Exception {
							abrirJanela(funcionalidade.getSrcJanela());
						}
					});
					menuitem.setParent(menuBar);
				}

			}

		}

		//////////////////////////////////////////////////////////////////////////
		// Adiciona as funcionalidades comuns a todos os usuários
		//////////////////////////////////////////////////////////////////////////

		// Adiciona o menu Opções
		Menu menuOpcoes = new NucleoMenu("Opções");
		menuOpcoes.setParent(menuBar);
		Menupopup menupopupOpcoes = new Menupopup();
		menupopupOpcoes.setParent(menuOpcoes);
		Menuitem menuitemAlterarSenha = new Menuitem("Alterar Senha");
		menuitemAlterarSenha.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				abrirJanela("nucleo.autenticacao.view.WindowDadosAlterarSenha");
			}
		});
		menuitemAlterarSenha.setParent(menupopupOpcoes);

	}

	/**
	 * Adiciona as subfuncionalidades de um menu.
	 * @param funcionalidadePai Funcionalidade pai.
	 * @param menuPai Menu pai.
	 */
	private void adicionarSubFuncionalidades(Funcionalidade funcionalidadePai, final Menu menuPai){

		Collection<Funcionalidade> funcionalidades = aplCadastrarFuncionalidade.recuperarSubFuncionalidadesPorFuncionalidade(funcionalidadePai);

		Iterator<Funcionalidade> i = funcionalidades.iterator();

		Menupopup menupopup = new Menupopup();
		menupopup.setParent(menuPai);

		while (i.hasNext()){

			final Funcionalidade funcionalidade = i.next();

			// Adiciona a funcionalidade na interface, caso esteja disponível para o usuário
			if (funcionalidadesDisponiveisUsuario.contains(funcionalidade)) {

				if (funcionalidade.getSubfuncionalidades().size() > 0) {
					Menu menu = new Menu(funcionalidade.getNome());
					menu.setParent(menupopup);
					this.adicionarSubFuncionalidades(funcionalidade, menu);
				} else {
					Menuitem menuitem = new Menuitem(funcionalidade.getNome());
					menuitem.setAttribute("srcJanela", funcionalidade.getSrcJanela());
					menuitem.addEventListener("onClick", new EventListener() {
						public void onEvent(Event arg0) throws Exception {
							abrirJanela(funcionalidade.getSrcJanela());
						}
					});
					menuitem.setParent(menupopup);
				}

			}
		}

	}

	/**
	 * Inicia o controlador de uma aplicação.
	 * @param srcCtrl Src do controlador da aplicação.
	 */
	public void abrirJanela(String srcCtrl){
		Map<String,String> map = new HashMap<String,String>();
		map.put("use", srcCtrl);
		CtrlBase ctrl = (CtrlBase) Executions.createComponents(
				"/paginas/principal/ctrl.zul",
				this, map);
		ctrl.iniciar();
	}

	/** 
	 * Evento executado ao fechar a window. 
	 */
	public void onClose() {
		this.detach();
	}

}


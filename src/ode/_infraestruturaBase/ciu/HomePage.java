package ode._infraestruturaBase.ciu;

import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoMensagens;

import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

public class HomePage extends Window{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8260157958444341511L;

	private Label labelIdiomas, labelProjeto, labelUsuario;
	
	
	public HomePage() {
		montaCabecalho();
		atualizaBarraDeInformacoes();
		
		new Separator().setParent(this);
		
		montaMenuEsquerda().setParent(this);
	}
	
	protected void montaCabecalho(){
		Div divCabecalho = new Div();
		divCabecalho.setParent(this);
		divCabecalho.setStyle("background: #DDDFFF");

		Div divNomeSistema = new Div();
		divNomeSistema.setParent(divCabecalho);
		divNomeSistema.setStyle("display:inline-block; padding: 5px 20px 5px 10px");
		
		// Adiciona o nome do sistema
		Label labelNomeSistema = new Label("ODE - Ontology Development Environment");
		labelNomeSistema.setParent(divNomeSistema);
		labelNomeSistema.setStyle("font-weight:bold;");

		// Adiciona o nome da versão
		Label labelNomeVersao = new Label(" - Versão 1.0");
		labelNomeVersao.setParent(divNomeSistema);

		Div divIdiomas = new Div();
		divIdiomas.setParent(divCabecalho);
		divIdiomas.setStyle("display:inline-block; padding: 5px 20px 5px 10px");
		
		// Adiciona label e toolbarbuttons dos idiomas
		labelIdiomas = new Label();
		labelIdiomas.setParent(divIdiomas);

		Toolbarbutton toolbarbuttonPortuguesBR = new Toolbarbutton();
		toolbarbuttonPortuguesBR.setTooltiptext("Português - Brasil");
		toolbarbuttonPortuguesBR.setParent(divIdiomas);
		toolbarbuttonPortuguesBR.setImage("/imagens/idiomaPort.gif");
		/*toolbarbuttonPortuguesBR.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				NucleoContexto.atribuirLocale(new Locale("pt", "BR"));
				atualizarWindowPrincipal();
				Messagebox.show(NucleoMensagens.getMensagem(NucleoMensagens.MSG_IDIOMA_ALTERADO));
			}
		});*/

		Toolbarbutton toolbarbuttonEnglish = new Toolbarbutton();
		toolbarbuttonEnglish.setTooltiptext("English");
		toolbarbuttonEnglish.setParent(divIdiomas);
		toolbarbuttonEnglish.setImage("/imagens/idiomaIng.gif");
		/*toolbarbuttonEnglish.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				NucleoContexto.atribuirLocale(new Locale("en", "US"));
				atualizarWindowPrincipal();
				Messagebox.show(NucleoMensagens.getMensagem(NucleoMensagens.MSG_IDIOMA_ALTERADO));
			}
		});*/
		
		// Adiciona nome do projeto
		labelProjeto = new Label();
		labelProjeto.setParent(divCabecalho);
		labelProjeto.setStyle("display:inline-block; padding: 5px 20px 5px 10px");
		labelProjeto.setMaxlength(70);

		// Adiciona nome do usuário		
		labelUsuario = new Label();
		labelUsuario.setParent(divCabecalho);
		labelUsuario.setStyle("display:inline-block; padding: 5px 5px 5px 10px");
		labelUsuario.setMaxlength(70);
	}

	protected void atualizaBarraDeInformacoes(){
		this.labelIdiomas.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_IDIOMAS) + ":");
		this.labelProjeto.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROJETO) + ": " + (NucleoContexto.recuperarProjeto() == null ? NucleoMensagens.getMensagem(NucleoMensagens.MSG_NENHUM_PROJETO_SELECIONADO) : NucleoContexto.recuperarProjeto().getNome()));
		this.labelUsuario.setValue(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO) + ": " + NucleoContexto.recuperarUsuarioLogado().getRecursoHumano().getNome() + " - " + NucleoContexto.recuperarUsuarioLogado().getPerfilAcesso());
	}

	protected Tabbox montaMenuEsquerda(){
		Tabbox tabbox = new Tabbox();
		tabbox.setMold("accordion");
		tabbox.setWidth(null);
		
		Tabs tabs = new Tabs();
		tabs.setParent(tabbox);
		
		Tab tabOpcoes = new Tab("Opções");
		tabOpcoes.setParent(tabs);

		Tabpanels tabpanels = new Tabpanels();
		tabpanels.setParent(tabbox);
		
		Tabpanel tabpanelOpcoes = new Tabpanel();
		tabpanelOpcoes.setParent(tabpanels);
		
		Menubar menubar = new Menubar();
		menubar.setOrient("vertical");
		menubar.setParent(tabpanelOpcoes);
		
		new Menuitem("Alterar Senha").setParent(menubar);
		new Menuseparator().setParent(menubar);
		new Menuitem("Sair").setParent(menubar);
		
		return tabbox;
	}
}

package ode.gerenciaConhecimento.ciu;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.East;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.North;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Separator;
import org.zkoss.zul.South;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

public class JanPrincipal extends Borderlayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ---- paineis do setor leste e oeste do borderlayout ---- //
	Panel links;
	Panel funcionalidades;
	Panel ferramApoioColab;
	Panel participacaoPortal;
	Panel qtdeMembros;
	Panel itensMaisAcessadosPortal;
	Panel itensMaisRecentes;
	Panel qtdeItensConhecimento;
	
	// --- janela principal --- //
	Panel painelCentro;
	Panelchildren painelChildrenCentro;
	
	// center do borderlayout
	Center center;
	
	public CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	
	public JanPrincipal(CtrlGerenciaConhecimento ctrl){
		
		ctrlGerenciaConhecimento = ctrl;
		
		criaWest();
		criaEast();
		criaCenter();
		
	}
	
	// --- funções que criam os setores leste, oeste e centro do borderlayout --- //
	
	public void criaEast(){
		East east = new East();
		Vbox vbox = new Vbox();
		
		east.setParent(this);
		vbox.setParent(east);
		
		east.setSize("300px");
		east.setFlex(true);
		east.setSplittable(true);
		east.setCollapsible(true);
		
		criaPainelItensMaisAcessadosPortal();
		itensMaisAcessadosPortal.setParent(vbox);
		
		criaPainelItensMaisRecentes();
		itensMaisRecentes.setParent(vbox);
		
		criaPainelQtdeItensConhecimento();
		qtdeItensConhecimento.setParent(vbox);
	}
	
	public void criaCenter(){
		
		center = new Center();
		painelCentro = new Panel();
		painelChildrenCentro = new Panelchildren();
			
		painelCentro.setTitle("Bem-vindo ao Portal de Gerência de Conhecimento");
		painelCentro.setClosable(true);
		painelCentro.setMaximizable(true);
		painelCentro.setMinimizable(true);
		
		painelChildrenCentro.setParent(painelCentro);
		painelCentro.setParent(center);
		center.setParent(this);

	}
	
	public void criaWest(){
		Vbox vbox = new Vbox();
		West west = new West();
		
		west.setParent(this);
		west.setSize("300px");
		west.setFlex(true);
		west.setSplittable(true);
		west.setCollapsible(true);
		vbox.setParent(west);
		
		criaPainelLinks();
		links.setParent(vbox);
		
		criaPainelFuncionalidades();
		funcionalidades.setParent(vbox);
		
		criaPainelFerramApoioColab();
		ferramApoioColab.setParent(vbox);
		
		criaPainelParticipacaoPortal();
		participacaoPortal.setParent(vbox);
		
		criaPainelQtdeMembros();
		qtdeMembros.setParent(vbox);
		
		
	}
	
	

	// --- funções que criam os paineis nos setores leste e oeste do borderlayout --- //

	public void criaPainelLinks(){
		links = new Panel();
		
		links.setTitle("Links");
		links.setClosable(true);
		links.setMaximizable(true);
		links.setMinimizable(true);
		links.setBorder("normal");
		
		Panelchildren painelchildrenLinks = new Panelchildren();
		Vlayout vlayout = new Vlayout();
	
		Toolbarbutton toolbarbuttonPaginaInicial = criaToolBarButton(vlayout,"Página Inicial","/imagens/home.png");
		toolbarbuttonPaginaInicial.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.exibirJanelaAvaliarItemConhecimento();
				
			}
		});
		Toolbarbutton toolbarbuttonOdeWeb = criaToolBarButton(vlayout, "ODE Web","/imagens/krec_record.png");
		
		vlayout.setParent(painelchildrenLinks);
		painelchildrenLinks.setParent(links);
		
	}
	
	public void criaPainelFuncionalidades(){
		
		funcionalidades = new Panel();
		
		funcionalidades.setTitle("Funcionalidades");
		funcionalidades.setClosable(true);
		funcionalidades.setMaximizable(true);
		funcionalidades.setMinimizable(true);
		funcionalidades.setBorder("normal");
		
		Panelchildren painelchildrenFuncionalidades = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonCriarItensConhecimento = criaToolBarButton(vlayout,"Criar itens de Conhecimento","/imagens/definir.png");
		toolbarbuttonCriarItensConhecimento.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.exibirJanelaTiposItemConhecimento();
				
			}
		});
		
		Toolbarbutton toolbarbuttonBuscarItensConhecimento = criaToolBarButton(vlayout,"Buscar itens de Conhecimento","/imagens/kpdf.png");
		toolbarbuttonBuscarItensConhecimento.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.exibirJanelaBuscarItensConhecimento();
				
			}
		});

		
		vlayout.setParent(painelchildrenFuncionalidades);
		painelchildrenFuncionalidades.setParent(funcionalidades);
		
	}
	
	public void criaPainelFerramApoioColab(){
		
		ferramApoioColab = new Panel();
		
		ferramApoioColab.setTitle("Ferramentas de Apoio à Colaboração");
		ferramApoioColab.setClosable(true);
		ferramApoioColab.setMaximizable(true);
		ferramApoioColab.setMinimizable(true);
		ferramApoioColab.setBorder("normal");
		
		Panelchildren painelchildrenFerramApoioColab = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonPaginasAmarelas = criaToolBarButton(vlayout,"Páginas Amarelas","/imagens/vcard.png");	
		Toolbarbutton toolbarbuttonForunsDiscussao = criaToolBarButton(vlayout,"Fóruns de Discussão","/imagens/edu_languages.png");
		
		vlayout.setParent(painelchildrenFerramApoioColab);
		painelchildrenFerramApoioColab.setParent(ferramApoioColab);
	
	}
	
	public void criaPainelParticipacaoPortal(){
		
		participacaoPortal = new Panel();
		
		participacaoPortal.setTitle("Sua Participação no Portal");
		participacaoPortal.setClosable(true);
		participacaoPortal.setMaximizable(true);
		participacaoPortal.setMinimizable(true);
		participacaoPortal.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonItensCriados = criaToolBarButton(vlayout,"Itens Criados","/imagens/ledblue.png");
		Toolbarbutton toolbarbuttonItensAvaliados = criaToolBarButton(vlayout,"Itens Avaliados","/imagens/ledblue.png");
		Toolbarbutton toolbarbuttonItensValorados = criaToolBarButton(vlayout,"Itens Valorados","/imagens/ledblue.png");
		Toolbarbutton toolbarbuttonItensPendentesAvaliacao = criaToolBarButton(vlayout,"Itens Pendentes de Avaliação","/imagens/ledblue.png");

		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(participacaoPortal);
		
		
	}
	
	public void criaPainelQtdeMembros(){
		
		qtdeMembros = new Panel();
		
		qtdeMembros.setTitle("Quantidade de Membros");
		qtdeMembros.setClosable(true);
		qtdeMembros.setMaximizable(true);
		qtdeMembros.setMinimizable(true);
		qtdeMembros.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonMembrosOrganizacao = criaToolBarButton(vlayout,"Membros da Organização","/imagens/kdmconfig.png");
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(qtdeMembros);
		
	}
	
	public void criaPainelItensMaisAcessadosPortal(){
		
		itensMaisAcessadosPortal = new Panel();
		
		itensMaisAcessadosPortal.setTitle("Itens mais Acessados no Portal");
		itensMaisAcessadosPortal.setClosable(true);
		itensMaisAcessadosPortal.setMaximizable(true);
		itensMaisAcessadosPortal.setMinimizable(true);
		itensMaisAcessadosPortal.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonEstimarCustoSoftware = criaToolBarButton(vlayout,"Estimar Custo de Software","/imagens/view_detailed.png");
		Toolbarbutton toolbarbuttonLinguagemProgramacaoJava = criaToolBarButton(vlayout,"Linguagem de Programação Java","/imagens/view_detailed.png");
		Toolbarbutton toolbarbuttonBancoDadosPostgre = criaToolBarButton(vlayout,"Banco de Dados PostgreSQL","/imagens/view_detailed.png");
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(itensMaisAcessadosPortal);
		
	}
	
	public void criaPainelItensMaisRecentes(){
		
		itensMaisRecentes = new Panel();
		
		itensMaisRecentes.setTitle("Itens mais Recentes");
		itensMaisRecentes.setClosable(true);
		itensMaisRecentes.setMaximizable(true);
		itensMaisRecentes.setMinimizable(true);
		itensMaisRecentes.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonModeloDocAnaliseReq = criaToolBarButton(vlayout,"Modelo de Documento de Análise de Requisitos","/imagens/view_detailed.png");
		Toolbarbutton toolbarbuttonBancoDadosRelacionais = criaToolBarButton(vlayout,"Bancos de Dados Relacionais","/imagens/view_detailed.png");
		Toolbarbutton toolbarbuttonOrientacaoObjetos = criaToolBarButton(vlayout,"Orientação a Objetos","/imagens/view_detailed.png");
	
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(itensMaisRecentes);
		
	}
	
	public void criaPainelQtdeItensConhecimento(){
		
		qtdeItensConhecimento = new Panel();
		
		qtdeItensConhecimento.setTitle("Quantidade de Itens de Conhecimento");
		qtdeItensConhecimento.setClosable(true);
		qtdeItensConhecimento.setMaximizable(true);
		qtdeItensConhecimento.setMinimizable(true);
		qtdeItensConhecimento.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonLicaoAprendida = criaToolBarButton(vlayout,"Lição Aprendida","/imagens/view_detailed.png");
		Toolbarbutton toolbarbuttonConhecimentoRelativoDiscussao = criaToolBarButton(vlayout,"Conhecimentos relativo a uma Discussão","/imagens/view_detailed.png");
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(qtdeItensConhecimento);
		
	}
	
	// --- função que cria os toolbarbuttons dos paineis acima --- //
	public Toolbarbutton criaToolBarButton(Vlayout vlayout, String nome, String path){
		
		
		Toolbarbutton toolbarbutton = new Toolbarbutton();		

		toolbarbutton.setLabel(nome);
		toolbarbutton.setImage(path);
		
		toolbarbutton.setParent(vlayout);
		
		return toolbarbutton;
		
	}
	
	/**
	 * Adiciona uma janela no conteudo da janela principal.
	 * @param window Janela a ser adicionada.
	 */
	public void mostrarJanelaConteudo(Window window){
		
		// Remove todos os filhos do center do borderlayout
		int quantidadeFilhos = painelChildrenCentro.getChildren().size();
		for (int i =0; i < quantidadeFilhos; i++){
			Component object = (Component) painelChildrenCentro.getChildren().get(0);
			painelChildrenCentro.removeChild(object);
		}
		
		// Adiciona janela no center
		this.painelChildrenCentro.appendChild(window);
	}
	
	
}

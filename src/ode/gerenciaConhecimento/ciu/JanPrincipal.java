package ode.gerenciaConhecimento.ciu;

//// testeeeeeeeee /////////
import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.East;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

import com.ibm.icu.text.SimpleDateFormat;

public class JanPrincipal extends Borderlayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ---- paineis do setor leste e oeste do borderlayout ---- //
	Panel funcionalidades;
	Panel ferramApoioColab;
	Panel participacaoPortal;
	Panel qtdeMembros;
	Panel licoesMaisAcessadasPortal;
	Panel itensMaisAcessadosPortal;
	Panel licoesMaisRecentes;
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
		
		east.setSize("270px");
		east.setFlex(true);
		east.setSplittable(true);
		east.setCollapsible(true);
		
		criaPainelQtdeItensConhecimento();
		qtdeItensConhecimento.setParent(vbox);
		
		criaPainelLicoesMaisRecentes();
		licoesMaisRecentes.setParent(vbox);
		
		criaPainelItensMaisRecentes();
		itensMaisRecentes.setParent(vbox);
		
		criaPainelLicoesMaisAcessadas();
		licoesMaisAcessadasPortal.setParent(vbox);
		
		criaPainelItensMaisAcessadosPortal();
		itensMaisAcessadosPortal.setParent(vbox);
		
	}
	

	
	public void criaCenter(){
		
		center = new Center();
		painelCentro = new Panel();
		painelChildrenCentro = new Panelchildren();
		//painelCentro.setHeight("500px");
		painelChildrenCentro.setStyle("overflow:auto;");
			
		painelCentro.setTitle("Bem-vindo ao Portal de Gerência de Conhecimento");
		
		ctrlGerenciaConhecimento.exibirJanelaItensCriados_inicial().setParent(painelChildrenCentro);
		Object v = ctrlGerenciaConhecimento.verificarGerenteOuUsuario();
		if(v instanceof JanItensPendentesAvaliacaoGerente){
			ctrlGerenciaConhecimento.exibirJanelaItensPendentesAvaliacaoGerente_inicial().setParent(painelChildrenCentro);
		}
		if(v instanceof JanItensPendentesAvaliacaoUsuarioComum){
			ctrlGerenciaConhecimento.exibirJanelaItensPendentesAvaliacaoUsuarioComum_inicial().setParent(painelChildrenCentro);
		}
			
		painelChildrenCentro.setParent(painelCentro);
		painelCentro.setParent(center);
		center.setParent(this);

	}
	
	public void criaWest(){
		Vbox vbox = new Vbox();
		West west = new West();
		
		west.setParent(this);
		west.setSize("270px");
		west.setFlex(true);
		west.setSplittable(true);
		west.setCollapsible(true);
		vbox.setParent(west);
		
		criaPainelFuncionalidades();
		funcionalidades.setParent(vbox);
		
		criaPainelFerramApoioColab();
		ferramApoioColab.setParent(vbox);
		
		criaPainelParticipacaoPortal();
		participacaoPortal.setParent(vbox);
		
		criaPainelQtdeMembros();
		qtdeMembros.setParent(vbox);
		
		
	}
	
	public void criaPainelFuncionalidades(){
		
		funcionalidades = new Panel();
		
		funcionalidades.setTitle("Funcionalidades");
		funcionalidades.setClosable(true);
		funcionalidades.setBorder("normal");
		
		Panelchildren painelchildrenFuncionalidades = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonCriarItensConhecimento = criarToolBarButton(vlayout,"Criar itens de Conhecimento","/imagens/definir.png");
		toolbarbuttonCriarItensConhecimento.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.exibirJanelaTiposItemConhecimento();
				
			}
		});
		
		Toolbarbutton toolbarbuttonBuscarItensConhecimento = criarToolBarButton(vlayout,"Buscar itens de Conhecimento","/imagens/kpdf.png");
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
		ferramApoioColab.setBorder("normal");
		
		Panelchildren painelchildrenFerramApoioColab = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		

		Toolbarbutton toolbarbuttonMeusTemas = criarToolBarButton(vlayout,"Meus Temas de Interesse","/imagens/vcard.png");
		toolbarbuttonMeusTemas.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaMeusTemasDeItenteresse();
			}
		});
		
		Toolbarbutton toolbarbuttonPaginasAmarelas = criarToolBarButton(vlayout,"Páginas Amarelas","/imagens/vcard.png");
		toolbarbuttonPaginasAmarelas.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaPaginasAmarelasBuscarPessoas();
			}
		});
		
		Toolbarbutton toolbarbuttonForunsDiscussao = criarToolBarButton(vlayout,"Fóruns de Discussão","/imagens/edu_languages.png");	

		
		vlayout.setParent(painelchildrenFerramApoioColab);
		painelchildrenFerramApoioColab.setParent(ferramApoioColab);
	
	}
	
	public void criaPainelParticipacaoPortal(){
		
		participacaoPortal = new Panel();
		
		participacaoPortal.setTitle("Sua Participação no Portal");
		participacaoPortal.setClosable(true);
		participacaoPortal.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		Toolbarbutton toolbarbuttonItensCriados = criarToolBarButton(vlayout,"Itens Criados","/imagens/ledblue.png");
		toolbarbuttonItensCriados.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				ctrlGerenciaConhecimento.exibirJanelaItensCriados();
				
			}
		});
		
		Toolbarbutton toolbarbuttonItensAvaliados = criarToolBarButton(vlayout,"Itens Avaliados","/imagens/ledblue.png");
		toolbarbuttonItensAvaliados.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaItensAvaliados();
			}
		});
		
		Toolbarbutton toolbarbuttonItensValorados = criarToolBarButton(vlayout,"Itens Valorados","/imagens/ledblue.png");
		toolbarbuttonItensValorados.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaItensValorados();
			}
		});
		
		Toolbarbutton toolbarbuttonItensPendentesAvaliacao = criarToolBarButton(vlayout,"Itens Pendentes de Avaliação","/imagens/ledblue.png");
		toolbarbuttonItensPendentesAvaliacao.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaItensPendentesAvaliacao();
			}
		});

		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(participacaoPortal);
		
		
	}
	
	public void criaPainelQtdeMembros(){
		
		qtdeMembros = new Panel();
		
		qtdeMembros.setTitle("Quantidade de Membros");
		qtdeMembros.setClosable(true);
		qtdeMembros.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		// Recupera quantidade de membros
		int qtd = ctrlGerenciaConhecimento.recuperarQuantidadeTotalMembros();
		criarToolBarButton(vlayout,"Membros da Organização (" + qtd +")","/imagens/kdmconfig.png");
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(qtdeMembros);
		
	}
	
	public void criaPainelLicoesMaisAcessadas(){
		
		licoesMaisAcessadasPortal = new Panel();
		
		licoesMaisAcessadasPortal.setTitle("Lições Aprendidas mais Acessadas");
		licoesMaisAcessadasPortal.setClosable(true);
		licoesMaisAcessadasPortal.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		List<LicaoAprendida> licoes = ctrlGerenciaConhecimento.recuperarLicoesOrdenadoPorQuantidadeAcesso();
		for (LicaoAprendida licao : licoes)
			criarToolBarButton(vlayout,licao.getTitulo() + " (" + licao.getQuantidadeAcessos() + ")","/imagens/view_detailed.png");
		
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(licoesMaisAcessadasPortal);
		
	}
	
	public void criaPainelItensMaisAcessadosPortal(){
		
		itensMaisAcessadosPortal = new Panel();
		
		itensMaisAcessadosPortal.setTitle("Conh. Rel. a Discussão mais Acessados");
		itensMaisAcessadosPortal.setClosable(true);
		itensMaisAcessadosPortal.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		List<ConhecimentoRelativoDiscussao> itens = ctrlGerenciaConhecimento.recuperarItensDiscussaoOrdenadoPorQuantidadeAcesso();
		for (ConhecimentoRelativoDiscussao item : itens)
			criarToolBarButton(vlayout,item.getTitulo() + " (" + item.getQuantidadeAcessos() + ")","/imagens/view_detailed.png");
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(itensMaisAcessadosPortal);
		
	}
	
	public void criaPainelLicoesMaisRecentes(){
		
		licoesMaisRecentes = new Panel();
		
		licoesMaisRecentes.setTitle("Lições Aprendidas mais Recentes");
		licoesMaisRecentes.setClosable(true);
		licoesMaisRecentes.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		List<LicaoAprendida> licoes = ctrlGerenciaConhecimento.recuperarLicoesOrdenadoPorDataCriacaoMaisRecente();
		for (LicaoAprendida licao : licoes)
			criarToolBarButton(vlayout,licao.getTitulo() + " (" + format.format(licao.getDataCriacao()) + ")","/imagens/view_detailed.png");
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(licoesMaisRecentes);
		
	}
	
	public void criaPainelItensMaisRecentes(){
		
		itensMaisRecentes = new Panel();
		
		itensMaisRecentes.setTitle("Conh. Rel. a Discussão mais Recentes");
		itensMaisRecentes.setClosable(true);
		itensMaisRecentes.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		List<ConhecimentoRelativoDiscussao> itens = ctrlGerenciaConhecimento.recuperarItensDiscussaoOrdenadoPorDataCriacaoMaisRecente();
		for (ConhecimentoRelativoDiscussao item : itens)
			criarToolBarButton(vlayout,item.getTitulo() + " (" + format.format(item.getDataCriacao()) + ")","/imagens/view_detailed.png");
		
	
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(itensMaisRecentes);
		
	}
	
	public void criaPainelQtdeItensConhecimento(){
		
		qtdeItensConhecimento = new Panel();
		
		qtdeItensConhecimento.setTitle("Quantidade de Itens de Conhecimento");
		qtdeItensConhecimento.setClosable(true);
		qtdeItensConhecimento.setBorder("normal");
		
		Panelchildren painelchildrenParticipacaoPortal = new Panelchildren();
		Vlayout vlayout = new Vlayout();
		
		// Recupera quantidade
		int qtdLicoes = this.ctrlGerenciaConhecimento.recuperarQuantidadeTotalLicoesAprendidas();
		int qtdItens = this.ctrlGerenciaConhecimento.recuperarQuantidadeTotalItensDiscussao();
		
		criarToolBarButton(vlayout,"Lição Aprendida (" + qtdLicoes + ")","/imagens/view_detailed.png");
		criarToolBarButton(vlayout,"Conhecimentos relativo a uma Discussão (" + qtdItens + ")","/imagens/view_detailed.png");
		
		vlayout.setParent(painelchildrenParticipacaoPortal);
		painelchildrenParticipacaoPortal.setParent(qtdeItensConhecimento);
		
	}
	
	// --- função que cria os toolbarbuttons dos paineis acima --- //
	public Toolbarbutton criarToolBarButton(Vlayout vlayout, String nome, String path){
		
		
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

package ode.gerenciaConhecimento.ciu;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgt.AplCadastrarUsuario;
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cdp.Valoracao;
import ode.gerenciaConhecimento.cgt.AplCadastrarConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cgt.AplCadastrarItemConhecimento;
import ode.gerenciaConhecimento.cgt.AplCadastrarLicaoAprendida;
import ode.gerenciaConhecimento.cgt.AplCadastrarTema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Window;

@Controller
public class CtrlGerenciaConhecimento extends CtrlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JanelaSimples jan;
	private JanPrincipal janP;	

	public JanTiposItemConhecimento janTiposItemConhecimento;
	public JanCriarLicaoAprendida janCriarLicaoAprendida;
	public JanCriarConhecimentoRelativoDiscussao janCriarConhecimentoRelativoDiscussao;
	public JanBuscarItensConhecimento janBuscarItensConhecimento;
	public JanAvaliarItemConhecimento janAvaliarItemConhecimento;
	public JanItensCriados janItensCriados;
	public JanListaBuscarItensConhecimento janListaBuscarItensConhecimento;
	public JanVisualizarItemConhecimentoUsuarioComum janVisualizarItemConhecimentoUsuarioComum;
	public JanItensPendentesAvaliacaoUsuarioComum janItensPendentesAvaliacaoUsuarioComum;
	public JanValorarItemConhecimento janValorarItemConhecimento;
	public JanItensPendentesAvaliacaoGerente janItensPendentesAvaliacaoGerente;
	public JanVisualizarItemConhecimentoGerente janVisualizarItemConhecimentoGerente;
	public JanMeusTemasDeInteresse janMeusTemasDeInteresse;
	public JanItensValorados janItensValorados;
	public JanItensAvaliados janItensAvaliados;
	public JanPaginasAmarelasBuscarPessoas janPaginasAmarelasBuscarPessoas;
	public JanPaginasAmarelasResultadoPesquisa janPaginasAmarelasResultadoPesquisa;
	public JanPaginasAmarelasVisualizarPerfilPessoal janPaginasAmarelasVisualizarPerfilPessoal;
	public JanAlterarItemConhecimento janAlterarItemConhecimento;


	@Autowired
	AplCadastrarConhecimentoRelativoDiscussao aplCadastrarConhecimentoRelativoDiscussao;

	@Autowired
	AplCadastrarLicaoAprendida aplCadastrarLicaoAprendida;

	@Autowired
	AplCadastrarProjeto aplCadastrarProjeto;

	@Autowired
	AplCadastrarTema aplCadastrarTema;

	@Autowired
	AplCadastrarKAtividade aplCadastrarKAtividade;

	@Autowired
	AplCadastrarItemConhecimento aplCadastrarItemConhecimento;

	@Autowired
	AplCadastrarRecursoHumano aplCadastrarRecursoHumano;
	
	@Autowired
	AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano;

	@Autowired
	AplCadastrarUsuario aplCadastrarUsuario;

	@Autowired
	AtuacaoRHDAO atuacaoRHDAO;

	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		mostrarJanelaPrincipal();
	}

	public void mostrarJanelaPrincipal(){

		jan = factoryJanelaSimples();

		janP = new JanPrincipal(this);
		janP.setParent(jan);

		jan.setTitle("Portal de Gerência de Conhecimento");
		jan.setWidth("100%");
		jan.setHeight("580px");

		jan.doEmbedded();
	}
	
	public Object verificarGerenteOuUsuario(){
		
		// Recupera todos que são gerentes de projeto
		Collection<RecursoHumano> recursos = this.recuperarGerentesConhecimento();

		if (recursos.contains(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano())){
			janItensPendentesAvaliacaoGerente = new JanItensPendentesAvaliacaoGerente(this);
			return janItensPendentesAvaliacaoGerente;
		} else {
			janItensPendentesAvaliacaoUsuarioComum = new JanItensPendentesAvaliacaoUsuarioComum(this);
			return janItensPendentesAvaliacaoUsuarioComum;
		}
		
	}

	public JanItensCriados exibirJanelaItensCriados_inicial(){

		janItensCriados = new JanItensCriados(this);

		return janItensCriados;

	}

	public JanItensPendentesAvaliacaoGerente exibirJanelaItensPendentesAvaliacaoGerente_inicial(){

		janItensPendentesAvaliacaoGerente = new JanItensPendentesAvaliacaoGerente(this);

		return janItensPendentesAvaliacaoGerente;
	}
	
	public JanItensPendentesAvaliacaoUsuarioComum exibirJanelaItensPendentesAvaliacaoUsuarioComum_inicial(){

		janItensPendentesAvaliacaoUsuarioComum = new JanItensPendentesAvaliacaoUsuarioComum(this);

		return janItensPendentesAvaliacaoUsuarioComum;
	}

	public void exibirJanelaTiposItemConhecimento(){

		janTiposItemConhecimento = new JanTiposItemConhecimento(this);

		janP.mostrarJanelaConteudo(janTiposItemConhecimento);

	}

	public void exibirJanelaBuscarItensConhecimento(){

		janBuscarItensConhecimento = new JanBuscarItensConhecimento(this);

		janP.mostrarJanelaConteudo(janBuscarItensConhecimento);

	}

	public void exibirJanelaItensValorados(){

		janItensValorados = new JanItensValorados(this);

		janP.mostrarJanelaConteudo(janItensValorados);
	}

	public void exibirJanelaItensAvaliados(){

		janItensAvaliados = new JanItensAvaliados(this);

		janP.mostrarJanelaConteudo(janItensAvaliados);
	}
	
	public void exibirJanelaPaginasAmarelasVisualizarPerfil(RecursoHumano rh){
		
		janPaginasAmarelasVisualizarPerfilPessoal = new JanPaginasAmarelasVisualizarPerfilPessoal(this, rh);
		
		janP.mostrarJanelaConteudo(janPaginasAmarelasVisualizarPerfilPessoal);
	}
	
	public void exibirJanelaPaginasAmarelasBuscarPessoas(){
		
		janPaginasAmarelasBuscarPessoas = new JanPaginasAmarelasBuscarPessoas(this);
		
		janP.mostrarJanelaConteudo(janPaginasAmarelasBuscarPessoas);
	}
	
	public void exibirJanelaPaginasAmarelasResultadoPesquisa(Collection<RecursoHumano> recursos){
		
		janPaginasAmarelasResultadoPesquisa = new JanPaginasAmarelasResultadoPesquisa(this,recursos);
		
		janP.mostrarJanelaConteudo(janPaginasAmarelasResultadoPesquisa);
	}

	public void exibirJanelaItensPendentesAvaliacao(){

		// Recupera todos que são gerentes de conhecimento
		Collection<RecursoHumano> recursos = this.recuperarGerentesConhecimento();

		if (recursos.contains(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano())){
			this.exibirJanelaItensPendentesAvaliacaoGerente();
			//exibirJanelaItensPendentesAvaliacaoGerente();
		} else {
			exibirJanelaItensPendentesAvaliacaoUsuarioComum();
		}
	}

	public void exibirJanelaItensPendentesAvaliacaoUsuarioComum(){

		janItensPendentesAvaliacaoUsuarioComum = new JanItensPendentesAvaliacaoUsuarioComum(this);

		janP.mostrarJanelaConteudo(janItensPendentesAvaliacaoUsuarioComum);

	}

	public void exibirJanelaItensPendentesAvaliacaoGerente(){

		janItensPendentesAvaliacaoGerente = new JanItensPendentesAvaliacaoGerente(this);

		janP.mostrarJanelaConteudo(janItensPendentesAvaliacaoGerente);

	}

	public void exibirJanelaValorarItemConhecimento(ItemConhecimento itemConhecimento){

		janValorarItemConhecimento = new JanValorarItemConhecimento(this, itemConhecimento);

		janP.mostrarJanelaConteudo(janValorarItemConhecimento);

	}



	public void exibirJanelaAvaliarItemConhecimento(ItemConhecimento itemConhecimento){

		janAvaliarItemConhecimento = new JanAvaliarItemConhecimento(this, itemConhecimento);

		janP.mostrarJanelaConteudo(janAvaliarItemConhecimento);

	}




	public void exibirJanelaItensCriados(){

		janItensCriados = new JanItensCriados(this);

		janP.mostrarJanelaConteudo(janItensCriados);

	}

	public void exibirJanelaVisualizarItemConhecimento(ItemConhecimento itemConhecimento){

		// Recupera todos que são gerentes de projeto
		Collection<RecursoHumano> recursos = this.recuperarGerentesConhecimento();

		if (recursos.contains(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano())){
			this.exibirJanelaVisualizarItemConhecimentoGerente(itemConhecimento);
		} else {
			exibirJanelaVisualizarItemConhecimentoUsuarioComum(itemConhecimento);
		}
	}

	public void exibirJanelaVisualizarItemConhecimentoUsuarioComum(ItemConhecimento item){

		janVisualizarItemConhecimentoUsuarioComum = new JanVisualizarItemConhecimentoUsuarioComum(this, item);

		janP.mostrarJanelaConteudo(janVisualizarItemConhecimentoUsuarioComum);

	}

	public void exibirJanelaVisualizarItemConhecimentoGerente(ItemConhecimento itemConhecimento){

		janVisualizarItemConhecimentoGerente = new JanVisualizarItemConhecimentoGerente(this,itemConhecimento);

		janP.mostrarJanelaConteudo(janVisualizarItemConhecimentoGerente);

	}
	
	public void exibirJanelaAlterarItemConhecimento(ItemConhecimento itemConhecimento){

		janAlterarItemConhecimento = new JanAlterarItemConhecimento(this,itemConhecimento);

		janP.mostrarJanelaConteudo(janAlterarItemConhecimento);

	}

	public void exibirJanelaListaBuscarItensConhecimento(List<ItemConhecimento> itens){

		janListaBuscarItensConhecimento = new JanListaBuscarItensConhecimento(this,itens);

		janP.mostrarJanelaConteudo(janListaBuscarItensConhecimento);

	}

	public void exibirJanelaCriarLicaoAprendida(){

		janCriarLicaoAprendida = new JanCriarLicaoAprendida(this);

		janP.mostrarJanelaConteudo(janCriarLicaoAprendida);

	}

	public void exibirJanelaCriarConhecimentoRelativoDiscussao(){

		janCriarConhecimentoRelativoDiscussao = new JanCriarConhecimentoRelativoDiscussao(this);

		janP.mostrarJanelaConteudo(janCriarConhecimentoRelativoDiscussao);

	}

	public void exibirJanelaMeusTemasDeItenteresse(){

		janMeusTemasDeInteresse = new JanMeusTemasDeInteresse(this);

		janP.mostrarJanelaConteudo(janMeusTemasDeInteresse);

	}
	
	public void salvarEstadoItemConhecimento(ItemConhecimento itemConhecimento, String estado) throws NucleoRegraNegocioExcecao{
		itemConhecimento.setEstado(estado);
		this.aplCadastrarItemConhecimento.salvar(itemConhecimento);
	}

	public void salvarLicaoAprendida(LicaoAprendida la) {

		try {
			aplCadastrarLicaoAprendida.salvar(la);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void salvarConhecimentoRelativoDiscussao(ConhecimentoRelativoDiscussao crd){

		try {
			aplCadastrarConhecimentoRelativoDiscussao.salvar(crd);
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}

	}

	public Collection<Projeto> recuperarProjetos(){
		return aplCadastrarProjeto.recuperarTodos();
	}

	public List<LicaoAprendida> recuperarLicoesOrdenadoPorQuantidadeAcesso(){
		return aplCadastrarLicaoAprendida.recuperarOrdenadoPorQuantidadeAcesso();
	}

	public List<LicaoAprendida> recuperarLicoesOrdenadoPorDataCriacaoMaisRecente(){
		return aplCadastrarLicaoAprendida.recuperarOrdenadoPorDataCriacaoMaisRecente();
	}

	public int recuperarQuantidadeTotalLicoesAprendidas(){
		return this.aplCadastrarLicaoAprendida.recuperarQuantidadeTotal();
	}

	public List<ConhecimentoRelativoDiscussao> recuperarItensDiscussaoOrdenadoPorQuantidadeAcesso(){
		return aplCadastrarConhecimentoRelativoDiscussao.recuperarOrdenadoPorQuantidadeAcesso();
	}

	public List<ConhecimentoRelativoDiscussao> recuperarItensDiscussaoOrdenadoPorDataCriacaoMaisRecente(){
		return aplCadastrarConhecimentoRelativoDiscussao.recuperarOrdenadoPorDataCriacaoMaisRecente();
	}

	public int recuperarQuantidadeTotalItensDiscussao(){
		return this.aplCadastrarConhecimentoRelativoDiscussao.recuperarQuantidadeTotal();
	}

	public int recuperarQuantidadeTotalMembros(){
		return this.aplCadastrarRecursoHumano.recuperarQuantidadeTotal();
	}

	public void adicionarValoracao(Valoracao valoracao, ItemConhecimento itemConhecimento){
		this.aplCadastrarItemConhecimento.adicionarValoracao(valoracao, itemConhecimento);
	}

	public Collection<RecursoHumano> recuperarRecursosHumanosAtivos(){
		return aplCadastrarRecursoHumano.recuperarRecursosHumanosAtivos();
	}

	public Usuario recuperarUsuarioPorID(Long id){
		return aplCadastrarUsuario.recuperarPorId(id);
	}

	public void salvarMeusTemas(RecursoHumano recursoHumano) {
		try {
			aplCadastrarRecursoHumano.salvar(recursoHumano);
		} catch (NucleoRegraNegocioExcecao e) {
			e.printStackTrace();
		}
	}

	public void salvarAvaliacaoItemConhecimento(Avaliacao avaliacao, ItemConhecimento itemConhecimento){
		this.aplCadastrarItemConhecimento.adicionarAvaliacao(avaliacao, itemConhecimento);
	}
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoPendentesPorUsuarioAtual(){
		return this.aplCadastrarItemConhecimento.recuperarItensConhecimentoPendentesPorUsuarioAtual();
	}
	
	public Collection<ItemConhecimento> recuperarItensCriados() { 
		return this.aplCadastrarItemConhecimento.recuperarItensCriados();
	}
	
	public void colocarItemConhecimentoSessao(ItemConhecimento itemConhecimento){
		// Coloca o item de conhecimento na sessao para selecionar seus avaliadores
		Sessions.getCurrent().setAttribute("ITEM_CONHECIMENTO",itemConhecimento);
	}
	
	public void definirAvaliadores(Set<RecursoHumano> avaliadores) throws NucleoRegraNegocioExcecao{
		
		ItemConhecimento itemConhecimento = (ItemConhecimento)Sessions.getCurrent().getAttribute("ITEM_CONHECIMENTO");
		
		itemConhecimento.getAvaliadores().addAll(avaliadores);
		
		this.aplCadastrarItemConhecimento.salvar(itemConhecimento);
		
		retirarItemConhecimento();
	}
	
	
	
	public void retirarItemConhecimento(){
		if (possuiItemNaSessao()){
			// Retira o item de conhecimento na sessao para selecionar seus avaliadores
			Sessions.getCurrent().setAttribute("ITEM_CONHECIMENTO",null);
		}
	}
	
	public boolean possuiItemNaSessao(){
		
		if ( Sessions.getCurrent().getAttribute("ITEM_CONHECIMENTO") == null )
			return false;
		
		return true;
	}
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoValorados(){
		return this.aplCadastrarItemConhecimento.recuperarItensConhecimentoValorados();
	}
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoAvaliados(){
		return this.aplCadastrarItemConhecimento.recuperarItensConhecimentoAvaliados();
	}
	
	public Collection<RecursoHumano> recuperarPorTemasItemCriadoAvaliadoValorado(List<Tema> temas, ItemConhecimento itemCriado, ItemConhecimento itemAvaliado, ItemConhecimento itemValorado) {
		return this.aplCadastrarRecursoHumano.recuperarPorTemasItemCriadoAvaliadoValorado(temas, itemCriado, itemAvaliado, itemValorado);
	}
	
	// Recupera todos os recursos humanos que tem o cargo gerente de conhecimento
	public Collection<RecursoHumano> recuperarGerentesConhecimento(){
		
		KRecursoHumano kRecursoHumano = this.aplCadastrarKRecursoHumano.recuperarPorParteNome("gerente conhecimento");
		
		return this.aplCadastrarRecursoHumano.recuperarPorCargo(kRecursoHumano);
	}
	
	public Collection<ItemConhecimento> recuperarItensCriadosPorAutor(RecursoHumano rh){
		
		return this.aplCadastrarItemConhecimento.recuperarItensCriadosPorUsuarioAtual(rh);
		
	}
	
	public Collection<ItemConhecimento> recuperarItensAvaliadosPorUsuarioAtual(RecursoHumano rh){
		
		return this.aplCadastrarItemConhecimento.recuperarItensAvaliadosPorUsuarioAtual(rh);
		
	}
	
	public Collection<ItemConhecimento> recuperarItensValoradosPorUsuarioAtual(RecursoHumano rh){
		
		return this.aplCadastrarItemConhecimento.recuperarItensValoradosPorUsuarioAtual(rh);
		
	}
	
	public List<ItemConhecimento> recuperarLicoesAprendidasDisponiveis(){
		return this.aplCadastrarLicaoAprendida.recuperarLicoesAprendidasDisponiveis();
	}
	
	public List<ItemConhecimento> recuperarConhecimentoRelativoDiscussaoDisponiveis(){
		return this.aplCadastrarConhecimentoRelativoDiscussao.recuperarConhecimentoRelativoDiscussaoDisponiveis();
	}
	
	public void voltar(){

		String stringUltimaJanela = (String) Sessions.getCurrent().getAttribute("ULTIMA_JANELA");
		
		if (stringUltimaJanela.compareTo(JanListaBuscarItensConhecimento.class.getName()) == 0) {
		            
			List<ItemConhecimento> ultimaLista = (List<ItemConhecimento>) Sessions.getCurrent().getAttribute("JANELA_LISTA_BUSCAR");
			if(ultimaLista != null)
				exibirJanelaListaBuscarItensConhecimento(ultimaLista);

		}
		
		if (stringUltimaJanela.compareTo(JanPaginasAmarelasResultadoPesquisa.class.getName()) == 0) {
            
			Collection<RecursoHumano> ultimaLista = (Collection<RecursoHumano>) Sessions.getCurrent().getAttribute("JANELA_PAG_AMA_RESULTADO_PESQUISA");
			if(ultimaLista != null)	
				exibirJanelaPaginasAmarelasResultadoPesquisa(ultimaLista);

		}
		
		if (stringUltimaJanela.compareTo(JanItensCriados.class.getName()) == 0) {
            
			List<ItemConhecimento> ultimaLista = (List<ItemConhecimento>) Sessions.getCurrent().getAttribute("JANELA_LISTA_BUSCAR");
			if(ultimaLista != null)	
				exibirJanelaItensCriados();

		}
		
		if (stringUltimaJanela.compareTo(JanItensValorados.class.getName()) == 0) {
            
			Collection<ItemConhecimento> ultimaLista = (Collection<ItemConhecimento>) Sessions.getCurrent().getAttribute("JANELA_LISTA_BUSCAR");
			if(ultimaLista != null)	
				exibirJanelaItensValorados();

		}
		
		if (stringUltimaJanela.compareTo(JanItensAvaliados.class.getName()) == 0) {
            
			Collection<ItemConhecimento> ultimaLista = (Collection<ItemConhecimento>) Sessions.getCurrent().getAttribute("JANELA_LISTA_BUSCAR");
			if(ultimaLista != null)	
				exibirJanelaItensAvaliados();

		}
		
		if (stringUltimaJanela.compareTo(JanItensPendentesAvaliacaoGerente.class.getName()) == 0) {
            
			Collection<ItemConhecimento> ultimaLista = (Collection<ItemConhecimento>) Sessions.getCurrent().getAttribute("JANELA_LISTA_BUSCAR");
			if(ultimaLista != null)	
				exibirJanelaItensPendentesAvaliacao();

		}
		
		if (stringUltimaJanela.compareTo(JanItensPendentesAvaliacaoUsuarioComum.class.getName()) == 0) {
            
			Collection<ItemConhecimento> ultimaLista = (Collection<ItemConhecimento>) Sessions.getCurrent().getAttribute("JANELA_LISTA_BUSCAR");
			if(ultimaLista != null)	
				exibirJanelaItensPendentesAvaliacao();

		}
		
		
	}
	
	public void guardarSessao(String jan, Collection<ItemConhecimento> itensJanListaBuscar, Collection<RecursoHumano> recursosJanResultadoPagAma){
		Sessions.getCurrent().setAttribute("ULTIMA_JANELA", jan);
		Sessions.getCurrent().setAttribute("JANELA_LISTA_BUSCAR", itensJanListaBuscar);
		Sessions.getCurrent().setAttribute("JANELA_PAG_AMA_RESULTADO_PESQUISA", recursosJanResultadoPagAma);
		
	}
	
}

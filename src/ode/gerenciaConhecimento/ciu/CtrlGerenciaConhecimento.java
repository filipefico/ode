package ode.gerenciaConhecimento.ciu;

import java.util.Collection;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cgt.AplCadastrarConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cgt.AplCadastrarItemConhecimento;
import ode.gerenciaConhecimento.cgt.AplCadastrarLicaoAprendida;
import ode.gerenciaConhecimento.cgt.AplCadastrarTema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
		jan.setHeight("600px");

		jan.doEmbedded();
	}

	public void exibirJanelaTiposItemConhecimento(){

		janTiposItemConhecimento = new JanTiposItemConhecimento(this);

		janP.mostrarJanelaConteudo(janTiposItemConhecimento);

	}

	public void exibirJanelaBuscarItensConhecimento(){

		janBuscarItensConhecimento = new JanBuscarItensConhecimento(this);

		janP.mostrarJanelaConteudo(janBuscarItensConhecimento);

	}

	
	public void exibirJanelaItensPendentesAvaliacaoUsuarioComum(){
		
		janItensPendentesAvaliacaoUsuarioComum = new JanItensPendentesAvaliacaoUsuarioComum(this);
		
		janP.mostrarJanelaConteudo(janItensPendentesAvaliacaoUsuarioComum);
		
	}
	
	public void exibirJanelaItensPendentesAvaliacaoGerente(){
		
		janItensPendentesAvaliacaoGerente = new JanItensPendentesAvaliacaoGerente(this);
		
		janP.mostrarJanelaConteudo(janItensPendentesAvaliacaoGerente);
		
	}

	public void exibirJanelaValorarItemConhecimento(){
		
		janValorarItemConhecimento = new JanValorarItemConhecimento(this);
		
		janP.mostrarJanelaConteudo(janValorarItemConhecimento);
		
	}
		


	public void exibirJanelaAvaliarItemConhecimento(){

		janAvaliarItemConhecimento = new JanAvaliarItemConhecimento(this);

		janP.mostrarJanelaConteudo(janAvaliarItemConhecimento);
		
	}
	 	


	
	public void exibirJanelaItensCriados(){
		
		janItensCriados = new JanItensCriados(this);
		
		janP.mostrarJanelaConteudo(janItensCriados);
		
	}
	
	public void exibirJanelaVisualizarItemConhecimentoUsuarioComum(){
		
		janVisualizarItemConhecimentoUsuarioComum = new JanVisualizarItemConhecimentoUsuarioComum(this);
		
		janP.mostrarJanelaConteudo(janVisualizarItemConhecimentoUsuarioComum);
		
	}
	
	public void exibirJanelaVisualizarItemConhecimentoGerente(){
		
		janVisualizarItemConhecimentoGerente = new JanVisualizarItemConhecimentoGerente(this);
		
		janP.mostrarJanelaConteudo(janVisualizarItemConhecimentoGerente);
		
	}
	
	public void exibirJanelaListaBuscarItensConhecimento(){
		
		janListaBuscarItensConhecimento = new JanListaBuscarItensConhecimento(this);
		
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

}

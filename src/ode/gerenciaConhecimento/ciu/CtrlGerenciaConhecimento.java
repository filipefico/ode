package ode.gerenciaConhecimento.ciu;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cgt.AplCadastrarConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cgt.AplCadastrarLicaoAprendida;

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
	
	@Autowired
	AplCadastrarConhecimentoRelativoDiscussao aplCadastrarConhecimentoRelativoDiscussao;
	
	@Autowired
	AplCadastrarLicaoAprendida aplCadastrarLicaoAprendida;
	
	@Autowired
	AplCadastrarProjeto aplCadastrarProjeto;
	
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		mostrarJanelaPrincipal();
	}
	
	public void mostrarJanelaPrincipal(){
		
		jan = factoryJanelaSimples();
		
		janP = new JanPrincipal(this);
		janP.setParent(jan);
		
		jan.setTitle("Portal de Ger�ncia de Conhecimento");
		jan.setWidth("100%");
		jan.setHeight("600px");
		
		jan.doEmbedded();
	}
	
	public void exibirJanelaTiposItemConhecimento(){
		
		janTiposItemConhecimento = new JanTiposItemConhecimento(this);
		
		janP.mostrarJanelaConteudo(janTiposItemConhecimento);
		
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
			e.printStackTrace();
		}
		
	}
	
	public Collection<Projeto> recuperarProjetos(){
		return aplCadastrarProjeto.recuperarTodos();
	}

}

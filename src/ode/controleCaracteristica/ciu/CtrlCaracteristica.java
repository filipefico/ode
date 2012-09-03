package ode.controleCaracteristica.ciu;

import java.util.Collection;
import java.util.Set;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD.ModoExibicao;
import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.CaracteristicaValorNaoOrdenado;
import ode.controleCaracteristica.cdp.CaracteristicaValorOrdenado;
import ode.controleCaracteristica.cdp.EnumTipoItemSoftware;
import ode.controleCaracteristica.cdp.Importancia;
import ode.controleCaracteristica.cdp.PerspectivaAnalise;
import ode.controleCaracteristica.cdp.PossivelValor;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;
import ode.controleCaracteristica.cgd.ImportanciaDAO;
import ode.controleCaracteristica.cgt.AplCadastrarCaracteristica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listitem;

@Controller
public class CtrlCaracteristica extends CtrlCRUD<Caracteristica> {

	private static final long serialVersionUID = -2967662654799321521L;

	//private Caracteristica caracteristicaSelecionada;
	
	@Autowired
	private AplCadastrarCaracteristica aplCadastrarCaracteristica;
	
	
	@Override
	public void iniciar() {
		super.iniciar();

	}
	

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<Caracteristica> definirAplCRUD() {
		return aplCadastrarCaracteristica;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCRUDCaracteristica();

	}

	@Override
	public Caracteristica factoryObjetoDados() {
		return new CaracteristicaValorOrdenado();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosCaracteristica();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Caracteristica";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Caracteristicas";
	}
	
	public FormularioDadosCRUD<Caracteristica> getFormularioDadosCRUD(){
		return this.formularioDados;
	}
	
	public void acaoSalvarIncompleto() {
		try {
			// passo os dados do formulario para o objeto antes de pega-lo
			formularioDados.atualizarObjeto();
			Caracteristica objetoCadastro = formularioDados.getObjetoCadastroDados();
			aplCadastrarCaracteristica.salvar(objetoCadastro);
			
		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}
	
	@Override
	public void acaoAbrir() {

		janDados = factoryJanelaSimples();
		String titulo = definirTituloJanelaDados();
		janDados.setTitle(titulo);

		formularioDados = definirFormularioCadastro();
		formularioDados.setControlador(this);
		formularioDados.setParent(janDados);
		formularioDados.configurarComponentes();

		try {
				// Se nao eh novo, tenho que recuperar o objeto do banco
				Caracteristica objetoSelecionado = painelCRUD.getListagem().getSelecionado();
				// atualizo a referencia do objetoSelecionado
				
				if(objetoSelecionado instanceof CaracteristicaValorOrdenado){
					objetoSelecionado = aplCadastrarCaracteristica.recuperarPorIdValorOrdenado(objetoSelecionado.getId());
				}
				else{
					objetoSelecionado = aplCadastrarCaracteristica.recuperarPorIdValorNaoOrdenado(objetoSelecionado.getId());
				}
				
				formularioDados.setObjetoCadastroDados(objetoSelecionado);
				// copio os dados do objeto pro formulario
				formularioDados.atualizarDadosTela();
				janDados.mostrar();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}
	

	protected void excluir(Set<Listitem> objetosSelecionados) {

		// Exclui os itens selecionados

		Set<Caracteristica> objetosConvertidosSelecionados = converterObjetos(objetosSelecionados);
		Collection<Importancia> importancia;
		ImportanciaDAO imp = (ImportanciaDAO) SpringUtil.getApplicationContext().getBean(ImportanciaDAO.class);
		
			try{
				for(Caracteristica c: objetosConvertidosSelecionados){
					importancia = imp.estaNumaImportancia(c.getId());	
					if(importancia.isEmpty()){
						getAplCRUD().excluir(c);
						atualizarPesquisa();
					}
					else{
						mostrarJanelaInformacao("Essa caracteristica está sendo usada numa Perspectiva de Analise. Não é possível excluí-la.");
					}
				}
			} catch (Exception e) {
				CtrlExcecoes
				.exibirJanelaErro("Não foi possivel excluir os objetos");
				e.printStackTrace();
				CtrlExcecoes.tratarExcecao(e);
			}
	}


}
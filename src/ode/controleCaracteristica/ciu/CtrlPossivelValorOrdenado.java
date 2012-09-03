package ode.controleCaracteristica.ciu;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.PossivelValor;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;
import ode.controleCaracteristica.cdp.PossivelValorOrdenado;
import ode.controleCaracteristica.cgt.AplCadastrarPossivelValorOrdenado;

@Controller
public class CtrlPossivelValorOrdenado extends CtrlCRUD<PossivelValorOrdenado>{

	private static final long serialVersionUID = -2967662654799321521L;

	private Caracteristica caracteristica = null;
	
	@Autowired
	private AplCadastrarPossivelValorOrdenado aplCadastrarPossivelValorOrdenado;
	
	@Override
	public void iniciar() {
			super.configurarComponentes();
			mostrarJanelaPrincipal();

	}
	
	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<PossivelValorOrdenado> definirAplCRUD() {
		return aplCadastrarPossivelValorOrdenado;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCRUDPossivelValorOrdenado();

	}

	@Override
	public PossivelValorOrdenado factoryObjetoDados() {
		return new PossivelValorOrdenado();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosPossivelValorOrdenado();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Possivel ValorOrdenado";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Possiveis Valores Ordenados";
	}
	
	
	// Terei que modificar para que a janela fique na aba de possiveis valores...
	@Override
	public void mostrarJanelaPrincipal() {
			CtrlCaracteristica ctrlC = (CtrlCaracteristica) SpringUtil.getApplicationContext().getBean(CtrlCaracteristica.class);
			((FormDadosCaracteristica) ctrlC.getFormularioDadosCRUD()).incluirTabPossiveisValores();
			
			atualizarPesquisa();
		}
	
	public PainelCRUD<PossivelValorOrdenado> getPainelCRUD(){
		return this.painelCRUD;
	}
	
	
	public void setCaracteristica(Caracteristica c){
		this.caracteristica = c;
	}
	
	public Caracteristica getCaracteristica(){
		return this.caracteristica;
	}
	
	@Override
	public void acaoSalvar() {
		try {
			// passo os dados do formulario para o objeto antes de pega-lo
			formularioDados.atualizarObjeto();
			PossivelValorOrdenado objetoCadastro = (PossivelValorOrdenado) formularioDados.getObjetoCadastroDados();
			aplCadastrarPossivelValorOrdenado.salvar(objetoCadastro);
			
			getCaracteristica().addPossivelValor(objetoCadastro);
			CtrlCaracteristica ctrlC = (CtrlCaracteristica) SpringUtil.getApplicationContext().getBean(CtrlCaracteristica.class);
			ctrlC.acaoSalvarIncompleto();
			
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}
	
	
	
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
				PossivelValorOrdenado objetoSelecionado = painelCRUD.getListagem().getSelecionado();
				// atualizo a referencia do objetoSelecionado
				
				objetoSelecionado = aplCadastrarPossivelValorOrdenado.recuperarPorId(objetoSelecionado.getId());

				formularioDados.setObjetoCadastroDados(objetoSelecionado);
				// copio os dados do objeto pro formulario
				formularioDados.atualizarDadosTela();

			janDados.mostrar();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}
	
	
	@Override
	public void atualizarPesquisa() {
		if(caracteristica!=null){
			Collection<PossivelValor> objetos = caracteristica.getPossiveisValores();
			Collection<PossivelValorOrdenado> objetosConvertidos = new HashSet<PossivelValorOrdenado>();
			for(PossivelValor p : objetos){
				objetosConvertidos.add((PossivelValorOrdenado)p);
			}	
			
			painelCRUD.getListagem().atualizar(objetosConvertidos);
		}
	}
	


}
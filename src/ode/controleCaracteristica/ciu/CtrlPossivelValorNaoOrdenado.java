package ode.controleCaracteristica.ciu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listitem;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.PossivelValor;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;
import ode.controleCaracteristica.cdp.Similaridade;
import ode.controleCaracteristica.cgt.AplCadastrarPossivelValorNaoOrdenado;

@Controller
public class CtrlPossivelValorNaoOrdenado extends CtrlCRUD<PossivelValorNaoOrdenado>{

	private static final long serialVersionUID = -2967662654799321521L;

	private Caracteristica caracteristica = null;
	
	@Autowired
	private AplCadastrarPossivelValorNaoOrdenado aplCadastrarPossivelValorNaoOrdenado;
	
	@Override
	public void iniciar() {
			super.configurarComponentes();
			mostrarJanelaPrincipal();
	}
	
	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<PossivelValorNaoOrdenado> definirAplCRUD() {
		return aplCadastrarPossivelValorNaoOrdenado;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCRUDPossivelValorNaoOrdenado();

	}

	@Override
	public PossivelValorNaoOrdenado factoryObjetoDados() {
		return new PossivelValorNaoOrdenado();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosPossivelValorNaoOrdenado();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Possivel Valor Não Ordenado";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Possiveis Valores Não Ordenado";
	}
	
	
	// Terei que modificar para que a janela fique na aba de possiveis valores...
	@Override
	public void mostrarJanelaPrincipal() {
			CtrlCaracteristica ctrlC = (CtrlCaracteristica) SpringUtil.getApplicationContext().getBean(CtrlCaracteristica.class);
			((FormDadosCaracteristica) ctrlC.getFormularioDadosCRUD()).incluirTabPossiveisValores();
			
			atualizarPesquisa();
		}
	
	public PainelCRUD<PossivelValorNaoOrdenado> getPainelCRUD(){
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
			PossivelValorNaoOrdenado objetoCadastro = formularioDados.getObjetoCadastroDados();
			aplCadastrarPossivelValorNaoOrdenado.salvar(objetoCadastro);
			
			getCaracteristica().addPossivelValor(objetoCadastro);
			CtrlCaracteristica ctrlC = (CtrlCaracteristica) SpringUtil.getApplicationContext().getBean(CtrlCaracteristica.class);
			ctrlC.acaoSalvarIncompleto();
			
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}
	
	public void definirTabelaSimilaridade(ModoExibicao modo){
		
		new JanelaDefinirTabelaSimilaridade(this, modo);
	}

	public void definirSimilaridade(){
		
		Set<Listitem> itensSelecionados = painelCRUD.getListagem()
				.getSelecionados();
		Set<PossivelValorNaoOrdenado> objetosConvertidosSelecionados = converterObjetos(itensSelecionados);
		
		try {
			// verifica se o numero de itens selecionados eh maior que zero.
			if (objetosConvertidosSelecionados.size() == 2) {
					abrirJanelaDeterminarSimilaridade(objetosConvertidosSelecionados);
			} else {
				mostrarJanelaInformacao("É possível determinar a similaridade somente entre dois valores! Selecione DOIS valores, e então click em Similaridade.");
			}
		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}

	public void salvarSimilaridade(Similaridade simi) {
		 aplCadastrarPossivelValorNaoOrdenado.salvarSimilaridade(simi);		
	}

	public void abrirJanelaDeterminarSimilaridade(
			Set<PossivelValorNaoOrdenado> pvalor) {
		new JanelaDeterminarSimilaridade(this, pvalor);
		
	}
	
	@Override
	public void atualizarPesquisa() {
		
		if(caracteristica!=null){
			Collection<PossivelValor> objetos = caracteristica.getPossiveisValores();
			Collection<PossivelValorNaoOrdenado> objetosConvertidos = new HashSet<PossivelValorNaoOrdenado>();
			for(PossivelValor p : objetos){
				objetosConvertidos.add((PossivelValorNaoOrdenado)p);
			}	
			
			painelCRUD.getListagem().atualizar(objetosConvertidos);
		}
	}


}
package ode.controleUsuario.cci;

import java.util.Collection;
import java.util.Set;

import ode.controleProcesso.cgt.AplCadastrarRecursoHumano;
import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.controleUsuario.cgt.AplCadastrarPerfilAcesso;
import ode.controleUsuario.cgt.AplCadastrarUsuario;
import ode.controleUsuario.cih.UsuarioFormularioDadosCRUD;
import ode.controleUsuario.cih.UsuarioPainelCRUD;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD.ModoExibicao;
import ode.nucleo.crud.cih.PainelCRUD;
import ode.nucleo.excecao.CtrlExcecoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Listitem;

@Controller(UsuarioCtrlCRUD.NOME)
public class UsuarioCtrlCRUD extends CtrlCRUD<NucleoUserDetails> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NOME = "NucleoUserDetailsCtrlCRUD";

	@Autowired
	AplCadastrarUsuario nucleoAplCadastrarNucleoUserDetails;

	@Autowired
	public AplCadastrarRecursoHumano aplCadastrarRecursoHumano;
	
	@Autowired
	public AplCadastrarPerfilAcesso aplCadastrarPerfilAcesso;

	@Override
	public String definirTituloJanelaDados() {
		return "Usuário";
	}

	@Override
	public NucleoAplCadastroBase<NucleoUserDetails> definirNucleoAplCadastroBase() {
		// Está usando outra apl.
		return null;
	}

	/**
	 * Não usa a nucleoaplcadastrobase.
	 */
	protected boolean estaUsandoNucleoAplCadastroBase(){
		return false;
	}

	@Override
	public PainelCRUD<NucleoUserDetails> definirPainelCRUD() {
		return new UsuarioPainelCRUD();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Usuários";
	}

	@Override
	public FormularioDadosCRUD<NucleoUserDetails> definirFormularioCadastro() {
		return new UsuarioFormularioDadosCRUD();
	}

	@Override
	public NucleoUserDetails factoryObjetoDados() {
		return new NucleoUserDetails();
	}

	public void atualizarPesquisa() {
		Collection<NucleoUserDetails> objetos = nucleoAplCadastrarNucleoUserDetails.recuperarTodos();		
		painelCRUD.getListagem().atualizar(objetos);
	}

	protected void excluir(Set<Listitem> objetosSelecionados) {

		// Exclui os itens selecionados

		Set<NucleoUserDetails> objetosConvertidosSelecionados = converterObjetos(objetosSelecionados);
		for (NucleoUserDetails nucleoUserDetails : objetosConvertidosSelecionados){
			nucleoAplCadastrarNucleoUserDetails.excluir(nucleoUserDetails);
		}

		// apos excluir recupera os objetos para pesquisa

		atualizarPesquisa();

	}

	@Override
	public void mostrarFormulario(ModoExibicao modoExibicao) {

		janDados = factoryJanelaSimples();
		String titulo = definirTituloJanelaDados();
		janDados.setTitle(titulo);

		formularioDados = definirFormularioCadastro();
		formularioDados.setControlador(this);
		formularioDados.setParent(janDados);
		formularioDados.configurarComponentes();
		formularioDados.setModoExibicao(modoExibicao);

		try {

			if (modoExibicao == ModoExibicao.NOVO) {
				formularioDados.setObjetoCadastroDados(factoryObjetoDados());
			} else {
				// Se nao eh novo, tenho que recuperar o objeto do banco
				NucleoUserDetails objetoSelecionado = painelCRUD.getListagem()
				.getSelecionado();
				// atualizo a referencia do objetoSelecionado
				objetoSelecionado = nucleoAplCadastrarNucleoUserDetails
				.recuperarPorId(objetoSelecionado.getId());

				formularioDados.setObjetoCadastroDados(objetoSelecionado);
				// copio os dados do objeto pro formulario
				formularioDados.atualizarDadosTela();
			}

			janDados.mostrar();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}

	@Override
	public void acaoSalvar() {
		try {
			// os dados do formulario para o objeto antes de pega-lo
			formularioDados.atualizarObjeto();
			NucleoUserDetails objetoCadastro = formularioDados.getObjetoCadastroDados();
			nucleoAplCadastrarNucleoUserDetails.salvar(objetoCadastro);
			// /atualiza a pesquisa e fecha a janela
			//atualizarPesquisa(painelCRUD.getListagemPaginada().getPagina());
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}

	public AplCadastrarUsuario getNucleoAplCadastrarNucleoUserDetails() {
		return nucleoAplCadastrarNucleoUserDetails;
	}

	public void setNucleoAplCadastrarNucleoUserDetails(
			AplCadastrarUsuario nucleoAplCadastrarNucleoUserDetails) {
		this.nucleoAplCadastrarNucleoUserDetails = nucleoAplCadastrarNucleoUserDetails;
	}

	public AplCadastrarRecursoHumano getAplCadastrarRecursoHumano() {
		return aplCadastrarRecursoHumano;
	}

	public void setAplCadastrarRecursoHumano(
			AplCadastrarRecursoHumano aplCadastrarRecursoHumano) {
		this.aplCadastrarRecursoHumano = aplCadastrarRecursoHumano;
	}

	public AplCadastrarPerfilAcesso getAplCadastrarPerfilAcesso() {
		return aplCadastrarPerfilAcesso;
	}

	public void setAplCadastrarPerfilAcesso(
			AplCadastrarPerfilAcesso aplCadastrarPerfilAcesso) {
		this.aplCadastrarPerfilAcesso = aplCadastrarPerfilAcesso;
	}

}

package ode.controleUsuario.cci;

import java.util.Collection;
import java.util.Set;

import ode.controleProcesso.cdp.RecursoHumano;
import ode.controleProcesso.cgt.AplCadastrarRecursoHumano;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgt.AplCadastrarUsuario;
import ode.controleUsuario.cih.FormDadosUsuario;
import ode.controleUsuario.cih.PainelCRUDUsuario;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD.ModoExibicao;
import ode.nucleo.crud.cih.PainelCRUD;
import ode.nucleo.excecao.CtrlExcecoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Listitem;

@Controller(CtrlUsuarioCRUD.NOME)
public class CtrlUsuarioCRUD extends CtrlCRUD<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NOME = "UsuarioCtrlCRUD";

	@Autowired
	AplCadastrarUsuario aplCadastrarUsuario;

	@Autowired
	public AplCadastrarRecursoHumano aplCadastrarRecursoHumano;
	
	@Override
	public String definirTituloJanelaDados() {
		return "Usuário";
	}

	@Override
	public AplBase<Usuario> definirNucleoAplCadastroBase() {
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
	public PainelCRUD<Usuario> definirPainelCRUD() {
		return new PainelCRUDUsuario();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Usuários";
	}

	@Override
	public FormularioDadosCRUD<Usuario> definirFormularioCadastro() {
		return new FormDadosUsuario();
	}

	@Override
	public Usuario factoryObjetoDados() {
		return new Usuario();
	}

	public void atualizarPesquisa() {
		Collection<Usuario> objetos = aplCadastrarUsuario.recuperarTodos();		
		painelCRUD.getListagem().atualizar(objetos);
	}

	protected void excluir(Set<Listitem> objetosSelecionados) {

		// Exclui os itens selecionados

		Set<Usuario> objetosConvertidosSelecionados = converterObjetos(objetosSelecionados);
		for (Usuario usuario : objetosConvertidosSelecionados){
			aplCadastrarUsuario.excluir(usuario);
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
				Usuario objetoSelecionado = painelCRUD.getListagem()
				.getSelecionado();
				// atualizo a referencia do objetoSelecionado
				objetoSelecionado = aplCadastrarUsuario
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
			Usuario objetoCadastro = formularioDados.getObjetoCadastroDados();
			aplCadastrarUsuario.salvar(objetoCadastro);
			// /atualiza a pesquisa e fecha a janela
			//atualizarPesquisa(painelCRUD.getListagemPaginada().getPagina());
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}

	public AplCadastrarUsuario getAplCadastrarUsuario() {
		return aplCadastrarUsuario;
	}

	public void setAplCadastrarUsuario(
			AplCadastrarUsuario aplCadastrarUsuario) {
		this.aplCadastrarUsuario = aplCadastrarUsuario;
	}

	public AplCadastrarRecursoHumano getAplCadastrarRecursoHumano() {
		return aplCadastrarRecursoHumano;
	}

	public void setAplCadastrarRecursoHumano(
			AplCadastrarRecursoHumano aplCadastrarRecursoHumano) {
		this.aplCadastrarRecursoHumano = aplCadastrarRecursoHumano;
	}
	
	public Collection<RecursoHumano> listarRecursosHumanos() {
		return getAplCadastrarRecursoHumano().recuperarTodos();
	}

}

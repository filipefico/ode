package ode.nucleo.crud.cci;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ode.nucleo.cci.CtrlBase;
import ode.nucleo.cgd.ObjetoPersistente;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD.ModoExibicao;
import ode.nucleo.crud.cih.JanelaSimples;
import ode.nucleo.crud.cih.PainelCRUD;
import ode.nucleo.excecao.CtrlExcecoes;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

public abstract class CtrlCRUD<T extends ObjetoPersistente> extends CtrlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Interface de aplica√ß√£oo usada para cadastros b√°sicos (CRUD)
	 */
	private NucleoAplCadastroBase<T> nucleoAplCadastroBase;

	protected PainelCRUD<T> painelCRUD;

	protected FormularioDadosCRUD<T> formularioDados;

	protected JanelaSimples janDados;

	protected String larguraJanPrincipal = "590px";

	public CtrlCRUD() {
	}

	public void iniciar() {
		configurarComponentes();
		mostrarJanelaPrincipal();
	}

	public void configurarComponentes() {

		definirComponentesExtensao();
		validarComponentesExtensao();

		painelCRUD.setControlador(this);
		painelCRUD.configurarComponentes();
	}

	protected void definirComponentesExtensao() {
		nucleoAplCadastroBase = definirNucleoAplCadastroBase();
		painelCRUD = definirPainelCRUD();
	}

	protected void validarComponentesExtensao() {

		if (painelCRUD == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao("painelCRUD",
					this.getClass());

		if (estaUsandoNucleoAplCadastroBase()){
			if (nucleoAplCadastroBase == null)
				throw CtrlExcecoes.factoryExcecaoDefinicao("nucleoAplCadastroBase",
						this.getClass());
		}

	}

	/**
	 * Indica se o controlador est· usuando a nucleoaplcadastrobase. 
	 * As vezes, n„o È necess·rio utiliz·-la ou pode optar por usar outra apl.
	 * Para quando a nucleoaplcadastrobase for nula, este mÈtodo deve ser sobrescrito retornando false.
	 *  
	 * @return verdadeiro, caso esteja usuando a nucleoaplcadastrobase. Falso, caso contr·rio.
	 */
	protected boolean estaUsandoNucleoAplCadastroBase(){
		return true;
	}

	public void setNucleoAplCadastroBase(
			NucleoAplCadastroBase<T> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBase = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<T> getNucleoAplCadastroBase() {
		return nucleoAplCadastroBase;
	}

	public void mostrarJanelaPrincipal() {
		JanelaSimples jan = factoryJanelaSimples();
		painelCRUD.setParent(jan);
		String titulo = definirTituloJanelaPrincipal();
		jan.setTitle(titulo);
		jan.setWidth(larguraJanPrincipal);

		jan.mostrar();

		atualizarPesquisa();
	}

	public void atualizarPesquisa() {
		Collection<T> objetos =getNucleoAplCadastroBase().recuperarTodos();		
		painelCRUD.getListagem().atualizar(objetos);

	}

	public void acaoExcluir() {
		validarExcluir();
	}

	public void validarExcluir() {
		Set<Listitem> itensSelecionados = painelCRUD.getListagem()
		.getSelecionados();
		try {
			// verifica se o n√∫mero de itens selecionados √© maior que zero.
			if (itensSelecionados.size() > 0) {

				String mensagemConfirmacao;

				if (itensSelecionados.size() > 1) {
					mensagemConfirmacao = NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_CONFIRMACAO_EXCLUSAO_PLURAL);
				} else {
					mensagemConfirmacao = NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_CONFIRMACAO_EXCLUSAO_SINGULAR);
				}

				confirmaSimNao(mensagemConfirmacao, new EventListener() {

					public void onEvent(Event evt) {
						switch (((Integer) evt.getData()).intValue()) {
						case Messagebox.YES:

							excluir(painelCRUD.getListagem().getSelecionados());
							break; //the No button is pressed

						case Messagebox.NO:

							break; //the No button is pressed

						default:


						}


					}
				});

			} else {

				mostrarJanelaInformacao(NucleoMensagens
						.getMensagem(NucleoMensagens.MSG_NENHUM_ELEMENTO_SELECIONADO));

			}

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}

	protected void excluir(Set<Listitem> objetosSelecionados) {

		// Exclui os itens selecionados

		Set<T> objetosConvertidosSelecionados = converterObjetos(objetosSelecionados);
		try {
			getNucleoAplCadastroBase().excluir(objetosConvertidosSelecionados);
			// apos excluir recupera os objetos para pesquisa

			atualizarPesquisa();

		} catch (NucleoRegraNegocioExcecao e) {
			CtrlExcecoes
			.exibirJanelaErro("N„o foi possivel excluir os objetos");

		} 

	}

	@SuppressWarnings("unchecked")
	protected Set<T> converterObjetos(Set<Listitem> itensSelecionados) {

		Set<T> listaRetorno = new HashSet<T>();
		Iterator<Listitem> itItensSelecionados = itensSelecionados.iterator();

		while (itItensSelecionados.hasNext()) {
			Listitem itemSelecionado = itItensSelecionados.next();
			T objeto = (T) itemSelecionado.getValue();
			listaRetorno.add(objeto);
		}

		return listaRetorno;

	}

	public void acaoNovo() {

		mostrarFormulario(ModoExibicao.NOVO);
	}

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
				T objetoSelecionado = painelCRUD.getListagem()
				.getSelecionado();
				// atualizo a referencia do objetoSelecionado
				objetoSelecionado = nucleoAplCadastroBase
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

	public void acaoAbrir() {
		mostrarFormulario(ModoExibicao.EDITAR);

	}

	public void acaoSalvar() {
		try {
			// passo os dados do formulario para o objeto antes de pega-lo
			formularioDados.atualizarObjeto();
			T objetoCadastro = formularioDados.getObjetoCadastroDados();
			nucleoAplCadastroBase.salvar(objetoCadastro);
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}

	public abstract String definirTituloJanelaDados();

	public abstract NucleoAplCadastroBase<T> definirNucleoAplCadastroBase();

	public abstract PainelCRUD<T> definirPainelCRUD();

	public abstract String definirTituloJanelaPrincipal();

	public abstract FormularioDadosCRUD<T> definirFormularioCadastro();

	public abstract T factoryObjetoDados();

	public String getLarguraJanPrincipal() {
		return larguraJanPrincipal;
	}

	public void setLarguraJanPrincipal(String larguraJanPrincipal) {
		this.larguraJanPrincipal = larguraJanPrincipal;
	}

}

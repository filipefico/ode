package ode.problema.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.problema.cdp.KCriterioSelecaoSolucao;
import org.zkoss.zul.Textbox;



public class FormDadosKCriterioSelecaoSolucao extends FormularioDadosCRUD<KCriterioSelecaoSolucao> {

	private static final long serialVersionUID = 3019782167391338963L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
//	private NucleoListbox<KValorCriterioSelecaoSolucao> listboxValores = new NucleoListbox<KValorCriterioSelecaoSolucao>();
//	private List<KValorCriterioSelecaoSolucao> listaKVl;
//	private KValorCriterioSelecaoSolucao valorSelecionado;

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
		return listaTabs;
	}

	private NucleoTab definirTabDadosCadastro() {
		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("240px");
		tbNome.setMaxlength(80);
		gridDadosCadastro
				.adicionarLinhaObrigatoria(
						NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
						tbNome);
		
		tbDescricao.setWidth("240px");
		tbDescricao.setMaxlength(400);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);

//		listaKVl = new ArrayList<KValorCriterioSelecaoSolucao>(((CtrlKCriterioSelecaoSolucaoCRUD)this.getControlador()).listarKValorCriterioSelecaoSolucao());

//		listboxValores.setWidth("240px");
//		listboxValores.setCheckmark(true);
//		listboxValores.setMultiple(true);

//		for (KValorCriterioSelecaoSolucao objeto : listaKVl) {
//			Listitem li = new Listitem(objeto.getNome(), objeto);
//			listboxValores.appendChild(li);
//		}
	
//		gridDadosCadastro.adicionarLinha("Valores", listboxValores);

		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		return tabDadosCadastro;
	}



	@Override
	protected void preencherDadosObjeto(KCriterioSelecaoSolucao objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
//		objeto.setValor(listboxValores.getObjetosSelecionados());
	}

	@Override
	protected void preencherDadosTela(KCriterioSelecaoSolucao objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
//		listboxValores.setObjetosSelecionados(objeto.getValor());
//		valorSelecionado = listboxValores.getObjetoSelecionado();
//	    listboxValores.getItemAtIndex(0).setDisabled(false);
//	    listboxValores.getItem(valorSelecionado).setDisabled(true);
	}

}


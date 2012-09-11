package ode.conhecimento.requisito.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimento.requisito.cci.CtrlCRUDCategoriaRequisito;
import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cdp.TipoRequisito;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Textbox;

public class FormDadosCategoriaRequisito extends
		FormularioDadosCRUD<CategoriaRequisito> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Combobox comboSupercategoria = new Combobox();
	private Combobox comboTipo = new Combobox();

	CtrlCRUDCategoriaRequisito ctrlCategoriaRequisito = (CtrlCRUDCategoriaRequisito) SpringUtil
			.getApplicationContext().getBean(CtrlCRUDCategoriaRequisito.class);

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("100%");
		tbNome.setMaxlength(100);
		gridDadosCadastro
				.adicionarLinha(
						NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
						tbNome);

		tbDescricao.setWidth("100%");
		tbDescricao.setMaxlength(500);
		tbDescricao.setRows(5);
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),
				tbDescricao);

		comboTipo.setWidth("100%");
		comboTipo.setReadonly(true);
		preencherComboTipo();
		comboTipo.addEventListener("onChange", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				preencherComboSupercategoria();
			}
		});
		gridDadosCadastro.adicionarLinha("Tipo", comboTipo);

		comboSupercategoria.setWidth("100%");
		comboSupercategoria.setReadonly(true);
		gridDadosCadastro.adicionarLinha("Supercategoria", comboSupercategoria);

		// adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	private void preencherComboTipo() {
		TipoRequisito[] tiposRequisito = TipoRequisito.values();

		for (TipoRequisito tipoRequisito : tiposRequisito) {
			Comboitem item = new Comboitem();

			item.setLabel(tipoRequisito.getNome());
			item.setValue(tipoRequisito);

			item.setParent(comboTipo);
		}

		comboTipo.setSelectedIndex(0);
	}

	private void preencherComboSupercategoria() {
		comboSupercategoria.getChildren().clear();

		TipoRequisito tipoRequisito = (TipoRequisito) comboTipo
				.getSelectedItem().getValue();
		Collection<CategoriaRequisito> categorias = ctrlCategoriaRequisito
				.obterCategoriasPorTipo(tipoRequisito);

		for (CategoriaRequisito categoriaRequisito : categorias) {
			if (!categoriaRequisito.equals(getObjetoCadastroDados())) {
				Comboitem item = new Comboitem();

				item.setLabel(categoriaRequisito.getNome());
				item.setValue(categoriaRequisito);

				item.setParent(comboSupercategoria);
			}
		}
		if (comboSupercategoria.getItemCount() > 0)
			comboSupercategoria.setSelectedIndex(0);
	}

	@Override
	protected void preencherDadosObjeto(CategoriaRequisito objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setTipoRequisito((TipoRequisito) comboTipo.getSelectedItem()
				.getValue());
		if (comboSupercategoria.getSelectedItem() == null)
			return;
		objeto.setSuperCategoria((CategoriaRequisito) comboSupercategoria
				.getSelectedItem().getValue());
	}

	@Override
	protected void preencherDadosTela(CategoriaRequisito objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		comboTipo.setValue(objeto.getTipoRequisito().getNome());
		comboTipo.setDisabled(true);
		Events.sendEvent("onChange", comboTipo, null);
		if (objeto.getSuperCategoria() == null)
			return;
		comboSupercategoria.setValue(objeto.getSuperCategoria().getNome());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbDescricao.setConstraint("no empty");
		comboTipo.setConstraint("no empty");
	}
}
package ode.gerenciaRequisitos.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._infraestruturaBase.ciu.DualListBox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimento.requisito.cci.CtrlCRUDCategoriaRequisito;
import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cdp.TipoRequisito;
import ode.conhecimento.requisito.cgd.TipoRequisitoDAO;
import ode.gerenciaRequisitos.cdp.Prioridade;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.gerenciaRequisitos.cgd.PrioridadeDAO;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

public class FormDadosRequisito extends FormularioDadosCRUD<Requisito> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CtrlCRUDCategoriaRequisito categoriaRequisitoCtrl = SpringUtil
			.getApplicationContext().getBean(CtrlCRUDCategoriaRequisito.class);

	private TipoRequisitoDAO tipoRequisitoDao = SpringUtil
			.getApplicationContext().getBean(TipoRequisitoDAO.class);

	private PrioridadeDAO prioridadeDao = SpringUtil.getApplicationContext()
			.getBean(PrioridadeDAO.class);

	private RecursoHumanoDAO daoRecursoHumano = (RecursoHumanoDAO) SpringUtil
			.getApplicationContext().getBean(RecursoHumanoDAO.class);

	private Textbox tbDescricao = new Textbox();
	private Combobox comboPrioridade = new Combobox();
	private Combobox comboTipo = new Combobox();
	private Combobox comboCategoria = new Combobox();
	private Combobox comboProjeto = new Combobox();
	private Datebox dataCriacao = new Datebox();
	private Textbox textboxIdentificador = new Textbox();

	@SuppressWarnings("serial")
	private DualListBoxRecursoHumano dlbResponsaveis = new DualListBoxRecursoHumano() {
		@Override
		public List<RecursoHumano> listaSelecionados() {
			List<RecursoHumano> recursos = new ArrayList<RecursoHumano>();
			if (objetoCadastro == null)
				return recursos;
			recursos.addAll(objetoCadastro.getResponsaveis());
			return recursos;
		}
	};

	@SuppressWarnings("serial")
	private DualListBoxRecursoHumano dlbInteressados = new DualListBoxRecursoHumano() {
		@Override
		public List<RecursoHumano> listaSelecionados() {
			List<RecursoHumano> recursos = new ArrayList<RecursoHumano>();
			if (objetoCadastro == null)
				return recursos;
			recursos.addAll(objetoCadastro.getInteressados());
			return recursos;
		}
	};

	private Requisito objetoCadastro;

	public FormDadosRequisito() {
		objetoCadastro = getObjetoCadastroDados();
	}

	public abstract class DualListBoxRecursoHumano extends
			DualListBox<RecursoHumano> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2908388768278789258L;

		@Override
		public Listhead defineCabecalho() {
			Listhead header = new Listhead();
			new Listheader("Nome").setParent(header);
			return header;
		}

		@Override
		public Listitem criaItem(RecursoHumano objeto) {
			Listitem item = new Listitem();
			new Listcell(objeto.getNome()).setParent(item);
			item.setValue(objeto);
			return item;
		}

		@Override
		public List<RecursoHumano> listaDisponiveis() {
			List<RecursoHumano> recursos = new ArrayList<RecursoHumano>();
			recursos.addAll(daoRecursoHumano.recuperarTodos());
			return recursos;
		}
	}

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();

		comboProjeto.setWidth("95%");
		comboProjeto.setDisabled(true);
		gridDadosCadastro.adicionarLinha("Projeto", comboProjeto);

		dataCriacao.setWidth("95%");
		dataCriacao.setDisabled(true);
		gridDadosCadastro.adicionarLinha("Data de Criação", dataCriacao);

		textboxIdentificador.setWidth("95%");
		textboxIdentificador.setDisabled(true);
		gridDadosCadastro.adicionarLinha("Identificador", textboxIdentificador);

		tbDescricao.setWidth("95%");
		tbDescricao.setMaxlength(500);
		tbDescricao.setRows(5);
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),
				tbDescricao);

		comboPrioridade.setWidth("95%");
		comboPrioridade.setReadonly(true);
		preencherComboPrioridade();
		gridDadosCadastro.adicionarLinha("Prioridade", comboPrioridade);

		comboTipo.setWidth("95%");
		comboTipo.setReadonly(true);
		preencherComboTipo();
		comboTipo.addEventListener("onChange", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				preencherComboCategoria();
			}
		});
		gridDadosCadastro.adicionarLinha("Tipo", comboTipo);

		comboCategoria.setWidth("95%");
		comboCategoria.setReadonly(true);
		gridDadosCadastro.adicionarLinha("Categoria", comboCategoria);

		// Adiciona a grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		NucleoTab tabResponsaveis = new NucleoTab();
		tabResponsaveis.setNomeTab("Responsáveis");
		tabResponsaveis.setConteudoTab(dlbResponsaveis);
		dlbResponsaveis.setCheckmark(true);
		dlbResponsaveis.setWidth("480px");

		NucleoTab tabInteressados = new NucleoTab();
		tabInteressados.setNomeTab("Interessados");
		tabInteressados.setConteudoTab(dlbInteressados);
		dlbInteressados.setCheckmark(true);
		dlbInteressados.setWidth("480px");

		listaTabs.add(tabDadosCadastro);
		listaTabs.add(tabResponsaveis);
		listaTabs.add(tabInteressados);

		return listaTabs;
	}

	private void preencherComboPrioridade() {
		comboPrioridade.getChildren().clear();

		Collection<Prioridade> prioridades = prioridadeDao.recuperarTodos();

		for (Prioridade prioridade : prioridades) {
			Comboitem item = new Comboitem();

			item.setLabel(prioridade.getNome());
			item.setValue(prioridade);

			item.setParent(comboPrioridade);
		}
		if (prioridades.size() > 0){
			comboPrioridade.setSelectedIndex(0);
		}
	}

	private void preencherComboTipo() {
		comboTipo.getChildren().clear();

		Collection<TipoRequisito> tipos = tipoRequisitoDao.recuperarTodos();

		for (TipoRequisito tipoRequisito : tipos) {
			Comboitem item = new Comboitem();

			item.setLabel(tipoRequisito.getNome());
			item.setValue(tipoRequisito);

			item.setParent(comboTipo);
		}
		if (tipos.size() > 0){
			comboTipo.setSelectedIndex(0);
		}
	}

	private void preencherComboCategoria() {
		comboCategoria.getChildren().clear();

		if (comboTipo.getSelectedItem() == null)
			return;
		TipoRequisito tipo = (TipoRequisito) comboTipo.getSelectedItem()
				.getValue();

		Collection<CategoriaRequisito> categorias = categoriaRequisitoCtrl
				.obterCategoriasPorTipo(tipo);
		for (CategoriaRequisito categoriaRequisito : categorias) {
			Comboitem item = new Comboitem();

			item.setLabel(categoriaRequisito.getNome());
			item.setValue(categoriaRequisito);

			item.setParent(comboCategoria);
		}
	}

	@Override
	protected void preencherDadosObjeto(Requisito objeto) {
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setPrioridade((Prioridade) comboPrioridade.getSelectedItem()
				.getValue());
		objeto.setTipoRequisito((TipoRequisito) comboTipo.getSelectedItem()
				.getValue());
		if (comboCategoria.getSelectedItem() != null)
			objeto.setCategoria((CategoriaRequisito) comboCategoria
					.getSelectedItem().getValue());
		objeto.setProjeto(NucleoContexto.recuperarProjeto());

		objeto.setResponsaveis(dlbResponsaveis.getSelecionados());
		objeto.setInteressados(dlbInteressados.getSelecionados());
	}

	@Override
	protected void preencherDadosTela(Requisito objeto)
			throws NucleoRegraNegocioExcecao {
		tbDescricao.setValue(objeto.getDescricao());
		comboPrioridade.setValue(objeto.getPrioridade().getNome());
		comboTipo.setValue(objeto.getTipoRequisito().getNome());
		Events.sendEvent("onChange", comboTipo, null);
		if (objeto.getCategoria() != null)
			comboCategoria.setValue(objeto.getCategoria().getNome());

		// preencherComboboxCategoria();
		// NucleoUtil.selecionaComboitem(coCategoria, objeto.getCategoria());
		// dlbResponsaveis.atualizaListas(daoRecursoHumano.recuperarTodos(),
		// objeto.getResponsaveis());
		// dlbInteressados.atualizaListas(daoRecursoHumano.recuperarTodos(),
		// objeto.getInteressados());
	}

	@Override
	protected void configurarConstraints() {
		tbDescricao.setConstraint("no empty");
		comboPrioridade.setConstraint("no empty");
		comboTipo.setConstraint("no empty");
	}
}
package ode.uml.cih;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.uml.cci.CtrlCRUDClasse;
import ode.uml.cci.CtrlCRUDPacote;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;
import ode._infraestruturaBase.ciu.DualListBox;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class FormDadosCasoUso extends FormularioDadosCRUD<CasoUso> {
	/**
	 * 
	 */

	CtrlCRUDPacote ctrlPacote = (CtrlCRUDPacote) SpringUtil
			.getApplicationContext().getBean(CtrlCRUDPacote.class);
	CtrlCRUDClasse ctrlClasse = (CtrlCRUDClasse) SpringUtil
			.getApplicationContext().getBean(CtrlCRUDClasse.class);

	private static final long serialVersionUID = 1L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Combobox comboPacote;
	private Vbox vBox = new Vbox();
	private Button cadastrarClasse = new Button("Cadastrar Classe");
	private DualListBoxClasses dlbClasses = new DualListBoxClasses();
	private CasoUso objetoCadastro;

	public FormDadosCasoUso() {
		objetoCadastro = getObjetoCadastroDados();
	}

	public class DualListBoxClasses extends DualListBox<Classe> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4468969860355507041L;

		public Listhead defineCabecalho() {
			Listhead header = new Listhead();
			new Listheader("Nome").setParent(header);
			new Listheader("Descrição").setParent(header);
			return header;
		}

		public Listitem criaItem(Classe c) {
			Listitem item = new Listitem();
			new Listcell(c.getNome()).setParent(item);
			new Listcell(c.getDescricao()).setParent(item);
			item.setValue(c);
			return item;
		}

		@Override
		public List<Classe> listaDisponiveis() {
			List<Classe> classes = new ArrayList<Classe>();
			classes.addAll(ctrlClasse.obterPorProjeto(NucleoContexto
					.recuperarProjeto()));
			return classes;
		}

		@Override
		public List<Classe> listaSelecionados() {
			List<Classe> classes = new ArrayList<Classe>();
			if (objetoCadastro == null)
				return classes;
			classes.addAll(objetoCadastro.getClasses());
			return classes;
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
		tbNome.setWidth("285px");
		tbNome.setMaxlength(100);
		gridDadosCadastro
				.adicionarLinha(
						NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
						tbNome);

		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),
				tbDescricao);

		comboPacote = UmlZkossHelper.comboboxPacote();
		comboPacote.setWidth("100%");
		comboPacote.setReadonly(true);
		gridDadosCadastro.adicionarLinha("Pacote", comboPacote);

		NucleoTab tabDadosClasses = new NucleoTab();
		tabDadosClasses.setNomeTab("Classes Tratadas");
		dlbClasses.setParent(vBox);
		dlbClasses.setWidth("500px");
		dlbClasses.setHeight("250px");

		cadastrarClasse.addEventListener("onClick", new eventCadastrarClasse());
		cadastrarClasse.setParent(vBox);

		// adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		tabDadosClasses.setConteudoTab(vBox);
		listaTabs.add(tabDadosCadastro);
		listaTabs.add(tabDadosClasses);

		return listaTabs;
	}

	private class eventCadastrarClasse implements EventListener {
		public void onEvent(Event event) {
			CtrlCRUDClasse ctrlClasse = (CtrlCRUDClasse) SpringUtil
					.getApplicationContext().getBean(CtrlCRUDClasse.class);
			ctrlClasse.iniciar();
		}
	}

	@Override
	protected void preencherDadosObjeto(CasoUso objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setPacote((Pacote) comboPacote.getSelectedItem().getValue());
		objeto.setClasses(dlbClasses.getSelecionados());
	}

	@Override
	protected void preencherDadosTela(CasoUso objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbDescricao.setConstraint("no empty");
		comboPacote.setConstraint("no empty");
	}
}
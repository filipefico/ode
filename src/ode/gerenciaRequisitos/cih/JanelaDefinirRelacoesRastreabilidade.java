package ode.gerenciaRequisitos.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._infraestruturaBase.ciu.DualListBox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.gerenciaRequisitos.cci.CtrlCRUDRequisito;
import ode.gerenciaRequisitos.cci.CtrlDefinirRelacoesRastreabilidade;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cci.CtrlCRUDCasoUso;
import ode.uml.cci.CtrlCRUDPacote;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Pacote;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

@SuppressWarnings("serial")
public class JanelaDefinirRelacoesRastreabilidade extends JanelaSimples {

	Combobox coRequisito = new Combobox();
	Textbox tbDescricao = new Textbox();
	Toolbarbutton salvar = new Toolbarbutton();
	Window referencia = this;
	Toolbar barraFerramentas = new Toolbar();

	CtrlCRUDRequisito ctrlRequisito = (CtrlCRUDRequisito) SpringUtil
			.getApplicationContext().getBean(CtrlCRUDRequisito.class);
	CtrlCRUDPacote ctrlPacote = (CtrlCRUDPacote) SpringUtil
			.getApplicationContext().getBean(CtrlCRUDPacote.class);
	CtrlCRUDCasoUso ctrlCasoUso = (CtrlCRUDCasoUso) SpringUtil
			.getApplicationContext().getBean(CtrlCRUDCasoUso.class);
	CtrlDefinirRelacoesRastreabilidade ctrlDefinirRelacoesRastreabilidade = (CtrlDefinirRelacoesRastreabilidade) SpringUtil
			.getApplicationContext().getBean(
					CtrlDefinirRelacoesRastreabilidade.class);

	DualListBoxRequisito dlbDependencias = new DualListBoxRequisito() {
		@Override
		public List<Requisito> listaSelecionados() {
			if (coRequisito.getSelectedItem() == null)
				return new ArrayList<Requisito>();
			List<Requisito> requisitos = new ArrayList<Requisito>();
			requisitos.addAll(((Requisito) coRequisito.getSelectedItem()
					.getValue()).getDependencias());
			return requisitos;
		}
	};

	DualListBoxRequisito dlbConflitos = new DualListBoxRequisito() {

		@Override
		public List<Requisito> listaSelecionados() {
			if (coRequisito.getSelectedItem() == null)
				return new ArrayList<Requisito>();
			List<Requisito> requisitos = new ArrayList<Requisito>();
			requisitos.addAll(((Requisito) coRequisito.getSelectedItem()
					.getValue()).getConflitos());
			return requisitos;
		}
	};

	DualListBoxPacote dlbPacotes = new DualListBoxPacote();

	DualListBoxCasoUso dlbCasosUso = new DualListBoxCasoUso();

	public JanelaDefinirRelacoesRastreabilidade() {
		super();
		this.setTitle("Definir Relações de Rastreabilidade");
		this.setWidth("600px");
		this.setHeight(null);
		this.setPosition("center");
		this.setSizable(false);
		
		barraFerramentas.setParent(this);
		barraFerramentas.setWidth("100%");
		
		salvar.addEventListener("onClick", new EventSalvar());
		salvar.setImage("imagens/filesave.png");
		salvar.setParent(barraFerramentas);

		GridDados grid = new GridDados();
		grid.setParent(this);

		coRequisito.setReadonly(true);
		coRequisito.setWidth("100%");
		coRequisito.addEventListener("onChange", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				tbDescricao.setValue(((Requisito) coRequisito.getSelectedItem()
						.getValue()).getDescricao());
				atualizaListas();
			}
		});
		grid.adicionarLinha("Identificador", coRequisito);

		tbDescricao.setWidth("100%");
		tbDescricao.setReadonly(true);
		tbDescricao.setRows(4);
		grid.adicionarLinha("Descrição", tbDescricao);

		
		NucleoTabbox tabbox = new NucleoTabbox();
		tabbox.setParent(this);
		
		NucleoTab dependencias = new NucleoTab("Dependências");
		tabbox.addTab(dependencias);
		dependencias.setConteudoTab(dlbDependencias);
		dlbDependencias.setWidth("560px");
		dlbDependencias.setHeight("400px");
		dlbDependencias.setCheckmark(true);

		NucleoTab conflitos = new NucleoTab("Conflitos");
		conflitos.setConteudoTab(dlbConflitos);
		tabbox.addTab(conflitos);
		dlbConflitos.setWidth("560px");
		dlbConflitos.setHeight("400px");
		dlbConflitos.setCheckmark(true);
		
		NucleoTab pacotes = new NucleoTab("Pacotes");
		pacotes.setConteudoTab(dlbPacotes);
		tabbox.addTab(pacotes);
		dlbPacotes.setWidth("560px");
		dlbPacotes.setHeight("400px");
		dlbPacotes.setCheckmark(true);

		NucleoTab casosDeUso = new NucleoTab("Casos de Uso");
		casosDeUso.setConteudoTab(dlbCasosUso);
		tabbox.addTab(casosDeUso);
		dlbCasosUso.setWidth("560px");
		dlbCasosUso.setHeight("400px");
		dlbCasosUso.setCheckmark(true);

		NucleoTab artefatos = new NucleoTab("Artefatos");
		artefatos.getTabpanel().setHeight("400px");
		tabbox.addTab(artefatos);

		

		atualizaListas();
		preencherComboboxRequisitos();
	}

	public void atualizaListas() {
		dlbCasosUso.preencheListbox();
		dlbConflitos.preencheListbox();
		dlbDependencias.preencheListbox();
		dlbPacotes.preencheListbox();
	}

	public void preencherComboboxRequisitos() {
		Collection<Requisito> requisitos = ctrlRequisito
				.obterPorProjeto(NucleoContexto.recuperarProjeto());
		for (Requisito r : requisitos) {
			Comboitem item = new Comboitem();

			item.setLabel(r.getIdentificador());
			item.setValue(r);

			item.setParent(coRequisito);
		}
		coRequisito.setSelectedIndex(0);
		Events.sendEvent("onChange", coRequisito, null);
	}

	public class EventSalvar implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			Requisito requisito = (Requisito) coRequisito.getSelectedItem()
					.getValue();
			requisito.setDependencias(dlbDependencias.getSelecionados());
			requisito.setConflitos(dlbConflitos.getSelecionados());
			requisito.setPacotes(dlbPacotes.getSelecionados());
			requisito.setCasosUso(dlbCasosUso.getSelecionados());
			try{
			ctrlRequisito.definirAplCRUD().salvar(requisito);
			}catch (Exception e) {
				Messagebox.show("Erro ao definir as relações. Erro: " + e.getMessage());
				e.printStackTrace();
				return;
			}
			Messagebox.show("As relações foram definidas com sucesso. Deseja definir relações de outro requisito?", "Confirmação", Messagebox.YES | Messagebox.NO, null,new EventListener() {
				@Override
				public void onEvent(Event arg0) throws Exception {
					if (arg0.getName().equals("onNo")){
						referencia.detach();
					}
					
				}
			});
		}

	}

	public abstract class DualListBoxRequisito extends DualListBox<Requisito> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3424161404873158327L;

		@Override
		public Listhead defineCabecalho() {
			Listhead listhead = new Listhead();
			new Listheader("ID", null, "30%").setParent(listhead);
			new Listheader("Descrição", null, "70%").setParent(listhead);
			return listhead;
		}

		@Override
		public Listitem criaItem(Requisito objeto) {
			Listitem listitem = new Listitem();
			new Listcell(objeto.getIdentificador()).setParent(listitem);
			new Listcell(objeto.getDescricao()).setParent(listitem);
			listitem.setValue(objeto);
			return listitem;
		}

		@Override
		public List<Requisito> listaDisponiveis() {
			List<Requisito> requisitos = new ArrayList<Requisito>();
			requisitos.addAll(ctrlRequisito.obterPorProjeto(NucleoContexto
					.recuperarProjeto()));
			return requisitos;
		}
	}

	public Listhead defineCabecalhoConhecimento() {
		Listhead listhead = new Listhead();
		new Listheader("Nome", null, "30%").setParent(listhead);
		new Listheader("Descrição", null, "70%").setParent(listhead);
		return listhead;
	}

	public Listitem criaItemConhecimento(Conhecimento conhecimento) {
		Listitem listitem = new Listitem();
		new Listcell(conhecimento.getNome()).setParent(listitem);
		new Listcell(conhecimento.getDescricao()).setParent(listitem);
		listitem.setValue(conhecimento);
		return listitem;
	}

	public class DualListBoxPacote extends DualListBox<Pacote> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4981322420250048211L;

		@Override
		public Listhead defineCabecalho() {
			return defineCabecalhoConhecimento();
		}

		@Override
		public Listitem criaItem(Pacote objeto) {
			return criaItemConhecimento(objeto);
		}

		@Override
		public List<Pacote> listaDisponiveis() {
			List<Pacote> pacotes = new ArrayList<Pacote>();
			pacotes.addAll(ctrlPacote.obterPorProjeto(NucleoContexto
					.recuperarProjeto()));
			return pacotes;
		}

		@Override
		public List<Pacote> listaSelecionados() {
			List<Pacote> pacotes = new ArrayList<Pacote>();
			if (coRequisito.getSelectedItem() == null)
				return pacotes;
			pacotes.addAll(((Requisito) coRequisito.getSelectedItem()
					.getValue()).getPacotes());
			return pacotes;
		}
	}

	public class DualListBoxCasoUso extends DualListBox<CasoUso> {

		@Override
		public Listhead defineCabecalho() {
			return defineCabecalhoConhecimento();
		}

		@Override
		public Listitem criaItem(CasoUso objeto) {
			return criaItemConhecimento(objeto);
		}

		@Override
		public List<CasoUso> listaDisponiveis() {
			List<CasoUso> casosUso = new ArrayList<CasoUso>();
			casosUso.addAll(ctrlCasoUso.obterPorProjeto(NucleoContexto
					.recuperarProjeto()));
			return casosUso;
		}

		@Override
		public List<CasoUso> listaSelecionados() {
			if (coRequisito.getSelectedItem() == null)
				return new ArrayList<CasoUso>();

			List<CasoUso> casosUso = new ArrayList<CasoUso>();
			casosUso.addAll(((Requisito) coRequisito.getSelectedItem()
					.getValue()).getCasosUso());

			return casosUso;
		}
	}

}

package ode.gerenciaRequisitos.cih;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._controleRecursoHumano.ciu.CtrlRecursoHumanoCRUD;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cci.CtrlCRUDRequisito;
import ode.gerenciaRequisitos.cci.CtrlRastrearRequisitos;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cci.CtrlCRUDCasoUso;
import ode.uml.cci.CtrlCRUDClasse;
import ode.uml.cci.CtrlCRUDPacote;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Vlayout;

public class PainelRastrearRequisitos extends Vlayout{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1724652229767845435L;
	
	Tabbox tabbox = new Tabbox();
	
	Combobox coCasoUso = new Combobox();
	Combobox coClasse = new Combobox();
	Combobox coPacote = new Combobox();
	Combobox coRecursoHumano = new Combobox();
	
	Radio interessados = new Radio("Interessados");
	Radio responsaveis = new Radio("Responsáveis");
	Radiogroup selecao = new Radiogroup();
	
	Listbox lbRequisitos = new Listbox();
	
	Button verRequisito = new Button("Ver Requisito");
	
	CtrlRastrearRequisitos ctrlRastrear = (CtrlRastrearRequisitos) SpringUtil.getApplicationContext().getBean(CtrlRastrearRequisitos.class);
	CtrlCRUDRequisito ctrlRequisito = (CtrlCRUDRequisito) SpringUtil.getApplicationContext().getBean(CtrlCRUDRequisito.class);
	CtrlCRUDCasoUso ctrlCasoUso = (CtrlCRUDCasoUso) SpringUtil.getApplicationContext().getBean(CtrlCRUDCasoUso.class);
	CtrlCRUDPacote ctrlPacote = (CtrlCRUDPacote) SpringUtil.getApplicationContext().getBean(CtrlCRUDPacote.class);
	CtrlCRUDClasse ctrlClasse = (CtrlCRUDClasse) SpringUtil.getApplicationContext().getBean(CtrlCRUDClasse.class);
	CtrlRecursoHumanoCRUD ctrlRecursoHumano = (CtrlRecursoHumanoCRUD) SpringUtil.getApplicationContext().getBean(CtrlRecursoHumanoCRUD.class);
	RecursoHumanoDAO recursoHumanoDao = (RecursoHumanoDAO) SpringUtil.getApplicationContext().getBean(RecursoHumanoDAO.class);
	
	public PainelRastrearRequisitos(){
		carregarComponentesInterface();
		preencherCombobox();
	}
	
	public Listhead defineCabecalho() {
		Listhead listhead = new Listhead();
		new Listheader("ID", null, "10%").setParent(listhead);
		new Listheader("Descrição").setParent(listhead);
		return listhead;
	}
	
	public void carregarComponentesInterface(){
		tabbox.addEventListener("onSelect", new EventLimpaResultado());
		
		Tabs tabs = new Tabs();
		tabs.setParent(tabbox);
		
		Tabpanels tabpanels = new Tabpanels();
		tabpanels.setParent(tabbox);
		
		new Tab("Por Caso de Uso").setParent(tabs);
		Tabpanel tpCasoUso = new Tabpanel();
		Hlayout hlCasoUso = new Hlayout();
		new Label("Caso de Uso:").setParent(hlCasoUso);
		coCasoUso.addEventListener("onChange", new EventFiltrarCasoUso());
		coCasoUso.setParent(hlCasoUso);
		hlCasoUso.setParent(tpCasoUso);
		tpCasoUso.setParent(tabpanels);
		
		new Tab("Por Classe").setParent(tabs);
		Tabpanel tpClasse = new Tabpanel();
		Hlayout hlClasse = new Hlayout();
		new Label("Classe:").setParent(hlClasse);
		coClasse.addEventListener("onChange", new EventFiltrarClasse());
		coClasse.setParent(hlClasse);
		hlClasse.setParent(tpClasse);
		tpClasse.setParent(tabpanels);
		
		new Tab("Por Pacote").setParent(tabs);
		Tabpanel tpPacote = new Tabpanel();
		Hlayout hlPacote = new Hlayout();
		new Label("Pacote:").setParent(hlPacote);
		coPacote.addEventListener("onChange", new EventFiltrarPacote());
		coPacote.setParent(hlPacote);
		hlPacote.setParent(tpPacote);
		tpPacote.setParent(tabpanels);
		
		new Tab("Por Recurso Humano").setParent(tabs);
		Tabpanel tpRecursoHumano = new Tabpanel();
		Hlayout hlRecursoHumano = new Hlayout();
		new Label("Recurso Humano:").setParent(hlRecursoHumano);
		coRecursoHumano.addEventListener("onChange", new EventFiltrarRecursoHumano());
		coRecursoHumano.setParent(hlRecursoHumano);
		interessados.setParent(selecao);
		interessados.setChecked(true);
		responsaveis.setParent(selecao);
		selecao.setParent(hlRecursoHumano);
		selecao.addEventListener("onCheck", new EventFiltrarRecursoHumano());
		hlRecursoHumano.setParent(tpRecursoHumano);
		tpRecursoHumano.setParent(tabpanels);
		
		tabbox.setParent(this);
		
		new Label("Requisitos:").setParent(this);
		lbRequisitos.appendChild(defineCabecalho());
		lbRequisitos.setHeight("300px");
		lbRequisitos.setParent(this);
		verRequisito.addEventListener("onClick", new EventVerRequisito());
		verRequisito.setParent(this);
	}
	
	public void limpaResultado (){
		lbRequisitos.getChildren().clear();
		lbRequisitos.appendChild(defineCabecalho());
	}
	
	public Listitem criaItem(Requisito objeto) {
		Listitem listitem = new Listitem();
		new Listcell(objeto.getIdentificador()).setParent(listitem);
		new Listcell(objeto.getDescricao()).setParent(listitem);
		listitem.setValue(objeto);
		return listitem;
	}
	
	public void atualizaResultado (Collection<Requisito> requisitos){
		lbRequisitos.getChildren().clear();
		lbRequisitos.appendChild(defineCabecalho());
		
		if (requisitos == null) return;
		for (Requisito r : requisitos){
			lbRequisitos.appendChild(criaItem(r));
		}		
	}
	
	public class EventLimpaResultado implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			limpaResultado();
			coCasoUso.setValue(null);
			coClasse.setValue(null);
			coPacote.setValue(null);
			coRecursoHumano.setValue(null);
		}
	}
	
	public class EventFiltrarCasoUso implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			if (coCasoUso.getSelectedItem() == null){
				limpaResultado();
				return;
			}
			CasoUso casoUso = (CasoUso) coCasoUso.getSelectedItem().getValue();
			if (casoUso == null){
				limpaResultado();
				return;
			}
			Collection<Requisito> requisitos = ctrlRastrear.obterPorCasoUso(casoUso);
			atualizaResultado(requisitos);
		}
	}
	
	public void preencherCombobox (){
		Projeto projeto = NucleoContexto.recuperarProjeto();
		Collection<CasoUso> casosUso = ctrlCasoUso.obterPorProjeto(projeto);
		for (CasoUso c : casosUso){
			Comboitem item = new Comboitem();
			item.setLabel(c.getNome());
			item.setValue(c);
			coCasoUso.appendChild(item);
		}
		
		Collection<Classe> classes = ctrlClasse.obterPorProjeto(projeto);
		for (Classe c : classes){
			Comboitem item = new Comboitem();
			item.setLabel(c.getNome());
			item.setValue(c);
			coClasse.appendChild(item);
		}
		
		Collection<Pacote> pacotes = ctrlPacote.obterPorProjeto(projeto);
		for (Pacote p : pacotes){
			Comboitem item = new Comboitem();
			item.setLabel(p.getNome());
			item.setValue(p);
			coPacote.appendChild(item);
		}
		
		Collection<RecursoHumano> recursos = recursoHumanoDao.recuperarTodos();
		for (RecursoHumano r : recursos){
			Comboitem item = new Comboitem();
			item.setLabel(r.getNome());
			item.setValue(r);
			coRecursoHumano.appendChild(item);
		}
		
	}
	
	public class EventFiltrarClasse implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			if (coClasse.getSelectedItem() == null){
				limpaResultado();
				return;
			}
			Classe classe = (Classe) coClasse.getSelectedItem().getValue();
			if (classe == null){
				limpaResultado();
				return;
			}
			Collection<Requisito> requisitos = ctrlRastrear.obterPorClasse(classe);
			atualizaResultado(requisitos);
		}
	}
	
	public class EventFiltrarPacote implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			if (coPacote.getSelectedItem() == null){
				limpaResultado();
				return;
			}
			Pacote pacote = (Pacote) coPacote.getSelectedItem().getValue();
			if (pacote == null){
				limpaResultado();
				return;
			}
			Collection<Requisito> requisitos = ctrlRastrear.obterPorPacote(pacote);
			atualizaResultado(requisitos);
		}
	}
	
	public class EventFiltrarRecursoHumano implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			if (coRecursoHumano.getSelectedItem() == null){
				limpaResultado();
				return;
			}
			RecursoHumano rhumano = (RecursoHumano) coRecursoHumano.getSelectedItem().getValue();
			if (rhumano == null){
				limpaResultado();
				return;
			}
			Collection<Requisito> requisitos;
			if (responsaveis.isChecked()){
				requisitos = ctrlRastrear.obterPorResponsavel(rhumano);
			}else{
				requisitos = ctrlRastrear.obterPorInteressado(rhumano);
			}
			atualizaResultado(requisitos);
		}
	}
	
	public class EventVerRequisito implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			Requisito requisito = (Requisito) lbRequisitos.getSelectedItem().getValue();
			if (requisito == null) return;
			requisito = ctrlRequisito.obterPorId(requisito.getId());
			ctrlRastrear.iniciarVerRequisito(requisito);
		}
	}
}

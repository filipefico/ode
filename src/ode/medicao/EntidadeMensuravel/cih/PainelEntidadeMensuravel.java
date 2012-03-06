package ode.medicao.EntidadeMensuravel.cih;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.NucleoSwitcher;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;
import ode.medicao.EntidadeMensuravel.cci.CtrlEntidadeMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ArtefatoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.AtividadeMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.EntidadeMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ProcessoPadraoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ProcessoProjetoEspecificoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ProjetoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.RecursoHumanoMensuravel;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vlayout;

public class PainelEntidadeMensuravel extends Vlayout{

	protected List<NucleoTab> listaTabs;
	protected Tabbox tabbox = new Tabbox();
	protected Tabs tabs = new Tabs();
	protected Tabpanels tabpanels = new Tabpanels();
	
	private NucleoSwitcher<RecursoHumanoMensuravel> nsrh;
	private NucleoSwitcher<AtividadeMensuravel> nsat;
	private NucleoSwitcher<ArtefatoMensuravel> nsar;
	private NucleoSwitcher<ProcessoPadraoMensuravel> nspc;
	private NucleoSwitcher<ProjetoMensuravel> nspt;
	
	private CtrlEntidadeMensuravel ctrl;

	
	
	//@SuppressWarnings({ "warning", "unchecked" })
	private <A extends ObjetoPersistente, B extends EntidadeMensuravel<A>> NucleoTab criaAba(String nome, NucleoSwitcher<B> ns, Collection<A> objetos, Collection<B> mens, TipoEntidadeMensuravel tipo) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		NucleoTab tab = new NucleoTab();
		tab.setNomeTab(nome);
		ns.montar("Não-Mensuráveis", "Mensuráveis");
		for(EntidadeMensuravel<A> entidade: mens){
			objetos.remove(entidade.getEntidade());
		}
		Collection<B> grupoEsq = new ArrayList<B>();
		Object construtor = null;
		try{
		if(!objetos.isEmpty()){
			switch (tipo) {
			case ARTEFATO:
				construtor = ArtefatoMensuravel.class.getConstructor();
				break;
			case ATIVIDADE:
				construtor =  AtividadeMensuravel.class.getConstructor();
				break;
			case PROCESSOESPECIFICOPROJETO:
				construtor =  ProcessoProjetoEspecificoMensuravel.class.getConstructor();
				break;
			case PROCESSOPADRAO:
				construtor =  ProcessoPadraoMensuravel.class.getConstructor();
				break;
			case PROJETO:
				construtor =  ProjetoMensuravel.class.getConstructor();
				break;
			case RECURSOHUMANO:
				construtor =  RecursoHumanoMensuravel.class.getConstructor();
				break;

			default:
				break;
			}
			B temp;
			for(A ent:objetos){
				grupoEsq.add(temp = (B) ((Constructor<Object>)construtor).newInstance());
				temp.setEntidade(ent);
			}
			
		}
		}catch(Exception e){
			
		}
		ns.setLeftItens(grupoEsq);
		ns.setRightItens(mens);
		tab.setConteudoTab(ns);
		return tab;
	}
	
	public List<NucleoTab> getAbas(){
		List<NucleoTab> lista = new ArrayList<NucleoTab>();
		
		try{
			lista.add(criaAba("Recurso Humano", nsrh = new NucleoSwitcher<RecursoHumanoMensuravel>(), ctrl.recuperarRecurso(), ctrl.recuperarRecursoMensuravel(), TipoEntidadeMensuravel.RECURSOHUMANO));
		
			lista.add(criaAba("Atividade", nsat = new NucleoSwitcher<AtividadeMensuravel>(), ctrl.recuperarAtividade(), ctrl.recuperarAtividadeMensuravel(), TipoEntidadeMensuravel.ATIVIDADE));
		
			lista.add(criaAba("Artefato", nsar = new NucleoSwitcher<ArtefatoMensuravel>(), ctrl.recuperarArtefato(), ctrl.recuperarArtefatoMensuravel(), TipoEntidadeMensuravel.ARTEFATO));
		
			lista.add(criaAba("Processo Padr�o", nspc = new NucleoSwitcher<ProcessoPadraoMensuravel>(), ctrl.recuperarProcesso(), ctrl.recuperarProcessoMensuravel(), TipoEntidadeMensuravel.PROCESSOPADRAO));
		
			lista.add(criaAba("Projeto", nspt = new NucleoSwitcher<ProjetoMensuravel>(), ctrl.recuperarProjeto(), ctrl.recuperarProjetoMensuravel(), TipoEntidadeMensuravel.PROJETO));
		}catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<NucleoTab>();
		}
		return lista;
		
	}

	public void montar() {
		
		listaTabs = getAbas();
		
		for (NucleoTab nucleoTab : listaTabs) {
			// Cria tab
			Tab tab = new Tab(nucleoTab.getNomeTab());
			tab.setParent(tabs);

			// Cria painel com conteudo
			Tabpanel tabpanel = new Tabpanel();
			tabpanel.setParent(tabpanels);
			Component conteudo = nucleoTab.getConteudoTab();
			conteudo.setParent(tabpanel);
		}
		tabpanels.setParent(tabbox);
		tabs.setParent(tabbox);
		this.appendChild(tabbox);
		
		Button salvar = new Button("Salvar");
		Toolbar toolbarInferior = new Toolbar();
		
		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");
		
		toolbarInferior.appendChild(salvar);
		salvar.addEventListener("onClick", new SalvarEntidades());
		this.appendChild(toolbarInferior);
	}
	
	private class SalvarEntidades implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.atualizarRecurso(nsrh.getLeftItens(),nsrh.getRightItens());
			ctrl.atualizarAtividade(nsat.getLeftItens(),nsat.getRightItens());
			ctrl.atualizarArtefato(nsar.getLeftItens(),nsar.getRightItens());
			ctrl.atualizarProcesso(nspc.getLeftItens(),nspc.getRightItens());
			ctrl.atualizarProjeto(nspt.getLeftItens(),nspt.getRightItens());
			Messagebox mbox = new Messagebox();
			mbox.show("Configuração salva com sucesso.");
		}
	}
	
	public void setCtrl(CtrlEntidadeMensuravel ctrlEntidadeMensuravel) {
		this.ctrl = ctrlEntidadeMensuravel;
	}
	
}
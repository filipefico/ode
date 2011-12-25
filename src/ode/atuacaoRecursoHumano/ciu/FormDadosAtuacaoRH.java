package ode.atuacaoRecursoHumano.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.ciu.FormDadosRecursoHumano;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.atuacaoRecursoHumano.cdp.AtuacaoRH;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;
import ode.conhecimento.organizacao.cdp.KCompetencia;
import ode.conhecimento.organizacao.cdp.NivelKCompetencia;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

public class FormDadosAtuacaoRH extends FormDadosRecursoHumano {

	private static final long serialVersionUID = 1L;

	private NucleoListbox<KRecursoHumano> listboxPapeis = new NucleoListbox<KRecursoHumano>();
	private KRecursoHumano cargoSelecionado = null;
	private NucleoListbox<KCompetencia> listboxCompetencias = new NucleoListbox<KCompetencia>();
	
	private RecursoHumano rh;
	private AtuacaoRH atuacaoRH;
	
	@Override public CtrlAtuacaoRHCRUD getControlador() {
		return (CtrlAtuacaoRHCRUD)super.getControlador();
	}
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
		listaTabs.add(definirTabPapeis());
		listaTabs.add(definirTabCompetencias());
		return listaTabs;
	}

	protected NucleoTab definirTabDadosCadastro() {
		NucleoTab tabDadosCadastro = super.definirTabDadosCadastro();

		listaKRH = ((CtrlAtuacaoRHCRUD) getControlador()).listarKRecursosHumanos();
		listboxCargo.addObjetos(listaKRH);

		listboxCargo.addEventListener("onSelect", new EventListener() {
			public void onEvent(Event event) {
				if (cargoSelecionado != null)
					listboxPapeis.getItem(cargoSelecionado).setDisabled(false);
				cargoSelecionado = listboxCargo.getObjetoSelecionado();
				if(cargoSelecionado!=null)
					listboxPapeis.getItem(cargoSelecionado).setDisabled(true);
			}
		});
		
		return tabDadosCadastro;
	}
	
	private NucleoTab definirTabPapeis() {
		NucleoTab tab = new NucleoTab("Papéis");
		GridDados grid = new GridDados();
		
		listboxPapeis.setWidth("240px");
		listboxPapeis.setCheckmark(true);
		listboxPapeis.setMultiple(true);
		listboxPapeis.setRows(5);
		for (KRecursoHumano objeto : listaKRH) {
			Listitem li = new Listitem(objeto.getNome(), objeto);
			listboxPapeis.appendChild(li);
		}
		
		grid.adicionarLinha("Papéis", listboxPapeis);
		tab.setConteudoTab(grid);
		return tab;
	}

	private NucleoTab definirTabCompetencias() {
		
		Collection<KCompetencia> kcompetencias = getControlador().listarKCompetencias();
		NucleoTab tabCompetencias = new NucleoTab("Competências");

		listboxCompetencias.setRows(5);
		
		for(KCompetencia kcompetencia : kcompetencias) {
			Listitem li = new Listitem();
			Listcell lc1 = new Listcell(kcompetencia.toString());
			Listcell lc2 = new Listcell();
			
			li.setValue(kcompetencia);
			NucleoListbox<NivelKCompetencia> lbNivelCompetencia = new NucleoListbox<NivelKCompetencia>();
			lbNivelCompetencia.setRows(1);
			lbNivelCompetencia.setObjetos(NivelKCompetencia.values());
			lbNivelCompetencia.setMold("select");
			lbNivelCompetencia.selecionarPrimeiroElemento();			
			lc2.appendChild(lbNivelCompetencia);
			
			li.appendChild(lc1);
			li.appendChild(lc2);
			
			listboxCompetencias.appendChild(li);	
			
		}
		
		//containerCompetencias.appendChild(listboxCompetencias);
		
		tabCompetencias.setConteudoTab(listboxCompetencias);
		
		return tabCompetencias;
	}
	
	protected void preencherDadosObjeto(RecursoHumano objeto) {
	}

	protected void preencherDadosTela(RecursoHumano objeto) {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void atualizarDadosTela() {
		rh = getObjetoCadastroDados();
		atuacaoRH = getControlador().getAtuacaoRH(rh);
		
		tbNome.setValue(rh.getNome());
		tbCargaHoraria.setValue(rh.getCargaHoraria());
		cbAtivo.setChecked(rh.isAtivo());
		tbEmail.setValue(rh.getEmail());
		tbTelefone.setValue(rh.getTelefone());
		listboxCargo.setObjetoSelecionado(rh.getCargo());
		
		cargoSelecionado = rh.getCargo();
		listboxPapeis.getItem(cargoSelecionado).setDisabled(true);
		
		if(atuacaoRH!=null) {
			listboxPapeis.setObjetosSelecionados(atuacaoRH.getPapeis());
			
			for(CompetenciaRH compRH : getControlador().obterCompetenciasRH(atuacaoRH)) {
				Listitem li = listboxCompetencias.getItem(compRH.getkCompetencia());
				Listcell lc2 = (Listcell) li.getChildren().get(1);
				NucleoListbox<NivelKCompetencia> lb = (NucleoListbox<NivelKCompetencia>) lc2.getChildren().get(0);
				lb.setObjetoSelecionado(compRH.getNivel());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void salvar() {
		rh = getObjetoCadastroDados();
		atuacaoRH = getControlador().getAtuacaoRH(rh);

		rh.setNome(tbNome.getValue());
		rh.setCargaHoraria(tbCargaHoraria.getValue());
		rh.setAtivo(cbAtivo.isChecked());
		rh.setTelefone(tbTelefone.getValue());
		rh.setEmail(tbEmail.getValue());
		rh.setCargo(listboxCargo.getObjetoSelecionado());
		
		Set<CompetenciaRH> competencias = new HashSet<CompetenciaRH>();
		for(Listitem li : (Collection<Listitem>)listboxCompetencias.getItems()) {
			//para cada possivel competencia, pega o listbox correspondente ao nivel da mesma
			Listcell lc2 = (Listcell) li.getChildren().get(1);
			NucleoListbox<NivelKCompetencia> lb = (NucleoListbox<NivelKCompetencia>) lc2.getChildren().get(0);
			if(lb.getSelectedIndex()>0) {
				//se o rh possui aquela competencia
				CompetenciaRH competencia = new CompetenciaRH();
				competencia.setNivel(lb.getObjetoSelecionado());
				competencia.setkCompetencia((KCompetencia)li.getValue());
				competencias.add(competencia);
			}
		}
		
		if(listboxPapeis.getSelectedCount()==0 && competencias.size()==0) {
			atuacaoRH = null;
		} else {
			if(atuacaoRH == null) {
				atuacaoRH = new AtuacaoRH();
				atuacaoRH.setRecursoHumano(rh);
			}
			atuacaoRH.setPapeis(listboxPapeis.getObjetosSelecionados());
		}
		
		getControlador().salvar(rh, atuacaoRH, competencias);
	}
}
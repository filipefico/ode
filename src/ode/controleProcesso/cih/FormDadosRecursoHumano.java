package ode.controleProcesso.cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.controleProcesso.cci.CtrlRecursoHumanoCRUD;
import ode.controleProcesso.cdp.RecursoHumano;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.nucleo.crud.cih.NucleoListbox;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

public class FormDadosRecursoHumano extends FormularioDadosCRUD<RecursoHumano> {

	private static final long serialVersionUID = 1L;

	private Textbox tbNome = new Textbox();
	private Intbox tbCargaHoraria = new Intbox();
	private Checkbox cbAtivo = new Checkbox();
	private NucleoListbox<KRecursoHumano> listboxCargo = new NucleoListbox<KRecursoHumano>();
	private NucleoListbox<KRecursoHumano> listboxPapeis = new NucleoListbox<KRecursoHumano>();
	private KRecursoHumano cargoSelecionado;

	private List<KRecursoHumano> listaKRH;

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
		listaTabs.add(definirTabCompetencias());
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

		cbAtivo.setChecked(true);
		gridDadosCadastro.adicionarLinhaObrigatoria("Ativo", cbAtivo);

		tbCargaHoraria.setWidth("25px");
		tbCargaHoraria.setMaxlength(3);
		gridDadosCadastro.adicionarLinhaObrigatoria("Carga Horária",
				tbCargaHoraria);

		listboxCargo.setWidth("240px");
		listboxCargo.setRows(1);
		listboxCargo.setMold("select");
		listaKRH = new ArrayList<KRecursoHumano>(((CtrlRecursoHumanoCRUD)this.getControlador()).listarKRecursosHumanos());

		listboxCargo.setObjetos(listaKRH);
		listboxCargo.setSelectedIndex(0);
		cargoSelecionado = listboxCargo.getObjetoSelecionado();
		listboxCargo.addEventListener("onSelect", new EventListenerCargo());
		gridDadosCadastro.adicionarLinhaObrigatoria("Cargo", listboxCargo);

		listboxPapeis.setWidth("240px");
		listboxPapeis.setCheckmark(true);
		listboxPapeis.setMultiple(true);

		for (KRecursoHumano objeto : listaKRH) {
			Listitem li = new Listitem(objeto.getNome(), objeto);
			listboxPapeis.appendChild(li);
		}
		listboxPapeis.getItemAtIndex(0).setDisabled(true);
		
		gridDadosCadastro.adicionarLinha("Papéis", listboxPapeis);

		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		return tabDadosCadastro;
	}

	private NucleoTab definirTabCompetencias() {
		NucleoTab tabCompetencias = new NucleoTab();
		tabCompetencias.setNomeTab("Competências");

		GridDados gridCompetencias = new GridDados();
		tabCompetencias.setConteudoTab(gridCompetencias);
		return tabCompetencias;
	}

	private class EventListenerCargo implements EventListener {

		public void onEvent(Event event) {
			listboxPapeis.getItem(cargoSelecionado).setDisabled(false);
			cargoSelecionado = listboxCargo.getObjetoSelecionado();
			listboxPapeis.getItem(cargoSelecionado).setDisabled(true);
		}
	}

	@Override
	protected void preencherDadosObjeto(RecursoHumano objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setCargaHoraria(tbCargaHoraria.getValue());
		objeto.setAtivo(cbAtivo.isChecked());
		objeto.setCargo(listboxCargo.getObjetoSelecionado());
		objeto.setPapeis(listboxPapeis.getObjetosSelecionados());
	}

	@Override
	protected void preencherDadosTela(RecursoHumano objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbCargaHoraria.setValue(objeto.getCargaHoraria());
		cbAtivo.setChecked(objeto.isAtivo());
		listboxCargo.setObjetoSelecionado(objeto.getCargo());
		listboxPapeis.setObjetosSelecionados(objeto.getPapeis());
		cargoSelecionado = listboxCargo.getObjetoSelecionado();
		listboxPapeis.getItemAtIndex(0).setDisabled(false);
		listboxPapeis.getItem(cargoSelecionado).setDisabled(true);
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty: Favor informar o Nome!");
		tbCargaHoraria
				.setConstraint("no empty: Favor informar a Carga Horária!");
	}

}
package ode.gerenciaRequisitos.cih;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.gerenciaRequisitos.cci.CtrlCRUDRequisito;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Window;

@SuppressWarnings("serial")
public class PainelGerarRelatorioRastreabilidade extends Window {
	Radiogroup radiogroup = new Radiogroup();
	Radio checkCompleto = new Radio("Completo");
	Radio checkPersonalizado = new Radio("Personalizado");
	List<Checkbox> lista = new ArrayList<Checkbox>();
	Button buttonGerar = new Button("Gerar");

	CtrlCRUDRequisito ctrlRequisito = SpringUtil.getApplicationContext()
			.getBean(CtrlCRUDRequisito.class);

	public PainelGerarRelatorioRastreabilidade() {
		carregaInterface();
	}

	private void carregaInterface() {
		GridDados gridDados = new GridDados();
		gridDados.setParent(this);

		radiogroup.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				for (Checkbox checkbox : lista) {
					if (checkCompleto.isChecked()) {
						checkbox.setChecked(true);
						checkbox.setDisabled(true);
					} else {
						checkbox.setDisabled(false);
					}
				}
			}
		});
		checkCompleto.setParent(radiogroup);
		checkCompleto.setChecked(true);
		checkPersonalizado.setParent(radiogroup);
		gridDados.adicionarLinha("Tipo", radiogroup);

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Identificador", lista.get(0));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Descrição", lista.get(1));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Data de Criação", lista.get(2));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Prioridade", lista.get(3));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Tipo de Requisito", lista.get(4));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Categoria de Requisito", lista.get(5));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Dependências", lista.get(6));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Conflitos", lista.get(7));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Interessados", lista.get(8));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Responsáveis", lista.get(9));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Pacotes Relacionados", lista.get(10));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Casos de Uso Relacionados", lista.get(11));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Classes Relacionadas", lista.get(12));

		lista.add(new Checkbox());
		gridDados.adicionarLinha("Artefatos Relacionados", lista.get(13));

		Hbox hbox = new Hbox();
		hbox.setAlign("end");
		buttonGerar.setParent(hbox);
		buttonGerar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				boolean zeroSelecionado = true;
				for (Checkbox checkbox : lista) {
					if (checkbox.isChecked()) {
						zeroSelecionado = false;
						break;
					}
				}
				if (zeroSelecionado) {
					Messagebox.show("Selecione ao menos uma informação.");
					return;
				}
				janelaRelatorio().doModal();
			}
		});
		hbox.setParent(this);
		Events.sendEvent("onClick", radiogroup, null);
	}

	private Window janelaRelatorio() {
		Window add = new Window();
		add.setParent(this);
		try {
			add.setMode("modal");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		add.setContentStyle("overflow:auto;background:white");
		add.setHeight("600px");
		add.setWidth("500px");
		add.setTitle("Relatório de Rastreabilidade - "
				+ NucleoContexto.recuperarProjeto().getNome());
		add.setClosable(true);
		add.setMaximizable(true);
		add.setMinimizable(true);
		add.setBorder("normal");

		List<Requisito> requisitos = new ArrayList<Requisito>();
		requisitos.addAll(ctrlRequisito.obterPorProjeto(NucleoContexto
				.recuperarProjeto()));

		for (Requisito requisito : requisitos) {
			GridDados grid = new GridDados();
			grid.setParent(add);

			if (lista.get(0).isChecked())
				grid.adicionarLinha("Identificador",
						new Label(requisito.getIdentificador()));

			if (lista.get(1).isChecked())
				grid.adicionarLinha("Descrição",
						new Label(requisito.getDescricao()));

			if (lista.get(2).isChecked())
				grid.adicionarLinha(
						"Data de Criação",
						new Label(new SimpleDateFormat().format(requisito
								.getDataCriacao())));

			if (lista.get(3).isChecked())
				grid.adicionarLinha("Prioridade", new Label(requisito
						.getPrioridade().name()));

			if (lista.get(4).isChecked())
				grid.adicionarLinha("Tipo de Requisito", new Label(requisito
						.getTipoRequisito().getNome()));

			if (lista.get(5).isChecked())
				grid.adicionarLinha("Categoria de Requisito", requisito
						.getCategoria() != null ? new Label(requisito
						.getCategoria().getNome()) : new Label("<<Vazio>>"));

			if (lista.get(6).isChecked()) {
				Listbox listbox = new Listbox();

				Listhead listhead = new Listhead();
				listhead.setParent(listbox);

				new Listheader("ID").setParent(listhead);
				new Listheader("Descrição").setParent(listhead);

				Set<Requisito> dependencias = requisito.getDependencias();

				for (Requisito requisito2 : dependencias) {
					Listitem item = new Listitem();

					new Listcell(requisito2.getIdentificador()).setParent(item);
					new Listcell(requisito2.getDescricao()).setParent(item);

					item.setParent(listbox);
				}
				if (dependencias.size() == 0) {
					grid.adicionarLinha("Dependências", new Label("<<Vazio>>"));
				} else {
					grid.adicionarLinha("Dependências", listbox);
				}
			}

			if (lista.get(7).isChecked()) {
				Listbox listbox = new Listbox();

				Listhead listhead = new Listhead();
				listhead.setParent(listbox);

				new Listheader("ID").setParent(listhead);
				new Listheader("Descrição").setParent(listhead);

				Set<Requisito> conflitos = requisito.getConflitos();
				for (Requisito requisito2 : conflitos) {
					Listitem item = new Listitem();

					new Listcell(requisito2.getIdentificador()).setParent(item);
					new Listcell(requisito2.getDescricao()).setParent(item);

					item.setParent(listbox);
				}
				if (conflitos.size() == 0) {
					grid.adicionarLinha("Conflitos", new Label("<<Vazio>>"));
				} else {
					grid.adicionarLinha("Conflitos", listbox);
				}
			}

			if (lista.get(8).isChecked()) {
				Listbox listbox = new Listbox();

				Listhead listhead = new Listhead();
				listhead.setParent(listbox);

				new Listheader("Nome").setParent(listhead);

				Set<RecursoHumano> interessados = requisito.getInteressados();
				for (RecursoHumano recursoHumano : interessados) {
					Listitem item = new Listitem();

					new Listcell(recursoHumano.getNome()).setParent(item);

					item.setParent(listbox);
				}
				if (interessados.size() == 0) {
					grid.adicionarLinha("Interessados", new Label("<<Vazio>>"));
				} else {
					grid.adicionarLinha("Interessados", listbox);
				}
			}

			if (lista.get(9).isChecked()) {
				Listbox listbox = new Listbox();

				Listhead listhead = new Listhead();
				listhead.setParent(listbox);

				new Listheader("Nome").setParent(listhead);

				Set<RecursoHumano> responsaveis = requisito.getResponsaveis();
				for (RecursoHumano recursoHumano : responsaveis) {
					Listitem item = new Listitem();

					new Listcell(recursoHumano.getNome()).setParent(item);

					item.setParent(listbox);
				}
				if (responsaveis.size() == 0) {
					grid.adicionarLinha("Responsáveis", new Label("<<Vazio>>"));
				} else {
					grid.adicionarLinha("Responsáveis", listbox);
				}
			}

			if (lista.get(10).isChecked()) {
				Listbox listbox = new Listbox();

				Listhead listhead = new Listhead();
				listhead.setParent(listbox);

				new Listheader("Nome").setParent(listhead);
				new Listheader("Descrição").setParent(listhead);

				Set<Pacote> pacotes = requisito.getPacotes();
				for (Conhecimento conhecimento : pacotes) {
					Listitem item = new Listitem();

					new Listcell(conhecimento.getNome()).setParent(item);
					new Listcell(conhecimento.getDescricao()).setParent(item);

					item.setParent(listbox);
				}
				if (pacotes.size() == 0) {
					grid.adicionarLinha("Pacotes Relacionados", new Label(
							"<<Vazio>>"));
				} else {
					grid.adicionarLinha("Pacotes Relacionados", listbox);
				}
			}

			if (lista.get(11).isChecked()) {
				Listbox listbox = new Listbox();

				Listhead listhead = new Listhead();
				listhead.setParent(listbox);

				new Listheader("Nome").setParent(listhead);
				new Listheader("Descrição").setParent(listhead);

				Set<CasoUso> casosUso = requisito.getCasosUso();
				for (Conhecimento conhecimento : casosUso) {
					Listitem item = new Listitem();

					new Listcell(conhecimento.getNome()).setParent(item);
					new Listcell(conhecimento.getDescricao()).setParent(item);

					item.setParent(listbox);
				}
				if (casosUso.size() == 0) {
					grid.adicionarLinha("Casos de Uso Relacionados", new Label(
							"<<Vazio>>"));
				} else {
					grid.adicionarLinha("Casos de Uso Relacionados", listbox);
				}
			}

			if (lista.get(12).isChecked()) {
				Listbox listbox = new Listbox();

				Listhead listhead = new Listhead();
				listhead.setParent(listbox);

				new Listheader("Nome").setParent(listhead);
				new Listheader("Descrição").setParent(listhead);

				Set<CasoUso> casosUso = requisito.getCasosUso();
				for (CasoUso casoUso : casosUso) {
					Set<Classe> classes = casoUso.getClasses();
					for (Classe classe : classes) {
						Listitem item = new Listitem();

						new Listcell(classe.getNome()).setParent(item);
						new Listcell(classe.getDescricao()).setParent(item);

						item.setParent(listbox);
					}
				}
				if (listbox.getItems().size() == 0) {
					grid.adicionarLinha("Classes Relacionadas", new Label(
							"<<Vazio>>"));
				} else {
					grid.adicionarLinha("Classes Relacionadas", listbox);
				}
			}

			new Separator().setParent(add);
		}
		return add;
	}
}

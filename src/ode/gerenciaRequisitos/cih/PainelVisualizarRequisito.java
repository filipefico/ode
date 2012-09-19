package ode.gerenciaRequisitos.cih;

import java.text.SimpleDateFormat;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vlayout;

public class PainelVisualizarRequisito extends Vlayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4609555786660056076L;

	public PainelVisualizarRequisito(Requisito requisito) {
		carregarComponentes(requisito);
	}

	public void carregarComponentes(Requisito requisito) {
		GridDados grid = new GridDados();
		grid.setParent(this);

		grid.adicionarLinha("Identificador",
				new Label(requisito.getIdentificador()));

		grid.adicionarLinha("Descrição", new Label(requisito.getDescricao()));

		grid.adicionarLinha(
				"Data de Criação",
				new Label(new SimpleDateFormat().format(requisito
						.getDataCriacao())));

		grid.adicionarLinha("Prioridade", new Label(requisito.getPrioridade()
				.name()));

		grid.adicionarLinha("Tipo de Requisito", new Label(requisito
				.getTipoRequisito().getNome()));

		grid.adicionarLinha("Categoria de Requisito",
				requisito.getCategoria() != null ? new Label(requisito
						.getCategoria().getNome()) : new Label("<<Vazio>>"));

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

		listbox = new Listbox();

		listhead = new Listhead();
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

		listbox = new Listbox();

		listhead = new Listhead();
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

		listbox = new Listbox();

		listhead = new Listhead();
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

		listbox = new Listbox();

		listhead = new Listhead();
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
			grid.adicionarLinha("Pacotes Relacionados", new Label("<<Vazio>>"));
		} else {
			grid.adicionarLinha("Pacotes Relacionados", listbox);
		}

		listbox = new Listbox();

		listhead = new Listhead();
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

		listbox = new Listbox();

		listhead = new Listhead();
		listhead.setParent(listbox);

		new Listheader("Nome").setParent(listhead);
		new Listheader("Descrição").setParent(listhead);

		casosUso = requisito.getCasosUso();
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
			grid.adicionarLinha("Classes Relacionadas", new Label("<<Vazio>>"));
		} else {
			grid.adicionarLinha("Classes Relacionadas", listbox);
		}

	}
}
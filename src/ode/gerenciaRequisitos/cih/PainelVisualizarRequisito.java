package ode.gerenciaRequisitos.cih;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Pacote;

import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
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

		Textbox tbIdentificador = new Textbox(requisito.getIdentificador());
		tbIdentificador.setReadonly(true);
		grid.adicionarLinha("Identificador", tbIdentificador);

		Datebox dbCriacao = new Datebox(requisito.getDataCriacao());
		dbCriacao.setReadonly(true);
		grid.adicionarLinha("Data de Criação", dbCriacao);

		Textbox tbPrioridade = new Textbox(requisito.getPrioridade().getNome());
		tbPrioridade.setReadonly(true);
		grid.adicionarLinha("Prioridade", tbPrioridade);

		Textbox tbDescricao = new Textbox(requisito.getDescricao());
		tbDescricao.setRows(5);
		tbDescricao.setMultiline(true);
		tbDescricao.setReadonly(true);
		grid.adicionarLinha("Descrição", tbDescricao);

		Textbox tbProjeto = new Textbox(requisito.getProjeto().getNome());
		tbProjeto.setReadonly(true);
		grid.adicionarLinha("Projeto", tbProjeto);

		Textbox tbTipoRequisito = new Textbox(requisito.getTipoRequisito()
				.getNome());
		tbTipoRequisito.setReadonly(true);
		grid.adicionarLinha("Tipo", tbTipoRequisito);

		Textbox tbCategoria = new Textbox(requisito.getCategoria().getNome());
		tbCategoria.setReadonly(true);
		grid.adicionarLinha("Categoria", tbCategoria);

		Listbox lbConflitos = new Listbox();
		Collection<Requisito> conflitos = requisito.getConflitos();
		for (Requisito r : conflitos) {
			Listitem item = new Listitem();
			new Listcell(r.getIdentificador()).setParent(item);
			new Listcell(r.getDescricao().substring(0, 99) + "...")
					.setParent(item);

			item.setValue(r);
			item.setParent(lbConflitos);
		}
		grid.adicionarLinha("Conflitos", lbConflitos);

		Listbox lbDependencias = new Listbox();
		Collection<Requisito> dependencias = requisito.getDependencias();
		for (Requisito r : dependencias) {
			Listitem item = new Listitem();
			new Listcell(r.getIdentificador()).setParent(item);
			new Listcell(r.getDescricao().substring(0, 99) + "...")
					.setParent(item);

			item.setValue(r);
			item.setParent(lbDependencias);
		}
		grid.adicionarLinha("Dependências", lbDependencias);

		Listbox lbPacotes = new Listbox();
		Collection<Pacote> pacotes = requisito.getPacotes();
		for (Pacote p : pacotes) {
			Listitem item = new Listitem();
			new Listcell(p.getNome()).setParent(item);
			new Listcell(p.getDescricao().substring(0, 99) + "...")
					.setParent(item);

			item.setValue(p);
			item.setParent(lbPacotes);
		}
		grid.adicionarLinha("Pacotes", lbPacotes);

		Listbox lbCasosUso = new Listbox();
		Collection<CasoUso> casosUso = requisito.getCasosUso();
		for (CasoUso c : casosUso) {
			Listitem item = new Listitem();
			new Listcell(c.getNome()).setParent(item);
			new Listcell(c.getDescricao().substring(0, 99) + "...")
					.setParent(item);

			item.setValue(c);
			item.setParent(lbCasosUso);
		}
		grid.adicionarLinha("Casos de Uso", lbCasosUso);

		Listbox lbResponsaveis = new Listbox();
		Collection<RecursoHumano> responsaveis = requisito.getResponsaveis();
		for (RecursoHumano resp : responsaveis) {
			Listitem item = new Listitem();
			new Listcell(resp.getNome()).setParent(item);

			item.setValue(resp);
			item.setParent(lbResponsaveis);
		}
		grid.adicionarLinha("Responsáveis", lbResponsaveis);

		Listbox lbInteressados = new Listbox();
		Collection<RecursoHumano> interessados = requisito.getInteressados();
		for (RecursoHumano interess : interessados) {
			Listitem item = new Listitem();
			new Listcell(interess.getNome()).setParent(item);

			item.setValue(interess);
			item.setParent(lbInteressados);
		}
		grid.adicionarLinha("Interessados", lbInteressados);

	}
}
package ode.processoPadrao.ciu;

import java.util.HashSet;
import java.util.Set;

import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KMetodo;
import ode.conhecimento.processo.cdp.KNorma;
import ode.conhecimento.processo.cdp.KRecursoHardware;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cdp.KRoteiro;
import ode.conhecimento.processo.cdp.KTecnica;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

public class JanEditarConhecimentosAtividadePP extends JanCore {
	Listbox listaK;

	public JanEditarConhecimentosAtividadePP(CtrlDefinirProcessoPadrao ctrl,
			AtividadeProcessoPadrao atv, EnumAtividadeProcessoPadrao enumAtv) {
		super(ctrl);
		conteudo(enumAtv);
		preencheLista(listaK, enumAtv, atv);
		botaoSalvar(atv, enumAtv);
		this.mostrar();
	}

	private void botaoSalvar(AtividadeProcessoPadrao atv,
			EnumAtividadeProcessoPadrao enumAtv) {

		Button buttonOK = new Button();
		buttonOK.setParent(this);
		buttonOK.setLabel("Salvar");

		EventSalvar eventSalvar = new EventSalvar(atv, enumAtv, this);
		buttonOK.addEventListener("onClick", eventSalvar);

	}

	private void preencheLista(Listbox lista,
			EnumAtividadeProcessoPadrao EnumAtv, AtividadeProcessoPadrao atv) {

		Set<Conhecimento> conhecimento = new HashSet<Conhecimento>();
		Set<Listitem> listItemsSelecionados = new HashSet<Listitem>();

		switch (EnumAtv) {
		case INSUMO:
			conhecimento.addAll(ctrl.getAllKArtefato());
			break;
		case PRODUTO:
			conhecimento.addAll(ctrl.getAllKArtefato());
			break;
		case REQUER_HARDWARE:
			conhecimento.addAll(ctrl.getAllKRecursoHardware());
			break;
		case REQUER_SOFTWARE:
			conhecimento.addAll(ctrl.getAllKRecursoSoftware());
			break;
		case ADOTA_METODOS:
			conhecimento.addAll(ctrl.getAllKProcedimentoMetodos());
			break;
		case ADOTA_NORMAS:
			conhecimento.addAll(ctrl.getAllKProcedimentoNormas());
			break;
		case ADOTA_ROTEIROS:
			conhecimento.addAll(ctrl.getAllKProcedimentoRoteiros());
			break;
		case ADOTA_TECNICAS:
			conhecimento.addAll(ctrl.getAllKProcedimentoTecnicas());
			break;
		case REALIZADA_POR:
			conhecimento.addAll(ctrl.getAllKRecursoHumano());
			break;
		}

		// esse for � responsavel por selecionar os elementos que j� est�o
		// associados ao objeto
		for (Conhecimento c : conhecimento) {
			Listitem li = new Listitem();
			Listcell lc = new Listcell();

			li.setParent(listaK);
			lc.setParent(li);
			lc.setLabel(c.getNome());
			lc.setValue(c);

			switch (EnumAtv) {
			case INSUMO:
				if (atv.getInsumos().contains(c)) {
					listItemsSelecionados.add(li);
				}
				break;
			case PRODUTO:
				if (atv.getProdutos().contains(c)) {
					listItemsSelecionados.add(li);
				}
				break;
			case REQUER_HARDWARE:
				if (atv.getRecursoHardware().contains(c)) {
					listItemsSelecionados.add(li);
				}
				break;
			case REQUER_SOFTWARE:
				if (atv.getRecursoSoftware().contains(c)) {
					listItemsSelecionados.add(li);
				}
				break;
			case ADOTA_METODOS:
				if (atv.getProcedimentoMetodo().contains(c)) {
					listItemsSelecionados.add(li);
				}
			case ADOTA_NORMAS:
				if (atv.getProcedimentoNorma().contains(c)) {
					listItemsSelecionados.add(li);
				}
			case ADOTA_ROTEIROS:
				if (atv.getProcedimentoRoteiro().contains(c)) {
					listItemsSelecionados.add(li);
				}
			case ADOTA_TECNICAS:
				if (atv.getProcedimentoTecnica().contains(c)) {
					listItemsSelecionados.add(li);
				}
				break;
			case REALIZADA_POR:
				if (atv.getRecursoHumano().contains(c)) {
					listItemsSelecionados.add(li);
				}
				break;
			}

		}

		listaK.setSelectedItems(listItemsSelecionados);
	}

	private static final long serialVersionUID = -6076367014192058189L;

	private void conteudo(EnumAtividadeProcessoPadrao EnumAtv) {
		switch (EnumAtv) {
		case INSUMO:
			this.setTitle("Editar artefato consumido");
			break;
		case PRODUTO:
			this.setTitle("Editar artefato produzido");
			break;
		case REQUER_HARDWARE:
			this.setTitle("Editar recurso de hardware requerido");
			break;
		case REQUER_SOFTWARE:
			this.setTitle("Editar recurso de software requerido");
			break;
		case ADOTA_METODOS:
			this.setTitle("Editar metodos adotados");
			break;
		case ADOTA_NORMAS:
			this.setTitle("Editar normas adotados");
			break;
		case ADOTA_ROTEIROS:
			this.setTitle("Editar roteiros adotados");
			break;
		case ADOTA_TECNICAS:
			this.setTitle("Editar tecnicas adotados");
			break;
		case REALIZADA_POR:
			this.setTitle("Editar responsavel associado");
			break;

		}

		listaK = new Listbox();
		listaK.setCheckmark(true);
		listaK.setMultiple(true);
		listaK.setParent(this);

	}

	class EventSalvar implements EventListener {
		private EnumAtividadeProcessoPadrao EnumAtv;
		private AtividadeProcessoPadrao atv;
		private JanEditarConhecimentosAtividadePP janela;

		public EventSalvar(AtividadeProcessoPadrao atv,
				EnumAtividadeProcessoPadrao EnumAtv,
				JanEditarConhecimentosAtividadePP janela) {
			this.atv = atv;
			this.EnumAtv = EnumAtv;
			this.janela = janela;

		}

		@Override
		public void onEvent(Event arg0) throws Exception {
			Set<Listitem> listItems = listaK.getSelectedItems();
			Set<KArtefato> conhecSelecionadosKartefato = new HashSet<KArtefato>();
			// Set<KRecurso> conhecSelecionadosKRecurso = new
			// HashSet<KRecurso>();
			Set<KRecursoHardware> conhecSelecionadosKRecursoHardware = new HashSet<KRecursoHardware>();
			Set<KFerramentaSoftware> conhecSelecionadosKRecursoSoftware = new HashSet<KFerramentaSoftware>();

			Set<KNorma> conhecSelecionadosKNorma = new HashSet<KNorma>();
			Set<KMetodo> conhecSelecionadosKMetodo = new HashSet<KMetodo>();
			Set<KRoteiro> conhecSelecionadosKRoteiro = new HashSet<KRoteiro>();
			Set<KTecnica> conhecSelecionadosKTecnica = new HashSet<KTecnica>();

			Set<KRecursoHumano> conhecSelecionadosKRecursoHumano = new HashSet<KRecursoHumano>();

			switch (EnumAtv) {
			case INSUMO:
			case PRODUTO:
				for (Listitem li : listItems) {
					conhecSelecionadosKartefato.add((KArtefato) ((Listcell) li
							.getChildren().get(0)).getValue());
				}
				break;
			case REQUER_HARDWARE:
				for (Listitem li : listItems) {
					conhecSelecionadosKRecursoHardware
							.add((KRecursoHardware) ((Listcell) li
									.getChildren().get(0)).getValue());
				}
				break;
			case REQUER_SOFTWARE:
				for (Listitem li : listItems) {
					conhecSelecionadosKRecursoSoftware
							.add((KFerramentaSoftware) ((Listcell) li
									.getChildren().get(0)).getValue());
				}
				break;
			case ADOTA_METODOS:
				for (Listitem li : listItems) {
					conhecSelecionadosKMetodo.add((KMetodo) ((Listcell) li
							.getChildren().get(0)).getValue());
				}
				break;
			case ADOTA_NORMAS:
				for (Listitem li : listItems) {
					conhecSelecionadosKNorma.add((KNorma) ((Listcell) li
							.getChildren().get(0)).getValue());
				}
				break;
			case ADOTA_ROTEIROS:
				for (Listitem li : listItems) {
					conhecSelecionadosKRoteiro.add((KRoteiro) ((Listcell) li
							.getChildren().get(0)).getValue());
				}
				break;
			case ADOTA_TECNICAS:
				for (Listitem li : listItems) {
					conhecSelecionadosKTecnica.add((KTecnica) ((Listcell) li
							.getChildren().get(0)).getValue());
				}
				break;

			case REALIZADA_POR:
				for (Listitem li : listItems) {
					conhecSelecionadosKRecursoHumano
							.add((KRecursoHumano) ((Listcell) li.getChildren()
									.get(0)).getValue());
				}

				break;
			}

			switch (EnumAtv) {
			case INSUMO:
				atv.setInsumos(conhecSelecionadosKartefato);
				break;
			case PRODUTO:
				atv.setProdutos(conhecSelecionadosKartefato);
				break;
			case REQUER_HARDWARE:
				atv.setRecursoHardware(conhecSelecionadosKRecursoHardware);
				break;
			case REQUER_SOFTWARE:
				atv.setRecursoSoftware(conhecSelecionadosKRecursoSoftware);
				break;
			case ADOTA_METODOS:
				atv.setProcedimentoMetodo(conhecSelecionadosKMetodo);
				break;
			case ADOTA_NORMAS:
				atv.setProcedimentoNorma(conhecSelecionadosKNorma);
				break;
			case ADOTA_ROTEIROS:
				atv.setProcedimentoRoteiro(conhecSelecionadosKRoteiro);
				break;
			case ADOTA_TECNICAS:
				atv.setProcedimentoTecnica(conhecSelecionadosKTecnica);
				break;
			case REALIZADA_POR:
				atv.setRecursoHumano(conhecSelecionadosKRecursoHumano);
				break;

			}
			ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
			janela.onClose();
		}
	}
}

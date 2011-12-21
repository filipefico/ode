package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zkoss.zhtml.Select;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Group;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listgroup;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;

import com.lowagie.text.ListItem;

import ode.medicao.planejamentoMedicao.cci.CtrlObjetivoMedicaoCRUD;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.TipoObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoSoftware;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Component
public class FormDadosObjetivoMedicao extends
		FormularioDadosCRUD<ObjetivoMedicao> {

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<ObjetivoEstrategico> ckEst = new NucleoMultipleListBox<ObjetivoEstrategico>();
	private NucleoMultipleListBox<ObjetivoSoftware> ckSoft = new NucleoMultipleListBox<ObjetivoSoftware>();
	private GridDados gridDadosCadastro;
	private Groupbox gb = new Groupbox();
	private Groupbox lg = new Groupbox();
	private NucleoListbox<TipoObjetivoMedicao> llb = new NucleoListbox<TipoObjetivoMedicao>();
	private EventListener myOnSelect = new EventListener() {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ckEst.clearSelection();
			for (Listitem item :(Set<Listitem>)ckSoft.getSelectedItems()) {
				for (ObjetivoEstrategico est : ((ObjetivoSoftware) item
						.getValue()).getObjetivoEstrategico()) {
					ckEst.getItem(est).setSelected(true);
				}
			}
		}
	};

	@Override
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// ////////////////////////////
		// Dados Cadastro - Objetivos Estrategicos Relacionados
		// ////////////////////////////
		NucleoTab tabDadosObjetivos = new NucleoTab();

		tabDadosObjetivos.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_OBJETIVO)+"s");

		CtrlObjetivoMedicaoCRUD ctrl = (CtrlObjetivoMedicaoCRUD) this
				.getControlador();
		AplCadastrarObjetivoEstrategico aplEst = ctrl
				.getAplKObjetivoEstrategico();
		Collection<ObjetivoEstrategico> estrategicos = aplEst.recuperarTodos();

		ckEst.setObjetos(estrategicos);
		ckEst.setCheckable(false);
		lg.appendChild(new Caption(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_OBJETIVOS_ESTRATEGICOS)));
		lg.appendChild(ckEst);

		// ////////////////////////////
		// Dados Cadastro - Objetivos Software Relacionados
		// ////////////////////////////


		AplCadastrarObjetivoSoftware aplSoft = ctrl.getAplKObjetivoSoftware();
		Collection<ObjetivoSoftware> software = aplSoft.recuperarTodos();

		ckSoft.setObjetos(software);
		ckSoft.addEventListener("onSelect", myOnSelect);
		
		gb.appendChild(new Caption(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_OBJETIVOS_SOFTWARE)));
		gb.appendChild(ckSoft);

		// //////////////

		// ////////////////////////////
		// Dados Cadastro - Nome e Descricao
		// ////////////////////////////

		// Atribui o conteúdo à tab
		gridDadosCadastro = new GridDados();
		tbNome.setWidth("385px");
		tbNome.setMaxlength(300);
		tbNome.setHeight("35px");
		tbNome.setMultiline(true);
		gridDadosCadastro
				.adicionarLinhaObrigatoria(
						NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
						tbNome);

		tbDescricao.setWidth("385px");
		tbDescricao.setMaxlength(400);
		tbDescricao.setHeight("65px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),
				tbDescricao);
		
		Listitem li = new Listitem();
		li.setSelected(true);
		li.setLabel("Analise de Desempenho");
		li.setValue(TipoObjetivoMedicao.ANALISEDEDESEMPENHO);
		llb.appendChild(li);
		li = new Listitem();
		li.setLabel("Monitoracao e Controle");
		li.setValue(TipoObjetivoMedicao.MONITORACAOECONTROLE);
		llb.appendChild(li);
		llb.setCheckmark(true);
		gridDadosCadastro.adicionarLinhaObrigatoria("Tipo", llb);

		return listaTabs;
	}

	@Override
	protected void montarTabs() {
		super.montarTabs();
		this.appendChild(lg);
		this.appendChild(gb);
		this.appendChild(gridDadosCadastro);
	};

	@Override
	protected void preencherDadosTela(ObjetivoMedicao objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		ckEst.setObjetosSelecionados(objeto.getObjetivosEstrategicos());
		ckSoft.setObjetosSelecionados(objeto.getObjetivosSoftware());
		llb.setObjetoSelecionado(objeto.getTipoObjetivoMedicao());
	}

	@Override
	protected void preencherDadosObjeto(ObjetivoMedicao objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setObjetivosEstrategicos(ckEst.getObjetosSelecionados());
		objeto.setObjetivosSoftware(ckSoft.getObjetosSelecionados());
		objeto.setTipoObjetivoMedicao(llb.getObjetoSelecionado());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbDescricao.setConstraint("no empty");
	}

}

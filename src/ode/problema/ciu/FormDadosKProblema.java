package ode.problema.ciu;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.problema.cdp.KCategoriaProblema;
import ode.problema.cdp.KProblema;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Textbox;




public class FormDadosKProblema extends FormularioDadosCRUD<KProblema> {

	private static final long serialVersionUID = 1L;

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoListbox<KCategoriaProblema> listboxCategoria = new NucleoListbox<KCategoriaProblema>();

	private KCategoriaProblema categoriaSelecionada;

	private List<KCategoriaProblema> listaKCategoria;

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
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
		
		
tbDescricao.setWidth("240px");
		tbDescricao.setMaxlength(400);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);


		listboxCategoria.setWidth("240px");
		listboxCategoria.setRows(1);
		listboxCategoria.setMold("select");
		listaKCategoria = new ArrayList<KCategoriaProblema>(((CtrlKProblemaCRUD)this.getControlador()).listarKCategoriaProblema());

		listboxCategoria.setObjetos(listaKCategoria);
	//	listboxCategoria.setSelectedIndex(0);
		categoriaSelecionada = listboxCategoria.getObjetoSelecionado();
	

//	    listboxCategoria.addEventListener("onSelect", new EventListenerCategoria());
		gridDadosCadastro.adicionarLinhaObrigatoria("Categoria", listboxCategoria);


		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		return tabDadosCadastro;
	}
	

	private class EventListenerCategoria implements EventListener {

		public void onEvent(Event event) {
			categoriaSelecionada = listboxCategoria.getObjetoSelecionado();
		}
	}

	@Override
	protected void preencherDadosObjeto(KProblema objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setCategoria(listboxCategoria.getObjetoSelecionado());

	}

	@Override
	protected void preencherDadosTela(KProblema objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		listboxCategoria.setObjetoSelecionado(objeto.getCategoria());
		categoriaSelecionada = listboxCategoria.getObjetoSelecionado();
		
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}
}

	



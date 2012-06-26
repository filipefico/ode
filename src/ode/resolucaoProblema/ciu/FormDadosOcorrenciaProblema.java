package ode.resolucaoProblema.ciu;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Listitem;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.entidadeProblema.cci.CtrlEntidadeProblema;
import ode.entidadeProblema.cdp.ArtefatoProblema;
import ode.entidadeProblema.cdp.ProjetoProblema;
import ode.problema.cdp.KProblema;
import ode.resolucaoProblema.cdp.NivelImpacto;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

public class FormDadosOcorrenciaProblema extends FormularioDadosCRUD<OcorrenciaProblema> {
	
	private static final long serialVersionUID = 5403126199937407213L;
	private Textbox tbDescricao = new Textbox();
	private Textbox tbNome = new Textbox();
	private Textbox tbVersao = new Textbox();
	private NucleoListbox<KProblema> listboxProblema = new NucleoListbox<KProblema>();
	private KProblema problemaSelecionado;
	private List<KProblema> listaKProblema;
	private NucleoListbox<RecursoHumano> listboxRecursoHumano = new NucleoListbox<RecursoHumano>();
	private RecursoHumano recursoSelecionado;
	private List<RecursoHumano> listaRecursoHumano;
	private NucleoListbox<ArtefatoProblema> listboxArtefato = new NucleoListbox<ArtefatoProblema>();
	private ArtefatoProblema artefatoSelecionado;
	private List<ArtefatoProblema> listaArtefatoProblema;
	private NucleoListbox<ProjetoProblema> listboxProjeto = new NucleoListbox<ProjetoProblema>();
	private ProjetoProblema projetoSelecionado;
	private List<ProjetoProblema> listaProjetoProblema;
	private Datebox tbData = new Datebox();
	private NucleoListbox<NivelImpacto> listboxnivelimpacto = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> listboxnivel = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> listboxnivelimpactoF = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> listboxnivelF = new NucleoListbox<NivelImpacto>();



	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
		return listaTabs;
	}

	
	private NucleoTab definirTabDadosCadastro() {
		NucleoTab tabDadosCadastro = new NucleoTab();
		GridDados gridDadosCadastro = new GridDados();

		
		

		
		tbNome.setWidth("240px");
		tbNome.setMaxlength(400);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		tbData.setWidth("240px");
		tbData.setMaxlength(400);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DATA),tbData);
	
		listboxProblema.setWidth("240px");
		listboxProblema.setRows(1);
		listboxProblema.setMold("select");
		listaKProblema = new ArrayList<KProblema>(((CtrlOcorrenciaProblema)this.getControlador()).listarKProblema());
		listboxProblema.setObjetos(listaKProblema);
		listboxProblema.selecionarPrimeiroElemento();
		problemaSelecionado = listboxProblema.getObjetoSelecionado();
		
		gridDadosCadastro.adicionarLinhaObrigatoria("Problema", listboxProblema);
		
		
		  listboxArtefato.setWidth("240px");
		listboxArtefato.setRows(1);
		listboxArtefato.setMold("select");
		listaArtefatoProblema = new ArrayList<ArtefatoProblema>(((CtrlOcorrenciaProblema)this.getControlador()).listarArtefatoProblema());
		listboxArtefato.setObjetos(listaArtefatoProblema);
		artefatoSelecionado = listboxArtefato.getObjetoSelecionado();
		
		gridDadosCadastro.adicionarLinhaObrigatoria("Artefato", listboxArtefato);
		tbVersao.setWidth("240px");
		tbVersao.setMaxlength(400);
		gridDadosCadastro.adicionarLinha("Versão", tbVersao);
		 
		  listboxProjeto.setWidth("240px");
			listboxProjeto.setRows(1);
			listboxProjeto.setMold("select");
			listaProjetoProblema = new ArrayList<ProjetoProblema>(((CtrlOcorrenciaProblema)this.getControlador()).listarProjetoProblema());
			listboxProjeto.setObjetos(listaProjetoProblema);
			projetoSelecionado = listboxProjeto.getObjetoSelecionado();
			gridDadosCadastro.adicionarLinhaObrigatoria("Projeto", listboxProjeto);
			
			
		  listboxRecursoHumano.setWidth("240px");
			listboxRecursoHumano.setRows(1);
			listboxRecursoHumano.setMold("select");
			listaRecursoHumano = new ArrayList<RecursoHumano>(((CtrlOcorrenciaProblema)this.getControlador()).listarRecursoHumano());
			listboxRecursoHumano.setObjetos(listaRecursoHumano);
			recursoSelecionado = listboxRecursoHumano.getObjetoSelecionado();
			
			gridDadosCadastro.adicionarLinhaObrigatoria("Recurso Humano", listboxRecursoHumano);
			 
		Listitem liN = new Listitem();
		Listcell lcN = new Listcell();
		listboxnivelimpacto.setWidth("233px");
		listboxnivelimpacto.setRows(1);
		listboxnivelimpacto.setObjetos(NivelImpacto.values());
		listboxnivelimpacto.setMold("select");
		listboxnivelimpacto.selecionarPrimeiroElemento();
		lcN.appendChild(listboxnivelimpacto);
		liN.appendChild(lcN);
		listboxnivel.appendChild(liN);
		gridDadosCadastro.adicionarLinhaObrigatoria("Impacto Presente", listboxnivel);
		
		Listitem liNF = new Listitem();
		Listcell lcNF = new Listcell();
		listboxnivelimpactoF.setWidth("233px");
		listboxnivelimpactoF.setRows(1);
		listboxnivelimpactoF.setObjetos(NivelImpacto.values());
		listboxnivelimpactoF.setMold("select");
		listboxnivelimpactoF.selecionarPrimeiroElemento();
		lcNF.appendChild(listboxnivelimpactoF);
		liNF.appendChild(lcNF);
		listboxnivelF.appendChild(liNF);
		gridDadosCadastro.adicionarLinhaObrigatoria("Impacto Futuro", listboxnivelF);
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		
		
		tbDescricao.setWidth("240px");
		tbDescricao.setMaxlength(400);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);
		
		
		
		
		
		return tabDadosCadastro;
	}
	

	@Override
	protected void preencherDadosTela(OcorrenciaProblema objeto)
			throws NucleoRegraNegocioExcecao {
		tbDescricao.setValue(objeto.getDescricao());
		tbNome.setValue(objeto.getNome());
		tbData.setValue(objeto.getData());
			listboxProblema.setObjetoSelecionado(objeto.getKproblema());
			problemaSelecionado = listboxProblema.getObjetoSelecionado();
			listboxRecursoHumano.setObjetoSelecionado(objeto.getRecursohumano());
			listboxProjeto.setObjetoSelecionado(objeto.getProjetoproblema());
			listboxArtefato.setObjetoSelecionado(objeto.getArtefatoproblema());
			listboxnivelimpacto.setObjetoSelecionado(objeto.getNivelimpactopresente());
			listboxnivelimpactoF.setObjetoSelecionado(objeto.getNivelimpactofuturo());
		
	}

	@Override
	protected void preencherDadosObjeto(OcorrenciaProblema objeto) {
		
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setNome(tbNome.getValue());
		objeto.setData(tbData.getValue());
		objeto.setKproblema(listboxProblema.getObjetoSelecionado());
		objeto.setRecursohumano(listboxRecursoHumano.getObjetoSelecionado());
		objeto.setProjetoproblema(listboxProjeto.getObjetoSelecionado());
		objeto.setArtefatoproblema(listboxArtefato.getObjetoSelecionado());
		objeto.setNivelimpactopresente(listboxnivelimpacto.getObjetoSelecionado());
		objeto.setNivelimpactofuturo(listboxnivelimpactoF.getObjetoSelecionado());
		
		
	}
	

}

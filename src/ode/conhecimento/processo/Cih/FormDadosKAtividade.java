package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.GridDados;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.componentes.selecao.NucleoBandbox;
import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KProcedimento;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cdp.KRecurso;
import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.conhecimento.processo.Cgd.KArtefatoDAO;
import ode.conhecimento.processo.Cgd.KAtividadeDAO;
import ode.conhecimento.processo.Cgd.KCategoriaProcessoDAO;
import ode.conhecimento.processo.Cgd.KProcedimentoDAO;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.conhecimento.processo.Cgd.KRecursoDAO;
import ode.conhecimento.processo.Cgd.TipoKArtefatoDAO;
import ode.controle.controleProcesso.Cgd.RecursoDAO;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;


public class FormDadosKAtividade extends FormularioDadosCRUD<KAtividade>{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4523082469660955412L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Combobox coProcesso = new Combobox();
	Listbox lbRecurso = new Listbox();
	Listbox lbProduto = new Listbox();
	Listbox lbInsumo = new Listbox();
	Listbox lbProcedimento = new Listbox();
	Listbox lbSubAtividade = new Listbox();
	Listbox lbPreAtividade = new Listbox();
	

	public static KProcesso buscaProcessoPorNome (String nome){
		KProcessoDAO processo = (KProcessoDAO) SpringUtil.getBean("kProcessoDao");
		Collection<KProcesso> listaProcessos = processo.recuperarTodos();
		for (KProcesso K : listaProcessos) {
			if (K.getNome().equals(nome)){
				return K;
			}
		}
		return null;
	}
	

	@Override
	protected List definirTabs() {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		
	
		//Recupera Processo e adiciona ao Combobox
		KProcessoDAO processo = (KProcessoDAO) SpringUtil
				.getBean("kProcessoDao");
		Collection<KProcesso> listaProcessos = processo.recuperarTodos();
		
		for (KProcesso K : listaProcessos) {
			coProcesso.appendItem(K.getNome());
		}
		coProcesso.setAutocomplete(true);
		

		NucleoTab tabDadosCadastro = new NucleoTab();

		// Atribui o nome à tab
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_GERAIS));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("400px");
		tbNome.setMaxlength(50);		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROCESSO), coProcesso);
		
		tbDescricao.setWidth("400px");
		tbDescricao.setMaxlength(300);
		tbDescricao.setHeight("150px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);		
		
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		//Tab Recursos
		
		NucleoTab tabRecursos = new NucleoTab();
		tabRecursos.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_RECURSOS));
		lbRecurso.setCheckmark(true);
		lbRecurso.setMultiple(true);
		tabRecursos.setConteudoTab(lbRecurso);
		listaTabs.add(tabRecursos);
		GridDados gridRecursos = new GridDados ();
		
		//Tab Produtos
		NucleoTab tabProdutos = new NucleoTab();
		tabProdutos.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PRODUTOS));
		lbProduto.setCheckmark(true);
		lbProduto.setMultiple(true);
		tabProdutos.setConteudoTab(lbProduto);
		listaTabs.add(tabProdutos);
		GridDados gridProdutos = new GridDados ();
		
		//Tab Insumos
		NucleoTab tabInsumos = new NucleoTab();
		tabInsumos.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_INSUMOS));
		lbInsumo.setCheckmark(true);
		lbInsumo.setMultiple(true);
		tabInsumos.setConteudoTab(lbInsumo);
		listaTabs.add(tabInsumos);
		GridDados gridInsumos = new GridDados ();
		
		//Tab Procedimentos
		NucleoTab tabProcedimentos = new NucleoTab();
		tabProcedimentos.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROCEDIMENTOS));
		lbProcedimento.setCheckmark(true);
		lbProcedimento.setMultiple(true);
		tabProcedimentos.setConteudoTab(lbProcedimento);
		listaTabs.add(tabProcedimentos);
		GridDados gridProcedimentos = new GridDados ();
		
		//Tab SubAtividade
		NucleoTab tabSubAtividade = new NucleoTab();
		tabSubAtividade.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_SUBATIVIDADE));
		lbSubAtividade.setCheckmark(true);
		lbSubAtividade.setMultiple(true);
		tabSubAtividade.setConteudoTab(lbSubAtividade);
		listaTabs.add(tabSubAtividade);
		GridDados gridSubAtividade = new GridDados ();
		
		//Tab PreAtividade
		NucleoTab tabPreAtividade = new NucleoTab();
		tabPreAtividade.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PREATIVIDADE));
		lbPreAtividade.setCheckmark(true);
		lbPreAtividade.setMultiple(true);
		tabPreAtividade.setConteudoTab(lbPreAtividade);
		listaTabs.add(tabPreAtividade);
		GridDados gridPreAtividade = new GridDados ();

		// recuperando os objetos
		
		
		KRecursoDAO  recursoDAO = (KRecursoDAO) SpringUtil
		.getBean("kRecursoDao");
		Collection<KRecurso> listaRecursos = recursoDAO
		.recuperarTodos();
		
		lbRecurso.setModel(new ListModelList(listaRecursos));
		lbRecurso.renderAll();
		
		KArtefatoDAO  produtoInsumoDAO = (KArtefatoDAO) SpringUtil
		.getBean("kArtefatoDao");
		Collection<KArtefato> listaProdutosInsumo = produtoInsumoDAO
		.recuperarTodos();
		
		lbProduto.setModel(new ListModelList(listaProdutosInsumo));
		lbProduto.renderAll();
		
		lbInsumo.setModel(new ListModelList(listaProdutosInsumo));
		lbInsumo.renderAll();
		
		KProcedimentoDAO  procedimentoDAO = (KProcedimentoDAO) SpringUtil
		.getBean("kProcedimentoDao");
		Collection<KProcedimento> listaProcedimentos = procedimentoDAO
		.recuperarTodos();
		
		lbProcedimento.setModel(new ListModelList(listaProcedimentos));
		lbProcedimento.renderAll();
		
		KAtividadeDAO  atividadeDAO = (KAtividadeDAO) SpringUtil
		.getBean("kAtividadeDao");
		Collection<KAtividade> listaAtividade = atividadeDAO
		.recuperarTodos();
		
		lbSubAtividade.setModel(new ListModelList(listaAtividade));
		lbSubAtividade.renderAll();
		
		lbPreAtividade.setModel(new ListModelList(listaAtividade));
		lbPreAtividade.renderAll();
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(KAtividade kAtividade) {		
		kAtividade.setNome(tbNome.getValue());
		kAtividade.setDescricao(tbDescricao.getValue());
		
		HashSet<KRecurso> setRecursos = new HashSet<KRecurso>();
		if (lbRecurso.getSelectedItems() != null) {
			for (Iterator<Listitem> iterDependencia = lbRecurso
					.getSelectedItems().iterator(); iterDependencia.hasNext();/**/) {
				setRecursos.add((KRecurso) iterDependencia.next()
						.getValue());
			}
		}
		kAtividade.setRecursos(setRecursos);
		
		HashSet<KArtefato> setProdutos = new HashSet<KArtefato>();
		if (lbProduto.getSelectedItems() != null) {
			for (Iterator<Listitem> iterProdutos = lbProduto
					.getSelectedItems().iterator(); iterProdutos.hasNext();/**/) {
				setProdutos.add((KArtefato) iterProdutos.next()
						.getValue());
			}
		}
		kAtividade.setProdutos(setProdutos);
		
		HashSet<KArtefato> setInsumo = new HashSet<KArtefato>();
		if (lbInsumo.getSelectedItems() != null) {
			for (Iterator<Listitem> iterInsumo = lbInsumo
					.getSelectedItems().iterator(); iterInsumo.hasNext();) {
				setInsumo.add((KArtefato) iterInsumo.next()
						.getValue());
			}
		}
		kAtividade.setInsumos(setInsumo);
		
		HashSet<KProcedimento> setProcedimento = new HashSet<KProcedimento>();
		if (lbInsumo.getSelectedItems() != null) {
			for (Iterator<Listitem> iterProcedimento = lbInsumo
					.getSelectedItems().iterator(); iterProcedimento.hasNext();) {
				setProcedimento.add((KProcedimento) iterProcedimento.next()
						.getValue());
			}
		}
		kAtividade.setProcedimentos(setProcedimento);		
		
		HashSet<KAtividade> setPreAtividade = new HashSet<KAtividade>();
		if (lbPreAtividade.getSelectedItems() != null) {
			for (Iterator<Listitem> iterPreAtividade = lbPreAtividade
					.getSelectedItems().iterator(); iterPreAtividade.hasNext();) {
				setPreAtividade.add((KAtividade) iterPreAtividade.next()
						.getValue());
			}
		}
		kAtividade.setPreAtividades(setPreAtividade);
		
		HashSet<KAtividade> setSubAtividade = new HashSet<KAtividade>();
		if (lbSubAtividade.getSelectedItems() != null) {
			for (Iterator<Listitem> iterSubAtividade = lbSubAtividade
					.getSelectedItems().iterator(); iterSubAtividade.hasNext();) {
				setSubAtividade.add((KAtividade) iterSubAtividade.next()
						.getValue());
			}
		}
		kAtividade.setSubAtividades(setSubAtividade);
		
		kAtividade.setKProcesso(buscaProcessoPorNome(coProcesso.getValue()));
		
	}
	
	

	@Override
	protected void preencherDadosTela(KAtividade kAtividade) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(kAtividade.getNome());
		tbDescricao.setValue(kAtividade.getDescricao());
		
		
		if (lbRecurso.getItems() != null) {
			for (KRecurso subRecurso : kAtividade.getRecursos()) {
				for (int indexlistbox = 0; indexlistbox < lbRecurso
						.getItems().size(); indexlistbox++) {
					Long idlbRecursos = ((KRecurso) lbRecurso
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idRecursos = subRecurso.getId();
					if (idlbRecursos.compareTo(idRecursos) == 0) {
						lbRecurso.addItemToSelection(lbRecurso
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}
		
		if (lbProduto.getItems() != null) {
			for (KArtefato subProdutos : kAtividade.getProdutos()) {
				for (int indexlistbox = 0; indexlistbox < lbProduto
						.getItems().size(); indexlistbox++) {
					Long idlbProdutos = ((KArtefato) lbProduto
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idProdutos = subProdutos.getId();
					if (idlbProdutos.compareTo(idProdutos) == 0) {
						lbProduto.addItemToSelection(lbProduto
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}
		
		if (lbInsumo.getItems() != null) {
			for (KArtefato subInsumo : kAtividade.getInsumos()) {
				for (int indexlistbox = 0; indexlistbox < lbInsumo
						.getItems().size(); indexlistbox++) {
					Long idlbInsumo = ((KArtefato) lbInsumo
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idInsumo = subInsumo.getId();
					if (idlbInsumo.compareTo(idInsumo) == 0) {
						lbInsumo.addItemToSelection(lbInsumo
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}
		
		if (lbProcedimento.getItems() != null) {
			for (KProcedimento subProcedimento : kAtividade.getProcedimentos()) {
				for (int indexlistbox = 0; indexlistbox < lbProcedimento
						.getItems().size(); indexlistbox++) {
					Long idlbProcedimento = ((KProcedimento) lbProcedimento
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idProcedimento = subProcedimento.getId();
					if (idlbProcedimento.compareTo(idProcedimento) == 0) {
						lbProcedimento.addItemToSelection(lbProcedimento
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}
		
		if (lbSubAtividade.getItems() != null) {
			for (KAtividade subSubAtividade : kAtividade.getSubAtividades()) {
				for (int indexlistbox = 0; indexlistbox < lbSubAtividade
						.getItems().size(); indexlistbox++) {
					Long idlbSubAtividade = ((KAtividade) lbSubAtividade
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idSubAtividade = subSubAtividade.getId();
					if (idlbSubAtividade.compareTo(idSubAtividade) == 0) {
						lbSubAtividade.addItemToSelection(lbSubAtividade
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}
		
		if (lbPreAtividade.getItems() != null) {
			for (KAtividade subpreAtividade : kAtividade.getPreAtividades()) {
				for (int indexlistbox = 0; indexlistbox < lbPreAtividade
						.getItems().size(); indexlistbox++) {
					Long idlbPreAtividade = ((KAtividade) lbPreAtividade
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idPreAtividade = subpreAtividade.getId();
					if (idlbPreAtividade.compareTo(idPreAtividade) == 0) {
						lbPreAtividade.addItemToSelection(lbPreAtividade
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}
		
		if (kAtividade.getKProcesso() != null){
			coProcesso.setValue(kAtividade.getKProcesso().getNome());
		}
		
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
				
	}
}

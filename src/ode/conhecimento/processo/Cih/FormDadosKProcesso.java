package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;

import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cdp.KTipoInteracao;
import ode.conhecimento.processo.Cgd.KCategoriaProcessoDAO;
import ode.conhecimento.processo.Cgd.KTipoInteracaoDAO;
import ode.conhecimento.processo.Cdp.KResultadoProcesso;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;

public class FormDadosKProcesso extends FormularioDadosCRUD<KProcesso>{
	
	private Textbox tbNome = new Textbox();
	private Textbox tbSigla = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Textbox tbProposito = new Textbox();
	private Combobox coCategoria = new Combobox();
	private Listbox lbInteracao = new Listbox();

	private Radiogroup rgEngenharia = new Radiogroup();
	private Radio cbEngenhariaNao =  new Radio("Não");
	private Radio cbEngenhariaSim =  new Radio("Sim");
	
	
	public static KCategoriaProcesso buscaCategoriaPorNome (String nome){
		KCategoriaProcessoDAO Categoria = (KCategoriaProcessoDAO) SpringUtil.getApplicationContext().getBean(KCategoriaProcessoDAO.class);
		Collection<KCategoriaProcesso> listaCategorias = Categoria.recuperarTodos();
		for (KCategoriaProcesso K : listaCategorias) {
			if (K.getNome().equals(nome)){
				return K;
			}
		}
		return null;
	}
	
	private static void ReadFromListbox (KProcesso objeto, Listbox lista){
		int i = 0, j;
		Set<KTipoInteracao> tiposInteracao = new HashSet();
		KTipoInteracaoDAO tipo = (KTipoInteracaoDAO) SpringUtil.getApplicationContext().getBean(KTipoInteracaoDAO.class);
		Object[] listaTipos = tipo.recuperarTodos().toArray();
		KTipoInteracao novo;
		while (i < 5){
			if (lista.getItemAtIndex(i).isSelected()){
				j = 0;
				while (j < 5){
					novo = (KTipoInteracao)listaTipos[j];
					if (lista.getItemAtIndex(i).getValue().equals(novo.getNome())){
						tiposInteracao.add(novo);
						break;
					}
					j++;
				}
			}
			i++;
		}
		objeto.setKTipoInteracao(tiposInteracao);
	}
	
	private static void loadListbox (KProcesso objeto, Listbox lista){
		int i = 0, j, k;
		Set<KTipoInteracao> listTipInter2 = objeto.getKTipoInteracao();
		if (listTipInter2 != null){
			j = listTipInter2.size();
			Object[] listTipInter = listTipInter2.toArray();
			KTipoInteracao kti;
			objeto.setSigla (String.valueOf(j));
			while (j > 0){
				kti = (KTipoInteracao) listTipInter[i];
				k = 0;
				while (k < 5){
					if (kti.getNome().equals(lista.getItemAtIndex(k).getLabel())){
						lista.getItemAtIndex(k).setSelected(true);
						j--;
						break;
					}
					k++;
				}
				i++;
			}
		}
	}
	
	public static void ReadFromCombobox (KProcesso objeto, Combobox coCategoria){
		KCategoriaProcessoDAO Categoria = (KCategoriaProcessoDAO) SpringUtil.getApplicationContext().getBean(KCategoriaProcessoDAO.class);
		Collection<KCategoriaProcesso> listaCategorias = Categoria.recuperarTodos();
		for (KCategoriaProcesso K : listaCategorias) {
			if (K.getNome().equals(coCategoria.getSelectedItem().getLabel())){
				objeto.setCategoria(K);
				return;
			}
		}
	}
	
	private void desabilitaTab(int i){
		List<Tab> teste = super.tabs.getChildren();
		teste.get(i).setDisabled(true);
	}
	private void habilitaTab(int i){
		List<Tab> teste = super.tabs.getChildren();
		teste.get(i).setDisabled(false);
	}
	
	@Override
	protected List definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		
		
		//Recupera Categorias de Processo e adiciona ao Combobox
		KCategoriaProcessoDAO Categoria = (KCategoriaProcessoDAO) SpringUtil
				.getApplicationContext().getBean(KCategoriaProcessoDAO.class);
		Collection<KCategoriaProcesso> listaCategorias = Categoria.recuperarTodos();
		for (KCategoriaProcesso K : listaCategorias) {
			coCategoria.appendItem(K.getNome());
		}
		coCategoria.setAutocomplete(true);
		
		/* Tab Dados Gerais. */
		NucleoTab tabDadosGerais = new NucleoTab();
		tabDadosGerais.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));
		GridDados gridDadosGerais = new GridDados ();
		tbNome.setWidth("400px");
		tbNome.setMaxlength(100);		
		gridDadosGerais.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		tbSigla.setWidth("400px");
		tbSigla.setMaxlength(100);		
		gridDadosGerais.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_SIGLA),tbSigla);
		
		
		tbDescricao.setWidth("400px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosGerais.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);
		
		gridDadosGerais.adicionarLinha("Processo É De Engenharia", rgEngenharia);
		cbEngenhariaSim.setParent(rgEngenharia);
		cbEngenhariaNao.setParent(rgEngenharia);
		cbEngenhariaNao.setChecked(true);
		rgEngenharia.addEventListener("onCheck", new EListenerEngenharia());
		
		
		gridDadosGerais.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_CATEGORIA), coCategoria);
		
		tbProposito.setWidth("400px");
		tbProposito.setMaxlength(500);
		tbProposito.setHeight("145px");
		tbProposito.setMultiline(true);
		gridDadosGerais.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROPOSITO),tbProposito);	
		
		//Tab Resultados Esperados
		NucleoTab tabResultEsper = new NucleoTab();
		tabResultEsper.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_RESULTADOS_ESPERADOS));
		GridDados gridResultEsper = new GridDados ();
		
		//Tab Tipos de Interação
		NucleoTab tabTiposInter = new NucleoTab();
		tabTiposInter.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_TIPOS_INTERACAO));
		GridDados gridTiposInter = new GridDados ();
		
		KTipoInteracaoDAO tipo = (KTipoInteracaoDAO) SpringUtil.getApplicationContext().getBean(KTipoInteracaoDAO.class);
		Collection<KTipoInteracao> listaTipos = tipo.recuperarTodos();
		for (KTipoInteracao TI : listaTipos) {
			lbInteracao.appendItem(TI.getNome(), TI.getNome());
		}		
		
		lbInteracao.setWidth("400px");
		lbInteracao.setHeight("120px");
		lbInteracao.setCheckmark(true);
		lbInteracao.setMultiple(true);
		
		gridTiposInter.adicionarLinha ("Tipos de Interação", lbInteracao);
		
		tabDadosGerais.setConteudoTab(gridDadosGerais);
		listaTabs.add(tabDadosGerais);
		
		tabResultEsper.setConteudoTab(gridResultEsper);
		listaTabs.add(tabResultEsper);
		
		tabTiposInter.setConteudoTab(gridTiposInter);
		listaTabs.add(tabTiposInter);
		
		
		return listaTabs;
	}

	private class EListenerEngenharia implements EventListener{
		public void onEvent(Event event) {
			if (cbEngenhariaSim.isChecked() == true){
				desabilitaTab(2);
			}else{
				habilitaTab(2);
			}
		}	
			@SuppressWarnings("unused")
			public boolean isAsap() {
				return true;
			}
	}

	@Override
	protected void preencherDadosObjeto(KProcesso objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setProposito(tbProposito.getValue());
		objeto.setSigla(tbSigla.getValue());
		objeto.setEhEngenharia(cbEngenhariaSim.isChecked());
		objeto.setCategoria(buscaCategoriaPorNome(coCategoria.getValue()));
		
		if (cbEngenhariaSim.isChecked()){
			objeto.setKTipoInteracao(null);
		}else{
			ReadFromListbox (objeto, lbInteracao);
		}
	}
	
	@Override
	protected void preencherDadosTela(KProcesso objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		tbSigla.setValue(objeto.getSigla());
		tbProposito.setValue(objeto.getProposito());
		cbEngenhariaSim.setChecked(objeto.isEhEngenharia());
		if (objeto.getCategoria() != null){
			coCategoria.setValue(objeto.getCategoria().getNome());
		}
		
		loadListbox (objeto, lbInteracao);
		if (cbEngenhariaSim.isChecked() == true){
			lbInteracao.clearSelection();
			desabilitaTab(2);
		}else{
			habilitaTab(2);
		}
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbSigla.setConstraint("no empty");
		tbProposito.setConstraint("no empty");
	}
}

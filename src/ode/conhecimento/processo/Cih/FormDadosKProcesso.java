package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Button;

import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.GridDados;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.componentes.selecao.NucleoBandbox;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cdp.KTipoInteracao;
import ode.conhecimento.processo.Cgd.KCategoriaProcessoDAO;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.conhecimento.processo.Cgd.KTipoInteracaoDAO;
import ode.conhecimento.processo.Cdp.KResultadoProcesso;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.conhecimento.processo.Cgd.TipoKArtefatoDAO;

public class FormDadosKProcesso extends FormularioDadosCRUD<KProcesso>{
	
	private Textbox tbNome = new Textbox();
	private Textbox tbSigla = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Textbox tbProposito = new Textbox();
	private Checkbox cbEngenhariaSim = new Checkbox();
	private Combobox coCategoria = new Combobox();
	private Listbox lbInteracao = new Listbox();
	
	private Textbox tbNome2 = new Textbox();
	private Textbox tbDescricao2 = new Textbox();
	
	private Combobox teste = new Combobox();
	
	public static KCategoriaProcesso buscaCategoriaPorNome (String nome){
		KCategoriaProcessoDAO Categoria = (KCategoriaProcessoDAO) SpringUtil.getBean("kcategoriaProcessoDao");
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
		Set<KTipoInteracao> tiposInteracao = new HashSet<KTipoInteracao>();
		KTipoInteracaoDAO tipo = (KTipoInteracaoDAO) SpringUtil.getBean("kTipoInteracaoDao");
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
	
	public static void ReadFromCombobox (KProcesso objeto, Combobox coCategoria){
		KCategoriaProcessoDAO Categoria = (KCategoriaProcessoDAO) SpringUtil.getBean("categoriaProcessoDao");
		Collection<KCategoriaProcesso> listaCategorias = Categoria.recuperarTodos();
		for (KCategoriaProcesso K : listaCategorias) {
			if (K.getNome().equals(coCategoria.getSelectedItem().getLabel())){
				objeto.setCategoria(K);
				return;
			}
		}
	}
	
	@Override
	protected List definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		
		
		//Recupera Categorias de Processo e adiciona ao Combobox
		KCategoriaProcessoDAO Categoria = (KCategoriaProcessoDAO) SpringUtil
				.getBean("kcategoriaProcessoDao");
		Collection<KCategoriaProcesso> listaCategorias = Categoria.recuperarTodos();
		for (KCategoriaProcesso K : listaCategorias) {
			coCategoria.appendItem(K.getNome());
		}
		coCategoria.setAutocomplete(true);
		
		//Tab Dados Gerais.
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
		
		gridDadosGerais.adicionarLinha("Processo é de Engenharia", cbEngenhariaSim);
		cbEngenhariaSim.setLabel(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_SIM));
		
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
		
		KProcessoDAO Proc = (KProcessoDAO) SpringUtil.getBean("kProcessoDao");
		Collection<KProcesso> listProc = Proc.recuperarTodos();
		for (KProcesso K : listProc) {
			teste.appendItem(K.getNome());
		}
		gridResultEsper.adicionarLinha("Teste", teste);
		
		tbNome2.setWidth("400px");
		tbNome2.setMaxlength(100);		
		gridResultEsper.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),tbNome2);
		
		tbDescricao2.setWidth("400px");
		tbDescricao2.setMaxlength(500);
		tbDescricao2.setHeight("145px");
		tbDescricao2.setMultiline(true);
		gridResultEsper.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao2);
		
		//Tab tipos de interações
		NucleoTab tabTiposInter = new NucleoTab();
		tabTiposInter.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_TIPOS_INTERACAO));
		GridDados gridTiposInter = new GridDados ();
		
		KTipoInteracaoDAO tipo = (KTipoInteracaoDAO) SpringUtil.getBean("kTipoInteracaoDao");
		Collection<KTipoInteracao> listaTipos = tipo.recuperarTodos();
		for (KTipoInteracao TI : listaTipos) {
			lbInteracao.appendItem(TI.getNome(), TI.getNome());
		}		
		
		lbInteracao.setWidth("400px");
		lbInteracao.setHeight("145px");
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
	
	@Override
	protected void preencherDadosObjeto(KProcesso objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setProposito(tbProposito.getValue());
		objeto.setSigla(tbSigla.getValue());
		objeto.setEhEngenharia(cbEngenhariaSim.isChecked());
		//ReadFromListbox (objeto, lbInteracao);
		objeto.setCategoria(buscaCategoriaPorNome(coCategoria.getValue()));
	}
	
	@Override
	protected void preencherDadosTela(KProcesso objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		tbSigla.setValue(objeto.getSigla());
		tbProposito.setValue(objeto.getProposito());
		cbEngenhariaSim.setChecked(objeto.isEhEngenharia());
		loadListbox (objeto, lbInteracao);
		coCategoria.setValue(objeto.getCategoria().getNome());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbSigla.setConstraint("no empty");
		tbProposito.setConstraint("no empty");
	}
}

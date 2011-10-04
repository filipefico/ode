package ode.conhecimento.organizacao.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.organizacao.Cdp.KCompetencia;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.zkoss.zul.*;

public class FormDadosKCompetencia extends FormularioDadosCRUD<KCompetencia> {

	private static final long serialVersionUID = 1L;

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
		return listaTabs;
	}

	private NucleoTab definirTabDadosCadastro() {
		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("240px");
		tbNome.setMaxlength(80);
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME), tbNome);
		
		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("45px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);	
		
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		return tabDadosCadastro;
	}

	@Override
	protected void preencherDadosObjeto(KCompetencia objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
	}

	@Override
	protected void preencherDadosTela(KCompetencia objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty: Favor informar o Nome!");
		tbDescricao.setConstraint("no empty: Favor informar a Descrição!");
	}

}
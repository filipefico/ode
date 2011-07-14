package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Textbox;

import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;


public class FormDadosTipoKArtefato extends FormularioDadosCRUD<TipoKArtefato> {


	private static final long serialVersionUID = -4718128554187248278L;

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	
	@Override
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		
		// Dados Cadastro
		NucleoTab tabDadosCadastro = new NucleoTab();
		
		// Atribui o nome � tab
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));
		
		// Atribui o conte�do � tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("285px");
		tbNome.setMaxlength(50);		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);	
	
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(TipoKArtefato objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
	}

	@Override
	protected void preencherDadosTela(TipoKArtefato objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());	
	}

}

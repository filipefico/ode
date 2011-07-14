package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.zkoss.zul.Textbox;


public class FormDadosKRecursoHardware extends FormularioDadosCRUD<KRecursoHardware>{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4523082469660955412L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();


	@Override
	protected List definirTabs() {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// ////////////////////////////
		// Dados Cadastro
		// ////////////////////////////
		NucleoTab tabDadosCadastro = new NucleoTab();

		// Atribui o nome à tab
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("150px");
		tbNome.setMaxlength(50);		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		tbDescricao.setWidth("150px");
		tbDescricao.setMaxlength(300);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);		
		
			tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(KRecursoHardware objeto) {		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
	}
	
	

	@Override
	protected void preencherDadosTela(KRecursoHardware objeto) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
				
	}
}

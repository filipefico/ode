package ode.controleProcesso.cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import ode.controleProcesso.cdp.RecursoHumano;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;

import org.zkoss.zul.Textbox;

public class FormDadosRecursoHumano extends FormularioDadosCRUD<RecursoHumano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Textbox tbNome = new Textbox();
	
	@Override
	protected List definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("285px");
		tbNome.setMaxlength(100);		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;
	}
	
	@Override
	protected void preencherDadosObjeto(RecursoHumano objeto) {		
		objeto.setNome(tbNome.getValue());
	}
	
	@Override
	protected void preencherDadosTela(RecursoHumano objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
	}
	
}
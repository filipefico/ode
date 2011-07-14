package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cdp.KTipoInteracao;
import ode.conhecimento.processo.Cgd.KCategoriaProcessoDAO;
import ode.conhecimento.processo.Cgd.KRecursoHumanoDAO;
import ode.conhecimento.processo.Cgd.KTipoInteracaoDAO;
import ode.nucleo.cih.NucleoBandbox;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;


public class FormDadosKRecursoHumano extends FormularioDadosCRUD<KRecursoHumano>{
	

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
	protected void preencherDadosObjeto(KRecursoHumano kRecursoHumano) {		
		kRecursoHumano.setNome(tbNome.getValue());
		kRecursoHumano.setDescricao(tbDescricao.getValue());
	}
	
	

	@Override
	protected void preencherDadosTela(KRecursoHumano kRecursoHumano) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(kRecursoHumano.getNome());
		tbDescricao.setValue(kRecursoHumano.getDescricao());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
				
	}
}

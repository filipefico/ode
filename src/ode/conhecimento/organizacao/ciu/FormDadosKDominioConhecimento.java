package ode.conhecimento.organizacao.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoBandbox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimento.organizacao.cdp.KDominioConhecimento;

import org.zkoss.zul.Textbox;

public class FormDadosKDominioConhecimento extends FormularioDadosCRUD<KDominioConhecimento>{
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
		tbNome.setWidth("285px");
		tbNome.setMaxlength(100);		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);	

		NucleoBandbox<KDominioConhecimento> band = new NucleoBandbox<KDominioConhecimento>(){

			@Override
			protected String[] definirTamanhosCabecalho() {
				return new String[]{"240px"};
			}

			@Override
			protected String[] definirTitulosCabecalho() {

				return new String[]{"Nome"};
			}

			@Override
			protected String[] recuperarDadosObjeto(KDominioConhecimento objeto) {
 
				return new String[]{objeto.getNome()};
			}

			@Override
			public String recuperarLabelObjetoSelecionado(
					KDominioConhecimento objeto) {
				return objeto.getNome();
			}
			
			
			
			
		};
		
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(KDominioConhecimento dconhecimento) {		
		dconhecimento.setNome(tbNome.getValue());
		dconhecimento.setDescricao(tbDescricao.getValue());
	}
	
	

	@Override
	protected void preencherDadosTela(KDominioConhecimento dconhecimento) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(dconhecimento.getNome());
		tbDescricao.setValue(dconhecimento.getDescricao());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}
}

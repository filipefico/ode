package ode.processoPadrao.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.GridDados;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.componentes.selecao.NucleoBandbox;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.processoPadrao.Cdp.CompPP;

import org.zkoss.zul.Textbox;

public class FormDadosProcessoPadrao extends FormularioDadosCRUD<CompPP>{
	

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

				
		NucleoBandbox<CompPP> band = new NucleoBandbox<CompPP>(){

			@Override
			protected String[] definirTamanhosCabecalho() {

				return new String[]{"240px"};
			}

			@Override
			protected String[] definirTitulosCabecalho() {

				return new String[]{"Nome"};
			}

			@Override
			protected String[] recuperarDadosObjeto(CompPP objeto) {
 
				return new String[]{objeto.getNome()};
			}
			
			
		};
		
		gridDadosCadastro.adicionarLinha("Pai", band);
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(CompPP compPP) {		
		compPP.setNome(tbNome.getValue());
		compPP.setDescricao(tbDescricao.getValue());
		
	}
	
	

	@Override
	protected void preencherDadosTela(CompPP compPP) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(compPP.getNome());
		tbDescricao.setValue(compPP.getDescricao());
		
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
				
	}
}

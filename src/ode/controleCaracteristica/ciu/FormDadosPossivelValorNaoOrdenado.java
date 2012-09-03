package ode.controleCaracteristica.ciu;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Textbox;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.controleCaracteristica.cdp.PossivelValor;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;

public class FormDadosPossivelValorNaoOrdenado extends FormularioDadosCRUD<PossivelValorNaoOrdenado>{
	
	private static final long serialVersionUID = 1L;


	private GridDados gridDadosCadastro = new GridDados();
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// Tabs
		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		NucleoTab tabPossiveisValores= new NucleoTab();
		tabPossiveisValores.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_POSSIVEIS_VALORES));
		
		// Atribui o nome:
		tbNome.setWidth("285px");
		tbNome.setMaxlength(100);		
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		//Atribui a descrição:
		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);
		
		//Adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
						
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;

	}

	@Override
	protected void preencherDadosTela(PossivelValorNaoOrdenado objeto)
			throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		
	}

	@Override
	protected void preencherDadosObjeto(PossivelValorNaoOrdenado objeto) {
		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		
	}
	
	
}

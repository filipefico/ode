package ode.gerenciaConhecimento.ciu;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Textbox;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.gerenciaConhecimento.cdp.Tema;

public class FormDadosTema extends FormularioDadosCRUD<Tema>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Textbox tbNome = new Textbox();
	Textbox tbDescricao = new Textbox();
	
	@Override
	protected List<NucleoTab> definirTabs() {
		// TODO Auto-generated method stub
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		

		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

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
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);
		
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosTela(Tema objeto)
			throws NucleoRegraNegocioExcecao {
		// TODO Auto-generated method stub
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao()); /*pega os dados preenchidos e coloca na tela*/
	}

	@Override
	protected void preencherDadosObjeto(Tema objeto) {
		// TODO Auto-generated method stub
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue()); /*pega o valor digitado e preenche o objeto*/
	}

}

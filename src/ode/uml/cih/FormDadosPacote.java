package ode.uml.cih;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.uml.cdp.Pacote;

import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;

public class FormDadosPacote extends FormularioDadosCRUD<Pacote>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Combobox coProjeto = new Combobox();

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("100%");
		tbNome.setMaxlength(100);		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		tbDescricao.setWidth("100%");
		tbDescricao.setMaxlength(500);
		tbDescricao.setRows(5);
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);	
		
		coProjeto.setWidth("100%");
		coProjeto.setDisabled(true);
		coProjeto.setValue(NucleoContexto.recuperarProjeto().getNome());
		gridDadosCadastro.adicionarLinha("Projeto", coProjeto);
		
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(Pacote objeto) {		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setProjeto(NucleoContexto.recuperarProjeto());
	}
	
	

	@Override
	protected void preencherDadosTela(Pacote objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		coProjeto.setValue(objeto.getProjeto().getNome());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}
}
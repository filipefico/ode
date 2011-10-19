package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Textbox;

import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

public class FormDadosKTipoEntidadeMensuravel extends
		FormularioDadosCRUD<KTipoEntidadeMensuravel> {
	
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	
	@Override
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
				List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

				// ////////////////////////////
				// Dados Cadastro
				// ////////////////////////////
				NucleoTab tabDadosCadastro = new NucleoTab();

				// Atribui o nome à tab
				tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

				// Atribui o conteúdo à tab
				GridDados gridDadosCadastro = new GridDados();
				tbNome.setWidth("285px");
				tbNome.setMaxlength(100);		
				gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
				
				tbDescricao.setWidth("285px");
				tbDescricao.setMaxlength(500);
				tbDescricao.setHeight("145px");
				tbDescricao.setMultiline(true);
				gridDadosCadastro.adicionarLinha(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);	
				
				listaTabs.add(tabDadosCadastro);
				tabDadosCadastro.setConteudoTab(gridDadosCadastro);

				return listaTabs;
	}

	@Override
	protected void preencherDadosTela(KTipoEntidadeMensuravel objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
	}

	@Override
	protected void preencherDadosObjeto(KTipoEntidadeMensuravel objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}


}

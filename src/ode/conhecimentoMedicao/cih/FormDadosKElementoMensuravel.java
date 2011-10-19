package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.zkoss.zul.Textbox;

import ode.conhecimentoMedicao.cci.CtrlKElementoMensuravelCRUD;
import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKTipoEntidadeMensuravel;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

public class FormDadosKElementoMensuravel extends FormularioDadosCRUD<KElementoMensuravel>{
	
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<KTipoEntidadeMensuravel> ck = new NucleoMultipleListBox<KTipoEntidadeMensuravel>();
	
	@Override
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
				List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

				// ////////////////////////////
				// Dados Cadastro - Nome e Descricao
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
				
				tabDadosCadastro.setConteudoTab(gridDadosCadastro);
				
				// ////////////////////////////
				// Dados Cadastro - Objetivos Estrategicos Relacionados
				// ////////////////////////////
				NucleoTab tabDadosObjetivos = new NucleoTab();
				
				tabDadosObjetivos.setNomeTab("Aba temporaria...");
				
				
				CtrlKElementoMensuravelCRUD ctrl = (CtrlKElementoMensuravelCRUD) this.getControlador();
				AplCadastrarKTipoEntidadeMensuravel apl =  ctrl.getAplKTipoEntidadeMensuravel();
				Collection<KTipoEntidadeMensuravel> tipos = apl.recuperarTodos();
				
				ck.setObjetos(tipos);
				
				tabDadosObjetivos.setConteudoTab(ck);
				////////////////
				
				listaTabs.add(tabDadosCadastro);
				listaTabs.add(tabDadosObjetivos);

				return listaTabs;
	}

	@Override
	protected void preencherDadosTela(KElementoMensuravel objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		ck.setObjetosSelecionados(objeto.getTipoEntidadeMensuravel());
	}

	@Override
	protected void preencherDadosObjeto(KElementoMensuravel objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setTipoEntidadeMensuravel(ck.getObjetosSelecionados());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}

}

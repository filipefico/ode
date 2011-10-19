package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.zkoss.zul.Textbox;

import ode.conhecimentoMedicao.cci.CtrlKMetodoAnaliticoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKProcedimentoAnaliseMedicaoCRUD;
import ode.conhecimentoMedicao.cdp.KMetodoAnalitico;
import ode.conhecimentoMedicao.cdp.KProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMetodoAnalitico;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

public class FormDadosKProcedimentoAnaliseMedicao extends FormularioDadosCRUD<KProcedimentoAnaliseMedicao>{

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<KMetodoAnalitico> ckMetodoAnalitico = new NucleoMultipleListBox<KMetodoAnalitico>();
	
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
				tbNome.setWidth("385px");
				tbNome.setMaxlength(300);	
				tbNome.setHeight("35px");
				tbNome.setMultiline(true);
				gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
				
				tbDescricao.setWidth("385px");
				tbDescricao.setMaxlength(400);
				tbDescricao.setHeight("65px");
				tbDescricao.setMultiline(true);
				gridDadosCadastro.adicionarLinha(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);	
				
				
				tabDadosCadastro.setConteudoTab(gridDadosCadastro);
				
				//////////////////////////////////
				////KMetodoAnalitico
				/////////////////////////////////
				NucleoTab tabKMetodoAnalitico = new NucleoTab();
				
				tabKMetodoAnalitico.setNomeTab("Metodos Analiticos");
				
				CtrlKProcedimentoAnaliseMedicaoCRUD ctrl = (CtrlKProcedimentoAnaliseMedicaoCRUD) this.getControlador();
				AplCadastrarKMetodoAnalitico aplKMetodoAnalitico = ctrl.getAplKMetodoAnalitico();
				Collection<KMetodoAnalitico> metodosAnaliticos = aplKMetodoAnalitico.recuperarTodos();
				
				ckMetodoAnalitico.setObjetos(metodosAnaliticos);
				
				tabKMetodoAnalitico.setConteudoTab(ckMetodoAnalitico);
				
				listaTabs.add(tabDadosCadastro);
				listaTabs.add(tabKMetodoAnalitico);
				
				return listaTabs;
	}

	@Override
	protected void preencherDadosTela(KProcedimentoAnaliseMedicao objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		ckMetodoAnalitico.setObjetosSelecionados(objeto.getMetodosAnaliticos());
	}

	@Override
	protected void preencherDadosObjeto(KProcedimentoAnaliseMedicao objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setMetodosAnaliticos(ckMetodoAnalitico.getObjetosSelecionados());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}

}

package ode.problema.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import ode.problema.cdp.KSolucao;
import ode.problema.cgt.AplCadastrarKCausa;
import ode.problema.cgt.AplCadastrarKProblema;
import ode.problema.cgt.AplCadastrarKSolucao;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;





public class FormDadosKSolucao extends FormularioDadosCRUD<KSolucao> {


	private static final long serialVersionUID = 4935711238485669792L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<KCausa> ckcausa = new NucleoMultipleListBox<KCausa>();
	

	
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		NucleoTab tabDadosCadastro = new NucleoTab();
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
		

		NucleoTab tabKCausa = new NucleoTab();
		
		tabKCausa.setNomeTab("Causas");
		
		CtrlKSolucaoCRUD ctrl = (CtrlKSolucaoCRUD) this.getControlador();
		AplCadastrarKCausa aplKCausa = ctrl.getAplCadastrarKCausa();
		Collection<KCausa> kcausa = aplKCausa.recuperarTodos();
		
		ckcausa.setObjetos(kcausa);
		
		tabKCausa.setConteudoTab(ckcausa);
		
		listaTabs.add(tabDadosCadastro);
		listaTabs.add(tabKCausa);
		return listaTabs;
		
	}	


	@Override
	protected void preencherDadosTela(KSolucao objeto) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		ckcausa.setObjetosSelecionados(objeto.getKCausa());
	}
	


	@Override
	protected void preencherDadosObjeto(KSolucao objeto) {		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setKCausa(ckcausa.getObjetosSelecionados());
	}


	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}	



	}



	
	
	

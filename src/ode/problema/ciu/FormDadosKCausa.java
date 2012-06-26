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
import ode.conhecimentoMedicao.cci.CtrlKProcedimentoAnaliseMedicaoCRUD;
import ode.conhecimentoMedicao.cdp.KMetodoAnalitico;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMetodoAnalitico;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import ode.problema.cgt.AplCadastrarKProblema;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;





public class FormDadosKCausa extends FormularioDadosCRUD<KCausa> {
	
	private static final long serialVersionUID = -3908139735326584430L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<KProblema> ckproblema = new NucleoMultipleListBox<KProblema>();
	private KProblema problemaSelecionado;
	private List<KProblema> listaKProblema;

 
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
		

		NucleoTab tabKProblema = new NucleoTab();
		
		tabKProblema.setNomeTab("Problemas");
		
		CtrlKCausaCRUD ctrl = (CtrlKCausaCRUD) this.getControlador();
		AplCadastrarKProblema aplKProblema = ctrl.getAplCadastrarKProblema();
		Collection<KProblema> kproblema = aplKProblema.recuperarTodos();
		
		ckproblema.setObjetos(kproblema);
		
		tabKProblema.setConteudoTab(ckproblema);
		
		listaTabs.add(tabDadosCadastro);
		listaTabs.add(tabKProblema);
		return listaTabs;
		
	}	


	@Override
	protected void preencherDadosTela(KCausa objeto) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		ckproblema.setObjetosSelecionados(objeto.getKproblema());
	}
	


	@Override
	protected void preencherDadosObjeto(KCausa objeto) {		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setKproblema(ckproblema.getObjetosSelecionados());
	}


	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}	



	}



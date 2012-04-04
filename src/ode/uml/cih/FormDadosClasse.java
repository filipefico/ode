package ode.uml.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.uml.cci.CtrlCRUDPacote;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Textbox;

public class FormDadosClasse extends FormularioDadosCRUD<Classe>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Combobox comboPacote = new Combobox();
	
	private CtrlCRUDPacote ctrlPacote = (CtrlCRUDPacote) SpringUtil.getApplicationContext().getBean(CtrlCRUDPacote.class);
	
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
		
		comboPacote.setWidth("100%");
		comboPacote.setReadonly(true);
		preencherComboPacotes();
		gridDadosCadastro.adicionarLinha("Pacote", comboPacote);
		
		
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;
	}
	
	private void preencherComboPacotes(){
		comboPacote.getChildren().clear();
		
		Collection<Pacote> pacotes = ctrlPacote.obterPorProjeto(NucleoContexto.recuperarProjeto());
		
		for (Pacote pacote : pacotes) {
			Comboitem item = new Comboitem();
			
			item.setLabel(pacote.getNome());
			item.setValue(pacote);
			
			item.setParent(comboPacote);
		}
		if (pacotes.size() > 0)
			comboPacote.setSelectedIndex(0);
	}

	@Override
	protected void preencherDadosObjeto(Classe objeto) {		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setPacote((Pacote) comboPacote.getSelectedItem().getValue());
	}
	
	

	@Override
	protected void preencherDadosTela(Classe objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		comboPacote.setValue(objeto.getPacote().getNome());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
		comboPacote.setConstraint("no empty");
	}
}
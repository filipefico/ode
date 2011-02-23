package ode.conhecimento.requisito.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.GridDados;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import ode.conhecimento.requisito.Cgd.KTipoRequisitoDAO;
import ode.conhecimento.requisito.Cdp.KTipoRequisito;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;

public class FormDadosKTipoRequisito extends FormularioDadosCRUD<KTipoRequisito>{
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	
	private Radiogroup rgFuncional = new Radiogroup();
	private Radio cbFuncionalNao =  new Radio("Não-funcional");
	private Radio cbFuncionalSim =  new Radio("Funcional");
	private Combobox coSuper = new Combobox();

	public KTipoRequisito buscaRequisitoPorNome (String nome, Radio cbFuncionalSim){
		KTipoRequisitoDAO Req = (KTipoRequisitoDAO) SpringUtil.getBean("kTipoRequisitoDao");
		Collection<KTipoRequisito> listaRequisitos = Req.recuperarTodos();
		for (KTipoRequisito K : listaRequisitos) {
			if (K.getNome().equals(nome) && K.getEhFuncional() == cbFuncionalSim.isChecked()){
				return K;
			}	
		}
		return null;
	}
	
	public static void carregaRequisitosCombobox (Combobox coSuper, Radio cbFuncionalSim){
		coSuper.getChildren().clear();
		KTipoRequisitoDAO Req = (KTipoRequisitoDAO) SpringUtil.getBean("kTipoRequisitoDao");
		Collection<KTipoRequisito> listaRequisitos = Req.recuperarTodos();
		for (KTipoRequisito K : listaRequisitos) {
			if (cbFuncionalSim.isChecked() == K.getEhFuncional()){
				coSuper.appendItem(K.getNome());
			}
			
		}
	}

	@Override
	protected List definirTabs() {
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
		
		gridDadosCadastro.adicionarLinha("Categoria", rgFuncional);
		cbFuncionalSim.setParent(rgFuncional);
		cbFuncionalNao.setParent(rgFuncional);
		cbFuncionalNao.setChecked(true);
		rgFuncional.addEventListener("onCheck", new EListenerFuncional());
		
		carregaRequisitosCombobox(coSuper, cbFuncionalSim);
		coSuper.setAutocomplete(true);
		gridDadosCadastro.adicionarLinha("Supertipo", coSuper);
		
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		return listaTabs;
	}
	
	private class EListenerFuncional implements EventListener{
		public void onEvent(Event event) {
			carregaRequisitosCombobox(coSuper, cbFuncionalSim);
			
		}	
			@SuppressWarnings("unused")
			public boolean isAsap() {
				return true;
			}
	}

	@Override
	protected void preencherDadosObjeto(KTipoRequisito objeto) {		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setEhFuncional(cbFuncionalSim.isChecked());
		objeto.setSuperKTipoRequisito(buscaRequisitoPorNome(coSuper.getValue(), cbFuncionalSim));
	}
	
	

	@Override
	protected void preencherDadosTela(KTipoRequisito objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		cbFuncionalSim.setChecked(objeto.getEhFuncional());
		carregaRequisitosCombobox(coSuper, cbFuncionalSim);
		if (objeto.getSuperKTipoRequisito() != null){
			coSuper.setValue(objeto.getSuperKTipoRequisito().getNome());
		}
		
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}
}
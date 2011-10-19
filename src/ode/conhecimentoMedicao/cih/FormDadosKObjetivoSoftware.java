package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zkoss.web.servlet.dsp.action.Page;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import ode.conhecimentoMedicao.cci.CtrlKObjetivoEstrategicoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKObjetivoSoftwareCRUD;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode.conhecimentoMedicao.cgd.KObjetivoEstrategicoDAOImpl;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;


@Component
public class FormDadosKObjetivoSoftware extends FormularioDadosCRUD<KObjetivoSoftware>{

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<KObjetivoEstrategico> ck = new NucleoMultipleListBox<KObjetivoEstrategico>();
	private Groupbox gb = new Groupbox();
	private GridDados gridDadosCadastro;
	
	@Override
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
				List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

				NucleoTab tabDadosCadastro = new NucleoTab();
				
				// ////////////////////////////
				// Dados Cadastro - Objetivos Estrategicos Relacionados
				// ////////////////////////////
				
				CtrlKObjetivoSoftwareCRUD ctrl = (CtrlKObjetivoSoftwareCRUD) this.getControlador();
				AplCadastrarKObjetivoEstrategico apl =  ctrl.getAplKObjetivoEstrategico();
				Collection<KObjetivoEstrategico> estrategicos = apl.recuperarTodos();
								
				ck.setObjetos(estrategicos);
				gb.appendChild(new Caption(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_OBJETIVOS_ESTRATEGICOS)));
				gb.appendChild(ck);
				////////////////
				
				// ////////////////////////////
				// Dados Cadastro - Nome e Descricao
				// ////////////////////////////

				// Atribui o nome à tab

				// Atribui o conteúdo à tab
				gridDadosCadastro = new GridDados();
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
			

				return listaTabs;
	}
	
	@Override
	protected void montarTabs() {
		super.montarTabs();
		this.appendChild(gb);
		this.appendChild(gridDadosCadastro);
	};

	@Override
	protected void preencherDadosTela(KObjetivoSoftware objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		ck.setObjetosSelecionados(objeto.getObjetivosEstrategicos());
	}

	@Override
	protected void preencherDadosObjeto(KObjetivoSoftware objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setObjetivosEstrategicos(ck.getObjetosSelecionados());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}

}

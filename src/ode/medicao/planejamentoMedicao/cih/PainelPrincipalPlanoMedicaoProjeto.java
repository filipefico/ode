package ode.medicao.planejamentoMedicao.cih;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.controleProjeto.cdp.Projeto;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoProjeto;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoProjeto;

public class PainelPrincipalPlanoMedicaoProjeto extends PainelPrincipalPlanoMedicao{

	NucleoCombobox<PlanoMedicaoOrganizacao> cbPlanos = new NucleoCombobox<PlanoMedicaoOrganizacao>();
	NucleoCombobox<Projeto> cbProjetos = new NucleoCombobox<Projeto>();
	
	Set<PlanoMedicaoOrganizacao> gambiarra = new HashSet<PlanoMedicaoOrganizacao>();
	
	private class EventoCopia implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			dbData.setValue(new Date());
			tbDescricao.setText("Plano de Medição versão "+cbPlanos.getObjetoSelecionado().getVersao());
			ibVersao.setText(Float.toString(cbPlanos.getObjetoSelecionado().getVersao()));
			cbResponsavel.selecionarPrimeiroElemento();
		}
		
	}
	
	@Override
	protected List<NucleoTab> configurarAbas(List<NucleoTab> listaabas) {
List<NucleoTab> abas = listaabas;
		
		////////////////////////////
		//Controle
		///////////////////////////
		
		//GAMBIARRA
		PlanoMedicaoOrganizacao pmo1 = new PlanoMedicaoOrganizacao();
		pmo1.setVersao((float)1.0);
		gambiarra.add(pmo1);
		//

		NucleoTab tabControle = new NucleoTab();
		
		
		GridDados gridControle =  new GridDados();
		tabControle.setNomeTab("Controle");
		
		cbPlanos.setObjetos(gambiarra);
		cbPlanos.selecionarPrimeiroElemento();
		cbPlanos.setWidth("100%");
		gridControle.adicionarLinha("Plano Base",cbPlanos);
		
		cbProjetos.setObjetos(((CtrlPlanoMedicaoProjeto)ctrl).getProjetos());
		cbProjetos.selecionarPrimeiroElemento();
		
		gridControle.adicionarLinhaObrigatoria("Projeto", cbProjetos);
		
		gridControle.adicionarLinhaObrigatoria("Versão", ibVersao);
		
		cbResponsavel.setObjetos(((CtrlPlanoMedicao)this.getControlador()).getAplRecursoHumano().recuperarTodos());
		cbResponsavel.selecionarPrimeiroElemento();
		gridControle.adicionarLinhaObrigatoria("Responsável", cbResponsavel);
		
		gridControle.adicionarLinhaObrigatoria("Data", dbData);
		
		gridControle.adicionarLinha("Descrição", tbDescricao);
		
		Button reset = new Button("Criar plano do Projeto idêntico ao Plano da Organização");
		
		reset.addEventListener("onClick", new EventoCopia());
		
		gridControle.adicionarLinha("", reset);
		
		tabControle.setConteudoTab(gridControle);
		
		abas.add(tabControle);
		
		///////////////////////////
		//Objetivos e Necessidade de Informacao
		//////////////////////////
		
		NucleoTab tabObjetivos = new NucleoTab();
		
		tabObjetivos.setNomeTab("Objetivos e Necessidades de Informação");
		
		Vbox caixa = new Vbox();
		
		compObj = new ComponenteObjetivosTree();
		
		compObj.iniciar(((CtrlPlanoMedicao)ctrl).getObjetivosEstrategicos());
		compObj.setEventoMedicao(new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				//medPlan.atualizar((HashSet<NecessidadeInformacao>)arg0.getData());
			}
		});
		
		tabObjetivos.setConteudoTab(compObj);
		
		
		abas.add(tabObjetivos);
		
		///////////////////////////////////
		///Medidas
		//////////////////////////////////
		
		NucleoTab tabMedidas = new NucleoTab();
		
		tabMedidas.setNomeTab("Medidas");
		
		Vbox boxMedidas = new Vbox();
		
		medPlan.setParent(boxMedidas);
		
		///
		tabMedidas.setConteudoTab(boxMedidas);
		
		abas.add(tabMedidas);
		
		/////////////////////////////////
		
		
		
		return abas;
	}
	
	@Override
	protected void novo() {
		dbData.setValue(new Date());
		tbDescricao.setText("");
		ibVersao.setText("");
		cbResponsavel.selecionarPrimeiroElemento();
		//compObj.decelecionaTudo();
		((CtrlPlanoMedicaoProjeto)getControlador()).setPlanoMedicao(((CtrlPlanoMedicaoProjeto)getControlador()).novoPlanoMedicao());
	}

	@Override
	protected void salvar() {
		((CtrlPlanoMedicaoProjeto)getControlador()).salvar();
	}

	@Override
	protected void abrir() {
		((CtrlPlanoMedicaoProjeto)getControlador()).abrir();
	}
	
	@Override
	protected void deletar() {
		
	}
	
	public void preencherDados(PlanoMedicao objeto) {
		dbData.setValue(objeto.getData());
		tbDescricao.setText(objeto.getDescricao());
		ibVersao.setText(Float.toString(objeto.getVersao()));
		cbResponsavel.setObjetoSelecionado(objeto.getResponsavel());
		compObj.populaArvore(objeto.getObjsEstrategico(), objeto.getObjsSoftware(), objeto.getObjsMedicao(), objeto.getNecessidades(), objeto.getProcessos());
	}

	public void preencherObjetos(PlanoMedicao objeto) {
		objeto.setData(dbData.getValue());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setVersao(Float.parseFloat(ibVersao.getText()));
		objeto.setResponsavel(cbResponsavel.getObjetoSelecionado());
		objeto.setObjsEstrategico(compObj.getEstrategicosSelecionados());
		objeto.setObjsSoftware(new HashSet(compObj.getSoftwareSelecionados()));
		objeto.setObjsMedicao(new HashSet(compObj.getMedicaoSelecionados()));
		objeto.setNecessidades(new HashSet(compObj.getNecessidadesSelecionadas()));
		objeto.setProcessos(new HashSet(compObj.getProcessosSelecionados()));
		objeto.adicionaMpm(new MedidaPlanoMedicao());
	}

	
}

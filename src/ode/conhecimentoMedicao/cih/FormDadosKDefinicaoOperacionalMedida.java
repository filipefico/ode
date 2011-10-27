package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.SpringLayout.Constraints;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode.conhecimentoMedicao.cci.CtrlKDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cci.CtrlKProcedimentoMedicaoCRUD;
import ode.conhecimentoMedicao.cdp.AplicacaoDefinicaoOperacional;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KPeriodicidade;
import ode.conhecimentoMedicao.cdp.KProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cdp.KProcedimentoMedicao;

public class FormDadosKDefinicaoOperacionalMedida extends FormularioDadosCRUD<KDefinicaoOperacionalMedida>{

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoListbox<AplicacaoDefinicaoOperacional> lbAplicacao = new NucleoListbox<AplicacaoDefinicaoOperacional>();
	private Datebox dbData = new Datebox(new Date());
	private Textbox tbIntervalo = new Textbox();
	private Textbox tbFormula = new Textbox();
	private NucleoMultipleListBox<KObjetivoMedicao> lbObjetivos = new NucleoMultipleListBox<KObjetivoMedicao>();
	
	private NucleoCombobox<KProcedimentoMedicao> cbProcMed = new NucleoCombobox<KProcedimentoMedicao>();
	private NucleoCombobox<KRecursoHumano> cbRespMed = new NucleoCombobox<KRecursoHumano>();
	private NucleoCombobox<KAtividade> cbMomentMed = new NucleoCombobox<KAtividade>();
	private NucleoCombobox<KPeriodicidade> cbPeriodMed = new NucleoCombobox<KPeriodicidade>();
	
	private NucleoCombobox<KProcedimentoAnaliseMedicao> cbProcAnali = new NucleoCombobox<KProcedimentoAnaliseMedicao>();
	private NucleoCombobox<KRecursoHumano> cbRespAnali = new NucleoCombobox<KRecursoHumano>();
	private NucleoCombobox<KAtividade> cbMomentAnali = new NucleoCombobox<KAtividade>();
	private NucleoCombobox<KPeriodicidade> cbPeriodAnali = new NucleoCombobox<KPeriodicidade>();

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> tabs = new ArrayList<NucleoTab>();
		
		NucleoTab tabDadosCadastro = new NucleoTab();

		/////////////////////////////////
		///Dados Cadastro
		/////////////////////////////////
		
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("385px");
		tbNome.setMaxlength(300);
		tbNome.setHeight("35px");
		tbNome.setMultiline(true);
		gridDadosCadastro
				.adicionarLinhaObrigatoria(
						NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
						tbNome);

		tbDescricao.setWidth("385px");
		tbDescricao.setMaxlength(400);
		tbDescricao.setHeight("65px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),
				tbDescricao);
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		
		lbAplicacao.setObjetos(AplicacaoDefinicaoOperacional.values());
		lbAplicacao.selecionarPrimeiroElemento();
		lbAplicacao.setCheckmark(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_APLICACAO_DEFINICAO_OPERACIONAL), lbAplicacao);
		
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DATA),dbData);
		
		tbIntervalo.setWidth("385px");
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_INTERVALO), tbIntervalo);
		
		tbFormula.setWidth("385px");
		gridDadosCadastro.adicionarLinha("Formula" , tbFormula);
		
		tabs.add(tabDadosCadastro);
		//////////////////////////////////////////
		///Objetivos
		//////////////////////////////////////////
		/*NucleoTab objetivosTab = new NucleoTab();
		
		objetivosTab.setNomeTab("Objetivos");
		
		Collection<KObjetivoMedicao> objs = this.getControlador().getAplMedicao;
		
		lbObjetivos.setObjetos(objs);
		
		tabs.add(objetivosTab);*/
		//////////////////////////////////////////
		Collection<KRecursoHumano> humanos = ((CtrlKDefinicaoOperacionalMedida)this.getControlador()).getAplKRecursoHumano().recuperarTodos();
		Collection<KAtividade> atividades = ((CtrlKDefinicaoOperacionalMedida)this.getControlador()).getAplKAtividade().recuperarTodos();
		Collection<KPeriodicidade> periodicidades = ((CtrlKDefinicaoOperacionalMedida)this.getControlador()).getAplKPeriodicidade().recuperarTodos();
		
		//////////////////////////////////////////
		////Execucao Medicao
		/////////////////////////////////////////
		NucleoTab execucaoTab = new NucleoTab();
		
		execucaoTab.setNomeTab("Execu��o da Medi��o");
		GridDados execucaoGridDados = new GridDados();
		
		cbProcMed.setObjetos(((CtrlKDefinicaoOperacionalMedida)this.getControlador()).getAplKProcedimentoMedicao().recuperarTodos());
		cbProcMed.selecionarPrimeiroElemento();
		cbProcMed.adicionaCampoNulo();
		execucaoGridDados.adicionarLinha("Procedimento", cbProcMed);
		cbRespMed.setObjetos(humanos);
		cbRespMed.selecionarPrimeiroElemento();
		cbRespMed.adicionaCampoNulo();
		execucaoGridDados.adicionarLinha("Respons�vel", cbRespMed);
		cbMomentMed.setObjetos(atividades);
		cbMomentMed.selecionarPrimeiroElemento();
		cbMomentMed.adicionaCampoNulo();
		execucaoGridDados.adicionarLinha("Momento", cbMomentMed);
		cbPeriodMed.setObjetos(periodicidades);
		cbPeriodMed.selecionarPrimeiroElemento();
		cbPeriodMed.adicionaCampoNulo();
		execucaoGridDados.adicionarLinha("Per�odo", cbPeriodMed);
		
		execucaoTab.setConteudoTab(execucaoGridDados);
		
		tabs.add(execucaoTab);
		////////////////////////////////////////
		
		///////////////////////////////////////
		////Analise Medicao
		///////////////////////////////////////
		NucleoTab analiseTab = new NucleoTab();
		
		analiseTab.setNomeTab("An�lise da Medi��o");
		GridDados analiseGridDados = new GridDados();
		
		cbProcAnali.setObjetos(((CtrlKDefinicaoOperacionalMedida)this.getControlador()).getAplKProcedimentoAnaliseMedicao().recuperarTodos());
		cbProcAnali.selecionarPrimeiroElemento();
		analiseGridDados.adicionarLinha("Procedimento", cbProcAnali);
		cbRespAnali.setObjetos(humanos);
		cbRespAnali.selecionarPrimeiroElemento();
		analiseGridDados.adicionarLinha("Respons�vel", cbRespAnali);
		cbMomentAnali.setObjetos(atividades);
		cbMomentAnali.selecionarPrimeiroElemento();
		analiseGridDados.adicionarLinha("Momento", cbMomentAnali);
		cbPeriodAnali.setObjetos(periodicidades);
		cbPeriodAnali.selecionarPrimeiroElemento();
		analiseGridDados.adicionarLinha("Per�odo", cbPeriodAnali);
		
		analiseTab.setConteudoTab(analiseGridDados);
		
		tabs.add(analiseTab);
		///////////////////////////////////////
		
		return tabs;
	}

	@Override
	protected void preencherDadosTela(KDefinicaoOperacionalMedida objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		lbAplicacao.setObjetoSelecionado(objeto.getAplicacao());
		dbData.setValue(objeto.getData());
		tbIntervalo.setText(objeto.getIntervalo());
		tbFormula.setText(objeto.getFormulaCalculoMedida());
		
		cbProcMed.setObjetoSelecionado(objeto.getProcedimentoMedicao());
		cbRespMed.setObjetoSelecionado(objeto.getResponsavelMedicao());
		cbMomentMed.setObjetoSelecionado(objeto.getMomentoMedicao());
		cbPeriodMed.setObjetoSelecionado(objeto.getPeriodicidadeMedicao());
		
		cbProcAnali.setObjetoSelecionado(objeto.getProcedimentoAnaliseMedicao());
		cbRespAnali.setObjetoSelecionado(objeto.getResponsavelAnaliseMedicao());
		cbMomentAnali.setObjetoSelecionado(objeto.getMomentoAnaliseMedicao());
		cbPeriodAnali.setObjetoSelecionado(objeto.getPeriodicidadeAnaliseMedicao());
	}

	@Override
	protected void preencherDadosObjeto(KDefinicaoOperacionalMedida objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setAplicacao(lbAplicacao.getObjetoSelecionado());
		objeto.setData(dbData.getValue());
		objeto.setIntervalo(tbIntervalo.getText());
		objeto.setFormulaCalculoMedida(tbFormula.getText());
		
		objeto.setProcedimentoMedicao(cbProcMed.getObjetoSelecionado());
		objeto.setResponsavelMedicao(cbRespMed.getObjetoSelecionado());
		objeto.setMomentoMedicao(cbMomentMed.getObjetoSelecionado());
		objeto.setPeriodicidadeMedicao(cbPeriodMed.getObjetoSelecionado());
		
		objeto.setProcedimentoAnaliseMedicao(cbProcAnali.getObjetoSelecionado());
		objeto.setResponsavelAnaliseMedicao(cbRespAnali.getObjetoSelecionado());
		objeto.setMomentoAnaliseMedicao(cbMomentAnali.getObjetoSelecionado());
		objeto.setPeriodicidadeAnaliseMedicao(cbPeriodAnali.getObjetoSelecionado());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbDescricao.setConstraint("no empty");
		tbIntervalo.setConstraint("no empty");
		tbFormula.setConstraint("no empty");
	}

}
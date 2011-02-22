package ode.conhecimento.processo.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.principal.JanelaSimples;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KProcedimento;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.Cgt.AplCadastrarKDominioAplicacao;
import ode.conhecimento.processo.Cgt.AplCadastrarKProcedimento;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHardware;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHumano;
import ode.conhecimento.processo.Cih.FormDadosKAtividade;
import ode.conhecimento.processo.Cih.FormDadosKDominioAplicacao;
import ode.conhecimento.processo.Cih.FormDadosKProcedimento;
import ode.conhecimento.processo.Cih.FormDadosKRecursoHardware;
import ode.conhecimento.processo.Cih.FormDadosKRecursoHumano;
import ode.conhecimento.processo.Cih.PainelCrudKAtividade;
import ode.conhecimento.processo.Cih.PainelCrudKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelCrudKProcedimento;
import ode.conhecimento.processo.Cih.PainelCrudKRecursoHardware;
import ode.conhecimento.processo.Cih.PainelSelecaoKDominioAplicacao;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlKProcedimentoCRUD extends CtrlCRUD<KProcedimento> {
@Override
	public void iniciar() {
		super.iniciar();
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKProcedimento) SpringUtil
				.getBean("aplCadastrarKProcedimento");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKProcedimento();
		
	}


	@Override
	public KProcedimento factoryObjetoDados() {
		return new KProcedimento();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKProcedimento();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Procedimento";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Procedimento";
	}
	



}

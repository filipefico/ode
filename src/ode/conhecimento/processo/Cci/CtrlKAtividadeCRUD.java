package ode.conhecimento.processo.Cci;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KProcedimento;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cdp.KRecurso;
import ode.conhecimento.processo.Cgd.KArtefatoDAO;
import ode.conhecimento.processo.Cgd.KAtividadeDAO;
import ode.conhecimento.processo.Cgd.KProcedimentoDAO;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.conhecimento.processo.Cgd.KRecursoDAO;
import ode.conhecimento.processo.Cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.Cgt.AplCadastrarKDominioAplicacao;
import ode.conhecimento.processo.Cih.FormDadosKAtividade;
import ode.conhecimento.processo.Cih.FormDadosKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelCrudKAtividade;
import ode.conhecimento.processo.Cih.PainelCrudKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelSelecaoKDominioAplicacao;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.JanelaSimples;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

@Controller
public class CtrlKAtividadeCRUD extends CtrlCRUD<KAtividade> {


	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Autowired
	AplCadastrarKAtividade aplCadastrarKAtividade;

	public AplCadastrarKAtividade getAplCadastrarKAtividade() {
		return aplCadastrarKAtividade;
	}

	public void setAplCadastrarKAtividade(
			AplCadastrarKAtividade aplCadastrarKAtividade) {
		this.aplCadastrarKAtividade = aplCadastrarKAtividade;
	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplBase definirNucleoAplCadastroBase() {
		return aplCadastrarKAtividade;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKAtividade();

	}

	@Override
	public KAtividade factoryObjetoDados() {
		return new KAtividade();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKAtividade();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Atividade";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Atividades";
	}
	
	@Autowired
	KProcessoDAO kprocessoDAO;
	public Collection<KProcesso> getAllKProcesso(){
		return kprocessoDAO.recuperarTodos();
	}
	
	@Autowired
	KRecursoDAO recursoDAO;
	public Collection<KRecurso> getAllKRecurso() {
		return recursoDAO.recuperarTodos();
	}

	@Autowired 
	KArtefatoDAO produtoInsumoDAO;
	public Collection<KArtefato> getAllKArtefato() {
		return produtoInsumoDAO.recuperarTodos();
	}

	@Autowired
	KProcedimentoDAO procedimentoDAO;
	public Collection<KProcedimento> getAllKProcedimento() {
		return procedimentoDAO.recuperarTodos();
	}
	
	@Autowired
	KAtividadeDAO atividadeDAO;
	public Collection<KAtividade> getAllKAtividade() {
		return atividadeDAO.recuperarTodos();
	}
}

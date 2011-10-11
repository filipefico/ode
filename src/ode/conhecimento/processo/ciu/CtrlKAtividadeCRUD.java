package ode.conhecimento.processo.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcedimento;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecurso;
import ode.conhecimento.processo.cgd.KArtefatoDAO;
import ode.conhecimento.processo.cgd.KAtividadeDAO;
import ode.conhecimento.processo.cgd.KProcedimentoDAO;
import ode.conhecimento.processo.cgd.KProcessoDAO;
import ode.conhecimento.processo.cgd.KRecursoDAO;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public AplCRUD<KAtividade> definirAplCRUD() {
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

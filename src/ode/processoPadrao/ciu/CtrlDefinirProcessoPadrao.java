package ode.processoPadrao.ciu;

import java.util.Collection;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgd.KAtividadeDAO;
import ode.conhecimento.processo.cgd.KProcessoDAO;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cgd.CompPPDAO;
import ode.processoPadrao.cgt.AplDefinirProcessoPadrao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlDefinirProcessoPadrao extends CtrlBase {

	private static final long serialVersionUID = -2967662654799321521L;

	private CompPP compPPSelecionado;

	@Override
	public void iniciar() {
		mostrarJanelaPrincipal();
	}

	JanDefinirProcessoPadrao janDefinirProcessoPadrao;

	private void mostrarJanelaPrincipal() {
		// essa janela faz chamadas para abrir as outras janelas.
		janDefinirProcessoPadrao = new JanDefinirProcessoPadrao(this,
				factoryJanelaSimples());
	}

	JanDefinirCompPP janDefinirCompPP;

	public void abrirJanDefinirCompPP() {
		janDefinirCompPP = new JanDefinirCompPP(this, factoryJanelaSimples());
	}

	@Autowired
	KProcessoDAO kprocessoDAO;

	public Collection<KProcesso> getAllKProcesso() {
		return kprocessoDAO.recuperarTodos();
	}

	@Autowired
	KAtividadeDAO katividadeDAO;

	public Collection<KAtividade> getAllKAtividade() {
		return katividadeDAO.recuperarTodos();
	}

	@Autowired
	private AplDefinirProcessoPadrao aplDefinirProcessoPadrao;

	public void salvarCompPP(CompPP compPP) {
		aplDefinirProcessoPadrao.salvarCompPP(compPP);
	}	

	public void salvarCompPP(String nome, String descricao, String objetivo,
			String tipo, Object objTipo) {

		if (tipo.compareTo("Processo Complexo") == 0) {
			aplDefinirProcessoPadrao.salvarProcessoComplexo(nome, descricao,
					objetivo);
		} else if (tipo.compareTo("Processo Simples") == 0) {
			aplDefinirProcessoPadrao.salvarProcessoSimples(nome, descricao,
					objetivo, objTipo);
		} else if (tipo.compareTo("Macroatividade") == 0) {// macroatividade
			aplDefinirProcessoPadrao.salvarMacroatividade(nome, descricao,
					objetivo, objTipo);
		} else {
			new RuntimeException(
					"A string de comparação do tipo esta incorreta: " + tipo);
		}
	}

	@Autowired
	CompPPDAO compPPDAO;

	public Collection<CompPP> getAllCompPP() {
		return compPPDAO.recuperarTodos();
	}

	private JanEstabelecerRequisitosCompPP janEstabelecerRequisitosCompPP;

	public void abrrrJanEstabelecerRequisitos() {
		janEstabelecerRequisitosCompPP = new JanEstabelecerRequisitosCompPP(
				this, factoryJanelaSimples());
	}

	private JanSelecionaProcessoPadrao janSelecionaProcessoPadrao;

	public void abrirJanSelecionaProcessoPadrao() {
		janSelecionaProcessoPadrao = new JanSelecionaProcessoPadrao(this,
				factoryJanelaSimples());
	}

	public void setCompPPSelecionado(CompPP selecionado) {
		compPPSelecionado = selecionado;
		janDefinirProcessoPadrao.setCompPPSelecionado();

	}

	public CompPP getcompPPSelecionado() {
		return compPPSelecionado;
	}

	private JanDefinirInterfaceCompPP janDefinirInterfaceCompPP;

	public void abrirJanDefinirInterfaceCompPP() {
		janDefinirInterfaceCompPP = new JanDefinirInterfaceCompPP(this,
				factoryJanelaSimples());
	}

}

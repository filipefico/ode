package ode.processoPadrao.Cci;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Textbox;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgd.KAtividadeDAO;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.controleUsuario.cgt.AplCadastrarUsuario;
import ode.nucleo.cci.CtrlBase;
import ode.nucleo.crud.cih.JanelaSimples;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cgd.CompPPDAO;
import ode.processoPadrao.Cgt.AplDefinirProcessoPadrao;
import ode.processoPadrao.Cih.JanDefinirCompPP;
import ode.processoPadrao.Cih.JanDefinirProcessoPadrao;

@Controller
public class CtrlDefinirProcessoPadrao extends CtrlBase {

	private static final long serialVersionUID = -2967662654799321521L;

	@Override
	public void iniciar() {
		mostrarJanelaPrincipal();
	}

	JanDefinirProcessoPadrao janDefinirProcessoPadrao;

	private void mostrarJanelaPrincipal() {
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
	
	public void salvarCompPP(String nome, String descricao, String objetivo,
			String tipo, Object objTipo) {
		
		if (tipo.compareTo("Processo Complexo") == 0) {
			aplDefinirProcessoPadrao.salvarProcessoComplexo(nome, descricao, objetivo);
		} else if (tipo.compareTo("Processo Simples") == 0) {
			aplDefinirProcessoPadrao.salvarProcessoSimples(nome, descricao, objetivo, objTipo);
		} else if (tipo.compareTo("Macroatividade") == 0){// macroatividade
			aplDefinirProcessoPadrao.salvarMacroatividade(nome, descricao, objetivo, objTipo);
		}else{
			new RuntimeException("A string de comparação do tipo esta incorreta: "+tipo);
		}
	}

	@Autowired
	CompPPDAO compPPDAO;
	public Collection<CompPP> getAllCompPP() {
		return  compPPDAO.recuperarTodos();
	}

}

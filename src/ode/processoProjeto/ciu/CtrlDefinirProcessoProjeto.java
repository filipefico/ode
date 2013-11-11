package ode.processoProjeto.ciu;

import java.util.Collection;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.ciu.JanEditarPropriedadesBasicasCompPP;
import ode.processoPadrao.ciu.JanSelecionaProcessoComplexo;
import ode.processoProjeto.cdp.CompPPr;
import ode.processoProjeto.cgt.AplDefinirProcessoProjeto;
import ode.processoProjeto.ciu.JanPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("CtrlDefinirProcessoProjeto")
public class CtrlDefinirProcessoProjeto extends CtrlBase{

	private static final long serialVersionUID = 6310959510716281765L;
	
	private CompPPr compPPrSelecionado;
	
	@Autowired
	private AplDefinirProcessoProjeto aplDefinirProcessoProjeto;

	public AplDefinirProcessoProjeto getAplDefinirProcessoProjeto() {
		return aplDefinirProcessoProjeto;
	}

	public void setAplDefinirProcessoProjeto (AplDefinirProcessoProjeto aplDefinirProcessoProjeto) {
		this.aplDefinirProcessoProjeto = aplDefinirProcessoProjeto;
	}

	@Override
	public void iniciar() {
		mostrarJanelaPrincipal();
	}
	
	JanPrincipal janPrincipal;
	
	private void mostrarJanelaPrincipal() { 	// Essa janela faz chamadas para abrir as outras janelas.
		janPrincipal = new JanPrincipal(this);
	}

	public void resetJanelaPrincipal() {
		janPrincipal.onClose();
		mostrarJanelaPrincipal();
	}
	
	public void setCompPPrSelecionado(CompPPr selecionado) {
		compPPrSelecionado = selecionado;
		janPrincipal.conteudo();    // atualiza informação na tela.
	}
	
	public CompPPr getcompPPrSelecionado() {
		return compPPrSelecionado;
	}
	
	
	public void abrirJanDefinirCompPPr(boolean setarCompPPEmArvore) {
		new JanDefinirCompPPr(this, setarCompPPEmArvore);
	}
	
	public void salvarCompPPr(CompPPr processoProjeto){
		aplDefinirProcessoProjeto.salvarCompPPr(processoProjeto);
	}
	
	public Collection<CompPP> getAllCompPPComOrdenacao(String orderBy) {
		return aplDefinirProcessoProjeto.recuperarTodosCompPPComOrdenacao(orderBy);
	}
	
	public Collection<CompPPr> getAllCompPPrComOrdenacao(String orderBy) {
		return aplDefinirProcessoProjeto.recuperarTodosCompPPrComOrdenacao(orderBy);
	}

	public Collection<CompPPr> getAllCompPPr() {
		return aplDefinirProcessoProjeto.recuperarTodosCompPPr();
	}
	
	public void abrirJanSelecionaProcessoProjeto() {
		new JanSelecionarProcessoProjeto(this);
	}
	
	public void abrirJanEditarPropriedadesBasicas() {
		new JanEditarPropriedadesBasicasProjeto(this);
	}
	
	
}

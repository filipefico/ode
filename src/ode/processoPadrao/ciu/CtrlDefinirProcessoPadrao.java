package ode.processoPadrao.ciu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.ElementoCompPP;
import ode.processoPadrao.cdp.EstruturaCompPP;
import ode.processoPadrao.cgt.AplDefinirProcessoPadrao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlDefinirProcessoPadrao extends CtrlBase {

	private static final long serialVersionUID = -2967662654799321521L;

	private CompPP compPPSelecionado;

	@Autowired
	private AplDefinirProcessoPadrao aplDefinirProcessoPadrao;

	public AplDefinirProcessoPadrao getAplDefinirProcessoPadrao() {
		return aplDefinirProcessoPadrao;
	}

	public void setAplDefinirProcessoPadrao(
			AplDefinirProcessoPadrao aplDefinirProcessoPadrao) {
		this.aplDefinirProcessoPadrao = aplDefinirProcessoPadrao;
	}

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

	public void setCompPPSelecionado(CompPP selecionado) {
		compPPSelecionado = selecionado;
		janDefinirProcessoPadrao.setCompPPSelecionado();// atualiza informação
														// na tela.
	}

	public void abrirJanDefinirCompPP() {
		new JanDefinirCompPP(this, factoryJanelaSimples());
	}

	public Collection<KProcesso> getAllKProcesso() {
		return aplDefinirProcessoPadrao.getAllKProcesso();
	}

	public Collection<KAtividade> getAllKAtividade() {
		return aplDefinirProcessoPadrao.getAllKAtividade();
	}

	public void salvarCompPP(CompPP compPP) {
		aplDefinirProcessoPadrao.salvarCompPP(compPP);
	}

	public void atualizarCompPP(CompPP compPP) {
		this.compPPSelecionado = aplDefinirProcessoPadrao
				.atualizarCompPP(compPP);
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

	public void abrirJanEstabelecerRequisitos() {
		new JanEstabelecerRequisitosCompPP(this, factoryJanelaSimples());
	}

	public void abrirJanSelecionaProcessoPadrao() {
		new JanSelecionaProcessoPadrao(this, factoryJanelaSimples());
	}

	public CompPP getcompPPSelecionado() {
		return compPPSelecionado;
	}

	public void abrirJanDefinirInterfaceCompPP() {
		new JanDefinirInterfaceCompPP(this, factoryJanelaSimples());
	}

	public void atualizarEstruturaCompPP(Set<Conhecimento> selecionados,
			Set<Conhecimento> selecionadosObrigatorios) {
		CompPP compPP = this.getcompPPSelecionado();

		Set<ElementoCompPP> elementos = new HashSet<ElementoCompPP>();

		// troca a lista antiga pela nova lista configurada pelo usuario
		EstruturaCompPP estruturaCompPP = compPP.getInterfaceCompPP()
				.getEstruturaCompPP();
		estruturaCompPP.setElementosCompPP(elementos);

		// adiciona elementos que não sao marcados como obrigatorios
		for (Conhecimento conhecimento : selecionados) {
			ElementoCompPP elemento = new ElementoCompPP();
			elemento.setElementoConhecimento(conhecimento);
			elemento.setObrigatorio(false);
			elementos.add(elemento);
		}

		// adiciona elementos marcados como obrigatorios
		for (Conhecimento conhecimento : selecionadosObrigatorios) {
			ElementoCompPP elemento = new ElementoCompPP();
			elemento.setElementoConhecimento(conhecimento);
			elemento.setObrigatorio(true);
			elementos.add(elemento);
		}

		// salva o compPP selecionado que acabou de ser alterado.
		this.compPPSelecionado = aplDefinirProcessoPadrao
				.atualizarCompPP(compPP);
	}

	public void abrirJanSelecionarCompPPBase() {
		new JanSelecionarCompPPBase(this, factoryJanelaSimples());
	}

	public void abrirJanIndicarSubProcessos() {
		new JanIndicarSubProcessos(this, factoryJanelaSimples());
	}

	public Collection<CompPP> getAllCompPP() {
		return aplDefinirProcessoPadrao.recuperarTodosCompPP();
	}

	public Collection<CompPPProcessoComplexo> getAllCompPPProcessoComplexo() {
		return aplDefinirProcessoPadrao.getAllCompPPProessoComplexo();
	}

	public Collection<CompPPProcessoSimples> getAllCompPPProcessoSimples() {
		return aplDefinirProcessoPadrao.getAllCompPPProessoSimples();
	}

	public Collection<CompPPMacroatividade> getAllCompPPMacroAtividade() {
		return aplDefinirProcessoPadrao.getAllCompPPMacroAtividade();
	}

	public void excluirCompPPselecionado() {
		aplDefinirProcessoPadrao.excluirCompPP(this.getcompPPSelecionado());
		this.setCompPPSelecionado(null);
	}

}

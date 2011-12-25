package ode.processoPadrao.ciu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KMetodo;
import ode.conhecimento.processo.cdp.KNorma;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecursoHardware;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cdp.KRoteiro;
import ode.conhecimento.processo.cdp.KTecnica;
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

	JanPrincipal janDefinirProcessoPadrao;

	private void mostrarJanelaPrincipal() {
		// essa janela faz chamadas para abrir as outras janelas.
		janDefinirProcessoPadrao = new JanPrincipal(this);

		HashSet<Conhecimento> c = new HashSet<Conhecimento>();
		for (KArtefato k : this.getAllKArtefato()) {
			c.add(k);
		}

	}

	public void resetJanelaPrincipal() {
		janDefinirProcessoPadrao.onClose();
		mostrarJanelaPrincipal();
	}

	public void setCompPPSelecionado(CompPP selecionado) {
		compPPSelecionado = selecionado;
		janDefinirProcessoPadrao.conteudo();// atualiza informação
											// na tela.
	}

	public void abrirJanDefinirCompPP() {
		new JanDefinirCompPP(this);
	}

	public void salvarCompPP(CompPP compPP) {
		aplDefinirProcessoPadrao.salvarCompPP(compPP);
		janDefinirProcessoPadrao.conteudo();
	}

	public void atualizarCompPP(CompPP compPP) {
		this.compPPSelecionado = aplDefinirProcessoPadrao
				.atualizarCompPP(compPP);
		janDefinirProcessoPadrao.conteudo();
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
		janDefinirProcessoPadrao.conteudo();
	}

	public void abrirJanEstabelecerRequisitos() {
		new JanEstabelecerRequisitosCompPP(this);
	}

	public void abrirJanSelecionaProcessoPadrao() {
		new JanSelecionaProcessoPadrao(this);
	}

	public CompPP getcompPPSelecionado() {
		return compPPSelecionado;
	}

	public void abrirJanDefinirInterfaceCompPP() {
		new JanDefinirInterfaceCompPP(this);
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
		new JanSelecionarCompPPBase(this);
	}

	public void abrirJanIndicarSubProcessos() {
		new JanIndicarSubProcessos(this);
	}

	public void abrirJanIndicarSubAtividades() {
		new JanIndicarSubAtividades(this);
	}

	public void abrirJanIndicarPreAtividades() {
		new JanIndicarPreAtividades(this);

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

	public void excluirCompPP(CompPP compPP) {
		aplDefinirProcessoPadrao.excluirCompPP(compPP);
	}

	public void finalizarDefinicao() {
		this.getcompPPSelecionado().setEhDefinido(true);
		this.atualizarCompPP(this.getcompPPSelecionado());
		janDefinirProcessoPadrao.onClose();
		this.mostrarJanelaPrincipal();
		janDefinirProcessoPadrao.conteudo();
	}

	public Collection<KProcesso> getAllKProcesso() {
		return aplDefinirProcessoPadrao.getAllKProcesso();
	}

	public Collection<KAtividade> getAllKAtividade() {
		return aplDefinirProcessoPadrao.getAllKAtividade();
	}

	public Collection<KArtefato> getAllKArtefato() {
		return aplDefinirProcessoPadrao.getAllKArtefato();
	}

	public Collection<KRecursoHumano> getAllKRecursoHumano() {
		return aplDefinirProcessoPadrao.getAllKRecursoHumano();
	}

	public Collection<KRecursoHardware> getAllKRecursoHardware() {
		return aplDefinirProcessoPadrao.getAllKRecursoHardware();
	}

	public Collection<KFerramentaSoftware> getAllKRecursoSoftware() {
		return aplDefinirProcessoPadrao.getAllKRecursoSoftware();
	}

	public Collection<KMetodo> getAllKProcedimentoMetodos() {
		return aplDefinirProcessoPadrao.getAllKProcedimentoMetodos();
	}

	public Collection<KNorma> getAllKProcedimentoNormas() {
		return aplDefinirProcessoPadrao.getAllKProcedimentoNormas();
	}

	public Collection<KRoteiro> getAllKProcedimentoRoteiros() {
		return aplDefinirProcessoPadrao.getAllKProcedimentoRoteiros();
	}

	public Collection<KTecnica> getAllKProcedimentoTecnicas() {
		return aplDefinirProcessoPadrao.getAllKProcedimentoTecnicas();
	}

}

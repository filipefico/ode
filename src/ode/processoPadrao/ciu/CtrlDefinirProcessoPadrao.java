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
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.ElementoCompPP;
import ode.processoPadrao.cdp.EstruturaCompPP;
import ode.processoPadrao.cgd.CompPPMacroatividadeDAO;
import ode.processoPadrao.cgt.AplDefinirProcessoPadrao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Messagebox;

@Controller("CtrlDefinirProcessoPadrao")
public class CtrlDefinirProcessoPadrao extends CtrlBase {

	private static final long serialVersionUID = 2692764915837753799L;

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

	JanPrincipal janPrincipal;

	private void mostrarJanelaPrincipal() {
		// essa classe abaixo é um teste e poderá ser removida quando as
		// consultas HQL forem otimizadas.
		class Teste extends Thread {
			CtrlDefinirProcessoPadrao ctrl;

			public Teste(CtrlDefinirProcessoPadrao ctrl) {
				this.ctrl = ctrl;
			}

			public void run() {
				ctrl.getAllCompPP();
			}
		}
		new Teste(this).start();
		
		// essa janela faz chamadas para abrir as outras janelas.
				janPrincipal = new JanPrincipal(this);
				

	}

	public void resetJanelaPrincipal() {
		janPrincipal.onClose();
		mostrarJanelaPrincipal();
	}

	public void setCompPPSelecionado(CompPP selecionado) {
		compPPSelecionado = selecionado;
		janPrincipal.conteudo();// atualiza informação
								// na tela.
	}

	public void abrirJanDefinirCompPP(boolean setarCompPPEmArvore) {
		new JanDefinirCompPP(this, setarCompPPEmArvore);
	}

	public void salvarCompPP(CompPP compPP) {
		aplDefinirProcessoPadrao.salvarCompPP(compPP);
		janPrincipal.conteudo();
	}

	public void salvarListaCompPP(Set compPP) {
		for (Object obj : compPP) {
			salvarCompPP((CompPP) obj);
		}
	}

	public void atualizarCompPP(CompPP compPP) {
		this.compPPSelecionado = aplDefinirProcessoPadrao
				.atualizarCompPP(compPP);
		janPrincipal.conteudo();
	}

	public void salvarCompPP(String nome, String descricao, String objetivo,
			Class tipo, String requisitos, Object objTipo,
			boolean setarCompPPEmArvore) {

		CompPP compPPSalvo = null;
		if (tipo == CompPPProcessoComplexo.class) {
			compPPSalvo = aplDefinirProcessoPadrao.salvarProcessoComplexo(nome,
					descricao, objetivo, requisitos);
		} else if (tipo == CompPPProcessoSimples.class) {
			compPPSalvo = aplDefinirProcessoPadrao.salvarProcessoSimples(nome,
					descricao, objetivo, requisitos, objTipo);
		} else if (tipo == CompPPMacroatividade.class) {
			compPPSalvo = aplDefinirProcessoPadrao.salvarMacroatividade(nome,
					descricao, objetivo, requisitos, objTipo);
		}

		if (setarCompPPEmArvore) {
			setCompPPSelecionado(compPPSalvo);
		}

		janPrincipal.conteudo();
	}

	public void abrirJanEditarPropriedadesBasicas() {
		new JanEditarPropriedadesBasicasCompPP(this);
	}

	public void abrirJanDefinirDependencias() {
		new JanDefinirDependencias(this);
	}

	public void abrirJanSelecionaProcessoPadrao() {
		new JanSelecionaProcessoPadrao(this);
	}

	public CompPP getcompPPSelecionado() {
		return compPPSelecionado;
	}

	public void abrirJanEditarEstruturaCompPP() {
		new JanEditarEstruturaCompPP(this);
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

	public Collection<CompPP> getAllCompPPComOrdenacao(String orderBy) {
		return aplDefinirProcessoPadrao
				.recuperarTodosCompPPComOrdenacao(orderBy);
	}

	public Collection getAllCompPPFinalizado(Class compPP) {

		if (compPP.equals(CompPPProcessoComplexo.class)) {
			return aplDefinirProcessoPadrao
					.recuperarTodosCompPPFinalizados(compPP);

		} else if (compPP.equals(CompPPProcessoSimples.class)) {
			return aplDefinirProcessoPadrao
					.recuperarTodosCompPPFinalizados(compPP);

		} else {// macroAtividade
			return aplDefinirProcessoPadrao
					.recuperarTodosCompPPFinalizados(compPP);
		}
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
		if (compPP instanceof CompPPMacroatividade) {
			System.out.println(teste((CompPPMacroatividade) compPP));
		}
		aplDefinirProcessoPadrao.excluirCompPP(compPP);
		setCompPPSelecionado(null);
		janPrincipal.conteudo();
	}

	public void finalizarDefinicao(CompPP compPP) {
		boolean finalizar = false;
		if (compPP instanceof CompPPProcessoComplexo)
			finalizar = finalizarDefinicao((CompPPProcessoComplexo) compPP);

		else if (compPP instanceof CompPPProcessoSimples)
			finalizar = finalizarDefinicao((CompPPProcessoSimples) compPP);

		else
			finalizar = finalizarDefinicao((CompPPMacroatividade) compPP);

		if (finalizar) {
			compPP.setDefinicaoConcluida(true);
			// persiste alterações
			this.atualizarCompPP(this.getcompPPSelecionado());
			//janPrincipal.onClose();// fecha janela

			// this.mostrarJanelaPrincipal();
			janPrincipal.conteudo();
		} else {
			try {
				Messagebox
						.show("Alguns subprocessos/subatividades não foram preenchidos. Finalização foi cancelada.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean finalizarDefinicao(CompPPProcessoComplexo compPP) {
		Set<Conhecimento> listaTiposInterface = new HashSet<Conhecimento>();

		for (ElementoCompPP el : compPP.getInterfaceCompPP()
				.getEstruturaCompPP().getElementosCompPP()) {
			listaTiposInterface.add(el.getElementoConhecimento());
		}

		Set<Conhecimento> listaTiposSubProc = new HashSet<Conhecimento>();
		for (CompPPProcessoSimples cppSimples : compPP.getProcessosSimples()) {
			listaTiposSubProc.add(cppSimples.getTipo());
		}

		return listaTiposSubProc.containsAll(listaTiposInterface);
	}

	public boolean finalizarDefinicao(CompPPProcessoSimples compPP) {
		Set<Conhecimento> listaTiposInterface = new HashSet<Conhecimento>();

		for (ElementoCompPP el : compPP.getInterfaceCompPP()
				.getEstruturaCompPP().getElementosCompPP()) {
			listaTiposInterface.add(el.getElementoConhecimento());
		}

		Set<Conhecimento> listaTiposSubProc = new HashSet<Conhecimento>();
		for (CompPPMacroatividade cppMacro : compPP.getMacroAtividades()) {
			listaTiposSubProc.add(cppMacro.getTipo());
		}

		return listaTiposSubProc.containsAll(listaTiposInterface);
	}

	public boolean finalizarDefinicao(CompPPMacroatividade compPP) {
		Set<Conhecimento> listaTiposInterface = new HashSet<Conhecimento>();

		for (ElementoCompPP el : compPP.getInterfaceCompPP()
				.getEstruturaCompPP().getElementosCompPP()) {
			listaTiposInterface.add(el.getElementoConhecimento());
		}

		Set<Conhecimento> listaTiposSubAtv = new HashSet<Conhecimento>();

		for (AtividadeProcessoPadrao subAtv : compPP
				.getAtividadeProcessoPadrao().getSubAtividades()) {
			listaTiposSubAtv.add(subAtv.getTipo());
		}

		return listaTiposSubAtv.containsAll(listaTiposInterface);
	}

	public Collection<KProcesso> getAllKProcesso() {
		return aplDefinirProcessoPadrao.getAllKProcesso();
	}
	
	public Collection<KProcesso> getAllKProcessoComOrdenacao(String orderBy) {
		return aplDefinirProcessoPadrao.getAllKProcessoComOrdenacao(orderBy);
	}

	public Collection getAllKProcessoNaoEngenharia() {
		return aplDefinirProcessoPadrao.getAllKProcessoNaoEngenharia();
	}

	public Collection getAllKProcessoEngenharia() {
		return aplDefinirProcessoPadrao.getAllKProcessoEngenharia();
	}

	public Collection<KAtividade> getAllKAtividade() {
		return aplDefinirProcessoPadrao.getAllKAtividade();
	}
	
	public Collection<KAtividade> getAllKAtividadeComOrdenacao(String orderBy) {
		return aplDefinirProcessoPadrao.getAllKAtividadeComOrdenacao(orderBy);
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

	@Autowired
	CompPPMacroatividadeDAO macroDao;

	public boolean teste(CompPPMacroatividade macro) {
		return macroDao.podeExcluir(macro);
	}

}

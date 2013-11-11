package ode.processoPadrao.ciu;

import java.util.ArrayList;
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
import ode.processoPadrao.cdp.DependenciaMacroAtividades;
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
	
	private AtividadeProcessoPadrao atividadeSelecionada;

	@Autowired
	private AplDefinirProcessoPadrao aplDefinirProcessoPadrao;

	public AplDefinirProcessoPadrao getAplDefinirProcessoPadrao() {
		return aplDefinirProcessoPadrao;
	}

	public void setAplDefinirProcessoPadrao(AplDefinirProcessoPadrao aplDefinirProcessoPadrao) {
		this.aplDefinirProcessoPadrao = aplDefinirProcessoPadrao;
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

	public void setCompPPSelecionado(CompPP selecionado) {
		compPPSelecionado = selecionado;
		janPrincipal.conteudo();    // atualiza informação na tela.
	}

	public void setCompPPSelecionadoParaVisualisar(CompPP selecionado) { // Essa janela abre a janela que irá visualizar o CompPP selecionado
		compPPSelecionado = selecionado;
		JanVisualisarProcessoPadrao JanVisualisar = new JanVisualisarProcessoPadrao(this);
		JanVisualisar.conteudoEstrutura();
		
		compPPSelecionado = null;
	}
	
	public void setAtividadeSelecionadaParaVisualisar(AtividadeProcessoPadrao selecionada) { // Essa janela abre a janela que irá visualizar a Atividade selecionado
		atividadeSelecionada = selecionada;
		JanVisualisarAtividadePadrao JanVisualisar = new JanVisualisarAtividadePadrao(this);
		//JanVisualisar.conteudoEstrutura();
		
		atividadeSelecionada = null;
	}
	
	public void abrirJanDefinirCompPP(boolean setarCompPPEmArvore) {
		new JanDefinirCompPP(this, setarCompPPEmArvore);
	}
	
	public void salvarCompPP(CompPP compPP) {
		aplDefinirProcessoPadrao.salvarCompPP(compPP);
		janPrincipal.conteudo();
	}

	public void salvarDependecia(DependenciaMacroAtividades dependencia) {
		aplDefinirProcessoPadrao.salvarDependencia(dependencia);
		janPrincipal.conteudo();
	}
	
	public void salvarListaCompPP(Set compPP) {
		for (Object obj : compPP) {
			salvarCompPP((CompPP) obj);
		}
	}

	public void atualizarCompPP(CompPP compPP) {
		this.compPPSelecionado = aplDefinirProcessoPadrao.atualizarCompPP(compPP);
		janPrincipal.conteudo();
	}

	public long existeAtividadePadraoBanco(KAtividade kAtividade){
		return aplDefinirProcessoPadrao.existeAtividadePadraoBanco(kAtividade);
	}
	
	// Salvar o Componente de Processo Padrão
	public void salvarCompPP(String nome, String descricao, String objetivo, Class tipo, String requisitos, Object objTipo, boolean setarCompPPEmArvore, boolean ehEngenharia) {

		CompPP compPPSalvo = null; // Receberá um compPP que vai ter sido salvo pela APL abaixo
		
		if (tipo == CompPPProcessoComplexo.class) {
			compPPSalvo = aplDefinirProcessoPadrao.salvarProcessoComplexo(nome, descricao, objetivo, requisitos);			
		} else if (tipo == CompPPProcessoSimples.class) {
			compPPSalvo = aplDefinirProcessoPadrao.salvarProcessoSimples(nome, descricao, objetivo, requisitos, objTipo, ehEngenharia);
		} else if (tipo == CompPPMacroatividade.class) {
			compPPSalvo = aplDefinirProcessoPadrao.salvarMacroatividade(nome, descricao, objetivo, requisitos, objTipo);
		}

		if (setarCompPPEmArvore) {
			setCompPPSelecionado(compPPSalvo);
		}

	}

	public AtividadeProcessoPadrao criarAtividadePadrao(String nome, String descricao, KAtividade tipoKAtividade, boolean ehMarco){
		return aplDefinirProcessoPadrao.criarAtividadePadrao(nome, descricao, tipoKAtividade, ehMarco);
	}
	
	public void abrirJanEditarPropriedadesBasicas() {
		new JanEditarPropriedadesBasicasCompPP(this);
	}

	public void abrirJanDefinirDependencias(CompPPMacroatividade macroAtv) {
		new JanDefinirDependencias(this, macroAtv);
	}

	public void abrirJanSelecionaProcessoComplexo() {
		new JanSelecionaProcessoComplexo(this);
	}

	public void abrirJanSelecionaProcessoSimples(){
		new JanSelecionaProcessoSimples(this);
	}
	
	public void abrirJanSelecionaProcessoMacroatividade(){
		new JanSelecionaProcessoMacroatividade(this);
	}
	
	public void abrirJanSelecionarVisualizarProcesso(){
		new JanSelecionarVisualizarProcesso(this);
	}
	
	public void abrirJanSelecionarVisualizarAtividade(){
		new JanSelecionarVisualizarAtividade(this);
	}
	
	public void abrirJanDefinirAtividadePadrao(){
		new JanDefinirAtividadePadrao(this);
	}
	
	public CompPP getcompPPSelecionado() {
		return compPPSelecionado;
	}
	
	public AtividadeProcessoPadrao getAtividadeSelecionada(){
		return atividadeSelecionada;
	}

	public void abrirJanEditarEstruturaCompPP() {
		new JanEditarEstruturaCompPP(this);
	}

	public void atualizarEstruturaCompPP(Set<Conhecimento> selecionados, Set<Conhecimento> selecionadosObrigatorios) {
		
		CompPP compPP = this.getcompPPSelecionado();

		Set<ElementoCompPP> elementos = new HashSet<ElementoCompPP>();

		// troca a lista antiga pela nova lista configurada pelo usuario
		EstruturaCompPP estruturaCompPP = compPP.getInterfaceCompPP().getEstruturaCompPP();
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
		this.compPPSelecionado = aplDefinirProcessoPadrao.atualizarCompPP(compPP);
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

	public void abrirJanIndicarPreAtividades(AtividadeProcessoPadrao atividade) {
		new JanIndicarPreAtividades(this, atividade);

	}
	
	public void abrirJanEditarPropriedadesAtividadePadrao(AtividadeProcessoPadrao Atv){
		new JanEditarPropriedadesAtividadePadrao(this, Atv);
	}

	public Collection<CompPP> getAllCompPP() {
		return aplDefinirProcessoPadrao.recuperarTodosCompPP();
	}

	public Collection<CompPP> getAllCompPPComOrdenacao(String orderBy) {
		return aplDefinirProcessoPadrao.recuperarTodosCompPPComOrdenacao(orderBy);
	}

	public Collection getAllCompPPFinalizado(Class compPP) {

		if (compPP.equals(CompPPProcessoComplexo.class)) {
			return aplDefinirProcessoPadrao.recuperarTodosCompPPFinalizados(compPP);

		} else if (compPP.equals(CompPPProcessoSimples.class)) {
			return aplDefinirProcessoPadrao.recuperarTodosCompPPFinalizados(compPP);

		} else {// macroAtividade
			return aplDefinirProcessoPadrao.recuperarTodosCompPPFinalizados(compPP);
		}
	}

	public Collection<CompPPProcessoComplexo> getAllCompPPProcessoComplexo() {
		return aplDefinirProcessoPadrao.getAllCompPPProcessoComplexo();
	}

	public Collection<CompPPProcessoSimples> getAllCompPPProcessoSimples() {
		return aplDefinirProcessoPadrao.getAllCompPPProcessoSimples();
	}

	public Collection<CompPPMacroatividade> getAllCompPPMacroAtividade() {
		return aplDefinirProcessoPadrao.getAllCompPPMacroAtividade();
	}

	public Collection<AtividadeProcessoPadrao> getAllAtividadeProcessoPadrao(){
		return aplDefinirProcessoPadrao.getAllAtividadeProcessoPadrao();
	}
	
	public void excluirCompPPselecionado() {
		aplDefinirProcessoPadrao.excluirCompPP(this.getcompPPSelecionado());
		this.setCompPPSelecionado(null);
	}

	public void excluirCompPP(CompPP compPP) {
		boolean podeExcluir = true;
		String motivo = "";
		String nomesCompPP = "";
		String nomesCompPPBase = "";
		int quantidadeNomes = 0;
		int quantidadeNomesBase = 0;
		int quantidadeMotivos = 0;
		

		// PROCESSO COMPLEXO
		
		if (compPP instanceof CompPPProcessoComplexo){
			Collection<CompPPProcessoComplexo> listaComplexo = this.getAllCompPPProcessoComplexo();
			
			for(CompPPProcessoComplexo complexo : listaComplexo){
				if(complexo.getCompPPBase() != null){
					if(complexo.getCompPPBase().equals((CompPPProcessoComplexo) compPP)){
						podeExcluir = false;
						quantidadeNomes++;
						
						if (quantidadeNomes == 1){
							nomesCompPP = nomesCompPP + complexo.getNome();
						}else{
							nomesCompPP = nomesCompPP + ", " + complexo.getNome();
						}
						
					}
				}
			}
			
			if(podeExcluir == false){
				try {					
					if(quantidadeNomes == 1){
						Messagebox.show("Este elemento é base do componente " + nomesCompPP + ". Exclusão cancelada.");
					}else{
						Messagebox.show("Este elemento é base dos componentes " + nomesCompPP + ". Exclusão cancelada.");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				((CompPPProcessoComplexo) compPP).setCompPPBase(null);
				((CompPPProcessoComplexo) compPP).setInterfaceCompPP(null);
				((CompPPProcessoComplexo) compPP).setProcessosSimples(null);
				((CompPPProcessoComplexo) compPP).setVersion(null);
			}
			
		}else
		
				// PROCESSO SIMPLES
				
				if (compPP instanceof CompPPProcessoSimples){
					Collection<CompPPProcessoComplexo> listaComplexo = this.getAllCompPPProcessoComplexo();
					Collection<CompPPProcessoSimples> listaSimples = this.getAllCompPPProcessoSimples();
					
					for(CompPPProcessoComplexo complexo : listaComplexo){
						if(complexo.getProcessosSimples().contains(compPP)) {
							podeExcluir = false;
							quantidadeNomes++;
							
							if (quantidadeNomes == 1){
								nomesCompPP = nomesCompPP + complexo.getNome();
							}else{
								nomesCompPP = nomesCompPP + ", " + complexo.getNome();
							}
						}
					}
					
					if(quantidadeNomes > 0){
						quantidadeMotivos++;
						motivo = "composicao";
					}
					
					for(CompPPProcessoSimples simples : listaSimples){
						if(simples.getCompPPBase() != null){
							if(simples.getCompPPBase().equals((CompPPProcessoSimples) compPP)) {
								podeExcluir = false;
								quantidadeNomesBase++;
								
								if (quantidadeNomesBase == 1){
									nomesCompPPBase = nomesCompPPBase + simples.getNome();
								}else{
									nomesCompPPBase = nomesCompPPBase + ", " + simples.getNome();
								}
							}
						}
					}
					
					if(quantidadeNomesBase > 0){
						quantidadeMotivos++;
						motivo = "base";
					}
					
					
					if(podeExcluir == false){
						
						if(quantidadeMotivos == 1){
							if(motivo.compareTo("composicao") == 0){
								try {					
									if(quantidadeNomes == 1){
										Messagebox.show("Este elemento faz parte da composiçao do componente " + nomesCompPP + ". Exclusão cancelada.");
									}else{
										Messagebox.show("Este elemento faz parte da composiçao dos componentes " + nomesCompPP + ". Exclusão cancelada.");
									}
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								try {					
									if(quantidadeNomes == 1){
										Messagebox.show("Este elemento é base do componente " + nomesCompPPBase + ". Exclusão cancelada.");
									}else{
										Messagebox.show("Este elemento é base dos componentes " + nomesCompPPBase + ". Exclusão cancelada.");
									}
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}else{
							try {					
								Messagebox.show("Este elemento faz parte da composiçao dos componentes " + nomesCompPP + ". E também usado como base dos componentes " + nomesCompPPBase + ". Exclusão cancelada.");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
								
						
					}else{ // Retira todos os componentes que o compõe antes da exclusão
						((CompPPProcessoSimples) compPP).setCompPPBase(null);
						((CompPPProcessoSimples) compPP).setDependenciaMacroAtividades(null);
						((CompPPProcessoSimples) compPP).setInterfaceCompPP(null);
						((CompPPProcessoSimples) compPP).setMacroAtividades(null);
						((CompPPProcessoSimples) compPP).setProcessosComplexos(null);
						((CompPPProcessoSimples) compPP).setTipo(null);				
					}
					
				
				}else
		
				
					// MACROATIVIDADE:
					
					if (compPP instanceof CompPPMacroatividade){
						Collection<CompPPProcessoSimples> listaSimples = this.getAllCompPPProcessoSimples();
						Collection<CompPPMacroatividade> listaMacro = this.getAllCompPPMacroAtividade();
						
						for(CompPPProcessoSimples simples : listaSimples){
							if(simples.getMacroAtividades().contains(compPP)) {
								podeExcluir = false;
								quantidadeNomes++;
								
								if (quantidadeNomes == 1){
									nomesCompPP = nomesCompPP + simples.getNome();
								}else{
									nomesCompPP = nomesCompPP + ", " + simples.getNome();
								}
							}
						}
						
						if(quantidadeNomes > 0){
							quantidadeMotivos++;
							motivo = "composicao";
						}
						
						for(CompPPMacroatividade macroatividade : listaMacro){
							if(macroatividade.getCompPPBase() != null){
								if(macroatividade.getCompPPBase().equals((CompPPProcessoSimples) compPP)) {
									podeExcluir = false;
									quantidadeNomesBase++;
									
									if (quantidadeNomesBase == 1){
										nomesCompPPBase = nomesCompPPBase + macroatividade.getNome();
									}else{
										nomesCompPPBase = nomesCompPPBase + ", " + macroatividade.getNome();
									}
								}
							}
						}
						
						if(quantidadeNomesBase > 0){
							quantidadeMotivos++;
							motivo = "base";
						}
						
						if(podeExcluir == false){
							if(quantidadeMotivos == 1){
								if(motivo.compareTo("composicao") == 0){
									try {					
										if(quantidadeNomes == 1){
											Messagebox.show("Este elemento faz parte da composiçao do componente " + nomesCompPP + ". Exclusão cancelada.");
										}else{
											Messagebox.show("Este elemento faz parte da composiçao dos componentes " + nomesCompPP + ". Exclusão cancelada.");
										}
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}else{
									try {					
										if(quantidadeNomes == 1){
											Messagebox.show("Este elemento é base do componente " + nomesCompPPBase + ". Exclusão cancelada.");
										}else{
											Messagebox.show("Este elemento é base dos componentes " + nomesCompPPBase + ". Exclusão cancelada.");
										}
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}else{
								try {					
									Messagebox.show("Este elemento faz parte da composiçao dos componentes " + nomesCompPP + ". E também usado como base dos componentes " + nomesCompPPBase + ". Exclusão cancelada.");
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
									
						}else{ // Retira todos os componentes que o compõe antes da exclusão
							((CompPPMacroatividade) compPP).setAtividadeProcessoPadrao(null);
							((CompPPMacroatividade) compPP).setBaseDependenciaMacroAtividade(null);
							((CompPPMacroatividade) compPP).setCompPPBase(null);
							((CompPPMacroatividade) compPP).setInterfaceCompPP(null);
							((CompPPMacroatividade) compPP).setPreDependenciaMacroAtividade(null);
							((CompPPMacroatividade) compPP).setTipo(null);
						}
						
				}
		
		
		if(podeExcluir){
			aplDefinirProcessoPadrao.excluirCompPP(compPP);
			setCompPPSelecionado(null);
			
			try {
				Messagebox.show("O componente foi excluido com sucesso.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			janPrincipal.conteudo();
		}

	}

	public void finalizarDefinicao(CompPP compPP) {
		boolean finalizar = false;
		if (compPP instanceof CompPPProcessoComplexo){
			finalizar = finalizarDefinicao((CompPPProcessoComplexo) compPP);
		}
			else if (compPP instanceof CompPPProcessoSimples){
				finalizar = finalizarDefinicao((CompPPProcessoSimples) compPP);
			}
				else
					finalizar = finalizarDefinicao((CompPPMacroatividade) compPP);

		if (finalizar) {
			compPP.setDefinicaoConcluida(true);
			// persiste alterações
			this.atualizarCompPP(this.getcompPPSelecionado());
			try {
				Messagebox.show("Definição finalizada com sucesso.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Messagebox.show("Por favor, finalize todos os SubElementos descritos na interface do componente e crie SubElementos para todos os tipos preenchidos na estrutura do Componente (Editar Estrutura). Finalização foi cancelada.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean finalizarDefinicao(CompPPProcessoComplexo compPP) {
		Set<Conhecimento> listaTiposInterface = new HashSet<Conhecimento>();
		boolean finalizar = true;

		for (ElementoCompPP el : compPP.getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()) {
			listaTiposInterface.add(el.getElementoConhecimento());
		}

		Set<Conhecimento> listaTiposSubProc = new HashSet<Conhecimento>();
		for (CompPPProcessoSimples cppSimples : compPP.getProcessosSimples()) {
			listaTiposSubProc.add(cppSimples.getTipo());
		}

		for (CompPPProcessoSimples simples : compPP.getProcessosSimples()){
			if(simples.isDefinicaoConcluida() == false){finalizar = false;}
		}
		
		return listaTiposSubProc.containsAll(listaTiposInterface) && finalizar;
	}

	public boolean finalizarDefinicao(CompPPProcessoSimples compPP) {
		Set<Conhecimento> listaTiposInterface = new HashSet<Conhecimento>();
		boolean finalizar = true;

		for (ElementoCompPP el : compPP.getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()) {
			listaTiposInterface.add(el.getElementoConhecimento());
		}

		Set<Conhecimento> listaTiposSubProc = new HashSet<Conhecimento>();
		for (CompPPMacroatividade cppMacro : compPP.getMacroAtividades()) {
			listaTiposSubProc.add(cppMacro.getTipo());
		}
		
		for (CompPPMacroatividade macroatividade : compPP.getMacroAtividades()){
			if(macroatividade.isDefinicaoConcluida() == false){finalizar = false;}
		}
		
		//Todas as macroatividades descritas na interface do CompPP de processo simples devem ter sido definidas.
		return listaTiposSubProc.containsAll(listaTiposInterface) && finalizar;
	}

	public boolean finalizarDefinicao(CompPPMacroatividade compPP) {
		Set<Conhecimento> listaTiposInterface = new HashSet<Conhecimento>();

		for (ElementoCompPP el : compPP.getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()) {
			listaTiposInterface.add(el.getElementoConhecimento());
		}

		Set<Conhecimento> listaTiposSubAtv = new HashSet<Conhecimento>();

		for (AtividadeProcessoPadrao subAtv : compPP.getAtividadeProcessoPadrao().getSubAtividades()) {
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

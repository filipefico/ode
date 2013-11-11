package ode.processoPadrao.cgt;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KMetodo;
import ode.conhecimento.processo.cdp.KNorma;
import ode.conhecimento.processo.cdp.KProcedimento;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecurso;
import ode.conhecimento.processo.cdp.KRecursoHardware;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cdp.KRoteiro;
import ode.conhecimento.processo.cdp.KTecnica;
import ode.conhecimento.processo.cgd.KArtefatoDAO;
import ode.conhecimento.processo.cgd.KAtividadeDAO;
import ode.conhecimento.processo.cgd.KFerramentaSoftwareDAO;
import ode.conhecimento.processo.cgd.KMetodoDAO;
import ode.conhecimento.processo.cgd.KNormaDAO;
import ode.conhecimento.processo.cgd.KProcessoDAO;
import ode.conhecimento.processo.cgd.KRecursoHardwareDAO;
import ode.conhecimento.processo.cgd.KRecursoHumanoDAO;
import ode.conhecimento.processo.cgd.KRoteiroDAO;
import ode.conhecimento.processo.cgd.KTecnicaDAO;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.DependenciaMacroAtividades;
import ode.processoPadrao.cgd.AtividadeProcessoPadraoDAO;
import ode.processoPadrao.cgd.CompPPDAO;
import ode.processoPadrao.cgd.CompPPMacroatividadeDAO;
import ode.processoPadrao.cgd.CompPPProcessoComplexoDAO;
import ode.processoPadrao.cgd.CompPPProcessoSimplesDAO;
import ode.processoPadrao.cgd.DependenciaMacroAtividadeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplDefinirProcessoPadrao")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirProcessoPadrao {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1948518281239702220L;
	@Autowired
	private CompPPDAO compPPDAO;
	@Autowired
	private AtividadeProcessoPadraoDAO atividadeProcessoPadraoDAO;
	@Autowired
	private KProcessoDAO kProcessoDAO;
	@Autowired
	private KAtividadeDAO kAtividadeDAO;
	@Autowired
	private KArtefatoDAO kArtefatoDAO;
	@Autowired
	private KRecursoHumanoDAO kRecursoHumanoDAO;
	@Autowired
	private KRecursoHardwareDAO kRecursoHardwareDAO;
	@Autowired
	private KFerramentaSoftwareDAO kFerramentaSoftwareDAO;

	@Autowired
	private KMetodoDAO kMetodoDAO;
	@Autowired
	private KNormaDAO kNormaDAO;
	@Autowired
	private KRoteiroDAO kRoteiroDAO;
	@Autowired
	private KTecnicaDAO kTecnicaDAO;
	@Autowired
	private DependenciaMacroAtividadeDAO dependenciaMacroDAO;

	
	// Processo Complexo:
	
	public CompPPProcessoComplexo salvarProcessoComplexo(String nome, String descricao, String objetivo, String requisitos) {
		
		CompPPProcessoComplexo compPPcomplexo = new CompPPProcessoComplexo();
		compPPcomplexo.setNome(nome);
		compPPcomplexo.setDescricao(descricao);
		compPPcomplexo.setObjetivo(objetivo);
		compPPcomplexo.setRequisitoCompPP(requisitos);

		compPPDAO.salvar(compPPcomplexo);
		return compPPcomplexo;
	}

	// Processo Simples:
	
	public CompPPProcessoSimples salvarProcessoSimples(String nome, String descricao, String objetivo, String requisitos, Object objTipo, boolean ehEngenharia) {
		
		CompPPProcessoSimples compPPsimples = new CompPPProcessoSimples();

		compPPsimples.setNome(nome);
		compPPsimples.setDescricao(descricao);
		compPPsimples.setObjetivo(objetivo);
		//compPPsimples.getAtividadeProcessoPadrao().setNome(nome);
		compPPsimples.setRequisitoCompPP(requisitos);
		compPPsimples.setTipo(null);
		compPPsimples.setEngenharia(ehEngenharia);
		
		compPPDAO.salvar(compPPsimples);

		compPPsimples.setTipo(kProcessoDAO.recuperarPorId(((KProcesso) objTipo).getId()));
		//compPPsimples.getAtividadeProcessoPadrao().setTipo(kAtividadeDAO.recuperarPorId(((KAtividade) objTipo).getId()));
		
		return (CompPPProcessoSimples) compPPDAO.atualizar(compPPsimples);

	}

	
	// Processo de Macroatividade:
	
	public CompPPMacroatividade salvarMacroatividade(String nome, String descricao, String objetivo, String requisitos, Object objTipo) {
		
		AtividadeProcessoPadrao atividadePadrao = (AtividadeProcessoPadrao) objTipo;
		CompPPMacroatividade compPPMacroatividade = new CompPPMacroatividade();
		compPPMacroatividade.setNome(nome);
		compPPMacroatividade.setDescricao(descricao);
		compPPMacroatividade.setObjetivo(objetivo);
		//compPPMacroatividade.getAtividadeProcessoPadrao().setNome(nome);
		compPPMacroatividade.setRequisitoCompPP(requisitos);

		compPPDAO.salvar(compPPMacroatividade);

		if(atividadePadrao.getTipo() != null){
			compPPMacroatividade.setTipo(kAtividadeDAO.recuperarPorId((atividadePadrao.getTipo()).getId()));
		}
		
		compPPMacroatividade.setAtividadeProcessoPadrao(atividadePadrao);
		//compPPMacroatividade.getAtividadeProcessoPadrao().setTipo(kAtividadeDAO.recuperarPorId(((KAtividade) objTipo).getId()));
		
		return (CompPPMacroatividade) compPPDAO.atualizar(compPPMacroatividade);
	}
	
	// Atividade Padrão
	
	public AtividadeProcessoPadrao criarAtividadePadrao(String nome, String descricao, KAtividade tipoKAtividade, boolean ehMarco){
		
		AtividadeProcessoPadrao Atv = new AtividadeProcessoPadrao();
		Atv.setNome(new String(nome));
		Atv.setDescricao(new String(descricao));
		Atv.setEhMarco(ehMarco);		
		
		// KProcedimento
		for(KProcedimento kProcedimento : tipoKAtividade.getProcedimentos()){
			if(kProcedimento instanceof KNorma){
				Atv.getProcedimentoNorma().add((KNorma) kProcedimento);
			}else
				if(kProcedimento instanceof KMetodo){
					Atv.getProcedimentoMetodo().add(((KMetodo) kProcedimento));
				}else
					if(kProcedimento instanceof KRoteiro){
						Atv.getProcedimentoRoteiro().add(((KRoteiro) kProcedimento));
					}else
						if(kProcedimento instanceof KTecnica){
								Atv.getProcedimentoTecnica().add(((KTecnica) kProcedimento));
						}
		}
		
		// KRecurso
		for(KRecurso kRecurso : tipoKAtividade.getRecursos()){
			if(kRecurso instanceof KRecursoHardware){
				Atv.getRecursoHardware().add(((KRecursoHardware) kRecurso));
			}else
				if(kRecurso instanceof KRecursoHumano){
					Atv.getRecursoHumano().add(((KRecursoHumano) kRecurso));
				}else
					if(kRecurso instanceof KFerramentaSoftware){
						Atv.getFerramentaSoftware().add(((KFerramentaSoftware) kRecurso));
					}
		}
		
		// KArtefato
		for(KArtefato kArtefato : tipoKAtividade.getInsumos()){
			Atv.getInsumos().add(kArtefato);
		}
		
		for(KArtefato kArtefato : tipoKAtividade.getProdutos()){
			Atv.getProdutos().add(kArtefato);
		}
		
		// Sub e Pre-Atividades por recursão:
		for(KAtividade kAtividade : tipoKAtividade.getSubAtividades()){
			long id = existeAtividadePadraoBanco(kAtividade);
			if(id == 0){
				Atv.getSubAtividades().add(criarAtividadePadrao(kAtividade.getNome(), kAtividade.getDescricao(), kAtividade, false));
			}else{
				Atv.getSubAtividades().add(atividadeProcessoPadraoDAO.recuperarPorId((long)id));
			}
		}
		
		for(KAtividade kAtividade : tipoKAtividade.getPreAtividades()){
			long id = existeAtividadePadraoBanco(kAtividade);
			if(id == 0){
				Atv.getPreAtividades().add(criarAtividadePadrao(kAtividade.getNome(), kAtividade.getDescricao(), kAtividade, false));
			}else{
				Atv.getPreAtividades().add(atividadeProcessoPadraoDAO.recuperarPorId((long)id));
			}
		}
		
		atividadeProcessoPadraoDAO.salvar(Atv);
		Atv.setTipo(kAtividadeDAO.recuperarPorId(tipoKAtividade.getId()));
		
		return (AtividadeProcessoPadrao) atividadeProcessoPadraoDAO.atualizar(Atv);
	}
	
	public long existeAtividadePadraoBanco(KAtividade kAtividade){
		
		if(kAtividade != null){		
			for(AtividadeProcessoPadrao Atv : atividadeProcessoPadraoDAO.recuperarTodos()){
				if(Atv != null){
					if(Atv.getTipo() != null){
						if(Atv.getTipo().getNome().compareTo(kAtividade.getNome()) == 0){
							return Atv.getId();
						}
					}
				}
			}
		}
		
		return 0;
	}
	
	public void salvarCompPP(CompPP compPP) {
		compPPDAO.salvar(compPP);
	}

	public void salvarDependencia(DependenciaMacroAtividades dependencia) {
		dependenciaMacroDAO.salvar(dependencia);
		
	}
	
	public CompPP atualizarCompPP(CompPP compPP) {
		return compPPDAO.atualizar(compPP);
	}

	public void excluirCompPP(CompPP compPP) {
		compPPDAO.excluir(compPP);
	}

	public Collection<CompPP> recuperarTodosCompPP() {
		return compPPDAO.recuperarTodos();

	}

	public Collection<CompPP> recuperarTodosCompPPComOrdenacao(String orderBy) {
		return compPPDAO.recuperarTodosComOrdenacao(orderBy);

	}

	@Autowired
	CompPPProcessoComplexoDAO compPPProcessoComplexoDAO;

	public Collection<CompPPProcessoComplexo> getAllCompPPProcessoComplexo() {
		return compPPProcessoComplexoDAO.recuperarTodos();
	}

	@Autowired
	CompPPProcessoSimplesDAO compPPProcessoSimplesDAO;

	public Collection<CompPPProcessoSimples> getAllCompPPProcessoSimples() {
		return compPPProcessoSimplesDAO.recuperarTodos();
	}

	@Autowired
	CompPPMacroatividadeDAO compPPMacroatividadeDAO;

	public Collection<CompPPMacroatividade> getAllCompPPMacroAtividade() {
		return compPPMacroatividadeDAO.recuperarTodos();
	}
	
	public Collection<AtividadeProcessoPadrao> getAllAtividadeProcessoPadrao(){
		return atividadeProcessoPadraoDAO.recuperarTodos();
	}
	
	public Collection<KProcesso> getAllKProcesso() {
		return kProcessoDAO.recuperarTodos();
	}
	
	public Collection<KProcesso> getAllKProcessoComOrdenacao(String orderBy) {
		return kProcessoDAO.recuperarTodosComOrdenacao(orderBy);
	}

	public Collection getAllKProcessoNaoEngenharia() {
		return kProcessoDAO.recuperarTodosNaoEngenharia();
	}

	public Collection getAllKProcessoEngenharia() {
		return kProcessoDAO.recuperarTodosEngenharia();
	}

	public Collection<KAtividade> getAllKAtividade() {
		return kAtividadeDAO.recuperarTodos();
	}
	
	public Collection<KAtividade> getAllKAtividadeComOrdenacao(String orderBy) {
		return kAtividadeDAO.recuperarTodosComOrdenacao(orderBy);
	}

	public Collection<KArtefato> getAllKArtefato() {
		return kArtefatoDAO.recuperarTodos();
	}

	public Collection<KRecursoHumano> getAllKRecursoHumano() {
		return kRecursoHumanoDAO.recuperarTodos();
	}

	public Collection<KRecursoHardware> getAllKRecursoHardware() {
		return kRecursoHardwareDAO.recuperarTodos();
	}

	public Collection<KFerramentaSoftware> getAllKRecursoSoftware() {
		return kFerramentaSoftwareDAO.recuperarTodos();
	}

	public Collection<KMetodo> getAllKProcedimentoMetodos() {
		return kMetodoDAO.recuperarTodos();
	}

	public Collection<KNorma> getAllKProcedimentoNormas() {
		return kNormaDAO.recuperarTodos();
	}

	public Collection<KRoteiro> getAllKProcedimentoRoteiros() {
		return kRoteiroDAO.recuperarTodos();
	}

	public Collection<KTecnica> getAllKProcedimentoTecnicas() {
		return kTecnicaDAO.recuperarTodos();
	}

	public Collection recuperarTodosCompPPFinalizados(Class compPP) {
		return compPPDAO.recuperarTodosFinalizados(compPP);
	}



}

package ode.processoPadrao.cgt;

import java.util.Collection;

import ode._infraestruturaBase.excecao.NucleoExcecao;
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
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cgd.AtividadeProcessoPadraoDAO;
import ode.processoPadrao.cgd.CompPPDAO;
import ode.processoPadrao.cgd.CompPPMacroatividadeDAO;
import ode.processoPadrao.cgd.CompPPProcessoComplexoDAO;
import ode.processoPadrao.cgd.CompPPProcessoSimplesDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplDefinirProcessoPadrao")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirProcessoPadrao {

	@Autowired
	private CompPPDAO compPPDAO;
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
	private AtividadeProcessoPadraoDAO atividadeProcessoPadraoDAO;

	@Autowired
	private KMetodoDAO kMetodoDAO;
	@Autowired
	private KNormaDAO kNormaDAO;
	@Autowired
	private KRoteiroDAO kRoteiroDAO;
	@Autowired
	private KTecnicaDAO kTecnicaDAO;

	public void salvarProcessoComplexo(String nome, String descricao,
			String objetivo) {
		CompPPProcessoComplexo compPPcomplexo = new CompPPProcessoComplexo();
		compPPcomplexo.setNome(nome);
		compPPcomplexo.setDescricao(descricao);
		compPPcomplexo.setObjetivo(objetivo);

		compPPDAO.salvar(compPPcomplexo);
	}

	public void salvarCompPP(CompPP compPP) {
		compPPDAO.salvar(compPP);
	}

	public CompPP atualizarCompPP(CompPP compPP) {
		return compPPDAO.atualizar(compPP);
	}

	public void excluirCompPP(CompPP compPP) {
		compPPDAO.excluir(compPP);
	}

	public void salvarProcessoSimples(String nome, String descricao,
			String objetivo, Object objTipo) {
		CompPPProcessoSimples compPPsimples = new CompPPProcessoSimples();

		compPPsimples.setNome(nome);
		compPPsimples.setDescricao(descricao);
		compPPsimples.setObjetivo(objetivo);
		compPPsimples.setTipo(kProcessoDAO.recuperarPorId(((KProcesso) objTipo)
				.getId()));

		compPPDAO.salvar(compPPsimples);

	}

	public void salvarMacroatividade(String nome, String descricao,
			String objetivo, Object objTipo) {
		CompPPMacroatividade compPPMacroatividade = new CompPPMacroatividade();

		compPPMacroatividade.setNome(nome);
		compPPMacroatividade.setDescricao(descricao);
		compPPMacroatividade.setObjetivo(objetivo);
		compPPMacroatividade.setTipo(kAtividadeDAO
				.recuperarPorId(((KAtividade) objTipo).getId()));

		compPPMacroatividade.getAtividadeProcessoPadrao().setTipo(
				kAtividadeDAO.recuperarPorId(((KAtividade) objTipo).getId()));
		compPPMacroatividade.getAtividadeProcessoPadrao().setNome(nome);

		compPPDAO.salvar(compPPMacroatividade);

	}

	public Collection<CompPP> recuperarTodosCompPP() {
		return compPPDAO.recuperarTodos();
	}

	@Autowired
	CompPPProcessoComplexoDAO compPPProcessoComplexoDAO;

	public Collection<CompPPProcessoComplexo> getAllCompPPProessoComplexo() {
		return compPPProcessoComplexoDAO.recuperarTodos();
	}

	@Autowired
	CompPPProcessoSimplesDAO compPPProcessoSimplesDAO;

	public Collection<CompPPProcessoSimples> getAllCompPPProessoSimples() {
		return compPPProcessoSimplesDAO.recuperarTodos();
	}

	@Autowired
	CompPPMacroatividadeDAO compPPMacroatividadeDAO;

	public Collection<CompPPMacroatividade> getAllCompPPMacroAtividade() {
		return compPPMacroatividadeDAO.recuperarTodos();
	}

	public Collection<KProcesso> getAllKProcesso() {
		return kProcessoDAO.recuperarTodos();
	}

	public Collection<KAtividade> getAllKAtividade() {
		return kAtividadeDAO.recuperarTodos();
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

}

package ode.processoPadrao.cgt;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.principal.cgd.ConhecimentoDAO;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgd.KAtividadeDAO;
import ode.conhecimento.processo.cgd.KProcessoDAO;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
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
	
	public void atualizarCompPP(CompPP compPP) {
		compPPDAO.atualizar(compPP);
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
		compPPMacroatividade.setTipo(kAtividadeDAO.recuperarPorId(((KAtividade) objTipo)
				.getId()));
									
		
		compPPDAO.salvar(compPPMacroatividade);

	}
	
	public Collection<KProcesso> getAllKProcesso() {
		return kProcessoDAO.recuperarTodos();
	}


	public Collection<KAtividade> getAllKAtividade() {
		return kAtividadeDAO.recuperarTodos();
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

}

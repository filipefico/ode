package ode.processoPadrao.Cgt;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.CompPPMacroatividade;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;
import ode.processoPadrao.Cdp.CompPPProcessoSimples;
import ode.processoPadrao.Cgd.CompPPDAO;
import ode.processoPadrao.Cgd.CompPPProcessoComplexoDAO;
import ode.processoPadrao.Cgd.CompPPProcessoSimplesDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirProcessoPadraoImpl extends AplBaseImpl<CompPP> implements
		AplDefinirProcessoPadrao {

	@Autowired
	private CompPPDAO compPPDAO;

	public CompPPDAO getCompPPDAO() {
		return compPPDAO;
	}

	public void setCompPPDAO(CompPPDAO compPPDAO) {
		this.compPPDAO = compPPDAO;
	}

	@Override
	public DAOBase<CompPP> getNucleoDaoBase() {
		return compPPDAO;
	}

	
	
	@Autowired
	private CompPPProcessoComplexoDAO compPPProcessoComplexoDAO;

	@Override
	public void salvarProcessoComplexo(String nome, String descricao,
			String objetivo) {
		CompPPProcessoComplexo compPPcomplexo = new CompPPProcessoComplexo();
		compPPcomplexo.setNome(nome);
		compPPcomplexo.setDescricao(descricao);
		compPPcomplexo.setObjetivo(objetivo);

		compPPProcessoComplexoDAO.salvar(compPPcomplexo);

	}

	
	@Autowired
	private CompPPProcessoSimplesDAO compPPProcessoSimplesDAO;
	
	
	@Autowired
	KProcessoDAO kProcessoDAO;
	@Override
	public void salvarProcessoSimples(String nome, String descricao,
			String objetivo, Object objTipo) {
		CompPPProcessoSimples compPPsimples = new CompPPProcessoSimples();
		compPPsimples.setNome(nome);
		compPPsimples.setDescricao(descricao);
		compPPsimples.setObjetivo(objetivo);
		compPPsimples.setTipo(kProcessoDAO.recuperarPorId(((KProcesso)objTipo).getId()));
		compPPProcessoSimplesDAO.salvar(compPPsimples);
		
		
	}

	@Override
	public void salvarMacroatividade(String nome, String descricao,
			String objetivo, Object objTipo) {
		CompPPMacroatividade compPPMacroatividade = new CompPPMacroatividade();
		compPPMacroatividade.setNome(nome);
		compPPMacroatividade.setDescricao(descricao);
		compPPMacroatividade.setObjetivo(objetivo);
	
		
	}

}

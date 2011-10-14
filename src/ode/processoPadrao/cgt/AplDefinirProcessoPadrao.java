package ode.processoPadrao.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgd.KProcessoDAO;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cgd.CompPPDAO;
import ode.processoPadrao.cgd.CompPPProcessoComplexoDAO;
import ode.processoPadrao.cgd.CompPPProcessoSimplesDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirProcessoPadrao extends AplCRUD<CompPP>{
	@Autowired
	private CompPPProcessoComplexoDAO compPPProcessoComplexoDAO;

	public void salvarProcessoComplexo(String nome, String descricao,
			String objetivo) {
		CompPPProcessoComplexo compPPcomplexo = new CompPPProcessoComplexo();
		compPPcomplexo.setNome(nome);
		compPPcomplexo.setDescricao(descricao);
		compPPcomplexo.setObjetivo(objetivo);

		compPPProcessoComplexoDAO.salvar(compPPcomplexo);
	}

	@Autowired
	private CompPPDAO compPPDAO;
	public void salvarCompPP(CompPP compPP) {
		compPPDAO.salvar(compPP);
	}


	@Autowired
	private CompPPProcessoSimplesDAO compPPProcessoSimplesDAO;

	@Autowired
	KProcessoDAO kProcessoDAO;

	public void salvarProcessoSimples(String nome, String descricao,
			String objetivo, Object objTipo) {
		CompPPProcessoSimples compPPsimples = new CompPPProcessoSimples();
		compPPsimples.setNome(nome);
		compPPsimples.setDescricao(descricao);
		compPPsimples.setObjetivo(objetivo);
		compPPsimples.setTipo(kProcessoDAO.recuperarPorId(((KProcesso) objTipo)
				.getId()));
		compPPProcessoSimplesDAO.salvar(compPPsimples);

	}

	public void salvarMacroatividade(String nome, String descricao,
			String objetivo, Object objTipo) {
		CompPPMacroatividade compPPMacroatividade = new CompPPMacroatividade();
		compPPMacroatividade.setNome(nome);
		compPPMacroatividade.setDescricao(descricao);
		compPPMacroatividade.setObjetivo(objetivo);

	}

	@Override
	public DAOBase<CompPP> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return null;
	}

}

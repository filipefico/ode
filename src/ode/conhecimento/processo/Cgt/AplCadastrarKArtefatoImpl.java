package ode.conhecimento.processo.Cgt;

import nucleo.comuns.excecao.NucleoExcecao;
import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cgd.KArtefatoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKArtefatoImpl extends
		NucleoAplCadastroBaseImpl<KArtefato> implements AplCadastrarKArtefato {

	@Autowired
	private KArtefatoDAO kArtefatoDAO;
	
	public KArtefatoDAO getkArtefatoDAO() {
		return kArtefatoDAO;
	}

	public void setkArtefatoDAO(KArtefatoDAO kArtefatoDAO) {
		this.kArtefatoDAO = kArtefatoDAO;
	}

	@Override
	public NucleoDAOBase<KArtefato> getNucleoDaoBase() {
		return kArtefatoDAO;
	}

}

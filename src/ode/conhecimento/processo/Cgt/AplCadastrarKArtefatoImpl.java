package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cgd.KArtefatoDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)

public class AplCadastrarKArtefatoImpl extends
		AplBaseImpl<KArtefato> implements AplCadastrarKArtefato {

	@Autowired
	private KArtefatoDAO kArtefatoDAO;
	
	public KArtefatoDAO getkArtefatoDAO() {
		return kArtefatoDAO;
	}
	
	public void setkArtefatoDAO(KArtefatoDAO kArtefatoDAO) {
		this.kArtefatoDAO = kArtefatoDAO;
	}

	@Override
	public DAOBase<KArtefato> getNucleoDaoBase() {
		return kArtefatoDAO;
	}

}

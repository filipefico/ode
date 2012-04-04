package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cgd.KArtefatoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarKArtefato")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKArtefato extends AplCRUD<KArtefato> {

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

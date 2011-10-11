package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cgd.KAtividadeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKAtividade extends
		AplCRUD<KAtividade> {

	@Autowired
	private KAtividadeDAO kAtividadeDAO;
	
	public KAtividadeDAO getkAtividadeDAO() {
		return kAtividadeDAO;
	}

	public void setkAtividadeDAO(KAtividadeDAO kAtividadeDAO) {
		this.kAtividadeDAO = kAtividadeDAO;
	}

	@Override
	public DAOBase<KAtividade> getNucleoDaoBase() {
		return kAtividadeDAO;
	}

}

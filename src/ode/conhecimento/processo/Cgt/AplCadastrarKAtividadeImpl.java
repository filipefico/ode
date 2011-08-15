package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cgd.KAtividadeDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKAtividadeImpl extends
		AplBaseImpl<KAtividade> implements AplCadastrarKAtividade {

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

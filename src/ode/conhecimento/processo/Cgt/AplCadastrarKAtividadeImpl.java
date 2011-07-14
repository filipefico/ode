package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cgd.KAtividadeDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKAtividadeImpl extends
		NucleoAplCadastroBaseImpl<KAtividade> implements AplCadastrarKAtividade {

	@Autowired
	private KAtividadeDAO kAtividadeDAO;
	
	public KAtividadeDAO getkAtividadeDAO() {
		return kAtividadeDAO;
	}

	public void setkAtividadeDAO(KAtividadeDAO kAtividadeDAO) {
		this.kAtividadeDAO = kAtividadeDAO;
	}

	@Override
	public NucleoDAOBase<KAtividade> getNucleoDaoBase() {
		return kAtividadeDAO;
	}

}

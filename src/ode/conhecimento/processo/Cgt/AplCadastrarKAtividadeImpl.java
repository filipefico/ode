package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cgd.KAtividadeDAO;

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
	protected void copiarValor(KAtividade objetoFonte, KAtividade objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}

	@Override
	public NucleoDAOBase<KAtividade> getNucleoDaoBase() {
		return kAtividadeDAO;
	}

}

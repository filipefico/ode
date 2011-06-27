package ode.conhecimento.processo.Cgt;


import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KProcedimento;
import ode.conhecimento.processo.Cgd.KProcedimentoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProcedimentoImpl extends
		NucleoAplCadastroBaseImpl<KProcedimento> implements AplCadastrarKProcedimento {

	@Autowired
	private KProcedimentoDAO kProcedimentoDAO;
	
	public KProcedimentoDAO getkProcedimentoDAO() {
		return kProcedimentoDAO;
	}

	public void setkProcedimentoDAO(KProcedimentoDAO kProcedimentoDAO) {
		this.kProcedimentoDAO = kProcedimentoDAO;
	}



	@Override
	protected void copiarValor(KProcedimento objetoFonte, KProcedimento objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}
	
	@Override
	public NucleoDAOBase<KProcedimento> getNucleoDaoBase() {
		return kProcedimentoDAO;
	}

}

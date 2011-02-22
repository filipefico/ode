package ode.conhecimento.processo.Cgt;


import ode.conhecimento.processo.Cdp.KProcedimento;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;

public class AplCadastrarKProcedimentoImpl extends
		NucleoAplCadastroBaseImpl<KProcedimento> implements AplCadastrarKProcedimento {

	@Override
	protected void copiarValor(KProcedimento objetoFonte, KProcedimento objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}

}

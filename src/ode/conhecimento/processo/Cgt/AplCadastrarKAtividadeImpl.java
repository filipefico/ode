package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KAtividade;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;

public class AplCadastrarKAtividadeImpl extends
		NucleoAplCadastroBaseImpl<KAtividade> implements AplCadastrarKAtividade {

	@Override
	protected void copiarValor(KAtividade objetoFonte, KAtividade objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}

}

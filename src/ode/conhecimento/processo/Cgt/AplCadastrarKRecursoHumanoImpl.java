package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import sun.security.krb5.KrbKdcReq;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;

public class AplCadastrarKRecursoHumanoImpl extends
		NucleoAplCadastroBaseImpl<KRecursoHumano> implements AplCadastrarKRecursoHumano {

	@Override
	protected void copiarValor(KRecursoHumano objetoFonte, KRecursoHumano objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}

}

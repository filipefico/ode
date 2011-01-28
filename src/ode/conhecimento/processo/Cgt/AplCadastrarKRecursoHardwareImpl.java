package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import sun.security.krb5.KrbKdcReq;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;

public class AplCadastrarKRecursoHardwareImpl extends
		NucleoAplCadastroBaseImpl<KRecursoHardware> implements AplCadastrarKRecursoHardware {

	@Override
	protected void copiarValor(KRecursoHardware objetoFonte, KRecursoHardware objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}

}

package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import ode.conhecimento.processo.Cdp.TipoKArtefato;

public class AplCadastrarTipoKArtefatoImpl extends
		NucleoAplCadastroBaseImpl<TipoKArtefato> implements
		AplCadastrarTipoKArtefato {

	@Override
	protected void copiarValor(TipoKArtefato objetoFonte,
			TipoKArtefato objetoDestino) {

		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());

	}

}

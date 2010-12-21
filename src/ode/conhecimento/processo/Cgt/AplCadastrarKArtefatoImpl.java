package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KArtefato;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;

public class AplCadastrarKArtefatoImpl extends
		NucleoAplCadastroBaseImpl<KArtefato> implements AplCadastrarKArtefato {

	@Override
	protected void copiarValor(KArtefato objetoFonte, KArtefato objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
		objetoDestino.setTipo(objetoFonte.getTipo());
		objetoDestino.setDependencias(objetoFonte.getDependencias());
		objetoDestino.setSubArtefatos(objetoFonte.getSubArtefatos());

	}

}

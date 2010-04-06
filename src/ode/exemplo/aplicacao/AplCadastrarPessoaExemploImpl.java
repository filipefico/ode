package ode.exemplo.aplicacao;

import ode.exemplo.dominio.PessoaExemplo;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;

public class AplCadastrarPessoaExemploImpl extends NucleoAplCadastroBaseImpl<PessoaExemplo> implements AplCadastrarPessoaExemplo{

	@Override
	protected void copiarValor(PessoaExemplo objetoFonte,
			PessoaExemplo objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setSobrenome(objetoFonte.getSobrenome());
		objetoDestino.setEmail(objetoFonte.getEmail());
		objetoDestino.setIdade(objetoFonte.getIdade());
		objetoDestino.setTelefone(objetoFonte.getTelefone());
	}

}

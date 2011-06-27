package ode.exemplo2.pessoa.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.exemplo2.pessoa.Cgd.PessoaExemploDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "aplCadastrarPessoaExemplo")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarPessoaExemploImpl extends
		NucleoAplCadastroBaseImpl<PessoaExemplo> implements
		AplCadastrarPessoaExemplo {
	
	@Autowired
	private PessoaExemploDAO pessoaExemploDAO;

	public PessoaExemploDAO getPessoaExemploDAO() {
		return pessoaExemploDAO;
	}

	public void setPessoaExemploDAO(PessoaExemploDAO pessoaExemploDao) {
		this.pessoaExemploDAO = pessoaExemploDao;
	}

	@Override
	protected void copiarValor(PessoaExemplo objetoFonte,
			PessoaExemplo objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setSobrenome(objetoFonte.getSobrenome());
		objetoDestino.setEmail(objetoFonte.getEmail());
		objetoDestino.setIdade(objetoFonte.getIdade());
		objetoDestino.setTelefone(objetoFonte.getTelefone());
	}
	
	protected PessoaExemplo alterarDados(PessoaExemplo objeto) throws NucleoRegraNegocioExcecao {
		// Executa a ação necessária antes de alterar os dados
//		antesAlterarDados(objeto);

		// Obtém o Principal Serviço persistido e altera seus dados
	//	PessoaExemplo objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());
		
	//	copiarValor(objeto, objetoPersistido);

		// Executa a ação necessária depois de alterar os dados
//		depoisAlterarDados(objeto);
		
		// Inclui o Principal Serviço
	getNucleoDaoBase().merge(objeto);


		//return objetoPersistido;
		return objeto;
	}

	@Override
	public NucleoDAOBase<PessoaExemplo> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return pessoaExemploDAO;
	}



}

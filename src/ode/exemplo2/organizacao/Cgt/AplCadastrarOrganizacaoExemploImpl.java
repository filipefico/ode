package ode.exemplo2.organizacao.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.exemplo2.organizacao.Cdp.OrganizacaoExemplo;
import ode.exemplo2.organizacao.Cgd.OrganizacaoExemploDAO;

import org.springframework.beans.factory.annotation.Autowired;

public class AplCadastrarOrganizacaoExemploImpl extends
		NucleoAplCadastroBaseImpl<OrganizacaoExemplo> implements
		AplCadastrarOrganizacaoExemplo {
	
	@Autowired
	private OrganizacaoExemploDAO organizacaoExemploDAO;

	public OrganizacaoExemploDAO getOrganizacaoExemploDAO() {
		return organizacaoExemploDAO;
	}

	public void setOrganizacaoExemploDAO(OrganizacaoExemploDAO organizacaoExemploDao) {
		this.organizacaoExemploDAO = organizacaoExemploDao;
	}

	@Override
	protected void copiarValor(OrganizacaoExemplo objetoFonte,
			OrganizacaoExemplo objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
	}
	
	protected OrganizacaoExemplo alterarDados(OrganizacaoExemplo objeto) throws NucleoRegraNegocioExcecao {
		// Executa a ação necessária antes de alterar os dados
//		antesAlterarDados(objeto);

		// Obtém o Principal Serviço persistido e altera seus dados
	//	OrganizacaoExemplo objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());
		
	//	copiarValor(objeto, objetoPersistido);

		// Executa a ação necessária depois de alterar os dados
//		depoisAlterarDados(objeto);
		
		// Inclui o Principal Serviço
	getNucleoDaoBase().merge(objeto);


		//return objetoPersistido;
		return objeto;
	}



}

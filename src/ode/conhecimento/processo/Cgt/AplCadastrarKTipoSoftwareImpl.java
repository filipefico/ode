package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.Cdp.KTipoSoftware;
import ode.conhecimento.processo.Cgd.KTipoSoftwareDAO;

import org.springframework.beans.factory.annotation.Autowired;

public class AplCadastrarKTipoSoftwareImpl extends
		NucleoAplCadastroBaseImpl<KTipoSoftware> implements
		AplCadastrarKTipoSoftware {
	
	@Autowired
	private KTipoSoftwareDAO tipoSoftwareDAO;

	public KTipoSoftwareDAO getTipoSoftwareDAO() {
		return tipoSoftwareDAO;
	}

	public void setTipoSoftwareDAO(KTipoSoftwareDAO tipoSoftwareDao) {
		this.tipoSoftwareDAO = tipoSoftwareDao;
	}

	@Override
	protected void copiarValor(KTipoSoftware objetoFonte,
			KTipoSoftware objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
	}
	
	protected KTipoSoftware alterarDados(KTipoSoftware objeto) throws NucleoRegraNegocioExcecao {
		// Executa a a��o necess�ria antes de alterar os dados
//		antesAlterarDados(objeto);

		// Obt�m o Principal Servi�o persistido e altera seus dados
	//	PessoaExemplo objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());
		
	//	copiarValor(objeto, objetoPersistido);

		// Executa a a��o necess�ria depois de alterar os dados
//		depoisAlterarDados(objeto);
		
		// Inclui o Principal Servi�o
	getNucleoDaoBase().merge(objeto);


		//return objetoPersistido;
		return objeto;
	}



}

package ode.conhecimento.processo.Cgt;

import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.Cdp.KTipoSoftware;
import ode.conhecimento.processo.Cgd.KTipoSoftwareDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKTipoSoftwareImpl extends
		NucleoAplCadastroBaseImpl<KTipoSoftware> implements
		AplCadastrarKTipoSoftware {
	
	@Autowired
	private KTipoSoftwareDAO kTipoSoftwareDAO;

	public KTipoSoftwareDAO getKTipoSoftwareDAO() {
		return kTipoSoftwareDAO;
	}

	public void setKTipoSoftwareDAO(KTipoSoftwareDAO kTipoSoftwareDao) {
		this.kTipoSoftwareDAO = kTipoSoftwareDao;
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

	@Override
	public NucleoDAOBase<KTipoSoftware> getNucleoDaoBase() {
		return kTipoSoftwareDAO;
	}

}

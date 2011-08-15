package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KTipoSoftware;
import ode.conhecimento.processo.Cgd.KTipoSoftwareDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKTipoSoftwareImpl extends
		AplBaseImpl<KTipoSoftware> implements
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
	public DAOBase<KTipoSoftware> getNucleoDaoBase() {
		return kTipoSoftwareDAO;
	}

}

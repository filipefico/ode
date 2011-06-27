package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cgd.KRecursoHardwareDAO;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKRecursoHardwareImpl extends
		NucleoAplCadastroBaseImpl<KRecursoHardware> implements AplCadastrarKRecursoHardware {

	@Autowired
	private KRecursoHardwareDAO kRecursoHardwareDAO;

	
	public KRecursoHardwareDAO getkRecursoHardwareDAO() {
		return kRecursoHardwareDAO;
	}

	public void setkRecursoHardwareDAO(KRecursoHardwareDAO kRecursoHardwareDAO) {
		this.kRecursoHardwareDAO = kRecursoHardwareDAO;
	}



	@Override
	protected void copiarValor(KRecursoHardware objetoFonte, KRecursoHardware objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}

	@Override
	public NucleoDAOBase<KRecursoHardware> getNucleoDaoBase() {
		return kRecursoHardwareDAO;
	}
	

}

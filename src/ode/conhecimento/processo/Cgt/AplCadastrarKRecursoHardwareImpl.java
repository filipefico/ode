package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nucleo.comuns.excecao.NucleoExcecao;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cgd.KRecursoHardwareDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

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
	public NucleoDAOBase<KRecursoHardware> getNucleoDaoBase() {
		return kRecursoHardwareDAO;
	}
	

}

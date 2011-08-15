package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cgd.KRecursoHardwareDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKRecursoHardwareImpl extends
		AplBaseImpl<KRecursoHardware> implements AplCadastrarKRecursoHardware {

	@Autowired
	private KRecursoHardwareDAO kRecursoHardwareDAO;

	
	public KRecursoHardwareDAO getkRecursoHardwareDAO() {
		return kRecursoHardwareDAO;
	}

	public void setkRecursoHardwareDAO(KRecursoHardwareDAO kRecursoHardwareDAO) {
		this.kRecursoHardwareDAO = kRecursoHardwareDAO;
	}

	@Override
	public DAOBase<KRecursoHardware> getNucleoDaoBase() {
		return kRecursoHardwareDAO;
	}
	

}

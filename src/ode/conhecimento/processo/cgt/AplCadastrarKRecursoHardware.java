package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KRecursoHardware;
import ode.conhecimento.processo.cgd.KRecursoHardwareDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKRecursoHardware extends
		AplCRUD<KRecursoHardware> {

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

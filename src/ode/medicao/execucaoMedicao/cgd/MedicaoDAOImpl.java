package ode.medicao.execucaoMedicao.cgd;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.execucaoMedicao.cdp.Medicao;

@Repository
@Transactional
public class MedicaoDAOImpl extends DAOBaseImpl<Medicao> implements MedicaoDAO{

	@Override
	@Transactional
	public void salvar(Medicao objeto) {
		super.salvar(objeto);
	}
	
	@Override
	@Transactional
	public Medicao atualizar(Medicao objeto) {
		return super.atualizar(objeto);
	}
	
	@Override
	@Transactional
	public void excluir(Medicao objeto) {
		super.excluir(objeto);
	}
	
}

package ode.medicao.planejamentoMedicao.cgd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.ValorReferencia;

@Repository
@Transactional
public class ValorReferenciaDAOImpl extends DAOBaseImpl<ValorReferencia> implements ValorReferenciaDAO{

	@Override
	@Transactional
	public void salvar(ValorReferencia objeto) {
		super.salvar(objeto);
	}
	
}

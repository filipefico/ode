package ode.medicao.planejamentoMedicao.cgd;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoProjeto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PlanoMedicaoProjetoDAOImpl extends DAOBaseImpl<PlanoMedicaoProjeto> implements PlanoMedicaoProjetoDAO{
	
	@Override
	@Transactional
	public void salvar(PlanoMedicaoProjeto objeto) {
		super.salvar(objeto);
	}
	
	@Override
	@Transactional
	public PlanoMedicaoProjeto atualizar(PlanoMedicaoProjeto objeto) {
		return super.atualizar(objeto);
	}
	
	@Override
	public void excluir(PlanoMedicaoProjeto objeto){
		entityManager.merge(objeto);
		super.excluir(objeto);
	}
}

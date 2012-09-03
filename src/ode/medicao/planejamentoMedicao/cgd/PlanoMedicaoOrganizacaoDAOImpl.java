package ode.medicao.planejamentoMedicao.cgd;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;

@Repository
@Transactional
public class PlanoMedicaoOrganizacaoDAOImpl extends DAOBaseImpl<PlanoMedicaoOrganizacao> implements PlanoMedicaoOrganizacaoDAO{
	
	@Override
	@Transactional
	public void salvar(PlanoMedicaoOrganizacao objeto) {
		super.salvar(objeto);
	}
	
	@Override
	@Transactional
	public PlanoMedicaoOrganizacao atualizar(PlanoMedicaoOrganizacao objeto) {
		return super.atualizar(objeto);
	}
	
	@Override
	public void excluir(PlanoMedicaoOrganizacao objeto){
		entityManager.merge(objeto);
		super.excluir(objeto);
	}
}

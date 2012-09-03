package ode.medicao.planejamentoMedicao.cgd;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;

@Repository
@Transactional
public class MedidaPlanoMedicaoDAOImpl extends DAOBaseImpl<MedidaPlanoMedicao> implements MedidaPlanoMedicaoDAO{

	@Override
	public MedidaPlanoMedicao recuperarPorId(Long id) {
		return (MedidaPlanoMedicao) getEntityManager().createQuery("select mpm from MedidaPlanoMedicao left join fetch mpm.objetivosMedicao where mpm.id = "+id).getSingleResult();
	}
	
	@Override
	@Transactional
	public MedidaPlanoMedicao atualizar(MedidaPlanoMedicao objeto) {
		return super.atualizar(objeto);
	}
}

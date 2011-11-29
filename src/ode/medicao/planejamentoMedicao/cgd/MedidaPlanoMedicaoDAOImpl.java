package ode.medicao.planejamentoMedicao.cgd;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
@Repository
public class MedidaPlanoMedicaoDAOImpl extends DAOBaseImpl<MedidaPlanoMedicao> implements MedidaPlanoMedicaoDAO{

	@Override
	public MedidaPlanoMedicao recuperarPorId(Long id) {
		return (MedidaPlanoMedicao) getEntityManager().createQuery("select mpm from MedidaPlanoMedicao left join fetch mpm.objetivosMedicao where mpm.id = "+id).getSingleResult();
	}
}

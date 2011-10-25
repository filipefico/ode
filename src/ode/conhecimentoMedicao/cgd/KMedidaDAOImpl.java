package ode.conhecimentoMedicao.cgd;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KMedidaDAOImpl extends DAOBaseImpl<KMedida> implements KMedidaDAO{

	@Override
	public KMedida recuperarPorId(Long id) {
		KMedida objeto = null;
		objeto = (KMedida)getEntityManager().createQuery("select med from KMedida med, KDefinicaoOperacionalMedida left join fetch med.medidasCorrelatas left join fetch med.derivadaDe left join fetch med.definicoesMedida where med.id = "+id).getSingleResult();

		return objeto;
	}
}

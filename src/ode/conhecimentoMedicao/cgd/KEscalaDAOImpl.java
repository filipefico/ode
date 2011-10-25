package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.TipoEscala;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KEscalaDAOImpl extends DAOBaseImpl<KEscala> implements KEscalaDAO{

	@Override
	public Collection<KEscala> recuperarPorTipo(TipoEscala tipo) {
		return getEntityManager().createQuery("select escala from KEscala as escala where escala.tipo="+tipo.ordinal()).getResultList();
	}

	@Override
	public Collection<KMedida> getMedidasRelacionadas(KEscala objeto) {
		return getEntityManager().createQuery("select medida from KMedida medida join medida.escala escala where escala.id="+objeto.getId()).getResultList();
	}

}

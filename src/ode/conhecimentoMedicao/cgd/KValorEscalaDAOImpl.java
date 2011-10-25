package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KValorEscalaDAOImpl extends DAOBaseImpl<KValorEscala> implements KValorEscalaDAO{

	@Override
	public Collection<KEscala> getRelacaoComEscala(KValorEscala objeto) {
		return getEntityManager().createQuery("select escala from KEscala escala join escala.valores valor where valor.id="+objeto.getId()).getResultList();
	}

}

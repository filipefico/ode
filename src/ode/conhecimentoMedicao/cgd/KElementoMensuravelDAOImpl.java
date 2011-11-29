package ode.conhecimentoMedicao.cgd;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KElementoMensuravelDAOImpl extends
		DAOBaseImpl<KElementoMensuravel> implements KElementoMensuravelDAO {

	@Override
	public Collection<KMedida> getRelacionamentoMedida(
			KElementoMensuravel objeto) {
		return getEntityManager().createQuery("select med from KMedida med join med.propriedadeMedida prop where prop.id="+objeto.getId()).getResultList();
	}

}

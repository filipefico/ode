package ode.conhecimento.requisito.cgd;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.requisito.cdp.KTipoRequisito;

import org.springframework.stereotype.Repository;

@Repository(value="kTipoRequisitoDao")
public class KTipoRequisitoDAOHibernate extends DAOBaseImpl<KTipoRequisito> implements KTipoRequisitoDAO{

}

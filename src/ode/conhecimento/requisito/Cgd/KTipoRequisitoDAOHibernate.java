package ode.conhecimento.requisito.Cgd;

import org.springframework.stereotype.Repository;

import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cgd.KTipoRequisitoDAO;
import ode.nucleo.cgd.NucleoDAOBaseHibernate;

@Repository(value="kTipoRequisitoDao")
public class KTipoRequisitoDAOHibernate extends NucleoDAOBaseHibernate<KTipoRequisito> implements KTipoRequisitoDAO{

}

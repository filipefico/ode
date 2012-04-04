package ode.conhecimento.requisito.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cdp.TipoRequisito;

public interface CategoriaRequisitoDAO extends DAOBase<CategoriaRequisito> {

	public Collection<CategoriaRequisito> obterCategoriaPorTipo (TipoRequisito tipoRequisito);

}

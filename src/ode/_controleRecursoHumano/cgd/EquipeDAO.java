package ode._controleRecursoHumano.cgd;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._infraestruturaBase.cgd.DAOBase;

public interface EquipeDAO extends DAOBase<Equipe> {

	public Equipe obterEquipePorProjeto(Long id);

}

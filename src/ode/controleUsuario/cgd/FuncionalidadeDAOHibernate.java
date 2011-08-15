package ode.controleUsuario.cgd;

import java.util.Collection;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

import org.springframework.stereotype.Repository;

@Repository
public class FuncionalidadeDAOHibernate extends DAOBaseHibernate<Funcionalidade>
		implements FuncionalidadeDAO {

	/**
	 * Recupera subfuncionalidades de uma funcionalidade.
	 * @param funcionalidadePai Funcionalidade a qual se deseja recuperar suas subfuncionalidades.
	 * @return Subfuncionalidades recuperadas.
	 */
	@SuppressWarnings("unchecked")
	public Collection<Funcionalidade> recuperarSubFuncionalidadesPorFuncionalidade(Funcionalidade funcionalidadePai) {
		Collection<Funcionalidade> funcionalidades = getHibernateTemplate().findByNamedParam(
				"from Funcionalidade subfuncionalidade " + " where subfuncionalidade.funcionalidadePai = :funcionalidadePai order by pos",
				new String[] { "funcionalidadePai" }, new Object[] { funcionalidadePai });
		return funcionalidades;
	}
	
	/**
	 * Recupera funcionalidades raíz do sistema.
	 * @return Funcionalidades recuperadas.
	 */
	@SuppressWarnings("unchecked")
	public Collection<Funcionalidade> recuperarFuncionalidadesRaiz() {
		Collection<Funcionalidade> funcionalidades = getHibernateTemplate().find("from Funcionalidade f where f.funcionalidadePai is null order by pos");

		return funcionalidades;
	}
	
	
}

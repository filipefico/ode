package ode.conhecimento.processo.Cgd;

import ode.conhecimento.processo.Cdp.KRecurso;

import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KRecursoDAO extends NucleoDAOBase<KRecurso> {

	public KRecurso obterPorId (String parId);
}

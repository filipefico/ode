package ode.problema.cgd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;



public interface KCausaDAO extends DAOBase<KCausa> {
	
Collection<KCausa> recuperarCausaPorProblema(KProblema kproblema);
}

package ode.problema.cgd;

import java.util.Collection;
import java.util.List;

import org.zkoss.zul.Listbox;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import ode.problema.cdp.KSolucao;




public interface KSolucaoDAO extends DAOBase<KSolucao> {
	Collection<KSolucao> recuperarSolucaoPorCausa(KCausa kcausa);
	
	
}

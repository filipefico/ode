package ode._controleFerramenta.cgd;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._infraestruturaBase.cgd.DAOBase;

public interface FerramentaSoftwareDAO extends DAOBase<FerramentaSoftware> {

	Collection<FerramentaSoftware> recuperarPorKFerramentaSoftware(Long idKFerramentaSoftware);

}

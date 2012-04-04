package ode.gerenciaRequisitos.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cdp.GeradorIdentificador;

public interface GeradorIdentificadorDAO extends DAOBase<GeradorIdentificador>{
	public boolean existeGerador (Projeto projeto);
	
	public String getIdentificadorFuncional (Projeto projeto);
	
	public String getIdentificadorNaoFuncional (Projeto projeto);
	
	public String getIdentificadorRegraDeNegocio (Projeto projeto);

}

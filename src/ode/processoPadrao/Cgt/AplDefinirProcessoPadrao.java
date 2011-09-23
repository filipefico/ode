package ode.processoPadrao.Cgt;

import ode.nucleo.crud.cgt.AplBase;
import ode.processoPadrao.Cdp.CompPP;

public interface AplDefinirProcessoPadrao extends AplBase<CompPP>{

	void salvarProcessoComplexo(String nome, String descricao, String objetivo);

	void salvarProcessoSimples(String nome, String descricao, String objetivo,
			Object objTipo);

	void salvarMacroatividade(String nome, String descricao, String objetivo,
			Object objTipo);

}

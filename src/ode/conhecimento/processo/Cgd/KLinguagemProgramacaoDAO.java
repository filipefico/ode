package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KLinguagemProgramacao;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KLinguagemProgramacaoDAO extends NucleoDAOBase<KLinguagemProgramacao>{
	
	public void salvar(KLinguagemProgramacao kLinguagemProgramacao);

	public void excluir(KLinguagemProgramacao kLinguagemProgramacao);

	public Collection<KLinguagemProgramacao> recuperarTodos();
}

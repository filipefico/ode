package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.CompPPProcessoComplexo;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface CompPPProcessoComplexoDAO  extends NucleoDAOBase<CompPPProcessoComplexo>{
	
	public void salvar(CompPPProcessoComplexo parProcessoPadraoGeral);
    
    public void excluir(CompPPProcessoComplexo parProcessoPadraoGeral);
    
    public Collection<CompPPProcessoComplexo> recuperarTodos();
    
    public CompPPProcessoComplexo obterPorNome(String parNome);
    
    /*public List obterProcessosDefinidos();
    
    public List obterProcessosEspecializados();
    
    public List obterProcessosNaoEspecializados();
    
    public List obterProcessosEspecializadosFinalizadosPorProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem);

    public List obterKProcessosSimplesObrigatorios(CompPPProcessoComplexo parProcessoOrigem);

    public CompPPProcessoComplexo obterPorId(CompPP parCompPP);*/	
}

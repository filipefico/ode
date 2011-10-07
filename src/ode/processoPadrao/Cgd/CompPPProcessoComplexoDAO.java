package ode.processoPadrao.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;

public interface CompPPProcessoComplexoDAO  extends DAOBase<CompPPProcessoComplexo>{
    
    public CompPPProcessoComplexo obterPorNome(String parNome);
    
    /*public List obterProcessosDefinidos();
    
    public List obterProcessosEspecializados();
    
    public List obterProcessosNaoEspecializados();
    
    public List obterProcessosEspecializadosFinalizadosPorProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem);

    public List obterKProcessosSimplesObrigatorios(CompPPProcessoComplexo parProcessoOrigem);

    public CompPPProcessoComplexo obterPorId(CompPP parCompPP);*/	
}

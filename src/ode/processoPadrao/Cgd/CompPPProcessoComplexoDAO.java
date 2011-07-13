package ode.processoPadrao.Cgd;

import ode.nucleo.cgd.NucleoDAOBase;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;

public interface CompPPProcessoComplexoDAO  extends NucleoDAOBase<CompPPProcessoComplexo>{
    
    public CompPPProcessoComplexo obterPorNome(String parNome);
    
    /*public List obterProcessosDefinidos();
    
    public List obterProcessosEspecializados();
    
    public List obterProcessosNaoEspecializados();
    
    public List obterProcessosEspecializadosFinalizadosPorProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem);

    public List obterKProcessosSimplesObrigatorios(CompPPProcessoComplexo parProcessoOrigem);

    public CompPPProcessoComplexo obterPorId(CompPP parCompPP);*/	
}

package ode.processoPadrao.Cgd;

import ode.nucleo.crud.cgd.DAOBase;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;

public interface CompPPProcessoComplexoDAO  extends DAOBase<CompPPProcessoComplexo>{
    
    public CompPPProcessoComplexo obterPorNome(String parNome);
    
    /*public List obterProcessosDefinidos();
    
    public List obterProcessosEspecializados();
    
    public List obterProcessosNaoEspecializados();
    
    public List obterProcessosEspecializadosFinalizadosPorProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem);

    public List obterKProcessosSimplesObrigatorios(CompPPProcessoComplexo parProcessoOrigem);

    public CompPPProcessoComplexo obterPorId(CompPP parCompPP);*/	
}

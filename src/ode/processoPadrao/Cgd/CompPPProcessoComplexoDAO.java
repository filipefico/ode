package ode.processoPadrao.Cgd;

import ode.processoPadrao.Cdp.CompPPProcessoComplexo;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface CompPPProcessoComplexoDAO  extends NucleoDAOBase<CompPPProcessoComplexo>{
	
	public void salvar(CompPPProcessoComplexo parProcessoPadraoGeral);
    
    public void excluir(CompPPProcessoComplexo parProcessoPadraoGeral);
    
    /*public List obterTodos();
    
    public CompPPProcessoComplexo obterPorNome(String parNome);
    
    public List obterProcessosDefinidos();
    
    public List obterProcessosEspecializados();
    
    public List obterProcessosNaoEspecializados();
    
    public List obterProcessosEspecializadosFinalizadosPorProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem);

    public List obterKProcessosSimplesObrigatorios(CompPPProcessoComplexo parProcessoOrigem);

    public CompPPProcessoComplexo obterPorId(CompPP parCompPP);*/	
}

package ode.conhecimento.processo.Cgd;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.nucleo.crud.cgd.DAOBase;

public interface KAtividadeDAO extends DAOBase<KAtividade>{
    
    /** Obtém as macro-atividades do Conhecimento Atividade. */
    //public List obterMacroAtividades();
    
    /** Obtém as macro-atividades de um Processo Padrão. */
    //public List obterMacroAtividadesPorProcessoPadrao(KProcessoPadrao parKProcessoPadrao);
    
    /** Obtém as macro-atividades do Conhecimento Atividade que possuem relacionamento com o 
     * kProcesso desejado. */
   // public List obterMacroAtividadesPorKProcesso(KProcesso parProc);
    
    /** Obtem as KAtividades dado um KProcesso*/
    //public List obterPorKProcesso( KProcesso parProc);
}

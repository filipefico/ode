package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KAtividade;

public interface KAtividadeDAO extends NucleoDAOBase<KAtividade>{
    
    /** Obt�m as macro-atividades do Conhecimento Atividade. */
    //public List obterMacroAtividades();
    
    /** Obt�m as macro-atividades de um Processo Padr�o. */
    //public List obterMacroAtividadesPorProcessoPadrao(KProcessoPadrao parKProcessoPadrao);
    
    /** Obt�m as macro-atividades do Conhecimento Atividade que possuem relacionamento com o 
     * kProcesso desejado. */
   // public List obterMacroAtividadesPorKProcesso(KProcesso parProc);
    
    /** Obtem as KAtividades dado um KProcesso*/
    //public List obterPorKProcesso( KProcesso parProc);
}

package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KAtividade;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KAtividadeDAO extends NucleoDAOBase<KAtividade>{
	
	 /** Salva o KAtividade */
    public void salvar(KAtividade parKAtividade);
    
    /** Exclui o KAtividade */
    public void excluir(KAtividade parKAtividade);
    
    /** Obtem todas as KAtividade */
    public Collection<KAtividade> recuperarTodos();
    
    /** Obt�m uma atividade pelo seu id */
    public KAtividade recuperarPorId(Long parId);
    
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

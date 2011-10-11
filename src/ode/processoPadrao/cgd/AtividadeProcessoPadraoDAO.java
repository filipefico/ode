package ode.processoPadrao.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;

public interface AtividadeProcessoPadraoDAO extends DAOBase<AtividadeProcessoPadrao> {
	    
	  //  public List obterMacroAtividades(CompPPProcessoSimples parPPE);
	    
	   // public List obterDependentes(AtividadeProcessoPadrao parAtividade);
	    
	   // public boolean possuiSubAtividadeObrigatoria(AtividadeProcessoPadrao parAtividade);
	    
	  //  public List obterPreAtividadesPelaAtividadeOrigem(AtividadeProcessoPadrao parAtividade);
	    
	    /** Quando est� se copiando um subProcesso para reutiliza��o, utiliza-se esse m�todo para se obter as pr�-atividades de uma 
	     atividade copiada dada a atividade original e a c�pia do subProcesso*/
	    // public List obterPreAtividadesNoSubProcessoPelaAtividadeOrigem(AtividadeProcessoPadrao parAtividadeOriginal, CompPPProcessoSimples parSubProcesso);
	        
	    /** Dado um subProcesso e uma atividade, obt�m as atividades deste processo que s�o pr�s da atividade passada como par�metro (olhando a ativiade de origem) */
	   // public List obterPreAtividadePorSubProcessoEAtividadeOrigem(AtividadeProcessoPadrao parAtividade, CompPPProcessoSimples parSubProcesso);
	    
	    /** Dado um subProcesso, obt�m as suas atividades que s�o pr� de atividades de outros subProcessos */
	   // public List obterAtividadeComoPreDeAtividadesDeOutroSubProcesso(CompPPProcessoSimples parSubProcesso);
	    
	    /** Dado um subProcesso e uma atividade, obt�m as atividades deste processo que s�o pr�s da atividade passada como par�metro */
	   // public List obterPreAtividadePorSubProcesso(AtividadeProcessoPadrao parAtividade, CompPPProcessoSimples parSubProcesso);
	    
	    /** Dada uma KAtividade, retorna a atividade desse tipo (se houver) contida no subProcesso */
	   // public AtividadeProcessoPadrao obterAtividadePorKAtividade(KAtividade parKAtividade, CompPPProcessoSimples parSubProcesso);
}

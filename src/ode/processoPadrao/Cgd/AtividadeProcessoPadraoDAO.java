package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.AtividadeProcessoPadrao;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface AtividadeProcessoPadraoDAO extends NucleoDAOBase<AtividadeProcessoPadrao> {

		public void salvar(AtividadeProcessoPadrao parAtividadeProcessoPadrao);
	    
	    public void excluir(AtividadeProcessoPadrao parAtividadeProcessoPadrao);
	    
	    public Collection<AtividadeProcessoPadrao> recuperarTodos();
	    
	  //  public List obterMacroAtividades(CompPPProcessoSimples parPPE);
	    
	   // public List obterDependentes(AtividadeProcessoPadrao parAtividade);
	    
	   // public boolean possuiSubAtividadeObrigatoria(AtividadeProcessoPadrao parAtividade);
	    
	  //  public List obterPreAtividadesPelaAtividadeOrigem(AtividadeProcessoPadrao parAtividade);
	    
	    /** Quando está se copiando um subProcesso para reutilização, utiliza-se esse método para se obter as prá-atividades de uma 
	     atividade copiada dada a atividade original e a cópia do subProcesso*/
	    // public List obterPreAtividadesNoSubProcessoPelaAtividadeOrigem(AtividadeProcessoPadrao parAtividadeOriginal, CompPPProcessoSimples parSubProcesso);
	        
	    /** Dado um subProcesso e uma atividade, obtém as atividades deste processo que são prés da atividade passada como parâmetro (olhando a ativiade de origem) */
	   // public List obterPreAtividadePorSubProcessoEAtividadeOrigem(AtividadeProcessoPadrao parAtividade, CompPPProcessoSimples parSubProcesso);
	    
	    /** Dado um subProcesso, obtém as suas atividades que são pré de atividades de outros subProcessos */
	   // public List obterAtividadeComoPreDeAtividadesDeOutroSubProcesso(CompPPProcessoSimples parSubProcesso);
	    
	    /** Dado um subProcesso e uma atividade, obtém as atividades deste processo que são prés da atividade passada como parâmetro */
	   // public List obterPreAtividadePorSubProcesso(AtividadeProcessoPadrao parAtividade, CompPPProcessoSimples parSubProcesso);
	    
	    /** Dada uma KAtividade, retorna a atividade desse tipo (se houver) contida no subProcesso */
	   // public AtividadeProcessoPadrao obterAtividadePorKAtividade(KAtividade parKAtividade, CompPPProcessoSimples parSubProcesso);
}

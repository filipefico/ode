package ode.processoPadrao.Cdp;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



import ode.conhecimento.processo.Cdp.KModeloCicloVida;
import ode.nucleo.cdp.ObjetoPersistente;

@Entity
public class ModeloCicloVidaProcessoPadrao extends ObjetoPersistente{

	private static final long serialVersionUID = -1966924825919978194L;
	/** Processo Padrão Específico associado */
    private CompPPProcessoSimples processosEspecifico;
    /** KModeloCicloVida referente */
    private KModeloCicloVida  kModeloCicloVida; 
    /** Combinacoes para o ModeloCicloVida */
    private Set<CombinacaoPP> combinacoes;
    

    public ModeloCicloVidaProcessoPadrao() {
        this.setCombinacoes(new HashSet<CombinacaoPP>());
    }
    
    
    /** Obtém o Processo Padrão Específico.
         *   not-null = "true"
       */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoSimples.class)
    public CompPPProcessoSimples getProcessosEspecifico() {
        return processosEspecifico;
    }

    public void setProcessosEspecifico(CompPPProcessoSimples parProcessosEspecifico) {
        this.processosEspecifico = parProcessosEspecifico;
    }
    
    
    /** Obtém o KModeloCicloVida refernete a este.
     *   not-null = "false"
     *
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KModeloCicloVida.class)
    public KModeloCicloVida getKModeloCicloVida() {
        return kModeloCicloVida;
    }

    public void setKModeloCicloVida(KModeloCicloVida parKModeloCicloVida) {
        this.kModeloCicloVida = parKModeloCicloVida;
    }
    
    
    /** Obtém as Combinações.
     *
     *    inverse = "true"
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = CombinacaoPP.class,fetch=FetchType.LAZY)
    public Set<CombinacaoPP> getCombinacoes() {
        return combinacoes;
    }

    public void setCombinacoes(Set<CombinacaoPP> parCombinacoes) {
        this.combinacoes = parCombinacoes;
    }

    
    public String toString(){
        //return this.nome;
        return kModeloCicloVida.toString();
    }

    
}

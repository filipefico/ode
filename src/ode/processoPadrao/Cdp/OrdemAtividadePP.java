package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import nucleo.comuns.persistencia.ObjetoPersistente;

@Entity
public class OrdemAtividadePP extends ObjetoPersistente{

	private static final long serialVersionUID = 7719981952306222807L;
	/** Combinação a que se refere */
    private CombinacaoPP combinacao;
    /** AtividadeProcessoPadrao a que se refere */
    private AtividadeProcessoPadrao atividade;
    /** Ordem */
    private int ordem;

    public OrdemAtividadePP() {
    }
    
    
    /** Obtém a Combinacao.
     *   not-null = "true"
        */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CombinacaoPP.class)
    public CombinacaoPP getCombinacao() {
        return combinacao;
    }

    public void setCombinacao(CombinacaoPP parCombinacao) {
        this.combinacao = parCombinacao;
    }
    
    
    /** Obtém a AtividadeProcessoPadrao.
     *   not-null = "true"
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = AtividadeProcessoPadrao.class)
    public AtividadeProcessoPadrao getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeProcessoPadrao atividade) {
        this.atividade = atividade;
    }
    
    
    /** Obtém a ordem
     *  @hibernate.property
     */
    @Column
    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    
	
}

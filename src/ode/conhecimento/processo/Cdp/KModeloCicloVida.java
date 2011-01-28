package ode.conhecimento.processo.Cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity
public class KModeloCicloVida extends Conhecimento{

	private static final long serialVersionUID = -8217469721136943061L;
	/** Ordem das combinações que compoe o Modelo de Ciclo de Vida */
    private Set<KOrdemCombinacao> ordemCombinacoes;
    
    public KModeloCicloVida() {
        setOrdemCombinacoes(new HashSet<KOrdemCombinacao>());
    }

    
    /** Obtém as ordens das combinacoes
     *    inverse = "true"
     */
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KOrdemCombinacao.class,fetch=FetchType.LAZY)
    public Set<KOrdemCombinacao> getOrdemCombinacoes() {
        return ordemCombinacoes;
    }

    public void setOrdemCombinacoes(Set<KOrdemCombinacao> ordemCombinacoes) {
        this.ordemCombinacoes = ordemCombinacoes;
    }
    
    public String toString(){
        return this.getNome();
    }
	
}

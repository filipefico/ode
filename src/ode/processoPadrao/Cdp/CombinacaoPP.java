package ode.processoPadrao.Cdp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nucleo.comuns.persistencia.ObjetoPersistente;

import ode.conhecimento.processo.Cdp.KCombinacao;

//@Entity(name = "padr_combinacao")
@Entity
@Table(name="ModeloCicloVidaProcessoPadrao")
public class CombinacaoPP extends ObjetoPersistente{

	private static final long serialVersionUID = 6574391510598699596L;
	/** ModeloCicloVida referente */
    private ModeloCicloVidaProcessoPadrao modeloCicloVida;
    /** OrdensAtividades da Combinacao */
    private Set<OrdemAtividadePP> ordensAtividades;
    /** Oredem da combinação */
    private int ordem;
    /** Tipo de combinacao */
    private KCombinacao tipo;
    

    public CombinacaoPP() {
        this.setOrdensAtividades(new HashSet<OrdemAtividadePP>());
    }
    
    /** Obtém o Modelo de Ciclo de Vida.
     *   not-null = "true"
    */
    
    @ManyToOne(targetEntity = ModeloCicloVidaProcessoPadrao.class)
    @JoinColumn(nullable = false)
    public ModeloCicloVidaProcessoPadrao getModeloCicloVida() {
        return modeloCicloVida;
    }

    public void setModeloCicloVida(ModeloCicloVidaProcessoPadrao parModeloCicloVida) {
        this.modeloCicloVida = parModeloCicloVida;
    }
        
    /** Obtém as OrdensAtividades.
    *@hibernate.set
     *    inverse = "true"
         */
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = OrdemAtividadePP.class,fetch=FetchType.LAZY)
    public Set<OrdemAtividadePP> getOrdensAtividades() {
        return ordensAtividades;
    }

    public void setOrdensAtividades(Set<OrdemAtividadePP> parOrdensAtividades) {
        this.ordensAtividades = parOrdensAtividades;
    }
    
    /** Obtém a ordem
    */
    @Column
    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    
    
    /** Obtém o tipo (KCombinacao) da combinacao
     *   not-null = "true"
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KCombinacao.class)
    public KCombinacao getTipo() {
        return tipo;
    }

    public void setTipo(KCombinacao parTipo) {
        this.tipo = parTipo;
    }
    
    
    public ArrayList<AtividadeProcessoPadrao> obterAtividades(){
        ArrayList<AtividadeProcessoPadrao> locAtivs = new ArrayList<AtividadeProcessoPadrao>();
        
        Iterator<OrdemAtividadePP> it = ordensAtividades.iterator();
        while(it.hasNext()){
            OrdemAtividadePP locOrdAtiv = (OrdemAtividadePP)it.next();
            locAtivs.add(locOrdAtiv.getAtividade());
        }
        
        
        return locAtivs;
    }
    
    public String toString(){
        return this.tipo.toString();
    }
}

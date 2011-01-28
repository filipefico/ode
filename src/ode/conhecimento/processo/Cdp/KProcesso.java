package ode.conhecimento.processo.Cdp;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ode.conhecimento.principal.Cdp.Conhecimento;


/** Representa os conhecimentos sobre Processos do ambiente ODE.
 *
 */

@Entity
public class KProcesso extends Conhecimento {
	
	private static final long serialVersionUID = -1893120551022720680L;
	private String proposito;
    private String sigla;
    private boolean ehEngenharia;
    private Set<KTipoInteracao> kTipoInteracao;
    //Indica qual a KCategoria
    private KCategoriaProcesso categoria;
    //Indica quais são os KResultadoProcesso's
    private Set<KResultadoProcesso> kResultadoProcesso;
    
    public KProcesso() {
    }
	
	 /**
     * Obtém o propósito do KProcesso.
     */
    @Column(length=500)
    public String getProposito() {
        return proposito;
    }
    
    public void setProposito(String parProposito) {
        this.proposito = parProposito;
    }
    
    /**
     * Obtém a sigla do KProcesso.
     */
    @Column(length=10)
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String parSigla) {
        this.sigla = parSigla;
    }
    
     
    /**
     * Diz se o KProcesso é ou não de engenharia.
     * @hibernate.property
     */
    @Column
    public boolean isEhEngenharia() {
        return ehEngenharia;
    }
    
    public void setEhEngenharia(boolean parEhEngenharia) {
        this.ehEngenharia = parEhEngenharia;
    }
    
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KTipoInteracao.class,fetch=FetchType.LAZY)
    public Set<KTipoInteracao> getKTipoInteracao(){
        return kTipoInteracao;
    }
    
    public void setKTipoInteracao(Set<KTipoInteracao> parKTI){
        kTipoInteracao = parKTI;
    }
    
    
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KCategoriaProcesso.class)
    @JoinColumn(nullable = false)
    public KCategoriaProcesso getCategoria() {
        return categoria;
    }
    
    public void setCategoria(KCategoriaProcesso parCategoria) {
        this.categoria = parCategoria;
    }
    

    
    /**
     * Obtem os resultados esperados do processo
     *
     *@hibernate.set
     *    inverse = “true”
     */
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KResultadoProcesso.class)
    public Set<KResultadoProcesso> getKResultadoProcesso(){
        return kResultadoProcesso;
    }
    
    public void setKResultadoProcesso(Set<KResultadoProcesso> parKRP){
        kResultadoProcesso = parKRP;
    }
    
}

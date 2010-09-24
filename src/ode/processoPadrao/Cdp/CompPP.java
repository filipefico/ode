package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


import nucleo.comuns.persistencia.ObjetoPersistente;

@Entity
public class CompPP extends ObjetoPersistente {

	private static final long serialVersionUID = -6201008355921694079L;
	/** Nome do Processo */
    private String nome;
    /** Indica se a definição do processo foi encerrada */
    private boolean ehDefinido;
    /** Descrição do Processo */
    private String descricao;
    /** Requisito */
    private RequisitoCompPP requisitoCompPP;
    /** Componente base*/
    private CompPP compPPBase;

    private InterfaceCompPP interfaceCompPP;
    
    
    public CompPP() {

    }
    
    
    /** Verifica se um Processo já teve sua definição encerrada
     *  @hibernate.property
     */
    @Column
    public boolean isEhDefinido() {
        return ehDefinido;
    }
    
    public void setEhDefinido(boolean parEhDefinido) {
        this.ehDefinido = parEhDefinido;
    }
    
  
    @Column(nullable=false, length=100)
    public String getNome() {
        return nome;
    }
    
    public void setNome(String parNome) {
        this.nome = parNome;
    }
    
    @Column(nullable=true, length=300)
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String parDescricao) {
        this.descricao = parDescricao;
    }

    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = InterfaceCompPP.class)
    public InterfaceCompPP getInterfaceCompPP() {
        return interfaceCompPP;
    }

    public void setInterfaceCompPP(InterfaceCompPP interfaceCompPP) {
        this.interfaceCompPP = interfaceCompPP;
    }


    public String toString(){
        if(this.interfaceCompPP != null)
            return this.interfaceCompPP.getNome();
        else
            return null;
    }

     /**
     *
     * @hibernate.many-to-one
     *   column = "idorequisitocomppp"
     *   not-null = "false"
     *   class = "Ode.processoPadrao.cdp.RequisitoCompPP"
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = RequisitoCompPP.class)
    public RequisitoCompPP getRequisitoCompPP() {
        return requisitoCompPP;
    }

    public void setRequisitoCompPP(RequisitoCompPP requisitoCompPP) {
        this.requisitoCompPP = requisitoCompPP;
    }

    /**
     *
     * @hibernate.many-to-one
     *   column = "idocompppbase"
     *   not-null = "false"
     *   class = "Ode.processoPadrao.cdp.CompPP"
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPP.class)
    public CompPP getCompPPBase() {
        return compPPBase;
    }

    public void setCompPPBase(CompPP compPPBase) {
        this.compPPBase = compPPBase;
    }
	
}

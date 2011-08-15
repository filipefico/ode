package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode.nucleo.cdp.ObjetoPersistente;



@Entity
public class InterfaceCompPP extends ObjetoPersistente {


	private static final long serialVersionUID = -1819692208262943582L;
		String nome;
	    String objetivo;
	    String descricao;
	    CompPP compPP;

	    public InterfaceCompPP(String nome, String objetivo, String descricao, CompPP compPP) {
	        this.nome = nome;
	        this.objetivo = objetivo;
	        this.descricao = descricao;
	        this.compPP = compPP;
	    }

	    public InterfaceCompPP() {
	    }

	    @ManyToOne (cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPP.class)
	    public CompPP getCompPP() {
	        return compPP;
	    }

	    public void setCompPP(CompPP compPP) {
	        this.compPP = compPP;
	    }

	    /** Obtém o a descricao da interface
	     *  
	     */
	    @Column(nullable=false, length = 500)
	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    /** Obtém o nome da interface
	     *  
	     */
	    @Column(nullable=false, length = 80)
	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    /** Obtém o objetivo da interface
	     * 
	     */
	    @Column(nullable=false, length = 500)
	    public String getObjetivo() {
	        return objetivo;
	    }

	    public void setObjetivo(String objetivo) {
	        this.objetivo = objetivo;
	    }
	
}

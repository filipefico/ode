package ode.conhecimento.principal.cdp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.processoPadrao.cdp.CompPPProcessoSimples;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Conhecimento extends ObjetoPersistente {

	private static final long serialVersionUID = -2477658761966661172L;

	/** Nome do conhecimento. */
	private String nome="";
	/** Descricao do conhecimento. */
	private String descricao="";

	/** Construtor. */
	public Conhecimento() {
	}
	   
	@Column(nullable = false, length = 100)
    public String getNome() {
        return nome;
    }
    
    /**
     * Atribui nome ao conhecimento.
     */
    public void setNome(String parNome) {
        nome = parNome;
    }
    
	/** Descreve o objeto Conhecimento detalhadamente. */
	public String sPubDescreverObjeto() {
		StringBuffer locDadosConhecimento = new StringBuffer();
		locDadosConhecimento.append(" *** CONHECIMENTO *** \n");
		locDadosConhecimento.append(" - Nome: ");
		locDadosConhecimento.append(getNome() + "\n");
		locDadosConhecimento.append(" - Descrição: ");
		locDadosConhecimento.append(getDescricao());
		return locDadosConhecimento.toString();
	}

    @Column(nullable = false, length = 500)
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Atribui descricao ao conhecimento.
     */
    public void setDescricao(String parDescricao) {
        descricao = parDescricao;
    }
    
    /** Sobreescreve toString(). */
    public String toString(){
        return this.nome;
    }

    /** Cezar Gobbo: Precisei fazer um construtor de cópia para esta classe **/
    @Override
    public Conhecimento clone() throws CloneNotSupportedException{
    	Conhecimento copia = new Conhecimento();
    	
    	copia.setNome(new String(this.getNome().toString()));
    	copia.setDescricao(new String(this.getDescricao().toString()));
    	
    	return copia;
    }
    
}

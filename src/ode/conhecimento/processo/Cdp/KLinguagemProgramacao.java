package ode.conhecimento.processo.Cdp;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

public class KLinguagemProgramacao extends KRecursoSoftware {


	private static final long serialVersionUID = 5655441504279988988L;
	private Set<KParadigma> kParadigma;
	
    public KLinguagemProgramacao() {
    }
    
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KParadigma.class,fetch=FetchType.LAZY)
    public Set<KParadigma> getKParadigma(){
        return kParadigma;
    }
    
    public void setKParadigma(Set<KParadigma> parKParadigma){
        kParadigma = parKParadigma;
    }
    
    public String obterNomeCanonico() {
        return "Linguagem de Programação";
    }

}

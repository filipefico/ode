package ode.conhecimento.processo.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;


/** Representa os conhecimentos sobre as ordens das combinacoes.
*/
@Entity
public class KOrdemCombinacao extends Conhecimento{

	private static final long serialVersionUID = -8275026654042459036L;

	/** Ordem da KCombinacao dentro do KModeloCicloVida */
    private int ordem;
    
    /** KCombinacao ligada ao KModeloCicloVida */
    private KCombinacao kCombinacao;
    
    /** KModeloCiloVida em que a KCombinacao estah ligada */
    private KModeloCicloVida kModeloCicloVida;
    
    /** Creates a new instance of KOrdemCombinacao */
    public KOrdemCombinacao() {
    }

    /** Obtém a ordem da KCombinacao no KModeloCicloVida. 
     */
    @Column
    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }


    /** Obtém a KCombinacao.
    *   not-null = "true"
    */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KCombinacao.class)
    public KCombinacao getKCombinacao() {
        return kCombinacao;
    }

    public void setKCombinacao(KCombinacao kCombinacao) {
        this.kCombinacao = kCombinacao;
    }

    
    /** Obtém o KModeloCicloVida.
     *   not-null = "true"
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KModeloCicloVida.class)
    public KModeloCicloVida getKModeloCicloVida() {
        return kModeloCicloVida;
    }

    public void setKModeloCicloVida(KModeloCicloVida kModeloCicloVida) {
        this.kModeloCicloVida = kModeloCicloVida;
    }
    
    public String toString(){
        return this.kCombinacao.toString();
    }
    
	
}

package ode.conhecimento.processo.cdp;


import javax.persistence.Column;
import javax.persistence.Entity;

import ode.conhecimento.principal.cdp.Conhecimento;

/************************** Classe KProcessoPadrao ***************************/
/** Representa os conhecimentos sobre Processo Padr�o do ambiente ODE.
 */
@Entity
public class KProcessoPadrao extends Conhecimento{

	private static final long serialVersionUID = -3098629217639516065L;

	/** Super processo do Processo*/
    private KProcessoPadrao superKProcessoPadrao = null;
    
    /** Indica se o processo est� completamente definido */
    private boolean definido = false;
    
    /** Construtor. */
    public KProcessoPadrao() {
    }
    
    /** Obt�m o Super-Processo do Processo Padr�o.
     *
     * ##### nao esta mapeado pois nao sabia as cardinalidades
     * como nao eh not-null... nao tem importancia
     */
    public KProcessoPadrao getSuperKProcessoPadrao() {
        return superKProcessoPadrao;
    }
    
    /** Atribui o super-processo. */
    public void setSuperKProcessoPadrao(KProcessoPadrao parKProcessoPadrao) {
        superKProcessoPadrao = parKProcessoPadrao;
    }
    
    /** Verifica se o processo est� completamente definido.
     *
     *@hibernate.property
     */@Column
    public boolean isDefinido() {
        return definido;
    }
    
    /** Atribui o atributo ehDefinido ao processo*/
    public void setDefinido(boolean partipo) {
        definido = partipo;
    }
    
    /** Obt�m as macro-atividades do processo. */
    /*public List<E> lPubObterMacroAtividades(){
        KAtividadeDAO daoKAtividade = (KAtividadeDAO) DAOFactory.getDefaultDAO(KAtividade.class);
        return  daoKAtividade.obterMacroAtividadesPorProcessoPadrao(this);
    }*/
    
}

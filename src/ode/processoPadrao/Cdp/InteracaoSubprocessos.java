package ode.processoPadrao.Cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import nucleo.comuns.persistencia.ObjetoPersistente;

@Entity
public class InteracaoSubprocessos extends ObjetoPersistente{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7452245244533060222L;
	private CompPPProcessoSimples origem;
    private CompPPProcessoSimples destino;
    private CompPPProcessoComplexo procComplexo;
    //private KTipoInteracao tipo;


    public InteracaoSubprocessos(CompPPProcessoSimples origem, CompPPProcessoSimples destino, CompPPProcessoComplexo procComplexo) {//, KTipoInteracao tipo
        this.origem = origem;
        this.destino = destino;
        this.procComplexo = procComplexo;
        //this.tipo = tipo;
    }

    public void InteracaoSubprocessos() {
    }

    /*
     *
     * @hibernate.many-to-one
     *   column = "idodestino"
     *   not-null = "false"
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoSimples"
     *
     */
    @ManyToOne(cascade=javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoSimples.class)
    public CompPPProcessoSimples getDestino() {
        return destino;
    }

    public void setDestino(CompPPProcessoSimples destino) {
        this.destino = destino;
    }

    /*
     *
     * @hibernate.many-to-one
     *   column = "idoorigem"
     *   not-null = "false"
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoSimples"
     *
     */
    @ManyToOne(cascade=javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoSimples.class)
    public CompPPProcessoSimples getOrigem() {
        return origem;
    }

    public void setOrigem(CompPPProcessoSimples origem) {
        this.origem = origem;
    }

    /*
     *
     * @hibernate.many-to-one
     *   column = "idoproccomplexo"
     *   not-null = "false"
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoComplexo"
     *
     */
    @ManyToOne(cascade=javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoComplexo.class)
    public CompPPProcessoComplexo getProcComplexo() {
        return procComplexo;
    }

    public void setProcComplexo(CompPPProcessoComplexo procComplexo) {
        this.procComplexo = procComplexo;
    }

    /*
     *
     * @hibernate.many-to-one
     *   column = "idotipo"
     *   not-null = "false"
     *   class = "Ode.Conhecimento.Processo.Cdp.KTipoInteracao"
     *
     */
//    public KTipoInteracao getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(KTipoInteracao tipo) {
//        this.tipo = tipo;
//    }
}
package ode.conhecimento.processo.Cdp;

import ode.conhecimento.principal.Cdp.Conhecimento;

/**
 * Represetacao do Tipo de um artefato
 *@hibernate.joined-subclass
 *      table = "conh_proc_tipokartefato"
 *@hibernate.joined-subclass-key
 *      column = "id"
 */
public class TipoKArtefato extends Conhecimento {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6014753880052632379L;

	public TipoKArtefato() {
    }

}

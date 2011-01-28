package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;

import ode.conhecimento.principal.Cdp.Conhecimento;


/** Representa os conhecimentos sobre Tipos de Interacao dos KProcessos
*/
@Entity
public class KTipoInteracao extends Conhecimento {
	

		private static final long serialVersionUID = 8244852932009015937L;
		public static final String PARALELO_INDEPENDENTE = "Paralelo Independente";
	    public static final String PARALELO_DEPENDENTE = "Paralelo Dependente";
	    public static final String SEQUENCIAL = "Seqüencial";
	    public static final String PONTUAL = "Pontual";
	    public static final String SOB_DEMANDA = "Sob-Demanda";
	    
	    public KTipoInteracao() {
	    }
}

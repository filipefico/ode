package ode.medicao.planejamentoMedicao.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimentoMedicao.cdp.KMedida;

@Entity
public class ValorReferencia extends ObjetoPersistente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572429986176025355L;

	private Date data;
	private String descricao;
	private Set<KMedida> indicadores;
	
}

package nucleo.comuns.persistencia;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ObjetoPersistente extends NucleoObjetoPersistenteImpl<Long, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8108442230361292528L;

}

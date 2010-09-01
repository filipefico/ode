package nucleo.comuns.autenticacao.acegi.persistencia;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoOrganizacao;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface NucleoOrganizacaoDAO extends NucleoDAOBase<NucleoOrganizacao> {

	/**
	 * Obt�m um NucleoPessoa pelo nome.
	 * 
	 * @param nome do NucleoOrganizacao que se deseja obter. 
	 * @return NucleoOrganizacao com o nome.
	 */
	public NucleoOrganizacao recuperarPorNome(String nome);
	
}

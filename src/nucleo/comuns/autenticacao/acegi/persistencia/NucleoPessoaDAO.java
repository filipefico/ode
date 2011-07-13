package nucleo.comuns.autenticacao.acegi.persistencia;

import ode.nucleo.cgd.NucleoDAOBase;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoPessoa;

public interface NucleoPessoaDAO extends NucleoDAOBase<NucleoPessoa> {

	/**
	 * Obtém um NucleoPessoa pelo e-mail.
	 * 
	 * @param email E-mail do NucleoPessoa que se deseja obter. 
	 * @return NucleoPessoa com o e-mail.
	 */
	public NucleoPessoa recuperarPorEmail(String email);
	
}

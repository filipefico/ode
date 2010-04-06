package nucleo.comuns.autenticacao.acegi.aplicacao;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.autenticacao.acegi.persistencia.NucleoUserDetailsDAO;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;


/**
 * Serviço para criação/alteração e consulta de usuários do sistema.
 * 
 * @author Alexandre G. N. Coelho
 */
@Transactional
public class NucleoUserDetailsService implements UserDetailsService,
		NucleoAplCadastrarNucleoUserDetails {

	private NucleoUserDetailsDAO nucleoUserDetailsDAO;

	public NucleoUserDetailsDAO getNucleoUserDetailsDAO() {
		return nucleoUserDetailsDAO;
	}

	public void setNucleoUserDetailsDAO(NucleoUserDetailsDAO nucleoUserDetailsDAO) {
		this.nucleoUserDetailsDAO = nucleoUserDetailsDAO;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		// Recupera por username
		UserDetails usuario = nucleoUserDetailsDAO.recuperarPorUsername(username);

		// Se o usuário não existir, dispara exceção para o Acegi
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}

		return usuario;
	}

	public void excluir(NucleoUserDetails nucleoUserDetails) {
		nucleoUserDetailsDAO.excluir(nucleoUserDetails);
	}

	public Collection<NucleoUserDetails> recuperarTodos() {
		List<NucleoUserDetails> pessoas = (List<NucleoUserDetails>) nucleoUserDetailsDAO
				.recuperarTodos();
		Collections.sort(pessoas, new ComparaPessoa());
		return pessoas;
	}

	public NucleoUserDetails recuperarPorId(Long id) {
		return nucleoUserDetailsDAO.recuperarPorId(id);
	}

	public NucleoUserDetails salvar(NucleoUserDetails nucleoUserDetails)
			throws NucleoRegraNegocioExcecao {
		if (nucleoUserDetails.isPersistente()) {
			return alterarDados(nucleoUserDetails);
		} else {
			return incluirNovo(nucleoUserDetails);
		}
	}

	/**
	 * Inclui um novo NucleoUserDetails no sistema. É importante observar que o
	 * NucleoUserDetails não pode ser persistente, ou seja, seu método
	 * isPersistente() deve retornar falso.
	 * 
	 * @param nucleoUserDetails
	 *            NucleoUserDetails a ser persistido
	 * @return NucleoUserDetails persistido.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio
	 */
	private NucleoUserDetails incluirNovo(NucleoUserDetails nucleoUserDetails)
			throws NucleoRegraNegocioExcecao {
		// Verifica se já existe algum usuário com o mesmo username
		UserDetails usuario = nucleoUserDetailsDAO
				.recuperarPorUsername(nucleoUserDetails.getUsername());
		if (usuario != null) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_EXISTE_USUARIO_MESMO_USERNAME),
					null);
		}

		// Inclui o NucleoUserDetails
		nucleoUserDetailsDAO.salvar(nucleoUserDetails);

		return nucleoUserDetails;
	}

	/**
	 * Salva os dados de um NucleoUserDetails já persistido.
	 * 
	 * @param nucleoUserDetails
	 *            NucleoUserDetails cujos dados devem ser alterados.
	 * @return o NucleoUserDetails alterado.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio
	 */
	private NucleoUserDetails alterarDados(NucleoUserDetails nucleoUserDetails)
			throws NucleoRegraNegocioExcecao {
		// Verifica se já existe algum usuário com o mesmo username
		NucleoUserDetails usuario = (NucleoUserDetails) nucleoUserDetailsDAO
				.recuperarPorUsername(nucleoUserDetails.getUsername());
		if (usuario != null) {
			if (!usuario.getId().equals(nucleoUserDetails.getId())) {
				throw new NucleoRegraNegocioExcecao(
						NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_EXISTE_USUARIO_MESMO_USERNAME),
						null);
			}
		}

		// Obtém o Principal Serviço persistido e altera seus dados
		NucleoUserDetails nucleoUserDetailsPersistido = nucleoUserDetailsDAO
				.recuperarPorId(nucleoUserDetails.getId());
		nucleoUserDetailsPersistido.alterarDados(nucleoUserDetails);

		return nucleoUserDetailsPersistido;
	}

	private class ComparaPessoa implements Comparator<NucleoUserDetails> {

		public int compare(NucleoUserDetails objeto1, NucleoUserDetails objeto2) {
			return objeto1.getPessoa().getNome().compareToIgnoreCase(
					objeto2.getPessoa().getNome());
		}
	}
}

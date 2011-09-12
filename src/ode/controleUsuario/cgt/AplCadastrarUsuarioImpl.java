package ode.controleUsuario.cgt;

import java.util.Collection;

import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgd.UsuarioDAO;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Serviço para criação/alteração e consulta de usuários do sistema.
 * 
 * @author Alexandre G. N. Coelho
 */
@Service("AplCadastrarUsuario")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarUsuarioImpl implements AplCadastrarUsuario {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void excluir(Usuario usuario) {
		usuarioDAO.excluir(usuario);
	}

	public Collection<Usuario> recuperarTodos() {
		return usuarioDAO.recuperarTodosComOrdenacao("nomeUsuario");
	}

	public Usuario recuperarPorId(Long id) {
		return usuarioDAO.recuperarPorId(id);
	}

	public Usuario salvar(Usuario usuario)
			throws NucleoRegraNegocioExcecao {
		if (usuario.isPersistente()) {
			return alterarDados(usuario);
		} else {
			return incluirNovo(usuario);
		}
	}

	/**
	 * Inclui um novo Usuario no sistema. É importante observar que o
	 * Usuario não pode ser persistente, ou seja, seu método
	 * isPersistente() deve retornar falso.
	 * 
	 * @param usuario
	 *            Usuario a ser persistido
	 * @return Usuario persistido.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio
	 */
	private Usuario incluirNovo(Usuario usuario)
			throws NucleoRegraNegocioExcecao {
		// Verifica se já existe algum usuário com o mesmo username
		Usuario novo = usuarioDAO
				.recuperarPorNomeUsuario(usuario.getNomeUsuario());
		if (novo != null) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_EXISTE_USUARIO_MESMO_USERNAME),
					null);
		}

		// Inclui o Usuario
		usuarioDAO.salvar(usuario);

		return usuario;
	}

	/**
	 * Salva os dados de um Usuario já persistido.
	 * 
	 * @param usuario
	 *            Usuario cujos dados devem ser alterados.
	 * @return o Usuario alterado.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio
	 */
	private Usuario alterarDados(Usuario usuario)
			throws NucleoRegraNegocioExcecao {
		// Verifica se já existe algum usuário com o mesmo username
		Usuario existente = (Usuario) usuarioDAO
				.recuperarPorNomeUsuario(usuario.getNomeUsuario());
		if (existente != null) {
			if (!existente.getId().equals(usuario.getId())) {
				throw new NucleoRegraNegocioExcecao(
						NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_EXISTE_USUARIO_MESMO_USERNAME),
						null);
			}
		}

		// Obtém o Principal Serviço persistido e altera seus dados
		Usuario usuarioPersistido = usuarioDAO
				.recuperarPorId(usuario.getId());
		usuarioPersistido.alterarDados(usuario);

		return usuarioPersistido;
	}

}

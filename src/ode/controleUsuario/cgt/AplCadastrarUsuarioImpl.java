package ode.controleUsuario.cgt;

import java.util.Collection;

import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgd.UsuarioDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
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
@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarUsuarioImpl extends AplBaseImpl<Usuario> implements AplCadastrarUsuario {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public DAOBase<Usuario> getNucleoDaoBase() {
		return usuarioDAO;
	}
	
	@Override
	public Collection<Usuario> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodosComOrdenacao("nomeUsuario");
	}

	@Override
	protected void antesIncluirNovo(Usuario usuario) throws NucleoRegraNegocioExcecao {
		// Verifica se já existe algum usuário com o mesmo username
		Usuario novo = usuarioDAO.recuperarPorNomeUsuario(usuario.getNomeUsuario());
		if (novo != null) {
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_EXISTE_USUARIO_MESMO_USERNAME), null);
		}
	}

	@Override
	protected void antesAlterarDados(Usuario usuario) throws NucleoRegraNegocioExcecao {
		// Verifica se já existe algum usuário com o mesmo username
		Usuario existente = (Usuario) usuarioDAO.recuperarPorNomeUsuario(usuario.getNomeUsuario());
		if (existente != null) {
			if (!existente.getId().equals(usuario.getId())) {
				throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_EXISTE_USUARIO_MESMO_USERNAME), null);
			}
		}
	}
}

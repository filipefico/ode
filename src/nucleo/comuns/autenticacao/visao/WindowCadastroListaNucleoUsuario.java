package nucleo.comuns.autenticacao.visao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nucleo.comuns.autenticacao.acegi.aplicacao.NucleoAplCadastrarNucleoUserDetails;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoGrantedAuthority;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.NucleoWindowCadastroLista;

import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listitem;


/**
 * Janela de lista de usuários do sistema.
 * 
 * @author Alexandre G. N. Coelho
 */
public class WindowCadastroListaNucleoUsuario extends
		NucleoWindowCadastroLista<NucleoUserDetails> {

	private static final long serialVersionUID = 3393474802594152573L;

	private static final String NOME_WINDOW_CADASTRO_DADOS = "/visao/admin/windowCadastroDadosNucleoUsuario.zul";

	private NucleoAplCadastrarNucleoUserDetails nucleoAplCadastrarNucleoUserDetails;

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_CADASTRO_USUARIOS);
	}

	@Override
	protected void configurarComponentesExtensao() {
		//this.setWidth("470px");
		//this.listBox.setWidth("98%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void acaoBotaoExcluir() throws DataAccessException,
			NucleoRegraNegocioExcecao {
		// Obtém a classe de aplicação
		nucleoAplCadastrarNucleoUserDetails = (NucleoAplCadastrarNucleoUserDetails) SpringUtil
				.getBean("nucleoUserDetailsService");

		// Exclui os itens selecionados
		Set<Listitem> itensSelecionados = listBox.getSelectedItems();
		Iterator<Listitem> itItensSelecionados = itensSelecionados.iterator();
		while (itItensSelecionados.hasNext()) {
			Listitem itemSelecionado = itItensSelecionados.next();
			NucleoUserDetails nucleoUserDetails = (NucleoUserDetails) itemSelecionado
					.getValue();
			nucleoAplCadastrarNucleoUserDetails.excluir(nucleoUserDetails);
		}
	}

	@Override
	protected NucleoUserDetails criarNovoObjetoDados() {
		// Cria um novo objeto NucleoUserDetails e seus relacionamentos
		NucleoUserDetails nucleoUserDetails = new NucleoUserDetails();
		NucleoGrantedAuthority nucleoGrantedAuthority = new NucleoGrantedAuthority();
		nucleoGrantedAuthority
				.setAuthority(NucleoGrantedAuthority.AUTHORITY_USUARIO_COMUM);
		Set<NucleoGrantedAuthority> conjGrantedAuthorities = new HashSet<NucleoGrantedAuthority>();
		conjGrantedAuthorities.add(nucleoGrantedAuthority);
		nucleoUserDetails.setGrantedAuthorities(conjGrantedAuthorities);
		return nucleoUserDetails;
	}

	@Override
	protected String definirNomeWindowCadastroDados() {
		return NOME_WINDOW_CADASTRO_DADOS;
	}

	@Override
	protected String[] definirTamanhosCabecalho() {
		return new String[] { "30%", "40%", "30%" };
	}

	@Override
	protected String[] definirTitulosCabecalho() {
		return new String[] {
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO),
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PESSOA),
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO) };
	}

	@Override
	protected String[] recuperarDadosObjeto(NucleoUserDetails nucleoUserDetails) {
		String tipoUsuario = "";
		if (nucleoUserDetails.getGrantedAuthorities().size() > 0) {
			NucleoGrantedAuthority nucleoGrantedAuthority = nucleoUserDetails
					.getGrantedAuthorities().iterator().next();
			if (NucleoGrantedAuthority.AUTHORITY_ADMINISTRADOR
					.equals(nucleoGrantedAuthority.getAuthority())) {
				tipoUsuario = NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_ADMINISTRADOR);
			} else if (NucleoGrantedAuthority.AUTHORITY_USUARIO_COMUM
					.equals(nucleoGrantedAuthority.getAuthority())) {
				tipoUsuario = NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_COMUM);
			}
		}

		if (nucleoUserDetails.getPessoa() == null) {
			return new String[] { nucleoUserDetails.getUsername(), null,
					tipoUsuario };
		}

		return new String[] { nucleoUserDetails.getUsername(),
				nucleoUserDetails.getPessoa().getNome(), tipoUsuario };
	}

	@Override
	protected Collection<NucleoUserDetails> recuperarObjetos()
			throws DataAccessException, NucleoRegraNegocioExcecao {
		// Retorna todos os usuários
		nucleoAplCadastrarNucleoUserDetails = (NucleoAplCadastrarNucleoUserDetails) SpringUtil
				.getBean("nucleoUserDetailsService");
		Collection<NucleoUserDetails> listaUsuarios = nucleoAplCadastrarNucleoUserDetails
				.recuperarTodos();
		return listaUsuarios;
	}

}

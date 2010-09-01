package nucleo.comuns.autenticacao.acegi.aplicacao;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoOrganizacao;
import nucleo.comuns.autenticacao.acegi.persistencia.NucleoOrganizacaoDAO;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;

import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarNucleoOrganizacaoImpl extends
		NucleoAplCadastroBaseImpl<NucleoOrganizacao> implements AplCadastrarNucleoOrganizacao {

	@Override
	protected void copiarValor(NucleoOrganizacao objetoFonte, NucleoOrganizacao objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
	}

	@Override
	protected void antesIncluirNovo(NucleoOrganizacao objeto)
			throws NucleoRegraNegocioExcecao {

		// Verifica se existe outra Organizacao com o mesmo nome
		if (((NucleoOrganizacaoDAO) this.getNucleoDaoBase()).recuperarPorNome(objeto
				.getNome()) != null) {
			throw new NucleoRegraNegocioExcecao(NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_EXISTE_ORGANIZACAO_MESMO_NOME),
					null);
		}
	}

	@Override
	protected void antesAlterarDados(NucleoOrganizacao objeto)
			throws NucleoRegraNegocioExcecao {

		// Verifica se existe Organizacao com o mesmo e-mail
		NucleoOrganizacao nucleoOrganizacaoPersistido = ((NucleoOrganizacaoDAO) this
				.getNucleoDaoBase()).recuperarPorNome(objeto.getNome());
		if (nucleoOrganizacaoPersistido != null) {
			if (!nucleoOrganizacaoPersistido.getId().equals(objeto.getId())) {
				throw new NucleoRegraNegocioExcecao(
						NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_EXISTE_ORGANIZACAO_MESMO_NOME),
						null);
			}
		}
	}

	public Collection<NucleoOrganizacao> recuperarTodos() throws NucleoRegraNegocioExcecao {
		List<NucleoOrganizacao> Organizacao = (List<NucleoOrganizacao>) super.recuperarTodos();
		Collections.sort(Organizacao, new ComparaNucleoOrganizacao());
		return Organizacao;
	}

	private class ComparaNucleoOrganizacao implements Comparator<NucleoOrganizacao> {

		public int compare(NucleoOrganizacao objeto1, NucleoOrganizacao objeto2) {
			return objeto1.getNome().compareToIgnoreCase(objeto2.getNome());
		}
	}
}

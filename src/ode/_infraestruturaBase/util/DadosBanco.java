package ode._infraestruturaBase.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zkplus.spring.SpringUtil;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.cdp.KCategoriaProcesso;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cdp.KTipoInteracao;
import ode.conhecimento.processo.cgd.KRecursoHumanoDAO;
import ode.conhecimento.processo.cgd.KTipoInteracaoDAO;
import ode.conhecimento.processo.cgd.KTipoInteracaoDAOImpl;
import ode.conhecimento.processo.cgt.AplCadastrarKCategoriaProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKTipoInteracao;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgd.UsuarioDAO;
import ode.controleUsuario.cgt.AplAutenticarUsuario;
import ode.controleUsuario.cgt.AplCadastrarUsuario;

public class DadosBanco {
	/*
	 * Essa classe setará os dados iniciais ao banco de dados, a utilização é
	 * simples. Observe o funionamento e repita o procedimento.
	 */
	public void aplicar() {
		try {

			usuarios();
			tipoInteracao();
			categoriaProcesso();

		} catch (NucleoRegraNegocioExcecao e) {
			e.printStackTrace();
		}
	}

	private void categoriaProcesso() throws NucleoRegraNegocioExcecao {
		KCategoriaProcesso kCategoriaProcesso1 = new KCategoriaProcesso();
		KCategoriaProcesso kCategoriaProcesso2 = new KCategoriaProcesso();
		KCategoriaProcesso kCategoriaProcesso3 = new KCategoriaProcesso();

		kCategoriaProcesso1.setNome("Fundamental");
		kCategoriaProcesso1.setDescricao("Categoria Fundamental");

		kCategoriaProcesso2.setNome("Apoio");
		kCategoriaProcesso2.setDescricao("Categoria de Apoio");

		kCategoriaProcesso3.setNome("Organizacional");
		kCategoriaProcesso3.setDescricao("Categoria Organizacional");

		AplCadastrarKCategoriaProcesso aplCadastrarKCategoriaProcesso = (AplCadastrarKCategoriaProcesso) SpringUtil
				.getBean(AplCadastrarKCategoriaProcesso.class.getSimpleName());

		aplCadastrarKCategoriaProcesso.salvar(kCategoriaProcesso1);
		aplCadastrarKCategoriaProcesso.salvar(kCategoriaProcesso2);
		aplCadastrarKCategoriaProcesso.salvar(kCategoriaProcesso3);

	}

	private void tipoInteracao() throws NucleoRegraNegocioExcecao {

		KTipoInteracao kTipoInteracao1 = new KTipoInteracao();
		KTipoInteracao kTipoInteracao2 = new KTipoInteracao();
		KTipoInteracao kTipoInteracao3 = new KTipoInteracao();
		KTipoInteracao kTipoInteracao4 = new KTipoInteracao();
		KTipoInteracao kTipoInteracao5 = new KTipoInteracao();

		kTipoInteracao1.setNome("Sequencial");
		kTipoInteracao1.setDescricao("Tipo de Interacao Sequencial");

		kTipoInteracao2.setNome("Paralelo Independente");
		kTipoInteracao2.setDescricao("Tipo de Interacao Paralelo Independente");

		kTipoInteracao3.setNome("Paralelo Dependente");
		kTipoInteracao3.setDescricao("Tipo de Interacao Paralelo Dependente");

		kTipoInteracao4.setNome("Pontual");
		kTipoInteracao4.setDescricao("Tipo de Interacao Pontual");

		kTipoInteracao5.setNome("Sob-Demanda");
		kTipoInteracao5.setDescricao("Tipo de Interacao Sob-Demanda");

		AplCadastrarKTipoInteracao aplCadastrarKTipoInteracao = (AplCadastrarKTipoInteracao) SpringUtil
				.getBean(AplCadastrarKTipoInteracao.class.getSimpleName());

		aplCadastrarKTipoInteracao.salvar(kTipoInteracao1);
		aplCadastrarKTipoInteracao.salvar(kTipoInteracao2);
		aplCadastrarKTipoInteracao.salvar(kTipoInteracao3);
		aplCadastrarKTipoInteracao.salvar(kTipoInteracao4);
		aplCadastrarKTipoInteracao.salvar(kTipoInteracao5);
	}

	private void usuarios() throws NucleoRegraNegocioExcecao {

		// cria os dados
		KRecursoHumano krhGP = new KRecursoHumano();
		krhGP.setNome("Gerente de Projetos");
		AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano = (AplCadastrarKRecursoHumano) SpringUtil
				.getBean(AplCadastrarKRecursoHumano.class.getSimpleName());
		aplCadastrarKRecursoHumano.salvar(krhGP);

		RecursoHumano rh = new RecursoHumano();
		rh.setNome("Falbo");
		rh.setCargo(krhGP);
		AplCadastrarRecursoHumano aplCadastrarRecursoHumano = (AplCadastrarRecursoHumano) SpringUtil
				.getBean(AplCadastrarRecursoHumano.class.getSimpleName());
		aplCadastrarRecursoHumano.salvar(rh);

		Usuario u = new Usuario();
		u.setNomeUsuario("admin");
		u.setSenha(NucleoUtil.encrypt("senha"));
		u.setPerfilAcesso(PerfilAcesso.Administrador);
		u.setRecursoHumano(rh);
		AplCadastrarUsuario aplCadastrarUsuario = (AplCadastrarUsuario) SpringUtil
				.getBean(AplCadastrarUsuario.class.getSimpleName());
		aplCadastrarUsuario.salvar(u);
	}

}

package ode._infraestruturaBase.util;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleFerramenta.cgt.AplCadastrarFerramentaSoftware;
import ode._controleProcesso.ciu.CtrlDefinicaoProcesso;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.alocacaoRecurso.ciu.CtrlAlocacaoRecurso;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KCategoriaProcesso;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cdp.KTipoInteracao;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKCategoriaProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKFerramentaSoftware;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKTipoInteracao;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgt.AplCadastrarUsuario;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cgt.AplDefinirProcessoPadrao;

import org.zkoss.zkplus.spring.SpringUtil;

public class DadosBanco {
	/*
	 * Essa classe setará os dados iniciais ao banco de dados, a utilização é
	 * simples. Observe o funionamento e repita o procedimento.
	 */
	public void aplicar() {

		try {
			// usuarios();
			// tipoInteracao();
			// categoriaProcesso();

			inicializarUsuarios();
			inicializarKRecursosHumanos();
			inicializarKTiposInteracao();
			inicializarKCategoriasProcesso();
			inicializarDefinicaoProcesso();// juliao
			inicializarFerramentasSoftware();

		} catch (NucleoRegraNegocioExcecao e) {
			e.printStackTrace();
		}
	}

	private void inicializarUsuarios() throws NucleoRegraNegocioExcecao {
		// cria os dados
		KRecursoHumano kRecursoHumano = new KRecursoHumano();
		kRecursoHumano.setNome("Gerente de Projetos");
		AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano = (AplCadastrarKRecursoHumano) SpringUtil
				.getBean(AplCadastrarKRecursoHumano.class.getSimpleName());
		aplCadastrarKRecursoHumano.salvar(kRecursoHumano);

		RecursoHumano recursohumano = new RecursoHumano();
		recursohumano.setNome("Ricardo de Almeida Falbo");
		recursohumano.setCargaHoraria(80);
		recursohumano.setEmail("falbo@inf.ufes.br");
		recursohumano.setTelefone("0800-1231234");
		recursohumano.setCargo(kRecursoHumano);

		AplCadastrarRecursoHumano aplCadastrarRecursoHumano = (AplCadastrarRecursoHumano) SpringUtil
				.getBean(AplCadastrarRecursoHumano.class.getSimpleName());
		aplCadastrarRecursoHumano.salvar(recursohumano);

		Usuario usuario = new Usuario();
		usuario.setNomeUsuario("admin");
		usuario.setSenha(NucleoUtil.encrypt("senha"));
		usuario.setPerfilAcesso(PerfilAcesso.Administrador);
		usuario.setRecursoHumano(recursohumano);

		AplCadastrarUsuario aplCadastrarUsuario = (AplCadastrarUsuario) SpringUtil
				.getBean(AplCadastrarUsuario.class.getSimpleName());
		aplCadastrarUsuario.salvar(usuario);
	}

	private void inicializarKRecursosHumanos() throws NucleoRegraNegocioExcecao {
		inserirKRecursoHumano("Analista");
		inserirKRecursoHumano("Desenvolvedor");
		inserirKRecursoHumano("Usuário");
		inserirKRecursoHumano("Cliente");
	}

	private void inicializarKCategoriasProcesso()
			throws NucleoRegraNegocioExcecao {
		inserirKCategoriaProcesso("Fundamental", "");
		inserirKCategoriaProcesso("Apoio", "");
		inserirKCategoriaProcesso("Organizacional", "");
	}

	private void inicializarKTiposInteracao() throws NucleoRegraNegocioExcecao {
		inserirKTipoInteracao("Sequencial", "");
		inserirKTipoInteracao("Paralelo Independente", "");
		inserirKTipoInteracao("Paralelo Dependente", "");
		inserirKTipoInteracao("Pontual", "");
		inserirKTipoInteracao("Sob-Demanda", "");
	}

	private void inicializarDefinicaoProcesso()
			throws NucleoRegraNegocioExcecao {
		// inserir processos
		KProcesso kProcesso1 = inserirKProcesso("Kprocesso1",
				"esse é o primeiro Kprocesso");
		KProcesso kProcesso2 = inserirKProcesso("Kprocesso2",
				"segundo kprocesso");
		KProcesso kProcesso3 = inserirKProcesso("Kprocesso3",
				"Esse portanto é o terceiro");
		KProcesso kProcesso4 = inserirKProcesso("Kprocesso4",
				"quarto elemento de kprocesso");
		KProcesso kProcesso5 = inserirKProcesso("Kprocesso5",
				"quinto e ultimo elemento de kprocesso");

		// inserir atividades
		KAtividade kAtividade1 = inserirKAtividade("KAtividade1",
				"primeira KAtividade");
		KAtividade kAtividade2 = inserirKAtividade("KAtividade2",
				"Segunda Katividade criada");
		KAtividade kAtividade3 = inserirKAtividade("KAtividade3",
				"Terceira KAtividade tranquilamente criada");
		KAtividade kAtividade4 = inserirKAtividade("KAtividade4",
				"KAtividade numero 4");
		KAtividade kAtividade5 = inserirKAtividade("KAtividade5",
				"quinta e ultima KAtividade automaticamente criada");

		// inserir um compPPProcessoComplexo
		inserirCompPPProcessoComplexo("CompPPComplexo1",
				"Primeiro CompPPProcessoComplexo criado");

		// inserir dois compPPProcessoSimples
		inserirCompPPProcessoSimples("CompPPSimples1",
				"primiero CompPPProcessoSimples", kProcesso1);
		inserirCompPPProcessoSimples("CompPPSimples2",
				"segundo CompPPProcessoSimples criado", kProcesso2);

		// inserir dois compPPMacroAtividade
		inserirCompPPMacroAtividade("CompPPMacroAtv1",
				"Primeiro compPPMacroAtividade", kAtividade1);
		inserirCompPPMacroAtividade("CompPPMacroAtv2",
				"segundo compPPMacroAtividade criado", kAtividade2);

	}

	private void inicializarFerramentasSoftware()
			throws NucleoRegraNegocioExcecao {
		AplCadastrarFerramentaSoftware aplFS = (AplCadastrarFerramentaSoftware) SpringUtil
				.getBean(AplCadastrarFerramentaSoftware.class.getSimpleName());
		AplCadastrarKFerramentaSoftware aplKFS = (AplCadastrarKFerramentaSoftware) SpringUtil
				.getBean(AplCadastrarKFerramentaSoftware.class.getSimpleName());

		KFerramentaSoftware alocacao = new KFerramentaSoftware();
		alocacao.setNome("Alocação de Recursos");
		aplKFS.salvar(alocacao);
		KFerramentaSoftware definicao = new KFerramentaSoftware();
		definicao.setNome("Definição de Processos");
		aplKFS.salvar(definicao);

		FerramentaSoftware alocaODE = new FerramentaSoftware();
		alocaODE.setNome("AlocaODE");
		alocaODE.setCaminho(CtrlAlocacaoRecurso.class.getCanonicalName());
		alocaODE.setDisponivelApenasParaProjetos(true);
		alocaODE.setVersao("1.0");
		alocaODE.setInterna(true);
		alocaODE.setkFerramentaSoftware(alocacao);
		aplFS.salvar(alocaODE);

		FerramentaSoftware defPro = new FerramentaSoftware();
		defPro.setNome("defPro");
		defPro.setCaminho(CtrlDefinicaoProcesso.class.getCanonicalName());
		defPro.setDisponivelApenasParaProjetos(true);
		defPro.setVersao("1.0");
		defPro.setInterna(true);
		defPro.setkFerramentaSoftware(definicao);
		aplFS.salvar(defPro);
	}

	private KRecursoHumano inserirKRecursoHumano(String nome)
			throws NucleoRegraNegocioExcecao {

		KRecursoHumano objeto = new KRecursoHumano();
		objeto.setNome(nome);

		AplCadastrarKRecursoHumano apl = (AplCadastrarKRecursoHumano) SpringUtil
				.getBean(AplCadastrarKRecursoHumano.class.getSimpleName());
		apl.salvar(objeto);
		return objeto;
	}

	private KCategoriaProcesso inserirKCategoriaProcesso(String nome,
			String Descricao) throws NucleoRegraNegocioExcecao {

		KCategoriaProcesso kCategoriaProcesso = new KCategoriaProcesso();
		kCategoriaProcesso.setNome(nome);
		kCategoriaProcesso.setDescricao(Descricao);
		AplCadastrarKCategoriaProcesso aplCadastrarKCategoriaProcesso = (AplCadastrarKCategoriaProcesso) SpringUtil
				.getBean(AplCadastrarKCategoriaProcesso.class.getSimpleName());
		aplCadastrarKCategoriaProcesso.salvar(kCategoriaProcesso);
		return kCategoriaProcesso;
	}

	private KTipoInteracao inserirKTipoInteracao(String nome, String descricao)
			throws NucleoRegraNegocioExcecao {
		KTipoInteracao kTipoInteracao = new KTipoInteracao();
		kTipoInteracao.setNome(nome);
		kTipoInteracao.setDescricao(descricao);

		AplCadastrarKTipoInteracao aplCadastrarKTipoInteracao = (AplCadastrarKTipoInteracao) SpringUtil
				.getBean(AplCadastrarKTipoInteracao.class.getSimpleName());

		aplCadastrarKTipoInteracao.salvar(kTipoInteracao);
		return kTipoInteracao;
	}

	private void inserirCompPPProcessoSimples(String nome, String descricao,
			KProcesso kprocesso) {

		CompPPProcessoSimples compPPSimples = new CompPPProcessoSimples();
		compPPSimples.setNome(nome);
		compPPSimples.setDescricao(descricao);
		compPPSimples.setTipo(kprocesso);

		AplDefinirProcessoPadrao aplDefinirProcessoPadrao = (AplDefinirProcessoPadrao) SpringUtil
				.getBean(AplDefinirProcessoPadrao.class.getSimpleName());
		// aplDefinirProcessoPadrao.salvarCompPP(compPPSimples);
		aplDefinirProcessoPadrao.atualizarCompPP(compPPSimples);

	}

	private void inserirCompPPProcessoComplexo(String nome, String descricao) {
		CompPPProcessoComplexo compComplexo = new CompPPProcessoComplexo();
		compComplexo.setNome(nome);
		compComplexo.setDescricao(descricao);

		AplDefinirProcessoPadrao aplDefinirProcessoPadrao = (AplDefinirProcessoPadrao) SpringUtil
				.getBean(AplDefinirProcessoPadrao.class.getSimpleName());

		aplDefinirProcessoPadrao.salvarCompPP(compComplexo);
	}

	private KAtividade inserirKAtividade(String nome, String descricao)
			throws NucleoRegraNegocioExcecao {
		KAtividade kAtividade = new KAtividade();
		kAtividade.setNome(nome);
		kAtividade.setDescricao(descricao);

		AplCadastrarKAtividade aplCadastrarKAtividade = (AplCadastrarKAtividade) SpringUtil
				.getBean(AplCadastrarKAtividade.class.getSimpleName());

		return aplCadastrarKAtividade.salvar(kAtividade);
	}

	private KProcesso inserirKProcesso(String nome, String descricao)
			throws NucleoRegraNegocioExcecao {

		KProcesso kProcesso = new KProcesso();
		kProcesso.setNome(nome);
		kProcesso.setDescricao(descricao);

		AplCadastrarKProcesso aplCadastrarKProcesso = (AplCadastrarKProcesso) SpringUtil
				.getBean(AplCadastrarKProcesso.class.getSimpleName());

		aplCadastrarKProcesso.salvar(kProcesso);
		return kProcesso;
	}

	private void inserirCompPPMacroAtividade(String nome, String descricao,
			KAtividade kAtividade) {

		CompPPMacroatividade compPPMacroAtividade = new CompPPMacroatividade();
		compPPMacroAtividade.setNome(nome);
		compPPMacroAtividade.setDescricao(descricao);
		compPPMacroAtividade.setTipo(kAtividade);

		AplDefinirProcessoPadrao aplDefinirProcessoPadrao = (AplDefinirProcessoPadrao) SpringUtil
				.getBean(AplDefinirProcessoPadrao.class.getSimpleName());

		aplDefinirProcessoPadrao.atualizarCompPP(compPPMacroAtividade);
		// aplDefinirProcessoPadrao.salvarCompPP(compPPMacroAtividade);

	}
}

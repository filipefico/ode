package ode._infraestruturaBase.util;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KCategoriaProcesso;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cdp.KTipoInteracao;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKCategoriaProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKTipoInteracao;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgt.AplCadastrarUsuario;
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

			usuarios();
			tipoInteracao();
			categoriaProcesso();
			
			definicaodeProcesso();//juliao

		} catch (NucleoRegraNegocioExcecao e) {
			e.printStackTrace();
		}
	}

	private void definicaodeProcesso() throws NucleoRegraNegocioExcecao {
		//inserir processos
		KProcesso kprocesso1 = inserirKProcesso("Kprocesso1","esse é o primeiro Kprocesso");
		KProcesso kprocesso2 = inserirKProcesso("Kprocesso2","segundo kprocesso");
		KProcesso kprocesso3 = inserirKProcesso("Kprocesso3","Esse portanto é o terceiro");
		KProcesso kprocesso4 = inserirKProcesso("Kprocesso4","quarto elemento de kprocesso");
		KProcesso kprocesso5 = inserirKProcesso("Kprocesso5","quinto e ultimo elemento de kprocesso");
	
		//inserir atividades	
		inserirKAtividade("KAtividade1","primeira KAtividade");
		inserirKAtividade("KAtividade2","Segunda Katividade criada");
		inserirKAtividade("KAtividade3","Terceira KAtividade tranquilamente criada");
		inserirKAtividade("KAtividade4","KAtividade numero 4");
		inserirKAtividade("KAtividade5","quinta e ultima KAtividade automaticamente criada");
		
		//inserir um compPPProcessoComplexo
		inserirCompPPProcessoComplexo("CompPPComplexo1","Primeiro CompPPProcessoComplexo criado");

		//inserir dois compPPProcessoSimples 
		inserirCompPPProcessoSimples("CompPPSimples1","primiero CompPPProcessoSimples",kprocesso1);
		inserirCompPPProcessoSimples("CompPPSimples2","segundo CompPPProcessoSimples criado",kprocesso2);

		
	}

	private void inserirCompPPProcessoSimples(String nome, String descricao,
			KProcesso kprocesso) {
		
		CompPPProcessoSimples compPPSimples = new CompPPProcessoSimples();
		compPPSimples.setNome(nome);
		compPPSimples.setDescricao(descricao);
		compPPSimples.setTipo(kprocesso);
		
		AplDefinirProcessoPadrao aplDefinirProcessoPadrao = (AplDefinirProcessoPadrao) SpringUtil
				.getBean(AplDefinirProcessoPadrao.class.getSimpleName());
		//aplDefinirProcessoPadrao.salvarCompPP(compPPSimples);
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

	private void inserirKAtividade(String nome, String descricao) throws NucleoRegraNegocioExcecao {
		KAtividade kAtividade = new KAtividade();
		kAtividade.setNome(nome);
		kAtividade.setDescricao(descricao);
				
		AplCadastrarKAtividade aplCadastrarKAtividade = (AplCadastrarKAtividade) SpringUtil
				.getBean(AplCadastrarKAtividade.class.getSimpleName());
		
		aplCadastrarKAtividade.salvar(kAtividade);
		
	}

	private KProcesso inserirKProcesso(String nome, String descricao) throws NucleoRegraNegocioExcecao {
		
		KProcesso kProcesso = new KProcesso();
		kProcesso.setNome(nome);
		kProcesso.setDescricao(descricao);
		
		AplCadastrarKProcesso aplCadastrarKProcesso = (AplCadastrarKProcesso) SpringUtil
				.getBean(AplCadastrarKProcesso.class.getSimpleName());
		
		aplCadastrarKProcesso.salvar(kProcesso);
		return kProcesso;
	}

	private void categoriaProcesso() throws NucleoRegraNegocioExcecao {
		inserirKCategoriaProcesso("Fundamental", "Categoria Fundamental");
		inserirKCategoriaProcesso("Apoio", "Categoria de Apoio");
		inserirKCategoriaProcesso("Organizacional", "Categoria Organizacional");
	}

	private void tipoInteracao() throws NucleoRegraNegocioExcecao {
		inserirKTipoInteracao("Sequencial", "Tipo de Interacao Sequencial");
		inserirKTipoInteracao("Paralelo Independente",
				"Tipo de Interacao Paralelo Independente");
		inserirKTipoInteracao("Paralelo Dependente",
				"Tipo de Interacao Paralelo Dependente");
		inserirKTipoInteracao("Pontual", "Tipo de Interacao Pontual");
		inserirKTipoInteracao("Sob-Demanda", "Tipo de Interacao Sob-Demanda");
	}

	private void usuarios() throws NucleoRegraNegocioExcecao {

		// cria os dados
		KRecursoHumano kRecursoHumano = new KRecursoHumano();
		kRecursoHumano.setNome("Gerente de Projetos");
		AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano = (AplCadastrarKRecursoHumano) SpringUtil
				.getBean(AplCadastrarKRecursoHumano.class.getSimpleName());
		aplCadastrarKRecursoHumano.salvar(kRecursoHumano);

		RecursoHumano recursohumano = new RecursoHumano();
		recursohumano.setNome("Ricardo de Almeida Falbo");
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

	private AplCadastrarKTipoInteracao inserirKTipoInteracao(String nome,
			String descricao) throws NucleoRegraNegocioExcecao {
		KTipoInteracao kTipoInteracao = new KTipoInteracao();
		kTipoInteracao.setNome(nome);
		kTipoInteracao.setDescricao(descricao);

		AplCadastrarKTipoInteracao aplCadastrarKTipoInteracao = (AplCadastrarKTipoInteracao) SpringUtil
				.getBean(AplCadastrarKTipoInteracao.class.getSimpleName());

		aplCadastrarKTipoInteracao.salvar(kTipoInteracao);
		return aplCadastrarKTipoInteracao;
	}
}

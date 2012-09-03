package ode._infraestruturaBase.util;

import org.springframework.context.MessageSource;
import org.zkoss.zkplus.spring.SpringUtil;

/**
 * Classe que disponibiliza mensagens internacionalizáveis para a aplicação.
 * 
 * @author Alexandre G. N. Coelho
 */
public class NucleoMensagens {

	private static MessageSource messageSource;

	static {
		// Obtém o messageSource de um bean controlado pelo Spring
		messageSource = (MessageSource) SpringUtil.getBean("messageSource");
	}

	/**
	 * Obtém a mensagem referente à chave passada na língua do Locale da sessão
	 * do usuário.
	 * 
	 * @param chave
	 *            Referência para a mensagem no arquivo .properties.
	 * @return A mensagem referente à chave na língua do Locale da sessão do
	 *         usuário.
	 */
	public static String getMensagem(String chave) {
		return messageSource.getMessage(chave, null, NucleoContexto
				.recuperarLocale());
	}

	/**
	 * Obtém a mensagem com parâmetros referente à chave passada na língua do
	 * Locale da sessão do usuário.
	 * 
	 * @param chave
	 *            Referência para a mensagem no arquivo .properties.
	 * @param parametros
	 *            Parâmetros a serem incluídos na mensagem
	 * @return A mensagem com parâmetros referente à chave na língua do Locale
	 *         da sessão do usuário.
	 */
	public static String getMensagem(String chave, Object[] parametros) {
		return messageSource.getMessage(chave, parametros, NucleoContexto
				.recuperarLocale());
	}
	
	//////////////////////////////////////////////////////////////////////
	////// MENSAGENS
	//////
	////// MANTER EM ORDEM ALFABÉTICA
	//////
	//////////////////////////////////////////////////////////////////////

	/**
	 * Chave da mensagem "Confirmação Exclusão Plural"
	 */
	public static final String MSG_CONFIRMACAO_EXCLUSAO_PLURAL = "msg.confirmacao.exclusao.plural";

	/**
	 * Chave da mensagem "Confirmação Exclusão Plural"
	 */
	public static final String MSG_CONFIRMACAO_EXCLUSAO_SINGULAR = "msg.confirmacao.exclusao.singular";

	/**
	 * Chave da mensagem "Dados salvos com sucesso"
	 */
	public static final String MSG_DADOS_SALVOS_SUCESSO = "msg.dados.salvos.sucesso";

	/**
	 * Chave da mensagem "Erro no acesso ao Banco de Dados."
	 */
	public static final String MSG_ERRO_ACESSO_BD = "msg.erro.acesso.bd";

	/**
	 * Chave da mensagem "Erro Desconhecido."
	 */
	public static final String MSG_ERRO_DESCONHECIDO = "msg.erro.desconhecido";

	/**
	 * Chave da mensagem "Já existe outra pessoa com o mesmo e-mail"
	 * 
	 */
	public static final String MSG_EXISTE_PESSOA_MESMO_EMAIL = "msg.existe.pessoa.mesmo.email";

	/**
	 * Chave da mensagem "Já existe outra organizacao com o mesmo nome"
	 * 
	 */
	public static final String MSG_EXISTE_ORGANIZACAO_MESMO_NOME = "msg.existe.organizacao.mesmo.nome";

	/**
	 * Chave da mensagem "Já existe outro usuário com o mesmo username"
	 */
	public static final String MSG_EXISTE_USUARIO_MESMO_USERNAME = "msg.existe.usuario.mesmo.username";

	/**
	 * Chave da mensagem "Idioma Alterado"
	 */
	public static final String MSG_IDIOMA_ALTERADO = "msg.idioma.alterado";

	/**
	 * Chave da mensagem "Mover para baixo"
	 */
	public static final String MSG_MOVER_PARA_BAIXO = "msg.mover.para.baixo";

	/**
	 * Chave da mensagem "Mover para cima"
	 */
	public static final String MSG_MOVER_PARA_CIMA = "msg.mover.para.cima";

	/**
	 * Chave da mensagem "Nenhum elemento selecionado"
	 */
	public static final String MSG_NENHUM_ELEMENTO_SELECIONADO = "msg.nenhum.elemento.selecionado";

	/**
	 * Chave da mensagem "Nenhum projeto selecionado!"
	 */
	public static final String MSG_NENHUM_PROJETO_SELECIONADO = "msg.nenhum.projeto.selecionado";
	
	/**
	 * Chave da mensagem "Selecione apenas um item"
	 */
	public static final String MSG_SELECIONE_APENAS_UM_ITEM = "msg.selecione.apenas.um.item";

	/**
	 * Chave da mensagem "Transferir para disponíveis"
	 */
	public static final String MSG_TRANSFERIR_PARA_DISPONIVEIS = "msg.transferir.para.disponiveis";

	/**
	 * Chave da mensagem "Transferir para selecionados"
	 */
	public static final String MSG_TRANSFERIR_PARA_SELECIONADOS = "msg.transferir.para.selecionados";

	/**
	 * Chave da mensagem "Usuário não logado"
	 */
	public static final String MSG_USUARIO_NAO_LOGADO = "msg.usuario.nao.logado";
	

	public static final String MSG_OBJETIVO_EXCLUSAO_ERRO = "msg.objetivo.exclusao.erro";

	
	public static final String MSG_AVISO_MEDICAO_SOFTWARE = "msg.aviso.medicao.software";

	
	public static final String MSG_AVISO_SOFTWARE = "msg.aviso.software";

	public static final String MSG_AVISO_NECESSIDADE_INFORMACAO = "msg.aviso.necessidade.informacao";
	public static final String MSG_MEDIDAS_RELACIONADAS_EXCLUSAO_ERRO = "msg.medidas.relacionadas.exclusao.erro";

	public static final String MSG_EMPTY_MEDICAO_INFORMACAO = "msg.empty.medicao.informacao";

	public static final String MSG_EMPTY_PROCESSO_INFORMACAO = "msg.empty.processo.informacao";
	
	public static final String MSG_SOMENTE_ADMINISTRADOR = "msg.somente.administrador";
	
	public static final String MSG_TIPOENTIDADE_EMPTY_ERRO = "msg.tipoentidade.empty.erro";

	public static final String MSG_ESCALAS_RELACIONADAS_EXCLUSAO_ERRO = "msg.escala.relacionadas.exclusao.erro";

	public static final String MSG_EMPTY_ESCALA_ERRO = "msg.empty.escala.erro";
	
	public static final String MSG_EMPTY_UNIDADE_ERRO = "msg.empty.unidade.erro";
	
	public static final String MSG_EMPTY_PROP_MED_ERRO = "msg.empty.prop.med.erro";
	
	public static final String MSG_EMPTY_NECES_INFO_ERRO = "msg.empty.neces.info.erro";
	
	public static final String MSG_EMPTY_DERIVADA_ERRO = "msg.empty.derivada.erro";

	//////////////////////////////////////////////////////////////////////
	////// TERMOS
	//////
	////// MANTER EM ORDEM ALFABÉTICA
	//////
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * Chave do termo "Abrir"
	 */
	public static final String TERMO_ABRIR = "termo.abrir";

	/**
	 * Chave do termo "Adicionar"
	 */
	public static final String TERMO_ADICIONAR = "termo.adicionar";

	/**
	 * Chave do termo "Áreas de Conhecimento"
	 */
	public static final String TERMO_ADMINISTRACAO = "termo.administracao";

	/**
	 * Chave do termo "Amarelo"
	 */
	public static final String TERMO_AMARELO = "termo.amarelo";

	/**
	 * Chave do termo "Arquivo"
	 */
	public static final String TERMO_ARQUIVO = "termo.arquivo";

	/**
	 * Chave do termo "Aviso"
	 */
	public static final String TERMO_AVISO = "termo.aviso";

	/**
	 * Chave do termo "Azul"
	 */
	public static final String TERMO_AZUL = "termo.azul";

	/**
	 * Chave do termo "Cadastro de Usuários"
	 */
	public static final String TERMO_CADASTRO_USUARIOS = "termo.cadastro.usuarios";
	
	/**
	 * Chave do termo "Cancelar"
	 */
	public static final String TERMO_CANCELAR = "termo.cancelar";

	/**
	 * Chave do termo "Celular"
	 */
	public static final String TERMO_CELULAR = "termo.celular";

	/**
	 * Chave do termo "Confirmação"
	 */
	public static final String TERMO_CONFIRMACAO = "termo.confirmacao";

	/**
	 * Chave do termo "Dados Cadastro"
	 */
	public static final String TERMO_DADOS_CADASTRO = "termo.dados.cadastro";

	/**
	 * Chave do termo "E-mail"
	 */
	public static final String TERMO_EMAIL = "termo.email";

	/**
	 * Chave do termo "Erro"
	 */
	public static final String TERMO_ERRO = "termo.erro";

	/**
	 * Chave do termo "Excluir"
	 */
	public static final String TERMO_EXCLUIR = "termo.excluir";

	/**
	 * Chave do termo "Filtro"
	 */
	public static final String TERMO_FILTRO = "termo.filtro";

	/**
	 * Chave do termo "Função"
	 */
	public static final String TERMO_FUNCAO = "termo.funcao";

	/**
	 * Chave do termo "Idade"
	 */
	public static final String TERMO_IDADE = "termo.idade";

	/**
	 * Chave do termo "Idioma"
	 */
	public static final String TERMO_IDIOMAS = "termo.idiomas";

	/**
	 * Chave do termo "Informação"
	 */
	public static final String TERMO_INFORMACAO = "termo.informacao";

	/**
	 * Chave do termo "Itens Disponíveis"
	 */
	public static final String TERMO_ITENS_DISPONIVEIS = "termo.itens.disponiveis";

	/**
	 * Chave do termo "Itens Selecionados"
	 */
	public static final String TERMO_ITENS_SELECIONADOS = "termo.itens.selecionados";

	/**
	 * Chave do termo "Login"
	 */
	public static final String TERMO_LOGIN = "termo.login";

	/**
	 * Chave do termo "Mover item para baixo"
	 */
	public static final String TERMO_MOVER_ITEM_PARA_BAIXO = "termo.mover_item_para_baixo";

	/**
	 * Chave do termo "Mover item para cima"
	 */
	public static final String TERMO_MOVER_ITEM_PARA_CIMA = "termo.mover_item_para_cima";

	/**
	 * Chave do termo "Nome"
	 */
	public static final String TERMO_NOME = "termo.nome";

	/**
	 * Chave do termo "Descrição"
	 */
	public static final String TERMO_DESCRICAO = "termo.descricao";

	/**
	 * Chave do termo "Novo"
	 */
	public static final String TERMO_NOVO = "termo.novo";

	/**
	 * Chave do termo "Pessoa"
	 */
	public static final String TERMO_PESSOA = "termo.pessoa";

	/**
	 * Chave do termo "Pessoas"
	 */
	public static final String TERMO_PESSOAS = "termo.pessoas";

	/**
	 * Chave do termo "Ramal"
	 */
	public static final String TERMO_RAMAL = "termo.ramal";

	/**
	 * Chave do termo "Sair"
	 */
	public static final String TERMO_SAIR = "termo.sair";

	/**
	 * Chave do termo "Salvar"
	 */
	public static final String TERMO_SALVAR = "termo.salvar";

	/**
	 * Chave do termo "Seleção de Usuários"
	 */
	public static final String TERMO_SELECAO_USUARIOS = "termo.selecao.usuarios";

	/**
	 * Chave do termo "Senha"
	 */
	public static final String TERMO_SENHA = "termo.senha";

	/**
	 * Chave do termo "Sobrenome"
	 */
	public static final String TERMO_SOBRENOME = "termo.sobrenome";

	/**
	 * Chave do termo "Telefone"
	 */
	public static final String TERMO_TELEFONE = "termo.telefone";

	/**
	 * Chave do termo "Tipo de Usuário"
	 */
	public static final String TERMO_TIPO_USUARIO = "termo.tipo_usuario";

	/**
	 * Chave do termo "Administrador"
	 */
	public static final String TERMO_TIPO_USUARIO_ADMINISTRADOR = "termo.tipo_usuario.administrador";

	/**
	 * Chave do termos "Usuário Comum"
	 */
	public static final String TERMO_TIPO_USUARIO_COMUM = "termo.tipo_usuario.comum";

	/**
	 * Chave do termo "Usuário"
	 */
	public static final String TERMO_USUARIO = "termo.usuario";

	/**
	 * Chave do termo "Verde"
	 */
	public static final String TERMO_VERDE = "termo.verde";

	/**
	 * Chave do termo "Vermelho"
	 */
	public static final String TERMO_VERMELHO = "termo.vermelho";

	/**
	 * Chave do termo "Organizacao"
	 */
	public static final String TERMO_ORGANIZACAO = "termo.organizacao";

	/**
	 * Chave do termo "Cadastro de Conhecimento"
	 */
	public static final String TERMO_CADASTRO_CONHECIMENTO = "termo.cadastro_conhecimento";

	/**
	 * Chave do termo "Processo"
	 */
	public static final String TERMO_PROCESSO = "termo.processo";

	/**
	 * Chave do termo "Paradigma"
	 */
	public static final String TERMO_PARADIGMA = "termo.paradigma";

	/**
	 * Chave do termo "Tipo de Software"
	 */
	public static final String TERMO_TIPO_SOFTWARE = "termo.tipo_software";

	/**
	 * Chave do termo "Ferramenta de Software"
	 */
	public static final String TERMO_FERRAMENTA_SOFTWARE = "termo.ferramenta_software";
	
	/**
	 * Chave do termo "Tipo de Artefato"
	 */
	public static final String TERMO_TIPO_K_ARTEFATO = "termo.tipo_k_artefato";

	/**
	 * Chave do termo "Tipo de Artefato"
	 */
	public static final String TERMO_K_ARTEFATO = "termo.k_artefato";

	/**
	 * Chave do termo "Dominio da Aplicação"
	 */
	public static final String TERMO_DOMINIO_DA_APLICACAO = "termo.dominio_da_aplicacao";

	/**
	 * Chave do termo "Processo Padrao"
	 */
	public static final String TERMO_PROCESSO_PADRAO = "termo.processo_padrao";

	/**
	 * Chave do termo "CompPP"
	 */
	public static final String TERMO_COMPPP = "termo.compp";
	
	/**
	 * Chave do termo "Categoria"
	 */
	public static final String TERMO_CATEGORIA = "termo.categoria";
	
	/**
	 * Chave do termo "CompPPProcessoSimples"
	 */
	public static final String TERMO_COMPPP_PROCESSO_SIMPLES = "termo.compp_processo_simples";
	
	/**
	 * Chave do termo "CompPPProcessoComplexo"
	 */
	public static final String TERMO_COMPPP_PROCESSO_COMPLEXO = "termo.compp_processo_complexo";
	
	/**
	 * Chave do termo "CompPPMacroativdade"
	 */
	public static final String TERMO_COMPPP_MACROATIVIDADE = "termo.compp_macroatividade";
	
	/**
	 * Chave do termo "Obejtivo""
	 */
	public static final String TERMO_OBJETIVO = "termo.objetivo";
		
	/**
	 * Chave do termo "Sigla"
	 */
	public static final String TERMO_SIGLA = "termo.sigla";
	
	/**
	 * Chave do termo "Resultados Esperados"
	 */
	public static final String TERMO_RESULTADOS_ESPERADOS = "termo.resultados_esperados";

	/**
	 * Chave do termo "Tipos de Interação"
	 */
	public static final String TERMO_TIPOS_INTERACAO = "termo.tipos_interacao";
	
	/**
	 * Chave do termo "Propósito"
	 */
	public static final String TERMO_PROPOSITO = "termo.proposito";
	
	/**
	 * Chave do termo "Sim"
	 */
	public static final String TERMO_SIM = "termo.sim";
	
	/**
	 * Chave do termo "Não"
	 */
	public static final String TERMO_NAO = "termo.nao";
	
	/**
	 * Chave do termo "Granularidade"
	 */
	public static final String TERMO_GRANULARIDADE = "termo.granularidade";
	
	/**
	 * Chave do termo "Tipo"
	 */
	public static final String TERMO_TIPO = "termo.tipo";
	
	/**
	 * Chave do termo "Katividade"
	 */
	public static final String TERMO_KATIVIDADE = "termo.katividade";
	
	/**
	 * Chave do termo "Katividade"
	 */
	public static final String TERMO_CADASTRO_RECURSO = "termo.cadastro.recurso";
	
	/**
	 * Chave do termo "KRecursoHumano"
	 */
	public static final String TERMO_kRECURSO_HUMANO = "termo.recurso.humano";
	
	/**
	 * Chave do termo "KRecursoHardware"
	 */
	public static final String TERMO_kRECURSO_HARDWARE = "termo.recurso.hardware";
	
	/**
	 * Chave do termo "Projeto"
	 */
	public static final String TERMO_PROJETO = "termo.projeto";
	
	/**
	 * Chave do termo "Selecionar CompPP Base"
	 */
	public static final String TERMO_SELECIONAR_COMPPP_BASE = "termo.selecionar.comppp.base";
	
	/**
	 * Chave do termo "Processo Especializado"
	 */
	public static final String TERMO_PROCESSO_ESPECIALIZADO = "termo.processo.especializado";
	
	/**
	 * Chave do termo "Estabelecer Requisitos"
	 */
	public static final String TERMO_ESTABELECER_REQUISITOS = "termo.estabelecer.requisitos";
	
	/**
	 * Chave do termo "Finalizar Definição"
	 */
	public static final String TERMO_FINALIZAR_DEFINICAO = "termo.finalizar.definicao";
	
	/**
	 * Chave do termo "Definir Modelo de Ciclo de Vida"
	 */
	public static final String TERMO_DEFINIR_MCV = "termo.definir.mcv";
	
	/**
	 * Chave do termo "Ferramentas"
	 */
	public static final String TERMO_FERRAMENTAS = "termo.ferramentas";
	
	/**
	 * Chave do termo "Definição de Processos"
	 */
	public static final String TERMO_DEFINICAO_PROCESSOS = "termo.definicao.processos";
	
	/**
	 * Chave do termo "Recursos"
	 */
	public static final String TERMO_RECURSOS = "termo.recursos";
	
	/**
	 * Chave do termo "Produtos"
	 */
	public static final String TERMO_PRODUTOS = "termo.produtos";
	
	/**
	 * Chave do termo "Insumos"
	 */
	public static final String TERMO_INSUMOS = "termo.insumos";
	/**
	 * Chave do termo "Procedimentos"
	 */
	public static final String TERMO_PROCEDIMENTOS = "termo.procedimentos";
	/**
	 * Chave do termo "subAtividades"
	 */
	public static final String TERMO_SUBATIVIDADE = "termo.subatividades";
	/**
	 * Chave do termo "preAtividades"
	 */
	public static final String TERMO_PREATIVIDADE = "termo.preatividades";
	
	/**
	 * Chave do termo "Dados Gerais"
	 */
	public static final String TERMO_DADOS_GERAIS = "termo.dados.gerais";
	
	/**
	 * Chave do termo "Cadastro de Processo"
	 */
	public static final String TERMO_CADASTRO_PROCESSO = "termo.cadastro.processo";
	
	/**
	 * Chave do termo "Caracteristicas Gerais"
	 */
	public static final String TERMO_CARACTERISTICAS_GERAIS = "termo.caracteristicas.gerais";
	
	/**
	 * Chave do termo "Modelo Ciclo Vida"
	 */
	public static final String TERMO_MCV = "termo.MCV";
	
	/**
	 * Chave do termo "Modelo Ciclo Vida"
	 */
	public static final String TERMO_ATIVO_PROCESSOS = "termo.ativo.processos";
	
	/**
	 * Chave do termo "Novo Projeto"
	 */
	public static final String TERMO_NOVO_PROJETO = "termo.novo.projeto";
	
	/**
	 * Chave do termo "Definir Processo"
	 */
	public static final String TERMO_DEFINIR_PROCESSO = "termo.definir.processo";
	
	public static final String MSG_OBJETIVO_ESTRATEGICO_EMPTY_ERRO = "msg.objetivo.estrategico.empty.erro";

	public static final String MSG_OBJETIVO_SOFTWARE_EMPTY_ERRO = "msg.objetivo.software.empty.erro";

	public static final String TERMO_VALORES_DE_ESCALA = "termo.valores.de.escala";

	public static final String TERMO_PROPRIEDADE_MEDIDA = "termo.propriedade.medida";

	public static final String TERMO_MNEMONICO = "termo.mnemonico";

	public static final String TERMO_UNIDADE_MEDIDA = "termo.unidade.medida";

	public static final String TERMO_NECESSIDADE_INFORMACAO = "termo.necessidade.informacao";

	public static final String TERMO_MEDIDA = "termo.medida";
		

	public static final String TERMO_OBJETIVOS_ESTRATEGICOS = "termo.objetivos.estrategicos";
	
	public static final String TERMO_OBJETIVO_ESTRATEGICO = "termo.objetivo.estrategico";
	

	public static final String TERMO_OBJETIVOS_SOFTWARE = "termo.objetivos.software";
	
	public static final String TERMO_OBJETIVO_SOFTWARE = "termo.objetivo.software";

	public static final String TERMO_OBJETIVOS_MEDICAO = "termo.objetivos.medicao";
	
	public static final String TERMO_OBJETIVO_MEDICAO = "termo.objetivo.medicao";

	public static final String TERMO_ALTERAR = "termo.alterar";

	public static final String TERMO_ELEMENTO_MENSURAVEL = "termo.elemento.mensuravel";

	public static final String TERMO_ESCALA = "termo.escala";

	public static final String TERMO_VALOR_ESCALA = "termo.valor.escala";

	public static final String TERMO_APLICACAO_DEFINICAO_OPERACIONAL = "termo.aplicacao.definicao.operacional";

	public static final String TERMO_DATA = "termo.data";

	public static final String TERMO_INTERVALO = "termo.intervalo";

	public static final String TERMO_DADOS_POSSIVEIS_VALORES = "termo.dados.possiveis.valores";
	
	public static final String TERMO_CADASTRO_DE_CARACTERISTICAS = "termo.cadastro.de.caracteristicas";
	
	public static final String TERMO_INCLUIR = "termo.incluir";
	
	public static final String TERMO_DADOS_DE_CARACTERISTICA = "termo.dados.de.caracteristica";
	
	public static final String TERMO_VALOR_ORDENADO = "termo.valor.ordenado";
	
	public static final String TERMO_VALOR_NAO_ORDENADO = "termo.valor.nao.ordenado";
	
	public static final String TERMO_VALOR = "termo.valor";

	public static final String TERMO_VALOR_MAXIMO = "termo.valor.maximo";

	public static final String TERMO_VALOR_MINIMO = "termo.valor.minimo";
	
	public static final String TERMO_PERMITE_CRIACAO_DE_POSSIVEL_VALOR = "termo.permite.criacao.de.possivel.valor";
	
	public static final String TERMO_PERMITE_ATRIBUICAO_DE_MULTI_VALOR = "termo.permite.atribuicao.de.multi.valor";

	public static final String TERMO_PERSPECTIVA_ANALISE = "termo.perspectiva.analise";
	
	public static final String TERMO_POSSIVEIS_VALORES = "termo.possiveis.valores";
	
	public static final String TERMO_DADOS_DO_VALOR = "termo.dados.do.valor";

	
}

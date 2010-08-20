package nucleo.comuns.util;

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
	public static final String TERMO_IDIOMA = "termo.idioma";
	
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
	 * Chave do termo "Tipo de Artefato"
	 */
	public static final String TERMO_TIPO_ARTEFATO = "termo.tipo_artefato";

}



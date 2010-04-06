package nucleo.comuns.visao;

import java.util.Map;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;

import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;


/**
 * Esta classe é uma janela do framework Zkoss com alguns propriedades
 * pré-definidas.
 * 
 * @author Alexandre G. N. Coelho
 */
public abstract class NucleoWindow extends Window {

	/** Tamanho da window. */
	public static String WIDTH_WINDOW = "450px";

	/**
	 * Parâmetros passados para a janela.
	 */
	private Map<String, Object> parametros;

	/**
	 * Quando necessário, esse método deve ser implementado caso ocorra alguma
	 * excecao.
	 */
	protected void acaoExcecao(Exception e) {
	}

	@SuppressWarnings("unchecked")
	public NucleoWindow() {
		parametros = Executions.getCurrent().getArg();
		this.setSclass("embedded");
		this.setContentStyle("background:white");
	}

	/**
	 * Método utilizado para obter o objeto associado ao parâmetro procurado. Se
	 * o parâmetro não existir, retorna null.
	 * 
	 * @param nomeParametro
	 *            Nome do parâmetro procurado.
	 * @return O objeto associado ao parâmetro se o mesmo existir. Se o
	 *         parâmetro não existir, retorna null.
	 */
	public Object obterParametro(String nomeParametro) {
		return parametros.get(nomeParametro);
	}

	/** Evento executado ao criar a window */
	public void onCreate() {
		this.configurarPropriedadesPadrao();
		this.onCreateWindow();
	}

	/** Iniciar componentes gráficos */
	private void configurarPropriedadesPadrao() {
		// configuracoes default da classe window
		this.setTitle(getTituloWindow());
		this.setBorder("normal");
		this.setClosable(true);
		this.setWidth(WIDTH_WINDOW);
		this.setPosition("&quot;center;&quot;;");
		this.setZIndex(10);
	}

	/** Método abstrato para obter o título da window. */
	protected abstract String getTituloWindow();

	/** Método abstrato que é executado na crição da window */
	public abstract void onCreateWindow();

	/**
	 * Exibe janela de erro apresentando a mensagem de erro passada.
	 * 
	 * @param mensagemErro
	 *            Mensagem de erro a ser exibida na janela de erro
	 */
	public static void exibirJanelaErro(String mensagemErro) {
		try {
			Messagebox.show(mensagemErro, NucleoMensagens
					.getMensagem(NucleoMensagens.TERMO_ERRO), Messagebox.OK,
					Messagebox.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exibe janela de erro apresentando a mensagem de erro de acordo com a
	 * exceção passada
	 * 
	 * @param excecao
	 *            Exceção para a qual o erro deve ser exibido
	 */
	public static void exibirJanelaErro(Exception excecao) {

		// Inicializa a mensagem de erro com "Erro Desconhecido!"
		String mensagemErro = NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_ERRO_DESCONHECIDO);

		// Altera a mensagem de erro se for uma exceção conhecida
		if (excecao instanceof DataAccessException) {
			mensagemErro = NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_ERRO_ACESSO_BD);
		} else if (excecao instanceof NucleoRegraNegocioExcecao) {
			mensagemErro = ((NucleoRegraNegocioExcecao) excecao).getMensagem();
		} else if (excecao instanceof WrongValueException) {
			mensagemErro = excecao.getMessage();
		}

		excecao.printStackTrace();

		// Exibe a janela de erro
		exibirJanelaErro(mensagemErro);

	}

}

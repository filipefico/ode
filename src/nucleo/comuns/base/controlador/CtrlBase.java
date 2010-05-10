package nucleo.comuns.base.controlador;

import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.principal.JanelaSimples;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

public abstract class CtrlBase {

	///caminho de todas as janelas simples utilizadas no sistema	
	public static final String CAMINHO_JANELA_SIMPLES = "/visao/principal/janelaSimples.zul";

	public JanelaSimples factoryJanelaSimples() {

		return (JanelaSimples) Executions.createComponents(CAMINHO_JANELA_SIMPLES, null, null);
	}

	public boolean confirmaSimNao(String mensagemConfirmacao)
			throws InterruptedException {
		return Messagebox.show(mensagemConfirmacao, NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CONFIRMACAO), Messagebox.YES
				| Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES;

	}
	

	public void mostrarJanelaInformacao(String mensagem)
			throws InterruptedException {

		Messagebox.show(mensagem, NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_INFORMACAO), Messagebox.OK,
				Messagebox.INFORMATION);

	}

	public void mostrarJanelaAviso(String mensagem) throws InterruptedException {

		Messagebox.show(mensagem, NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
				Messagebox.INFORMATION);

	}
	
	public abstract void configurarComponentes();

}

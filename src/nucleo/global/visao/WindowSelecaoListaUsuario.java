package nucleo.global.visao;

import nucleo.comuns.autenticacao.acegi.aplicacao.NucleoAplCadastrarNucleoUserDetails;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.NucleoWindowSelecaoLista;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;


public class WindowSelecaoListaUsuario extends
		NucleoWindowSelecaoLista<NucleoUserDetails> {

	private static final long serialVersionUID = -7831513838159785097L;

	private NucleoAplCadastrarNucleoUserDetails nucleoAplCadastrarNucleoUserDetails;

	@SuppressWarnings("unchecked")
	public WindowSelecaoListaUsuario() {

		nucleoAplCadastrarNucleoUserDetails = ((NucleoAplCadastrarNucleoUserDetails) SpringUtil
				.getBean("nucleoUserDetailsService"));

		parametros = Executions.getCurrent().getArg();

		try {
			this.preencherLista(nucleoAplCadastrarNucleoUserDetails
					.recuperarTodos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onCreateWindow() {
		this.setWidth("414px");
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_SELECAO_USUARIOS);
	}

	@Override
	protected String[] definirTamanhosCabecalho() {
		return new String[] { "300px"};
	}

	@Override
	protected String[] definirTitulosCabecalho() {
		return new String[] {
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME)};
	}

	@Override
	protected String[] recuperarDadosObjeto(NucleoUserDetails nucleoUserDetails) {

		return new String[] { nucleoUserDetails.getPessoa().getNome() };
	}

	@Override
	protected void acaoAbrirItemSelecionado() {
		Listbox lbxUsuario = ((Listbox) parametros.get("lbxUsuario"));
		lbxUsuario.getItems().clear();
		NucleoUserDetails usuario = (NucleoUserDetails) super.listBox
				.getSelectedItem().getValue();
		Listitem listitem = new Listitem(usuario.getPessoa().getNome());
		listitem.setValue(usuario);
		lbxUsuario.appendChild(listitem);
		lbxUsuario.setSelectedIndex(0);
		this.detach();
	}
}

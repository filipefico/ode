package ode.middlewareIssueTracker.ciu;

import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class JanUsuarioMantis extends Window {

	private static final long serialVersionUID = 1L;
	
	private CtrlUsuarioMantis ctrlUsuarioMantis;
	
	private Textbox textUsuario;
	private Textbox textSenha;

	private UsuarioMantis usuarioMantis;
	
	public JanUsuarioMantis(CtrlUsuarioMantis ctrl){
		super();		

		this.setTitle("Gerenciar Usuário MantisBT");
		this.setClosable(true);
		
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
		this.ctrlUsuarioMantis = ctrl;
		
		this.setWidth("400px");
		this.setPosition("&quot;center;&quot;;");
		this.setBorder("normal");
		
		textUsuario = new Textbox();
		textSenha = new Textbox();
		
		textUsuario.setConstraint("no empty:Campo Obrigatório");
		textSenha.setConstraint("no empty:Campo Obrigatório");
		
		usuarioMantis = ctrlUsuarioMantis.recuperarUsuarioMantisAtual();
		if(usuarioMantis != null){
			textUsuario.setValue(usuarioMantis.getUsuarioMantis());
			textSenha.setValue(usuarioMantis.getSenhaMantis());
		} else{
			usuarioMantis = new UsuarioMantis();
		}
		
		
		GridDados gridDados = new GridDados();
		gridDados.setLarguras("30%", "70%");
		gridDados.setAlinhamento("right", "left");
		
		textUsuario.setWidth("100%");
		gridDados.adicionarLinha("Usuário MantisBT", textUsuario);
		
		textSenha.setWidth("100%");
		textSenha.setType("password");
		gridDados.adicionarLinha("Senha MantisBT", textSenha);
		
		Button btSalvar = new Button("Salvar");
		btSalvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ajustaUsuarioMantis();
				usuarioMantis.setUsuario(NucleoContexto.recuperarUsuarioLogado());
				
				ctrlUsuarioMantis.salvarUsuarioMantisAtual(usuarioMantis);
				onClose();
			}
		});
		
		gridDados.adicionarLinhaUnica(btSalvar);
		
		gridDados.setParent(this);		
		
		
	}
	
	private void ajustaUsuarioMantis(){
		usuarioMantis.setUsuarioMantis(textUsuario.getValue());
		usuarioMantis.setSenhaMantis(textSenha.getValue());
	}
	
	public void mostrar(){
		doOverlapped();
	}
}

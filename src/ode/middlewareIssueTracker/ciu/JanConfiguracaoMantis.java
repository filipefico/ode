package ode.middlewareIssueTracker.ciu;

import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.middlewareIssueTracker.cdp.ConfiguracaoMantis;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class JanConfiguracaoMantis extends Window {

	private static final long serialVersionUID = 1L;
	
	private CtrlConfiguracaoMantis ctrlConfiguracaoMantis;

	private Textbox textUsuario;
	private Textbox textSenha;
	private Textbox textUrl;
	
	private UsuarioMantis usuarioMantisPadrao;
	private ConfiguracaoMantis configuracaoMantis;
	
	public JanConfiguracaoMantis(CtrlConfiguracaoMantis ctrl){
		super();
		
		this.setTitle("Gerenciar Configuração MantisBT");
		this.setClosable(true);
		
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
		this.ctrlConfiguracaoMantis = ctrl;
		
		this.setWidth("600px");
		this.setSizable(true);
		this.setPosition("&quot;center;&quot;;");
		this.setBorder("normal");
		
		textUsuario = new Textbox();
		textSenha = new Textbox();
		textUrl = new Textbox();
		
		textUsuario.setConstraint("no empty:Campo Obrigatório");
		textSenha.setConstraint("no empty:Campo Obrigatório");
		textUrl.setConstraint("no empty:Campo Obrigatório");
		
		configuracaoMantis = ctrlConfiguracaoMantis.recuperarConfiguracaoMantisPadrao();
		
		if(configuracaoMantis != null){
			usuarioMantisPadrao = configuracaoMantis.getUsuarioMantisPadrao();
			
			textUsuario.setValue(usuarioMantisPadrao.getUsuarioMantis());
			textSenha.setValue(usuarioMantisPadrao.getSenhaMantis());
			textUrl.setValue(configuracaoMantis.getUrl());
			
		}else{
			configuracaoMantis = new ConfiguracaoMantis();
			usuarioMantisPadrao = new UsuarioMantis();
			configuracaoMantis.setUsuarioMantisPadrao(usuarioMantisPadrao);
		}
		
		GridDados gridDados = new GridDados();
		gridDados.setLarguras("30%", "70%");
		gridDados.setAlinhamento("right", "left");
		
		
		textUrl.setWidth("100%");
		gridDados.adicionarLinha("Url Serviço MantisBT", textUrl);
		
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
				ajustarConfiguracaoMantis();
				
				ctrlConfiguracaoMantis.salvarConfiguracaoMantisPadrao(configuracaoMantis);
				onClose();
			}
		});
		gridDados.adicionarLinhaUnica(btSalvar);
		
		gridDados.setParent(this);
		
		
	}
	
	private void ajustarConfiguracaoMantis(){
		
		usuarioMantisPadrao.setUsuarioMantis(textUsuario.getValue());
		usuarioMantisPadrao.setSenhaMantis(textSenha.getValue());
		
		configuracaoMantis.setUrl(textUrl.getValue());
		configuracaoMantis.setUsuarioMantisPadrao(usuarioMantisPadrao);		
	}
	
	
	public void mostrar(){
		doOverlapped();
	}
}

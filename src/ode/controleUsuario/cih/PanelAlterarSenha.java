package ode.controleUsuario.cih;

import ode.controleUsuario.cci.AlterarSenhaCtrl;
import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoContexto;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;

public class PanelAlterarSenha extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NucleoUserDetails usuario;
	
	private AlterarSenhaCtrl alterarSenhaCtrl;
	
	public PanelAlterarSenha(AlterarSenhaCtrl alterarSenhaCtrl){
		
		super();
		
		Panelchildren panelchildren = new Panelchildren();
		panelchildren.setParent(this);
		
		this.alterarSenhaCtrl = alterarSenhaCtrl;
		
		usuario = NucleoContexto.recuperarUsuarioLogado();
		
		Grid grid = new Grid();
		
		grid.setParent(panelchildren);
		
		Rows rows = new Rows();
		rows.setParent(grid);
		
		Row row = new Row();
		row.setParent(rows);
		
		Label label = new Label("Usuário:");
		label.setParent(row);
		
		label = new Label(usuario.getRecursoHumano().getNome());
		label.setParent(row);
		
		row = new Row();
		row.setParent(rows);
		
		label = new Label("Senha:");
		label.setParent(row);
		
		textboxSenha.setParent(row);
		textboxSenha.setType("password");
		textboxSenha.setMaxlength(20);
		textboxSenha.setWidth("150px");
		
		row = new Row();
		row.setParent(rows);
		
		label = new Label("Repita a senha:");
		label.setParent(row);
		
		textboxRepitaSenha.setParent(row);
		textboxRepitaSenha.setType("password");
		textboxRepitaSenha.setMaxlength(20);
		textboxRepitaSenha.setWidth("150px");

		(new Separator()).setParent(panelchildren);
		
		Div div = new Div();
		div.setParent(panelchildren);
		div.setAlign("right");
		
		Button buttonCancelar = new Button("Cancelar");
		buttonCancelar.setWidth("80px");
		buttonCancelar.setParent(div);
		buttonCancelar.addEventListener("onClick", new EventListener() {
			
			public void onEvent(Event arg0) throws Exception {
				fecharJanela();
			}
		});	
		
		Button buttonSalvar = new Button("Salvar");
		buttonSalvar.setWidth("80px");
		buttonSalvar.setParent(div);
		buttonSalvar.addEventListener("onClick", new EventListener() {
			
			public void onEvent(Event arg0) throws Exception {
				salvarDados();
			}
			
		});
		
	}
	
	/**
	 * Salva os dados do usuário.
	 */
	public void salvarDados(){
		
		// Verifica
		
		if (!textboxSenha.getValue().equals(textboxRepitaSenha.getValue())) {
			try {
				Messagebox.show("As senhas devem ser iguais.");
				return;
			} catch (InterruptedException e) {
				
			}
		}
		
		if (textboxSenha.getValue().isEmpty() || textboxRepitaSenha.getValue().isEmpty()) {
			try {
				Messagebox.show("A senha não pode ser nula.");
				return;
			} catch (InterruptedException e) {
				
			}
		}
		
		// Atualiza os dados
		usuario.setPassword(new Md5PasswordEncoder()
		.encodePassword(textboxSenha.getValue(), null));
		
		try {
			// Salva os dados
			alterarSenhaCtrl.getAplCadastrarUsuario().salvar(usuario);
			fecharJanela();
		} catch (NucleoRegraNegocioExcecao e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Fechar janela.
	 */
	public void fecharJanela(){
		alterarSenhaCtrl.finalizar();
	}

	Textbox textboxSenha = new Textbox();
	
	Textbox textboxRepitaSenha = new Textbox();
}

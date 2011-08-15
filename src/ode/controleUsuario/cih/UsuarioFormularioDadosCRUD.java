package ode.controleUsuario.cih;

import java.util.ArrayList;
import java.util.List;

import ode.controleProcesso.cih.RecursoHuamanoBandbox;
import ode.controleUsuario.cci.UsuarioCtrlCRUD;
import ode.controleUsuario.cdp.GrantedAuthorityImpl;
import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

public class UsuarioFormularioDadosCRUD extends
FormularioDadosCRUD<NucleoUserDetails> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected List definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		
		UsuarioCtrlCRUD ctrl= (UsuarioCtrlCRUD) this.getControlador();
		recursoHuamanoBandbox.preencherLista(ctrl.getAplCadastrarRecursoHumano().recuperarTodos());
		gridDadosCadastro.adicionarLinhaObrigatoria("Recurso Humano", recursoHuamanoBandbox);
		
		tbLogin.setWidth("200px");
		tbLogin.setMaxlength(100);		
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_LOGIN),tbLogin);
		
		tbSenha.setWidth("200px");
		tbSenha.setType("password");
		tbSenha.setMaxlength(15);		
		gridDadosCadastro.adicionarLinhaObrigatoria("Senha",tbSenha);
		
		tbSenha2.setWidth("200px");
		tbSenha2.setType("password");
		tbSenha2.setMaxlength(15);		
		
		listboxPerfilAcesso.setWidth("200px");
		listboxPerfilAcesso.setRows(1);
		listboxPerfilAcesso.setMold("select");
		List<PerfilAcesso> lista = new ArrayList(((UsuarioCtrlCRUD)this.getControlador()).getAplCadastrarPerfilAcesso().recuperarTodos());
		for (int i=0; i<lista.size(); i++){
			PerfilAcesso objeto = lista.get(i);
			Listitem listitem = new Listitem(objeto.getNome());
			listitem.setValue(objeto);
			listboxPerfilAcesso.appendChild(listitem);
		}
		listboxPerfilAcesso.setSelectedIndex(0);
		gridDadosCadastro.adicionarLinhaObrigatoria("Perfil de Acesso",listboxPerfilAcesso);

		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(NucleoUserDetails objeto) {		
		objeto.setRecursoHumano(recursoHuamanoBandbox.getObjetoSelecionado());
		objeto.setUsername(tbLogin.getValue());
		
		// Só modifica a senha caso algo seja digitado
		if (tbSenha.getValue().length() > 0) {
			objeto.setPassword(new Md5PasswordEncoder()
					.encodePassword(tbSenha.getValue(), null));
		}
		
		////////////////////////////
		// Configurações Acegi
		////////////////////////////
		
		// Limpa as authorities
		objeto.getGrantedAuthorities().clear();

		// Cria nova authority
		GrantedAuthorityImpl nucleoGrantedAuthority = new GrantedAuthorityImpl();
		nucleoGrantedAuthority
					.setAuthority(GrantedAuthorityImpl.AUTHORITY_USUARIO_COMUM);
		
		// Adiciona nova authority
		objeto.getGrantedAuthorities().add(nucleoGrantedAuthority);
		
		//////////////////////////////
		// Perfil de Acesso
		//////////////////////////////
		
		objeto.setPerfilAcesso((PerfilAcesso)listboxPerfilAcesso.getSelectedItem().getValue());
		
	}

	@Override
	protected void preencherDadosTela(NucleoUserDetails objeto) throws NucleoRegraNegocioExcecao {
		recursoHuamanoBandbox.setObjetoSelecionado(objeto.getRecursoHumano());
		tbLogin.setValue(objeto.getUsername());
		List<Listitem> listItems = listboxPerfilAcesso.getItems();
		for (Listitem item : listItems) {
			if (item.getValue().equals(objeto.getPerfilAcesso())) {
				listboxPerfilAcesso.selectItem(item);
				break;
			}
		}
	}

	@Override
	protected void configurarConstraints() {
		tbLogin.setConstraint("no empty");
	}
	
	private RecursoHuamanoBandbox recursoHuamanoBandbox = new RecursoHuamanoBandbox();
	
	private Textbox tbLogin = new Textbox();
	
	private Textbox tbSenha = new Textbox();

	private Textbox tbSenha2 = new Textbox();

	private Listbox listboxPerfilAcesso = new Listbox();

}
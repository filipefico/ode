package ode.controleUsuario.cih;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ode._controleRecursoHumano.cih.RecursoHumanoBandbox;
import ode.controleUsuario.cci.CtrlUsuarioCRUD;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.cdp.Usuario;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.nucleo.crud.cih.NucleoListbox;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;
import ode.nucleo.util.NucleoUtil;

import org.zkoss.zul.Textbox;

public class FormDadosUsuario extends
FormularioDadosCRUD<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		
		CtrlUsuarioCRUD ctrl= (CtrlUsuarioCRUD) this.getControlador();
		recursoHumanoBandbox.setObjetos(ctrl.listarRecursosHumanos());
		gridDadosCadastro.adicionarLinhaObrigatoria("Recurso Humano", recursoHumanoBandbox);
		
		tbLogin.setWidth("200px");
		tbLogin.setMaxlength(100);		
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_LOGIN),tbLogin);
		
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
		
		PerfilAcesso[] lista = PerfilAcesso.values();
		listboxPerfilAcesso.setObjetos(Arrays.asList(lista));
		listboxPerfilAcesso.selecionarPrimeiroElemento();
		gridDadosCadastro.adicionarLinhaObrigatoria("Perfil de Acesso",listboxPerfilAcesso);

		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(Usuario objeto) {
		
		objeto.setRecursoHumano(recursoHumanoBandbox.getObjetoSelecionado());
		objeto.setNomeUsuario(tbLogin.getValue());
		
		// Só modifica a senha caso algo seja digitado
		if (tbSenha.getValue().length() > 0) {
			objeto.setSenha(NucleoUtil.encrypt(tbSenha.getValue()));
		}
		objeto.setPerfilAcesso(listboxPerfilAcesso.getObjetoSelecionado());

	}

	@Override
	protected void preencherDadosTela(Usuario objeto) throws NucleoRegraNegocioExcecao {
		recursoHumanoBandbox.setObjetoSelecionado(objeto.getRecursoHumano());
		tbLogin.setValue(objeto.getNomeUsuario());
		listboxPerfilAcesso.setObjetoSelecionado(objeto.getPerfilAcesso());
	}

	@Override
	protected void configurarConstraints() {
		tbLogin.setConstraint("no empty");
	}
	
	@Override
	protected boolean isValido() {
		if (recursoHumanoBandbox.getObjetoSelecionado() == null)
			disparaErro(recursoHumanoBandbox, "É necessário informar um Recurso Humano!");
		return super.isValido();
	}
	
	private RecursoHumanoBandbox recursoHumanoBandbox = new RecursoHumanoBandbox();
	
	private Textbox tbLogin = new Textbox();
	
	private Textbox tbSenha = new Textbox();

	private Textbox tbSenha2 = new Textbox();

	private NucleoListbox<PerfilAcesso> listboxPerfilAcesso = new NucleoListbox<PerfilAcesso>();

}
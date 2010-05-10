package ode.exemplo2.pessoa.controlador;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.exemplo.aplicacao.AplCadastrarPessoaExemplo;
import ode.exemplo.dominio.PessoaExemplo;
import ode.exemplo2.pessoa.visao.FormDadosPessoaExemplo;
import ode.exemplo2.pessoa.visao.PainelCrudPessoa;
import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlPessoaCRUD extends CtrlCRUD<PessoaExemplo> {

	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarPessoaExemplo) SpringUtil
				.getBean("aplCadastrarPessoaExemplo");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudPessoa();
		
	}


	@Override
	public PessoaExemplo factoryObjetoDados() {
		return new PessoaExemplo();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosPessoaExemplo();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Pessoa";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Pessoa com Controlador";
	}
	



}

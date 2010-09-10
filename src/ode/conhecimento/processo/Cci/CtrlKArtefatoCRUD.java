package ode.conhecimento.processo.Cci;

import org.zkoss.zkplus.spring.SpringUtil;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cgt.AplCadastrarKArtefato;
import ode.conhecimento.processo.Cih.FormDadosKArtefato;
import ode.conhecimento.processo.Cih.PainelCrudKArtefato;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;

public class CtrlKArtefatoCRUD extends CtrlCRUD<KArtefato> {

	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Override
	public FormularioDadosCRUD<KArtefato> definirFormularioCadastro() {
		return new FormDadosKArtefato();
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase<KArtefato> definirNucleoAplCadastroBase() {
		return (AplCadastrarKArtefato) SpringUtil
				.getBean("aplCadastrarKArtefato");
	}

	@Override
	public PainelCRUD<KArtefato> definirPainelCRUD() {
		return new PainelCrudKArtefato();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "KArtefato";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de artefato";
	}

	@Override
	public KArtefato factoryObjetoDados() {
		return new KArtefato();
	}

}

package ode._controleRecursoHumano.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.ciu.CtrlRecursoHumanoCRUD;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

public class FormDadosRecursoHumano extends FormularioDadosCRUD<RecursoHumano> {

	private static final long serialVersionUID = 1L;

	protected Textbox tbNome = new Textbox();
	protected Intbox tbCargaHoraria = new Intbox();
	protected Textbox tbTelefone = new Textbox();
	protected Textbox tbEmail = new Textbox();
	protected Checkbox cbAtivo = new Checkbox();
	protected NucleoListbox<KRecursoHumano> listboxCargo = new NucleoListbox<KRecursoHumano>();

	protected Collection<KRecursoHumano> listaKRH;
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
		return listaTabs;
	}
	
	protected NucleoTab definirTabDadosCadastro() {
		
		
		NucleoTab tabDadosCadastro = new NucleoTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		
		tbNome.setWidth("240px");
		tbNome.setMaxlength(80);
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME), tbNome);

		cbAtivo.setChecked(true);
		gridDadosCadastro.adicionarLinhaObrigatoria("Ativo", cbAtivo);

		tbCargaHoraria.setWidth("25px");
		tbCargaHoraria.setMaxlength(3);
		gridDadosCadastro.adicionarLinhaObrigatoria("Carga Horária", tbCargaHoraria);
		
		tbEmail.setWidth("240px");
		gridDadosCadastro.adicionarLinhaObrigatoria("E-mail", tbEmail);
		
		tbTelefone.setWidth("240px");
		gridDadosCadastro.adicionarLinhaObrigatoria("Telefone", tbTelefone);

		listboxCargo.setWidth("240px");
		listboxCargo.setRows(1);
		listboxCargo.setMold("select");
		listboxCargo.setPrimeiroItem("< selecione o Cargo >");
		
		gridDadosCadastro.adicionarLinhaObrigatoria("Cargo", listboxCargo);
		
		try {
			listaKRH = ((CtrlRecursoHumanoCRUD) getControlador()).listarKRecursosHumanos();
			listboxCargo.setObjetos(listaKRH);
		}
		catch(Exception e) {
			
		}

		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		return tabDadosCadastro;
	}


	@Override
	protected void preencherDadosObjeto(RecursoHumano objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setCargaHoraria(tbCargaHoraria.getValue());
		objeto.setAtivo(cbAtivo.isChecked());
		objeto.setTelefone(tbTelefone.getValue());
		objeto.setEmail(tbEmail.getValue());
		objeto.setCargo(listboxCargo.getObjetoSelecionado());
	}

	@Override
	protected void preencherDadosTela(RecursoHumano objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbCargaHoraria.setValue(objeto.getCargaHoraria());
		cbAtivo.setChecked(objeto.isAtivo());
		tbEmail.setValue(objeto.getEmail());
		tbTelefone.setValue(objeto.getTelefone());
		listboxCargo.setObjetoSelecionado(objeto.getCargo());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty: Favor informar o Nome!");
		tbCargaHoraria.setConstraint("no empty: Favor informar a Carga Horária!");
		tbTelefone.setConstraint("no empty: Favor informar o Telefone!");
		tbEmail.setConstraint("no empty: Favor informar o E-mail!");
	}
	
	@Override
	protected boolean isValido(){
		if(listboxCargo.getObjetoSelecionado()==null)
			throw new WrongValueException(listboxCargo, "Favor informar o Cargo!");
		return super.isValido();
	}

}
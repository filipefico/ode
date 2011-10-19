package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Textbox;

import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimentoMedicao.cci.CtrlKNecessidadeInformacaoCRUD;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoMedicao;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

public class FormDadosKNecessidadeInformacao extends  FormularioDadosCRUD<KNecessidadeInformacao>{

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<KObjetivoMedicao> ckMed = new NucleoMultipleListBox<KObjetivoMedicao>();
	private NucleoMultipleListBox<KProcesso> ckProc = new NucleoMultipleListBox<KProcesso>();
	private Groupbox gbMedicao = new Groupbox();
	private Groupbox gbProc = new Groupbox();
	GridDados gridDadosCadastro;
	
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
				List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

				// ////////////////////////////
				// Dados Cadastro - Objetivos Medicao Relacionados
				// ////////////////////////////
				
				CtrlKNecessidadeInformacaoCRUD ctrl = (CtrlKNecessidadeInformacaoCRUD) this.getControlador();
				AplCadastrarKObjetivoMedicao aplMed =  ctrl.getAplKObjetivoMedicao();
				Collection<KObjetivoMedicao> medicoes = aplMed.recuperarTodos();
				
				ckMed.setObjetos(medicoes);
				gbMedicao.appendChild(new Caption(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_OBJETIVOS_MEDICAO)));
				gbMedicao.appendChild(ckMed);
				
				// ////////////////////////////
				// Dados Cadastro - Processo
				// ////////////////////////////
				
				AplCadastrarKProcesso aplPrc =  ctrl.getAplKProcesso();
				Collection<KProcesso> processos = aplPrc.recuperarTodos();
				
				ckProc.setObjetos(processos);
				gbProc.appendChild(new Caption(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROCESSO)));
				gbProc.appendChild(ckProc);
				
				////////////////
				
				// ////////////////////////////
				// Dados Cadastro - Nome e Descricao
				// ////////////////////////////
				NucleoTab tabDadosCadastro = new NucleoTab();

				// Atribui o nome à tab
				tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

				// Atribui o conteúdo à tab
				gridDadosCadastro = new GridDados();
				tbNome.setWidth("385px");
				tbNome.setMaxlength(300);	
				tbNome.setHeight("35px");
				tbNome.setMultiline(true);
				gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
				
				tbDescricao.setWidth("385px");
				tbDescricao.setMaxlength(400);
				tbDescricao.setHeight("65px");
				tbDescricao.setMultiline(true);
				gridDadosCadastro.adicionarLinha(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);
				
				tabDadosCadastro.setConteudoTab(gridDadosCadastro);

				return listaTabs;
	}
	
	@Override
	protected void montarTabs() {
		super.montarTabs();
		this.appendChild(gbMedicao);
		this.appendChild(gbProc);
		this.appendChild(gridDadosCadastro);
	};

	@Override
	protected void preencherDadosTela(KNecessidadeInformacao objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		ckMed.setObjetosSelecionados(objeto.getObjetivosMedicao());
		ckProc.setObjetosSelecionados(objeto.getProcessos());
	}

	@Override
	protected void preencherDadosObjeto(KNecessidadeInformacao objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setObjetivosMedicao(ckMed.getObjetosSelecionados());
		objeto.setProcessos(ckProc.getObjetosSelecionados());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}


}

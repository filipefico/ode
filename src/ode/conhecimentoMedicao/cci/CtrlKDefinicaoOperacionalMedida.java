package ode.conhecimentoMedicao.cci;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.impl.XulElement;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.KProcedimentoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKPeriodicidade;
import ode.conhecimentoMedicao.cgt.AplCadastrarKProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKProcedimentoMedicao;
import ode.conhecimentoMedicao.cih.FormDadosKDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cih.ListagemKDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cih.PainelCRUDKDefinicaoOperacionalMedida;

@Controller(CtrlKDefinicaoOperacionalMedida.NOME)
public class CtrlKDefinicaoOperacionalMedida extends CtrlCRUD<KDefinicaoOperacionalMedida>{

	public static final String NOME = "CtrlKDefinicaoOperacionalMedida";
	
	protected NucleoTab aba;
	protected CtrlKMedidaCRUD ctrlKMedida;
	
	@Autowired
	AplCadastrarKDefinicaoOperacionalMedida apl;
	
	@Autowired
	AplCadastrarKProcedimentoMedicao aplKProcedimentoMedicao;
	
	@Autowired
	AplCadastrarKRecursoHumano aplKRecursoHumano;
	
	@Autowired
	AplCadastrarKAtividade aplKAtividade;
	
	@Autowired
	AplCadastrarKPeriodicidade aplKPeriodicidade;
	
	@Autowired
	AplCadastrarKProcedimentoAnaliseMedicao aplKProcedimentoAnaliseMedicao;
	
	public final String tituloJanelaDados = "Definição Operacional de Medida";
	
	public ListagemKDefinicaoOperacionalMedida getListagemSimples(){
		return new ListagemKDefinicaoOperacionalMedida();
	}

	@Override
	public void mostrarJanelaPrincipal() {
		Vbox vb = new Vbox();
		vb.appendChild(painelCRUD);
		aba.setConteudoTab(vb);

		atualizarPesquisa();
	};
	
	public void definirAba(NucleoTab tab){
		aba = tab;
	}
	
	@Override
	public void atualizarPesquisa() {
		if(painelCRUD.getListagem().getObjetos() != null){
			painelCRUD.getListagem().preencherLista();
		}else{
			painelCRUD.getListagem().setObjetos(new ArrayList<KDefinicaoOperacionalMedida>());
		}
	}
	
	public void atualizarPesquisa(KMedida med){
		Collection<KDefinicaoOperacionalMedida> objetos =((AplCadastrarKDefinicaoOperacionalMedida)getAplCRUD()).recuperarRelacionadosMedida(med);		
		painelCRUD.getListagem().atualizar(objetos);
		atualizarPesquisa();
	}
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KDefinicaoOperacionalMedida> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KDefinicaoOperacionalMedida> definirPainelCRUD() {
		return new PainelCRUDKDefinicaoOperacionalMedida();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "";
	}

	@Override
	public FormularioDadosCRUD<KDefinicaoOperacionalMedida> definirFormularioCadastro() {
		return new FormDadosKDefinicaoOperacionalMedida();
	}

	@Override
	public KDefinicaoOperacionalMedida factoryObjetoDados() {
		return new KDefinicaoOperacionalMedida();
	}
	
	public AplCadastrarKProcedimentoMedicao getAplKProcedimentoMedicao(){
		return aplKProcedimentoMedicao;
	}

	public AplCadastrarKRecursoHumano getAplKRecursoHumano() {
		return aplKRecursoHumano;
	}

	public AplCadastrarKAtividade getAplKAtividade() {
		return aplKAtividade;
	}

	public AplCadastrarKPeriodicidade getAplKPeriodicidade() {
		return aplKPeriodicidade;
	}

	public AplCadastrarKProcedimentoAnaliseMedicao getAplKProcedimentoAnaliseMedicao() {
		return aplKProcedimentoAnaliseMedicao;
	}
	
	@Override
	public void acaoSalvar() {
		try {
			// passo os dados do formulario para o objeto antes de pega-lo
			formularioDados.atualizarObjeto();
			KDefinicaoOperacionalMedida objetoCadastro = formularioDados.getObjetoCadastroDados();
			Collection<KDefinicaoOperacionalMedida> listaTemp = painelCRUD.getListagem().getObjetos();
			listaTemp.add(objetoCadastro);
			painelCRUD.getListagem().setObjetos(listaTemp);
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}
	}
	
	@Override
	protected void excluir(Set<Listitem> objetosSelecionados) {
		Collection<KDefinicaoOperacionalMedida> listtemp = painelCRUD.getListagem().getObjetos();
		KDefinicaoOperacionalMedida defItem;
			for(Listitem item : objetosSelecionados){
				defItem = (KDefinicaoOperacionalMedida)item.getValue();
				if(defItem.isPersistente()){
					try{
						getAplCRUD().excluir(defItem);
					}catch (Exception e) {
						System.out.println("Deu zica");
					}
				}
				listtemp.remove(defItem);
			}
		painelCRUD.getListagem().setObjetos(listtemp);
		atualizarPesquisa();
	}

	public FormDadosKDefinicaoOperacionalMedida getFormDados() {
		return (FormDadosKDefinicaoOperacionalMedida) formularioDados;
	}

	public Collection<KDefinicaoOperacionalMedida> getListagemKDefinicaoOperacional() {
		return painelCRUD.getListagem().getObjetos();
	}
}

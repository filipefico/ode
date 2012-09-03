package ode.medicao.planejamentoMedicao.cci;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgd.KMedidaDAO;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.medicao.planejamentoMedicao.cdp.FaixaReferencia;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.ValorReferencia;
import ode.medicao.planejamentoMedicao.cgd.MedidaPlanoMedicaoDAO;
import ode.medicao.planejamentoMedicao.cgd.ValorReferenciaDAO;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cih.PainelValorReferencia;

@Controller
public abstract class CtrlValorReferencia extends CtrlBase{

	JanelaSimples js;

	public abstract NucleoCombobox<? extends PlanoMedicao> popularCBPlano();
	protected abstract String getTitulo();
	
	
	@Autowired
	AplCadastrarObjetivoEstrategico aplObj;
	
	@Autowired
	MedidaPlanoMedicaoDAO daoMPM;
	@Autowired
	ValorReferenciaDAO daoVR;
	
	public Collection<ObjetivoEstrategico> getObjEstrategicos(){
		return aplObj.recuperarTodos();
	}
	
	@Override
	public void iniciar() {
		js = factoryJanelaSimples();
		js.setTitle(getTitulo());
		
		
		PainelValorReferencia pvr = new PainelValorReferencia();
		
		pvr.setCtrl(this);
		
		try{
		pvr.montar();
		}catch(Exception e){
			System.out.println("ERRO AO MONTAR ESTRUTURA");
			e.printStackTrace();
		}
		
		js.appendChild(pvr);
		
		js.mostrar();
	}
	public void salvarMPM(MedidaPlanoMedicao mpm) {
		daoMPM.atualizar(mpm);
	}
	public void salvarValorReferencia(ValorReferencia temp) {
		daoVR.salvar(temp);
	}

}

package ode.medicao.planejamentoMedicao.cci;

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
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;

@Controller
public abstract class CtrlValorReferencia extends CtrlBase{

	JanelaSimples js;
	Listbox lbValores;
	
	protected abstract NucleoCombobox<PlanoMedicao> popularCBPlano();
	
	NucleoCombobox<ObjetivoEstrategico> cbEstrategico = new NucleoCombobox<ObjetivoEstrategico>();
	NucleoCombobox<ObjetivoSoftware> cbSoftware = new NucleoCombobox<ObjetivoSoftware>();
	NucleoCombobox<ObjetivoMedicao> cbMedicao = new NucleoCombobox<ObjetivoMedicao>();
	
	Set<PlanoMedicao> planos = new HashSet<PlanoMedicao>();
	
	
	private class OnSelectEstrategico implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			cbSoftware.getItems().clear();
			cbSoftware.setObjetos(cbEstrategico.getObjetoSelecionado().getObjetivoSoftware());
			cbSoftware.selecionarPrimeiroElemento();
			cbMedicao.selecionarPrimeiroElemento();
		}
		
	}
	private class OnSelectSoftware implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			cbMedicao.getItems().clear();
			cbMedicao.setObjetos(cbSoftware.getObjetoSelecionado().getObjetivoMedicao());
			cbMedicao.selecionarPrimeiroElemento();
		}
		
	}
	private class OnSelectMedicao implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			/*TANTO O OBJETIVO DE MEDICAO QNTO O PLANO DE MEDICAO
			 * AO SEREM SELECIONADOS DEVEM MONSTRAR MEDIDAS ESPECIFICAS*/
		}
		
	}
	
	@Autowired
	AplCadastrarKMedida apl;
	
	@Autowired
	AplCadastrarObjetivoEstrategico aplObj;
	
	@Override
	public void iniciar() {
		js = factoryJanelaSimples();
		js.setTitle("Valores de Referência");
		Vbox vb = new Vbox();
		GridDados gd = new GridDados();
		
		
		gd.setParent(vb);
		
		
		Datebox db = new Datebox();
		gd.adicionarLinha("Data do Estabelecimento", db);
		
		
		NucleoCombobox<PlanoMedicao> cbPlano = popularCBPlano(); 
		gd.adicionarLinha("Plano de Medição", cbPlano);
		cbPlano.selecionarPrimeiroElemento();
		
		gd.adicionarLinha("Objetivo Estratégico", cbEstrategico);	
		cbEstrategico.setObjetos(aplObj.recuperarTodos());
		cbEstrategico.addEventListener("onSelect", new OnSelectEstrategico());
		cbEstrategico.selecionarPrimeiroElemento();
		cbEstrategico.setWidth("100%");
		
		gd.adicionarLinha("Objetivo de Software", cbSoftware);
		cbSoftware.addEventListener("onSelect", new OnSelectSoftware());
		cbSoftware.setObjetos(cbEstrategico.getObjetoSelecionado().getObjetivoSoftware());
		cbSoftware.selecionarPrimeiroElemento();
		cbSoftware.setWidth("100%");
		
		gd.adicionarLinha("Objetivo de Medição", cbMedicao);
		cbMedicao.addEventListener("onSelect", new OnSelectMedicao());
		cbMedicao.setObjetos(cbSoftware.getObjetoSelecionado().getObjetivoMedicao());
		cbMedicao.selecionarPrimeiroElemento();
		cbMedicao.setWidth("100%");
		
		
		//////////////////////////////////////////////////////
		PlanoMedicaoOrganizacao p = new PlanoMedicaoOrganizacao();
		p.setVersao((float)1.0);
		planos.add(p);
		
		//////////////////////////////////////////////////////
		
		cbPlano.setObjetos(planos);
		cbPlano.selecionarPrimeiroElemento();
		cbPlano.setWidth("100%");
		
		lbValores = new Listbox();
		lbValores.setCheckmark(true);
		lbValores.setMultiple(true);
		Listhead c = new Listhead();
		c.appendChild(new Listheader("Indicador","","300px"));
		c.appendChild(new Listheader("Bom","","70px"));
		c.appendChild(new Listheader("Regular","","70px"));
		c.appendChild(new Listheader("Ruim","","70px"));
		
		lbValores.appendChild(c);
		Listitem l;
		Listcell aux;
		for(KMedida med:apl.recuperarTodos()){
			l = new Listitem();
			l.appendChild(new Listcell(med.getNome()));
			l.appendChild(aux = new Listcell());
			aux.appendChild(new Textbox());
			l.appendChild(aux = new Listcell());
			aux.appendChild(new Textbox());
			l.appendChild(aux = new Listcell());
			aux.appendChild(new Textbox());
			lbValores.appendChild(l);
		}
		
		vb.setParent(js);
		
		gd.adicionarLinhaUnica(lbValores);
		
		Button salvar = new Button("Salvar");
		Toolbar tb = new Toolbar();
		salvar.setParent(tb);
		tb.setStyle("border:0px;background:white;");
		tb.setAlign("end");
		
		salvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				try {
					Messagebox mbox = new Messagebox();
					mbox.show("Valores de Referência definidos");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		tb.setParent(vb);
		
		js.mostrar();
	}

}

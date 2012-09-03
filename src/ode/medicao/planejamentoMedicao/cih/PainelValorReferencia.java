package ode.medicao.planejamentoMedicao.cih;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cci.CtrlValorReferencia;
import ode.medicao.planejamentoMedicao.cdp.FaixaReferencia;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.ValorReferencia;

import org.python.antlr.PythonParser.classdef_return;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.api.Comboitem;

public class PainelValorReferencia extends Vlayout{

	Listbox lbValores;

	CtrlValorReferencia ctrl;
	
	Datebox db;
	
	public void setCtrl(CtrlValorReferencia ctrl){
		this.ctrl = ctrl;
	}
	
	NucleoCombobox<? extends PlanoMedicao> cbPlano;
	NucleoCombobox<ObjetivoEstrategico> cbEstrategico = new NucleoCombobox<ObjetivoEstrategico>();
	NucleoCombobox<ObjetivoSoftware> cbSoftware = new NucleoCombobox<ObjetivoSoftware>();
	NucleoCombobox<ObjetivoMedicao> cbMedicao = new NucleoCombobox<ObjetivoMedicao>();
	Set<ComponentIndicadores> cis = new HashSet<PainelValorReferencia.ComponentIndicadores>();	
	Set<PlanoMedicao> planos = new HashSet<PlanoMedicao>();
		

	private class OnSelectPlanoMedicao implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			cbEstrategico.getItems().clear();
			if(!cbPlano.getItems().isEmpty()){
				cbEstrategico.setObjetos(cbPlano.getObjetoSelecionado().getObjsEstrategico());
				cbEstrategico.selecionarPrimeiroElemento();
			}else{
				cbEstrategico.setSelectedItem(null);
			}
			(new OnSelectEstrategico()).onEvent(new Event("onSelect"));
		}
		
	}
	
	private class OnSelectEstrategico implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			cbSoftware.getItems().clear();
			if(!cbEstrategico.getItems().isEmpty() && !cbPlano.getItems().isEmpty()){
				Set<ObjetivoSoftware> sos = new HashSet<ObjetivoSoftware>(cbPlano.getObjetoSelecionado().getObjsSoftware());
				sos.retainAll(cbEstrategico.getObjetoSelecionado().getObjetivoSoftware());
				cbSoftware.setObjetos(sos);
				cbSoftware.selecionarPrimeiroElemento();
			}else{
				cbEstrategico.setSelectedItem(null);				
			}
			(new OnSelectSoftware()).onEvent(new Event("onSelect"));
		}
	}
	
	private class OnSelectSoftware implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			cbMedicao.getItems().clear();
			if(!cbSoftware.getItems().isEmpty() && !cbPlano.getItems().isEmpty()){
				Set<ObjetivoMedicao> sos = new HashSet<ObjetivoMedicao>(cbPlano.getObjetoSelecionado().getObjsMedicao());
				sos.retainAll(cbSoftware.getObjetoSelecionado().getObjetivoMedicao());
				cbMedicao.setObjetos(sos);
				cbMedicao.selecionarPrimeiroElemento();
			}else{
				cbSoftware.setSelectedItem(null);
			}
			(new OnSelectMedicao()).onEvent(new Event("onSelect"));
		}
	}
	
	private class OnSelectMedicao implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			allInvisible();
			if(!cbMedicao.getItems().isEmpty() && !cbPlano.getItems().isEmpty()){
				Set<MedidaPlanoMedicao> smpm = getMedidasByObjetivo(cbMedicao.getObjetoSelecionado(),cbPlano.getObjetoSelecionado());
				for(ComponentIndicadores comps:cis){
					if(smpm.contains(comps.getValor())){
						comps.setVisible(true);
						if( comps.getValor().isIndicadorDe(cbMedicao.getObjetoSelecionado())){
							comps.setSelecionado(true);
							comps.setTexts();
						}
					}
				}
			}else{
				cbMedicao.setSelectedItem(null);
			}
		}
	}
	
	private Set<MedidaPlanoMedicao> getMedidasByObjetivo(ObjetivoMedicao objetoSelecionado, PlanoMedicao pm) {
		Set <MedidaPlanoMedicao> retorno = new HashSet<MedidaPlanoMedicao>();
		for(NecessidadeInformacao ni:objetoSelecionado.getNecessidadeInformacao()){
			retorno.addAll(ni.getMedidasNoPlano());
		}
		retorno.retainAll(pm.getMpm());
		return retorno;
	}
	
	private void allInvisible(){
		for(ComponentIndicadores ciInternos:cis){
			ciInternos.setVisible(false);
		}
	}
	
	private class ComponentIndicadores extends Listitem{
		
		private Textbox bom;
		private Textbox regular;
		private Textbox ruim;
		private ComponentIndicadores me = this;
		private MedidaPlanoMedicao valor;
		
		public ComponentIndicadores(MedidaPlanoMedicao med){
			valor = med;
			Listcell aux;
			this.appendChild(new Listcell(med.getMedida().getNome()));
			this.appendChild(aux = new Listcell());
			aux.appendChild(bom = new Textbox());
			this.appendChild(aux = new Listcell());
			aux.appendChild(regular = new Textbox());
			this.appendChild(aux = new Listcell());
			aux.appendChild(ruim = new Textbox());
			setSelecionado(false);
			this.addEventListener("onClick", new EventListener() {
				
				@Override
				public void onEvent(Event arg0) throws Exception {
					if(me.isSelected()){
						valor.getIndicaAlcanceA().add(cbMedicao.getObjetoSelecionado());
					}else{
						valor.getIndicaAlcanceA().remove(cbMedicao.getObjetoSelecionado());
					}
					me.setSelecionado(me.isSelected());
				}
			});
		}
		
		public Set<ValorReferencia> getValoresRef() throws NumberFormatException{
			Set<ValorReferencia> retorno = new HashSet<ValorReferencia>();
			ValorReferencia temp;
			
			temp = new ValorReferencia();
			temp.setData(db.getValue());
			temp.setFaixa(FaixaReferencia.BOM);
			temp.setValor(Float.parseFloat(bom.getText()));
			retorno.add(temp);
			ctrl.salvarValorReferencia(temp);

			temp = new ValorReferencia();
			temp.setData(db.getValue());
			temp.setFaixa(FaixaReferencia.REGULAR);
			temp.setValor(Float.parseFloat(regular.getText()));
			retorno.add(temp);
			ctrl.salvarValorReferencia(temp);

			temp = new ValorReferencia();
			temp.setData(db.getValue());
			temp.setFaixa(FaixaReferencia.RUIM);
			temp.setValor(Float.parseFloat(ruim.getText()));
			retorno.add(temp);
			ctrl.salvarValorReferencia(temp);
			
			return retorno;
		}
		
		public void setSelecionado(boolean check){
			this.setSelected(check);
			bom.setDisabled(!check);
			regular.setDisabled(!check);
			ruim.setDisabled(!check);
		}

		public void setTexts(){
			this.bom.setText(valor.getUltimoValorReferencia(FaixaReferencia.BOM).toString());
			this.regular.setText(valor.getUltimoValorReferencia(FaixaReferencia.REGULAR).toString());
			this.ruim.setText(valor.getUltimoValorReferencia(FaixaReferencia.RUIM).toString());
		}

		public MedidaPlanoMedicao getValor() {
			return valor;
		}
	}
	
	public void montar() throws Exception{

		GridDados gd = new GridDados();
		
		
		db = new Datebox(new Date());
		gd.adicionarLinha("Data do Estabelecimento", db);
		
		
		cbPlano = ctrl.popularCBPlano(); 
		gd.adicionarLinha("Plano de Medição", cbPlano);
		cbPlano.addEventListener("onSelect", new OnSelectPlanoMedicao());
		cbPlano.selecionarPrimeiroElemento();
		cbPlano.setWidth("100%");
		
		gd.adicionarLinha("Objetivo Estrategico", cbEstrategico);
		cbEstrategico.addEventListener("onSelect", new OnSelectEstrategico());
		cbEstrategico.setWidth("100%");
		
		gd.adicionarLinha("Objetivo de Software", cbSoftware);
		cbSoftware.addEventListener("onSelect", new OnSelectSoftware());
		cbSoftware.setWidth("100%");
		
		gd.adicionarLinha("Objetivo de Medição", cbMedicao);
		cbMedicao.addEventListener("onSelect", new OnSelectMedicao());
		cbMedicao.setWidth("100%");
		
		lbValores = new Listbox();
		lbValores.setCheckmark(true);
		lbValores.setMultiple(true);
		Listhead c = new Listhead();
		c.appendChild(new Listheader("Indicador","","300px"));
		c.appendChild(new Listheader(FaixaReferencia.BOM.toString(),"","70px"));
		c.appendChild(new Listheader(FaixaReferencia.REGULAR.toString(),"","70px"));
		c.appendChild(new Listheader(FaixaReferencia.RUIM.toString(),"","70px"));
		
		lbValores.appendChild(c);
		ComponentIndicadores ci;
		for(Comboitem li:(Collection<Comboitem>)cbPlano.getItems()){
			for(MedidaPlanoMedicao med:((PlanoMedicao)li.getValue()).getMpm()){
				lbValores.appendChild(ci = new ComponentIndicadores(med));
				cis.add(ci);
			}
		}

		(new OnSelectPlanoMedicao()).onEvent(new Event("onSelect"));
		
		gd.adicionarLinhaUnica(lbValores);
		
		Button salvar = new Button("Salvar");
		Toolbar tb = new Toolbar();
		salvar.setParent(tb);
		tb.setStyle("border:0px;background:white;");
		tb.setAlign("end");
		
		salvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				try{
					ComponentIndicadores indic;
					for(ComponentIndicadores indicador:cis){
						if(indicador.isSelected()){
							Set<ValorReferencia> vrs = indicador.getValoresRef();
							MedidaPlanoMedicao mpm = indicador.getValor();
							mpm.getValoresReferencia().addAll(vrs);
							ctrl.salvarMPM(mpm);
						}
					}
					try {
						Messagebox mbox = new Messagebox();
						mbox.show("Valores de Referência definidos");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}catch (NumberFormatException e){
					try {
						Messagebox mbox = new Messagebox();
						mbox.show("Por favor, digite um valor decimal");
					} catch (InterruptedException I) {
						e.printStackTrace();
					}
				}
			}
		});

		gd.setParent(this);
		
		tb.setParent(this);
	}
}

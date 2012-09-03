package ode.controleCaracteristica.ciu;

import java.util.Collection;
import java.util.Set;

import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.CtrlCRUD.ModoExibicao;
import ode.controleCaracteristica.cdp.CaracteristicaValorNaoOrdenado;
import ode.controleCaracteristica.cdp.PossivelValor;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;
import ode.controleCaracteristica.cdp.Similaridade;
import ode.controleCaracteristica.cgd.SimilaridadeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;

public class JanelaDefinirTabelaSimilaridade extends JanelaCore{

		private static final long serialVersionUID = -3624797736187568796L;
		private JanelaSimples janela;
		private Vbox vbox = new Vbox();
		private Listbox listboxValores = new Listbox();
		private Listhead listHead = new Listhead();
		private ModoExibicao modoExibicao = ModoExibicao.NOVO;
		
		@Autowired
		CtrlPossivelValorNaoOrdenado ctrlPossivelValorNaoOrdenado;
		
		public JanelaDefinirTabelaSimilaridade(CtrlPossivelValorNaoOrdenado ctrlP, ModoExibicao modo) {
			
			super(ctrlP);
			janela = this;
			
			modoExibicao = modo;
			
			configuraElementosJanela();
			janela.mostrar();
		}


		private void configuraElementosJanela() {
			janela.setTitle("Determinar Similaridade"); 
			
			vbox.setParent(janela);
			vbox.setWidth("100%");
			
			Label labelNome1 = new Label();
			labelNome1.setValue("Determine uma peso de similaridade entre os valores da caracteristica.");
			labelNome1.setParent(vbox);
		
			listboxValores.setParent(vbox);
			listHead.setParent(listboxValores);
			Listheader listHeaderCaracteristica = new Listheader("");
			listHeaderCaracteristica.setParent(listHead);
			
			if(modoExibicao==ModoExibicao.NOVO){
				carregaValoresListbox();
			}
			else{
				insereValoresListbox();
			}
			
			botaoSalvar();
			botaoCancelar();
			
		}
		
		protected void botaoSalvar() {
			Button buttonSalvar = new Button();
			buttonSalvar.setLabel(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_SALVAR));
			buttonSalvar.setParent(janela);
			
			buttonSalvar.addEventListener("onClick", new EventListener() {
				@Override
				public void onEvent(Event arg0) throws Exception {
					salvarTabelaSimilaridades();
					janela.onClose();
				}
			});
			
		}
		

		protected void botaoCancelar() {
			Button buttonCancelar= new Button();
			buttonCancelar.setLabel(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_CANCELAR));
			buttonCancelar.setParent(janela);
			
			buttonCancelar.addEventListener("onClick", new EventListener() {
				@Override
				public void onEvent(Event arg0) throws Exception {
					janela.onClose();
				}
			});
			
		}
		
		/// Cria a tabela de similaridades
	public void carregaValoresListbox (){
		
		listboxValores.getItems().clear();
		
		CaracteristicaValorNaoOrdenado c = (CaracteristicaValorNaoOrdenado) ctrlP.getCaracteristica();
		Set<PossivelValor> listaValores = c.getPossiveisValores();
		
		for (PossivelValor  P : listaValores) {
			Listheader listHeaderCaracteristica = new Listheader(P.getNome());
			listHeaderCaracteristica.setParent(listHead);	
		}
		
		int i = 0;
		int j= 0;
		for (PossivelValor possivelValor: listaValores) {

			Listitem item = new Listitem();	
			
			//Nome do Possivel Valor Nao Ordenado
			Listcell cellNome = new Listcell( possivelValor.getNome());
			cellNome.setParent(item);
			cellNome.setValue(possivelValor);
			
			i=0;
			for(PossivelValor possivelValor2: listaValores){
				//Valor da similaridade:
				
				if(i==j){
					Listcell cell = new Listcell("1");
					cell.setParent(item);
					cell.setValue(1);
				}
				
				if(i>j){
					Doublebox doubleboxPossivelValor = new Doublebox();
					Listcell cell = new Listcell();
					cell.setParent(item);
					cell.setValue(doubleboxPossivelValor);
					cell.appendChild(doubleboxPossivelValor);
				}
				
				if(i<j){
					Listcell cell = new Listcell("---");
					cell.setParent(item);
				}
				
				i=i+1;
			}
			
			j=j+1;
			
			item.setParent(listboxValores);
		}
	}
	
	//Funcao usada para Editar os valores já inseridos na Tabela de similaridades.
	public void insereValoresListbox (){
		
		listboxValores.getItems().clear();
		double valor=0;
		
		CaracteristicaValorNaoOrdenado c = (CaracteristicaValorNaoOrdenado) ctrlP.getCaracteristica();
		Set<PossivelValor> listaValores = c.getPossiveisValores();
				
		for (PossivelValor  P : listaValores) {
			System.out.println("Nome: "+ P.getNome());
			
			Listheader listHeaderCaracteristica = new Listheader(P.getNome());
			listHeaderCaracteristica.setParent(listHead);	
		}
		
		int i = 0;
		int j = 0;
		for (PossivelValor possivelValor: listaValores) {

			Listitem item = new Listitem();	
			
			//Nome do Possivel Valor Nao Ordenado
			Listcell cellNome = new Listcell( possivelValor.getNome());
			cellNome.setParent(item);
			cellNome.setValue(possivelValor);
			
			i=0;
			for(PossivelValor possivelValor2: listaValores){
				valor = 0;

				if(i==j){
					Listcell cell = new Listcell("1");
					cell.setParent(item);
					cell.setValue(1);
				}
								
				if(i>j){
					PossivelValorNaoOrdenado p = (PossivelValorNaoOrdenado) possivelValor;
					Set<Similaridade> similaridades = p.getSimilaridades();
					
					for(Similaridade s : similaridades){
						if(possivelValor2.equals(s.getPValor()) ){
							valor = s.getValor();
						}
					}
					
					//Valor da similaridade:
					Doublebox doubleboxPossivelValor = new Doublebox();
					doubleboxPossivelValor.setValue(valor);
					Listcell cell = new Listcell();
					cell.setParent(item);
					cell.setValue(doubleboxPossivelValor);
					cell.appendChild(doubleboxPossivelValor);
				}
				
				if(i<j){
					Listcell cell = new Listcell("---");
					cell.setParent(item);
				}
								
				i=i+1;
			}
			
			item.setParent(listboxValores);
			j=j+1;
		}
	}
	
	
	//Salva a tabela de similaridades
	public void salvarTabelaSimilaridades(){
		
		@SuppressWarnings("unchecked")
		Collection<Listitem> lista = listboxValores.getItems();
		int i = 0;
		int j = 0;
		PossivelValorNaoOrdenado pValor1 = null;
		PossivelValorNaoOrdenado pValor2 = null;
		double valor =  0;
		
		j = 1;
		for(Listitem L1: lista){
			
			System.out.println(L1);
			pValor1 = (PossivelValorNaoOrdenado) ((Listcell)L1.getFirstChild()).getValue();
			i=1;
			for(Listitem L2: lista){
				valor = 0;
				
				if(i==j){
					pValor2 = (PossivelValorNaoOrdenado) ((Listcell)L2.getChildren().get(0)).getValue();
					valor = 1;
					
					Similaridade similaridade = new Similaridade();
					similaridade.setValor(valor);
					similaridade.setPValor(pValor2);
					
					ctrlP.salvarSimilaridade(similaridade);
					pValor1.addSimilaridade(similaridade);
					
				}
				
				if(i>j){
					pValor2 = (PossivelValorNaoOrdenado)((Listcell)L2.getFirstChild()).getValue();
					valor = (Double)((Doublebox)((Listcell)L1.getChildren().get(i)).getValue()).getValue();
					
					Similaridade similaridade = new Similaridade();
					similaridade.setValor(valor);
					similaridade.setPValor(pValor2);
					
					ctrlP.salvarSimilaridade(similaridade);
					pValor1.addSimilaridade(similaridade);	
				}
								
				i=i+1;
			}
			j=j+1;
		}
	}
	
}

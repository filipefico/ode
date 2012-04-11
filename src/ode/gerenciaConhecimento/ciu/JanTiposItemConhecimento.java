package ode.gerenciaConhecimento.ciu;


import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Window;

public class JanTiposItemConhecimento extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Listbox listbox;
	
	public JanTiposItemConhecimento(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criar();
	}
	
	public void criar(){

			Label label = new Label();
			listbox = new Listbox();
			Button selecionar = new Button("Selecionar");
			Toolbar toolbarInferior = new Toolbar();
			
			this.setTitle("Tipos de Item de Conhecimento");
			this.setHeight("200px");
			this.setBorder("normal");
			
			label.setValue("Selecione o tipo de item de conhecimento:");
			label.setParent(this);
			
			criaListboxTiposItemConhecimento();
			listbox.setParent(this);
			
			selecionar.addEventListener("onClick", new EventListener() {
				
				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					
					int indice = listbox.getSelectedIndex();
					
					if (indice == 0){
						//eh licao aprendida
						ctrlGerenciaConhecimento.exibirJanelaCriarLicaoAprendida();
					}	
					else if (indice == 1){ 
						ctrlGerenciaConhecimento.exibirJanelaCriarConhecimentoRelativoDiscussao();
					}
					
					
				}
			});
			
			toolbarInferior.setStyle("border:0px;background:white;");
			toolbarInferior.setAlign("end");
			
			toolbarInferior.appendChild(selecionar);
			this.appendChild(toolbarInferior);
					
		
	}
	
	public void criaListboxTiposItemConhecimento(){
		
		Listhead colunas = new Listhead();
		Listitem linha1 = new Listitem();
		Listitem linha2 = new Listitem();
		
		Listheader colunaRadio = new Listheader(" ");
		colunaRadio.setWidth("30px");
		Listheader colunaTipo = new Listheader("Tipo");
		colunaTipo.setWidth("100%");
		
		listbox.setMultiple(false);
		listbox.setCheckmark(true);
						
		colunaRadio.setParent(colunas);
		colunaTipo.setParent(colunas);
		
		linha1 = criaLinhaListboxTiposItemConhecimento(linha1, "", "Lição Aprendida");
		linha2 = criaLinhaListboxTiposItemConhecimento(linha2, "", "Conhecimento Relativo a uma Discussão");

		colunas.setParent(listbox);
		linha1.setParent(listbox);
		linha2.setParent(listbox);
		
		//return listbox;
		
	}

	public Listitem criaLinhaListboxTiposItemConhecimento(Listitem linhas, String tipo1, String tipo2){
	
		Listcell cell = new Listcell();
		Label labelTipo = new Label();
		labelTipo.setValue(tipo1);
		labelTipo.setParent(cell);
		
		cell.setParent(linhas);
		
		cell = new Listcell();
		labelTipo = new Label();
		labelTipo.setValue(tipo2);
		labelTipo.setParent(cell);
		
		cell.setParent(linhas);
		
		return linhas;
	
}
	
}

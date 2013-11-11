
package ode.processoPadrao.ciu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.RollbackException;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.DependenciaMacroAtividades;
import ode.processoPadrao.cdp.InterfaceCompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

public class JanSelecionarCompPPBase extends JanCore {

	private static final long serialVersionUID = -4850958617603787734L;
	JanelaSimples janela;
	private Listbox listaProcessoPadrao;
	String TipoCompPPSelecionado;
	Collection<CompPP> listaCompPP;

	public JanSelecionarCompPPBase(	CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		janela = this;
		listaProcessoPadrao = new Listbox();
		
		// Pega o tipo do selecionado:
		if(ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples){
			TipoCompPPSelecionado = ((CompPPProcessoSimples)ctrl.getcompPPSelecionado()).getTipo().getNome();
		}else
			if(ctrl.getcompPPSelecionado() instanceof CompPPMacroatividade){
				TipoCompPPSelecionado = ((CompPPMacroatividade)ctrl.getcompPPSelecionado()).getTipo().getNome();
			}

		configuraElementosJanela();
		janela.mostrar();

	}

	private void configuraElementosJanela() {
		janela.setTitle("Selecionar Componente Base");

		listaProcessoPadrao.setCheckmark(true);
		Listitem itemLista = new Listitem();

		listaProcessoPadrao.setParent(janela);
		
		Collection<CompPP> listaCompPP = new ArrayList<CompPP>();

		preencheListaCompPPsFinalizado(listaCompPP);

		if (listaCompPP.isEmpty() == false){ // Caso tenha componentes definidos:
			for (CompPP compPP : listaCompPP) {
				itemLista.setValue(compPP);
	
				// Não adiciona na lista o compPP atual:
				if (compPP.getNome().compareTo(ctrl.getcompPPSelecionado().getNome()) != 0) {
					
					if(compPP instanceof CompPPProcessoSimples){
						
						// Verifica se é do mesmo tipo para Simples:
						if(TipoCompPPSelecionado.compareTo(((CompPPProcessoSimples) compPP).getTipo().getNome()) == 0) {							
							adicionaItem(false, compPP.getNome(), itemLista);
						}
					}else
						if (compPP instanceof CompPPMacroatividade){
							
							// Verifica se é do mesmo tipo para Macroatividade:
							if (TipoCompPPSelecionado.compareTo(((CompPPMacroatividade) compPP).getTipo().getNome()) == 0){
								adicionaItem(false, compPP.getNome(), itemLista);
							}
							
						}else{ // Processo Complexo:
							adicionaItem(false, compPP.getNome(), itemLista);	
					}
				}
			}
		
			Button buttonSelecionar = new Button("Selecionar");
			buttonSelecionar.setParent(janela);
			buttonSelecionar.addEventListener("onClick", new EventListener() {
				@Override
				public void onEvent(Event arg0) throws Exception {
	
					if (listaProcessoPadrao.getSelectedItem() != null) {
						Messagebox.show(
								"A estrutura do Componente selecionado será alterada",
								"Alteração", Messagebox.OK + Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {
	
									public void onEvent(Event evt) {
										switch (((Integer) evt.getData()).intValue()) {
										case Messagebox.OK:
											try {
												alterarEstruturaCompPP();
												ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
	
											} catch (CloneNotSupportedException e) {
												e.printStackTrace();
											}
											janela.onClose();
											break;
										}
									}
	
								});
					} else {
						Messagebox.show("Selecione um elemento da lista.");
					}
				}
			});
			
		}else{ // Caso não tenha:
			
			adicionaItem(true, "Não existem componentes definidos. Por favor, defina um componente com o mesmo tipo do selecionado.", itemLista);
			
			Button buttonSelecionar = new Button("Cancelar");
			buttonSelecionar.setParent(janela);
			buttonSelecionar.addEventListener("onClick", new EventListenerCancelar());
		}

		if (itemLista.getParent() == null){
			adicionaItem(true, "Não existem componentes definidos do mesmo tipo. Por favor, defina um componente do mesmo tipo do componente selecionado.", itemLista);
		}
		
	}

	private void adicionaItem(boolean NaoEhUmComponente, String label, Listitem item){
		
		if (NaoEhUmComponente) listaProcessoPadrao.setCheckmark(false);
		
		Listcell listcell = new Listcell(label);
		item.setParent(listaProcessoPadrao);
		item.appendChild(listcell);
	}
	
	
	public class EventListenerCancelar implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			janela.onClose();
		}
	}
	
	private void preencheListaCompPPsFinalizado(Collection<CompPP> listaCompPP) {
		listaCompPP.addAll(retornaCompPPsFinalizados(ctrl.getcompPPSelecionado()));
	}

	private Collection<CompPP> retornaCompPPsFinalizados(CompPP compPP) {

		if (compPP instanceof CompPPProcessoComplexo) {
			return retornaCompPPsFinalizados((CompPPProcessoComplexo) compPP);

		} else if (compPP instanceof CompPPProcessoSimples) {
			return retornaCompPPsFinalizados((CompPPProcessoSimples) compPP);

		} else {
			return retornaCompPPsFinalizados((CompPPMacroatividade) compPP);
		}
	}

	private Collection<CompPP> retornaCompPPsFinalizados(CompPPProcessoComplexo compPP) {
		return ctrl.getAllCompPPFinalizado(CompPPProcessoComplexo.class);
	}

	private Collection<CompPP> retornaCompPPsFinalizados(CompPPProcessoSimples compPP) {
		return ctrl.getAllCompPPFinalizado(CompPPProcessoSimples.class);
	}

	private Collection<CompPP> retornaCompPPsFinalizados(CompPPMacroatividade compPP) {
		return ctrl.getAllCompPPFinalizado(CompPPMacroatividade.class);
	}

	
	
	
	void alterarEstruturaCompPP() throws CloneNotSupportedException {

		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {

			if(listaProcessoPadrao.getSelectedIndex() == -1){
				try {
					Messagebox.show("Por favor, selecione um Processo", "Informação", Messagebox.OK, Messagebox.INFORMATION);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				
				Object objeto = new Object();
				if (listaProcessoPadrao.getSelectedItem() != null) {
					objeto = listaProcessoPadrao.getSelectedItem().getValue();
					
					if(objeto != null){
						
						/*Set<CompPPProcessoSimples> processosSimplesCopia = new HashSet<CompPPProcessoSimples>();
						InterfaceCompPP interfaceCompPPCopia = ((CompPPProcessoComplexo) objeto).getInterfaceCompPP().clone();
						String requisitoCopia = new String(((CompPPProcessoComplexo) objeto).getRequisitoCompPP().toString());
						
						for(CompPPProcessoSimples simples : ((CompPPProcessoComplexo) objeto).getProcessosSimples()){
							processosSimplesCopia.add(simples.clone());
						}
						
						((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).setProcessosSimples(processosSimplesCopia);
						((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).setRequisitoCompPP(requisitoCopia);
						((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).setInterfaceCompPP(interfaceCompPPCopia);
						
						ctrl.salvarListaCompPP(((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getProcessosSimples());
						ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());*/
						
						/*CompPPProcessoComplexo copia = ((CompPPProcessoComplexo) objeto).clone();
						((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).setProcessosSimples(copia.getProcessosSimples());
						//ctrl.getcompPPSelecionado().setCompPPBase((CompPP) objeto);
						
						ctrl.salvarCompPP(copia);
						
						ctrl.salvarListaCompPP(((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getProcessosSimples());
						ctrl.getcompPPSelecionado().setCompPPBase(copia);
						
						copia = null;*/
					}
				}
			}

		}else{
			if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {
				
				if(listaProcessoPadrao.getSelectedIndex() == -1){
					try {
						Messagebox.show("Por favor, selecione um Processo", "Informação", Messagebox.OK, Messagebox.INFORMATION);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					
					Object objeto = new Object();
					if (listaProcessoPadrao.getSelectedItem() != null) {
						objeto = listaProcessoPadrao.getSelectedItem().getValue();
						
						if(objeto != null){
													
							Set<CompPPProcessoComplexo> processosComplexosCopia = new HashSet<CompPPProcessoComplexo>();
							Set<CompPPMacroatividade> macroAtividadesCopia = new HashSet<CompPPMacroatividade>(); 
							Set<DependenciaMacroAtividades> dependenciaMacroAtividadesCopia = new HashSet<DependenciaMacroAtividades>();
							//AtividadeProcessoPadrao atvCopia = ((CompPPProcessoSimples) objeto).getAtividadeProcessoPadrao().clone();
							//KProcesso tipoCopia = ((CompPPProcessoSimples) objeto).getTipo().clone();
							InterfaceCompPP interfaceCompPPCopia = ((CompPPProcessoSimples) objeto).getInterfaceCompPP().clone();
							String requisitoCopia = new String(((CompPPProcessoSimples) objeto).getRequisitoCompPP().toString());
							boolean ehEngenharia = ((CompPPProcessoSimples) objeto).getEngenharia();
							
							for(CompPPProcessoComplexo complexo : ((CompPPProcessoSimples) objeto).getProcessosComplexos()){
								processosComplexosCopia.add(complexo.clone());
							}
							
							for(CompPPMacroatividade macroatividade : ((CompPPProcessoSimples) objeto).getMacroAtividades()){
								macroAtividadesCopia.add(macroatividade.clone());
							}
							
							for(DependenciaMacroAtividades macroDependencia : ((CompPPProcessoSimples) objeto).getDependenciaMacroAtividades()){
								dependenciaMacroAtividadesCopia.add(macroDependencia);
							}
							
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setProcessosComplexos(processosComplexosCopia);
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setMacroAtividades(macroAtividadesCopia); 
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setDependenciaMacroAtividades(dependenciaMacroAtividadesCopia);
							//((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setAtividadeProcessoPadrao(atvCopia);
							//((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setTipo(tipoCopia);
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setEngenharia(ehEngenharia);
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setCompPPBase((CompPP)(listaProcessoPadrao.getSelectedItem().getValue()));
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setInterfaceCompPP(interfaceCompPPCopia);
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setRequisitoCompPP(requisitoCopia);
							((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setDefinicaoConcluida(false);
							
							ctrl.salvarListaCompPP(((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).getMacroAtividades());
							ctrl.salvarListaCompPP(((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).getProcessosComplexos());
							ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
							
							
							
							//CompPPProcessoSimples copia = ((CompPPProcessoSimples) objeto).clone();
							//Set<CompPPMacroatividade> macroAtividadesCopia = new HashSet<CompPPMacroatividade>(); 
							
							//((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setMacroAtividades(copia.getMacroAtividades()); 
							//((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setAtividadeProcessoPadrao(copia.getAtividadeProcessoPadrao());
							//ctrl.getcompPPSelecionado().setCompPPBase((CompPP) objeto);
							
							//ctrl.salvarCompPP(copia);
							
							//ctrl.salvarListaCompPP(((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).getMacroAtividades());
							
							//copia = null;
						}
					}
				}
			}else{ /* Macroatividade */

				if(listaProcessoPadrao.getSelectedIndex() == -1){
					try {
						Messagebox.show("Por favor, selecione um Processo", "Informação", Messagebox.OK, Messagebox.INFORMATION);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					
					Object objeto = new Object();
					if (listaProcessoPadrao.getSelectedItem() != null) {
						objeto = listaProcessoPadrao.getSelectedItem().getValue();
						
						if(objeto != null){
							
							Set<DependenciaMacroAtividades> preDependenciaMacroAtividadeCopia = new HashSet<DependenciaMacroAtividades>();
							DependenciaMacroAtividades baseDependenciaMacroAtividadeCopia = ((CompPPMacroatividade) objeto).getBaseDependenciaMacroAtividade();
							//KAtividade tipoCopia = ((CompPPMacroatividade) objeto).getTipo().clone();
							//AtividadeProcessoPadrao atvCopia = ((CompPPMacroatividade) objeto).getAtividadeProcessoPadrao().clone();
							InterfaceCompPP interfaceCompPPCopia = ((CompPPMacroatividade) objeto).getInterfaceCompPP().clone();
							String requisitoCopia = new String(((CompPPMacroatividade) objeto).getRequisitoCompPP().toString());
							
							for(DependenciaMacroAtividades macroDependencia : ((CompPPMacroatividade) objeto).getPreDependenciaMacroAtividade()){
								preDependenciaMacroAtividadeCopia.add(macroDependencia);
							}
							
							((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setBaseDependenciaMacroAtividade(baseDependenciaMacroAtividadeCopia);
							((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setPreDependenciaMacroAtividade(preDependenciaMacroAtividadeCopia);
							//((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setTipo(tipoCopia);
							//((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setAtividadeProcessoPadrao(atvCopia);
							((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setCompPPBase((CompPP)(listaProcessoPadrao.getSelectedItem().getValue()));
							((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setInterfaceCompPP(interfaceCompPPCopia);
							((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setRequisitoCompPP(requisitoCopia);
							((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setDefinicaoConcluida(false);

							ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
							
							/*CompPPMacroatividade copia = ((CompPPMacroatividade) objeto).clone();
							((CompPPMacroatividade) ctrl.getcompPPSelecionado()).setAtividadeProcessoPadrao(copia.getAtividadeProcessoPadrao());
							ctrl.salvarCompPP(copia);
							ctrl.getcompPPSelecionado().setCompPPBase((CompPP) objeto);*/
						}
					}
				}

			}
		}

	}

}

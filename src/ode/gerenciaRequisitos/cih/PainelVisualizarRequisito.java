package ode.gerenciaRequisitos.cih;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Pacote;

import org.zkoss.zul.Label;
import org.zkoss.zul.Vlayout;

public class PainelVisualizarRequisito extends Vlayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4609555786660056076L;

	public PainelVisualizarRequisito(Requisito requisito){
		carregarComponentes(requisito);
	}

	public void carregarComponentes(Requisito requisito){
		new Label("Identificador: " + requisito.getIdentificador()).setParent(this);
		
		new Label("Data de Criação: " + requisito.getDataCriacao()).setParent(this);
		
		new Label("Prioridade: " + requisito.getPrioridade()).setParent(this);
		
		new Label("Descrição: " + requisito.getDescricao()).setParent(this);
		
		new Label("Projeto: " + requisito.getProjeto().getNome()).setParent(this);
		
		new Label("Tipo de Requisito: " + requisito.getTipoRequisito().getNome()).setParent(this);
		
		new Label("Categoria: " + requisito.getCategoria().getNome()).setParent(this);
		
		new Label("Conflitos:").setParent(this);
		Collection<Requisito> conflitos = requisito.getConflitos();
		for (Requisito r : conflitos){
			new Label(r.getIdentificador() + "   " + r.getDescricao()).setParent(this);
		}
		
		new Label("Dependências:").setParent(this);
		Collection<Requisito> dependencias = requisito.getDependencias();
		for (Requisito r : dependencias){
			new Label(r.getIdentificador() + "   " + r.getDescricao()).setParent(this);
		}
		
		new Label("Pacotes:").setParent(this);
		Collection<Pacote> pacotes = requisito.getPacotes();
		for (Pacote p : pacotes){
			new Label(p.getNome() + "   " + p.getDescricao()).setParent(this);
		}
		
		new Label("Casos de Uso:").setParent(this);
		Collection<CasoUso> casosUso = requisito.getCasosUso();
		for (CasoUso c : casosUso){
			new Label(c.getNome() + "   " + c.getDescricao()).setParent(this);
		}
		
		new Label("Responsáveis:").setParent(this);
		Collection<RecursoHumano> responsaveis = requisito.getResponsaveis();
		for (RecursoHumano resp : responsaveis){
			new Label(resp.getNome() + "   " + resp.getCargo());
		}
		
		new Label("Interessados:").setParent(this);
		Collection<RecursoHumano> interessados = requisito.getInteressados();
		for (RecursoHumano interess : interessados){
			new Label(interess.getNome() + "   " + interess.getCargo());
		}
		
	}
}
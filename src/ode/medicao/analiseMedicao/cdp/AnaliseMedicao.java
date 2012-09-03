package ode.medicao.analiseMedicao.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.execucaoMedicao.cdp.Medicao;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;

@Entity
public class AnaliseMedicao extends ObjetoPersistente implements Resultado{

	private Date data ;
	private Date inicio;
	private Date fim;
	private String resultadoAnaliseMedicao;
	private Set<Medicao> medicoes;
	private KMedida medida;
	private DefinicaoOperacionalMedida definicaoOperacional;
	private KAtividade momentoAnalise;
	private RecursoHumano executorAnalise;
	
	public String toString(){
		return this.getMedida()+" - "+this.getData()+" : "+this.getExecutorAnalise();
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getResultadoAnaliseMedicao() {
		return resultadoAnaliseMedicao;
	}
	public void setResultadoAnaliseMedicao(String resultadoAnaliseMedicao) {
		this.resultadoAnaliseMedicao = resultadoAnaliseMedicao;
	}
	
	@Transient
	public Set<Medicao> getMedicoes() {
		return medicoes;
	}
	public void setMedicoes(Set<Medicao> medicoes) {
		this.medicoes = medicoes;
	}
	
	public KMedida getMedida() {
		return medida;
	}
	public void setMedida(KMedida medida) {
		this.medida = medida;
	}
	
	public DefinicaoOperacionalMedida getDefinicaoOperacional() {
		return definicaoOperacional;
	}
	public void setDefinicaoOperacional(DefinicaoOperacionalMedida definicaoOperacional) {
		this.definicaoOperacional = definicaoOperacional;
	}
	
	public KAtividade getMomentoAnalise() {
		return momentoAnalise;
	}
	public void setMomentoAnalise(KAtividade momentoAnalise) {
		this.momentoAnalise = momentoAnalise;
	}
	
	public RecursoHumano getExecutorAnalise() {
		return executorAnalise;
	}
	public void setExecutorAnalise(RecursoHumano executorAnalise) {
		this.executorAnalise = executorAnalise;
	}
	
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	
}

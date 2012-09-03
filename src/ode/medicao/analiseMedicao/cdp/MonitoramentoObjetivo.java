package ode.medicao.analiseMedicao.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ValorReferencia;

@Entity
public class MonitoramentoObjetivo extends ObjetoPersistente implements Resultado{

	private Date data ;
	private Date inicio;
	private Date fim;
	private String resultadoMonitoramento;
	private Set<KMedida> medidas;
	private MedidaPlanoMedicao indicador;
	private ValorReferencia valorReferencia;
	private ObjetivoMedicao objetivo;
	private RecursoHumano executorMonitoramento;
	private KAtividade momentoMonitoramento;
	
	public String toString(){
		return this.getObjetivo()+" - "+this.getData()+" : "+this.getExecutorMonitoramento();
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getResultadoMonitoramento() {
		return resultadoMonitoramento;
	}
	public void setResultadoMonitoramento(String resultadoMonitoramento) {
		this.resultadoMonitoramento = resultadoMonitoramento;
	}
	
	public ValorReferencia getValorReferencia() {
		return valorReferencia;
	}
	public void setValorReferencia(ValorReferencia valorReferencia) {
		this.valorReferencia = valorReferencia;
	}
	
	public ObjetivoMedicao getObjetivo() {
		return objetivo;
	}	
	public void setObjetivo(ObjetivoMedicao objetivo) {
		this.objetivo = objetivo;
	}
	
	public RecursoHumano getExecutorMonitoramento() {
		return executorMonitoramento;
	}
	public void setExecutorMonitoramento(RecursoHumano executorMonitoramento) {
		this.executorMonitoramento = executorMonitoramento;
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
	public KAtividade getMomentoMonitoramento() {
		return momentoMonitoramento;
	}
	public void setMomentoMonitoramento(KAtividade momentoMonitoramento) {
		this.momentoMonitoramento = momentoMonitoramento;
	}
	
	@Transient
	public Set<KMedida> getMedidas() {
		return medidas;
	}
	public void setMedidas(Set<KMedida> medidas) {
		this.medidas = medidas;
	}
	public MedidaPlanoMedicao getIndicador() {
		return indicador;
	}
	public void setIndicador(MedidaPlanoMedicao indicador) {
		this.indicador = indicador;
	}
	
}

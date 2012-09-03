package ode.medicao.analiseMedicao.cdp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javassist.expr.Instanceof;

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
public class AcaoCorretiva extends ObjetoPersistente {

	private String nome;
	private String descricao;
	private Set<MonitoramentoObjetivo> causaMonitoramento;
	private Set<AnaliseMedicao> causaAnalise;

	public String getNome() {
		return nome;
	}

	public void setNome(String titulo) {
		this.nome = titulo;
	}

	public String toString() {
		return this.nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<MonitoramentoObjetivo> getCausaMonitoramento() {
		return causaMonitoramento;
	}

	public void setCausaMonitoramento(
			Set<MonitoramentoObjetivo> causasMonitoramento) {
		this.causaMonitoramento = causasMonitoramento;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<AnaliseMedicao> getCausaAnalise() {
		return causaAnalise;
	}

	public void setCausaAnalise(Set<AnaliseMedicao> causaAnalise) {
		this.causaAnalise = causaAnalise;
	}

	@Transient
	public Set<Resultado> getCausas() {
		HashSet<Resultado> HSr = new HashSet<Resultado>();
		HSr.addAll(getCausaAnalise());
		HSr.addAll(getCausaMonitoramento());
		return HSr;
	}

	public void setCausas(Set<Resultado> causas) {
		causaAnalise = new HashSet<AnaliseMedicao>();
		causaMonitoramento = new HashSet<MonitoramentoObjetivo>();
		for (Resultado resultado : causas) {
			if (resultado instanceof MonitoramentoObjetivo) {
				causaMonitoramento.add((MonitoramentoObjetivo)resultado);
			} else {
				causaAnalise.add((AnaliseMedicao)resultado);
			}
		}
	}

}

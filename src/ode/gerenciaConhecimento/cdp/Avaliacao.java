package ode.gerenciaConhecimento.cdp;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class Avaliacao extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal notaCorrecao;
	
	private BigDecimal notaCompletude;
	
	private BigDecimal notaConsistencia;
	
	private BigDecimal notaUtilidade;
	
	private BigDecimal notaAplicabilidade;
	
	private String parecer;
	
	private String resultadoFinal;
	
	private Date dataAvaliacao;
	
	private ItemConhecimento itemConhecimentoAvaliado;
	
	private RecursoHumano autor;

	public BigDecimal getNotaCorrecao() {
		return notaCorrecao;
	}

	public void setNotaCorrecao(BigDecimal notaCorrecao) {
		this.notaCorrecao = notaCorrecao;
	}

	public BigDecimal getNotaCompletude() {
		return notaCompletude;
	}

	public void setNotaCompletude(BigDecimal notaCompletude) {
		this.notaCompletude = notaCompletude;
	}

	public BigDecimal getNotaConsistencia() {
		return notaConsistencia;
	}

	public void setNotaConsistencia(BigDecimal notaConsistencia) {
		this.notaConsistencia = notaConsistencia;
	}

	public BigDecimal getNotaUtilidade() {
		return notaUtilidade;
	}

	public void setNotaUtilidade(BigDecimal notaUtilidade) {
		this.notaUtilidade = notaUtilidade;
	}

	public BigDecimal getNotaAplicabilidade() {
		return notaAplicabilidade;
	}

	public void setNotaAplicabilidade(BigDecimal notaAplicabilidade) {
		this.notaAplicabilidade = notaAplicabilidade;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getResultadoFinal() {
		return resultadoFinal;
	}

	public void setResultadoFinal(String resultadoFinal) {
		this.resultadoFinal = resultadoFinal;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	@ManyToOne
	public ItemConhecimento getItemConhecimentoAvaliado() {
		return itemConhecimentoAvaliado;
	}

	public void setItemConhecimentoAvaliado(
			ItemConhecimento itemConhecimentoAvaliado) {
		this.itemConhecimentoAvaliado = itemConhecimentoAvaliado;
	}

	@ManyToOne
	public RecursoHumano getAutor() {
		return autor;
	}

	public void setAutor(RecursoHumano autor) {
		this.autor = autor;
	}

}

package ode.gerenciaConhecimento.cdp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class Valoracao extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long grauUtilidade;
	
	private String comentario;
	
	private Date dataValoracao;

	private ItemConhecimento itemConhecimentoAvaliado;
	
	private RecursoHumano autor;
	
	public Long getGrauUtilidade() {
		return grauUtilidade;
	}

	public void setGrauUtilidade(Long grauUtilidade) {
		this.grauUtilidade = grauUtilidade;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataValoracao() {
		return dataValoracao;
	}

	public void setDataValoracao(Date dataValoracao) {
		this.dataValoracao = dataValoracao;
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

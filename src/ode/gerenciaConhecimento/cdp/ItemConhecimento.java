package ode.gerenciaConhecimento.cdp;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;

import org.hibernate.annotations.IndexColumn;

@Entity
@Inheritance
@DiscriminatorColumn(name="TIPO_CONHECIMENTO")
public class ItemConhecimento extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	
	private String resumo;
	
	private String aplicabilidade;
	
	private Date dataCriacao;
	
	private Date dataUltimoAcesso;
	
	private String estado;
	
	private List<ItemConhecimento> itensRelacionados;
	
	private List<Projeto> projeto;
	
	private RecursoHumano autor;
	
	private List<Tema> tema;
	
	private List<KAtividade> kAtividades; 

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getAplicabilidade() {
		return aplicabilidade;
	}

	public void setAplicabilidade(String aplicabilidade) {
		this.aplicabilidade = aplicabilidade;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@IndexColumn(name = "index_col")
	public List<ItemConhecimento> getItensRelacionados() {
		return itensRelacionados;
	}

	public void setItensRelacionados(List<ItemConhecimento> itensRelacionados) {
		this.itensRelacionados = itensRelacionados;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@IndexColumn(name = "index_col")
	public List<Projeto> getProjeto() {
		return projeto;
	}

	public void setProjeto(List<Projeto> projeto) {
		this.projeto = projeto;
	}

	@ManyToOne
	public RecursoHumano getAutor() {
		return autor;
	}

	public void setAutor(RecursoHumano autor) {
		this.autor = autor;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "index_col")
	public List<Tema> getTema() {
		return tema;
	}

	public void setTema(List<Tema> tema) {
		this.tema = tema;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@IndexColumn(name = "index_col")
	public List<KAtividade> getkAtividades() {
		return kAtividades;
	}

	public void setkAtividades(List<KAtividade> kAtividades) {
		this.kAtividades = kAtividades;
	}

}
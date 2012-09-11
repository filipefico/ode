package ode.gerenciaRequisitos.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cdp.TipoRequisito;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Pacote;

@Entity
public class Requisito extends ObjetoPersistente{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1456129254088012358L;
	
	private String identificador;
	private String descricao;
	private Date dataCriacao;
	private CategoriaRequisito categoria;
	private Set<Requisito> conflitos;
	private Set<Requisito> dependencias;
	private Projeto projeto;
	//private Set<Artefato> artefatos;
	private Set<Pacote> pacotes;
	private Set<CasoUso> casosUso;
	private TipoRequisito tipoRequisito;
	private Prioridade prioridade;
	private Set<RecursoHumano> interessados;
	private Set<RecursoHumano> responsaveis;
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	@Column (length = 4000)
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@ManyToOne (fetch = FetchType.LAZY)
	public CategoriaRequisito getCategoria() {
		return categoria;
	}
	
	public void setCategoria(CategoriaRequisito categoria) {
		this.categoria = categoria;
	}
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "Requisito_Conflitos")
	public Set<Requisito> getConflitos() {
		return conflitos;
	}
	
	public void setConflitos(Set<Requisito> conflitos) {
		this.conflitos = conflitos;
	}
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "Requisito_Dependencias")
	public Set<Requisito> getDependencias() {
		return dependencias;
	}
	
	public void setDependencias(Set<Requisito> dependencias) {
		this.dependencias = dependencias;
	}
	
	@ManyToOne (fetch = FetchType.LAZY)
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	@ManyToMany (fetch = FetchType.LAZY)
	public Set<Pacote> getPacotes() {
		return pacotes;
	}
	
	public void setPacotes(Set<Pacote> pacotes) {
		this.pacotes = pacotes;
	}
	
	@ManyToMany (fetch = FetchType.LAZY)
	public Set<CasoUso> getCasosUso() {
		return casosUso;
	}
	
	public void setCasosUso(Set<CasoUso> casosUso) {
		this.casosUso = casosUso;
	}
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable (name = "Requisito_Interessado")
	public Set<RecursoHumano> getInteressados() {
		return interessados;
	}
	
	public void setInteressados(Set<RecursoHumano> interessados) {
		this.interessados = interessados;
	}
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "Requisito_Responsavel")
	public Set<RecursoHumano> getResponsaveis() {
		return responsaveis;
	}
	
	public void setResponsaveis(Set<RecursoHumano> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public TipoRequisito getTipoRequisito() {
		return tipoRequisito;
	}

	public void setTipoRequisito(TipoRequisito tipoRequisito) {
		this.tipoRequisito = tipoRequisito;
	}

	@Enumerated (EnumType.ORDINAL)
	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
}

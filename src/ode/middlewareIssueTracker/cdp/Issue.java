package ode.middlewareIssueTracker.cdp;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.controleProjeto.cdp.Projeto;


@XmlRootElement
@Entity
public class Issue extends ObjetoPersistente {
	
	private static final long serialVersionUID = 1144168960171946495L;
	
	private long idIssue;
	private String resumo;
	private String  descricao;
	private String nomeProjeto;
	private String estado;
	private String responsavel;
	private String dataAtualizacao;
	private String dataLimite;
	
	private Projeto projeto;
	

	public long getIdIssue() {
		return idIssue;
	}

	public void setIdIssue(long id) {
		this.idIssue = id;
	}

	public String  getResumo() {
		return resumo;
	}

	public void setResumo(String  resumo) {
		this.resumo = resumo;
	}

	public String  getDescricao() {
		return descricao;
	}

	public void setDescricao(String  descricao) {
		this.descricao = descricao;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getDataAtualizacao() {
		
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dateAtualizacao) {
		this.dataAtualizacao = dateAtualizacao;
	}

	public String getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	@ManyToOne
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	Date converteData(String date){
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.parseLong(date) * 1000);
		return c.getTime();
	}
	
	@Override
	public String toString(){
		String s = "Id: " + idIssue + "\n Resumo: " + resumo + "\n Descricao: " + descricao + "\n Nome Projeto: " + nomeProjeto + "\n Estado: " + estado + "\n Responsavel: " + responsavel;
		
		if(dataAtualizacao != null) {
			s += "\n Data Atualizacao: " + converteData(dataAtualizacao);
		}
		if(dataLimite != null) {
			s += "\n Data Limite: " + converteData(dataLimite);
		}
		return s;
	}
	
}

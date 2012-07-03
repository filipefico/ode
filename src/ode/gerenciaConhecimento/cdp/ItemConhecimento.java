package ode.gerenciaConhecimento.cdp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;

@Entity
@Inheritance
@DiscriminatorColumn(name="TIPO_CONHECIMENTO")
public class ItemConhecimento extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String ESTADO_AGUARDANDO_AVALIACAO = "Aguardando Avaliação";
	
	public static String ESTADO_DISPONIVEL = "Disponível";
	
	public static String ESTADO_REJEITADO = "Rejeitado";
	
	private String titulo;
	
	private String resumo;
	
	private String aplicabilidade;

	private Date dataCriacao;
	
	private Date dataUltimoAcesso;
	
	private String estado;
	
	private Long quantidadeAcessos = new Long(0);
	
	private Set<ItemConhecimento> itensRelacionados = new HashSet<ItemConhecimento>();
	
	private Set<Projeto> projetos = new HashSet<Projeto>();
	
	private RecursoHumano autor;
	
	private Set<Tema> temas = new HashSet<Tema>();
	
	private Set<KAtividade> kAtividades = new HashSet<KAtividade>(); 
	
	private Set<Valoracao> valoracoes = new HashSet<Valoracao>();
	
	private Set<Avaliacao> avaliacoes = new HashSet<Avaliacao>();

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

	@Temporal(TemporalType.TIMESTAMP) 
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Temporal(TemporalType.TIMESTAMP) 
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
	
	public Long getQuantidadeAcessos() {
		return quantidadeAcessos;
	}

	public void setQuantidadeAcessos(Long quantidadeAcessos) {
		this.quantidadeAcessos = quantidadeAcessos;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<ItemConhecimento> getItensRelacionados() {
		return itensRelacionados;
	}

	public void setItensRelacionados(Set<ItemConhecimento> itensRelacionados) {
		this.itensRelacionados = itensRelacionados;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(Set<Projeto> projetos) {
		this.projetos = projetos;
	}

	@ManyToOne
	public RecursoHumano getAutor() {
		return autor;
	}

	public void setAutor(RecursoHumano autor) {
		this.autor = autor;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<Tema> getTemas() {
		return temas;
	}

	public void setTemas(Set<Tema> temas) {
		this.temas = temas;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KAtividade> getkAtividades() {
		return kAtividades;
	}

	public void setkAtividades(Set<KAtividade> kAtividades) {
		this.kAtividades = kAtividades;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ITEMCONHECIMENTOAVALIADO_ID", referencedColumnName="ID")
	public Set<Valoracao> getValoracoes() {
		return valoracoes;
	}

	public void setValoracoes(Set<Valoracao> valoracoes) {
		this.valoracoes = valoracoes;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ITEMCONHECIMENTOAVALIADO_ID", referencedColumnName="ID")
	public Set<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	/**
	 * Recupera quantidade de valorações deste item de conhecimento.
	 * @param tipo -1, para quantidade de valorações negativas, 0 para neutras e 1 para positivas.
	 * @return Quantidade de valorações.
	 */
	public int quantidadeValoracoes(int tipo){

		Set<Valoracao> valoracoes = this.getValoracoes();
		int positivas = 0; 
		int negativas = 0;
		int neutras = 0;

		for (Valoracao valoracao : valoracoes) {
			if (valoracao.getGrauUtilidade().compareTo(new BigDecimal(0)) < 0)
				negativas++;
			else if (valoracao.getGrauUtilidade().compareTo(new BigDecimal(0)) == 0)
				neutras++;
			else 
				positivas++;
		}

		if (tipo == -1)
			return negativas;
		else if (tipo == 0)
			return neutras;

		return positivas;

	}
	
	public void addValoracao(Valoracao valoracao) {
        this.valoracoes.add(valoracao);
        if (valoracao.getItemConhecimentoAvaliado() != this) {
            valoracao.setItemConhecimentoAvaliado(this);
        }
    }
	
}

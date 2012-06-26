package ode.resolucaoProblema.cdp;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.ManyToAny;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.entidadeProblema.cdp.ArtefatoProblema;
import ode.entidadeProblema.cdp.ProjetoProblema;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;

@Entity
public class OcorrenciaProblema extends ObjetoPersistente {

	private static final long serialVersionUID = 4609432810469425886L;

	private Integer versao;
	public String nome;
	private Date data;

	private String descricao;

	private Boolean resubmetida;

	private Set<KCausa> kcausa;

	private KProblema kproblema;

	private Set<SolucaoOcorrenciaProblema> solucaoocorrenciaproblema;

	private RecursoHumano recursohumano;

	private ProjetoProblema projetoproblema;

	private ArtefatoProblema artefatoproblema;

	private Set<HistoricoOcorrenciaProblema> historicoocorrenciaproblema;

	private EstadoOcorrencia estadoocorrencia;

	private NivelImpacto nivelimpactopresente;

	private NivelImpacto nivelimpactofuturo;

	// private EntidadeProblema entidadeproblema;
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// versão

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	// descrição
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// resubmetida
	public Boolean getResubmetida() {
		return resubmetida;
	}

	public void setResubmetida(Boolean resubmetida) {
		this.resubmetida = resubmetida;
	}

	@ManyToAny(metaColumn = @Column)
	public Set<KCausa> getKcausa() {
		return kcausa;
	}

	public void setKcausa(Set<KCausa> kcausa) {
		this.kcausa = kcausa;
	}

	@OneToMany
	public Set<SolucaoOcorrenciaProblema> getSolucaoocorrenciaproblema() {
		return solucaoocorrenciaproblema;
	}

	public void setSolucaoocorrenciaproblema(
			Set<SolucaoOcorrenciaProblema> solucaoocorrenciaproblema) {
		this.solucaoocorrenciaproblema = solucaoocorrenciaproblema;
	}

	@OneToMany
	public Set<HistoricoOcorrenciaProblema> getHistoricoocorrenciaproblema() {
		return historicoocorrenciaproblema;
	}

	public void setHistoricoocorrenciaproblema(
			Set<HistoricoOcorrenciaProblema> historicoocorrenciaproblema) {
		this.historicoocorrenciaproblema = historicoocorrenciaproblema;
	}

	@Enumerated(EnumType.ORDINAL)
	public EstadoOcorrencia getEstadoocorrencia() {
		return estadoocorrencia;
	}

	public void setEstadoocorrencia(EstadoOcorrencia estadoocorrencia) {
		this.estadoocorrencia = estadoocorrencia;
	}

	@ManyToOne
	public RecursoHumano getRecursohumano() {
		return recursohumano;
	}

	public void setRecursohumano(RecursoHumano recursohumano) {
		this.recursohumano = recursohumano;
	}

	@Enumerated(EnumType.ORDINAL)
	public NivelImpacto getNivelimpactopresente() {
		return nivelimpactopresente;
	}

	public void setNivelimpactopresente(NivelImpacto nivelimpactopresente) {
		this.nivelimpactopresente = nivelimpactopresente;
	}

	@Enumerated(EnumType.STRING)
	public NivelImpacto getNivelimpactofuturo() {
		return nivelimpactofuturo;
	}

	public void setNivelimpactofuturo(NivelImpacto nivelimpactofuturo) {
		this.nivelimpactofuturo = nivelimpactofuturo;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	@ManyToOne
	public ProjetoProblema getProjetoproblema() {
		return projetoproblema;
	}

	public void setProjetoproblema(ProjetoProblema projetoproblema) {
		this.projetoproblema = projetoproblema;
	}

	@ManyToOne
	public ArtefatoProblema getArtefatoproblema() {
		return artefatoproblema;
	}

	public void setArtefatoproblema(ArtefatoProblema artefatoproblema) {
		this.artefatoproblema = artefatoproblema;
	}

	@ManyToOne
	public KProblema getKproblema() {
		return kproblema;
	}

	public void setKproblema(KProblema kproblema) {
		this.kproblema = kproblema;
	}

}

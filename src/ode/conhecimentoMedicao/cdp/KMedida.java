package ode.conhecimentoMedicao.cdp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ode.conhecimento.principal.cdp.Conhecimento;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;

@Entity
public class KMedida extends Conhecimento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1131134688369383223L;
	private String mnemonico;
	private Set<KMedida> derivadaDe;
	private KElementoMensuravel propriedadeMedida;
	private KUnidadeMedida unidadeMedida;
	private KEscala escala;
	private NaturezaMedida naturezaMedida;
	private Set<NecessidadeInformacao> necessidade;
	private Set<KMedida> medidasCorrelatas;
	private Set<DefinicaoOperacionalMedida> definicoesMedida;
	
	public String getMnemonico() {
		return mnemonico;
	}
	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="DerivadaDeMedida")
	public Set<KMedida> getDerivadaDe() {
		return derivadaDe;
	}
	public void setDerivadaDe(Set<KMedida> derivadaDe) {
		this.derivadaDe = derivadaDe;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	public KElementoMensuravel getPropriedadeMedida() {
		return propriedadeMedida;
	}
	public void setPropriedadeMedida(KElementoMensuravel propriedadeMedida) {
		this.propriedadeMedida = propriedadeMedida;
	}
	
	public KUnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(KUnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
	public KEscala getEscala() {
		return escala;
	}
	public void setEscala(KEscala escala) {
		this.escala = escala;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<KMedida> getMedidasCorrelatas() {
		return medidasCorrelatas;
	}
	public void setMedidasCorrelatas(Set<KMedida> medidasCorrelatas) {
		this.medidasCorrelatas = medidasCorrelatas;
	}
	
	public NaturezaMedida getNaturezaMedida() {
		return naturezaMedida;
	}
	public void setNaturezaMedida(NaturezaMedida naturezaMedida) {
		this.naturezaMedida = naturezaMedida;
	}
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<DefinicaoOperacionalMedida> getDefinicoesMedida() {
		return definicoesMedida;
	}
	public void setDefinicoesMedida(Set<DefinicaoOperacionalMedida> definicoesMedida) {
		this.definicoesMedida = definicoesMedida;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="necessidade_medida")
	public Set<NecessidadeInformacao> getNecessidade() {
		return necessidade;
	}
	public void setNecessidade(Set<NecessidadeInformacao> necessidade) {
		this.necessidade = necessidade;
	}
	
	
}

package ode.conhecimentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KMedida extends Conhecimento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1131134688369383223L;
	private String mnemonico;
	private Set<KMedida> derivadaDe;
	private Set<KTipoEntidadeMensuravel> TiposEntidadeMensuraveis;
	private KElementoMensuravel propriedadeMedida;
	private KUnidadeMedida unidadeMedida;
	private KEscala escala;
	private NaturezaMedida naturezaMedida;
	private Set<KMedida> medidasCorrelatas;
	private Set<KNecessidadeInformacao> necessidadesInformacao;
	
	public String getMnemonico() {
		return mnemonico;
	}
	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	public Set<KMedida> getDerivadaDe() {
		return derivadaDe;
	}
	public void setDerivadaDe(Set<KMedida> derivadaDe) {
		this.derivadaDe = derivadaDe;
	}
	
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
	
	@ManyToMany(fetch=FetchType.LAZY)
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
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<KTipoEntidadeMensuravel> getTiposEntidadeMensuraveis() {
		return TiposEntidadeMensuraveis;
	}
	public void setTiposEntidadeMensuraveis(Set<KTipoEntidadeMensuravel> tiposEntidadeMensuraveis) {
		TiposEntidadeMensuraveis = tiposEntidadeMensuraveis;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<KNecessidadeInformacao> getNecessidadesInformacao() {
		return necessidadesInformacao;
	}
	public void setNecessidadesInformacao(Set<KNecessidadeInformacao> necessidadesInformacao) {
		this.necessidadesInformacao = necessidadesInformacao;
	}
	
}

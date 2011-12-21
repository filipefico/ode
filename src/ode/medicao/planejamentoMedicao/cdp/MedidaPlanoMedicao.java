package ode.medicao.planejamentoMedicao.cdp;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimentoMedicao.cdp.KMedida;

@Entity
public class MedidaPlanoMedicao extends ObjetoPersistente{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7627860090723974323L;
	private KMedida medida;
	private DefinicaoOperacionalMedida definicaoOperacional;
	private Set<ValorReferencia> valoresReferencia;
	private Collection<ObjetivoMedicao> objetivosMedicao;
	
	
	public KMedida getMedida() {
		return medida;
	}
	public void setMedida(KMedida medida) {
		this.medida = medida;
	}
	
	public DefinicaoOperacionalMedida getDefinicaoOperacional() {
		return definicaoOperacional;
	}
	public void setDefinicaoOperacional(DefinicaoOperacionalMedida definicaoOperacional) {
		this.definicaoOperacional = definicaoOperacional;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<ValorReferencia> getValoresReferencia() {
		return valoresReferencia;
	}
	public void setValoresReferencia(Set<ValorReferencia> valoresReferencia) {
		this.valoresReferencia = valoresReferencia;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<ObjetivoMedicao> getObjetivosMedicao() {
		return objetivosMedicao;
	}
	public void setObjetivosMedicao(Collection<ObjetivoMedicao> objetivosMedicao) {
		this.objetivosMedicao = objetivosMedicao;
	}
}

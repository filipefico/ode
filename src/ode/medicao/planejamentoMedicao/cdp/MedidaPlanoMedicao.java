package ode.medicao.planejamentoMedicao.cdp;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cdp.KMedida;

@Entity
public class MedidaPlanoMedicao extends ObjetoPersistente{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7627860090723974323L;
	private KMedida medida;
	private KDefinicaoOperacionalMedida definicaoOperacional;
	private Collection<ValorReferencia> valoresReferencia;
	private Collection<KObjetivoMedicao> objetivosMedicao;
	
	
	public KMedida getMedida() {
		return medida;
	}
	public void setMedida(KMedida medida) {
		this.medida = medida;
	}
	
	public KDefinicaoOperacionalMedida getDefinicaoOperacional() {
		return definicaoOperacional;
	}
	public void setDefinicaoOperacional(KDefinicaoOperacionalMedida definicaoOperacional) {
		this.definicaoOperacional = definicaoOperacional;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<ValorReferencia> getValoresReferencia() {
		return valoresReferencia;
	}
	public void setValoresReferencia(Collection<ValorReferencia> valoresReferencia) {
		this.valoresReferencia = valoresReferencia;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	public Collection<KObjetivoMedicao> getObjetivosMedicao() {
		return objetivosMedicao;
	}
	public void setObjetivosMedicao(Collection<KObjetivoMedicao> objetivosMedicao) {
		this.objetivosMedicao = objetivosMedicao;
	}
}

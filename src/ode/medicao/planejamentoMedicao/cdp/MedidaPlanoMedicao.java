package ode.medicao.planejamentoMedicao.cdp;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	private Set<NecessidadeInformacao> necessidadesInformacao;
	private Set<ObjetivoMedicao> indicaAlcanceA;
	
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
	public Set<NecessidadeInformacao> getNecessidadesInformacao() {
		return necessidadesInformacao;
	}

	public void setNecessidadesInformacao(Set<NecessidadeInformacao> necessidadeInformacao) {
		this.necessidadesInformacao = necessidadeInformacao;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<ObjetivoMedicao> getIndicaAlcanceA() {
		return indicaAlcanceA;
	}
	
	public void setIndicaAlcanceA(Set<ObjetivoMedicao> indicaAlcance) {
		this.indicaAlcanceA = indicaAlcance;
	}
	
	public boolean isIndicadorDe(ObjetivoMedicao objetoSelecionado) {
		return indicaAlcanceA.contains(objetoSelecionado);
	}
	
	public Set<ValorReferencia> getValoresReferencia(FaixaReferencia FR) {
		Set<ValorReferencia> vrs = new HashSet<ValorReferencia>();
		for(ValorReferencia vr:valoresReferencia){
			if(vr.getFaixa().equals(FR)){
				vrs.add(vr);
			}
		}
		return vrs;
	}
	
	public ValorReferencia getUltimoValorReferencia(FaixaReferencia FR) {
		ValorReferencia vrUltimo = null;
		for(ValorReferencia vr:valoresReferencia){
			if(vr.getFaixa().equals(FR)){
				if(vrUltimo==null){
					vrUltimo = vr;
				}
				if(vrUltimo.getData().before(vr.getData())){
					vrUltimo = vr;
				}
			}
		}
		return vrUltimo;		
	}
	
	public String toString(){
		return getMedida().toString();
	}
	
}

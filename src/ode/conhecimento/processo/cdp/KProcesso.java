package ode.conhecimento.processo.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KProcesso extends Conhecimento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String proposito;
	private String sigla;
	private boolean ehEngenharia;
	
	private Set<KTipoInteracao> kTipoInteracao;
    
	private KCategoriaProcesso categoria;
    
	private Set<KResultadoProcesso> kResultadoProcesso;
	
	public KProcesso() {
	}
	
	public String getProposito() {
		return proposito;
	}

	public void setProposito(String parProposito) {
		this.proposito = parProposito;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String parSigla) {
		this.sigla = parSigla;
	}

	public boolean isEhEngenharia() {
		return ehEngenharia;
	}

	public void setEhEngenharia(boolean parEhEngenharia) {
		this.ehEngenharia = parEhEngenharia;
	}

	@ManyToMany (fetch = FetchType.LAZY)
	public Set<KTipoInteracao> getKTipoInteracao(){
		return kTipoInteracao;
	}

	public void setKTipoInteracao(Set<KTipoInteracao> parKTI){
		kTipoInteracao = parKTI;
	}
	
	@ManyToOne (fetch = FetchType.LAZY)
	public KCategoriaProcesso getCategoria() {
		return categoria;
	}

	public void setCategoria(KCategoriaProcesso parCategoria) {
		this.categoria = parCategoria;
	}

	@OneToMany (fetch = FetchType.LAZY)
	public Set<KResultadoProcesso> getKResultadoProcesso(){
		return kResultadoProcesso;
	}

	public void setKResultadoProcesso(Set parKRP){
		kResultadoProcesso = parKRP;
	}
	
    
}

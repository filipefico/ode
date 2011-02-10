package ode.conhecimento.processo.Cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import ode.conhecimento.principal.Cdp.Conhecimento;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cdp.KResultadoProcesso;
import ode.conhecimento.processo.Cdp.KTipoInteracao;

@Entity
public class KProcesso extends Conhecimento{
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

	@ManyToMany (fetch = FetchType.EAGER)
	public Set<KTipoInteracao> getKTipoInteracao(){
		return kTipoInteracao;
	}

	public void setKTipoInteracao(Set<KTipoInteracao> parKTI){
		kTipoInteracao = parKTI;
	}
	
	@ManyToOne (fetch = FetchType.EAGER)
	public KCategoriaProcesso getCategoria() {
		return categoria;
	}

	public void setCategoria(KCategoriaProcesso parCategoria) {
		this.categoria = parCategoria;
	}

	@OneToMany (fetch = FetchType.EAGER)
	public Set<KResultadoProcesso> getKResultadoProcesso(){
		return kResultadoProcesso;
	}

	public void setKResultadoProcesso(Set parKRP){
		kResultadoProcesso = parKRP;
	}
}

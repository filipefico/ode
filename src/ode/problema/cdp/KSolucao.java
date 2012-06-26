package ode.problema.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KSolucao extends Conhecimento {

	private static final long serialVersionUID = 6294281466361763403L;
	private Set<KCausa> KCausa;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="KSolucao_KCausa")
	public Set<KCausa> getKCausa() {
		return KCausa;
	}
	public void setKCausa(Set<KCausa> kCausa) {
		KCausa = kCausa;
	}
	

	
	
}

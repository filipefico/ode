package ode.processoPadrao.Cdp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.processoPadrao.Cdp.CompPP;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CompPPProcessoComplexo extends CompPP {
	private static final long serialVersionUID = 3645553289873117858L;

	/** Processos Padrão Específicos contidos neste */
	private Set<CompPPProcessoSimples> processosSimples;

	
	public CompPPProcessoComplexo() {
		this.processosSimples = new HashSet<CompPPProcessoSimples>();
	}

	
	// Obtém os Processos especificos deste.
	@ManyToMany(targetEntity = CompPPProcessoSimples.class, fetch = FetchType.EAGER)
	public Set<CompPPProcessoSimples> getProcessosSimples() {
		return processosSimples;
	}

	public void setProcessosSimples(
			Set<CompPPProcessoSimples> parProcessosSimples) {
		this.processosSimples = parProcessosSimples;
	}

	public void addProcessosSimples(CompPPProcessoSimples parProcessosSimples) {
		this.processosSimples.add(parProcessosSimples);
	}

	public void addProcessosSimples(
			Set<CompPPProcessoSimples> parProcessosSimples) {
		this.processosSimples.addAll(parProcessosSimples);
	}

	public CompPPProcessoSimples obterProcessoEngenharia() {

		List<CompPPProcessoSimples> locProcessosEspecificos = new ArrayList<CompPPProcessoSimples>(
				this.processosSimples);

		for (int i = 0; i < locProcessosEspecificos.size(); i++) {
			CompPPProcessoSimples locProc = (CompPPProcessoSimples) locProcessosEspecificos
					.get(i);
			if (locProc.getTipo().isEhEngenharia())
				return locProc;
		}

		return null;
	}

	public ArrayList<KProcesso> obterKSubProcessos() {
		ArrayList<CompPPProcessoSimples> locProcessosEspecificos = new ArrayList<CompPPProcessoSimples>(
				this.processosSimples);
		ArrayList<KProcesso> processos = new ArrayList<KProcesso>();

		for (int i = 0; i < locProcessosEspecificos.size(); i++) {
			CompPPProcessoSimples locProc = (CompPPProcessoSimples) locProcessosEspecificos
					.get(i);
			processos.add(locProc.getTipo());
		}

		return processos;
	}
}

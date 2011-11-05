package ode.processoPadrao.cdp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import ode.conhecimento.processo.cdp.KProcesso;


@Entity
public class CompPPProcessoComplexo extends CompPP implements Cloneable {
	private static final long serialVersionUID = 3645553289873117858L;

	/** Processos Padrão Específicos contidos neste */
	private Set<CompPPProcessoSimples> processosSimples;

	
	public CompPPProcessoComplexo() {
		this.processosSimples = new HashSet<CompPPProcessoSimples>();
	}

	
	// Obtém os Processos especificos deste.
	//@ManyToMany(targetEntity = CompPPProcessoSimples.class, fetch = FetchType.EAGER)
	@ManyToMany(cascade = {javax.persistence.CascadeType.MERGE,javax.persistence.CascadeType.PERSIST},targetEntity = CompPPProcessoSimples.class, fetch = FetchType.EAGER)
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
	
	@Override
	public CompPPProcessoComplexo clone() throws CloneNotSupportedException {
		
		CompPPProcessoComplexo copia = (CompPPProcessoComplexo) super.clone();
		copia.setProcessosSimples(new HashSet<CompPPProcessoSimples>());
		
		for (CompPPProcessoSimples PPsimples : this.getProcessosSimples()) {
			copia.getProcessosSimples().add((CompPPProcessoSimples) PPsimples.clone());
		}
		
		return copia;
	}
}

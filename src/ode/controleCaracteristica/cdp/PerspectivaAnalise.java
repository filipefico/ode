package ode.controleCaracteristica.cdp;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;

@Entity
public class PerspectivaAnalise extends ObjetoPersistente {

			private static final long serialVersionUID = 1L;

			private String nome;
			private String descricao;
			private Set<Importancia> importancia= new HashSet<Importancia>();
			private EnumTipoItemSoftware tipo;
			private Set<KProcesso> kprocessos =new HashSet<KProcesso>();
			private Set<KAtividade> katividades= new HashSet<KAtividade>();
			
			@Column(length = 80)
			public String getNome() {
				return nome;
			}

			public void setNome(String parNome) {
				nome = parNome;
			}

			public String getDescricao() {
				return descricao;
			}

			public void setDescricao(String descricao) {
				this.descricao = descricao;
			}
			
			@OneToMany(fetch = FetchType.EAGER)
			public Set<Importancia> getImportancia() {
				return importancia;
			}

			public void setImportancia(Set<Importancia> importancia) {
				this.importancia.addAll(importancia);
				
			}
/*
			public void addImportancia(Set<Importancia> setPV){
				this.importancia.addAll(setPV);
			}
*/
			///@ManyToOne(fetch = FetchType.EAGER)
			@Enumerated(EnumType.STRING)
			public EnumTipoItemSoftware getTipo() {
				return tipo;
			}

			public void setTipo(EnumTipoItemSoftware tipo) {
				this.tipo = tipo;
			}
			
			@ManyToMany(fetch = FetchType.EAGER)
			public Set<KProcesso> getKProcessos() {
				return kprocessos;
			}

			public void setKProcessos(Set<KProcesso> kprocessos) {
				this.kprocessos = kprocessos;
			}

			public void addKProcessos(Set<KProcesso> setPV){
				this.kprocessos.addAll(setPV);
			}
			
			@ManyToMany(fetch = FetchType.EAGER)
			public Set<KAtividade> getKAtividades() {
				return katividades;
			}

			public void setKAtividades(Set<KAtividade> katividades) {
				this.katividades = katividades;
			}

			public void addKAtividades(Set<KAtividade> setPV){
				this.katividades.addAll(setPV);
			}
}

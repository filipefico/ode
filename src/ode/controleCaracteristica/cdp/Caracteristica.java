package ode.controleCaracteristica.cdp;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;
import org.jcodings.util.Hash;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

@Entity
abstract public class Caracteristica extends ObjetoPersistente {

		private static final long serialVersionUID = 1L;
		
		protected String nome;
		private String descricao;
		protected Set<PossivelValor> possiveisValores = new HashSet<PossivelValor>();
		private Set<EnumTipoItemSoftware> aplicabilidades; 
		
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
		
		/**
		 * Esse metodo, diferente do metodo setPossiveisValores, adiciona mais
		 * elementos a lista já criada.
		 * 
		 * @param conjPossiveisValores
		 *            Possiveis valores que serão adicionados
		 */
		//@OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = PossivelValor.class, fetch = FetchType.EAGER)
		@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
		public Set<PossivelValor> getPossiveisValores() {
			return possiveisValores;
		}

		public void setPossiveisValores(Set<PossivelValor> possiveisValores) {
			this.possiveisValores = possiveisValores;
		}
		public void addPossivelValor(PossivelValor setPV){
			this.possiveisValores.add(setPV);
		}
		
		///@ManyToMany(fetch = FetchType.EAGER)
		@SuppressWarnings("deprecation")
		@CollectionOfElements
		@Enumerated(EnumType.STRING)
		public Set<EnumTipoItemSoftware> getSetEnum() {
			return aplicabilidades;
		}

		public void setSetEnum(Set<EnumTipoItemSoftware> setEnum1) {
			this.aplicabilidades = setEnum1;
		}

		public void addEnumTIS(Set<EnumTipoItemSoftware> setPV){
			this.aplicabilidades.addAll(setPV);
		}
		
		public String toString() {
			return this.nome;
		}
}

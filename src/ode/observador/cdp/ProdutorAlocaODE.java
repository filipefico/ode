package ode.observador.cdp;

import java.util.Observable;

import ode.alocacaoRecurso.cdp.AlocacaoRH;

public class ProdutorAlocaODE extends Observable {
	
	public static ProdutorAlocaODE instance = null;
	
	private AlocacaoRH alocacaoRH;
	private TipoAlocacao tipo;
	private String nomeProjeto;
	
	private ProdutorAlocaODE(){
		this.alocacaoRH = null;
		this.tipo = null;
	}
	
	//singleton
	public static ProdutorAlocaODE getInstance(){
		if(instance == null) {
			instance = new ProdutorAlocaODE();
		}
		return instance;
	}
	
	
	public AlocacaoRH getAlocacaoRH() {
		return alocacaoRH;
	}
	public void setAlocacaoRH(AlocacaoRH alocacaoRH) {
		this.alocacaoRH = alocacaoRH;
	}

	public TipoAlocacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoAlocacao tipo) {
		this.tipo = tipo;
	}
	
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public void setModify(AlocacaoRH alocacaoRH, TipoAlocacao tipoAlocacao, String nomeProjeto){
		this.alocacaoRH = alocacaoRH;
		this.tipo = tipoAlocacao;
		this.nomeProjeto = nomeProjeto;
		
		setChanged();
		
		notifyObservers();
	}


	public enum TipoAlocacao{
		
		CRIAR(1, "Criar"), ATUALIZAR(2, "Atualizar"), EXCLUIR(3, "Excluir");
		
		private int valor;
		private String descricao;
		
		TipoAlocacao(int valor, String descricao){
			this.valor = valor;
			this.descricao = descricao;
		}
		
		public int getValor(){
			return this.valor;
		}
		
		public String getDescricao(){
			return this.descricao;
		}
		
	}

}

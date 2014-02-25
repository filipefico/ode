package ode.observador.cdp;

import java.util.Observable;
import ode.controleProjeto.cdp.Projeto;



public class ProdutorProjeto extends Observable {

	public static ProdutorProjeto instance = null;
	
	private Projeto projeto;
	private TipoProjeto tipo;
	
	private ProdutorProjeto(){
		this.projeto = null;
		this.tipo = null;
	}
	
	
	//singleton
	public static ProdutorProjeto getInstance(){
		if(instance == null) {
			instance = new ProdutorProjeto();
		}
		return instance;
	}
	
	
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	public TipoProjeto getTipo() {
		return tipo;
	}
	public void setTipo(TipoProjeto tipo) {
		this.tipo = tipo;
	}

	
	public void setModify(Projeto projeto, TipoProjeto tipoProjeto){
		this.projeto = projeto;
		this.tipo = tipoProjeto;
		
		setChanged();
		
		notifyObservers();
	}

	//verificar se tem com atualizar mesmo
	public enum TipoProjeto{
		CRIAR(1, "Criar"), ATUALIZAR(2, "Atualizar"), EXCLUIR(3, "Excluir");
		
		private int valor;
		private String descricao;
		
		TipoProjeto(int valor, String descricao){
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

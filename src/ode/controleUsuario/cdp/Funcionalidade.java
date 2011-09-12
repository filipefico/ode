package ode.controleUsuario.cdp;

import java.util.ArrayList;
import java.util.List;

import ode.nucleo.cci.CtrlBase;
import ode.nucleo.cdp.NucleoObjetoDominioImpl;

public class Funcionalidade extends NucleoObjetoDominioImpl {

	private static final long serialVersionUID = 1L;

	// Nome da funcionalidade
	private String nome;

	// Caminho da Controlador da funcionalidade
	private String ctrl = null;

	// Verifica se a funcionalidade está disponível apenas para projetos abertos
	private boolean disponivelApenasParaProjetosAbertos = false;

	// Subfuncionalidades
	private List<Funcionalidade> subfuncionalidades = new ArrayList<Funcionalidade>();
	
	// Permissões
	private List<PerfilAcesso> permissoes = new ArrayList<PerfilAcesso>();
	
	public Funcionalidade(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Funcionalidade setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public boolean isDisponivelApenasParaProjetosAbertos() {
		return disponivelApenasParaProjetosAbertos;
	}

	public Funcionalidade setDisponivelApenasParaProjetosAbertos(
			boolean disponivelApenasParaProjetosAbertos) {
		this.disponivelApenasParaProjetosAbertos = disponivelApenasParaProjetosAbertos;
		return this;
	}
	
	public String getCtrl() {
		return ctrl;
	}
	
	public Funcionalidade setCtrl(Class<? extends CtrlBase> ctrl) {
		this.ctrl = ctrl.getCanonicalName();
		return this;
	}

	public Funcionalidade setCtrl(String srcCtrl) {
		this.ctrl = srcCtrl;
		return this;
	}

	public List<Funcionalidade> getSubfuncionalidades() {
		return subfuncionalidades;
	}	

	public Funcionalidade addSubfuncionalidade(Funcionalidade f) {
		this.subfuncionalidades.add(f);
		return this;
	}

	public Funcionalidade permitir(PerfilAcesso perfil) {
		this.permissoes.add(perfil);
		return this;
	}
	
	public boolean permite(PerfilAcesso perfil) {
		return this.permissoes.contains(perfil);
	}
}
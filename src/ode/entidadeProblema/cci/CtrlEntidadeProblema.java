package ode.entidadeProblema.cci;

import java.util.Collection;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cgt.AplCadastrarKArtefato;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.entidadeProblema.cdp.ArtefatoProblema;
import ode.entidadeProblema.cdp.ProjetoProblema;
import ode.entidadeProblema.cgt.AplCadastrarArtefatoProblema;
import ode.entidadeProblema.cgt.AplCadastrarProjetoProblema;
import ode.entidadeProblema.cih.PainelEntidadeProblema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class CtrlEntidadeProblema extends CtrlBase  {

	
	private static final long serialVersionUID = -5151215043403557105L;
	@Autowired
	AplCadastrarKArtefato aplArt;
	@Autowired
	AplCadastrarArtefatoProblema aplArtP;
	@Autowired
	AplCadastrarProjeto aplProj;
	@Autowired
	AplCadastrarProjetoProblema aplProjP;

	private JanelaSimples jan;

	@Override
	public void iniciar() {
		jan = factoryJanelaSimples();
		jan.setTitle("Entidade Problema");
		PainelEntidadeProblema p = new PainelEntidadeProblema();
		p.setCtrl(this);
		p.setParent(jan);
		p.montar();
		jan.mostrar();
	}


	public Collection<KArtefato> recuperarArtefato() {
		return aplArt.recuperarTodos();
	}

	public Collection<Projeto> recuperarProjeto() {
		return aplProj.recuperarTodos();
	}

	public Collection<ArtefatoProblema> recuperarArtefatoProblema() {
		return aplArtP.recuperarTodos();
	}

	public Collection<ProjetoProblema> recuperarProjetoProblema() {
		return aplProjP.recuperarTodos();
	}
			
	public void atualizarArtefato(Collection<KArtefato> leftItens,
			Collection<KArtefato> rightItens) {
		try {
			for (KArtefato artefato : leftItens) {
				Collection<ArtefatoProblema> artefatosProblema = aplArtP.obterPorArtefato(artefato);
				for (ArtefatoProblema artefatoProblema : artefatosProblema) {
					artefatoProblema.setKartefato(null);
					aplArtP.excluir(artefatoProblema);
				}
			}
			for (KArtefato artefato : rightItens) {
				Collection<ArtefatoProblema> artefatosProblema = aplArtP.obterPorArtefato(artefato);
				if (artefatosProblema.size() == 0){
					ArtefatoProblema artefatoProblema = new ArtefatoProblema();
					artefatoProblema.setKartefato(artefato);
					aplArtP.salvar(artefatoProblema);
				}
			}
		} catch (NucleoRegraNegocioExcecao e) {
			e.printStackTrace();
		}
	}

	

	public void atualizarProjeto(Collection<Projeto> leftItens,
			Collection<Projeto> rightItens) {
		try {
			for (Projeto projeto : leftItens) {
				Collection<ProjetoProblema> projetosProblema = aplProjP.obterPorProjeto(projeto);
				for (ProjetoProblema projetoProblema : projetosProblema) {
					projetoProblema.setProjeto(null);
					aplProjP.excluir(projetoProblema);
				}
			}
			for (Projeto artefato : rightItens) {
				Collection<ProjetoProblema> projetosProblema = aplProjP.obterPorProjeto(artefato);
				if (projetosProblema.size() == 0){
					ProjetoProblema projetoProblema = new ProjetoProblema();
					projetoProblema.setProjeto(artefato);
					aplProjP.salvar(projetoProblema);
				}
			}
		} catch (NucleoRegraNegocioExcecao e) {
			e.printStackTrace();
		}
	}


}

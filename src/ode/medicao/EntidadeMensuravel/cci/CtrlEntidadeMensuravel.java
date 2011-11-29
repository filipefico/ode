package ode.medicao.EntidadeMensuravel.cci;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKArtefato;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.medicao.EntidadeMensuravel.cdp.ArtefatoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.AtividadeMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ProcessoPadraoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ProjetoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.RecursoHumanoMensuravel;
import ode.medicao.EntidadeMensuravel.cgt.AplCadastrarArtefatoMensuravel;
import ode.medicao.EntidadeMensuravel.cgt.AplCadastrarAtividadeMensuravel;
import ode.medicao.EntidadeMensuravel.cgt.AplCadastrarProcessoPadraoMensuravel;
import ode.medicao.EntidadeMensuravel.cgt.AplCadastrarProjetoMensuravel;
import ode.medicao.EntidadeMensuravel.cgt.AplCadastrarRecursoHumanoMensuravel;
import ode.medicao.EntidadeMensuravel.cih.PainelEntidadeMensuravel;

@Controller
public class CtrlEntidadeMensuravel extends CtrlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 679579956433365268L;
	@Autowired
	AplCadastrarKRecursoHumano aplRH;
	@Autowired
	AplCadastrarRecursoHumanoMensuravel aplRHM;
	@Autowired
	AplCadastrarKArtefato aplArt;
	@Autowired
	AplCadastrarArtefatoMensuravel aplArtM;
	@Autowired
	AplCadastrarKAtividade aplAtv;
	@Autowired
	AplCadastrarAtividadeMensuravel aplAtvM;
	@Autowired
	AplCadastrarKProcesso aplProc;
	@Autowired
	AplCadastrarProcessoPadraoMensuravel aplProcM;
	@Autowired
	AplCadastrarProjeto aplProj;
	@Autowired
	AplCadastrarProjetoMensuravel aplProjM;

	private JanelaSimples jan;

	@Override
	public void iniciar() {
		jan = factoryJanelaSimples();
		jan.setTitle("Entidade Mensuravel");
		PainelEntidadeMensuravel p = new PainelEntidadeMensuravel();
		p.setCtrl(this);
		p.setParent(jan);
		p.montar();
		jan.mostrar();
	}

	public Collection<KRecursoHumano> recuperarRecurso() {
		return aplRH.recuperarTodos();
	}

	public Collection<KAtividade> recuperarAtividade() {
		return aplAtv.recuperarTodos();
	}

	public Collection<KArtefato> recuperarArtefato() {
		return aplArt.recuperarTodos();
	}

	public Collection<KProcesso> recuperarProcesso() {
		return aplProc.recuperarTodos();
	}

	public Collection<Projeto> recuperarProjeto() {
		return aplProj.recuperarTodos();
	}

	public Collection<RecursoHumanoMensuravel> recuperarRecursoMensuravel() {
		return aplRHM.recuperarTodos();
	}

	public Collection<AtividadeMensuravel> recuperarAtividadeMensuravel() {
		return aplAtvM.recuperarTodos();
	}

	public Collection<ArtefatoMensuravel> recuperarArtefatoMensuravel() {
		return aplArtM.recuperarTodos();
	}

	public Collection<ProcessoPadraoMensuravel> recuperarProcessoMensuravel() {
		return aplProcM.recuperarTodos();
	}

	public Collection<ProjetoMensuravel> recuperarProjetoMensuravel() {
		return aplProjM.recuperarTodos();
	}

	public void atualizarRecurso(Collection<RecursoHumanoMensuravel> leftItens,
			Collection<RecursoHumanoMensuravel> rightItens) {
		try {
			for (RecursoHumanoMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					aplRHM.excluir(rhm);
				}
			}
			for (RecursoHumanoMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					aplRHM.salvar(rhm);
				}
			}
		} catch (NucleoRegraNegocioExcecao e) {
		}
	}

	public void atualizarAtividade(Collection<AtividadeMensuravel> leftItens,
			Collection<AtividadeMensuravel> rightItens) {
		try {
			for (AtividadeMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					aplAtvM.excluir(rhm);
				}
			}
			for (AtividadeMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					aplAtvM.salvar(rhm);
				}
			}
		} catch (NucleoRegraNegocioExcecao e) {
		}
	}

	public void atualizarArtefato(Collection<ArtefatoMensuravel> leftItens,
			Collection<ArtefatoMensuravel> rightItens) {
		try {
			for (ArtefatoMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					aplArtM.excluir(rhm);
				}
			}
			for (ArtefatoMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					aplArtM.salvar(rhm);
				}
			}
		} catch (NucleoRegraNegocioExcecao e) {
		}
	}

	public void atualizarProcesso(
			Collection<ProcessoPadraoMensuravel> leftItens,
			Collection<ProcessoPadraoMensuravel> rightItens) {
		try {
			for (ProcessoPadraoMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					aplProcM.excluir(rhm);
				}
			}
			for (ProcessoPadraoMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					aplProcM.salvar(rhm);
				}
			}
		} catch (NucleoRegraNegocioExcecao e) {
		}
	}

	public void atualizarProjeto(Collection<ProjetoMensuravel> leftItens,
			Collection<ProjetoMensuravel> rightItens) {
		try {
			for (ProjetoMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					aplProjM.excluir(rhm);
				}
			}
			for (ProjetoMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					aplProjM.salvar(rhm);
				}
			}
		} catch (NucleoRegraNegocioExcecao e) {
		}
	}

}

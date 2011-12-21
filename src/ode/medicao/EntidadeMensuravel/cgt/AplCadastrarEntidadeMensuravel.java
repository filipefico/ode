package ode.medicao.EntidadeMensuravel.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._controleProcesso.cgd.ProcessoProjetoEspecificoDAO;
import ode.medicao.EntidadeMensuravel.cdp.ArtefatoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.AtividadeMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ProcessoPadraoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.ProjetoMensuravel;
import ode.medicao.EntidadeMensuravel.cdp.RecursoHumanoMensuravel;
import ode.medicao.EntidadeMensuravel.cgd.ArtefatoMensuravelDAO;
import ode.medicao.EntidadeMensuravel.cgd.AtividadeMensuravelDAO;
import ode.medicao.EntidadeMensuravel.cgd.ProcessoPadraoMensuravelDAO;
import ode.medicao.EntidadeMensuravel.cgd.ProjetoMensuravelDAO;
import ode.medicao.EntidadeMensuravel.cgd.RecursoHumanoMensuravelDAO;

@Service
public class AplCadastrarEntidadeMensuravel {

	@Autowired
	ArtefatoMensuravelDAO artefatoDao;
	@Autowired
	AtividadeMensuravelDAO atividadeDao;
	@Autowired
	ProcessoPadraoMensuravelDAO PPMDao;
	@Autowired
	ProcessoProjetoEspecificoDAO PPEDao;
	@Autowired
	ProjetoMensuravelDAO projetoDao;
	@Autowired
	RecursoHumanoMensuravelDAO RHDao;

	public Collection<RecursoHumanoMensuravel> recuperarRecursoMensuravel() {
		return RHDao.recuperarTodos();
	}

	public Collection<AtividadeMensuravel> recuperarAtividadeMensuravel() {
		return atividadeDao.recuperarTodos();
	}

	public Collection<ArtefatoMensuravel> recuperarArtefatoMensuravel() {
		return artefatoDao.recuperarTodos();
	}

	public Collection<ProcessoPadraoMensuravel> recuperarProcessoMensuravel() {
		return PPMDao.recuperarTodos();
	}

	public Collection<ProjetoMensuravel> recuperarProjetoMensuravel() {
		return projetoDao.recuperarTodos();
	}

	public void atualizarRecurso(Collection<RecursoHumanoMensuravel> leftItens,
			Collection<RecursoHumanoMensuravel> rightItens) {
		for (RecursoHumanoMensuravel rhm : leftItens) {
			if (rhm.isPersistente()) {
				RHDao.excluir(rhm);
			}
		}
		for (RecursoHumanoMensuravel rhm : rightItens) {
			if (!rhm.isPersistente()) {
				RHDao.salvar(rhm);
			}
		}
	}

	public void atualizarAtividade(Collection<AtividadeMensuravel> leftItens,
			Collection<AtividadeMensuravel> rightItens) {
			for (AtividadeMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					atividadeDao.excluir(rhm);
				}
			}
			for (AtividadeMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					atividadeDao.salvar(rhm);
				}
			}
	}

	public void atualizarArtefato(Collection<ArtefatoMensuravel> leftItens,
			Collection<ArtefatoMensuravel> rightItens) {
			for (ArtefatoMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					artefatoDao.excluir((ArtefatoMensuravel)rhm);
				}
			}
			for (ArtefatoMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					artefatoDao.salvar((ArtefatoMensuravel)rhm);
				}
			}
	}

	public void atualizarProcesso(
			Collection<ProcessoPadraoMensuravel> leftItens,
			Collection<ProcessoPadraoMensuravel> rightItens) {
			for (ProcessoPadraoMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					PPMDao.excluir((ProcessoPadraoMensuravel)rhm);
				}
			}
			for (ProcessoPadraoMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					PPMDao.salvar((ProcessoPadraoMensuravel)rhm);
				}
			}
	}

	public void atualizarProjeto(Collection<ProjetoMensuravel> leftItens,
			Collection<ProjetoMensuravel> rightItens) {
			for (ProjetoMensuravel rhm : leftItens) {
				if (rhm.isPersistente()) {
					projetoDao.excluir(rhm);
				}
			}
			for (ProjetoMensuravel rhm : rightItens) {
				if (!rhm.isPersistente()) {
					projetoDao.salvar(rhm);
				}
			}
	}

}
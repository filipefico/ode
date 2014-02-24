package ode.alocacaoRecurso.cgt;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleProcesso.cdp.Atividade;
import ode._controleProcesso.cdp.DemandaRH;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.alocacaoRecurso.cdp.AlocacaoFerramentaSoftware;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cgd.AlocacaoFerramentaSoftwareDAO;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;
import ode.controleProjeto.cdp.Projeto;
import ode.observador.cdp.ProdutorAlocaODE;
import ode.observador.cdp.ProdutorAlocaODE.TipoAlocacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplAlocarRecurso {

	@Autowired
	private AlocacaoRHDAO alocacaoRHDAO;
	
	@Autowired
	private AlocacaoFerramentaSoftwareDAO alocacaoFerramentaSoftwareDAO;
	
	//Produtor que avisa a agenda que um evento envolvendo uma alocacao aconteceu
	private ProdutorAlocaODE alocaODE = ProdutorAlocaODE.getInstance();
	
	public void alocarRecursosHumanos(DemandaRH demandaRH, Set<RecursoHumano> objetosSelecionados, Set<RecursoHumano> objetosNaoSelecionados) {
		for(RecursoHumano rh : objetosSelecionados) {
			alocarRecursoHumano(demandaRH, rh);
		}
		for(RecursoHumano rh : objetosNaoSelecionados) {
			desalocarRecursoHumano(demandaRH, rh);
		}
	}

	public void alocarRecursoHumano(DemandaRH demandaRH, RecursoHumano rh) {
		AlocacaoRH a = alocacaoRHDAO.recuperarPorRecursoHumanoAtividadeKRecursoHumano(rh.getId(), demandaRH.getDefinicaoAtividade().getAtividade().getId(), demandaRH.getkRecursoHumano().getId());
		if(a==null) {
			a = new AlocacaoRH(demandaRH.getDefinicaoAtividade().getAtividade(), rh, demandaRH.getkRecursoHumano());
			alocacaoRHDAO.salvar(a);
						
			//Notifica
			//alocaODE.setModify(a, TipoAlocacao.CRIAR);
		}
	}
	
	public void desalocarRecursoHumano(DemandaRH demandaRH, RecursoHumano rh) {
		AlocacaoRH a = alocacaoRHDAO.recuperarPorRecursoHumanoAtividadeKRecursoHumano(rh.getId(), demandaRH.getDefinicaoAtividade().getAtividade().getId(), demandaRH.getkRecursoHumano().getId());
		if(a!=null){
			alocacaoRHDAO.excluir(a);
			
		
			//Notifica
			alocaODE.setModify(a, TipoAlocacao.EXCLUIR, "");
		}
	}	
	
	public void editarAlocacao(AlocacaoRH alocacaoRH, Integer dedicacao, Date dtInicioPrevisto, Date dtFimPrevisto) {
		alocacaoRH.setDedicacao(dedicacao);
		alocacaoRH.setDtInicioPrevisto(dtInicioPrevisto);
		alocacaoRH.setDtFimPrevisto(dtFimPrevisto);
		alocacaoRHDAO.atualizar(alocacaoRH);
		
		
		//Notifica
		alocaODE.setModify(alocacaoRH, TipoAlocacao.ATUALIZAR,NucleoContexto.recuperarProjeto().getNome());
	}
	
	public void alocarFerramentasSoftware(Atividade atividade, Collection<FerramentaSoftware> objetosSelecionados, Collection<FerramentaSoftware> objetosNaoSelecionados) {
		for(FerramentaSoftware fs : objetosSelecionados) {
			alocarFerramentaSoftware(atividade, fs);
		}
		for(FerramentaSoftware fs : objetosNaoSelecionados) {
			desalocarFerramentaSoftware(atividade, fs);
		}
	}
	
	public void alocarFerramentaSoftware(Atividade atividade, FerramentaSoftware fs) {
		AlocacaoFerramentaSoftware a = alocacaoFerramentaSoftwareDAO.recuperarPorAtividadeFerramentaSoftware(atividade.getId(), fs.getId());
		if(a==null) {
			a = new AlocacaoFerramentaSoftware(atividade, fs);
			alocacaoFerramentaSoftwareDAO.salvar(a);
		}
	}

	public void desalocarFerramentaSoftware(Atividade atividade, FerramentaSoftware fs) {
		AlocacaoFerramentaSoftware a = alocacaoFerramentaSoftwareDAO.recuperarPorAtividadeFerramentaSoftware(atividade.getId(), fs.getId());
		if(a!=null)
			alocacaoFerramentaSoftwareDAO.excluir(a);
	}

	public void alocarAutomaticamente(Projeto projeto) {
		for (AlocacaoFerramentaSoftware afs : alocacaoFerramentaSoftwareDAO.obterPossiveisAlocacoesAutomaticas(projeto.getId())) {
			alocacaoFerramentaSoftwareDAO.salvar(afs);
		}
		for (AlocacaoRH arh : alocacaoRHDAO.obterPossiveisAlocacoesAutomaticas(projeto.getId())) {
			alocacaoRHDAO.salvar(arh);
			
			//Notifica
			alocaODE.setModify(arh, TipoAlocacao.CRIAR, "");
		}
	}

	public boolean verificarPossibilidadeAlocarAutomaticamente(Projeto projeto) {
		if(alocacaoFerramentaSoftwareDAO.obterPossiveisAlocacoesAutomaticas(projeto.getId()).size()>0)
			return true;
		if(alocacaoRHDAO.obterPossiveisAlocacoesAutomaticas(projeto.getId()).size()>0)
			return true;
		return false;
	}

	


}
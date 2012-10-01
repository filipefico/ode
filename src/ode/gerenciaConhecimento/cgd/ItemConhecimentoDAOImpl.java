package ode.gerenciaConhecimento.cgd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgd.KRecursoHumanoDAO;
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.Valoracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemConhecimentoDAOImpl extends DAOBaseImpl<ItemConhecimento>
		implements ItemConhecimentoDAO {
	
	@Autowired
	KRecursoHumanoDAO kRecursoHumanoDAO;
	
	@Autowired
	RecursoHumanoDAO recursoHumanoDAO;
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoPendentesPorUsuarioAtual(){
		String estado = ItemConhecimento.ESTADO_AGUARDANDO_AVALIACAO; 
		
		List<ItemConhecimento> itens = getEntityManager().
				createQuery("from ItemConhecimento where estado = :estado order by dataCriacao desc").setParameter("estado", estado).getResultList();
		
		// Recupera todos que são gerentes de projeto
		Collection<RecursoHumano> recursos = this.recuperarGerentesConhecimento();

		// Se for gerente, recupera todos que estao aguardando avaliacao
		if (recursos.contains(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano())){
			return itens;
		}
		
		// Se nao for gerente, recuperar todos que estao aguardando avalicao e que o usuario atual logado deve avaliar
		RecursoHumano avaliador = NucleoContexto.recuperarUsuarioLogado().getRecursoHumano();
		List<ItemConhecimento> novosItens = new ArrayList<ItemConhecimento>(); 
		for (ItemConhecimento itemConhecimento : itens){
			if (itemConhecimento.getAvaliadores().contains(avaliador)){
				boolean avaliou = false;
				for (Avaliacao avaliacao : itemConhecimento.getAvaliacoes()){
					if (avaliacao.getAutor().equals(avaliador)){
						avaliou = true;
					}
				}
				if (!avaliou){
					novosItens.add(itemConhecimento);
				}
			}
		}
		
		return novosItens;
	}
	
	public Collection<ItemConhecimento> recuperarItensCriados(){ 
		
		List<ItemConhecimento> itens = getEntityManager().
				createQuery("from ItemConhecimento where autor = :autor order by dataCriacao desc").setParameter("autor", NucleoContexto.recuperarUsuarioLogado().getRecursoHumano()).getResultList();
		
		return itens;
	}
	

	public Collection<ItemConhecimento> recuperarItensConhecimentoValorados(){
		
		List<Valoracao> itens = getEntityManager().
				createQuery("from Valoracao where autor = :autor order by dataValoracao desc").setParameter("autor", NucleoContexto.recuperarUsuarioLogado().getRecursoHumano()).getResultList();
		
		Set<ItemConhecimento> novosItens = new HashSet<ItemConhecimento>(); 
		for (Valoracao valoracao : itens){
			novosItens.add(valoracao.getItemConhecimentoAvaliado());
		}
		
		return novosItens;
	}
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoAvaliados(){
		
		List<Avaliacao> itens = getEntityManager().
				createQuery("from Avaliacao where autor = :autor order by dataAvaliacao desc").setParameter("autor", NucleoContexto.recuperarUsuarioLogado().getRecursoHumano()).getResultList();
		
		Set<ItemConhecimento> novosItens = new HashSet<ItemConhecimento>(); 
		for (Avaliacao avaliacao : itens){
			novosItens.add(avaliacao.getItemConhecimentoAvaliado());
		}
		
		return novosItens;
	}
	
	// Recupera todos os recursos humanos que tem o cargo gerente de conhecimento
	public Collection<RecursoHumano> recuperarGerentesConhecimento(){
		
		KRecursoHumano kRecursoHumano = this.kRecursoHumanoDAO.recuperarPorParteNome("gerente conhecimento");
		
		return this.recursoHumanoDAO.recuperarPorCargo(kRecursoHumano);
	}
	
	public Collection<ItemConhecimento> recuperarItensCriadosPorUsuarioAtual(RecursoHumano rh){
		List<ItemConhecimento> list = getEntityManager().createQuery("from ItemConhecimento where autor = :recursoHumanoSelecionado").setParameter("recursoHumanoSelecionado",rh).getResultList();
		
		return list;
	}
	
	public Collection<ItemConhecimento> recuperarItensAvaliadosPorUsuarioAtual(RecursoHumano rh){
		
		List<Avaliacao> list = getEntityManager().createQuery("from Avaliacao where autor = :recursoHumanoSelecionado").setParameter("recursoHumanoSelecionado",rh).getResultList();
		
		Set<ItemConhecimento> novosItens = new HashSet<ItemConhecimento>();
		for(Avaliacao avaliacao : list){
			novosItens.add(avaliacao.getItemConhecimentoAvaliado());
		}
		
		return novosItens;
		
	}
	
	public Collection<ItemConhecimento> recuperarItensValoradosPorUsuarioAtual(RecursoHumano rh){
		
		List<Valoracao> list = getEntityManager().createQuery("from Valoracao where autor = :recursoHumanoSelecionado").setParameter("recursoHumanoSelecionado",rh).getResultList();
		
		Set<ItemConhecimento> novosItens = new HashSet<ItemConhecimento>();
		for(Valoracao valoracao : list){
			novosItens.add(valoracao.getItemConhecimentoAvaliado());
		}
		
		return novosItens;
		
	}
}

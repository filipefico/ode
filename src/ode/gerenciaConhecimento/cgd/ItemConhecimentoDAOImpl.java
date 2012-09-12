package ode.gerenciaConhecimento.cgd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;

import org.springframework.stereotype.Repository;

@Repository
public class ItemConhecimentoDAOImpl extends DAOBaseImpl<ItemConhecimento>
		implements ItemConhecimentoDAO {
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoPendentesPorUsuarioAtual(){
		String estado = ItemConhecimento.ESTADO_AGUARDANDO_AVALIACAO; 
		
		List<ItemConhecimento> itens = getEntityManager().
				createQuery("from ItemConhecimento where estado = :estado order by dataCriacao").setParameter("estado", estado).getResultList();
		
		RecursoHumano avaliador = NucleoContexto.recuperarUsuarioLogado().getRecursoHumano();
		List<ItemConhecimento> novosItens = new ArrayList<ItemConhecimento>(); 
		for (ItemConhecimento itemConhecimento : itens){
			if (itemConhecimento.getAvaliadores().contains(avaliador)){
				boolean autorAvaliou = false;
				for (Avaliacao avaliacao : itemConhecimento.getAvaliacoes()){
					if (avaliacao.getAutor().equals(avaliador)){
						autorAvaliou = true;
					}
				}
				if (!autorAvaliou){
					novosItens.add(itemConhecimento);
				}
			}
		}
		
		return novosItens;
	}

}

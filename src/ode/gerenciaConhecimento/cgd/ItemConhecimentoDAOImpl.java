package ode.gerenciaConhecimento.cgd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.Valoracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemConhecimentoDAOImpl extends DAOBaseImpl<ItemConhecimento>
		implements ItemConhecimentoDAO {
	
	@Autowired
	AtuacaoRHDAO atuacaoRHDAO;
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoPendentesPorUsuarioAtual(){
		String estado = ItemConhecimento.ESTADO_AGUARDANDO_AVALIACAO; 
		
		List<ItemConhecimento> itens = getEntityManager().
				createQuery("from ItemConhecimento where estado = :estado order by dataCriacao desc").setParameter("estado", estado).getResultList();
		
		// Recupera todos que são gerentes de projeto
		Collection<RecursoHumano> recursos = atuacaoRHDAO.recuperarAptosPorPapel(new Long(31));

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

}

package ode._controleRecursoHumano.cgd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cdp.Valoracao;

import org.springframework.stereotype.Repository;

@Repository
public class RecursoHumanoDAOImpl extends
DAOBaseImpl<RecursoHumano> implements RecursoHumanoDAO {

	public Collection<RecursoHumano> recuperarRecursosHumanosAtivos() {
		return entityManager.createQuery("from RecursoHumano e where e.ativo = true").getResultList();
	}

	public Collection<RecursoHumano> recuperarPorTemasItemCriadoAvaliadoValorado(List<Tema> temas, ItemConhecimento itemCriado, ItemConhecimento itemAvaliado, ItemConhecimento itemValorado) {

		List<RecursoHumano> recursosBanco = entityManager.createQuery("from RecursoHumano").getResultList();
		
		// Temas
		List<RecursoHumano> recursos = new ArrayList<RecursoHumano>();
		for (RecursoHumano recursoHumano : recursosBanco){
			boolean contemTodos = true;
			for (Tema tema : temas){
				if (!recursoHumano.getTemasInteresse().contains(tema)){
					contemTodos = false;
				}
			}
			if (contemTodos)
				recursos.add(recursoHumano);
		}

		// Itens Criados
		List<ItemConhecimento> itens = getEntityManager().
		createQuery("from ItemConhecimento where id = :id")
		.setParameter("id", itemCriado == null ? null : itemCriado.getId())
		.getResultList();
		List<RecursoHumano> recursosItemCriado = new ArrayList<RecursoHumano>();
		for (ItemConhecimento itemConhecimento : itens) {
			recursosItemCriado.add(itemConhecimento.getAutor());
		}
		if (recursosItemCriado.size()>0)
			recursos.retainAll(recursosItemCriado);

		// Itens Avaliados
		List<Avaliacao> avaliacoes = getEntityManager().
		createQuery("from Avaliacao where itemConhecimentoAvaliado = :itemConhecimentoAvaliado")
		.setParameter("itemConhecimentoAvaliado", itemAvaliado)
		.getResultList();
		List<RecursoHumano> recursosItemAvaliado = new ArrayList<RecursoHumano>();
		for (Avaliacao avaliacao : avaliacoes) {
			recursosItemAvaliado.add(avaliacao.getAutor());
		}
		if (recursosItemAvaliado.size()>0)
			recursos.retainAll(recursosItemAvaliado);

		// Itens Valorados
		List<Valoracao> valoracoes = getEntityManager().
		createQuery("from Valoracao where itemConhecimentoAvaliado = :itemConhecimentoValorado")
		.setParameter("itemConhecimentoValorado", itemValorado)
		.getResultList();
		List<RecursoHumano> recursosItemValorados = new ArrayList<RecursoHumano>();
		for (Valoracao valoracao : valoracoes) {
			recursosItemValorados.add(valoracao.getAutor());
		}
		if (recursosItemValorados.size()>0)
			recursos.retainAll(recursosItemValorados);

		return recursos;
	}

}

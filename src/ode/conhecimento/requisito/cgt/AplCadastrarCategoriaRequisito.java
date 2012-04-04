package ode.conhecimento.requisito.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cdp.TipoRequisito;
import ode.conhecimento.requisito.cgd.CategoriaRequisitoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarCategoriaRequisito extends AplCRUD<CategoriaRequisito> {

	@Autowired
	private CategoriaRequisitoDAO categoriaRequisitoDAO;
	
	public Collection<CategoriaRequisito> recuperarTodos() {
		return categoriaRequisitoDAO.recuperarTodos();
	}

	@Override
	public DAOBase<CategoriaRequisito> getNucleoDaoBase() {
		return categoriaRequisitoDAO;
	}
	
	public Collection<CategoriaRequisito> obterCategoriasPorTipoRequisito (TipoRequisito tipo){
		return categoriaRequisitoDAO.obterCategoriaPorTipo(tipo);
	}
}

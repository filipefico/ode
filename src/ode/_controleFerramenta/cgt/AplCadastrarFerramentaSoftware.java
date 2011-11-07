package ode._controleFerramenta.cgt;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleFerramenta.cgd.FerramentaSoftwareDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarFerramentaSoftware extends	AplCRUD<FerramentaSoftware> {
	@Autowired
	private FerramentaSoftwareDAO ferramentaSoftwareDAO;
	
	@Override
	public DAOBase<FerramentaSoftware> getNucleoDaoBase() {
		return ferramentaSoftwareDAO;
	}
	
	@Override
	public Collection<FerramentaSoftware> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodosComOrdenacao("nome");
	}
}

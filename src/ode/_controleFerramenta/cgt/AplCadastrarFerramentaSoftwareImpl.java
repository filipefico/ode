package ode._controleFerramenta.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleFerramenta.cgd.FerramentaSoftwareDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarFerramentaSoftwareImpl extends	AplBaseImpl<FerramentaSoftware> implements AplCadastrarFerramentaSoftware {
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

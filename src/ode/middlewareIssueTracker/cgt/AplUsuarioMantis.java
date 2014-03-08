package ode.middlewareIssueTracker.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;
import ode.middlewareIssueTracker.cgd.UsuarioMantisDAO;

@Service
public class AplUsuarioMantis extends AplCRUD<UsuarioMantis> {

	@Autowired
	UsuarioMantisDAO usuarioMantisDAO;
	
	@Override
	public DAOBase<UsuarioMantis> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return usuarioMantisDAO;
	}

	
	public UsuarioMantis recuperarUsuarioMantisAtual(){
		UsuarioMantis usuarioMantis = usuarioMantisDAO.recuperarUsuarioMantisDoUsuarioAtual();
		return usuarioMantis;
	}
	
}

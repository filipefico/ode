package ode.middlewareIssueTracker.cgd;


import java.util.List;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.controleUsuario.cdp.Usuario;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;

@Repository
public class UsuarioMantisDAOImpl extends DAOBaseImpl<UsuarioMantis> implements UsuarioMantisDAO {

	@Override
	public UsuarioMantis recuperarUsuarioMantisDoUsuarioAtual() {
		// TODO Auto-generated method stub
		
		Usuario usuario = NucleoContexto.recuperarUsuarioLogado();
		List<UsuarioMantis> usuarioMantis = getEntityManager().createQuery("from UsuarioMantis where usuario = :usuario").setParameter("usuario", usuario).getResultList();
		
		if(usuarioMantis.size() > 0){
			return usuarioMantis.get(0);
		}
		
		return null;
	}

}

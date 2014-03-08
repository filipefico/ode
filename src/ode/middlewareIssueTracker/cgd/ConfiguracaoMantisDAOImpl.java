package ode.middlewareIssueTracker.cgd;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.controleUsuario.cdp.Usuario;
import ode.middlewareIssueTracker.cdp.ConfiguracaoMantis;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;

@Repository
public class ConfiguracaoMantisDAOImpl extends DAOBaseImpl<ConfiguracaoMantis> implements ConfiguracaoMantisDAO {

	@Autowired
	UsuarioMantisDAO usuarioMantisDAO;
	
	@Override
	public ConfiguracaoMantis recuperarConfiguracaoMantisPadrao() {
		// TODO Auto-generated method stub
	
		
		List<ConfiguracaoMantis> configuracaoMantis = getEntityManager().createQuery("from ConfiguracaoMantis").getResultList();
		
		if(configuracaoMantis.size() > 0){
			
			ConfiguracaoMantis element = configuracaoMantis.get(0);
			
			UsuarioMantis usuarioMantis = usuarioMantisDAO.recuperarPorId(element.getUsuarioMantisPadrao().getId());
			
			element.setUsuarioMantisPadrao(usuarioMantis);
			
			return element;
		}
		
		
		return null;
	}



}

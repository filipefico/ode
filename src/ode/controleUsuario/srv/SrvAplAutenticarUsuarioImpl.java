package ode.controleUsuario.srv;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoUtil;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgd.UsuarioDAO;
import ode.controleUsuario.cgt.AplAutenticarUsuario;

@WebService(endpointInterface = "ode.controleUsuario.srv.SrvAplAutenticarUsuario")  
public class SrvAplAutenticarUsuarioImpl implements SrvAplAutenticarUsuario {

	@Autowired
	private UsuarioDAO dao;
	
	
	@Override
	public Long efetuarLogin(String nomeUsuario, String senha,
			boolean rememberme) throws NucleoRegraNegocioExcecao {
		
		try{
			Usuario user = dao.recuperarPorNomeUsuario(nomeUsuario);
			System.out.println(user.getSenha());

			senha = NucleoUtil.encrypt(senha);
			System.out.println(senha);
			if(user.getSenha().equals(senha)){
				return user.getId();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return (long) 0;
		
	}

}

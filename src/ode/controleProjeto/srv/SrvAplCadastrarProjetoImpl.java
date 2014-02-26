package ode.controleProjeto.srv;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Collection;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;

import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;

@WebService(endpointInterface = "ode.controleProjeto.srv.SrvAplCadastrarProjeto")  
public class SrvAplCadastrarProjetoImpl implements SrvAplCadastrarProjeto {

	
	@Autowired
	private AplCadastrarProjeto apl;
	
	@Override
	public Collection<Projeto> recuperarTodos() {
		return apl.recuperarTodos();
	}

	@Override
	public Projeto recuperarPorId(long id) {
		return apl.recuperarPorId(id);
	}
	
	@Override
	public int recuperarQuantidadeTotal(){
		return apl.recuperarQuantidadeTotal();
	}
	
	@Override
	public void excluir(Projeto p){
		try {
			apl.excluir(p);
		} catch (NucleoRegraNegocioExcecao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int salvar(Projeto p){
		try {
				apl.salvar(p);
				return 1;
		} catch (NucleoRegraNegocioExcecao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
  
   
}
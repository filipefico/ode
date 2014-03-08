package ode.middlewareIssueTracker.ciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.middlewareIssueTracker.cdp.ConfiguracaoMantis;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;
import ode.middlewareIssueTracker.cgt.AplConfiguracaoMantis;
import ode.middlewareIssueTracker.cgt.AplUsuarioMantis;

@Controller
public class CtrlConfiguracaoMantis extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	private JanConfiguracaoMantis jcm;
	
	@Autowired
	AplConfiguracaoMantis aplConfiguracaoMantis;
	
	@Autowired
	AplUsuarioMantis aplUsuarioMantis;

	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		jcm = new JanConfiguracaoMantis(this);
		jcm.mostrar();

	}
	
	public ConfiguracaoMantis recuperarConfiguracaoMantisPadrao(){
		ConfiguracaoMantis configuracaoMantis = aplConfiguracaoMantis.recuperarConfiguracaoMantisPadrao();
		return configuracaoMantis;
	}
	
	public void salvarConfiguracaoMantisPadrao(ConfiguracaoMantis configuracaoMantis){
		
		UsuarioMantis usuarioMantisPadrao = configuracaoMantis.getUsuarioMantisPadrao();
		
		
		try {
			aplUsuarioMantis.salvar(usuarioMantisPadrao);			
			
			configuracaoMantis.setUsuarioMantisPadrao(usuarioMantisPadrao);
			
			aplConfiguracaoMantis.salvar(configuracaoMantis);
			
			
		} catch (NucleoRegraNegocioExcecao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

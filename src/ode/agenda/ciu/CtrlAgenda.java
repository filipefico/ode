package ode.agenda.ciu;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.alocacaoRecurso.cgd.AlocacaoFerramentaSoftwareDAO;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlAgenda extends CtrlBase {
	
	private static final long serialVersionUID = 1L;
	
	private JanAgenda jan;
	
	@Autowired
	public AlocacaoRHDAO alocacaoRHDAO;
	
	@Autowired
	public AlocacaoFerramentaSoftwareDAO alocacaoFerramentaSoftwareDAO;
	
	@Override
	public void iniciar() {
		jan = new JanAgenda(this);
		jan.mostrar();
	}

	public RecursoHumano getRecursoHumano() {
		return NucleoContexto.recuperarUsuarioLogado().getRecursoHumano();
	}

}

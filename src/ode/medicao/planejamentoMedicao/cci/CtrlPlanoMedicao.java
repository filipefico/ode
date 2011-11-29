package ode.medicao.planejamentoMedicao.cci;

import java.util.Collection;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplElaborarPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cih.PainelPrincipalPlanoMedicaoOganizacao;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class CtrlPlanoMedicao extends CtrlBase{
public static final String NOME = "CtrlPlanoMedicaoOrganizacao";
	
	@Autowired
	AplElaborarPlanoMedicaoOrganizacao aplPlanoMedicaoOrganizacao;
	
	@Autowired
	AplCadastrarKRecursoHumano aplKRecursoHumano;
	
	@Autowired
	AplCadastrarKObjetivoEstrategico aplKObjetivoEstrategico;
	
	protected JanelaSimples janelaPrincipal;
	protected PlanoMedicaoOrganizacao objetoAtual;
	protected PainelPrincipalPlanoMedicaoOganizacao painel;
	
	public AplCadastrarKRecursoHumano getAplRecursoHumano(){
		return aplKRecursoHumano;
	}
	
	@Override
	public void iniciar() {
		configurarPainelPrincipal();
		abrePainelPrincipal();
	}
	
	protected void configurarPainelPrincipal(){
		painel = getPainelPrincipal();
	}

	protected void abrePainelPrincipal() {
		janelaPrincipal = factoryJanelaSimples();
		painel.setParent(janelaPrincipal);
		definirTituloJanelaPrincipal();
		painel.montar();
		janelaPrincipal.mostrar();
	}

	protected void definirTituloJanelaPrincipal(){
		definirObjetoAtual();
		if(objetoAtual.isPersistente()){
			janelaPrincipal.setTitle(objetoAtual.toString());
		}else{
			janelaPrincipal.setTitle("Novo Plano de Medição da Organização");
		}
	}
	
	protected PainelPrincipalPlanoMedicaoOganizacao getPainelPrincipal() {
		PainelPrincipalPlanoMedicaoOganizacao pppmo = new PainelPrincipalPlanoMedicaoOganizacao();
		pppmo.setControlador(this);
		return pppmo;
	}
	
	public PlanoMedicaoOrganizacao getPlanoMedicaoOrganizacao(){
		return new PlanoMedicaoOrganizacao();
	}
	
	protected void definirObjetoAtual(){
		objetoAtual = new PlanoMedicaoOrganizacao(); 
	}

	public Collection<KObjetivoEstrategico> getObjetivosEstrategicos() {
		return aplKObjetivoEstrategico.recuperarTodos();
	}
	
	public abstract PlanoMedicao novoPlanoMedicao();
	
	
}

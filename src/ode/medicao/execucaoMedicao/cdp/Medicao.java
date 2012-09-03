package ode.medicao.execucaoMedicao.cdp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;

@Entity
public class Medicao extends ObjetoPersistente{
	
	private Date data;
	private ValorMedido valorMedido;
	private Projeto projeto;
	private KAtividade atividade;
	private ContextMedicao contexto;
	private DefinicaoOperacionalMedida definicaoOperacional;
	private KMedida medida;
	private RecursoHumano executor;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public ValorMedido getValorMedido() {
		return valorMedido;
	}
	public void setValorMedido(ValorMedido valorMedido) {
		this.valorMedido = valorMedido;
	}
	
	@OneToOne
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	@OneToOne
	public KAtividade getAtividade() {
		return atividade;
	}
	public void setAtividade(KAtividade atividade) {
		this.atividade = atividade;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public ContextMedicao getContexto() {
		return contexto;
	}
	public void setContexto(ContextMedicao contexto) {
		this.contexto = contexto;
	}
	
	@OneToOne
	public DefinicaoOperacionalMedida getDefinicaoOperacional() {
		return definicaoOperacional;
	}
	public void setDefinicaoOperacional(DefinicaoOperacionalMedida definicaoOperacional) {
		this.definicaoOperacional = definicaoOperacional;
	}
	
	@OneToOne
	public KMedida getMedida() {
		return medida;
	}
	public void setMedida(KMedida medida) {
		this.medida = medida;
	}
	
	@OneToOne
	public RecursoHumano getExecutor() {
		return executor;
	}
	public void setExecutor(RecursoHumano executor) {
		this.executor = executor;
	}
	
	public String toString(){
		return "("+getData()+") "+getMedida()+" - "+getValorMedido();
	}
}

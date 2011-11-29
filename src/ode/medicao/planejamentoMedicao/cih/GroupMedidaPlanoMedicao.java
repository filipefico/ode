package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;

import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;

public class GroupMedidaPlanoMedicao extends Groupbox {
	private Caption cap;
	private Collection<Component> lista = new ArrayList<Component>();
	private NucleoCombobox<KDefinicaoOperacionalMedida> especial;

	public GroupMedidaPlanoMedicao(String str) {
		cap = new Caption(str);
		this.appendChild(cap);
	}

	public void adicionaFilhoEspecifico(Component comp) {
		this.lista.add(comp);
		this.appendChild(comp);
	}

	public void adicionaFilhoDefinicao(Component comp) {
		especial = (NucleoCombobox<KDefinicaoOperacionalMedida>) comp;
		adicionaFilhoEspecifico(comp);
	}

	public NucleoCombobox<KDefinicaoOperacionalMedida> getDefinicao() {
		return especial;
	}

	public Collection<Component> getFilhosEspecificos() {
		return this.lista;
	}

	public void insereCaption(String cap) {
		this.cap.setLabel(cap);
	}
}

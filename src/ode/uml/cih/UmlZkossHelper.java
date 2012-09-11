package ode.uml.cih;

import java.util.Collection;

import ode._infraestruturaBase.util.NucleoContexto;
import ode.uml.cci.CtrlCRUDPacote;
import ode.uml.cdp.Pacote;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

public class UmlZkossHelper {

	public static Combobox comboboxTipoRequisito() {
		return new Combobox();
	}

	public static Combobox comboboxPacote() {
		Combobox comboPacote = new Combobox();
		
		CtrlCRUDPacote ctrlPacote = SpringUtil.getApplicationContext().getBean(CtrlCRUDPacote.class);

		Collection<Pacote> pacotes = ctrlPacote.obterPorProjeto(NucleoContexto
				.recuperarProjeto());

		for (Pacote pacote : pacotes) {
			Comboitem item = new Comboitem();

			item.setLabel(pacote.getNome());
			item.setValue(pacote);

			item.setParent(comboPacote);
		}
		if(pacotes.size() > 0)
			comboPacote.setSelectedIndex(0);
		
		return comboPacote;
	}
	
}

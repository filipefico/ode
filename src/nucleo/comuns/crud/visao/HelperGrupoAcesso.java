package nucleo.comuns.crud.visao;

import org.zkoss.zk.ui.Component;

public class HelperGrupoAcesso {

	public static void adicionarComponente(Component pai, Component filho, boolean temAcesso){
		
		if (temAcesso)
			filho.setParent(pai);
	}
}

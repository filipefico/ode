package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;

@Component
public class ListagemKDefinicaoOperacionalMedida extends ListagemSimples<KDefinicaoOperacionalMedida>{
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> cabecalhos = new ArrayList<NucleoListHeader>();
		
		cabecalhos.add(new NucleoListHeader("Nome", "nome", "40%"));
		cabecalhos.add(new NucleoListHeader("Descrição", "descricao", "60%"));
		
		return cabecalhos;
	}

	@Override
	protected String[] recuperarDadosObjeto(KDefinicaoOperacionalMedida objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
	
}

package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.NaturezaMedida;
import ode.conhecimentoMedicao.cgd.KDefinicaoOperacionalMedidaDAO;
import ode.conhecimentoMedicao.cgd.KMedidaDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKMedida extends AplCRUD<KMedida>{

	@Autowired
	KMedidaDAO dao;
	
	@Autowired
	AplCadastrarKDefinicaoOperacionalMedida aplKdef;
	
	@Override
	public DAOBase<KMedida> getNucleoDaoBase() {
		return dao;
	}
	
	@Override
	protected void antesSalvar(KMedida objeto) throws NucleoRegraNegocioExcecao {
		if(objeto.getEscala()==null){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_EMPTY_ESCALA_ERRO));
		}
		if(objeto.getUnidadeMedida()==null){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_EMPTY_UNIDADE_ERRO));
		}
		if(objeto.getPropriedadeMedida()==null){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_EMPTY_PROP_MED_ERRO));
		}
		if(objeto.getNecessidadesInformacao().isEmpty()){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_EMPTY_NECES_INFO_ERRO));
		}
		if((objeto.getNaturezaMedida()==NaturezaMedida.DERIVADA)&&(objeto.getDerivadaDe().isEmpty())){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_EMPTY_DERIVADA_ERRO));
		}
		
		for(KDefinicaoOperacionalMedida kdef:objeto.getDefinicoesMedida()){
			aplKdef.salvar(kdef);
		}
	}

	@Override
	protected void depoisExcluir(KMedida objeto)
			throws NucleoRegraNegocioExcecao {
		for(KDefinicaoOperacionalMedida def:objeto.getDefinicoesMedida()){
			aplKdef.excluir(def);
		}
	}
	
}

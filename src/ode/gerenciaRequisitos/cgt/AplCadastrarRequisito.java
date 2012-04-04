package ode.gerenciaRequisitos.cgt;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.gerenciaRequisitos.cgd.GeradorIdentificadorDAO;
import ode.gerenciaRequisitos.cgd.RequisitoDAO;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarRequisito extends AplCRUD<Requisito> {
	
	@Autowired
	private RequisitoDAO requisitoDAO;
	
	@Autowired
	private GeradorIdentificadorDAO geradorIdDao;

	public Collection<Requisito> recuperarTodos() {
		return requisitoDAO.recuperarTodos();
	}

	public DAOBase<Requisito> getNucleoDaoBase() {
		return requisitoDAO ;
	}
	
	public Collection<Requisito> obterPorProjeto (Projeto p){
		return requisitoDAO.obterPorProjeto(p);
	}

	@Override
	protected void antesIncluirNovo(Requisito objeto) throws NucleoRegraNegocioExcecao {
		objeto.setDataCriacao(new Date());
		if (objeto.getTipoRequisito().getNome().equals("Funcional")){
			objeto.setIdentificador(geradorIdDao.getIdentificadorFuncional(objeto.getProjeto()));
			return;
		}else{
			if (objeto.getTipoRequisito().getNome().equals("Não-Funcional")){
				objeto.setIdentificador(geradorIdDao.getIdentificadorNaoFuncional(objeto.getProjeto()));
			}else{
				objeto.setIdentificador(geradorIdDao.getIdentificadorRegraDeNegocio(objeto.getProjeto()));
			}
		}
	}
	
	
}

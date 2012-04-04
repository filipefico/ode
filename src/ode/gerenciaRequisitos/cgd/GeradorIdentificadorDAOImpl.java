package ode.gerenciaRequisitos.cgd;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cdp.GeradorIdentificador;

import org.springframework.stereotype.Repository;

@Repository(value="geradorIdentificadorDao")
public class GeradorIdentificadorDAOImpl extends DAOBaseImpl<GeradorIdentificador> implements GeradorIdentificadorDAO{
	
	public boolean existeGerador (Projeto projeto){
		String query = "select g from GeradorIdentificador g ";
		query += "left join g.projeto p ";
		query += "where p.id =  " + projeto.getId();
		if (getEntityManager().createQuery(query).getResultList().size() == 0){
			return false;
		}
		return true;
	}
	
	public String getIdentificadorFuncional (Projeto projeto){
		long idNum;
		if (existeGerador(projeto)){
			String query = "select g from GeradorIdentificador g ";
			query += "left join g.projeto p ";
			query += "where p.id =  " + projeto.getId();
			GeradorIdentificador gerador = (GeradorIdentificador) getEntityManager().createQuery(query).getSingleResult();
			idNum = gerador.getIdRf();
			gerador.incIdRf();
		}else{
			GeradorIdentificador gerador = new GeradorIdentificador(projeto);
			idNum = gerador.getIdRf();
			gerador.incIdRf();
			this.salvar(gerador);
		}
		String id = "RF";
		if (idNum < 10){
			id += "00" + idNum;
			return id;
		}
		if (idNum < 100){
			id += "0" + idNum;
			return id;
		}
		return id;
	}
	
	public String getIdentificadorNaoFuncional (Projeto projeto){
		long idNum;
		if (existeGerador(projeto)){
			String query = "select g from GeradorIdentificador g ";
			query += "left join g.projeto p ";
			query += "where p.id =  " + projeto.getId();
			GeradorIdentificador gerador = (GeradorIdentificador) getEntityManager().createQuery(query).getSingleResult();
			idNum = gerador.getIdRnf();
			gerador.incIdRnf();
		}else{
			GeradorIdentificador gerador = new GeradorIdentificador(projeto);
			idNum = gerador.getIdRnf();
			gerador.incIdRnf();
			this.salvar(gerador);
		}
		String id = "RNF";
		if (idNum < 10){
			id += "00" + idNum;
			return id;
		}
		if (idNum < 100){
			id += "0" + idNum;
			return id;
		}
		return id;
	}
	
	public String getIdentificadorRegraDeNegocio (Projeto projeto){
		long idNum;
		if (existeGerador(projeto)){
			String query = "select g from GeradorIdentificador g ";
			query += "left join g.projeto p ";
			query += "where p.id =  " + projeto.getId();
			GeradorIdentificador gerador = (GeradorIdentificador) getEntityManager().createQuery(query).getSingleResult();
			idNum = gerador.getIdRn();
			gerador.incIdRn();
		}else{
			GeradorIdentificador gerador = new GeradorIdentificador(projeto);
			idNum = gerador.getIdRn();
			gerador.incIdRn();
			this.salvar(gerador);
		}
		String id = "RN";
		if (idNum < 10){
			id += "00" + idNum;
			return id;
		}
		if (idNum < 100){
			id += "0" + idNum;
			return id;
		}
		return id;
	}

}

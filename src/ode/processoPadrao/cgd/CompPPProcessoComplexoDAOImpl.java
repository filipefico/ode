package ode.processoPadrao.cgd;

import java.util.List;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;

import org.springframework.stereotype.Repository;

@Repository
public class CompPPProcessoComplexoDAOImpl extends DAOBaseImpl<CompPPProcessoComplexo> implements CompPPProcessoComplexoDAO{
		
	/*  public KArtefato recuperarPorId(Long id) {
		    DetachedCriteria detaCriteria = DetachedCriteria.forClass(getClasseDominio());
		    detaCriteria.setFetchMode("subArtefatos", FetchMode.JOIN)
		        .setFetchMode("dependencias", FetchMode.JOIN)
		        .setFetchMode("tipo", FetchMode.JOIN)
		        .add( Restrictions.idEq(id) );

		    //List resultado = getHibernateTemplate().findByCriteria(detaCriteria);
		    List resultado = getHibernateTemplate().findByCriteria(detaCriteria, 0, 1);
			return (KArtefato)resultado.get(0);
		}*/
	
	  public CompPPProcessoComplexo obterPorNome(String parNome){
	        List locProc = getEntityManager().createQuery("from "+ CompPPProcessoComplexo.class.getName()+ " as proc where proc.nome = '" + parNome + "'").getResultList();
	        if (locProc.isEmpty())
	            return null;
	        return (CompPPProcessoComplexo)locProc.get(0);       
	  }
	  
	   /* public void excluir(CompPPProcessoComplexo parProcessoPadraoGeral){
	       *try{
	            
	            DAOFactory.getDAOFactory().beginTransaction();
	            
	            //exclui os subProcessos, desde que estes não façam parte de outro processo padrão
	            CompPPProcessoSimplesDAO daoPPE = (CompPPProcessoSimplesDAO) DAOFactory.getDefaultDAO(CompPPProcessoSimples.class);
	            Set locSubProcessos = new HashSet(parProcessoPadraoGeral.getProcessosSimples());
	            Iterator it = locSubProcessos.iterator();
	            while (it.hasNext()){
	                CompPPProcessoSimples locProc = (CompPPProcessoSimples)it.next();
	                List locProcessosGerais = new ArrayList(locProc.getProcessosComplexos());
	                locProcessosGerais.remove(parProcessoPadraoGeral);
	                //se o subProcesso pertence somente a este processo padrão, exclui normalmente
	                if (locProcessosGerais.size() == 0){
	                    daoPPE.excluir(locProc);
	                }
	                else{//senão, quebra o relacionamento entre os dois
	                    Set locPPGs = new HashSet(locProc.getProcessosComplexos());
	                    locPPGs.remove(parProcessoPadraoGeral);
	                    locProc.setProcessosComplexos(locPPGs);
	                    daoPPE.salvar(locProc);
	                }
	            }
	            
	            super.excluir(parProcessoPadraoGeral);
	            
	            DAOFactory.getDAOFactory().commit();
	            
	        }catch (ExcecaoConcorrencia e){
	            JOptionPane.showMessageDialog(null, e.getMessage(), "ODE", JOptionPane.ERROR_MESSAGE);
	            DAOFactory.getDAOFactory().rollback();
	            System.out.println("Rolled Back!-excluirProcessoPadrao");
	        }catch (ExcecaoPersistencia e) {
	            JOptionPane.showMessageDialog(null, e.getMessage(), "ODE", JOptionPane.ERROR_MESSAGE);
	            DAOFactory.getDAOFactory().rollback();
	            System.out.println("Rolled Back!-excluirProcessoPadrao");
	        } catch (Throwable e) {
	            System.out.println("Rolled Back!-excluirProcessoPadrao");
	            JOptionPane.showMessageDialog(null, "Erro Inesperado.", "ODE", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	            DAOFactory.getDAOFactory().rollback();
	        }*/
	    
	    
	  /*  public List obterTodos(){
	        return super.obterTodos(CompPPProcessoComplexo.class);
	    }
	    */
	  
	    /*
	    public List obterProcessosDefinidos(){
	        return getSession().createQuery("from "+ CompPPProcessoComplexo.nomeClass + " as ppg where ppg.ehDefinido = true").list();
	    }
	    
	    
	    public List obterProcessosEspecializados(){
	        return getSession().createQuery("from "+ CompPPProcessoComplexo.nomeClass + " as ppg where ppg.processoOrigem is not null").list();
	    }
	    
	    public List obterProcessosNaoEspecializados(){
	        return getSession().createQuery("from "+ CompPPProcessoComplexo.nomeClass + " as ppg where ppg.processoOrigem is null").list();
	    }
	    
	    public List obterProcessosEspecializadosFinalizadosPorProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem){
	        return getSession().createQuery("from CompPPProcessoComplexo as ppg where ppg.processoOrigem = '" + parProcessoOrigem.getId() + "' and ppg.ehDefinido = true").list();
	    }

	    public List obterKProcessosSimplesObrigatorios(CompPPProcessoComplexo parProcessoOrigem){
	        return getSession().createQuery(" from CompPPProcessoComplexo as c, " +
	                "InterfaceCompPPProcessoComplexo as i, " +
	                "ObrigatoriedadeSubprocesso as ob, " +
	                "KProcesso as kp" +
	                " where " +
	                "c.id = '" + parProcessoOrigem.getId() + "' " +
	                "and i = c.interfaceCompPP " +
	                "and ob.interfaceCompPPProcessoComplexo = i and " +
	                "kp = ob.kProcesso and " +
	                "ob.ehObrigatorio = true").list();
	    }

	    public CompPPProcessoComplexo obterPorId(CompPP parCompPP) {
	        List locInterface = getSession().createQuery("from CompPPProcessoComplexo as comp where comp.id = '" + parCompPP.getId() + "'").list();

	        if (locInterface.isEmpty()) {
	            return null;
	        }

	        return (CompPPProcessoComplexo) locInterface.get(0);
	    }
	    */
	    
	    
	    
}

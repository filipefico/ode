package ode.processoPadrao.Cgd;

import ode.nucleo.crud.cgd.DAOBaseHibernate;
import ode.processoPadrao.Cdp.CompPPProcessoSimples;

import org.springframework.stereotype.Repository;

@Repository
public class CompPPProcessoSimplesDAOHibernate extends
DAOBaseHibernate<CompPPProcessoSimples> implements CompPPProcessoSimplesDAO{
    
  /*  public List obterTodos(){
        return super.obterTodos(CompPPProcessoSimples.class);
    }
    
    public List obterProcessosEngenharia(CompPPProcessoComplexo parProc){
        return getSession().createQuery("from "+ CompPPProcessoSimples.nomeClass + " as ppe where '" + parProc.getId() + "' in elements (ppe.processosComplexos) and ppe.KProcesso.id in (Select kproc.id from KProcesso as kproc where kproc.ehEngenharia = true)").list();
    }
    
    public List obterProcessosNaoEngenhaira(CompPPProcessoComplexo parProc){
        return getSession().createQuery("from "+ CompPPProcessoSimples.nomeClass + " as ppe where '" + parProc.getId() + "' in elements (ppe.processosComplexos) and ppe.KProcesso.id in (Select kproc.id from KProcesso as kproc where kproc.ehEngenharia = false)").list();
    }
    
    public List obterKProcessosDefinidos(CompPPProcessoComplexo parPPG){
        return getSession().createQuery("Select ppe.KProcesso from "+ CompPPProcessoSimples.nomeClass + " as ppe where '" + parPPG.getId() + "' in elements (ppe.processosComplexos)").list();
    }

    
    public CompPPProcessoSimples obterPorNome(String parNome){

        List<CompPPProcessoSimples> locProc = getSession().createQuery("from "+ CompPPProcessoSimples.nomeClass + " as proc where proc.nome = '" + parNome + "'").list();
        if (locProc.isEmpty())
            return null;
        return (CompPPProcessoSimples)locProc.get(0);
    }

     public CompPPProcessoSimples obterPorId(CompPP parCompPP) {
        List locInterface = getSession().createQuery("from CompPPProcessoSimples as comp where comp.id = '" + parCompPP.getId() + "'").list();

        if (locInterface.isEmpty()) {
            return null;
        }

        return (CompPPProcessoSimples) locInterface.get(0);
    }

*/
}

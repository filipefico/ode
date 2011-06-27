package ode.processoPadrao.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cgd.CompPPDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirProcessoPadraoImpl extends NucleoAplCadastroBaseImpl<CompPP> 
implements AplDefinirProcessoPadrao{
	public static String BUSCAR_DIRETAMENTE = "0";
    public static String BUSCAR_A_PARTIR_DOS_REQUISTOS = "1";

    @Autowired
    private CompPPDAO compPPDAO;
    
    public CompPPDAO getCompPPDAO() {
		return compPPDAO;
	}



	public void setCompPPDAO(CompPPDAO compPPDAO) {
		this.compPPDAO = compPPDAO;
	}



	@Override
	protected void copiarValor(CompPP objetoFonte, CompPP objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
		//objetoDestino.setObjetivo(objetoFonte.getObjetivo());
	}



	@Override
	public NucleoDAOBase<CompPP> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return compPPDAO;
	}
    
    
    /*    @Autowired
	private InterfaceCompPPDAO interfaceCompPPDAO;

	public InterfaceCompPPDAO getInterfaceCompPPDAO() {
		return interfaceCompPPDAO;
	}

	public void setCompPPDAO(InterfaceCompPPDAO interfaceCompPPDAO) {
		this.interfaceCompPPDAO = interfaceCompPPDAO;
	}

	
	protected InterfaceCompPP alterarDados(InterfaceCompPP objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().merge(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}
    
    // Creates a new instance of AplDefinirProcessoPadrao 
    public AplDefinirProcessoPadraoImpl() {
    	System.out.println("NADA DISSO.------------>");
    }

    /** Verifica se ja existe o nome no momento do cadastro. Não pode haver dois nomes iguais. */
   /* public boolean validarNome(String parNome) {
        CompPPDAO daoProcessoPadrao = getCompPPDAO();
        CompPP locProcessoPadrao = daoProcessoPadrao.obterPorNome(parNome);
        if (locProcessoPadrao == null) {
            return true;
        } else {
            return false;
        }

    }

    // Definir CompPP. 
   public static CompPP definirCompPP(String nome, String descricao, String objetivos, Object tipo) {

        CompPP compPP;

       {
            // Cria Requisitos
            RequisitoCompPP requisitoCompPP = new RequisitoCompPP();

            // Salva Requisitos
      /*      RequisitoCompPPDAO requisitoCompPPDAO = (RequisitoCompPPDAO) DAOFactory.getDefaultDAO(RequisitoCompPP.class);
            DAOFactory.getDAOFactory().beginTransaction();
            requisitoCompPPDAO.salvar(requisitoCompPP);
           /DAOFactory.getDAOFactory().commit();

           if (tipo instanceof KProcesso) {

                // Cria a interface
                InterfaceCompPPProcessoSimples interfaceCompPPProcessoSimples = new InterfaceCompPPProcessoSimples();
                interfaceCompPPProcessoSimples.setNome(nome);
                interfaceCompPPProcessoSimples.setDescricao(descricao);
                interfaceCompPPProcessoSimples.setObjetivo(objetivos);
 

                // Salva Interface
          //      InterfaceCompPPProcessoSimplesDAO interfaceCompPPProcessoSimplesDAO = (InterfaceCompPPProcessoSimplesDAO) DAOFactory.getDefaultDAO(InterfaceCompPPProcessoSimples.class);
              //  DAOFactory.getDAOFactory().beginTransaction();
             //   interfaceCompPPProcessoSimplesDAO.salvar(interfaceCompPPProcessoSimples);
             //   DAOFactory.getDAOFactory().commit();

             
                // Cria componente
                compPP = new CompPPProcessoSimples();
                compPP.setInterfaceCompPP(interfaceCompPPProcessoSimples);
                ((CompPPProcessoSimples) compPP).setKProcesso((KProcesso) tipo);
                compPP.setRequisitoCompPP(requisitoCompPP);

                // Salva
         //       CompPPProcessoSimplesDAO compPPProcessoSimplesDAO = (CompPPProcessoSimplesDAO) DAOFactory.getDefaultDAO(CompPPProcessoSimples.class);
           //     DAOFactory.getDAOFactory().beginTransaction();
          //      compPPProcessoSimplesDAO.salvar((CompPPProcessoSimples) compPP);
             //   DAOFactory.getDAOFactory().commit();

                // Seta compPP e Salva interface novamente
         //       interfaceCompPPProcessoSimples.setCompPP(compPP);
        //        DAOFactory.getDAOFactory().beginTransaction();
          //      interfaceCompPPProcessoSimplesDAO.salvar(interfaceCompPPProcessoSimples);
           //     DAOFactory.getDAOFactory().commit();

            } else if (tipo instanceof KAtividade) {

                // Cria a interface
                InterfaceCompPPMacroatividade interfaceCompPPMacroatividade = new InterfaceCompPPMacroatividade();
                interfaceCompPPMacroatividade.setNome(nome);
                interfaceCompPPMacroatividade.setDescricao(descricao);
                interfaceCompPPMacroatividade.setObjetivo(objetivos);

                // Salva Interface
       /*         InterfaceCompPPMacroatividadeDAO interfaceCompPPMacroatividadeDAO = (InterfaceCompPPMacroatividadeDAO) DAOFactory.getDefaultDAO(InterfaceCompPPMacroatividade.class);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPMacroatividadeDAO.salvar(interfaceCompPPMacroatividade);
                DAOFactory.getDAOFactory().commit();


                // Cria a Atividade Processo Padrao
                AtividadeProcessoPadrao atividadeProcessoPadrao = new AtividadeProcessoPadrao();
                atividadeProcessoPadrao.setKAtividade((KAtividade) tipo);

                // Salva Atividade Processo Padrao
/*                AtividadeProcessoPadraoDAO atividadeProcessoPadraoDAO = (AtividadeProcessoPadraoDAO) DAOFactory.getDefaultDAO(AtividadeProcessoPadrao.class);
                DAOFactory.getDAOFactory().beginTransaction();
                atividadeProcessoPadraoDAO.salvar(atividadeProcessoPadrao);
                DAOFactory.getDAOFactory().commit();

                // Cria componente
                compPP = new CompPPMacroatividade();
                compPP.setInterfaceCompPP(interfaceCompPPMacroatividade);
                ((CompPPMacroatividade) compPP).setAtividade(atividadeProcessoPadrao);
                compPP.setRequisitoCompPP(requisitoCompPP);

                // Salva
              /*  CompPPMacroatividadeDAO compPPMacroatividadeDAO = (CompPPMacroatividadeDAO) DAOFactory.getDefaultDAO(CompPPMacroatividade.class);
                DAOFactory.getDAOFactory().beginTransaction();
                compPPMacroatividadeDAO.salvar((CompPPMacroatividade) compPP);
                DAOFactory.getDAOFactory().commit();

                // Seta compPP e Salva interface novamente
                interfaceCompPPMacroatividade.setCompPP(compPP);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPMacroatividadeDAO.salvar(interfaceCompPPMacroatividade);
                DAOFactory.getDAOFactory().commit();

            } else {

                // Cria a interface
                InterfaceCompPPProcessoComplexo interfaceCompPPProcessoComplexo = new InterfaceCompPPProcessoComplexo();
                interfaceCompPPProcessoComplexo.setNome(nome);
                interfaceCompPPProcessoComplexo.setDescricao(descricao);
                interfaceCompPPProcessoComplexo.setObjetivo(objetivos);                

                // Salva Interface
               /* InterfaceCompPPProcessoComplexoDAO interfaceCompPPProcessoComplexoDAO = (InterfaceCompPPProcessoComplexoDAO) DAOFactory.getDefaultDAO(InterfaceCompPPProcessoComplexo.class);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPProcessoComplexoDAO.salvar(interfaceCompPPProcessoComplexo);
                DAOFactory.getDAOFactory().commit();

             
                // Cria componente
                compPP = new CompPPProcessoComplexo();
                compPP.setInterfaceCompPP(interfaceCompPPProcessoComplexo);
                compPP.setRequisitoCompPP(requisitoCompPP);

                // Salva
//                CompPPProcessoComplexoDAO compPPProcessoComplexoDAO = (CompPPProcessoComplexoDAO) DAOFactory.getDefaultDAO(CompPPProcessoComplexo.class);
               // DAOFactory.getDAOFactory().beginTransaction(); 
          //      compPPProcessoComplexoDAO.salvar((CompPPProcessoComplexo) compPP);
               // DAOFactory.getDAOFactory().commit();

   /*             // Seta compPP e Salva interface novamente
                interfaceCompPPProcessoComplexo.setCompPP(compPP);
               // DAOFactory.getDAOFactory().beginTransaction();
                InterfaceCompPPProcessoComplexoDAO.salvar(interfaceCompPPProcessoComplexo);
               // DAOFactory.getDAOFactory().commit();*/


            /*}*/

        /*} catch (ExcecaoPersistencia e) { // Tambem trata ExcecaoConcorrencia
            DAOFactory.getDAOFactory().rollback();
            throw e;
        }

        System.out.println("CompPP salvo.------------>" + compPP);

       
        }
    }
    return compPP;
}
    	
    // Alterar CompPP. 
    public static CompPP alterarCompPP(CompPP parCompPP)
           /* throws ExcecaoProcessoPadrao {
    	System.out.println("NADA DISSO.------------>");
       /* try {

            // Obtem Requisitos
    	
            RequisitoCompPP requisitoCompPP = parCompPP.getRequisitoCompPP();

            // Salva Requisitos
     //       RequisitoCompPPDAO requisitoCompPPDAO = (RequisitoCompPPDAO) DAOFactory.getDefaultDAO(RequisitoCompPP.class);
          //  DAOFactory.getDAOFactory().beginTransaction();
      //      requisitoCompPPDAO.salvar(requisitoCompPP);
          //  DAOFactory.getDAOFactory().commit();

            if (parCompPP instanceof CompPPProcessoSimples) {

                // Obtem a interface
                InterfaceCompPPProcessoSimples interfaceCompPPProcessoSimples = (InterfaceCompPPProcessoSimples) parCompPP.getInterfaceCompPP();

                // Salva Inteface
    //            InterfaceCompPPProcessoSimplesDAO interfaceCompPPProcessoSimplesDAO = (InterfaceCompPPProcessoSimplesDAO) DAOFactory.getDefaultDAO(InterfaceCompPPProcessoSimples.class);
               // DAOFactory.getDAOFactory().beginTransaction();
     //           interfaceCompPPProcessoSimplesDAO.salvar(interfaceCompPPProcessoSimples);
               // DAOFactory.getDAOFactory().commit();

            } else if (parCompPP instanceof CompPPMacroatividade) {

                // Obtem a interface
                InterfaceCompPPMacroatividade interfaceCompPPMacroatividade = (InterfaceCompPPMacroatividade) parCompPP.getInterfaceCompPP();

                // Salva Inteface
            /*    InterfaceCompPPMacroatividadeDAO interfaceCompPPMacroatividadeDAO = (InterfaceCompPPMacroatividadeDAO) DAOFactory.getDefaultDAO(InterfaceCompPPMacroatividade.class);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPMacroatividadeDAO.salvar(interfaceCompPPMacroatividade);
                DAOFactory.getDAOFactory().commit();

            } else {

                // Obtem a interface
                InterfaceCompPPProcessoComplexo interfaceCompPPProcessoComplexo = (InterfaceCompPPProcessoComplexo) parCompPP.getInterfaceCompPP();

                // Salva Inteface
                //InterfaceCompPPProcessoComplexoDAO interfaceCompPPProcessoComplexoDAO = (InterfaceCompPPProcessoComplexoDAO) DAOFactory.getDefaultDAO(InterfaceCompPPProcessoComplexo.class);
               // DAOFactory.getDAOFactory().beginTransaction();
                //interfaceCompPPProcessoComplexoDAO.salvar(interfaceCompPPProcessoComplexo);
                //DAOFactory.getDAOFactory().commit();

            }

        /*} catch (ExcecaoPersistencia e) { // Tambem trata ExcecaoConcorrencia
            DAOFactory.getDAOFactory().rollback();
            throw e;
        }

        return parCompPP;
    }
*/
	}

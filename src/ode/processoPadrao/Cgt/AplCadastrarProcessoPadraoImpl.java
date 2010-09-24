package ode.processoPadrao.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;
import ode.processoPadrao.Cgd.CompPPProcessoComplexoDAO;
import ode.processoPadrao.Cdp.CompPPProcessoSimples;
import ode.processoPadrao.Cgd.CompPPProcessoSimplesDAO;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoComplexo;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoSimples;
import ode.processoPadrao.Cdp.RequisitoCompPP;
import ode.processoPadrao.Cgd.InterfaceCompPPProcessoComplexoDAO;
import ode.processoPadrao.Cgd.InterfaceCompPPProcessoSimplesDAO;
import ode.processoPadrao.Cgd.RequisitoCompPPDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AplCadastrarProcessoPadraoImpl extends NucleoAplCadastroBaseImpl<CompPP> implements AplCadastrarProcessoPadrao{
	public static String BUSCAR_DIRETAMENTE = "0";
    public static String BUSCAR_A_PARTIR_DOS_REQUISTOS = "1";

    /** Creates a new instance of AplDefinirProcessoPadrao */
    public AplCadastrarProcessoPadraoImpl() {
    }

    /** Verifica se ja existe o nome no momento do cadastro. Não pode haver dois nomes iguais. */
    public static boolean validarNome(String parNome) {
    /*    CompPPProcessoComplexoDAO daoProcessoPadrao = (CompPPProcessoComplexoDAO) DAOFactory.getDefaultDAO(CompPPProcessoComplexo.class);
        CompPPProcessoComplexo locProcessoPadrao = daoProcessoPadrao.obterPorNome(parNome);
        if (locProcessoPadrao == null) {
            return true;
        } else {
            return false;
        }*/
    	return true;
    }

    /* Definir CompPP. */
    public static CompPP definirCompPP(String nome, String descricao, String objetivos, Object tipo)
            /*throws ExcecaoProcessoPadrao*/ {

        CompPP compPP;

        /*try {*/

           /* // Cria a caracterizacao
            Caracterizacao caracterizacao = new Caracterizacao();

            // Salva a caracterizacao
            CaracterizacaoDAO caracterizacaoDAO = (CaracterizacaoDAO) DAOFactory.getDefaultDAO(Caracterizacao.class);
            DAOFactory.getDAOFactory().beginTransaction();
            caracterizacaoDAO.salvar(caracterizacao);
            DAOFactory.getDAOFactory().commit();
*/
            // Cria Requisitos
            RequisitoCompPP requisitoCompPP = new RequisitoCompPP();

            // Salva Requisitos
            //RequisitoCompPPDAO requisitoCompPPDAO = (RequisitoCompPPDAO) DAOFactory.getDefaultDAO(RequisitoCompPP.class);
            //DAOFactory.getDAOFactory().beginTransaction();
           // requisitoCompPPDAO.salvar(requisitoCompPP);
            //DAOFactory.getDAOFactory().commit();

            /*if (tipo instanceof KProcesso) {

                // Cria a interface
                InterfaceCompPPProcessoSimples interfaceCompPPProcessoSimples = new InterfaceCompPPProcessoSimples();
                interfaceCompPPProcessoSimples.setNome(nome);
                interfaceCompPPProcessoSimples.setDescricao(descricao);
                interfaceCompPPProcessoSimples.setObjetivo(objetivos);
                interfaceCompPPProcessoSimples.setCaracterizacao(caracterizacao);

                // Salva Interface
                InterfaceCompPPProcessoSimplesDAO interfaceCompPPProcessoSimplesDAO = (InterfaceCompPPProcessoSimplesDAO) DAOFactory.getDefaultDAO(InterfaceCompPPProcessoSimples.class);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPProcessoSimplesDAO.salvar(interfaceCompPPProcessoSimples);
                DAOFactory.getDAOFactory().commit();

                // Seta a interface e Salva a caracterizacao
                caracterizacao.setInterfaceCompPP(interfaceCompPPProcessoSimples);
                DAOFactory.getDAOFactory().beginTransaction();
                caracterizacaoDAO.salvar(caracterizacao);
                DAOFactory.getDAOFactory().commit();

                // Cria componente
                compPP = new CompPPProcessoSimples();
                compPP.setInterfaceCompPP(interfaceCompPPProcessoSimples);
                ((CompPPProcessoSimples) compPP).setKProcesso((KProcesso) tipo);
                compPP.setRequisitoCompPP(requisitoCompPP);

                // Salva
                CompPPProcessoSimplesDAO compPPProcessoSimplesDAO = (CompPPProcessoSimplesDAO) DAOFactory.getDefaultDAO(CompPPProcessoSimples.class);
                DAOFactory.getDAOFactory().beginTransaction();
                compPPProcessoSimplesDAO.salvar((CompPPProcessoSimples) compPP);
                DAOFactory.getDAOFactory().commit();

                // Seta compPP e Salva interface novamente
                interfaceCompPPProcessoSimples.setCompPP(compPP);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPProcessoSimplesDAO.salvar(interfaceCompPPProcessoSimples);
                DAOFactory.getDAOFactory().commit();

            } else if (tipo instanceof KAtividade) {

                // Cria a interface
                InterfaceCompPPMacroatividade interfaceCompPPMacroatividade = new InterfaceCompPPMacroatividade();
                interfaceCompPPMacroatividade.setNome(nome);
                interfaceCompPPMacroatividade.setDescricao(descricao);
                interfaceCompPPMacroatividade.setObjetivo(objetivos);
                interfaceCompPPMacroatividade.setCaracterizacao(caracterizacao);

                // Salva Interface
                InterfaceCompPPMacroatividadeDAO interfaceCompPPMacroatividadeDAO = (InterfaceCompPPMacroatividadeDAO) DAOFactory.getDefaultDAO(InterfaceCompPPMacroatividade.class);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPMacroatividadeDAO.salvar(interfaceCompPPMacroatividade);
                DAOFactory.getDAOFactory().commit();

                // Seta a interface e Salva a caracterizacao
                caracterizacao.setInterfaceCompPP(interfaceCompPPMacroatividade);
                DAOFactory.getDAOFactory().beginTransaction();
                caracterizacaoDAO.salvar(caracterizacao);
                DAOFactory.getDAOFactory().commit();

                // Cria a Atividade Processo Padrao
                AtividadeProcessoPadrao atividadeProcessoPadrao = new AtividadeProcessoPadrao();
                atividadeProcessoPadrao.setKAtividade((KAtividade) tipo);

                // Salva Atividade Processo Padrao
                AtividadeProcessoPadraoDAO atividadeProcessoPadraoDAO = (AtividadeProcessoPadraoDAO) DAOFactory.getDefaultDAO(AtividadeProcessoPadrao.class);
                DAOFactory.getDAOFactory().beginTransaction();
                atividadeProcessoPadraoDAO.salvar(atividadeProcessoPadrao);
                DAOFactory.getDAOFactory().commit();

                // Cria componente
                compPP = new CompPPMacroatividade();
                compPP.setInterfaceCompPP(interfaceCompPPMacroatividade);
                ((CompPPMacroatividade) compPP).setAtividade(atividadeProcessoPadrao);
                compPP.setRequisitoCompPP(requisitoCompPP);

                // Salva
                CompPPMacroatividadeDAO compPPMacroatividadeDAO = (CompPPMacroatividadeDAO) DAOFactory.getDefaultDAO(CompPPMacroatividade.class);
                DAOFactory.getDAOFactory().beginTransaction();
                compPPMacroatividadeDAO.salvar((CompPPMacroatividade) compPP);
                DAOFactory.getDAOFactory().commit();

                // Seta compPP e Salva interface novamente
                interfaceCompPPMacroatividade.setCompPP(compPP);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPMacroatividadeDAO.salvar(interfaceCompPPMacroatividade);
                DAOFactory.getDAOFactory().commit();

            } else {*/

                // Cria a interface
                InterfaceCompPPProcessoComplexo interfaceCompPPProcessoComplexo = new InterfaceCompPPProcessoComplexo();
                interfaceCompPPProcessoComplexo.setNome(nome);
                interfaceCompPPProcessoComplexo.setDescricao(descricao);
                interfaceCompPPProcessoComplexo.setObjetivo(objetivos);
                //interfaceCompPPProcessoComplexo.setCaracterizacao(caracterizacao);

                // Salva Interface
          //      InterfaceCompPPProcessoComplexoDAO interfaceCompPPProcessoComplexoDAO = (InterfaceCompPPProcessoComplexoDAO) DAOFactory.getDefaultDAO(InterfaceCompPPProcessoComplexo.class);
                //DAOFactory.getDAOFactory().beginTransaction();
          //      interfaceCompPPProcessoComplexoDAO.salvar(interfaceCompPPProcessoComplexo);
               // DAOFactory.getDAOFactory().commit();

              /*  // Seta a interface e Salva a caracterizacao
                caracterizacao.setInterfaceCompPP(interfaceCompPPProcessoComplexo);
                DAOFactory.getDAOFactory().beginTransaction();
                caracterizacaoDAO.salvar(caracterizacao);
                DAOFactory.getDAOFactory().commit();
*/
                // Cria componente
                compPP = new CompPPProcessoComplexo();
                compPP.setInterfaceCompPP(interfaceCompPPProcessoComplexo);
                compPP.setRequisitoCompPP(requisitoCompPP);

                // Salva
           //     CompPPProcessoComplexoDAO compPPProcessoComplexoDAO = (CompPPProcessoComplexoDAO) DAOFactory.getDefaultDAO(CompPPProcessoComplexo.class);
               // DAOFactory.getDAOFactory().beginTransaction();
          //      compPPProcessoComplexoDAO.salvar((CompPPProcessoComplexo) compPP);
               // DAOFactory.getDAOFactory().commit();

                // Seta compPP e Salva interface novamente
                interfaceCompPPProcessoComplexo.setCompPP(compPP);
               // DAOFactory.getDAOFactory().beginTransaction();
              //  interfaceCompPPProcessoComplexoDAO.salvar(interfaceCompPPProcessoComplexo);
               // DAOFactory.getDAOFactory().commit();


            /*}*/

        /*} catch (ExcecaoPersistencia e) { // Tambem trata ExcecaoConcorrencia
            DAOFactory.getDAOFactory().rollback();
            throw e;
        }*/

        System.out.println("CompPP salvo.------------>" + compPP);

        return compPP;
    }
    	
    /* Alterar CompPP. */
    public static CompPP alterarCompPP(CompPP parCompPP)
           /* throws ExcecaoProcessoPadrao*/ {
    	System.out.println("NADA DISSO.------------>");
       /* try {*/

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

            } /*else if (parCompPP instanceof CompPPMacroatividade) {

                // Obtem a interface
                InterfaceCompPPMacroatividade interfaceCompPPMacroatividade = (InterfaceCompPPMacroatividade) parCompPP.getInterfaceCompPP();

                // Salva Inteface
                InterfaceCompPPMacroatividadeDAO interfaceCompPPMacroatividadeDAO = (InterfaceCompPPMacroatividadeDAO) DAOFactory.getDefaultDAO(InterfaceCompPPMacroatividade.class);
                DAOFactory.getDAOFactory().beginTransaction();
                interfaceCompPPMacroatividadeDAO.salvar(interfaceCompPPMacroatividade);
                DAOFactory.getDAOFactory().commit();

            }*/ else {

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
        }*/

        return parCompPP;
    }

	@Override
	protected void copiarValor(CompPP objetoFonte, CompPP objetoDestino) {
		// TODO Auto-generated method stub
		
	}

}

package ode._infraestruturaBase.util;

import java.util.ArrayList;
import java.util.List;

import ode._controleFerramenta.ciu.CtrlFerramentaSoftwareCRUD;
import ode._controleProcesso.ciu.CtrlDefinicaoProcesso;
import ode._controleRecursoHumano.ciu.CtrlDefinirEquipe;
import ode._controleRecursoHumano.ciu.CtrlRecursoHumanoCRUD;
import ode.agenda.ciu.CtrlContatos;
import ode.alocacaoRecurso.ciu.CtrlAlocacaoRecurso;
import ode.atuacaoRecursoHumano.ciu.CtrlAtuacaoRHCRUD;
//import ode.caracterizacaoBusca.ciu.CtrlCaracterizarProjeto;
import ode.conhecimento.organizacao.ciu.CtrlKCompetenciaCRUD;
import ode.conhecimento.processo.ciu.CrtlTipoKArtefatoCRUD;
import ode.conhecimento.processo.ciu.CtrlKArtefatoCRUD;
import ode.conhecimento.processo.ciu.CtrlKAtividadeCRUD;
import ode.conhecimento.processo.ciu.CtrlKCategoriaProcessoCRUD;
import ode.conhecimento.processo.ciu.CtrlKDominioAplicacaoCRUD;
import ode.conhecimento.processo.ciu.CtrlKFerramentaSoftwareCRUD;
import ode.conhecimento.processo.ciu.CtrlKParadigmaCRUD;
import ode.conhecimento.processo.ciu.CtrlKProcedimentoCRUD;
import ode.conhecimento.processo.ciu.CtrlKProcessoCRUD;
import ode.conhecimento.processo.ciu.CtrlKRecursoHardwareCRUD;
import ode.conhecimento.processo.ciu.CtrlKRecursoHumanoCRUD;
import ode.conhecimento.processo.ciu.CtrlKTipoSoftwareCRUD;
import ode.conhecimento.requisito.cci.CtrlCRUDCategoriaRequisito;
import ode.conhecimentoMedicao.cci.CtrlKElementoMensuravelCRUD;
import ode.conhecimentoMedicao.cci.CtrlKEscalaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKMedidaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKMetodoAnaliticoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKPeriodicidadeCRUD;
import ode.conhecimentoMedicao.cci.CtrlKProcedimentoAnaliseMedicaoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKProcedimentoMedicaoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKUnidadeMedidaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKValorEscalaCRUD;
import ode.controleCaracteristica.ciu.CtrlCaracteristica;
import ode.controleCaracteristica.ciu.CtrlPerspectivaAnalise;
import ode.controleProjeto.ciu.CtrlProjetoCRUD;
import ode.controleProjeto.ciu.CtrlSelecionarProjeto;
import ode.controleUsuario.cdp.Funcionalidade;
import ode.controleUsuario.cdp.PerfilAcesso;
import ode.controleUsuario.ciu.CtrlUsuarioCRUD;
import ode.entidadeProblema.cci.CtrlEntidadeProblema;
import ode.gerenciaConhecimento.ciu.CtrlGerenciaConhecimento;
import ode.gerenciaConhecimento.ciu.CtrlTema;
import ode.gerenciaRequisitos.cci.CtrlCRUDRequisito;
import ode.gerenciaRequisitos.cci.CtrlDefinirRelacoesRastreabilidade;
import ode.gerenciaRequisitos.cci.CtrlGerarRelatorioRastreabilidade;
import ode.gerenciaRequisitos.cci.CtrlRastrearRequisitos;
import ode.medicao.EntidadeMensuravel.cci.CtrlEntidadeMensuravel;
import ode.medicao.analiseMedicao.ciu.CtrlAcaoCorretiva;
import ode.medicao.analiseMedicao.ciu.CtrlAnaliseMedicao;
import ode.medicao.analiseMedicao.ciu.CtrlMonitoramentoObjetivo;
import ode.medicao.execucaoMedicao.ciu.CtrlMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoProjeto;
import ode.medicao.planejamentoMedicao.cci.CtrlValorReferenciaOrganizacao;
import ode.medicao.planejamentoMedicao.cci.CtrlValorReferenciaProjeto;
import ode.middlewareIssueTracker.ciu.CtrlConfiguracaoMantis;
import ode.problema.ciu.CtrlKCategoriaProblemaCRUD;
import ode.problema.ciu.CtrlKCausaCRUD;
import ode.problema.ciu.CtrlKCriterioSelecaoSolucaoCRUD;
import ode.problema.ciu.CtrlKProblemaCRUD;
import ode.problema.ciu.CtrlKSolucaoCRUD;
import ode.processoPadrao.ciu.CtrlDefinirProcessoPadrao;
import ode.pgds.cci.CtrlImportarProjeto;
import ode.resolucaoProblema.ciu.CtrlOcorrenciaProblema;
import ode.resolucaoProblema.ciu.CtrlRegistrarResultadoSolucao;
import ode.resolucaoProblema.ciu.CtrlResolverProblema;
import ode.processoPadrao.ciu.CtrlDefinirProcessoPadrao;
import ode.processoProjeto.ciu.CtrlDefinirProcessoProjeto;
import ode.uml.cci.CtrlCRUDCasoUso;
import ode.uml.cci.CtrlCRUDClasse;
import ode.uml.cci.CtrlCRUDPacote;

public class FuncionalidadesMenu {

public static List<Funcionalidade> obterFuncionalidades() {
		
		/*
		 * Seguir o padr�o para cadastrar as funcionalidades:
		 *
		 * Itens para a raiz do menu entram em funcionalidades.add()
		 * Itens em n�vel hier�rquico inferior entram em .addSubfuncionalidade() do item pai
		 * 
		 * Criar cada funcionalidade com o m�todo criar(String nomeFuncionalidade)
		 * 
		 *  Atribuir a classe controladora que cont�m o m�todo iniciar() atrav�s de setCtrl()
		 *  Atribuir as eventuais permiss�es al�m do Perfil de Acesso Administrador atrav�s de permitir()
		 */

		List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
		
		funcionalidades.add(criar("Projeto").permitir(PerfilAcesso.Desenvolvedor)
			.addSubfuncionalidade(criar("Selecionar Projeto").setCtrl(CtrlSelecionarProjeto.class).permitir(PerfilAcesso.Desenvolvedor))
			.addSubfuncionalidade(criar("Cadastrar Projeto").setCtrl(CtrlProjetoCRUD.class))
			//.addSubfuncionalidade(criar("Caracterizar Projeto").setCtrl(CtrlCaracterizarProjeto.class).setDisponivelApenasParaProjetosAbertos(true))
			.addSubfuncionalidade(criar("Definir Equipe (Vers�o Simplificada)").setCtrl(CtrlDefinirEquipe.class).setDisponivelApenasParaProjetosAbertos(true))
			.addSubfuncionalidade(criar("Definir Equipe (Vers�o Completa)").setCtrl(ode.atuacaoRecursoHumano.ciu.CtrlDefinirEquipe.class).setDisponivelApenasParaProjetosAbertos(true))
			
		);
		
		funcionalidades.add(criar("Administra��o")
			.addSubfuncionalidade(criar("Usu�rios").setCtrl(CtrlUsuarioCRUD.class))
			.addSubfuncionalidade(criar("Configura��o Mantis").setCtrl(CtrlConfiguracaoMantis.class))
		);
		
		funcionalidades.add(criar("Recursos")
			.addSubfuncionalidade(criar("Recursos Humanos (Vers�o Simplificada)").setCtrl(CtrlRecursoHumanoCRUD.class))
			.addSubfuncionalidade(criar("Recursos Humanos (Vers�o Completa)").setCtrl(CtrlAtuacaoRHCRUD.class))
			.addSubfuncionalidade(criar("Ferramentas de Software").setCtrl(CtrlFerramentaSoftwareCRUD.class))
		);
		
		funcionalidades.add(criar("Agenda")
				//.addSubfuncionalidade(criar("Aloca��o de Recursos").setCtrl(CtrlAlocacaoRecurso.class).setDisponivelApenasParaProjetosAbertos(true))
				.addSubfuncionalidade(criar("Contatos").setCtrl(CtrlContatos.class))
			);
			
		funcionalidades.add(criar("Conhecimento")
			.addSubfuncionalidade(criar("Recursos")
				.addSubfuncionalidade(criar("Recursos Humanos").setCtrl(CtrlKRecursoHumanoCRUD.class))
				.addSubfuncionalidade(criar("Recursos de Hardware").setCtrl(CtrlKRecursoHardwareCRUD.class))
				.addSubfuncionalidade(criar("Ferramentas de Software").setCtrl(CtrlKFerramentaSoftwareCRUD.class))
			)
			.addSubfuncionalidade(criar("Processos")
				.addSubfuncionalidade(criar("Paradigmas").setCtrl(CtrlKParadigmaCRUD.class))
				.addSubfuncionalidade(criar("Tipos de Software").setCtrl(CtrlKTipoSoftwareCRUD.class))
				.addSubfuncionalidade(criar("Tipos de Artefato").setCtrl(CrtlTipoKArtefatoCRUD.class))
				.addSubfuncionalidade(criar("Artefatos").setCtrl(CtrlKArtefatoCRUD.class))
				.addSubfuncionalidade(criar("Dom�nios de Aplica��o").setCtrl(CtrlKDominioAplicacaoCRUD.class))
				.addSubfuncionalidade(criar("Categorias de Processo").setCtrl(CtrlKCategoriaProcessoCRUD.class))
				.addSubfuncionalidade(criar("Processos").setCtrl(CtrlKProcessoCRUD.class))
				.addSubfuncionalidade(criar("Atividades").setCtrl(CtrlKAtividadeCRUD.class))
				.addSubfuncionalidade(criar("Procedimentos").setCtrl(CtrlKProcedimentoCRUD.class))
			)
			.addSubfuncionalidade(criar("Organiza��o")
				.addSubfuncionalidade(criar("Compet�ncias").setCtrl(CtrlKCompetenciaCRUD.class))
			)
			.addSubfuncionalidade(criar("Requisito")
				.addSubfuncionalidade(criar("Categoria").setCtrl(CtrlCRUDCategoriaRequisito.class))
			)
			.addSubfuncionalidade(criar("Medi��o")
					.addSubfuncionalidade(criar("Medida")
							.addSubfuncionalidade(criar("Medida").setCtrl(CtrlKMedidaCRUD.class))
							.addSubfuncionalidade(criar("Unidade de Medida").setCtrl(CtrlKUnidadeMedidaCRUD.class))
							.addSubfuncionalidade(criar("Elemento Mensur�vel").setCtrl(CtrlKElementoMensuravelCRUD.class))
							)
					.addSubfuncionalidade(criar("Escala")
							.addSubfuncionalidade(criar("Escala").setCtrl(CtrlKEscalaCRUD.class))
							.addSubfuncionalidade(criar("Valor de Escala").setCtrl(CtrlKValorEscalaCRUD.class))
							)
					.addSubfuncionalidade(criar("Procedimentos")
							.addSubfuncionalidade(criar("Procedimento de Medicao").setCtrl(CtrlKProcedimentoMedicaoCRUD.class))
							.addSubfuncionalidade(criar("Procedimento de Analise de Medicao").setCtrl(CtrlKProcedimentoAnaliseMedicaoCRUD.class))
							.addSubfuncionalidade(criar("Metodo Analitico").setCtrl(CtrlKMetodoAnaliticoCRUD.class))
							)
					.addSubfuncionalidade(criar("Periodicidade").setCtrl(CtrlKPeriodicidadeCRUD.class))
				)
				.addSubfuncionalidade(criar("Problema")
				.addSubfuncionalidade(criar("Problema").setCtrl(CtrlKProblemaCRUD.class))
				.addSubfuncionalidade(criar("Solu��o").setCtrl(CtrlKSolucaoCRUD.class))
				.addSubfuncionalidade(criar("Causa").setCtrl(CtrlKCausaCRUD.class))
			    .addSubfuncionalidade(criar("Categoria Problema").setCtrl(CtrlKCategoriaProblemaCRUD.class))
			    .addSubfuncionalidade(criar("Crit�rio Sele��o Solu��o").setCtrl(CtrlKCriterioSelecaoSolucaoCRUD.class))

			)
		);
	/*	funcionalidades.add(criar("Entidade Problema")
				.addSubfuncionalidade(criar("Entidade Problema").setCtrl(CtrlEntidadeProblema.class)
					)
		);
		funcionalidades.add(criar("ResolucaoProblema")
				.addSubfuncionalidade(criar("Ocorrencia Problema").setCtrl(CtrlOcorrenciaProblema.class))
				.addSubfuncionalidade(criar("Resolver Problema").setCtrl(CtrlResolverProblema.class))
				.addSubfuncionalidade(criar("Registrar Resultado Solucao").setCtrl(CtrlRegistrarResultadoSolucao.class))
				
		);*/
		
		funcionalidades.add(criar("Medi��o")
				.addSubfuncionalidade(criar("Entidade Mensur�veis").setCtrl(CtrlEntidadeMensuravel.class)
					)
				.addSubfuncionalidade(criar("Planejamento")
					.addSubfuncionalidade(criar("Elaborar Plano de Medi��o da Organiza��o").setCtrl(CtrlPlanoMedicaoOrganizacao.class))
					.addSubfuncionalidade(criar("Elaborar Plano de Medi��o do Projeto").setCtrl(CtrlPlanoMedicaoProjeto.class))
					.addSubfuncionalidade(criar("Estabelecer Valores de Refer�ncia para Organiza��o").setCtrl(CtrlValorReferenciaOrganizacao.class))
					.addSubfuncionalidade(criar("Estabelecer Valores de Refer�ncia para Projeto").setCtrl(CtrlValorReferenciaProjeto.class))
				)
				.addSubfuncionalidade(criar("Execu��o")
						.addSubfuncionalidade(criar("Coletar Dados").setCtrl(CtrlMedicao.class))
				)
				.addSubfuncionalidade(criar("Analise")
						.addSubfuncionalidade(criar("Analisar Medi��o").setCtrl(CtrlAnaliseMedicao.class))
						.addSubfuncionalidade(criar("Monitorar Objetivos").setCtrl(CtrlMonitoramentoObjetivo.class))
						.addSubfuncionalidade(criar("Registrar A��es Corretivas").setCtrl(CtrlAcaoCorretiva.class))
						)
		);
		
		/*funcionalidades.add(criar("Processo Padr�o")
			.addSubfuncionalidade(criar("Componentes de Processo Padr�o").setCtrl(CtrlDefinirProcessoPadrao.class))
		);*/
		
		//funcionalidades.add(criar("Agenda").setCtrl(ode.agenda.ciu.CtrlAgenda.class));
		
		funcionalidades.add(criar("Defini��o de Processos")
				.addSubfuncionalidade(criar("Processo Padr�o").setCtrl(CtrlDefinirProcessoPadrao.class))
				.addSubfuncionalidade(criar("Processo de Projeto").setCtrl(CtrlDefinirProcessoProjeto.class).setDisponivelApenasParaProjetosAbertos(true))
			);
		
		funcionalidades.add(criar("Ferramentas")
			.addSubfuncionalidade(criar("Aloca��o de Recursos").setCtrl(CtrlAlocacaoRecurso.class).setDisponivelApenasParaProjetosAbertos(true))
		);
		

		funcionalidades.add(criar("Ger�ncia de Conhecimento")
			.addSubfuncionalidade(criar("Portal").setCtrl(CtrlGerenciaConhecimento.class))
			.addSubfuncionalidade(criar("Tema").setCtrl(CtrlTema.class))
		);
		

		funcionalidades.add(criar("uml").setDisponivelApenasParaProjetosAbertos(true)
			.addSubfuncionalidade(criar("Cadastrar Pacote").setCtrl(CtrlCRUDPacote.class))
			.addSubfuncionalidade(criar("Cadastrar Classe").setCtrl(CtrlCRUDClasse.class))
			.addSubfuncionalidade(criar("Cadastrar Caso de Uso").setCtrl(CtrlCRUDCasoUso.class))
		);
			
		funcionalidades.add(criar("Ger�ncia de Requisitos").setDisponivelApenasParaProjetosAbertos(true)
			.addSubfuncionalidade(criar("Cadastrar Requisito").setCtrl(CtrlCRUDRequisito.class))
			.addSubfuncionalidade(criar("Definir Rela��es de Rastreabilidade").setCtrl(CtrlDefinirRelacoesRastreabilidade.class))
			.addSubfuncionalidade(criar("Rastrear Requisitos").setCtrl(CtrlRastrearRequisitos.class))
			.addSubfuncionalidade(criar("Gerar Relat�rio").setCtrl(CtrlGerarRelatorioRastreabilidade.class))
			.addSubfuncionalidade(criar("Importar Projeto - PGDS").setCtrl(CtrlImportarProjeto.class))
		);
		
		funcionalidades.add(criar("Organiza��o")
				.addSubfuncionalidade(criar("Caracteristicas").setCtrl(CtrlCaracteristica.class))
				.addSubfuncionalidade(criar("Perspectivas de Analise").setCtrl(CtrlPerspectivaAnalise.class))
			);
		
		return funcionalidades;

	}
	
	/*
	 * Fun��o auxiliar para o m�todo obterFuncionalidades
	 */

	private static Funcionalidade criar(String s) {
		Funcionalidade f = new Funcionalidade(s);
		f.permitir(PerfilAcesso.Administrador);
		return f;
	}
}

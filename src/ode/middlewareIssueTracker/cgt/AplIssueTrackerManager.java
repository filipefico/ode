package ode.middlewareIssueTracker.cgt;

import java.net.MalformedURLException;
import java.net.URL;

import org.mantisbt.connect.AccessLevel;
import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.axis.MCSession;
import org.mantisbt.connect.model.IProject;
import org.mantisbt.connect.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.middlewareIssueTracker.cdp.ConfiguracaoMantis;
import ode.middlewareIssueTracker.cdp.Issue;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;
import ode.middlewareIssueTracker.cgd.IssueDAO;


@Service
public class AplIssueTrackerManager extends AplCRUD<Issue> {
	
	@Autowired
	IssueDAO issueDAO;
	
	@Autowired
	AplConfiguracaoMantis aplConfiguracaoMantis;
	
	@Autowired
	AplUsuarioMantis aplUsuarioMantis;
	
	@Autowired
	ProjetoDAO projetoDAO;
	
	
	
	@Override
	public DAOBase<Issue> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return issueDAO;
	}
	 

	private IMCSession criaSessaoPadrao(){
		
		//pega o configuracao do mantis no banco
		URL url = null;
		try {
			
			ConfiguracaoMantis configuracaoMantis = aplConfiguracaoMantis.recuperarConfiguracaoMantisPadrao();
			UsuarioMantis usuarioMantisPadrao = configuracaoMantis.getUsuarioMantisPadrao();
			
			url = new URL(configuracaoMantis.getUrl());

			IMCSession sessao = new MCSession(url,usuarioMantisPadrao.getUsuarioMantis(), usuarioMantisPadrao.getSenhaMantis());
			
			return sessao;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}
	 

	private IMCSession criaSessaoComum(){
		
		//pega o configuracao do mantis no banco
		URL url = null;
		try {
			
			ConfiguracaoMantis configuracaoMantis = aplConfiguracaoMantis.recuperarConfiguracaoMantisPadrao();
			UsuarioMantis usuarioMantis = aplUsuarioMantis.recuperarUsuarioMantisAtual();
			
			url = new URL(configuracaoMantis.getUrl());

			IMCSession sessao = new MCSession(url,usuarioMantis.getUsuarioMantis(), usuarioMantis.getSenhaMantis());
			
			return sessao;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}
	
	public Projeto criarProjetoODE(IProject iProject) {
		
		Projeto projeto = new Projeto();
		
		projeto.setId(iProject.getId());
		projeto.setNome(iProject.getName());
		projeto.setDescricao(iProject.getDescription());
		
		return projeto;
	}
	
	private IProject criarProjetoMantis(Projeto projeto){
		
		Project project = new Project();
		
		project.setId(projeto.getId().intValue()); //verificar pois um eh int e o outro long
		project.setName(projeto.getNome());
		project.setDesription(projeto.getDescricao());
		
		project.setAccessLevelMin(AccessLevel.VIEWER);
		project.setEnabled(true);
		
		IProject iProject = project;	
		
		return iProject;
	}
	
	//vai usar o usuario do mantis guardado no ode
	public void insereProjetoMantis(Projeto projeto){
		
		IMCSession sessao = criaSessaoPadrao();
		IProject iProject = criarProjetoMantis(projeto);
		iProject.setPrivate(true);
		
		
		try {
			sessao.addProject(iProject);
		} catch (MCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void excluirProjetoMantis(Projeto projeto) {
		
		
		IMCSession sessao = criaSessaoPadrao();
		
		try {
			IProject iProject = sessao.getProject(projeto.getNome());
						
			if(iProject != null) {
				sessao.deleteProject(iProject.getId());
			}
			
		} catch (MCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void inserirIssue(Issue issue){
		
			issue.setProjeto(projetoDAO.recuperarPorNome(issue.getNomeProjeto()));
			
			System.out.println("Issue");
			System.out.println(issue);

			
			try {
				this.salvar(issue);
			} catch (NucleoRegraNegocioExcecao e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	//QUANDO O USARIO FOR CADASTRADO VERIFICAR SE JÁ EXISTE UMA ISSUE ALOCADO PARA ELE NO MANTISBT
	
}

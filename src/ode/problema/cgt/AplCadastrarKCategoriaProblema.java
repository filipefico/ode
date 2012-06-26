package ode.problema.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.problema.cdp.KCategoriaProblema;
import ode.problema.cgd.KCategoriaProblemaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional (rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCategoriaProblema extends AplCRUD<KCategoriaProblema>  {
@Autowired
KCategoriaProblemaDAO kcategoriaproblema;

//protected void antesExcluir(T objeto) throws NucleoRegraNegocioExcecao {
//lembrar antes de excluir uma categoria verificar aquelas que possuem problemas associados não podem ser excluídas.
//}


//protected void antesSalvar(T objeto) throws NucleoRegraNegocioExcecao {
//lembrar que caso a categoria esteja associada a um problema , é exibida uma mensagem informando isso ao usuário e é solicitada a confirmação da alteração.
//}
	@Override
	public DAOBase<KCategoriaProblema> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return kcategoriaproblema;
	}


}
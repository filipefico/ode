package ode.controleCaracteristica.cgt;

import java.util.Collection;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.CaracteristicaValorNaoOrdenado;
import ode.controleCaracteristica.cdp.CaracteristicaValorOrdenado;
import ode.controleCaracteristica.cgd.CaracteristicaDAO;
import ode.controleCaracteristica.cgd.CaracteristicaValorNaoOrdenadoDAO;
import ode.controleCaracteristica.cgd.CaracteristicaValorOrdenadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zul.Messagebox;

@Service("AplCadastrarCaracteristica")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarCaracteristica extends AplCRUD<Caracteristica>{

	@Autowired
	private CaracteristicaDAO caracteristicaDAO;
	
	@Autowired
	private CaracteristicaValorOrdenadoDAO caracteristicaValorOrdenadoDAO;
	
	@Autowired
	private CaracteristicaValorNaoOrdenadoDAO caracteristicaValorNaoOrdenadoDAO;
	
	@Override
	public DAOBase<Caracteristica> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CaracteristicaDAO getCaracteristicaDAO() {
		return  caracteristicaDAO;
	}

	public void setCaracteristicaDAO(CaracteristicaDAO caracteristicaDAO) {
		this.caracteristicaDAO = caracteristicaDAO;
	}

	protected Caracteristica alterarDados(Caracteristica objeto) throws NucleoRegraNegocioExcecao {
		
		if (objeto instanceof CaracteristicaValorOrdenado){
			getCaracteristicaValorOrdenadoDAO().atualizar((CaracteristicaValorOrdenado)objeto);
		}else{
			getCaracteristicaValorNaoOrdenadoDAO().atualizar((CaracteristicaValorNaoOrdenado)objeto);
		}
		//Retorna objetoPersistido;
		return objeto;
	}
	
	public CaracteristicaValorOrdenadoDAO getCaracteristicaValorOrdenadoDAO() {
		return  caracteristicaValorOrdenadoDAO;
	}

	public void setCaracteristicaValorOrdenadoDAO(CaracteristicaValorOrdenadoDAO caracteristicaValorOrdenadoDAO) {
		this.caracteristicaValorOrdenadoDAO = caracteristicaValorOrdenadoDAO;
	}


	public CaracteristicaValorNaoOrdenadoDAO getCaracteristicaValorNaoOrdenadoDAO() {
		return  caracteristicaValorNaoOrdenadoDAO;
	}

	public void setCaracteristicaValorNaoOrdenadoDAO(CaracteristicaValorNaoOrdenadoDAO caracteristicaValorNaoOrdenadoDAO) {
		this.caracteristicaValorNaoOrdenadoDAO = caracteristicaValorNaoOrdenadoDAO;
	}

	
	@Override
	public void excluir(Caracteristica objeto) throws NucleoRegraNegocioExcecao {
				
				if (objeto instanceof CaracteristicaValorOrdenado){
					getCaracteristicaValorOrdenadoDAO().excluir((CaracteristicaValorOrdenado)objeto);
				}else{
					getCaracteristicaValorNaoOrdenadoDAO().excluir((CaracteristicaValorNaoOrdenado)objeto);
				}
	}

	public Caracteristica recuperarPorIdValorOrdenado(Long id) {
		return getCaracteristicaValorOrdenadoDAO().recuperarPorId(id);
	}
	public Caracteristica recuperarPorIdValorNaoOrdenado(Long id) {
		return getCaracteristicaValorNaoOrdenadoDAO().recuperarPorId(id);
	}

	public Collection<CaracteristicaValorOrdenado> recuperarTodosValoresOrdenados() {
		return getCaracteristicaValorOrdenadoDAO().recuperarTodos();
	}
	
	public Collection<CaracteristicaValorNaoOrdenado> recuperarTodosValoresNaoOrdenados() {
		return getCaracteristicaValorNaoOrdenadoDAO().recuperarTodos();
	}
	
	public Collection<Caracteristica> recuperarTodos() {
		return caracteristicaDAO.recuperarTodos();
	}
	protected Caracteristica incluirNovo(Caracteristica objeto) throws NucleoRegraNegocioExcecao {

		if (objeto instanceof CaracteristicaValorOrdenado){
			getCaracteristicaValorOrdenadoDAO().salvar((CaracteristicaValorOrdenado)objeto);
		}else{
			getCaracteristicaValorNaoOrdenadoDAO().salvar((CaracteristicaValorNaoOrdenado)objeto);
		}

		return objeto;
	}	

	public void mostrarJanelaInformacao(String mensagem)
			throws InterruptedException {

				Messagebox.show(mensagem, NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_INFORMACAO), Messagebox.OK,
						Messagebox.INFORMATION);

			}
	
}
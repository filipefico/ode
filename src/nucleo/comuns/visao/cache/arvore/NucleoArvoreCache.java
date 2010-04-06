package nucleo.comuns.visao.cache.arvore;

import java.util.ArrayList;
import java.util.List;

public class NucleoArvoreCache {

	List<NucleoItemArvoreCache> raizesArvore = new ArrayList<NucleoItemArvoreCache>();

	public List<NucleoItemArvoreCache> getRaizesArvore() {
		return raizesArvore;
	}

	public void setRaizesArvore(List<NucleoItemArvoreCache> raizesArvore) {
		this.raizesArvore = raizesArvore;
	}

}

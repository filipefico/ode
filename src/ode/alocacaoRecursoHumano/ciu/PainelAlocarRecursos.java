package ode.alocacaoRecursoHumano.ciu;

import java.util.Collection;

import ode._controleProcesso.cdp.Atividade;
import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._infraestruturaBase.ciu.ImprovedTreeNode;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;

public class PainelAlocarRecursos extends Hbox {
	
	private static final long serialVersionUID = 1L;
	
	public PainelAlocarRecursos(CtrlAlocacaoRecursoHumano ctrl) {
		Collection<ProcessoProjetoEspecifico> listaProc = ctrl.listarProcessosProjetoEspecifico();
		if(listaProc.size()>0) {
			Tree tree = new Tree();
			tree.setParent(this);
			ImprovedTreeNode root = new ImprovedTreeNode(null);
			tree.setItemRenderer(new AlocacaoRecursoItemRenderer());
			tree.setModel(new DefaultTreeModel(root));
			tree.setWidth("400px");
						
			for (ProcessoProjetoEspecifico processo : listaProc) {
                         
				ImprovedTreeNode nodeProcesso = new ImprovedTreeNode(processo);
                
                //obterAtividades por Processo
                Collection<Atividade> atividades = ctrl.listarAtividadesProcesso(processo);
                
                for (Atividade atividade : atividades) {
                	ImprovedTreeNode nodeAtividade = new ImprovedTreeNode(atividade);
                    nodeProcesso.add(nodeAtividade);
                    //this.inserirKRecursos(locNo);
                    //this.inserirSubAtividades(locNo);
                }
                
                root.add(nodeProcesso);
            }
		}
		else {
			Label label = new Label("Este projeto não possui atividades cadastradas.");
			label.setParent(this);
		}
	}
	
	public class AlocacaoRecursoItemRenderer implements TreeitemRenderer {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void render(final Treeitem treeItem, Object treeNode) throws Exception {
			ImprovedTreeNode ctn = (ImprovedTreeNode) treeNode;
            Object data = ctn.getData();
            
            //treeItem.setValue(ctn);

            if (data instanceof ProcessoProjetoEspecifico) {
            	treeItem.setLabel(((ProcessoProjetoEspecifico) data).getNome());
            }
            if (data instanceof Atividade) {
            	treeItem.setImage("/imagens/atividade.jpeg");
            	treeItem.setLabel(((Atividade) data).getNome());
            }
            else if (data instanceof KRecursoHumano) {
            	treeItem.setImage("/imagens/recursoHumano.gif");
            	treeItem.setLabel(((KRecursoHumano) data).getNome());
            }
        }

	}
	
}
